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
    protected String taskDate;
    protected boolean taskFinished;
    protected boolean taskReminder;


    public ListItem(TaskPriority taskPriority, String taskName, String taskDate, boolean taskReminder, boolean taskFinished) {
        this.taskPriority = taskPriority;
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskReminder = taskReminder;
        this.taskFinished = taskFinished;
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

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
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
}
