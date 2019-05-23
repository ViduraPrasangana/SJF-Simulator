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

import java.io.IOException;

public class Job {
    private Stage window;
    private int jobNo;
    private int arrivalTime;
    private int burstTime;
    private String jobColor;
    private Rectangle readyRect,grantRect,currentJob;
    private static Rectangle emptyGrantRect;
    private static StackPane stackForEmptyGrant;
    private StackPane stackForReadyRect,stackForGrant,stackCurrentJob;
    private int waitingTime = 0;
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
        Text textReadyRect = new Text(String.valueOf(this.getJobNo()));
        textReadyRect.setStyle("-fx-font-size: 16pt");
        stackForReadyRect = new StackPane();
        stackForReadyRect.getChildren().addAll(readyRect,textReadyRect);
    }
    public StackPane getReadyRect(){
        return stackForReadyRect;
    }

    public void setGrantRect(){
        grantRect = new Rectangle();
        grantRect.setHeight(82);
        grantRect.setWidth(10);
        grantRect.setFill(Paint.valueOf(this.getJobColor()));
        Text textGrant = new Text(String.valueOf(this.getJobNo()));
        stackForGrant = new StackPane();
        stackForGrant.getChildren().addAll(grantRect,textGrant);
    }
    public StackPane getGrantRect(){
        return stackForGrant;
    }
    public void setCurrentRect(){
        currentJob = new Rectangle();
        currentJob.setWidth(143);
        currentJob.setHeight(27);
        currentJob.setFill(Paint.valueOf(this.getJobColor()));
        Text currentText = new Text(String.valueOf(this.getJobNo()));
        stackCurrentJob = new StackPane();
        stackCurrentJob.getChildren().addAll(currentJob,currentText);
    }
    public static void setEmptyGrantRect(){
        emptyGrantRect = new Rectangle();
//        emptyGrantRect.setStroke(Color.BLACK);
//        emptyGrantRect.setStrokeWidth(1);
        emptyGrantRect.setHeight(82);
        emptyGrantRect.setWidth(10);
        emptyGrantRect.setFill(Color.TRANSPARENT);
        Text textGrant = new Text("n");
        stackForEmptyGrant = new StackPane();
        stackForEmptyGrant.getChildren().addAll(emptyGrantRect,textGrant);

    }
    public static StackPane getEmptyGrantRect(){
        return stackForEmptyGrant;
    }
    public StackPane getCurrentRect(){
        return stackCurrentJob;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void incWaitingTime() {
        this.waitingTime +=1;
    }

}
