LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := StatisticsLib
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := Stat.c

include $(BUILD_SHARED_LIBRARY)
