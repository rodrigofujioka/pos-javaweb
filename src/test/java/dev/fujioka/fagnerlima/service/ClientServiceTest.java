package dev.fujioka.fagnerlima.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import dev.fujioka.fagnerlima.domain.Client;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    private Long firstClientId;

    @Before
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

    @After
    public void tearDown() {
        clientService.findAll().stream().forEach(c -> clientService.delete(c));
    }

    @Test
    public void testFindAll() {
        List<Client> clients = clientService.findAll();

        assertTrue(clients.size() == 2);
    }

    @Test
    public void testFindAllByPageable() {
        Page<Client> clientsPage = clientService.findAll(PageRequest.of(0, 10));

        assertTrue(clientsPage.getContent().size() == 2);
        assertTrue(clientsPage.getTotalElements() == 2);
        assertTrue(clientsPage.getNumberOfElements() == 2);
        assertTrue(clientsPage.getSize() == 10);
        assertTrue(clientsPage.getNumber() == 0);
        assertFalse(clientsPage.hasNext());
    }

    @Test
    public void findById() {
        Client client = clientService.findById(firstClientId).orElseThrow();

        assertNotNull(client.getId());
    }

    @Test(expected = NoSuchElementException.class)
    public void findById_NotFound() {
        clientService.findById(1000L).orElseThrow();
    }

    @Test
    public void testSave() {
        Client client = new Client();
        client.setFirstName("Augusto");
        client.setLastName("Reis");
        client.setActive(true);

        clientService.save(client).orElseThrow();

        assertNotNull(client.getId());
        assertEquals(client.getFirstName(), "Augusto");
        assertEquals(client.getLastName(), "Reis");
        assertTrue(client.getActive());
        assertNotNull(client.getCreatedAt());
        assertNotNull(client.getUpdatedAt());
    }

    @Test
    public void testUpdate() {
        Client client = new Client();
        client.setId(firstClientId);
        client.setFirstName("José");
        client.setLastName("de Lima");
        client.setActive(false);

        clientService.save(client).orElseThrow();

        assertEquals(client.getId(), firstClientId);
        assertEquals(client.getFirstName(), "José");
        assertEquals(client.getLastName(), "de Lima");
        assertFalse(client.getActive());
    }

    @Test(expected = NoSuchElementException.class)
    public void testDelete() {
        clientService.deleteById(firstClientId);
        clientService.findById(firstClientId).orElseThrow();
    }

}
