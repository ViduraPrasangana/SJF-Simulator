package sample;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Callback;

import javax.swing.*;
//import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Button addNew;
    public TableView table;
    public TableColumn tableColor,tableNo,tableArrival,tableBurst;
    public HBox readyQueue;
    private static Main main;
    private static Job currentJob;
    private ObservableList<Job> data;

    public static void setMain(Main main){
        Controller.main = main;

    }
    public void onClickAddNew() throws IOException {
        currentJob = main.newJob();
        currentJob.add();
        AddNewController.setCurrentJob(currentJob);
    }

    public void addRowToTable(Job job){
        table.getItems().add(job);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColor.setCellValueFactory(new PropertyValueFactory<>("jobColor"));
        tableNo.setCellValueFactory(new PropertyValueFactory<>("jobNo"));
        tableArrival.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        tableBurst.setCellValueFactory(new PropertyValueFactory<>("burstTime"));

        Callback<TableColumn, TableCell> cellFactory =
                new Callback<TableColumn, TableCell>() {
                    public TableCell call(TableColumn p) {
                        TableCell cell = new TableCell<Job, String>() {
                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                setText(null);
                                setStyle("-fx-background-color:"+getString());
                            }

                            private String getString() {
                                return getItem() == null ? "" : getItem();
                            }
                        };


                        return cell;
                    }
                };
        tableColor.setCellFactory(cellFactory);

        Main.setController(this);

    }
    public void start(){
        main.startSimulation();
    }
    public void addRectangle(Job job){
        job.setReadyRect();
        StackPane stack = job.getReadyRect();
        readyQueue.getChildren().add(stack);

    }
    public void removeAllReadyQ(ArrayList<Job> jobs){
        for (Job job:jobs) {
            readyQueue.getChildren().remove(job.getReadyRect());
        }

    }
    public void pause(){
        main.pauseSimulate();
    }
}
