package rtrk.pnrs1.ra11_2014;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Milan on 8.4.2017.
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ListItem> taskList;


    public CustomAdapter(Context context) {
        this.context = context;
        this.taskList = new ArrayList<ListItem>();
    }


    public void addTask(ListItem item) {
        taskList.add(item);
        notifyDataSetChanged();
    }

    public void modifyTask(ListItem listItem, int position) {
        taskList.set(position, listItem);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        Object rv = null;
        try {
            rv = taskList.get(position);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        return rv;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem listItem = (ListItem) getItem(position);
        final ViewHolder viewHolder;


        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.taskName = (TextView) convertView.findViewById(R.id.item_task_name);
            viewHolder.taskPriority = convertView.findViewById(R.id.item_task_priority);
            viewHolder.taskDate = (TextView) convertView.findViewById(R.id.item_task_date);
            viewHolder.taskFinished = (CheckBox) convertView.findViewById(R.id.item_task_finished);
            viewHolder.taskReminder = (RadioButton) convertView.findViewById(R.id.item_task_reminder);

            convertView.setTag(viewHolder);
        } else {
                viewHolder = (ViewHolder) convertView.getTag();
        }


        viewHolder.taskName.setText(listItem.getTaskName());
        ListItem.TaskPriority priority = listItem.getTaskPriority();
        if(priority == ListItem.TaskPriority.HIGH) {
            viewHolder.taskPriority.setBackgroundColor(Color.RED);
        }
        else if(priority == ListItem.TaskPriority.MEDIUM) {
            viewHolder.taskPriority.setBackgroundColor(Color.YELLOW);
        }
        else {
            viewHolder.taskPriority.setBackgroundColor(Color.GREEN);
        }

        int date[] = listItem.getTaskDate();
        String strDate = getFormatedDate(date);

        viewHolder.taskDate.setText(strDate);
        if(listItem.getTaskReminder()) {
            viewHolder.taskReminder.setVisibility(View.VISIBLE);
            viewHolder.taskReminder.setChecked(listItem.getTaskReminder());
        }
        else {
            viewHolder.taskReminder.setVisibility(View.INVISIBLE);
        }
        viewHolder.taskFinished.setChecked(listItem.getTaskFinished());

        viewHolder.taskFinished.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    viewHolder.taskName.setPaintFlags(viewHolder.taskName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else {
                    viewHolder.taskName.setPaintFlags(viewHolder.taskName.getPaintFlags() & ~(Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });


        return convertView;
    }

    String getFormatedDate(int date[]) {
        String hours;
        String minutes;
        String day = Integer.toString(date[0]);
        String month = Integer.toString(date[1]);
        String year = Integer.toString(date[2]);

       // int daysInMonths[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String daysOfWeek[] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
                "Saturday"};

        String dateStr;

        Calendar currentDate = Calendar.getInstance();
        Calendar taskDate = Calendar.getInstance();

        taskDate.set(date[2], date[1]-1, date[0]);

        if(taskDate.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR)) {
            int difference = taskDate.get(Calendar.DAY_OF_YEAR) - currentDate.get(Calendar.DAY_OF_YEAR);
            if (difference < 7) {
                if (difference == 0) {
                    dateStr = "Today";
                }
                else if (difference == 1) {
                    dateStr = "Tomorrow";
                }
                else {
                    int dayofWeek = taskDate.get(Calendar.DAY_OF_WEEK);
                    taskDate.get(Calendar.DAY_OF_WEEK);
                    dateStr = daysOfWeek[dayofWeek-1];
                }
            }
            else {
                dateStr = Integer.toString(date[0]) + "/" + Integer.toString(date[1]) +
                        "/" + Integer.toString(date[2]);
            }
        }
        else {
            dateStr = Integer.toString(date[0]) + "/" + Integer.toString(date[1]) +
                    "/" + Integer.toString(date[2]);
        }


        if(date[3] > 9) {
            hours = Integer.toString(date[3]);
        }
        else {
            hours = "0"+Integer.toString(date[3]);
        }

        if(date[4] > 9) {
            minutes = Integer.toString(date[4]);
        }
        else {
            minutes = "0"+Integer.toString(date[4]);
        }




        String ret = dateStr +"\n"+hours+":"+minutes;

        return ret;
    }



    private static class ViewHolder {
        TextView taskName;
        TextView taskDate;
        View taskPriority;
        CheckBox taskFinished;
        RadioButton taskReminder;
    }
}
