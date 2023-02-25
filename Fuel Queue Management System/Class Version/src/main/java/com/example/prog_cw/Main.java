package com.example.prog_cw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;

public class Main {
    //Initializing The Stock and the getter and the setter
    static int stock = 6600;
    public static int getStock() {
        return stock;
    }
    public static void setStock(int stock) {
        Main.stock = stock;
    }


    public static void main(String[] args) throws IOException {

        //Defining an object for the Scanner
        Scanner input = new Scanner(System.in);

        //Creating an array of FuelQueue Objects
        FuelQueue[] queueObj = new FuelQueue[5];

        //Creating actual FuelQueue Objects
        for (int i = 0; i < 5; i++){
            queueObj[i] = new FuelQueue();
        }

        ArrayList<String[]> waiting = new ArrayList<>();


        //Displaying the menu
        mainLoop:
        while (true){
            System.out.println("\n----------------------------------------------------------------------------");
            System.out.println("Menu of The Fuel Queue Management System");
            System.out.println("\nEnter,");
            System.out.println("\t100 or VFQ : View all Fuel Queues.");
            System.out.println("\t101 or VEQ : View all Empty Queues.");
            System.out.println("\t102 or ACQ : Add customer to a Queue.");
            System.out.println("\t103 or RCQ : Remove a customer from a Queue.");
            System.out.println("\t104 or PCQ : Remove a served customer.");
            System.out.println("\t105 or VCS : View Customers Sorted in alphabetical order");
            System.out.println("\t106 or SPD : Store Program Data into file.");
            System.out.println("\t107 or LPD : Load Program Data from file.");
            System.out.println("\t108 or STK : View Remaining Fuel Stock.");
            System.out.println("\t109 or AFS : Add Fuel Stock.");
            System.out.println("\t110 or IFQ : Income of each Fuel Queue.");
            System.out.println("\t999 or EXT : Exit the Program.");

            //Getting the user choice for the menu
            System.out.print("\nEnter your choice : ");
            String choice = input.next();

            //Using switch case to call the methods
            switch (choice.toUpperCase()){
                case "100":
                case "VFQ":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("View all Fuel Queues.");
                    for (int i = 0; i < 5; i++){
                        System.out.println("\n************************** Queue " + (i+1) + " **************************");
                        queueObj[i].viewAllQueues();
                    }
                    System.out.println("\n************************** Waiting Queue **************************");
                    for (int j = 0; j < waiting.size(); j++){
                        System.out.println("\nWaiting Customer " + (j+1));
                        System.out.println("\tFirst Name     : " + waiting.get(j)[0]);
                        System.out.println("\tLast Name      : " + waiting.get(j)[1]);
                        System.out.println("\tVehicle No     : " + waiting.get(j)[2]);
                        System.out.println("\tRequired Liters: " + waiting.get(j)[3]);
                    }
                    if (waiting.size() == 0){
                        System.out.println("EMPTY\n\tCurrently there are no customers in the Waiting Queue");
                    }

                    break;
                case "101":
                case "VEQ":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("View all Empty Queues.");
                    for (int i = 0; i < 5; i++){
                        System.out.println("\n************************** Queue " + (i+1) + " **************************");
                        queueObj[i].emptyQueues();
                    }
                    if (waiting.size() == 0){
                        System.out.println("\n************************** Waiting Queue **************************");
                        System.out.println("EMPTY\n\tCurrently there are no customers in the Waiting Queue");
                    }

                    break;
                case "102":
                case "ACQ":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Add customer to a Queue.\n");
                    validateBeforeAddCustomer(queueObj, waiting);

                    break;
                case "103":
                case "RCQ":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Remove a customer at a specific location from a Queue.\n");
                    remove_Customer(input, queueObj, waiting);

                    break;
                case "104":
                case "PCQ":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Remove a served customer.\n");
                    remove_Served_Customer(input, queueObj, waiting);

                    break;
                case "105":
                case "VCS":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("View Customers Sorted in alphabetical order\n");
                    callSortFunc(queueObj);

                    break;
                case "106":
                case "SPD":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Store Program Data into file.\n");
                    store_Data(queueObj, waiting);

                    break;
                case "107":
                case "LPD":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Load Program Data from file.\n");
                    load_Data(queueObj, waiting);

                    break;
                case "108":
                case "STK":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("View Remaining Fuel Stock.\n");
                    System.out.println("Remaining Fuel Stock : " + getStock() + " Liters out of 6600 Liters");

                    break;
                case "109":
                case "AFS":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Add Fuel Stock.\n");
                    addStock(input);

                    break;
                case "110":
                case "IFQ":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Income of each Fuel Queue.");
                    System.out.println("\nQueue 1 --> Rs." + queueObj[0].getQueue_income());
                    System.out.println("\nQueue 2 --> Rs." + queueObj[1].getQueue_income());
                    System.out.println("\nQueue 3 --> Rs." + queueObj[2].getQueue_income());
                    System.out.println("\nQueue 4 --> Rs." + queueObj[3].getQueue_income());
                    System.out.println("\nQueue 5 --> Rs." + queueObj[4].getQueue_income());

                    break;
                case "999":
                case "EXT":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Exit the Program.");
                    break mainLoop;
                default:
                    System.out.println("ERROR:\n\tOops...There's no such a choice.\n\tPlease read the instructions carefully and TRY AGAIN!");
            }
        }
        //Store all the existing data in a text file
        gui_data(queueObj, waiting);

        //Launch the GUI
        Application.launch(Task_4_Application.class, args);
    }

