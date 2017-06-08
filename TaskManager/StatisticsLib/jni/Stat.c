#include "Stat.h"
#include <jni.h>

JNIEXPORT jfloat JNICALL Java_rtrk_pnrs1_ra11_12014_Stat_count
  (JNIEnv *env, jobject obj, jint mVal1, jint mVal2)
  {
	return (jfloat) ((mVal2 == 0) ? 0 : mVal2/mVal1*100);
  }