package model;

public class Client extends Thread{
    private int id = 0;
    private int counter =0;
    private String name = "";
    private SeatManager sm;

    public Client(SeatManager sm, int id, String name){
        this.id = id;
        this.sm = sm;
        this.name = name;
    }


    public void run(){
        while (!Thread.interrupted()){
            try {
                Thread.sleep(1000);
                int x = sm.getSeatId(1);
                System.out.println("x: " + x);
                if (x == -1){
                    System.out.println("No seats available");
                    break;
                }else {
                    System.out.println(name + ": have taken seat: " + x);
                    break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }




}
