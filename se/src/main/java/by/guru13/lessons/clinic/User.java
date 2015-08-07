package by.guru13.lessons.clinic;

/**
 * Created by ASUS on 29.07.2015.
 */
public class User extends Thread {
    private Clinic clinic;

    public User(Clinic clinic) {
        this.clinic = clinic;
    }

    @Override
    public void run() {
        while (true) {
            int index = (int) (Math.random() * 100);
            Client client = null;
            try {
                 client = clinic.getClient(index);
            }catch (Exception e){
                System.out.println("there is no client with the index: " + index);
            }
            if (client != null) {
                System.out.println(client + " - " + index);
            }
            clinic.printAllClients();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
