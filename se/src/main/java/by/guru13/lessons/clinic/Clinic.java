package by.guru13.lessons.clinic;

import java.util.Iterator;
import java.util.Scanner;

/**
 * класс описывает клинику
 *
 * @auhtor alexey
 * @since 16.07.2015
 */
public class Clinic {

    /**
     * Список клиентов
     */
    private final SimpleLinkedList<Client> clients;

    /**
     * конструктор
     */
    public Clinic() {
        this.clients = new SimpleLinkedList<Client>();
    }

    /**
     * @return массив клиентов
     */
    public SimpleLinkedList<Client> getClients() {
        return clients;
    }

    /**
     * Получение клиента по индексу
     *
     * @param index индекс клиента
     * @return возвращает клиента под данным индексом
     */
    public Client getClient(int index) {
        return clients.get(index);
    }

    /**
     * добавить клиента
     *
     * @param client клиент
     */
    public void addClient(final Client client) {
        this.clients.addLast(client);
    }


    /**
     * удалить клиента по имени
     *
     * @param name имя клиента
     */
    public void removeClientByName(final String name) {
        int j = 0;
        for (Iterator<Client> it = clients.iterator(); it.hasNext(); ) {
            if (it.next().getId().equals(name)) {
                it.remove();
                j = j + 1;
            }
        }
        if (j != 0) {
            System.out.println("The client " + name + " has removed successfully");
        } else {
            System.out.println("That client doesn't exist in the base");
        }
    }

    /**
     * найти клиентов по имени животного
     *
     * @param name имя животного
     */
    public SimpleLinkedList<Client> findClientsByPetName(final String name) {
        SimpleLinkedList<Client> foundedClients = new SimpleLinkedList<Client>();
        int i = 0;
        for (Client client : clients) {
            if (client != null && client.getPet().getName().equals(name)) {
                foundedClients.addLast(client);
                i++;
            }
        }
        return foundedClients;
    }

    /**
     * печать клиентов, найденных по имени животного
     *
     * @param name имя животинки
     */
    public void printClientsByPetName(final String name) {
        SimpleLinkedList<Client> foundedClients = findClientsByPetName(name);
        int j = 0;
        for (Client client : foundedClients) {
            if (client != null) {
                System.out.println(client.getId());
                j = j + 1;
            }
        }
        if (j == 0) {
            System.out.println("Client with that pet's name didn't find");
        }
    }

    /**
     * найти животных по имени клиента
     *
     * @param client имя клиента
     */
    public SimpleLinkedList<Pet> findPetsByClient(final String client) {
        SimpleLinkedList<Pet> foundedPets = new SimpleLinkedList<Pet>();
        int i = 0;
        for (Client clientId : clients) {
            if (clientId != null && clientId.getId().equals(client)) {
                foundedPets.addLast(clientId.getPet());
                i++;
            }
        }
        return foundedPets;
    }

    /**
     * печать всех животных по имени клиента
     *
     * @param client имя клиента
     */
    public void printPetsByClient(final String client) {
        SimpleLinkedList<Pet> foundedPets = findPetsByClient(client);
        int j = 0;
        for (Pet pet : foundedPets) {
            if (pet != null) {
                System.out.println(pet.getName() + "   " + pet.getClass().getSimpleName());
                j = j + 1;
            }
        }
        if (j == 0) {
            System.out.println("Client with that pet's name didn't find");
        }
    }

    /**
     * печать всех клиентов клиники и их домашних животных
     */
    public void printAllClients() {
        for (Client client : clients) {
            System.out.println(client.getId() + " - " + client.getPet().getName());
        }
    }
}