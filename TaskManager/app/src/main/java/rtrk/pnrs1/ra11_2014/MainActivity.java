package rtrk.pnrs1.ra11_2014;

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
        taskList = new ArrayList<ListItem>();
        taskList.add(new ListItem("P", "aaaa", "Sutra", true, false));
        taskList.add(new ListItem("P", "bbbb", "Danas", true, true));
        try {
            taskList.add(new ListItem("P", "bbbb", simpleDateFormat.format(simpleDateFormat.parse("28/11/1995")), true, true));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        customAdapter = new CustomAdapter(this.getApplicationContext(), R.layout.list_item, taskList);

        listView.setAdapter(customAdapter);

        Button btnNewTask = (Button) findViewById(R.id.btnNewTask);
        Button btnStat = (Button) findViewById(R.id.btnStat);
        final Intent inNewTask = new Intent(MainActivity.this, Main2Activity.class);
        final Intent inStat = new Intent(MainActivity.this, Main3Activity.class);

        btnNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inNewTask);
            }
        });

        btnStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inStat);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    public static ListView getListView() {
        return listView;
    }
}
