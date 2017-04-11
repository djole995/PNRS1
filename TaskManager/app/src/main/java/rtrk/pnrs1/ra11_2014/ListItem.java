package rtrk.pnrs1.ra11_2014;

import android.app.LauncherActivity;
import android.graphics.Color;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Milan on 7.4.2017.
 */

public class ListItem implements Serializable {

    public enum TaskPriority {LOW, MEDIUM, HIGH};
    protected TaskPriority taskPriority;
    protected String taskName;
    protected int[] taskDate;
    protected  String taskDescription;
    protected boolean taskFinished;
    protected boolean taskReminder;


    public ListItem(TaskPriority taskPriority, String taskName, String taskDescription, int[] taskDate, boolean taskReminder, boolean taskFinished) {
        this.taskPriority = taskPriority;
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskReminder = taskReminder;
        this.taskFinished = taskFinished;
        this.taskDescription = taskDescription;
    }


    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int[] getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(int[] taskDate) {
        this.taskDate = taskDate;
    }

    public boolean getTaskReminder() {
        return taskReminder;
    }

    public void setTaskReminder(boolean taskReminder) {
        this.taskReminder = taskReminder;
    }

    public boolean getTaskFinished() {
        return taskFinished;
    }

    public void setTaskFinished(boolean taskFinished) {
        this.taskFinished = taskFinished;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

   /* public int[] getTaskDateAsArray() {
        String[] day = new String[3];
        int[] ret = new int[3];
        day = taskDate.split("/");

        for(int i = 0; i < 3; i++) {

        }
    }*/
}
