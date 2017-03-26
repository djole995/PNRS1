package rtrk.pnrs1.ra11_2014;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    protected int attrSetCnt;
    protected int attrNumber;
    protected boolean prioritySelected;
    protected Button btnAddTask;
    protected Button btnPriority1;
    protected Button btnPriority2;
    protected Button btnPriority3;
    protected Button btnCancel;
    protected Button btnConfirmTaskName;
    protected Button btnConfirmTaskTime;
    protected Button btnConfirmTaskDesc;
    protected EditText txtTaskName;

    protected Layout taskTime;
    protected EditText txtTaskDescription;
    protected Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnAddTask = (Button) findViewById(R.id.btnAdd);
        prioritySelected = false;
        attrSetCnt = 0;
        attrNumber = 4;
        btnConfirmTaskName = (Button) findViewById((R.id.btnConfirm1));
        btnConfirmTaskTime = (Button) findViewById((R.id.btnConfirm2));
        btnConfirmTaskDesc = (Button) findViewById((R.id.btnConfirm3));
        btnPriority1 = (Button) findViewById(R.id.btnRed);
        btnPriority2 = (Button) findViewById(R.id.btnYellow);
        btnPriority3 = (Button) findViewById(R.id.btnGreen);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        txtTaskName = (EditText) findViewById(R.id.day);
        in = new Intent(Main2Activity.this, MainActivity.class);

        btnPriority1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!prioritySelected) {
                    if(++attrSetCnt == attrNumber) {
                        btnAddTask.setEnabled(true);
                    }
                    btnPriority2.setEnabled(false);
                    btnPriority2.setAlpha(0.2f);
                    btnPriority3.setEnabled(false);
                    btnPriority3.setAlpha(0.2f);
                    prioritySelected = true;
                }
                else {
                    --attrSetCnt;
                    btnAddTask.setEnabled(false);
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
                        btnAddTask.setEnabled(true);
                    }
                    btnPriority1.setEnabled(false);
                    btnPriority1.setAlpha(0.2f);
                    btnPriority3.setEnabled(false);
                    btnPriority3.setAlpha(0.2f);
                    prioritySelected = true;
                }
                else {
                    --attrSetCnt;
                    btnAddTask.setEnabled(false);
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
                        btnAddTask.setEnabled(true);
                    }
                    btnPriority1.setEnabled(false);
                    btnPriority1.setAlpha(0.2f);
                    btnPriority2.setEnabled(false);
                    btnPriority2.setAlpha(0.2f);
                    prioritySelected = true;
                }
                else {
                    --attrSetCnt;
                    btnAddTask.setEnabled(false);
                    btnPriority1.setEnabled(true);
                    btnPriority1.setAlpha(1.0f);
                    btnPriority2.setEnabled(true);
                    btnPriority2.setAlpha(1.0f);
                    prioritySelected = false;
                }
            }
        });

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in);
            }
        });

        btnConfirmTaskName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(btnConfirmTaskName.getText().equals(getString(R.string.confirm))) {
                    if(++attrSetCnt == attrNumber) {
                        btnAddTask.setEnabled(true);
                    }
                    btnConfirmTaskName.setText(R.string.change);
                }
                else {
                    attrSetCnt--;
                    btnAddTask.setEnabled(false);
                    btnConfirmTaskName.setText(R.string.confirm);
                }
            }
        });

        btnConfirmTaskTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(btnConfirmTaskTime.getText().equals(getString(R.string.confirm))) {
                    if(++attrSetCnt == attrNumber) {
                        btnAddTask.setEnabled(true);
                    }
                    btnConfirmTaskTime.setText(R.string.change);
                }
                else {
                    attrSetCnt--;
                    btnAddTask.setEnabled(false);
                    btnConfirmTaskTime.setText(R.string.confirm);
                }

            }
        });

        btnConfirmTaskDesc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(btnConfirmTaskDesc.getText().equals(getString(R.string.confirm))) {
                    if(++attrSetCnt == attrNumber) {
                        btnAddTask.setEnabled(true);
                    }
                    btnConfirmTaskDesc.setText(R.string.change);
                }
                else {
                    attrSetCnt--;
                    btnAddTask.setEnabled(false);
                    btnConfirmTaskDesc.setText(R.string.confirm);
                }

            }
        });

    }

}
