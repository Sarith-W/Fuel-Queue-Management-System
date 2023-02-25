package com.example.prog_cw;

import java.util.ArrayList;

public class FuelQueue {
    //Defining objects for 6 passengers
    Passenger passenger_1 = new Passenger();
    Passenger passenger_2 = new Passenger();
    Passenger passenger_3 = new Passenger();
    Passenger passenger_4 = new Passenger();
    Passenger passenger_5 = new Passenger();
    Passenger passenger_6 = new Passenger();

    //Creating an array of Passenger Objects
    Passenger[] passengerObj = {passenger_1, passenger_2, passenger_3, passenger_4, passenger_5, passenger_6};

    //Defining a variable to store the income of each fuel queue
    private int queue_income = 0;

    //Defining the getter and the setter for the income
    public int getQueue_income() {
        return queue_income;
    }
    public void setQueue_income(int queue_income) {
        this.queue_income = queue_income;
    }

    //This method is to display all the passengers in a queue.
    public void viewAllQueues(){
        for (int i = 0; i < 6; i++) {
            System.out.println("\nCustomer " + (i+1));
            if (passengerObj[i].getFirstName() == null){
                System.out.println("EMPTY");
            }else {
                System.out.println("\tFirst Name     : " + passengerObj[i].getFirstName());
                System.out.println("\tLast Name      : " + passengerObj[i].getLastName());
                System.out.println("\tVehicle No     : " + passengerObj[i].getVehicleNo());
                System.out.println("\tRequired Liters: " + passengerObj[i].getLiters());
            }
        }
    }

    //This method is to display all the empty passengers in the queue.
    public void emptyQueues(){
        int maxCount = 0;
        for (int i = 0; i < 6; i++){
            if (passengerObj[i].getFirstName() == null){
                System.out.println("\nCustomer " + (i+1));
                System.out.println("EMPTY");
            }else {
                maxCount++;
            }
        }
        if (maxCount == 6){
            System.out.println("NOTE:\n\tQueue is Full!");
        }
    }


    //Get null count of the queue
    public int getNullCount(){
        //This method counts the null values in each queue
        int nullCount = 0;
        for (int i = 0; i < 6; i++){
            if (passengerObj[i].getFirstName() == null){
                nullCount += 1;
            }
        }
        return nullCount;
    }

    //This method assigns all the details of the customer to the passenger object
    public void addCustomerToQueue(int queueNo,String[] details){
        for (int i = 0; i < 6; i++){
            if (passengerObj[i].getFirstName() == null){
                passengerObj[i].setFirstName(details[0]);
                passengerObj[i].setLastName(details[1]);
                passengerObj[i].setVehicleNo(details[2]);
                passengerObj[i].setLiters(details[3]);
                setQueue_income(getQueue_income() + (430 * Integer.parseInt(details[3])));

                if (queueNo == 1){
                    System.out.println("NOTE:\n\t" + passengerObj[i].getFirstName() + " " + passengerObj[i].getLastName() + " was added to the Queue 1 successfully!");
                }else if (queueNo == 2){
                    System.out.println("NOTE:\n\t" + passengerObj[i].getFirstName() + " " + passengerObj[i].getLastName() + " was added to the Queue 2 successfully!");
                }else if (queueNo == 3){
                    System.out.println("NOTE:\n\t" + passengerObj[i].getFirstName() + " " + passengerObj[i].getLastName() + " was added to the Queue 3 successfully!");
                }else if (queueNo == 4){
                    System.out.println("NOTE:\n\t" + passengerObj[i].getFirstName() + " " + passengerObj[i].getLastName() + " was added to the Queue 4 successfully!");
                }else if (queueNo == 5){
                    System.out.println("NOTE:\n\t" + passengerObj[i].getFirstName() + " " + passengerObj[i].getLastName() + " was added to the Queue 5 successfully!");
                }
                break;
            }
        }
    }

    //This method is to remove a customer from a specific index
    public int removeFromIndex(int queueNo, int position, int stock, ArrayList<String[]> waiting){
        if (passengerObj[position].getFirstName() != null){
            if (stock + Integer.parseInt(passengerObj[position].getLiters()) > 6600){
                stock = 6600;
            }else {
                stock += Integer.parseInt(passengerObj[position].getLiters());
            }
            setQueue_income(getQueue_income() - (430 * Integer.parseInt(passengerObj[position].getLiters())));
            shift_Queue(position);
            System.out.println("NOTE:\n\tThe customer at the position " + position + " was removed from the Queue " + queueNo + " successfully!" + addFromWaitingQueue(waiting));
        }else {
            System.out.println("NOTE:\n\tOops...There's no customer at the position " + position + " in the Queue " + queueNo);
        }
        return stock;
    }


    //This method removes a served customer
    public void removeServedCustomer(int queueNo, ArrayList<String[]> waiting){
        if (passengerObj[0].getFirstName() != null){
            shift_Queue(0);
            System.out.println("NOTE:\n\tThe first customer was removed from the Queue " + queueNo + " successfully!" + addFromWaitingQueue(waiting));
        }else {
            System.out.println("NOTE:\n\tOops...There are no customers in the Queue " + queueNo);
        }
    }

