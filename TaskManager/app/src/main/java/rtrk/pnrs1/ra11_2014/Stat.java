package rtrk.pnrs1.ra11_2014;

/**
 * Created by student on 8.6.2017.
 */

public class Stat {

    public native float count(int taskNum, float taskFinished);

    static {
        System.loadLibrary("StatisticsLib");
    }
}
