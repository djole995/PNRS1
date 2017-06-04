package rtrk.pnrs1.ra11_2014;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MyService extends Service {
    protected static final long PERIOD = 60000L;

    protected NotificationThread notificationThread;
    protected NotificationCompat.Builder builder;
    protected static TaskDBHelper taskDBHelper;

    protected NotificationManager notificationManager;
    protected MyBinder myBinder;


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    };

    @Override
    public void onCreate() {
        super.onCreate();

        builder = new NotificationCompat.Builder(this).setSmallIcon(R.mipmap.ic_launcher);
        notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        taskDBHelper = new TaskDBHelper(getApplicationContext());

        ListItem[] tasks = taskDBHelper.readTasks();

        for(int i = 0; i < tasks.length; i++)
            MainActivity.customAdapter.addTask(tasks[i]);

        notificationThread = new NotificationThread();
        notificationThread.start();
        myBinder = new MyBinder(notificationManager, builder);


    }

    public static void addTask(ListItem listItem) {
        taskDBHelper.insertTask(listItem);
    }

    public static void modifyTask(ListItem listItem, String taskName) {
        taskDBHelper.updateTask(listItem, taskName);
    }

    public static void deleteTask(String taskName) {
        taskDBHelper.deleteTask(taskName);
    }

    public static ListItem[] readTasks() {
        return taskDBHelper.readTasks();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        notificationThread.exit();
    }

    private class NotificationThread extends Thread {
        private boolean run = false;

        private NotificationThread() {

        }

        @Override
        public synchronized void start() {
            run = true;
            super.start();
        }

        public synchronized void exit() {
            //run = false;
        }

        @Override
        public void run() {
            while(run) {
                ListItem[] taskList = taskDBHelper.readTasks();
                Calendar currentTime = Calendar.getInstance();

                builder.setContentTitle("Task Manager");

                for(int i = 0; i < taskList.length; i++) {
                    int date[] = taskList[i].getTaskDate();

                    Calendar taskTime = Calendar.getInstance();

                    taskTime.set(date[2], date[1]-1, date[0], date[3], date[4]);
                    if(taskList[i].getTaskReminder() == true) {
                        if (taskTime.get(Calendar.YEAR) == currentTime.get(Calendar.YEAR)
                                && taskTime.get(Calendar.MONTH) == currentTime.get(Calendar.MONTH)
                                && taskTime.get(Calendar.DAY_OF_MONTH) == currentTime.get(Calendar.DAY_OF_MONTH)) {

                            if ((taskTime.get(Calendar.HOUR_OF_DAY) == currentTime.get(Calendar.HOUR_OF_DAY)
                                    && (taskTime.get(Calendar.MINUTE) - currentTime.get(Calendar.MINUTE) == 20))
                                    || (taskTime.get(Calendar.HOUR_OF_DAY) - currentTime.get(Calendar.HOUR_OF_DAY) == 1
                                    && (taskTime.get(Calendar.MINUTE) + (60 - currentTime.get(Calendar.MINUTE)) == 20))) {
                                String time = new String();
                                time += (date[3] < 10) ? "0"+date[3] : date[3];
                                time += ":";
                                time += (date[4] < 10) ? "0"+date[4] : date[4];
                                builder.setContentText("Task "+taskList[i].getTaskName()
                                +" elapses in 15 minutes"+" ["+time+"]");

                                notificationManager.notify(i, builder.build());
                            }
                        }
                    }
                }
                try {
                    Thread.sleep(PERIOD); //milliseconds
                } catch (InterruptedException e) {
                    // interrupted finish thread
                    break;
                }
            }
        }
    }



}
