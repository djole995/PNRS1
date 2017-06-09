#include "Stat.h"
#include <jni.h>

JNIEXPORT jfloat JNICALL Java_rtrk_pnrs1_ra11_12014_Stat_count
  (JNIEnv *env, jobject obj, jint taskNum, jfloat taskFinished)
  {
	return (jfloat) ((taskFinished == 0) ? 0 : taskFinished/taskNum*100);
  }