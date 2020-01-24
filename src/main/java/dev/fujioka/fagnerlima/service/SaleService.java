package dev.fujioka.fagnerlima.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.fujioka.fagnerlima.domain.Client;
import dev.fujioka.fagnerlima.domain.Product;
import dev.fujioka.fagnerlima.domain.Sale;
import dev.fujioka.fagnerlima.domain.Store;
import dev.fujioka.fagnerlima.exception.EntityNotFoundException;
import dev.fujioka.fagnerlima.exception.SaleException;
import dev.fujioka.fagnerlima.repository.SaleItemRepository;
import dev.fujioka.fagnerlima.repository.SaleRepository;

@Service
public class SaleService extends BaseCrudService<Sale> {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public Optional<Sale> save(Sale entity) {
        checkSale(entity);
        super.save(entity);
        saveItems(entity);

        return Optional.of(entity);
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        Sale entity = findById(id).orElseThrow(() -> new EntityNotFoundException("id", id, "Informação não encontrada"));
        deleteItems(entity);
        super.deleteById(id);
    }

    @Override
    protected JpaRepository<Sale, Long> getRepository() {
        return saleRepository;
    }

    private void checkSale(Sale entity) {
        Store store = storeService.findById(entity.getStore().getId())
                .orElseThrow(() -> new EntityNotFoundException("id", entity.getStore().getId(), "Loja não encontrada"));

        if (!store.getActive()) {
            throw new SaleException("Loja inativa");
        }

        Client client = clientService.findById(entity.getClient().getId())
                .orElseThrow(() -> new EntityNotFoundException("id", entity.getClient().getId(), "Cliente não encontrado"));

        if (!client.getActive()) {
            throw new SaleException("Cliente inativo");
        }
    }

    private void saveItems(Sale entity) {
        entity.getItems().stream().forEach(i -> {
            Product product = productService.findById(i.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("id", i.getProduct().getId(), "Produto não encontrado"));
            product.subtractAmount(i.getAmount());
            productService.save(product);

            Sale sale = new Sale();
            sale.setId(entity.getId());
            i.setSale(sale);
            saleItemRepository.save(i);
        });
    }

    private void deleteItems(Sale entity) {
        entity.getItems().stream().forEach(i -> {
            Product product = productService.findById(i.getProduct().getId())
                    .orElseThrow(() -> new EntityNotFoundException("id", i.getProduct().getId(), "Produto não encontrado"));
            product.addAmount(i.getAmount());
            productService.save(product);

            saleItemRepository.deleteById(i.getId());
        });
    }
}
