package model;

import control.Control;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SeatManager {

    Control control;

    private List<Seat> seats = new ArrayList<>();
    private List<String> logBook = new ArrayList<>();

    private final int maxSeats = 10;
    private final int availableSeats = 5;
    private final int maxClients = 15;
    private List<Seat> takenSeat;
    private Client client, client1, client2, client3;
    private Thread thread;

    // Constructor
    public SeatManager(Control control){
        pickRandomSeats();
        this.control = control;

         client = new Client(this, 1, "client1");
        new Thread(client).start();
         client1 = new Client(this, 1, " client2");
        new Thread(client1).start();

         client2 = new Client(this, 1, " client3");
        new Thread(client2).start();

         client3 = new Client(this, 1, " client4");
        new Thread(client3).start();

    }

    // Select 5 random number without duplicate between 0 and 10
    public boolean pickRandomSeats(){
        takenSeat = getRandomNonRepeatingIntegers(availableSeats, 0, maxSeats);
        for (int i = 0; i < takenSeat.size(); i++) {
            System.out.println("" + takenSeat.get(i));
        }
        return true;
    }

    // Get selected size number without duplicate
    public List<Seat> getRandomNonRepeatingIntegers(int size, int min, int max) {
        List<Seat> numbers = new ArrayList<>();
        Random random = new Random();
        while (numbers.size() < size) {
            //Get Random numbers between range
            int randomNumber = random.nextInt((max - min) + 1) + min;
            //Check for duplicate values
            if (!numbers.contains(randomNumber)) {
                numbers.add(new Seat(randomNumber, Status.Available));
            }
        }
        return numbers;
    }

    /////// get status ////////
    public void printStatus(){
        String result = "";
        for (Seat seat: takenSeat) {
            result = seat.toString();
            logBook.add(result);
        }
    }

    public int getSeatId(int id){
        int output = 0;
        if (takenSeat.get(id).getSeatStatus().equals(Status.Available)){
            takenSeat.get(id).setSeatStatus(Status.Occupied);
            System.out.println("thread: " +  "" +"seat has been taken." + id + " time: " + LocalTime.now());
            output= id;
        }else {
            System.out.println("thread: " +""+" could");
            output = -1;
        }

        /*

        System.out.println("size: " + takenSeat.size());
        for (Seat seat : takenSeat) { // doesn't go inside the loop
            if (seat.getSeatStatus().equals(Status.Available)) {
                seat.setSeatStatus(Status.Occupied);
                output = seat.getSeatId();
                //printStatus();
                logBook.add(seat.toString());
                System.out.println("status: " + seat.getSeatStatus());
            } else {
                System.out.println("status: " + seat.getSeatStatus());
                output = -1;
            }

        }

         */
        return output;
    }
}
