package rtrk.pnrs1.ra11_2014;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Milan on 8.4.2017.
 */

public class CustomAdapter extends ArrayAdapter<ListItem> {

    private static class ViewHolder {
        TextView taskName;
        TextView taskDate;
        TextView taskPriority;
        CheckBox taskFinished;
        RadioButton taskReminder;
    }

    public CustomAdapter(Context context, int resource, ArrayList<ListItem> objects) {
        super(context, resource, objects);

    }

    @Override
    public void add(ListItem object) {
        super.add(object);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItem listItem = getItem(position);
        ViewHolder viewHolder = new ViewHolder();


        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item, parent, false);
            viewHolder.taskName = (TextView) convertView.findViewById(R.id.item_task_name);
            viewHolder.taskPriority = (TextView) convertView.findViewById(R.id.item_task_priority);
            viewHolder.taskDate = (TextView) convertView.findViewById(R.id.item_task_date);
            viewHolder.taskFinished = (CheckBox) convertView.findViewById(R.id.item_task_finished);
            viewHolder.taskReminder = (RadioButton) convertView.findViewById(R.id.item_task_reminder);

            convertView.setTag(viewHolder);
        } else {
                viewHolder = (ViewHolder) convertView.getTag();
        }


            viewHolder.taskName.setText(listItem.getTaskName());
            viewHolder.taskPriority.setText(listItem.getTaskPriority());
            viewHolder.taskDate.setText(listItem.getTaskDate());
            viewHolder.taskReminder.setChecked(listItem.getTaskFinished());
            viewHolder.taskFinished.setChecked(listItem.getTaskReminder());


        return convertView;
    }
}
