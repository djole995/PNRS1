package rtrk.pnrs1.ra11_2014;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

public class Main2Activity extends AppCompatActivity {

    protected int attrSetCnt;
    protected int attrNumber = 4;
    protected int day;
    protected int month;
    protected int year;
    protected int hours;
    protected int minutes;
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
    protected CheckBox chBoxReminder;
    protected Intent in;
    ListItem listItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btnAddTask = (Button) findViewById(R.id.btnAdd);
        prioritySelected = false;
        attrSetCnt = 0;
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
        chBoxReminder = (CheckBox) findViewById(R.id.checkBoxReminder);
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
                ListItem.TaskPriority priority;
                int date[] = {day, month, year, hours, minutes};

                if(btnPriority1.isEnabled()) {
                    priority = ListItem.TaskPriority.HIGH;
                }
                else if(btnPriority2.isEnabled()) {
                    priority = ListItem.TaskPriority.MEDIUM;
                }
                else {
                    priority = ListItem.TaskPriority.LOW;
                }



                Intent intent = getIntent();

                if(intent.getSerializableExtra(getString(R.string.key_modify_task)) != null) {
                    ListItem listItem = (ListItem) intent.getSerializableExtra(getString(R.string.key_modify_task));
                    listItem = new ListItem(priority, txtTaskName.getText().toString(),
                            txtTaskDescription.getText().toString(), date, chBoxReminder.isChecked(), false);
                }
                else {
                    intent.putExtra(getString(R.string.key_add_task), new ListItem(priority, txtTaskName.getText().toString(),
                            txtTaskDescription.getText().toString(), date, chBoxReminder.isChecked(), false));
                }
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        btnConfirmTaskName.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(btnConfirmTaskName.getText().equals(getString(R.string.confirm))) {
                    if(txtTaskName.getText().toString().isEmpty()) {
                        Toast badInputNotify = Toast.makeText(getApplicationContext(),
                                R.string.bad_task_name_input, Toast.LENGTH_SHORT);
                        badInputNotify.setGravity(Gravity.BOTTOM, 0, 20);
                        badInputNotify.show();
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
                        Toast badInputNotify = Toast.makeText(getApplicationContext(),
                                R.string.bad_task_time_input, Toast.LENGTH_SHORT);
                        badInputNotify.setGravity(Gravity.BOTTOM, 0, 20);
                        badInputNotify.show();
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
                        Toast badInputNotify = Toast.makeText(getApplicationContext(),
                                R.string.bad_task_desc_input, Toast.LENGTH_SHORT);
                        badInputNotify.setGravity(Gravity.BOTTOM, 0, 20);
                        badInputNotify.show();
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

        if(getIntent().getSerializableExtra(getString(R.string.key_modify_task)) != null) {
            listItem = (ListItem) getIntent().getSerializableExtra(getString(R.string.key_modify_task));

            txtTaskName.setText(listItem.getTaskName());
            btnConfirmTaskName.callOnClick();

            ListItem.TaskPriority taskPriority = listItem.getTaskPriority();
            if(taskPriority == ListItem.TaskPriority.HIGH) {
                btnPriority1.callOnClick();
            }
            else if(taskPriority == ListItem.TaskPriority.MEDIUM) {
                btnPriority2.callOnClick();
            }
            else {
                btnPriority3.callOnClick();
            }

            txtTaskDescription.setText(listItem.getTaskDescription());
            btnConfirmTaskDesc.callOnClick();


            int date[] = listItem.getTaskDate();

            txtDay.setText(Integer.toString(date[0]));
            txtMonth.setText(Integer.toString(date[1]));
            txtYear.setText(Integer.toString(date[2]));
            txtHours.setText(Integer.toString(date[3]));
            txtMinutes.setText(Integer.toString(date[4]));
            btnConfirmTaskTime.callOnClick();

            chBoxReminder.setChecked(listItem.getTaskReminder());

            btnAddTask.setText(R.string.confirm);
        }

    }

/*    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == MainActivity.REQUEST_CODE_MODIFY  && resultCode  == RESULT_OK) {
                ListItem listItem = (ListItem) data.getSerializableExtra(getString(R.string.key_add_task));

                attrSetCnt = 4;

                txtTaskName.setText(listItem.getTaskName());
                txtTaskName.setEnabled(false);
                btnConfirmTaskName.setText(R.string.modify);

            }
        } catch (Exception ex) {
            Toast.makeText(Main2Activity.this, ex.toString(),
                    Toast.LENGTH_SHORT).show();
        }

    }*/

    protected boolean checkDateAndTime() {
        String s1 = txtDay.getText().toString();
        String s2 = txtMonth.getText().toString();
        String s3 = txtYear.getText().toString();
        String s4 = txtHours.getText().toString();
        String s5 = txtMinutes.getText().toString();

        if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty()) {
            return false;
        }

        day = Integer.parseInt(s1);
        month = Integer.parseInt(s2);
        year = Integer.parseInt(s3);
        hours = Integer.parseInt(s4);
        minutes = Integer.parseInt(s5);

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
