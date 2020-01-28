package dev.fujioka.fagnerlima.service;

import static dev.fujioka.fagnerlima.service.ServiceAssertions.assertPage;
import static dev.fujioka.fagnerlima.service.ServiceAssertions.assertPageNoContent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import dev.fujioka.fagnerlima.domain.Store;
import dev.fujioka.fagnerlima.repository.StoreRepository;

@ActiveProfiles("test")
@SpringBootTest
public class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreRepository storeRepository;

    @AfterEach
    public void tearDown() {
        storeRepository.deleteAll();
    }

    @Test
    public void testFindAll() {
        generateAndSaveStores(4);
        List<Store> stores = storeService.findAll();

        assertThat(stores.size()).isEqualTo(4);
    }

    @Test
    public void testFindAllByPageable() {
        generateAndSaveStores(15);
        Page<Store> storesPage = storeService.findAll(PageRequest.of(0, 10));

        assertPage(storesPage, 10, 0, 10, 2, 15);
    }

    @Test
    public void testFindAllByPageable_noContent() {
        Page<Store> storesPage = storeService.findAll(PageRequest.of(0, 10));

        assertPageNoContent(storesPage, 10, 0);
    }

    @Test
    public void findById() {
        Store mockStore = generateAndSaveStore();
        Store store = storeService.findById(mockStore.getId()).orElseThrow();

        assertThat(store.getId()).isEqualTo(mockStore.getId());
    }

    @Test
    public void findById_NotFound() {
        assertThrows(NoSuchElementException.class, () -> storeService.findById(1000L).orElseThrow());
    }

    @Test
    public void testSave() {
        Store store = generateStore();

        storeService.save(store).orElseThrow();

        assertThat(store.getId()).isNotNull();
        assertThat(store.getName()).isNotBlank();
        assertThat(store.getActive()).isTrue();
    }

    @Test
    public void testUpdate() {
        Store mockStore = generateAndSaveStore();

        Store updatedStore = new Store();
        BeanUtils.copyProperties(mockStore, updatedStore);
        updatedStore.setName("Modified");
        storeService.save(updatedStore).orElseThrow();

        assertThat(updatedStore.getId()).isEqualTo(mockStore.getId());
        assertThat(updatedStore.getName()).isNotEqualTo(mockStore.getName());
        assertThat(updatedStore.getActive()).isEqualTo(mockStore.getActive());
    }

    @Test
    public void testDelete() {
        Store mockStore = generateAndSaveStore();

        assertThatCode(() -> storeService.deleteById(mockStore.getId())).doesNotThrowAnyException();
    }

    @Test
    public void testDelete_whenNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> storeService.deleteById(100L));
    }

    private Store generateStore() {
        return new Store(RandomString.make(10), true);
    }

    private Store generateAndSaveStore() {
        return storeRepository.save(generateStore());
    }

    private List<Store> generateStores(Integer amount) {
        List<Store> stores = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            stores.add(new Store(RandomString.make(10), true));
        }

        return stores;
    }

    private List<Store> generateAndSaveStores(Integer amount) {
        return storeRepository.saveAll(generateStores(amount));
    }

}
