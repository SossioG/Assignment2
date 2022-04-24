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

    private final Object lock = new Object();
    private  Random random;
    private final int maxSeats = 10;
    private final int availableSeats = 5;
    private final int maxClients = 15;
    private List<Seat> takenSeat = new ArrayList<>();

    // Constructor
    public SeatManager(Control control){
        boolean readyToPick = pickRandomSeats();
        this.control = control;
        if (readyToPick){
            new Client(this, 1, "client1");
            new Client(this, 1, "client2");
            new Client(this, 1, "client3");
            new Client(this, 1, "client4");
            new Client(this, 1, "client5");
            new Client(this, 1, "client6");
            new Client(this, 1, "client7");
            new Client(this, 1, "client8");
            new Client(this, 1, "client9");
            new Client(this, 1, "client10");
        }
    }

    // Select 5 random number without duplicate between 0 and 10
    public boolean pickRandomSeats(){
        takenSeat.add(new Seat(1,Status.Available));
        takenSeat.add(new Seat(5,Status.Available));
        takenSeat.add(new Seat(3,Status.Available));
        takenSeat.add(new Seat(9,Status.Available));
        takenSeat.add(new Seat(4,Status.Available));
        /*takenSeat = getRandomNonRepeatingIntegers(availableSeats, 0, maxSeats);
        for (int i = 0; i < takenSeat.size(); i++) {
            System.out.println("available seats: " + takenSeat.get(i).getSeatId());
        }*/
        return true;
    }

    // Get selected size number without duplicate
    public List<Seat> getRandomNonRepeatingIntegers(int size, int min, int max) {
        List<Seat> numbers = new ArrayList<>();
        random = new Random();
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

    public int getSeatId(int id, String name){
        synchronized (lock){
        int output = 0;
            for (int i =0; i < availableSeats; i++){
                if (takenSeat.get(i).getSeatId() == id){
                    System.out.println(takenSeat.get(i).getSeatId() +" = " + id);
                    if (takenSeat.get(i).getSeatStatus().equals(Status.Available)){
                        takenSeat.get(i).setSeatStatus(Status.Occupied);
                        output= id;
                        System.out.println("Available seat place "+ id+"  for thread: " + name);
                    }else {
                        System.out.println("no seat place "+ id+" available for thread: " + name);
                        output = -1;
                    }
                }else {
                    System.out.println("no seat place "+ id+" available for thread: " + name);
                }
            }
            return output;
        }
    }
}
