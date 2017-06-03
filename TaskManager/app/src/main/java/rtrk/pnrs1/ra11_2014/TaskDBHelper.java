package rtrk.pnrs1.ra11_2014;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.RenderScript;

/**
 * Created by Milan on 2.6.2017.
 */

public class TaskDBHelper extends SQLiteOpenHelper {

    final static String databaseName = "Tasks";
    final static int version = 10;

    public TaskDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, databaseName, factory, version);
    }

    public TaskDBHelper(Context context) {
        super(context, databaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+databaseName+"(TaskName TEXT, TaskDay INTEGER, TaskMonth INTEGER, TaskYear INTEGER, TaskHour INTEGER, TaskMinute INTEGER, TaskReminder INTEGER, TaskFinished INTEGER, TaskPriority INTEGER, TaskDesc TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertTask(ListItem task) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("TaskName", task.getTaskName());
        int[] taskDate = task.getTaskDate();
        values.put("TaskDay", taskDate[0]);
        values.put("TaskMonth", taskDate[1]);
        values.put("TaskYear", taskDate[2]);
        values.put("TaskHour", taskDate[3]);
        values.put("TaskMinute", taskDate[4]);
        values.put("TaskFinished", (task.getTaskFinished() == true) ? 1 : 0 );
        values.put("TaskReminder", (task.getTaskReminder() == true) ? 1 : 0);
        values.put("TaskDesc", task.getTaskDescription());

        if(task.getTaskPriority() == ListItem.TaskPriority.LOW)
            values.put("TaskPriority", 0);
        else if(task.getTaskPriority() == ListItem.TaskPriority.MEDIUM)
            values.put("TaskPriority", 1);
        else
            values.put("TaskPriority", 2);

        db.insert(databaseName, null, values);

        db.close();

    }

    public ListItem createTask(Cursor curs) {
        String taskName = curs.getString(curs.getColumnIndex("TaskName"));
        String taskDesc = curs.getString(curs.getColumnIndex("TaskDesc"));
        int taskDay = curs.getInt(curs.getColumnIndex("TaskDay"));
        int taskMonth = curs.getInt(curs.getColumnIndex("TaskMonth"));
        int taskYear = curs.getInt(curs.getColumnIndex("TaskYear"));
        int taskHour = curs.getInt(curs.getColumnIndex("TaskHour"));
        int taskMinute = curs.getInt(curs.getColumnIndex("TaskMinute"));
        int taskFinished = curs.getInt(curs.getColumnIndex("TaskFinished"));
        int taskReminder = curs.getInt(curs.getColumnIndex("TaskReminder"));
        int taskPriority = curs.getInt(curs.getColumnIndex("TaskPriority"));

        int taskDate[] = new int[5];
        taskDate[0] = taskDay;
        taskDate[1] = taskMonth;
        taskDate[2] = taskYear;
        taskDate[3] = taskHour;
        taskDate[4] = taskMinute;

        ListItem retVal;
        if(taskPriority == 0)
            retVal = new ListItem(ListItem.TaskPriority.LOW, taskName, taskDesc, taskDate,
                    (taskReminder == 1) ? true : false, (taskFinished == 1) ? true : false);
        else if(taskPriority == 1)
            retVal = new ListItem(ListItem.TaskPriority.MEDIUM, taskName, taskDesc, taskDate,
                    (taskReminder == 1) ? true : false, (taskFinished == 1) ? true : false);
        else
            retVal = new ListItem(ListItem.TaskPriority.HIGH, taskName, taskDesc, taskDate,
                    (taskReminder == 1) ? true : false, (taskFinished == 1) ? true : false);

        return retVal;
    }

    public ListItem[] readTasks() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cur = db.query(databaseName, null, null, null, null, null, null, null);
        ListItem[] tasks =  new ListItem[cur.getCount()];
        int i = 0;

        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
            tasks[i] = createTask(cur);
            i++;
        }

        return tasks;
    }

    public void deleteTask(String taskName) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(databaseName, "TaskName"+"=?", new String[] {taskName});
        close();
    }

    public void updateTask(ListItem task, String taskName) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("TaskName", task.getTaskName());
        int[] taskDate = task.getTaskDate();
        values.put("TaskDay", taskDate[0]);
        values.put("TaskMonth", taskDate[1]);
        values.put("TaskYear", taskDate[2]);
        values.put("TaskHour", taskDate[3]);
        values.put("TaskMinute", taskDate[4]);
        values.put("TaskFinished", (task.getTaskFinished() == true) ? 1 : 0 );
        values.put("TaskReminder", (task.getTaskReminder() == true) ? 1 : 0);
        values.put("TaskDesc", task.getTaskDescription());

        if(task.getTaskPriority() == ListItem.TaskPriority.LOW)
            values.put("TaskPriority", 0);
        else if(task.getTaskPriority() == ListItem.TaskPriority.MEDIUM)
            values.put("TaskPriority", 1);
        else
            values.put("TaskPriority", 2);

        db.update(databaseName, values, "TaskName"+"=?", new String[] {taskName});

        db.close();
    }
}
