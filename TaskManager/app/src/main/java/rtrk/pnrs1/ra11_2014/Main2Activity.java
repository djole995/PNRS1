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
    protected EditText txtTaskDescription;
    protected EditText txtDay;
    protected EditText txtMonth;
    protected EditText txtYear;
    protected EditText txtHours;
    protected EditText txtMinutes;
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
        txtTaskName = (EditText) findViewById(R.id.taskName);
        txtTaskDescription = (EditText) findViewById(R.id.description);
        txtDay = (EditText) findViewById(R.id.day);
        txtMonth = (EditText) findViewById(R.id.month);
        txtYear = (EditText) findViewById(R.id.year);
        txtHours = (EditText) findViewById(R.id.hour);
        txtMinutes = (EditText) findViewById(R.id.minute);
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
                    if(txtTaskName.getText().toString().isEmpty()) {
                        return;
                    }
                    if(++attrSetCnt == attrNumber) {
                        btnAddTask.setEnabled(true);
                    }
                    btnConfirmTaskName.setText(R.string.modify);
                    txtTaskName.setEnabled(false);
                }
                else {
                    attrSetCnt--;
                    btnAddTask.setEnabled(false);
                    btnConfirmTaskName.setText(R.string.confirm);
                    txtTaskName.setEnabled(true);
                }
            }
        });

        btnConfirmTaskTime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(btnConfirmTaskTime.getText().equals(getString(R.string.confirm))) {
                    if(!checkDateAndTime()) {
                        return;
                    }

                    if(++attrSetCnt == attrNumber) {
                        btnAddTask.setEnabled(true);
                    }
                    btnConfirmTaskTime.setText(R.string.modify);

                    txtDay.setEnabled(false);
                    txtMonth.setEnabled(false);
                    txtYear.setEnabled(false);
                    txtHours.setEnabled(false);
                    txtMinutes.setEnabled(false);
                }
                else {
                    attrSetCnt--;
                    btnAddTask.setEnabled(false);
                    btnConfirmTaskTime.setText(R.string.confirm);

                    txtDay.setEnabled(true);
                    txtMonth.setEnabled(true);
                    txtYear.setEnabled(true);
                    txtHours.setEnabled(true);
                    txtMinutes.setEnabled(true);
                }

            }
        });

        btnConfirmTaskDesc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(btnConfirmTaskDesc.getText().equals(getString(R.string.confirm))) {
                    if(txtTaskDescription.getText().toString().isEmpty()) {
                        return;
                    }

                    if(++attrSetCnt == attrNumber) {
                        btnAddTask.setEnabled(true);
                    }
                    btnConfirmTaskDesc.setText(R.string.modify);
                    txtTaskDescription.setEnabled(false);
                }
                else {
                    attrSetCnt--;
                    btnAddTask.setEnabled(false);
                    btnConfirmTaskDesc.setText(R.string.confirm);
                    txtTaskDescription.setEnabled(true);
                }

            }
        });

    }

    protected boolean checkDateAndTime() {
        String s1 = txtDay.getText().toString();
        String s2 = txtMonth.getText().toString();
        String s3 = txtYear.getText().toString();
        String s4 = txtHours.getText().toString();
        String s5 = txtMinutes.getText().toString();

        if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty()) {
            return false;
        }

        int day = Integer.parseInt(s1);
        int month = Integer.parseInt(s2);
        int year = Integer.parseInt(s3);
        int hours = Integer.parseInt(s4);
        int minutes = Integer.parseInt(s5);

        /* Checking day and month */
        if(day <= 0 || month <= 0) {
            return false;
        }
        else if(month > 12) {
            return false;
        }
        else if(month == 2) {
            if(day > 28 && (year % 4) != 0) {
                return false;
            }
            else if(day > 29 && (year % 4) == 0) {
                return false;
            }
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11) {
            if(day > 30) {
                return false;
            }
        }
        else {
            if(day > 31) {
                return false;
            }
        }

        /* Checking year */
        if(year < 2017) {
            return false;
        }
        else if(year == 2017) {
            if(month < 3) {
                return false;
            }
            else if(month == 3 && day < 30) {
                return false;
            }
        }

        /* Checking time */
        if(hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            return false;
        }

        return true;
    }

}