    //Find the minimum queue
    public static FuelQueue getMinimumQueue(FuelQueue[] queueObj){
        //This method adds a customer to the queue with the minimum length
        int queue_1_nullCount = queueObj[0].getNullCount();
        int queue_2_nullCount = queueObj[1].getNullCount();
        int queue_3_nullCount = queueObj[2].getNullCount();
        int queue_4_nullCount = queueObj[3].getNullCount();
        int queue_5_nullCount = queueObj[4].getNullCount();


        if ((queue_1_nullCount >= queue_2_nullCount) && (queue_1_nullCount >= queue_3_nullCount) && (queue_1_nullCount >= queue_4_nullCount) && (queue_1_nullCount >= queue_5_nullCount)){
            return queueObj[0];
        } else if ((queue_2_nullCount > queue_1_nullCount) && (queue_2_nullCount >= queue_3_nullCount) && (queue_2_nullCount >= queue_4_nullCount) && (queue_2_nullCount >= queue_5_nullCount)){
            return queueObj[1];
        } else if ((queue_3_nullCount > queue_1_nullCount) && (queue_3_nullCount > queue_2_nullCount) && (queue_3_nullCount >= queue_4_nullCount) && (queue_3_nullCount >= queue_5_nullCount)){
            return queueObj[2];
        } else if ((queue_4_nullCount > queue_1_nullCount) && (queue_4_nullCount > queue_2_nullCount) && (queue_4_nullCount > queue_3_nullCount) && (queue_4_nullCount >= queue_5_nullCount)){
            return queueObj[3];
        } else{
            return queueObj[4];
        }
    }

    //This method pass the inputs to the queue with the minimum length
    public static void addCustomer(FuelQueue[] queueObj){
        int queueNo = 0;
        FuelQueue addObj = getMinimumQueue(queueObj);
        if (addObj == queueObj[0]){
            queueNo = 1;
        }else if (addObj == queueObj[1]){
            queueNo = 2;
        }else if (addObj == queueObj[2]){
            queueNo = 3;
        }else if (addObj == queueObj[3]){
            queueNo = 4;
        }else if (addObj == queueObj[4]){
            queueNo = 5;
        }
        String[] details = getCustomerInputs();
        addObj.addCustomerToQueue(queueNo, details);
    }

    //This method validates if the stock is finished or the queues are full
    public static void validateBeforeAddCustomer(FuelQueue[] queueObj, ArrayList<String[]> waiting){
        if (getStock() == 0){
            System.out.println("NOTE:\n\tOops...The fuel is OUT OF STOCK. Please come back again when a new stock arrives.\n\tThank You!");

        }
        else if ((queueObj[0].passengerObj[5].getFirstName() != null) && (queueObj[1].passengerObj[5].getFirstName() != null) && (queueObj[2].passengerObj[5].getFirstName() != null) && (queueObj[3].passengerObj[5].getFirstName() != null) && (queueObj[4].passengerObj[5].getFirstName() != null)) {
            System.out.println("NOTE:\n\tOops...All The Fuel Queues are Full!\n\tYou'll be added to The Waiting Queue!\n");
            String[] details = getCustomerInputs();
            waiting.add(details);
            System.out.println("NOTE:\n\t" + details[0] + " " + details[1] + " was added to the Waiting Queue successfully!");

        }
        else {
            addCustomer(queueObj);
        }
    }

