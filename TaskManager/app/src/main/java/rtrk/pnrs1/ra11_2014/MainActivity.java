package rtrk.pnrs1.ra11_2014;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

/*        Button btnNewTask = (Button) findViewById(R.id.btnNewTask);
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
        });*/
    }
}
