package dev.fujioka.fagnerlima.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Set;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import dev.fujioka.fagnerlima.domain.Client;
import dev.fujioka.fagnerlima.domain.Product;
import dev.fujioka.fagnerlima.domain.Sale;
import dev.fujioka.fagnerlima.domain.SaleItem;
import dev.fujioka.fagnerlima.domain.Store;
import dev.fujioka.fagnerlima.domain.User;
import dev.fujioka.fagnerlima.exception.EntityNotFoundException;
import dev.fujioka.fagnerlima.exception.ProductException;
import dev.fujioka.fagnerlima.exception.SaleException;
import dev.fujioka.fagnerlima.repository.ClientRepository;
import dev.fujioka.fagnerlima.repository.ProductRepository;
import dev.fujioka.fagnerlima.repository.SaleRepository;
import dev.fujioka.fagnerlima.repository.StoreRepository;

@ActiveProfiles("test")
@SpringBootTest
public class SaleServiceTest {

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @AfterEach
    public void tearDown() throws Exception {
        saleRepository.deleteAll();
        storeRepository.deleteAll();
        clientRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    public void testSave() {
        
    }

    @Test
    public void testSave_whenStoreNotActive() {
        Set<SaleItem> items = Set.of(generateSaleItem(10, 2));
        Sale sale = generateSale(items);

        sale.getStore().setActive(false);
        storeService.save(sale.getStore());

        assertThrows(SaleException.class, () -> saleService.save(sale), "Store not active");
    }

    @Test
    public void testSave_whenStoreNotFound() {
        Set<SaleItem> items = Set.of(generateSaleItem(10, 2));
        Sale sale = generateSale(items);

        Store store = new Store();
        store.setId(100L);
        sale.setStore(store);

        assertThrows(EntityNotFoundException.class, () -> saleService.save(sale), "Store not found");
    }

    @Test
    public void testSave_whenClientNotActive() {
        Set<SaleItem> items = Set.of(generateSaleItem(10, 2));
        Sale sale = generateSale(items);

        sale.getClient().setActive(false);
        clientService.save(sale.getClient());

        assertThrows(SaleException.class, () -> saleService.save(sale), "Client not active");
    }

    @Test
    public void testSave_whenClientNotFound() {
        Set<SaleItem> items = Set.of(generateSaleItem(10, 2));
        Sale sale = generateSale(items);

        Client client = new Client();
        client.setId(100L);
        sale.setClient(client);

        assertThrows(EntityNotFoundException.class, () -> saleService.save(sale), "Client not found");
    }

    @Test
    public void testSave_whenProductNotFound() {
        Product product = new Product();
        product.setId(100L);
        Set<SaleItem> items = Set.of(new SaleItem(product, 10));
        Sale sale = generateSale(items);

        assertThrows(EntityNotFoundException.class, () -> saleService.save(sale), "Product not found");
    }

    @Test
    public void testSave_whenProductAmountUnavailable() {
        Set<SaleItem> items = Set.of(generateSaleItem(10, 20));
        Sale sale = generateSale(items);

        assertThrows(ProductException.class, () -> saleService.save(sale));
    }

    private Sale generateSale(Set<SaleItem> items) {
        Store store = storeService.save(new Store(RandomString.make(10), true)).get();
        User user = userService.save(new User(RandomString.make(10), RandomString.make(10), RandomString.make(10), RandomString.make(10)))
                .get();
        Client client = clientService.save(new Client(RandomString.make(10), RandomString.make(10), true)).get();

        return new Sale(store, user, client, items);
    }

    private SaleItem generateSaleItem(Integer amountProduct, Integer amountSale) {
        Product product = productService.save(new Product(RandomString.make(10), RandomString.make(10), amountProduct)).get();

        return new SaleItem(product, amountSale);
    }

}
