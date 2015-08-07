package by.guru13.lessons.clinic;

/**
 * Created by ASUS on 29.07.2015.
 */
public class Emulator {
    public static void main(String[] args) {
        Clinic clinic = new Clinic();
        User user = new User(clinic);
        Admin admin = new Admin(clinic);
        user.start();
        admin.start();
    }
}
