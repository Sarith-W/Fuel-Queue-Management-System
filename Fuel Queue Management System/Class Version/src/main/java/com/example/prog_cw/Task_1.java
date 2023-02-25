package com.example.prog_cw;

//Importing the packages
import java.util.*;
import java.io.*;
public class Task_1 {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);   //Defining a variable for the Scanner
        String[] queue_1 = new String[6];      //Initializing 3 arrays for queues
        String[] queue_2 = new String[6];
        String[] queue_3 = new String[6];
        boolean[] stock_Update = new boolean[1];    //Initializing a separate array to get a boolean value to decide whether the fuel stock should be updated or not
        int stock = 6600;     //Defining the fuel stock
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
                    all_Queues(queue_1,queue_2,queue_3);
                    break;
                case "101":
                case "VEQ":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("View all Empty Queues.");
                    empty_Queues(queue_1,queue_2,queue_3);
                    break;
                case "102":
                case "ACQ":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Add customer to a Queue.\n");
                    System.out.println(add_Customer(stock_Update,input,queue_1,queue_2,queue_3));
                    if (stock_Update[0]){   //If true, the stock will be updated (reduce by 10)
                        stock -= 10;
                    }
                    if (stock <= 500){     //If the stock is less than or equals to 500, a warning message will be displayed
                        System.out.println("\nWARNING:\n\tThe fuel stock has reached a value of 500 Liters");
                    }
                    //I assumed when a customer is added to a queue, that customer is already being served. Therefore, I removed 10 liters from the stock
                    break;
                case "103":
                case "RCQ":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Remove a customer at a specific location from a Queue.\n");
                    System.out.println(remove_Customer(input,stock_Update,queue_1,queue_2,queue_3));
                    if (stock_Update[0]){   //If true, the stock will be updated (increase by 10)
                        if ((stock + 10) > 6600){
                            stock = 6600;
                        }else {
                            stock += 10;
                        }
                    }
                    break;
                case "104":
                case "PCQ":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Remove a served customer.\n");
                    System.out.println(remove_Served_Customer(input,queue_1,queue_2,queue_3));
                    //I assumed that when removing a served customer from a queue, it's practical to remove the first customer in the queue.
                    break;
                case "105":
                case "VCS":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("View Customers Sorted in alphabetical order\n");
                    System.out.println("Queue 1:");
                    sort_Customers(queue_1);
                    System.out.println("\nQueue 2:");
                    sort_Customers(queue_2);
                    System.out.println("\nQueue 3:");
                    sort_Customers(queue_3);
                    break;
                case "106":
                case "SPD":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Store Program Data into file.\n");
                    System.out.println(store_Data(stock,queue_1,queue_2,queue_3));
                    break;
                case "107":
                case "LPD":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Load Program Data from file.\n");
                    stock = load_Data(stock,queue_1,queue_2,queue_3);
                    break;
                case "108":
                case "STK":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("View Remaining Fuel Stock.\n");
                    System.out.println("Remaining Fuel Stock : " + stock + " Liters out of 6600 Liters");
                    break;
                case "109":
                case "AFS":
                    System.out.println("\n----------------------------------------------------------------------------");
                    System.out.println("Add Fuel Stock.\n");
                    stock = add_Stock(input, stock);
                    System.out.println("NOTE:\n\tThe new fuel stock is " + stock + " Liters");
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
    }
    //View all Fuel Queues
    public static void all_Queues(String[] queue_1, String[] queue_2, String[] queue_3){
        //This method displays all the queues
        System.out.println("\nQueue 1:\n" + Arrays.toString(queue_1));
        System.out.println("\nQueue 2:\n" + Arrays.toString(queue_2));
        System.out.println("\nQueue 3:\n" + Arrays.toString(queue_3));
    }

    //View all Empty Queues
    public static void empty_Queues(String[] queue_1, String[] queue_2, String[] queue_3){
        //This method checks if there are any empty spaces in each of the arrays.
        //If there is at least one empty space, then that specific array will be considered and displayed as an empty queue.
        for (String i: queue_1){
            if (i == null){
                System.out.println("\nQueue 1:\n" + Arrays.toString(queue_1));
                break;
            }
        }
        for (String i: queue_2){
            if (i == null){
                System.out.println("\nQueue 2:\n" + Arrays.toString(queue_2));
                break;
            }
        }
        for (String i: queue_3){
            if (i == null){
                System.out.println("\nQueue 3:\n" + Arrays.toString(queue_3));
                break;
            }
        }
    }

    //Get The Queue Number
    public static int get_Queue_Number(Scanner input){
        //This method validates the user input for the queue number which can only be 1/2/3.
        //This method is used to get the queue number when adding a customer, removing a customer and removing a served customer.
        int queue;
        while (true) {
            try {
                System.out.print("Enter The Queue Number (1/2/3): ");
                queue = input.nextInt();
                if (!(1 <= queue && queue <= 3)) {    //If the queue number is not a number between 1 and 3 inclusive, then an error message will be displayed.
                    System.out.println("ERROR:\n\tThe Queue number can only be 1 or 2 or 3!\n\tPlease Try Again!\n");
                    input.nextLine();
                }else {
                    break;
                }
            } catch (Exception e) {     //If the user input for the queue number is not an integer value, then an error message will be displayed.
                System.out.println("ERROR:\n\tThe Input can only be a number!\n\tPlease Try Again!\n");
                input.nextLine();
            }
        }
        return queue;
    }

    //Updating the array after a customer was added
    public static int update_Array(String[] queue, String name, boolean[] stock_Update, int is_Full){
        //This method adds a new customer to the first empty space in the queue
        for (int i = 0; i < queue.length; i++){
            if (queue[i] == null){      //If the space is empty, then it will replace that empty space with the customer name.
                queue[i] = name;
                stock_Update[0] = true;   //Since the stock_Update has a true value, the fuel stock will be reduced by 10 in the main method.
                break;
            }else {
                is_Full++;      //If it's not an empty space, then the is_Full will be increased by 1
                stock_Update[0] = false;
            }
        }
        return is_Full;
    }

    //Validating the user input name
    public static boolean nameValidate(String name){
        char[] charArray = name.toCharArray();
        for (char i : charArray){
            if (!Character.isAlphabetic(i)){
                return true;
            }
        }
        return false;
    }

    //Add customer to a Queue
    public static String add_Customer(boolean[] stock_Update, Scanner input, String[] queue_1, String[] queue_2, String[] queue_3){
        //This method gets the queue number and the customer name from the user and adds the customer to a queue.
        int is_Full = 0;
        String name;
        int queue = get_Queue_Number(input);   //Calling the get_Queue_Number method to validate the queue number.
        do {
            System.out.print("Enter the name of the customer : ");    //If user inputs 'null' as the customer name, it will display an error message.
            name = input.next();
            if (name.equals("null")){
                System.out.println("NOTE:\n\tOops..." + name + " is not a valid name since it is a reserved keyword in JAVA.\n\tPlease Try Again!\n");
            } else if (nameValidate(name)) {
                System.out.println("NOTE:\n\tThe Name can only contain LETTERS! Please Try Again!\n");
            } else {
                break;
            }
        }while (true);
        switch (queue){
            case 1:
                is_Full = update_Array(queue_1, name, stock_Update, is_Full);    //Calling the update_Array method to update the queue.
                break;
            case 2:
                is_Full = update_Array(queue_2, name, stock_Update, is_Full);
                break;
            case 3:
                is_Full = update_Array(queue_3, name, stock_Update, is_Full);
                break;
        }
        if (is_Full == 6){      //If it's true, it will display a message saying the queue is full.
            return "NOTE:\n\tOops...The Queue is full!";
        }else {
            return "NOTE:\n\t" + name + " was added to the Queue " + queue + " successfully!";
        }
    }

    //Shifting the queue after a customer was removed
    public static void shift_Queue(int position, String[] queue){
        //This method shifts the remaining customers to the left, when a customer was removed.
        if (position == 5){
            queue[position] = null;
        }else {
            for (int i = 0; i < queue.length; i++){
                if ((position + i + 1) == 5){   //This only becomes true for the 4th index. If it's true, the index 4 will be replaced by the 5th index.
                    queue[4] = queue[5];
                    queue[5] = null;
                    break;
                }else {
                    queue[position + i] = queue[position + i + 1];    //The index will be replaced by the index after that.
                }
            }
        }
    }

    //Remove a customer from a Queue (Specific Position)
    public static String remove_Customer(Scanner input, boolean[] stock_Update, String[] queue_1, String[] queue_2, String[] queue_3){
        //This method removes a customer from the queue by index.
        int position;
        int queue = get_Queue_Number(input);    //Calling the get_Queue_Number method to validate the queue number.
        while (true){
            try{
                System.out.print("Enter the position (0/1/2/3/4/5) : ");
                position = input.nextInt();
                if (!(0 <= position && position <= 5)){     //If the position is not a number between 0 and 5 inclusive, then an error message will be displayed.
                    System.out.println("ERROR:\n\tThe Position can only be a number between 0 to 6 inclusive!\n\tPlease Try Again!\n");
                    input.nextLine();
                } else {
                    break;
                }
            } catch (Exception e){      //If the position is not an integer value, then an error message will be displayed.
                System.out.println("ERROR:\n\tThe Input can only be a number!\n\tPlease Try Again!\n");
                input.nextLine();
            }
        }
        switch (queue){
            case 1:
                if (queue_1[position] != null){     //If the position is not null, then it calls the shift_Queue method to replace the positions
                    shift_Queue(position,queue_1);
                }else {
                    stock_Update[0] = false;      //Since there's no customer at that position, the fuel stock won't be updated.
                    return "NOTE:\n\tOops...There's no customer at the position " + position + " in the Queue " + queue;
                }
                break;
            case 2:
                if (queue_2[position] != null){
                    shift_Queue(position,queue_2);
                }else {
                    stock_Update[0] = false;
                    return "NOTE:\n\tOops...There's no customer at the position " + position + " in the Queue " + queue;
                }
                break;
            case 3:
                if (queue_3[position] != null){
                    shift_Queue(position,queue_3);
                }else {
                    stock_Update[0] = false;
                    return "NOTE:\n\tOops...There's no customer at the position " + position + " in the Queue " + queue;
                }
                break;
        }
        stock_Update[0] = true;     //Since the stock_Update has a true value, the fuel stock will be increased by 10 in the main method.
        return "NOTE:\n\tThe customer at the position " + position + " was removed from the Queue " + queue + " successfully!";
    }

    //Remove a served customer from a Queue
    public static String remove_Served_Customer(Scanner input, String[] queue_1, String[] queue_2, String[] queue_3) {
        //This method removes the first served customer from the queue.
        int queue = get_Queue_Number(input);    //Calling the get_Queue_Number method to validate the queue number.
        switch (queue){
            case 1:
                if (queue_1[0] != null){
                    shift_Queue(0,queue_1);
                }else {
                    return "NOTE:\n\tOops...There are no customers in the Queue " + queue;
                }
                break;
            case 2:
                if (queue_2[0] != null){
                    shift_Queue(0,queue_2);
                }else {
                    return "NOTE:\n\tOops...There are no customers in the Queue " + queue;
                }
                break;
            case 3:
                if (queue_3[0] != null){
                    shift_Queue(0,queue_3);
                }else {
                    return "NOTE:\n\tOops...There are no customers in the Queue " + queue;
                }
                break;
        }
        return "NOTE:\n\tThe first customer was removed from the Queue " + queue + " successfully!";
    }

    //View Customers Sorted in alphabetical order
    public static void sort_Customers(String[] queue){
        //This method sorts the customer names in the alphabetical order without using the library sort routine.
        int name_Count = 0, max_Count, clone;
        String[] sorted_Queue = new String[6];  //Initializing a new queue to store the sorted customer names.
        for (String x: queue) {
            if (x != null) {     //This loop determines how many customer names are there in the queue without the null values.
                name_Count++;
            }
        }
        for (String name : queue) {
            if (name != null) {
                name = name.toLowerCase();
                max_Count = 0;    //max_Count variable increases the count when a name appears at the first in the alphabetical order than the other names in the queue.
                clone = 0;    //clone variable counts how many repeated names are there in the queue.
                //The below loop compares each name in the queue with all the names in the same queue.
                for (String compare_Name : queue) {
                    if (compare_Name != null){
                        compare_Name = compare_Name.toLowerCase();
                        if (!name.equals(compare_Name)){   //If the name is not equals to the comparison name and null, then the comparison statement will run.
                            if (name.compareToIgnoreCase(compare_Name) < 0) {
                                max_Count++;    //If the name is at the first in the alphabetical order than the comparison name, then it increases by 1.
                            }
                        }else if (name.equalsIgnoreCase(compare_Name)){
                            clone++;    //If the name is same as the comparison name, then the clone increases by 1.
                        }
                    }
                }
                for (int i = 0; i < name_Count; i++) {
                    if (max_Count == ((name_Count - 1) - i)) {
                        sorted_Queue[i] = name;
                        for (int j = 0; j < clone; j++){
                            sorted_Queue[i-j] = name;   //If there are customers with the same name, then those will be appeared next to the other.
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(sorted_Queue));    //Displaying the sorted queue.
    }

    //Store data into a file
    public static String store_Data(int stock, String[] queue_1, String[] queue_2, String[] queue_3) throws IOException{
        //This method stores the program data into a text file.
        try {
            FileWriter file = new FileWriter("Program_Data.txt");   //Creates a text file to store data.
            file.write((Arrays.toString(queue_1)).replaceAll("[\\[\\],]","") + "\n");   //Converting arrays to string and replacing all the "[ , ]" symbols with empty spaces.
            file.write((Arrays.toString(queue_2)).replaceAll("[\\[\\],]","") + "\n");   //Storing all the queues in the text file.
            file.write((Arrays.toString(queue_3)).replaceAll("[\\[\\],]","") + "\n");
            file.write(String.valueOf(stock) + "\n");   //Converting the fuel stock to a string and storing it in the text file.
            file.close();
        }catch (Exception e){
            System.out.println("ERROR:\n\t" + e);
        }
        System.out.println("The data is processing...\n");      //Displaying a message to the user that the process has happened successfully.
        return "NOTE:\n\tThe data was stored successfully into the file.";
    }

    //Load data from a file
    public static int load_Data(int stock, String[] queue_1, String[] queue_2, String[] queue_3) throws IOException{
        //This method loads the data from a text file.
        int line_Count = 0;
        try {
            File file = new File("Program_Data.txt");   //Opening the text file.
            Scanner read_File = new Scanner(file);    //Reads the content in the text file.
            while (read_File.hasNext()){
                line_Count++;   //line_Count counts how many lines are there in the text file.
                switch (line_Count){
                    case 1:
                        String[] toArray1 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                        for (int i = 0; i < queue_1.length; i++){
                            if (toArray1[i].equals("null")){    //In this for loop, the values in the converted array will be cloned to the queue.
                                queue_1[i] = null;
                            } else {
                                queue_1[i] = toArray1[i];
                            }
                        }
                        break;
                    case 2:
                        String[] toArray2 = read_File.nextLine().split(" ");
                        for (int i = 0; i < queue_2.length; i++){
                            if (toArray2[i].equals("null")){
                                queue_2[i] = null;
                            } else {
                                queue_2[i] = toArray2[i];
                            }
                        }
                        break;
                    case 3:
                        String[] toArray3 = read_File.nextLine().split(" ");
                        for (int i = 0; i < queue_3.length; i++){
                            if (toArray3[i].equals("null")){
                                queue_3[i] = null;
                            } else {
                                queue_3[i] = toArray3[i];
                            }
                        }
                        break;
                    case 4:
                        stock = Integer.parseInt(read_File.nextLine());
                        break;
                }
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
        return stock;
    }

    //Add Fuel Stock
    public static int add_Stock(Scanner input, int stock){
        //This method adds new fuel stock to the existing stock.
        while (true){
            try {
                System.out.print("Enter the amount of the new stock : ");
                int new_Stock = input.nextInt();     //Getting the amount of the new stock from the user.
                if ((stock + new_Stock) > 6600){    //If the stock is greater than 6600, then an error message will be displayed.
                    System.out.println("ERROR:\n\tThe amount of the fuel stock cannot exceed 6600 Liters!\n\tPlease Try Again!\n");
                    input.nextLine();
                } else if (new_Stock < 0) {     //If the stock is a negative number, an error message will be displayed.
                    System.out.println("ERROR:\n\tThe amount of the fuel stock cannot be a NEGATIVE NUMBER!\n\tPlease Try Again!\n");
                    input.nextLine();
                } else {
                    stock += new_Stock;
                    break;
                }
            }catch (Exception e){      //If the fuel stock is not an integer value, then an error message will be displayed.
                System.out.println("ERROR:\n\tThe amount of the fuel stock can only be a NUMBER!\n\tPlease Try Again!\n");
                input.nextLine();
            }
        }
        return stock;
    }
}