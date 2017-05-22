// IMyBinder.aidl
package rtrk.pnrs1.ra11_2014;

// Declare any non-default types here with import statements

interface IMyBinder {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
    void addTaskNotify(String taskName);
    void modifyTaskNotify(String taskName);
    void deleteTaskNotify(String taskName);
}
