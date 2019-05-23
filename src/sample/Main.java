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


public class Main extends Application {
    private ArrayList<Job> AllJobs, jobList, readyQueue,arrived;
    private int counter;
    private static Controller controller;
    private Timeline simulate;
    private Job currentJob;
    private int currentTime;

    @Override
    public void start(Stage primaryStage) throws Exception {
        counter = 0;
        jobList = new ArrayList<>();
        readyQueue = new ArrayList<>();
        arrived = new ArrayList<>();
        AllJobs =new ArrayList<>();
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
        AllJobs.add(job);
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

        simulate = new Timeline(new KeyFrame(Duration.seconds(1), (ActionEvent event1) -> {

            for (Job job : jobList) {
                if (job.getArrivalTime() <= counter) {
                    readyQueue.add(job);
                    arrived.add(job);
                }
            }
            for (Job job : readyQueue) {
                if (jobList.contains(job)) {
                    jobList.remove(job);
                }

            }
            controller.setAvgWaitingTime(waitingTime(arrived));
            controller.setAvgTurnaroundTime(turnArroundTime(arrived));

            sort(readyQueue);


            if (!readyQueue.isEmpty()) {
                if (currentJob == null) {
                    currentJob = readyQueue.get(0);
                    readyQueue.remove(currentJob);
                    controller.removeReadyRect(currentJob);
                    controller.addCurrentRect(currentJob);
                    currentTime = 0;
                }
            }
            if (currentJob != null) {
                controller.addGrantRect(currentJob);
                currentTime += 1;
                if (currentTime >= currentJob.getBurstTime()) {
                    controller.removeCurrentRect(currentJob);
                    currentJob = null;
                }
            }else{
                controller.addEmptyGrantRec();
            }
            controller.removeAllReadyRect(readyQueue);
            for (Job job : readyQueue) {
                controller.addReadyRect(job);
                job.incWaitingTime();
            }
            if (jobList.isEmpty() && readyQueue.isEmpty() && (currentJob == null)) {
                simulate.stop();
            }
            counter += 1;
            controller.setTime(counter);

        }));
        simulate.setCycleCount(Timeline.INDEFINITE);
        simulate.play();
    }

    public void pauseSimulate() {
        simulate.pause();
    }

    public void resumeSimulate() {
        simulate.play();
    }

    public float waitingTime(ArrayList<Job> list) {
        int sum = 0;
        for (Job job : list) {
            sum += job.getWaitingTime();
        }
        return (list.size()==0) ?  0: sum / list.size();
    }
    public float turnArroundTime(ArrayList<Job> list) {
        float sum = 0;
        for (Job job : list) {
            sum += job.getWaitingTime()+job.getBurstTime();
        }
        return (list.size()==0) ?  0: sum / list.size();
    }
    public void reset(){
        arrived.clear();
        readyQueue.clear();
        jobList = AllJobs;
        counter = 0;
        currentTime= 0;
    }
}
