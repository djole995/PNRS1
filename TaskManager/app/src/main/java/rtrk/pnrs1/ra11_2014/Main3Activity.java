package rtrk.pnrs1.ra11_2014;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatisticView statisticView = new StatisticView(getApplicationContext());
        setContentView(statisticView);
    }
}
