package by.guru13.lessons.clinic;

/**
 * клиент поликлиники
 *
 * @auhtor alexey
 * @since 16.07.2015
 */
public class Client {
    private String id;
    private final Pet pet;

    /**
     * конструктор
     *
     * @param id  идентификационное имя клиента
     * @param pet животное этого клиента
     */
    public Client(String id, Pet pet) {
        this.id = id;
        this.pet = pet;
    }

    /**
     * возвращает объект питомца
     *
     * @return pet
     */
    public Pet getPet() {
        return this.pet;
    }

    /**
     * @return идентификационное имя клиента
     */
    public String getId() {
        return this.id;
    }

    /**
     * устанавливает идентификационное имя клиента
     *
     * @param id имя клиента
     */
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", pet=" + pet +
                '}';
    }
}