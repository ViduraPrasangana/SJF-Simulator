package sample;

import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;

public class AddNewController {
    private static Job currentJob;
    public Button add;
    public TextField jobNoText,jobArrivalTime,jobBurstTime;
    public ColorPicker jobColor;
    private static Main main;

    public static void setMain(Main main){
        AddNewController.main = main;
    }

    public static void setCurrentJob(Job job){
        AddNewController.currentJob = job;
    }
    public void onClickAdd(){
        boolean success = true;
        if (jobBurstTime.getCharacters().toString().equals("")) {
            jobBurstTime.requestFocus();
            success = false;
        } else {
            currentJob.setBurstTime(Integer.parseInt(jobBurstTime.getCharacters().toString()));
        }
        if (jobArrivalTime.getCharacters().toString().equals("")){
            jobArrivalTime.requestFocus();
            success = false;
        } else {
            currentJob.setArrivalTime(Integer.parseInt(jobArrivalTime.getCharacters().toString()));
        }
        if (jobNoText.getCharacters().toString().equals("")) {
            jobNoText.requestFocus();
            success = false;
        } else {
            currentJob.setJobNo(Integer.parseInt(jobNoText.getCharacters().toString()));
        }

        currentJob.setJobColor("#"+jobColor.getValue().toString().substring(2,8));
        if(success) {
            currentJob.close();
            main.addToJobs(currentJob);
        }
    }
    public void onClickCancel(){
        currentJob.close();
    }
}
