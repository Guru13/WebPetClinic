package by.guru13.lessons.clinic;

import java.util.Scanner;

/**
 * класс запускает клинику
 *
 * @auhtor alexey
 * @since 16.07.2015
 */
public class ClinicRunner {
    Scanner reader = new Scanner(System.in);
    Clinic clinic = new Clinic();
    public String exit = "";

    /**
     * заполняет клинику клиентами
     */
    public void fillClinic() {
        clinic.addClient(new Client("Gogol", new Cat("Vaska")));
        clinic.addClient(new Client("Pushkin", new Parot("Gosha")));
        clinic.addClient(new Client("Turgenev", new Dog("Mu-mu")));
    }

    /**
     * добавляет клиента
     */
    public void addClient( ){
        System.out.println("Enter client's name, please");
        final String name = reader.next();
        System.out.println("Enter pet's name, please");
        final String petName = reader.next();
        System.out.println("Enter type of pet, please (Dog/Cat/Parot)");
         String petType = reader.next();
        while (!petType.equalsIgnoreCase("Dog") && !petType.equalsIgnoreCase("Cat") && !petType.equalsIgnoreCase("Parot")) {
            System.out.println("You shoud enter sCl/sPet/print/rem/get");
            petType = reader.next();
        }
        Pet pet = null;
        if (petType.equalsIgnoreCase("Dog")){
            pet = new Dog(petName);
        }else if (petType.equalsIgnoreCase("Cat")){
            pet = new Cat(petName);
        }else if (petType.equalsIgnoreCase("Parot")){
            pet = new Parot(petName);
        }
        clinic.addClient(new Client(name, pet));
    }

    /**
     * устанавливает выход из программы
     */
    public void setExit() throws UserException {
        System.out.println("Exit: y/n");
        exit = reader.next();
//        while (!"y".equals(exit) && !"n".equals(exit)) {
//            System.out.println("You should enter y or n");
//            exit = reader.next();
//        }
        if (!exit.equals("y") && !"n".equals(exit)) {

            throw new UserException("You should enter \"y\" or \"n\"");

        }
    }

    /**
     * запуск клиники
     */
    public void go() {

        try {
            while (!exit.equals("y")) {
                chooseOperation();
                setExit();
            }
        } catch (UserException e) {
            e.printStackTrace();
            e.getMessage();
        } finally {
            reader.close();
        }
    }

    /**
     * Выбор операции
     */
    public void chooseOperation() {
        System.out.println("Choose operation you want to do:" +
                "\n add client to clinic(add)" +
                "\n search client by his pet's name(sCl)," +
                "\n search pet by its client's name(sPet)," +
                "\n print all clients(print)," +
                "\n remove client(rem)" +
                "\n get client(get)");
        String operation = reader.next();
        while (!operation.equals("add") && !operation.equals("sCl") && !operation.equals("sPet") && !operation.equals("print")
                && !operation.equals("rem") && !operation.equals("get")) {
            System.out.println("You shoud enter add/sCl/sPet/print/rem/get");
            operation = reader.next();
        }
        if ("add".equals(operation)) {
          addClient();
        }
        if ("sCl".equals(operation)) {
            System.out.println("Enter pet's name:");
            String petName = reader.next();
            System.out.print("Found client: ");
            clinic.printClientsByPetName(petName);
        }
        if ("sPet".equals(operation)) {
            System.out.println("Enter client's name:");
            String name = reader.next();
            System.out.print("Found pet: ");
            clinic.printPetsByClient(name);
        }
        if ("print".equals(operation)) {
            System.out.println("Print all clients:");
            clinic.printAllClients();
        }
        if ("rem".equals(operation)) {
            System.out.println("Enter client's name for remove:");
            String name = reader.next();
            clinic.removeClientByName(name);
        }
        if ("get".equals(operation)) {
            System.out.println("Enter number:");
            String sNumber = reader.next();
            while (sNumber.matches("\\D")) {
                System.out.println("You should enter a number!");
                sNumber = reader.next();
            }
            Integer number = Integer.parseInt(sNumber);
            if (number != 0 && number < clinic.getClients().getSize()+1) {
                System.out.println(clinic.getClient(number).toString());
            } else {
                System.out.println(number + " is empty");
            }

        }
    }


    public static void main(String[] args) {
        ClinicRunner cr = new ClinicRunner();
        cr.go();
    }
}