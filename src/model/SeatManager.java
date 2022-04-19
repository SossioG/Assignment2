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
    private ArrayList takenSeat;
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
    public void pickRandomSeats(){
        takenSeat = getRandomNonRepeatingIntegers(availableSeats, 0, maxSeats);
        for (int i = 0; i < takenSeat.size(); i++) {
            System.out.println("" + takenSeat.get(i));
        }
    }

    // Get selected size number without duplicate
    public ArrayList getRandomNonRepeatingIntegers(int size, int min, int max) {
        ArrayList numbers = new ArrayList();
        Random random = new Random();
        while (numbers.size() < size) {
            //Get Random numbers between range
            int randomNumber = random.nextInt((max - min) + 1) + min;
            //Check for duplicate values
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
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
        for (int i = 0; i <seats.size(); i++){
            if (seats.get(i).getSeatStatus().equals(Status.Available)){
                seats.get(i).setSeatStatus(Status.Occupied);
                return seats.get(i).getSeatId();

            }else {
                return -1;
            }

        }
        return -1;
    }
}
