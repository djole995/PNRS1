package rtrk.pnrs1.ra11_2014;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    protected static CustomAdapter customAdapter;
    protected ArrayList<ListItem> taskList;
    protected static ListView listView;

    String pattern;
    SimpleDateFormat simpleDateFormat;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public static CustomAdapter getCustomAdapter() {
        return customAdapter;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pattern = "dd/MM/yyyy";
        simpleDateFormat = new SimpleDateFormat(pattern);

        listView = (ListView) findViewById(R.id.listView);
        customAdapter = new CustomAdapter(this.getApplicationContext());

        listView.setAdapter(customAdapter);

       /* try {
            customAdapter.addTask(new ListItem("HIGH", "bbbb", simpleDateFormat.format(simpleDateFormat.parse("28/11/1995")), true, true));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/



        Button btnNewTask = (Button) findViewById(R.id.btnNewTask);
        Button btnStat = (Button) findViewById(R.id.btnStat);
        final Intent inNewTask = new Intent(MainActivity.this, Main2Activity.class);
        final Intent inStat = new Intent(MainActivity.this, Main3Activity.class);

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
                customAdapter.notifyDataSetChanged();
            }
        } catch (Exception ex) {
            Toast.makeText(MainActivity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }





    public static ListView getListView() {
        return listView;
    }
}
