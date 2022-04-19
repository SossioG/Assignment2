package model;

public class Client {
    private int id = 0;
    private SeatManager sm;

    public Client(SeatManager sm, int id){
        this.id = id;
        this.sm = sm;
    }

}
