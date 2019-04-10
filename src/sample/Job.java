package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.plugin2.util.ColorUtil;

import java.awt.*;
import java.io.IOException;

public class Job {
    private Stage window;
    private int jobNo;
    private int arrivalTime;
    private int burstTime;
    private String jobColor;
    private Rectangle readyRect;
    private StackPane stack;
    public void add() throws IOException {
        Parent root  = FXMLLoader.load(getClass().getResource("addNew.fxml"));
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add New Job");
        window.setScene(new Scene(root,471,247));
        window.show();
    }
    public void close(){
        window.close();
    }

    public int getJobNo() {
        return jobNo;
    }

    public void setJobNo(int jobNo) {
        this.jobNo = jobNo;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public String getJobColor() {
        return jobColor;
    }

    public void setJobColor(String jobColor) {
        this.jobColor = jobColor;
    }

    public void setReadyRect(){
        readyRect = new Rectangle();
        readyRect.setHeight(70);
        readyRect.setWidth(30);
        readyRect.setFill(Paint.valueOf(this.getJobColor()));
        Text text = new Text(String.valueOf(this.getJobNo()));
        text.setStyle("-fx-font-size: 16pt");
        stack = new StackPane();
        stack.getChildren().addAll(readyRect,text);
    }
    public StackPane getReadyRect(){
        return stack;
    }
}
