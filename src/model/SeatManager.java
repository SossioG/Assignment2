package model;

import control.Control;

import java.util.ArrayList;
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
    private Client client;

    // Constructor
    public SeatManager(Control control){
        pickRandomSeats();
        this.control = control;
        client = new Client(this, 12);
        Thread thead = new Thread(client);
        thead.start();

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
    public String printStatus(){
        String result = "";
        for (Seat seat: seats) {
            result = seat.toString();
            logBook.add(result);
        }
        return  result;
    }

    public int getSeatId(){
        int output = 0;
        System.out.println();
        for (Seat seat : takenSeat) { // doesn't go inside the loop
            System.out.println(" loop");
            if (seat.getSeatStatus().equals(Status.Available)) {
                seat.setSeatStatus(Status.Occupied);
                output = seat.getSeatId();
            } else {
                output = -1;
            }

        }
        return output;
    }
}
