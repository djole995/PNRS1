package rtrk.pnrs1.ra11_2014;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button newTaskBtn = (Button) findViewById(R.id.btnNewTask);
        Button statBtn = (Button) findViewById(R.id.btnStat);
        final Intent in1 = new Intent(MainActivity.this, Main2Activity.class);
        final Intent in2 = new Intent(MainActivity.this, Main3Activity.class);

        newTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in1);
            }
        });

        statBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in2);
            }
        });
    }
}
