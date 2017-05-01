package rtrk.pnrs1.ra11_2014;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by Milan on 30.4.2017.
 */

public class StatisticView extends View {
    protected Paint paint;
    protected RectF highPBound;
    protected RectF mediumPBound;
    protected RectF lowPBound;

    protected int highPFinished = 68;
    protected int mediumPFinished = 90;
    protected int lowPFinished = 30;
    protected int animHighPCnt = 1;
    protected int animMediumPCnt = 1;
    protected int animLowPCnt = 1;
    protected int pieChartSize = 180;

    public StatisticView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        highPBound = new RectF();
        mediumPBound = new RectF();
        lowPBound = new RectF();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();
        float highPDegree = ((float)highPFinished/100)*360;
        float mediumPDegree = ((float)mediumPFinished/100)*360;
        float lowPDegree = ((float)lowPFinished/100)*360;
        boolean animFinished = true;

        highPBound.set(width/2-pieChartSize/2, height/2-pieChartSize-100 ,width/2+pieChartSize/2, height/2-100);
        mediumPBound.set(width/2-pieChartSize-25, height/2 ,width/2-25, height/2+pieChartSize);
        lowPBound.set(width/2+25, height/2 ,width/2+25+pieChartSize, height/2+pieChartSize);

        /* Drawing High priority tasks chart */
        paint.setTextSize(25.0f);
        canvas.drawText(getContext().getText(R.string.high_priority).toString(), highPBound.left, highPBound.bottom+20, paint);
        paint.setARGB(255, 255, 100, 100);
        canvas.drawArc(highPBound, 270, animHighPCnt, true, paint);
        paint.setARGB(255, 0, 200, 255);
        canvas.drawArc(highPBound, animHighPCnt - 90, 360 - animHighPCnt, true, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40.0f);
        canvas.drawText(Integer.toString((int)(((float)animHighPCnt/360)*100)) + "%",
                highPBound.centerX() - 30, highPBound.centerY() + 10, paint);

        /* Drawing Medium priority tasks chart */
        paint.setTextSize(25.0f);
        canvas.drawText(getContext().getText(R.string.medium_priority).toString(),
                mediumPBound.left-30, mediumPBound.bottom+20, paint);
        paint.setARGB(255, 0, 255, 0);
        canvas.drawArc(mediumPBound, 270, animMediumPCnt, true, paint);
        paint.setARGB(255, 0, 200, 255);
        canvas.drawArc(mediumPBound, animMediumPCnt - 90, 360 - animMediumPCnt, true, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40.0f);
        canvas.drawText(Integer.toString((int)(((float)animMediumPCnt/360)*100)) + "%",
                mediumPBound.centerX() - 30, mediumPBound.centerY() + 10, paint);

        /* Drawing Low priority tasks chart */
        paint.setTextSize(25.0f);
        canvas.drawText(getContext().getText(R.string.low_priority).toString(),
                lowPBound.left, lowPBound.bottom+20, paint);
        paint.setARGB(255, 255, 255, 0);
        canvas.drawArc(lowPBound, 270, animLowPCnt, true, paint);
        paint.setARGB(255, 0, 200, 255);
        canvas.drawArc(lowPBound, animLowPCnt - 90, 360 - animLowPCnt, true, paint);
        paint.setColor(Color.BLACK);
        paint.setTextSize(40.0f);
        canvas.drawText(Integer.toString((int)(((float)animLowPCnt/360)*100)) + "%",
                lowPBound.centerX() - 30, lowPBound.centerY() + 10, paint);

        if(animHighPCnt < highPDegree) {
            animHighPCnt += 2;
            animFinished = false;
        }

        if(animMediumPCnt < mediumPDegree) {
            animMediumPCnt += 2;
            animFinished = false;
        }

        if(animLowPCnt < lowPDegree) {
            animLowPCnt += 2;
            animFinished = false;
        }

        if(!animFinished) {
            invalidate();
        }
    }
}
