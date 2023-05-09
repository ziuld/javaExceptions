package org.example;

public class Player {
    private String id;
    private int position;
    private double kd;


    public Player(String id, int position, double kd) {
        this.id = id;
        this.kd = kd;
        this.position = position;
    }

    public String toString(){
        return this.id+" - "+this.position+" - "+this.kd;
    }
}
