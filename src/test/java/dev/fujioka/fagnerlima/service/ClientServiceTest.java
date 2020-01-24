package dev.fujioka.fagnerlima.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    private Long firstClientId;

    @BeforeEach
    public void setUp() {
        Client client = new Client();
        client.setFirstName("José");
        client.setLastName("da Silva");
        client.setActive(true);

        clientService.save(client).orElseThrow();
        firstClientId = client.getId();

        client = new Client();
        client.setFirstName("Maria");
        client.setLastName("Alburquerque");
        client.setActive(false);

        clientService.save(client).orElseThrow();
    }

    @AfterEach
    public void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    public void testFindAll() {
        List<Client> clients = clientService.findAll();

        assertThat(clients.size() == 2).isTrue();
    }

    @Test
    public void testFindAllByPageable() {
        Page<Client> clientsPage = clientService.findAll(PageRequest.of(0, 10));

        assertThat(clientsPage.getContent().size() == 2).isTrue();
        assertThat(clientsPage.getTotalElements() == 2).isTrue();
        assertThat(clientsPage.getNumberOfElements() == 2).isTrue();
        assertThat(clientsPage.getSize() == 10).isTrue();
        assertThat(clientsPage.getNumber() == 0).isTrue();
        assertThat(clientsPage.hasNext()).isFalse();
    }

    @Test
    public void findById() {
        Client client = clientService.findById(firstClientId).orElseThrow();

        assertThat(client.getId()).isNotNull();
    }

    @Test
    public void findById_NotFound() {
        assertThrows(NoSuchElementException.class, () -> clientService.findById(1000L).orElseThrow());
    }

    @Test
    public void testSave() {
        Client client = new Client();
        client.setFirstName("Augusto");
        client.setLastName("Reis");
        client.setActive(true);

        clientService.save(client).orElseThrow();

        assertThat(client.getId()).isNotNull();
        assertThat(client.getFirstName()).isEqualTo("Augusto");
        assertThat(client.getLastName()).isEqualTo("Reis");
        assertThat(client.getActive()).isTrue();
        assertThat(client.getCreatedAt()).isNotNull();
        assertThat(client.getUpdatedAt()).isNotNull();
    }

    @Test
    public void testUpdate() {
        Client client = new Client();
        client.setId(firstClientId);
        client.setFirstName("José");
        client.setLastName("de Lima");
        client.setActive(false);

        clientService.save(client).orElseThrow();

        assertThat(client.getId()).isEqualTo(firstClientId);
        assertThat(client.getFirstName()).isEqualTo("José");
        assertThat(client.getLastName()).isEqualTo("de Lima");
        assertThat(client.getActive()).isFalse();
    }

    @Test
    public void testDelete() {
        clientService.deleteById(firstClientId);
        assertThrows(NoSuchElementException.class, () -> clientService.findById(firstClientId).orElseThrow());
    }

}
