package com.example.prog_cw;

public class Passenger {
    //Defining four variables to store passenger details
    private String firstName = null;
    private String lastName = null;
    private String vehicleNo = null;
    private String liters = null;


    //Defining getters and setters
    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String newFirstName){
        this.firstName = newFirstName;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String newLastName) {
        this.lastName = newLastName;
    }

    public String getVehicleNo(){
        return vehicleNo;
    }

    public void setVehicleNo(String newVehicleNo){
        this.vehicleNo = newVehicleNo;
    }

    public String getLiters(){
        return liters;
    }

    public void setLiters(String newLiters){
        this.liters = newLiters;
    }
}
