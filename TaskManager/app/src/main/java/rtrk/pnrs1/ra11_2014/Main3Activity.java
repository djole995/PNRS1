package rtrk.pnrs1.ra11_2014;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main3Activity extends AppCompatActivity {

    Button btnReturn;
    Intent inReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        inReturn = new Intent(Main3Activity.this, MainActivity.class);
        btnReturn = (Button) findViewById(R.id.btn_return);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(inReturn);
            }
        });
    }
}
