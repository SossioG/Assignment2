package model;

import control.Control;
import view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SeatManager {
    private Control control;
    private View view;
    private List<Seat> seats = new ArrayList<>();
    private List<String> logBook = new ArrayList<>();
    private final Object lock = new Object();
    private  Random random;
    private final int maxSeats = 10;
    private final int availableSeats = 5;
    private final int maxClients = 15;
    private List<Seat> takenSeat = new ArrayList<>();
    private HashMap<Integer,String> notReachedClientList = new HashMap<Integer, String>();

    // Constructor
    public SeatManager(Control control){
        this.control = control;
        this.view = new View(this);
        boolean readyToPick = pickRandomSeats();
        if (readyToPick){
            new Client(this, 1, "client 1");
            new Client(this, 1, "client 2");
            new Client(this, 6, "client 3");
            new Client(this, 6, "client 4");
            new Client(this, 3, "client 5");
            new Client(this, 1, "client 6");
            new Client(this, 3, "client 7");
            new Client(this, 1, "client 8");
            new Client(this, 4, "client 9");
            new Client(this, 1, "client 10");
        }
    }

    // Select 5 random number without duplicate between 0 and 10
    public boolean pickRandomSeats(){
        takenSeat.add(new Seat(1,Status.Available));
        takenSeat.add(new Seat(2,Status.Available));
        takenSeat.add(new Seat(3,Status.Available));
        takenSeat.add(new Seat(4,Status.Available));
        takenSeat.add(new Seat(5,Status.Available));

        //takenSeat = getRandomNonRepeatingIntegers(availableSeats, 0, maxSeats);
        view.printAvailableSeats(takenSeat);

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
    private String noSeat = "";
    public int getSeatId(int id, String name){
        synchronized (lock){
            System.out.println("Thread " + name + " take lock");
        int output = 0;
            for (int i =0; i < availableSeats; i++){
                if (takenSeat.get(i).getSeatId() == id){
                    if (takenSeat.get(i).getSeatStatus().equals(Status.Available)){
                        takenSeat.get(i).setSeatStatus(Status.Occupied);
                        output= id;
                        view.printTakenSeat(id, name);
                    }else {
                        if (!notReachedClientList.containsValue(name)){
                            notReachedClientList.put(id, name);
                            output = -1;
                        }
                    }
                }else {
                }
            }
            if (notReachedClientList.size() > 0){
                view.printClientInfo(notReachedClientList);
            }
            System.out.println("Thread " + name + " leaved lock");

            return output;
        }
    }
}
