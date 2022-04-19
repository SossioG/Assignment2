package model;

public class Client extends Thread{
    private int id = 0;
    private int counter =0;
    private SeatManager sm;

    public Client(SeatManager sm, int id){
        this.id = id;
        this.sm = sm;
    }


    public void run(){
        while (!Thread.interrupted()){
            try {
                int x = sm.getSeatId();
                System.out.println("x: " + x);
                if (x == -1){
                    System.out.println("No seats available");
                    break;
                }else {
                    System.out.println("Place status has changed to occopaid: " + x);
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }




}
