package control;

import model.Seat;
import model.SeatManager;
import view.View;

import java.util.Scanner;

public class Control {

    private SeatManager seatManager;
    private View view;
    // Constructor
    public Control() {
        seatManager = new SeatManager(this);
    }
}
