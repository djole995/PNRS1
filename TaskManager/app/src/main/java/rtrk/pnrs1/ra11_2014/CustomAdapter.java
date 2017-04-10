package rtrk.pnrs1.ra11_2014;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

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
        ViewHolder viewHolder = new ViewHolder();


        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.taskName = (TextView) convertView.findViewById(R.id.item_task_name);
            viewHolder.taskPriority = (View) convertView.findViewById(R.id.item_task_priority);
            viewHolder.taskDate = (TextView) convertView.findViewById(R.id.item_task_date);
            viewHolder.taskFinished = (CheckBox) convertView.findViewById(R.id.item_task_finished);
            viewHolder.taskReminder = (RadioButton) convertView.findViewById(R.id.item_task_reminder);

            convertView.setTag(viewHolder);
        } else {
                viewHolder = (ViewHolder) convertView.getTag();
        }


            viewHolder.taskName.setText(listItem.getTaskName());
            String priority = listItem.getTaskName();
            if(priority == "HIGH") {
                viewHolder.taskPriority.setBackgroundColor(Color.RED);
            }
            else if(priority == "MEDIUM") {
                viewHolder.taskPriority.setBackgroundColor(Color.YELLOW);
            }
            else {
                viewHolder.taskPriority.setBackgroundColor(Color.GREEN);
            }

        viewHolder.taskDate.setText(listItem.getTaskDate());
            viewHolder.taskReminder.setChecked(listItem.getTaskFinished());
            viewHolder.taskFinished.setChecked(listItem.getTaskReminder());


        return convertView;
    }



    private static class ViewHolder {
        TextView taskName;
        TextView taskDate;
        View taskPriority;
        CheckBox taskFinished;
        RadioButton taskReminder;
    }
}
