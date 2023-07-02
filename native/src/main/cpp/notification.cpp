// Save as "HelloJNI.c"
#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header
#include "notification.h"   // Generated

// Implementation of the native method sayHello()
JNIEXPORT void JNICALL Java_main_NotificationManager_sayHello(JNIEnv *, jobject) {
   printf("Hello World from cpp!\n");
   return;
}