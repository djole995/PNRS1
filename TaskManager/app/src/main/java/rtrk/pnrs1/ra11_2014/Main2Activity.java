package rtrk.pnrs1.ra11_2014;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    int attrSetCnt = 0;
    int attrNumber = 4;
    final Button addTaskBtn = (Button) findViewById(R.id.btnAdd);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button btnPriority1 = (Button) findViewById(R.id.btnRed);
        final Button btnPriority2 = (Button) findViewById(R.id.btnYellow);
        final Button btnPriority3 = (Button) findViewById(R.id.btnGreen);

        final Button cancelBtn = (Button) findViewById(R.id.btnCancel);

        final Intent in = new Intent(Main2Activity.this, MainActivity.class);

        btnPriority1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(++attrSetCnt == attrNumber)
                {
                    addTaskBtn.setEnabled(true);
                }
                btnPriority2.setEnabled(false);
                btnPriority3.setEnabled(false);
            }
        });

        btnPriority2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(++attrSetCnt == attrNumber)
                {
                    addTaskBtn.setEnabled(true);
                }
                btnPriority1.setEnabled(false);
                btnPriority3.setEnabled(false);
            }
        });

        btnPriority3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(++attrSetCnt == attrNumber)
                {
                    addTaskBtn.setEnabled(true);
                }
                btnPriority2.setEnabled(false);
                btnPriority3.setEnabled(false);
            }
        });

        addTaskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in);
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in);
            }
        });

    }

    public void onClickEditText(EditText editText) {
       // editText.setEnabled(true);
        editText.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
    }

    public void onClickConfirmButton(Button btnConfirm) {
        EditText attrText = (EditText) findViewById(R.id.TaskName);
        if(++attrSetCnt == attrNumber) {
            addTaskBtn.setEnabled(true);
        }

        btnConfirm.setEnabled(false);

        attrText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
    }
}
