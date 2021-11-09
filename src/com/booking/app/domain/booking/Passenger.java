package com.booking.app.domain.booking;

import java.io.Serializable;

public class Passenger implements Serializable {
    private String name;
    private String lName;

    public Passenger (String name, String lName){
        this.name = name;
        this.lName = lName;
    }

    @Override
    public String toString(){
        return "\n\t\tname: " + name + "\t last name: " + lName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