    //This method gets all the customer details from the user
    public static String[] getCustomerInputs(){
        //Defining an object for the Scanner
        Scanner input = new Scanner(System.in);

        String[] details = new String[4];
        do {
            System.out.print("Enter The First Name : ");
            String fName = input.next();
            if (nameValidate(fName)){
                System.out.println("NOTE:\n\tFirst Name can only contain LETTERS! Please Try Again!\n");
                continue;
            }
            if (fName.equals("null")){
                System.out.println("NOTE:\n\tOops..." + fName + " is not a valid name since it is a reserved keyword in JAVA.\n\tPlease Try Again!\n");
            }else {
                details[0] = fName;
                break;
            }
        }while (true);

        do {
            System.out.print("Enter The Second Name : ");
            String lName = input.next();
            if (nameValidate(lName)){
                System.out.println("NOTE:\n\tLast Name can only contain LETTERS! Please Try Again!\n");
                continue;
            }
            if (lName.equals("null")){
                System.out.println("NOTE:\n\tOops..." + lName + " is not a valid name since it is a reserved keyword in JAVA.\n\tPlease Try Again!\n");
            }else {
                details[1] = lName;
                break;
            }
        }while (true);

        do {
            System.out.print("Enter The Vehicle Number : ");
            String vehicleNo = input.next();
            if (vehicleNo.equals("null")){
                System.out.println("NOTE:\n\tOops..." + vehicleNo + " is not a valid input since it is a reserved keyword in JAVA.\n\tPlease Try Again!\n");
            }else {
                details[2] = vehicleNo;
                break;
            }
        }while (true);

        while (true){
            try {
                System.out.print("Enter The Number of Liters Required : ");
                int liters = input.nextInt();
                if (liters <= 0){
                    System.out.println("ERROR:\n\tThe required fuel liters cannot be less than or equal to zero!\n\tPlease Try Again!\n");
                } else if (liters > 10) {
                    System.out.println("ERROR:\n\tOops...The maximum amount of liters per each customer is 10L!\n\tPlease Try Again!\n");
                } else if (liters > stock) {
                    System.out.println("ERROR:\n\tThe required fuel liters cannot exceed the existing amount of liters in the stock\n\tPlease Try Again!\n");
                } else {
                    details[3] = String.valueOf(liters);
                    setStock(getStock() - liters);
                    break;
                }
            }catch (Exception e){
                System.out.println("ERROR:\n\tThe required fuel liters can only be a NUMBER!\n\tPlease Try Again!\n");
                input.nextLine();
            }
        }
        return details;
    }

    //This method validates whether the user input name contains any other symbols except letters
    public static boolean nameValidate(String name){
        char[] charArray = name.toCharArray();
        for (char i : charArray){
            if (!Character.isAlphabetic(i)){
                return true;
            }
        }
        return false;
    }

    //Add stock
    public static void addStock(Scanner input){
        //This method adds new fuel stock to the existing stock.
        while (true){
            try {
                System.out.print("Enter the amount of the new stock : ");
                int new_Stock = input.nextInt();     //Getting the amount of the new stock from the user.
                if ((getStock() + new_Stock) > 6600){    //If the stock is greater than 6600, then an error message will be displayed.
                    System.out.println("ERROR:\n\tThe amount of the fuel stock cannot exceed 6600 Liters!\n\tPlease Try Again!\n");
                    input.nextLine();
                } else if (new_Stock < 0) {     //If the stock is a negative number, an error message will be displayed.
                    System.out.println("ERROR:\n\tThe amount of the fuel stock cannot be a NEGATIVE NUMBER!\n\tPlease Try Again!\n");
                    input.nextLine();
                } else {
                    setStock(getStock()+new_Stock);
                    System.out.println("NOTE:\n\t" + new_Stock + " liters have been added to the stock successfully!");
                    break;
                }
            }catch (Exception e){      //If the fuel stock is not an integer value, then an error message will be displayed.
                System.out.println("ERROR:\n\tThe amount of the fuel stock can only be a NUMBER!\n\tPlease Try Again!\n");
                input.nextLine();
            }
        }
    }

