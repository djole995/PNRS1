package rtrk.pnrs1.ra11_2014;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Milan on 30.4.2017.
 */

public class StatisticView extends View {
    protected Paint paint;
    protected RectF highPBound;
    protected RectF mediumPBound;
    protected RectF lowPBound;

    protected float highPFinished = 0;
    protected float mediumPFinished = 0;
    protected float lowPFinished = 0;
    protected int animHighPCnt = 1;
    protected int animMediumPCnt = 1;
    protected int animLowPCnt = 1;
    protected float pieChartSize;
    protected float textSize;

    Stat stat;

    public StatisticView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        highPBound = new RectF();
        mediumPBound = new RectF();
        lowPBound = new RectF();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Point screenSize = new Point();
        wm.getDefaultDisplay().getSize(screenSize);
        int height = screenSize.y;
        int width = screenSize.x;
        stat = new Stat();

        /* Landscape */
        if(wm.getDefaultDisplay().getRotation() == Surface.ROTATION_90
                || wm.getDefaultDisplay().getRotation() == Surface.ROTATION_270) {
            pieChartSize = width/4.5f;

            highPBound.set(width/2-pieChartSize/2, height/2-pieChartSize ,width/2+pieChartSize/2, height/2);
            mediumPBound.set(width/2-3*pieChartSize/2-50, height/2-pieChartSize, width/2-pieChartSize/2-50, height/2);
            lowPBound.set(width/2+pieChartSize/2+50, height/2-pieChartSize, width/2+3*pieChartSize/2+50, height/2);

            textSize = width/40;
        }
        /* Portrait */
        else {
            pieChartSize = height/4.5f;

            highPBound.set(width/2-pieChartSize/2, height/2-pieChartSize-100 ,width/2+pieChartSize/2, height/2-100);
            mediumPBound.set(width/2-pieChartSize-25, height/2 ,width/2-25, height/2+pieChartSize);
            lowPBound.set(width/2+25, height/2 ,width/2+25+pieChartSize, height/2+pieChartSize);

            textSize = height/40;
        }

        int highPNum = 0;
        int mediumPNum = 0;
        int lowPNum = 0;

        for(int i = 0; i < MainActivity.customAdapter.taskList.size(); i++) {
            if(MainActivity.customAdapter.taskList.get(i).getTaskPriority() == ListItem.TaskPriority.HIGH) {
                highPNum++;
                if(MainActivity.customAdapter.taskList.get(i).getTaskFinished())
                    highPFinished++;
            }
            else if(MainActivity.customAdapter.taskList.get(i).getTaskPriority() == ListItem.TaskPriority.MEDIUM) {
                mediumPNum++;
                if(MainActivity.customAdapter.taskList.get(i).getTaskFinished())
                    mediumPFinished++;
            }
            else {
                lowPNum++;
                if(MainActivity.customAdapter.taskList.get(i).getTaskFinished())
                    lowPFinished++;
            }
        }

        highPFinished = stat.count(highPNum, highPFinished);
        mediumPFinished = stat.count(mediumPNum, mediumPFinished);
        lowPFinished = stat.count(lowPNum, lowPFinished);
        /*highPFinished = (highPFinished == 0) ? 0 : highPFinished/highPNum*100;
        mediumPFinished = (mediumPFinished == 0) ? 0 : mediumPFinished/mediumPNum*100;
        lowPFinished = (lowPFinished == 0) ? 0 : lowPFinished/lowPNum*100;*/


        String params[] = new String[3];
        new AnimationThread().execute(params);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /* Drawing High priority tasks chart */
        paint.setTextSize(textSize);
        canvas.drawText(getContext().getText(R.string.high_priority).toString(), highPBound.left, highPBound.bottom+textSize, paint);
        paint.setARGB(255, 255, 100, 100);
        canvas.drawArc(highPBound, 270, animHighPCnt, true, paint);
        paint.setARGB(255, 0, 200, 255);
        canvas.drawArc(highPBound, animHighPCnt - 90, 360 - animHighPCnt, true, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40.0f);
        canvas.drawText(Integer.toString((int)(((float)animHighPCnt/360)*100)) + "%",
                highPBound.centerX() - 30, highPBound.centerY() + 10, paint);

        /* Drawing Medium priority tasks chart */
        paint.setTextSize(textSize);
        canvas.drawText(getContext().getText(R.string.medium_priority).toString(),
                mediumPBound.left-30, mediumPBound.bottom+textSize, paint);
        paint.setARGB(255, 0, 255, 0);
        canvas.drawArc(mediumPBound, 270, animMediumPCnt, true, paint);
        paint.setARGB(255, 0, 200, 255);
        canvas.drawArc(mediumPBound, animMediumPCnt - 90, 360 - animMediumPCnt, true, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40.0f);
        canvas.drawText(Integer.toString((int)(((float)animMediumPCnt/360)*100)) + "%",
                mediumPBound.centerX() - 30, mediumPBound.centerY() + 10, paint);

        /* Drawing Low priority tasks chart */
        paint.setTextSize(textSize);
        canvas.drawText(getContext().getText(R.string.low_priority).toString(),
                lowPBound.left, lowPBound.bottom+textSize, paint);
        paint.setARGB(255, 255, 255, 0);
        canvas.drawArc(lowPBound, 270, animLowPCnt, true, paint);
        paint.setARGB(255, 0, 200, 255);
        canvas.drawArc(lowPBound, animLowPCnt - 90, 360 - animLowPCnt, true, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40.0f);
        canvas.drawText(Integer.toString((int)(((float)animLowPCnt/360)*100)) + "%",
                lowPBound.centerX() - 30, lowPBound.centerY() + 10, paint);
    }

    private class AnimationThread extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            boolean animFinished = false;
            float highPDegree = ((float)highPFinished/100)*360;
            float mediumPDegree = ((float)mediumPFinished/100)*360;
            float lowPDegree = ((float)lowPFinished/100)*360;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while(animFinished != true) {

                animFinished = true;

                if(animHighPCnt < highPDegree) {
                    animHighPCnt += 1;
                    animFinished = false;
                }

                if(animMediumPCnt < mediumPDegree) {
                    animMediumPCnt += 1;
                    animFinished = false;
                }

                if(animLowPCnt < lowPDegree) {
                    animLowPCnt += 1;
                    animFinished = false;
                }

                if(!animFinished) {
                    postInvalidate();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            return (String)null;
        }
    }
}
