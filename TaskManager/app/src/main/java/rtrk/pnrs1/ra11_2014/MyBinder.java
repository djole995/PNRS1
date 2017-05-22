package rtrk.pnrs1.ra11_2014;

import android.app.NotificationManager;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Milan on 21.5.2017.
 */

public class MyBinder extends IMyBinder.Stub {

    protected NotificationManager notificationManager;
    protected NotificationCompat.Builder builder;

    public MyBinder(NotificationManager notificationManager, NotificationCompat.Builder builder) {
        this.notificationManager = notificationManager;
        this.builder = builder;
    }

    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

    }

    @Override
    public void addTaskNotify(String taskName) throws RemoteException {
        builder.setContentTitle("Task Manager").setContentText("Task "+taskName+" created.");
        notificationManager.notify(-3, builder.build());
    }

    @Override
    public void modifyTaskNotify(String taskName) throws RemoteException {
        builder.setContentTitle("Task Manager").setContentText("Task "+taskName+" modified.");
        notificationManager.notify(-2, builder.build());
    }

    @Override
    public void deleteTaskNotify(String taskName) throws RemoteException {
        builder.setContentTitle("Task Manager").setContentText("Task "+taskName+" deleted.");
        notificationManager.notify(-1, builder.build());
    }
}
