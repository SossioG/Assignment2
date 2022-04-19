package control;

import model.Seat;
import model.SeatManager;

import java.util.Scanner;

public class Control {

    private SeatManager seatManager;

    // Constructor
    public Control() {
        seatManager = new SeatManager(this);
    }

    // This is start method
    public void start(){
        System.out.println("===========================");
        System.out.println("=== LAST MINUTE TICKETS ===");
        System.out.println("===========================");

        try {
            while(true){
                menuAltMain();
                int userInput = Integer.parseInt(getUserInput("Chose alt:"));
                switch(userInput) {
                    case 1: System.out.println("List of seats!"); break;
                    case 2: System.out.println("Book a seat!"); break;
                    case 3: System.out.println("Come back later!"); System.exit(0); break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Error!");
        }
    }

    // This menu is main menu
    private void menuAltMain() {
        System.out.println("====== MAIN MENU =====");
        System.out.println("[1] - List of seats");
        System.out.println("[2] - Book a seat");
        System.out.println("[3] - Exit");
        System.out.println("=====================");
        System.out.println();
    }

    // This is where all input goes to the user
    private String getUserInput(String msg) {
        Scanner myObj = new Scanner(System.in);
        if (msg != null)
            System.out.println(msg);

        String input = myObj.nextLine();
        return input;
    }
}
