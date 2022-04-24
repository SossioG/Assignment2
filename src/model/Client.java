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
        start();
    }

    public void run(){
        while (!Thread.interrupted()){
            try {
                    Thread.sleep(100);
                    int x = sm.getSeatId(id, name);
                    if (x == -1){
                        break;
                    }else {
                        break;
                    }
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }




}
