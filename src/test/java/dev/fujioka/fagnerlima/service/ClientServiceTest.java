package dev.fujioka.fagnerlima.service;

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

import dev.fujioka.fagnerlima.domain.Client;
import dev.fujioka.fagnerlima.repository.ClientRepository;

@ActiveProfiles("test")
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @AfterEach
    public void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    public void testFindAll() {
        generateAndSaveClients(4);
        List<Client> clients = clientService.findAll();

        assertThat(clients.size()).isEqualTo(4);
    }

    @Test
    public void testFindAllByPageable() {
        generateAndSaveClients(15);
        Page<Client> clientsPage = clientService.findAll(PageRequest.of(0, 10));

        assertPage(clientsPage, 10, 0, 10, 2, 15);
    }

    @Test
    public void testFindAllByPageable_noContent() {
        Page<Client> clientsPage = clientService.findAll(PageRequest.of(0, 10));

        assertPageNoContent(clientsPage, 10, 0);
    }

    @Test
    public void findById() {
        Client mockClient = generateAndSaveClient();
        Client client = clientService.findById(mockClient.getId()).orElseThrow();

        assertThat(client.getId()).isEqualTo(mockClient.getId());
    }

    @Test
    public void findById_NotFound() {
        assertThrows(NoSuchElementException.class, () -> clientService.findById(1000L).orElseThrow());
    }

    @Test
    public void testSave() {
        Client client = generateClient();

        clientService.save(client).orElseThrow();

        assertThat(client.getId()).isNotNull();
        assertThat(client.getFirstName()).isNotBlank();
        assertThat(client.getLastName()).isNotBlank();
        assertThat(client.getActive()).isTrue();
    }

    @Test
    public void testUpdate() {
        Client mockClient = generateAndSaveClient();

        Client updatedClient = new Client();
        BeanUtils.copyProperties(mockClient, updatedClient);
        updatedClient.setFirstName("Modified");
        updatedClient.setActive(!mockClient.getActive());
        clientService.save(updatedClient).orElseThrow();

        assertThat(updatedClient.getId()).isEqualTo(mockClient.getId());
        assertThat(updatedClient.getFirstName()).isNotEqualTo(mockClient.getFirstName());
        assertThat(updatedClient.getLastName()).isEqualTo(mockClient.getLastName());
        assertThat(updatedClient.getActive()).isNotEqualTo(mockClient.getActive());
    }

    @Test
    public void testDelete() {
        Client mockClient = generateAndSaveClient();

        assertThatCode(() -> clientService.deleteById(mockClient.getId())).doesNotThrowAnyException();
    }

    @Test
    public void testDelete_whenNotFound() {
        assertThrows(EmptyResultDataAccessException.class, () -> clientService.deleteById(100L));
    }

    private Client generateClient() {
        return new Client(RandomString.make(10), RandomString.make(10), true);
    }

    private Client generateAndSaveClient() {
        return clientRepository.save(generateClient());
    }

    private List<Client> generateClients(Integer amount) {
        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < amount; i++) {
            clients.add(new Client(RandomString.make(10), RandomString.make(10), true));
        }

        return clients;
    }

    private List<Client> generateAndSaveClients(Integer amount) {
        return clientRepository.saveAll(generateClients(amount));
    }

    private void assertPage(Page<?> page, int pageSize, int pageNumber, int numberOfElements, int totalPages, int totalElements) {
        assertThat(page.getContent().size()).isEqualTo(numberOfElements);
        assertThat(page.getNumberOfElements()).isEqualTo(numberOfElements);
        assertThat(page.getNumber()).isEqualTo(pageNumber);
        assertThat(page.getSize()).isEqualTo(pageSize);
        assertThat(page.getTotalPages()).isEqualTo(totalPages);
        assertThat(page.getTotalElements()).isEqualTo(totalElements);
    }

    private void assertPageNoContent(Page<?> page, int pageSize, int pageNumber) {
        assertPage(page, pageSize, pageNumber, 0, 0, 0);
    }

}
