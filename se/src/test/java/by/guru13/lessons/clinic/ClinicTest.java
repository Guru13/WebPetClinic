package by.guru13.lessons.clinic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ASUS on 17.07.2015.
 */

/**
 * Тестируем клинику
 *
 * @author Alexey
 * @date 18.07.2015
 */
public class ClinicTest {
    Client client;
    Clinic clinic;

    /**
     * инициализация клиента и клиники перед запуском каждого метода
     */
    @Before
    public void init() {
        client = new Client("testClient", new Cat("testCat"));
        clinic = new Clinic();
    }

    /**
     * проверяем добавление клиента в клинику
     *
     * @throws Exception
     */
    @Test
    public void testAddClient() throws Exception {
        clinic.addClient(client);
        assertTrue("clients[1] shouldn't be null", clinic.getClient(1) != null);
        String name = clinic.getClient(1).getId();
        assertEquals("testClient", name);
        assertSame(client, clinic.getClient(1));

    }

    /**
     * проверяем удаление клиента по его имени
     *
     * @throws Exception
     */
    @Test
    public void testRemoveClientByName() throws Exception {
        clinic.addClient(new Client("London", new Dog("Jack")));
        clinic.addClient(new Client("Doil", new Dog("Baskervil")));
        clinic.addClient(client);
        clinic.removeClientByName("Doil");
        assertTrue("second client should be testClient", clinic.getClient(2).equals(client));
    }

    /**
     * проверяем поиск клиента по кличке животного
     *
     * @throws Exception
     */
    @Test
    public void testFindClientsByPetName() throws Exception {
        clinic.addClient(client);
        SimpleLinkedList<Client> clients = clinic.findClientsByPetName("testCat");
        assertTrue("clients[1].getPet().getName()  should be testCat", clients.get(1).getPet().getName().equals("testCat"));

    }

    /**
     * проверяем поиск животного по имени клиента
     *
     * @throws Exception
     */
    @Test
    public void testFindPetsByClient() throws Exception {
        clinic.addClient(client);
        SimpleLinkedList<Pet> pets = clinic.findPetsByClient("testClient");
        assertTrue("pets[1].getName() should be testCat", pets.get(1).getName().equals("testCat"));
    }
}