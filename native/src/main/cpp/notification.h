/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class HelloJNI */

#ifndef _Included_main_NotificationManager
#define _Included_main_NotificationManager
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     NotificationManager
 * Method:    sayHello
 * Signature: ()V
 */

JNIEXPORT void JNICALL
Java_main_NotificationManager_postNotification(JNIEnv *env, jobject obj, jstring title, jstring subtitle, jint identifier, jstring avatarPath, jstring sound, jobjectArray actions);

JNIEXPORT void JNICALL
Java_main_NotificationManager_clearNotifications(JNIEnv *env, jobject obj);

#ifdef __cplusplus
}
#endif
#endif