package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Thread.sleep;

public class Main extends Application {
    private ArrayList<Job> jobList, readyQueue;
    private boolean start = false;
    private int counter;
    private static Controller controller;
    private Timeline simulate;

    @Override
    public void start(Stage primaryStage) throws Exception {
        counter = 0;
        jobList = new ArrayList<>();
        readyQueue = new ArrayList<>();
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Shortest Job First Algorithm Simulator");
        primaryStage.setScene(new Scene(root, 655, 557));
        primaryStage.show();

        Controller.setMain(this);
        AddNewController.setMain(this);
    }


    public static void main(String[] args) {
        launch(args);
    }

    public Job newJob() {
        Job currentJob = new Job();
        return currentJob;
    }

    public void addToJobs(Job job) {
        jobList.add(job);
        updateTable(job);
    }

    public void updateTable(Job job) {
        controller.addRowToTable(job);
    }

    public static void setController(Controller controller) {
        Main.controller = controller;
    }

    public void sort(ArrayList arr) {
        Collections.sort(arr, (Comparator<Job>) (j1, j2) -> String.valueOf(j1.getBurstTime()).compareToIgnoreCase(String.valueOf(j2.getBurstTime())));
    }

    public void startSimulation() {
        start = true;

        simulate = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event1) -> {
            for (Job job : jobList) {
                if (job.getArrivalTime() <= counter) {
                    readyQueue.add(job);

                }
            }
            sort(readyQueue);
            controller.removeAllReadyQ(readyQueue);
            for (Job job : readyQueue) {
                if (jobList.contains(job)) {
                    jobList.remove(job);
                }
                controller.addRectangle(job);

            }
            if (jobList.isEmpty()){
                simulate.stop();
            }
            counter+=1;
        }));
        simulate.setCycleCount(Timeline.INDEFINITE);
        simulate.play();
    }
    public void pauseSimulate(){
        simulate.pause();
    }
}