    //This method is to add a customer to a queue from the waiting list when a customer was removed from a queue.
    public String addFromWaitingQueue(ArrayList<String[]> waiting){
        String message = "";
        if (waiting.size() > 0) {
            for (int i = 0; i < 6; i++) {
                if (passengerObj[i].getFirstName() == null) {
                    passengerObj[i].setFirstName(waiting.get(0)[0]);
                    passengerObj[i].setLastName(waiting.get(0)[1]);
                    passengerObj[i].setVehicleNo(waiting.get(0)[2]);
                    passengerObj[i].setLiters(waiting.get(0)[3]);
                    setQueue_income(getQueue_income() + (430 * Integer.parseInt(waiting.get(0)[3])));
                    waiting.remove(0);
                    message = "\n\tThe next customer at The Waiting Queue was added to the empty space successfully!";
                }
            }
        }
        return message;
    }

    //Shifting the queue after a customer was removed
    public void shift_Queue(int position){
        //This method shifts the remaining customers to the left, when a customer was removed.
        if (position == 5){
            allToNull(5);
        }else {
            for (int i = 0; i < 6; i++){
                if ((position + i + 1) == 5){   //This only becomes true for the 4th index. If it's true, the index 4 will be replaced by the 5th index.
                    passengerObj[4].setFirstName(passengerObj[5].getFirstName());
                    passengerObj[4].setLastName(passengerObj[5].getLastName());
                    passengerObj[4].setVehicleNo(passengerObj[5].getVehicleNo());
                    passengerObj[4].setLiters(passengerObj[5].getLiters());
                    allToNull(5);
                    break;
                }else {
                    passengerObj[position + i].setFirstName(passengerObj[position + i + 1].getFirstName());
                    passengerObj[position + i].setLastName(passengerObj[position + i + 1].getLastName());
                    passengerObj[position + i].setVehicleNo(passengerObj[position + i + 1].getVehicleNo());
                    passengerObj[position + i].setLiters(passengerObj[position + i + 1].getLiters());
                    //The index will be replaced by the index after that.
                }
            }
        }
    }

    //This method is to assign the details of a customer to null value when shifting the customers
    public void allToNull(int i){
        passengerObj[i].setFirstName(null);
        passengerObj[i].setLastName(null);
        passengerObj[i].setVehicleNo(null);
        passengerObj[i].setLiters(null);
    }


    //View Customers Sorted in alphabetical order
    public void sort_Customers(String[][] queue){
        //This method sorts the customer names in the alphabetical order without using the library sort routine.
        int name_Count = 0, max_Count, clone;
        ArrayList<String[]> store = new ArrayList<>();
        String[][] sorted_Queue = new String[6][4];  //Initializing a new queue to store the sorted customer names.
        for (String[] strings : queue) {
            if (strings[0] != null) {     //This loop determines how many customer names are there in the queue without the null values.
                name_Count++;
            }
        }
        for (String[] name : queue) {
            if (name[0] != null) {
                name[0] = name[0].toLowerCase();
                clone = 0;
                ArrayList <String[]> cloneList = new ArrayList<>();
                max_Count = 0;    //max_Count variable increases the count when a name appears at the first in the alphabetical order than the other names in the queue.
                //The below loop compares each name in the queue with all the names in the same queue.
                for (String[] compare_Name : queue) {
                    if (compare_Name[0] != null){
                        compare_Name[0] = compare_Name[0].toLowerCase();
                        if (!name[0].equals(compare_Name[0])) {   //If the name is not equals to the comparison name and null, then the comparison statement will run.
                            if (name[0].compareTo(compare_Name[0]) < 0) {
                                max_Count++;    //If the name is at the first in the alphabetical order than the comparison name, then it increases by 1.
                            }
                        }else if (name[0].equalsIgnoreCase(compare_Name[0])){
                            name[1] = name[1].toLowerCase();
                            compare_Name[1] = compare_Name[1].toLowerCase();  //If the first name of the customers are equal, then it will compare from the last name.
                            if (name[1].compareTo(compare_Name[1]) < 0) {
                                max_Count++;
                            } else if (name[1].equals(compare_Name[1])){
                                clone++;
                                if (clone > 1){
                                    if (!store.contains(name)){
                                        store.add((name));
                                        cloneList.add(name);
                                    }
                                    if (!store.contains(compare_Name)){
                                        store.add(compare_Name);
                                        cloneList.add(compare_Name);
                                    }
                                }
                            }
                        }
                    }
                }
                for (int i = 0; i < name_Count; i++) {
                    if (max_Count == ((name_Count - 1) - i)) {
                        if (!store.contains(name)){
                            sorted_Queue[i] = name;
                        }else {
                            for (int j = 0; j < cloneList.size(); j++){
                                sorted_Queue[i-j] = cloneList.get(cloneList.size()-(j+1));   //If there are customers with the same name, then those will be appeared next to the other.
                            }
                        }
                    }
                }
            }
        }
        //Displaying the sorted queue.
        for (int i = 0; i < 6; i++) {
            System.out.println("\nCustomer " + (i+1));
            if (sorted_Queue[i][0] == null){
                System.out.println("EMPTY");
            }else {
                System.out.println("\tFirst Name     : " + sorted_Queue[i][0]);
                System.out.println("\tLast Name      : " + sorted_Queue[i][1]);
                System.out.println("\tVehicle No     : " + sorted_Queue[i][2]);
                System.out.println("\tRequired Liters: " + sorted_Queue[i][3]);
            }
        }
    }

}