    //Get The Queue Number
    public static int get_Queue_Number(Scanner input){
        //This method validates the user input for the queue number which can only be 1/2/3/4/5.
        //This method is used to get the queue number when removing a customer and removing a served customer.
        int queueNo;
        while (true) {
            try {
                System.out.print("Enter The Queue Number (1/2/3/4/5): ");
                queueNo = input.nextInt();
                if (!(1 <= queueNo && queueNo <= 5)) {    //If the queue number is not a number between 1 and 5 inclusive, then an error message will be displayed.
                    System.out.println("ERROR:\n\tThe Queue number can only be 1 or 2 or 3 or 4 or 5!\n\tPlease Try Again!\n");
                    input.nextLine();
                }else {
                    break;
                }
            } catch (Exception e) {     //If the user input for the queue number is not an integer value, then an error message will be displayed.
                System.out.println("ERROR:\n\tThe Input can only be a number!\n\tPlease Try Again!\n");
                input.nextLine();
            }
        }
        return queueNo;
    }

    //Remove a customer from a Queue (Specific Position)
    public static void remove_Customer(Scanner input, FuelQueue[] queueObj, ArrayList<String[]> waiting) {
        //This method removes a customer from the queue by index.
        int queueNo = get_Queue_Number(input);    //Calling the get_Queue_Number method to validate the queue number.
        while (true) {
            try {
                System.out.print("Enter the position (0/1/2/3/4/5) : ");
                int position = input.nextInt();
                if (!(0 <= position && position <= 5)) {     //If the position is not a number between 0 and 5 inclusive, then an error message will be displayed.
                    System.out.println("ERROR:\n\tThe Position can only be a number between 0 to 6 inclusive!\n\tPlease Try Again!\n");
                    input.nextLine();
                }else {
                    if (queueNo == 1){
                        setStock(queueObj[0].removeFromIndex(queueNo, position, getStock(), waiting));
                    }else if (queueNo == 2){
                        setStock(queueObj[1].removeFromIndex(queueNo, position, getStock(), waiting));
                    }else if (queueNo == 3){
                        setStock(queueObj[2].removeFromIndex(queueNo, position, getStock(), waiting));
                    }else if (queueNo == 4){
                        setStock(queueObj[3].removeFromIndex(queueNo, position, getStock(), waiting));
                    }else if (queueNo == 5){
                        setStock(queueObj[4].removeFromIndex(queueNo, position, getStock(), waiting));
                    }
                    break;
                }
            }catch (Exception e) {      //If the position is not an integer value, then an error message will be displayed.
                System.out.println("ERROR:\n\tThe Input can only be a number!\n\tPlease Try Again!\n");
                input.nextLine();
            }
        }
    }


    //Remove a served customer from a Queue
    public static void remove_Served_Customer(Scanner input, FuelQueue[] queueObj, ArrayList<String[]> waiting){
        //This method removes the first served customer from the queue.
        int queueNo = get_Queue_Number(input);    //Calling the get_Queue_Number method to validate the queue number.
        if (queueNo == 1){
            queueObj[0].removeServedCustomer(queueNo, waiting);
        }else if (queueNo == 2){
            queueObj[1].removeServedCustomer(queueNo, waiting);
        }else if (queueNo == 3){
            queueObj[2].removeServedCustomer(queueNo, waiting);
        }else if (queueNo == 4){
            queueObj[3].removeServedCustomer(queueNo, waiting);
        }else if (queueNo == 5){
            queueObj[4].removeServedCustomer(queueNo, waiting);
        }
    }


    //This method calls the sort method in the FuelQueue objects
    public static void callSortFunc(FuelQueue[] queueObj){
        for (int i = 0; i < 5; i++){
            System.out.println("\n************************** Queue " + (i+1) + " **************************");
            String[][] queue = new String[6][4];
            for (int j = 0; j < 6; j++){
                queue[j][0] = queueObj[i].passengerObj[j].getFirstName();
                queue[j][1] = queueObj[i].passengerObj[j].getLastName();
                queue[j][2] = queueObj[i].passengerObj[j].getVehicleNo();
                queue[j][3] = queueObj[i].passengerObj[j].getLiters();
            }
            queueObj[i].sort_Customers(queue);
        }
    }

