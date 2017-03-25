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
    Button addTaskBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addTaskBtn = (Button) findViewById(R.id.btnAdd);
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

    public  void onClickEditText(View view) {
        EditText editText = (EditText) findViewById(view.getId());

        editText.setEnabled(true);
    }

    public void onClickConfirmButton(View view) {
        Button btnConfirm = (Button) findViewById(view.getId());

        if(btnConfirm.getText().equals(getString(R.string.confirm))) {
            if(++attrSetCnt == attrNumber) {
                addTaskBtn.setEnabled(true);
            }
            btnConfirm.setText(R.string.change);
        }
        else {
            attrSetCnt--;
            addTaskBtn.setEnabled(false);
            btnConfirm.setText(R.string.confirm);
        }
    }

}
