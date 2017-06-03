package rtrk.pnrs1.ra11_2014;

import android.app.Activity;
import android.app.LauncherActivity;
import android.app.NotificationManager;
import android.content.ClipData;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Paint;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1;
    public static final int REQUEST_CODE_MODIFY = 2;
    protected static CustomAdapter customAdapter;
    protected static ListView listView;
    protected int modifyTaskIndex;
    protected CheckBox chBoxTaskFinished;
    protected Intent serviceIntent;

    private IMyBinder iMyBinder = new IMyBinder() {

        @Override
        public IBinder asBinder() {
            return null;
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void addTaskNotify(String taskName) throws RemoteException {

        }

        @Override
        public void modifyTaskNotify(String taskName) throws RemoteException {

        }

        @Override
        public void deleteTaskNotify(String taskName) throws RemoteException {

        }
    };
    protected ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int date[] = {1, 2, 2018, 22, 30};

        listView = (ListView) findViewById(R.id.listView);
        customAdapter = new CustomAdapter(this.getApplicationContext());

        listView.setAdapter(customAdapter);

        Button btnNewTask = (Button) findViewById(R.id.btnNewTask);
        Button btnStat = (Button) findViewById(R.id.btnStat);
        final Intent inNewTask = new Intent(MainActivity.this, Main2Activity.class);
        final Intent inStat = new Intent(MainActivity.this, Main3Activity.class);
        chBoxTaskFinished = (CheckBox) findViewById(R.id.item_task_finished);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                inNewTask.putExtra(getString(R.string.key_modify_task), (ListItem) parent.getItemAtPosition(position));
                modifyTaskIndex = position;
                startActivityForResult(inNewTask , REQUEST_CODE_MODIFY);
                return true;
            }
        });

        btnNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inNewTask.putExtra(getString(R.string.key_modify_task), (ListItem) null);
                startActivityForResult(inNewTask , REQUEST_CODE);
            }
        });

        btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inStat);
            }
        });

        serviceIntent = new Intent(MainActivity.this, MyService.class);

        serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                iMyBinder = IMyBinder.Stub.asInterface(service);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };

        bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {
                if(data.getSerializableExtra(getString(R.string.key_add_task)) != null) {
                    ListItem listItem = (ListItem) data.getSerializableExtra(getString(R.string.key_add_task));
                    customAdapter.addTask(listItem);
                    MyService.addTask(listItem);
                    iMyBinder.addTaskNotify(listItem.getTaskName());
                }
            }
            else if(requestCode == REQUEST_CODE_MODIFY  && resultCode  == RESULT_OK) {
                ListItem listItem = (ListItem) data.getSerializableExtra(getString(R.string.key_modify_task));

                if(listItem != null) {
                    ListItem oldListItem = (ListItem) customAdapter.getItem(modifyTaskIndex);
                    customAdapter.modifyTask(listItem, modifyTaskIndex);
                    MyService.modifyTask(listItem, oldListItem.getTaskName());
                    iMyBinder.modifyTaskNotify(listItem.getTaskName());

                }
                else {
                    MyService.deleteTask(customAdapter.taskList.get(modifyTaskIndex).getTaskName());
                    iMyBinder.deleteTaskNotify(customAdapter.taskList.get(modifyTaskIndex).getTaskName());
                    customAdapter.deleteTask(listItem, modifyTaskIndex);
                }
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void onDestroy() {
        super.onDestroy();

        unbindService(serviceConnection);
    }



}
