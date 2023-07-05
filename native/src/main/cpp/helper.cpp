
#include "helper.h"

std::wstring Java_To_WStr(JNIEnv *env, jstring string) {
    std::wstring value;
    const jchar *raw = env->GetStringChars(string, 0);
    jsize len = env->GetStringLength(string);

    value.assign(raw, raw + len);
    env->ReleaseStringChars(string, raw);
    return value;
}

std::wstring appName(JNIEnv *env) {
    jclass cls = (env) -> FindClass("main/NotificationManager");
    jmethodID appNameMethod = (env) -> GetStaticMethodID(cls, "getApplicationName", "()Ljava/lang/String;");
    jstring str = (jstring) (env) -> CallStaticObjectMethod(cls, appNameMethod);
    return Java_To_WStr(env, str);
}

std::wstring appUserModelId(JNIEnv *env) {
    jclass cls = (env) -> FindClass("main/NotificationManager");
    jmethodID appNameMethod = (env) -> GetStaticMethodID(cls, "getAppUserModelId", "()Ljava/lang/String;");
    jstring str = (jstring) (env) -> CallStaticObjectMethod(cls, appNameMethod);
    return Java_To_WStr(env, str);
}

WinToastTemplate::Duration getDurationEnum(jint duration) {
    if (duration == 0) {
        return WinToastTemplate::Duration::System;
    } else if (duration == 1) {
       return WinToastTemplate::Duration::Short;
    }  else if (duration == 2) {
        return WinToastTemplate::Duration::Long;
    }
    return WinToastTemplate::Duration::System;
}