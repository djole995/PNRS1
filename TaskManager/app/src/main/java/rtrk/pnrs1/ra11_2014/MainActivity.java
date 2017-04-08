package rtrk.pnrs1.ra11_2014;

import android.app.LauncherActivity;
import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected CustomAdapter customAdapter;
    protected ArrayList<ListItem> taskList;
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        taskList = new ArrayList<ListItem>();
        taskList.add(new ListItem("P", "aaaa", "Sutra", true, false));
        taskList.add(new ListItem("P", "bbbb", "Danas", true, true));
        taskList.add(new ListItem("P", "bbbb", "Sutra", true, true));

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
    }
}
