package rtrk.pnrs1.ra11_2014;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
    protected ArrayList<ListItem> taskList;
    protected static ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int date[] = {1, 2, 2018, 22, 30};

        listView = (ListView) findViewById(R.id.listView);
        customAdapter = new CustomAdapter(this.getApplicationContext());
        customAdapter.addTask(new ListItem(ListItem.TaskPriority.HIGH, "aaa", "aab", date, true, false));
        customAdapter.addTask(new ListItem(ListItem.TaskPriority.MEDIUM, "BBB", "aab", date, true, false));

        listView.setAdapter(customAdapter);

        Button btnNewTask = (Button) findViewById(R.id.btnNewTask);
        Button btnStat = (Button) findViewById(R.id.btnStat);
        final Intent inNewTask = new Intent(MainActivity.this, Main2Activity.class);
        final Intent inStat = new Intent(MainActivity.this, Main3Activity.class);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
               inNewTask.putExtra(getString(R.string.key_modify_task), (ListItem) parent.getItemAtPosition(position));

                startActivityForResult(inNewTask , REQUEST_CODE_MODIFY);
                return true;
            }
        });

        btnNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(inNewTask , REQUEST_CODE);
            }
        });

        btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inStat);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE  && resultCode  == RESULT_OK) {
                ListItem listItem = (ListItem) data.getSerializableExtra(getString(R.string.key_add_task));
                customAdapter.addTask(listItem);
            }
            else if(requestCode == REQUEST_CODE_MODIFY  && resultCode  == RESULT_OK) {

            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

}
