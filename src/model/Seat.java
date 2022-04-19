package model;

public class Seat {
    private int seatId;
    private Status seatStatus;

    // Constructor
    public Seat(int seatId, Status seatStatus) {
        this.seatId = seatId;
        this.seatStatus = seatStatus;
    }

    // Getter and setters
    public int getSeatId() {
        return seatId;
    }
    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public Status getSeatStatus() {
        return seatStatus;
    }
    public void setSeatStatus(Status seatStatus) {
        this.seatStatus = seatStatus;
    }

}
