package dev.fujioka.fagnerlima.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;

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
        SaleItem saleItem1 = generateSaleItem(10, 2);
        SaleItem saleItem2 = generateSaleItem(5, 5);
        List<SaleItem> items = List.of(saleItem1, saleItem2);
        Sale sale = generateSale(items);

        saleService.save(sale).get();

        Product product1 = productService.findById(saleItem1.getProduct().getId()).get();
        Product product2 = productService.findById(saleItem2.getProduct().getId()).get();

        assertThat(sale.getDate().toLocalDate()).isEqualTo(LocalDate.now());
        assertThat(product1.getQuantity()).isEqualTo(8);
        assertThat(product2.getQuantity()).isEqualTo(0);
    }

    @Test
    public void testDelete() {
        SaleItem saleItem1 = generateSaleItem(5, 2);
        SaleItem saleItem2 = generateSaleItem(5, 5);
        List<SaleItem> items = List.of(saleItem1, saleItem2);
        Sale sale = generateSale(items);

        saleService.save(sale).get();
        saleService.deleteById(sale.getId());

        Product product1 = productService.findById(saleItem1.getProduct().getId()).get();
        Product product2 = productService.findById(saleItem2.getProduct().getId()).get();

        assertThat(product1.getQuantity()).isEqualTo(5);
        assertThat(product2.getQuantity()).isEqualTo(5);
    }

    @Test
    public void testSave_whenStoreNotActive() {
        List<SaleItem> items = List.of(generateSaleItem(10, 2));
        Sale sale = generateSale(items);

        sale.getStore().setActive(false);
        storeService.save(sale.getStore());

        assertThrows(SaleException.class, () -> saleService.save(sale), "Store not active");
    }

    @Test
    public void testSave_whenStoreNotFound() {
        List<SaleItem> items = List.of(generateSaleItem(10, 2));
        Sale sale = generateSale(items);

        Store store = new Store();
        store.setId(100L);
        sale.setStore(store);

        assertThrows(EntityNotFoundException.class, () -> saleService.save(sale), "Store not found");
    }

    @Test
    public void testSave_whenClientNotActive() {
        List<SaleItem> items = List.of(generateSaleItem(10, 2));
        Sale sale = generateSale(items);

        sale.getClient().setActive(false);
        clientService.save(sale.getClient());

        assertThrows(SaleException.class, () -> saleService.save(sale), "Client not active");
    }

    @Test
    public void testSave_whenClientNotFound() {
        List<SaleItem> items = List.of(generateSaleItem(10, 2));
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
        List<SaleItem> items = List.of(new SaleItem(product, 10));
        Sale sale = generateSale(items);

        assertThrows(EntityNotFoundException.class, () -> saleService.save(sale), "Product not found");
    }

    @Test
    public void testSave_whenProductQuantityUnavailable() {
        List<SaleItem> items = List.of(generateSaleItem(10, 20));
        Sale sale = generateSale(items);

        assertThrows(ProductException.class, () -> saleService.save(sale));
    }

    private Sale generateSale(List<SaleItem> items) {
        Store store = storeService.save(new Store(RandomString.make(10), true)).get();
        User user = userService.save(new User(RandomString.make(10), RandomString.make(10), RandomString.make(10), RandomString.make(10)))
                .get();
        Client client = clientService.save(new Client(RandomString.make(10), RandomString.make(10), true)).get();

        return new Sale(store, user, client, items);
    }

    private SaleItem generateSaleItem(Integer quantityProduct, Integer quantitySale) {
        Product product = productService.save(new Product(RandomString.make(10), RandomString.make(10), quantityProduct)).get();

        return new SaleItem(product, quantitySale);
    }

}
