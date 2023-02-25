package com.example.prog_cw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;


public class Task_4_Controller implements Initializable {
    //Defining a 3D array to store all the customers and their details in queues
    String[][][] store = new String[5][6][4];

    //OnAction for the 'Exit' button
    public void quit(ActionEvent event) throws IOException {
        //Deletes the text file when exiting from the GUI
        String filePath = "/Users/sarithwijesundera/Desktop/w1912785_Prog_CW/Gui_Data.txt";
        Path path = Paths.get(filePath);
        try {
            Files.delete(path);
        }catch (Exception e){
            System.out.println("");
        }

        Stage previousWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
        previousWindow.close();
    }

    //OnAction for the 'View All Queues' button
    @FXML
    public void view_all(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("view_all_queues.fxml"));
        Scene newScene = new Scene(root, 1080, 700);
        newStage.setTitle("View All Queues");
        newStage.setScene(newScene);
        newStage.show();

        //Identify previous stage and close it
        Stage previousWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousWindow.close();
    }

    //OnAction for the 'Search a Customer' button
    @FXML
    public void search(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("search.fxml"));
        Scene newScene = new Scene(root, 480, 400);
        newStage.setTitle("Search a Customer");
        newStage.setScene(newScene);
        newStage.show();

        //Identify previous stage and close it
        Stage previousWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousWindow.close();
    }

    //OnAction for the 'Go Back' button
    public void back(ActionEvent event) throws IOException {
        Stage newStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene newScene = new Scene(root, 480, 400);
        newStage.setTitle("Fuel Queue Management System");
        newStage.setScene(newScene);
        newStage.show();

        //Identify previous stage and close it
        Stage previousWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        previousWindow.close();
    }

    //FXid for the text-field of search bar
    @FXML
    private TextField searchBar;

    //OnAction for the 'Search' button in the 'Search a Customer' window.
    public void onSearch(ActionEvent event) throws  IOException{
        //Load the program data from the text file
        try {
            File file = new File("Gui_Data.txt");   //Opening the text file.
            Scanner read_File = new Scanner(file);    //Reads the content in the text file.

            for (int i = 0; i < 6; i++){
                String[] toArray1 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                store[0][i] = toArray1;
            }

            for (int i = 0; i < 6; i++){
                String[] toArray2 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                store[1][i] = toArray2;
            }

            for (int i = 0; i < 6; i++){
                String[] toArray3 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                store[2][i] = toArray3;
            }

            for (int i = 0; i < 6; i++){
                String[] toArray4 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                store[3][i] = toArray4;
            }

            for (int i = 0; i < 6; i++){
                String[] toArray5 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                store[4][i] = toArray5;
            }
            int foundCount = 0;
            int notFoundCount = 0;
            String userSearch = searchBar.getText().toLowerCase();
            for (int i = 0; i < store.length; i++) {
                for (int j = 0; j < 6; j++) {
                    if (store[i][j][0].toLowerCase().equals(userSearch)) {
                        foundCount++;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);  //If a search found, an information alert window appears
                        alert.setTitle("Search Found");
                        alert.setHeaderText("Here's the result " + foundCount);
                        alert.setContentText("First Name :  " + store[i][j][0] + "\nLast Name :  " + store[i][j][1] + "\nVehicle No :  " + store[i][j][2] + "\nLiters :  " + store[i][j][3] + "\n\nQueue Number :  " + (i+1) + "\nPosition :  " + j + " (Starting from '0')");
                        Optional<ButtonType> result = alert.showAndWait();
                    }else {
                        notFoundCount++;
                    }
                }
            }
            if (notFoundCount == 30){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);   //If a search is not found, an information alert window appears
                alert.setTitle("Search Not Found");
                alert.setHeaderText("No Result");
                alert.setContentText("Oops...There are no customers with the name '" + userSearch + "'");
                Optional<ButtonType> result = alert.showAndWait();
            }

        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);  //If the text file is not found, an error alert window appears
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("There are no previously available data");
            Optional<ButtonType> result=alert.showAndWait();
        }
    }

    //OnAction for the 'Update' button in the 'View All Queues' window.
    public void click(ActionEvent event) throws IOException {
        load_Data();
    }

    //FXid's in the 'View All Queues' window.
    @FXML
    private TextArea queue_1;
    @FXML
    private TextArea queue_2;
    @FXML
    private TextArea queue_3;
    @FXML
    private TextArea queue_4;
    @FXML
    private TextArea queue_5;
    @FXML
    private TextArea waiting_queue;
    @FXML
    private Label stock;
    @FXML
    private Label income_1;
    @FXML
    private Label income_2;
    @FXML
    private Label income_3;
    @FXML
    private Label income_4;
    @FXML
    private Label income_5;

    //Load program data from the text file.
    public void load_Data()  {
        try {
            File file = new File("Gui_Data.txt");   //Opening the text file.
            Scanner read_File = new Scanner(file);    //Reads the content in the text file.

            for (int i = 0; i < 6; i++){
                String[] toArray1 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                if (toArray1[0].equals("null")) {    //In this for loop, the values in the converted array will be cloned to the queue.
                    queue_1.appendText("Customer 0" + String.valueOf(i+1) + "\n\t EMPTY\n\n");
                } else {
                    queue_1.appendText("Customer " + 0+(i+1) + "\n");
                    queue_1.appendText("\tFirst Name : " + toArray1[0] + "\n");
                    queue_1.appendText("\tLast Name : " + toArray1[1] + "\n");
                    queue_1.appendText("\tVehicle No : " + toArray1[2] + "\n");
                    queue_1.appendText("\tLiters : " + toArray1[3] + "\n\n");
                }
                store[0][i] = toArray1;
            }

            for (int i = 0; i < 6; i++){
                String[] toArray2 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                if (toArray2[0].equals("null")) {    //In this for loop, the values in the converted array will be cloned to the queue.
                    queue_2.appendText("Customer 0" + String.valueOf(i+1) + "\n\t EMPTY\n\n");
                } else {
                    queue_2.appendText("Customer " + 0+(i+1) + "\n");
                    queue_2.appendText("\tFirst Name : " + toArray2[0] + "\n");
                    queue_2.appendText("\tLast Name : " + toArray2[1] + "\n");
                    queue_2.appendText("\tVehicle No : " + toArray2[2] + "\n");
                    queue_2.appendText("\tLiters : " + toArray2[3] + "\n\n");
                }
                store[1][i] = toArray2;
            }

            for (int i = 0; i < 6; i++){
                String[] toArray3 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                if (toArray3[0].equals("null")) {    //In this for loop, the values in the converted array will be cloned to the queue.
                    queue_3.appendText("Customer 0" + String.valueOf(i+1) + "\n\t EMPTY\n\n");
                } else {
                    queue_3.appendText("Customer " + 0+(i+1) + "\n");
                    queue_3.appendText("\tFirst Name : " + toArray3[0] + "\n");
                    queue_3.appendText("\tLast Name : " + toArray3[1] + "\n");
                    queue_3.appendText("\tVehicle No : " + toArray3[2] + "\n");
                    queue_3.appendText("\tLiters : " + toArray3[3] + "\n\n");
                }
                store[2][i] = toArray3;
            }

            for (int i = 0; i < 6; i++){
                String[] toArray4 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                if (toArray4[0].equals("null")) {    //In this for loop, the values in the converted array will be cloned to the queue.
                    queue_4.appendText("Customer 0" + String.valueOf(i+1) + "\n\t EMPTY\n\n");
                } else {
                    queue_4.appendText("Customer " + 0+(i+1) + "\n");
                    queue_4.appendText("\tFirst Name : " + toArray4[0] + "\n");
                    queue_4.appendText("\tLast Name : " + toArray4[1] + "\n");
                    queue_4.appendText("\tVehicle No : " + toArray4[2] + "\n");
                    queue_4.appendText("\tLiters : " + toArray4[3] + "\n\n");
                }
                store[3][i] = toArray4;
            }

            for (int i = 0; i < 6; i++){
                String[] toArray5 = read_File.nextLine().split(" ");    //Converting each line which is a string to an array.
                if (toArray5[0].equals("null")) {    //In this for loop, the values in the converted array will be cloned to the queue.
                    queue_5.appendText("Customer 0" + String.valueOf(i+1) + "\n\t EMPTY\n\n");
                } else {
                    queue_5.appendText("Customer " + 0+(i+1) + "\n");
                    queue_5.appendText("\tFirst Name : " + toArray5[0] + "\n");
                    queue_5.appendText("\tLast Name : " + toArray5[1] + "\n");
                    queue_5.appendText("\tVehicle No : " + toArray5[2] + "\n");
                    queue_5.appendText("\tLiters : " + toArray5[3] + "\n\n");
                }
                store[4][i] = toArray5;
            }

            stock.setText(read_File.nextLine());    //Reads the stock
            income_1.setText(read_File.nextLine()); //Reads all the incomes of the queues
            income_2.setText(read_File.nextLine());
            income_3.setText(read_File.nextLine());
            income_4.setText(read_File.nextLine());
            income_5.setText(read_File.nextLine());

            int count = 0;
            while (read_File.hasNext()){    //Loading the waiting queue customers
                count++;
                String[] toArray = read_File.nextLine().split(" ");
                waiting_queue.appendText("Waiting Customer " + count + "\n");
                waiting_queue.appendText("\tFirst Name : " + toArray[0] + "\n");
                waiting_queue.appendText("\tLast Name : " + toArray[1] + "\n");
                waiting_queue.appendText("\tVehicle No : " + toArray[2] + "\n");
                waiting_queue.appendText("\tLiters : " + toArray[3] + "\n\n");
            }
            if (count == 0){
                waiting_queue.appendText("EMPTY");
            }


        } catch (FileNotFoundException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);    //If the text file is not found, an error alert window appears
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("There are no previously available data");
            Optional<ButtonType> result=alert.showAndWait();
        }
    }

    //Initialize method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}