    //Store program data to a text file
    public static void store_Data(FuelQueue[] queueObj, ArrayList<String[]> waiting){
        //This method stores the program data into a text file.
        try {
            FileWriter file = new FileWriter("Class_Version_Data.txt");   //Creates a text file to store data.

            for (int i = 0; i < 5; i++){
                for (int j = 0; j < 6; j++){
                    file.write(queueObj[i].passengerObj[j].getFirstName() + " ");
                    file.write(queueObj[i].passengerObj[j].getLastName() + " ");
                    file.write(queueObj[i].passengerObj[j].getVehicleNo() + " ");
                    file.write(queueObj[i].passengerObj[j].getLiters() + "\n");
                }
            }

            file.write(getStock() + "\n");   //Storing the fuel stock in the text file.
            for (int i = 0; i < 5; i++){          //Storing the fuel pump incomes in the text file.
                file.write(queueObj[i].getQueue_income() + "\n");
            }

            for (String[] strings : waiting) {  //Storing customers in the waiting queue
                file.write(strings[0] + " ");
                file.write(strings[1] + " ");
                file.write(strings[2] + " ");
                file.write(strings[3] + "\n");
            }
            file.close();
        }catch (Exception e){
            System.out.println("ERROR:\n\t" + e);
        }
        System.out.println("The data is processing...\n");      //Displaying a message to the user that the process has happened successfully.
        System.out.println("NOTE:\n\tThe data was stored successfully into the file.");
    }


    //Load data from a text file
    public static void load_Data(FuelQueue[] queueObj, ArrayList<String[]> waiting){
        //This method loads the data from a text file.
        try {
            File file = new File("Class_Version_Data.txt");   //Opening the text file.
            Scanner read_File = new Scanner(file);    //Reads the content in the text file.

            int line_Count = 0;
            for (int i = 0; i < 5; i++){
                for (int j = 0; j <6; j++){
                    line_Count++;   //line_Count counts how many lines are there in the text file.
                    String[] toArray = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                    if (toArray[0].equals("null")){
                        queueObj[i].passengerObj[j].setFirstName(null);
                        queueObj[i].passengerObj[j].setLastName(null);
                        queueObj[i].passengerObj[j].setVehicleNo(null);
                        queueObj[i].passengerObj[j].setLiters(null);
                    }else {
                        queueObj[i].passengerObj[j].setFirstName(toArray[0]);
                        queueObj[i].passengerObj[j].setLastName(toArray[1]);
                        queueObj[i].passengerObj[j].setVehicleNo(toArray[2]);
                        queueObj[i].passengerObj[j].setLiters(toArray[3]);
                    }
                }
            }

            for (int i = 0; i < 2; i++){
                if (line_Count == 30){
                    setStock(Integer.parseInt(read_File.nextLine()));
                    line_Count++;   //line_Count counts how many lines are there in the text file.
                }else if (line_Count > 30){
                    for (int k = 0; k < 5; k++){
                        queueObj[k].setQueue_income(Integer.parseInt(read_File.nextLine()));
                        line_Count++;   //line_Count counts how many lines are there in the text file.
                    }
                }
            }

            while (read_File.hasNext()){    //Loading the waiting queue customers
                String[] toArray = read_File.nextLine().split(" ");
                waiting.add(toArray);
            }

            if (line_Count == 0){       //If there are no lines in the text file, an error message will be displayed.
                System.out.println("NOTE:\n\tOops...There's no previously available data in the text file.");
            }else {
                System.out.println("The data is loading...\n");     //Displaying a message to the user that the process has happened successfully.
                System.out.println("NOTE:\n\tThe data was loaded successfully!");
            }
        }catch (IOException e){     //If there's no existing text file with that name, an error message will be displayed.
            System.out.println("ERROR:\n\tOops...The text file is not available");
        }
    }


    //This method is to store all the existing data to a text file when the program ends, which can be used in the controller to access the program data.
    public static void gui_data(FuelQueue[] queueObj, ArrayList<String[]> waiting) throws IOException {
        //This method stores the program data into a text file.
        FileWriter file = new FileWriter("Gui_Data.txt");   //Creates a text file to store data.

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 6; j++) {
                file.write(queueObj[i].passengerObj[j].getFirstName() + " ");
                file.write(queueObj[i].passengerObj[j].getLastName() + " ");
                file.write(queueObj[i].passengerObj[j].getVehicleNo() + " ");
                file.write(queueObj[i].passengerObj[j].getLiters() + "\n");
            }
        }

        file.write(getStock() + "\n");   //Storing the fuel stock in the text file.
        for (int i = 0; i < 5; i++) {          //Storing the fuel pump incomes in the text file.
            file.write(queueObj[i].getQueue_income() + "\n");
        }

        for (String[] strings : waiting) {  //Storing customers in the waiting queue
            file.write(strings[0] + " ");
            file.write(strings[1] + " ");
            file.write(strings[2] + " ");
            file.write(strings[3] + "\n");
        }
        file.close();
    }
}
