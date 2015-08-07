package by.guru13.lessons.clinic;

/**
 * Created by Guru on 29.07.2015.
 */
public class Admin extends Thread{


    private Clinic clinic;

    public Admin(Clinic clinic) {
        this.clinic = clinic;
    }

    @Override
    public void run() {
        Pet pet = null;

        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                clinic.addClient(new Client(String.valueOf(i), new Cat("cat" + i)));
            } else if (i % 3 == 0) {
                clinic.addClient(new Client(String.valueOf(i), new Dog("dog" + i)));
            } else {
                clinic.addClient(new Client(String.valueOf(i), new Parot("parrot" + i)));
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

//    public void addClient() {
//        Pet pet = null;
//
//        for (int i = 0; i < 100; i++) {
//            if (i % 2 == 0) {
//                clinic.addClient(new Client(String.valueOf(i), new Cat("cat" + i)));
//            } else if (i % 3 == 0) {
//                clinic.addClient(new Client(String.valueOf(i), new Dog("dog" + i)));
//            } else {
//                clinic.addClient(new Client(String.valueOf(i), new Parot("parrot" + i)));
//            }
//        }
//    }
}
