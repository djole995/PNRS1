package rtrk.pnrs1.ra11_2014;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static rtrk.pnrs1.ra11_2014.R.drawable.custom_border;

public class Main2Activity extends AppCompatActivity {

    protected int attrSetCnt;
    protected int attrNumber;
    protected boolean prioritySelected;
    protected Button addTaskBtn;
    protected Button btnPriority1;
    protected Button btnPriority2;
    protected Button btnPriority3;
    protected Button cancelBtn;
    protected Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addTaskBtn = (Button) findViewById(R.id.btnAdd);
        prioritySelected = false;
        attrSetCnt = 0;
        attrNumber = 4;
        btnPriority1 = (Button) findViewById(R.id.btnRed);
        btnPriority2 = (Button) findViewById(R.id.btnYellow);
        btnPriority3 = (Button) findViewById(R.id.btnGreen);
        cancelBtn = (Button) findViewById(R.id.btnCancel);
        in = new Intent(Main2Activity.this, MainActivity.class);

        btnPriority1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!prioritySelected) {
                    if(++attrSetCnt == attrNumber) {
                        addTaskBtn.setEnabled(true);
                    }
                    btnPriority2.setEnabled(false);
                    btnPriority2.setAlpha(0.2f);
                    btnPriority3.setEnabled(false);
                    btnPriority3.setAlpha(0.2f);
                    prioritySelected = true;
                }
                else {
                    --attrSetCnt;
                    addTaskBtn.setEnabled(false);
                    btnPriority2.setEnabled(true);
                    btnPriority2.setAlpha(1.0f);
                    btnPriority3.setEnabled(true);
                    btnPriority3.setAlpha(1.0f);
                    prioritySelected = false;
                }
            }
        });

        btnPriority2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!prioritySelected) {
                    if(++attrSetCnt == attrNumber) {
                        addTaskBtn.setEnabled(true);
                    }
                    btnPriority1.setEnabled(false);
                    btnPriority1.setAlpha(0.2f);
                    btnPriority3.setEnabled(false);
                    btnPriority3.setAlpha(0.2f);
                    prioritySelected = true;
                }
                else {
                    --attrSetCnt;
                    addTaskBtn.setEnabled(false);
                    btnPriority1.setEnabled(true);
                    btnPriority1.setAlpha(1.0f);
                    btnPriority3.setEnabled(true);
                    btnPriority3.setAlpha(1.0f);
                    prioritySelected = false;
                }
            }
        });

        btnPriority3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!prioritySelected) {
                    if(++attrSetCnt == attrNumber) {
                        addTaskBtn.setEnabled(true);
                    }
                    btnPriority1.setEnabled(false);
                    btnPriority1.setAlpha(0.2f);
                    btnPriority2.setEnabled(false);
                    btnPriority2.setAlpha(0.2f);
                    prioritySelected = true;
                }
                else {
                    --attrSetCnt;
                    addTaskBtn.setEnabled(false);
                    btnPriority1.setEnabled(true);
                    btnPriority1.setAlpha(1.0f);
                    btnPriority2.setEnabled(true);
                    btnPriority2.setAlpha(1.0f);
                    prioritySelected = false;
                }
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
