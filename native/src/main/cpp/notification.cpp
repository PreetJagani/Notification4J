// Save as "HelloJNI.c"
#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header
#include "notification.h"   // Generated
#include "wintoastlib.h"
#include "helper.h"

using namespace WinToastLib;
using namespace std;

static JavaVM* mVm = NULL;

jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    mVm = vm;
    JNIEnv* env;

    if ((vm)->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        return -1;
    }
    return JNI_VERSION_1_6;
}

void javaLog(JNIEnv *env, char * message) {
    if((mVm)->AttachCurrentThread((void **)&env, NULL) == JNI_OK) {
        cout << "Attach success" <<endl;

        jstring _message = (env)->NewStringUTF(message);

        jclass cls = (env) -> FindClass("main/NotificationManager");
        jmethodID midNativeLog = (env) -> GetStaticMethodID(cls, "javaLog", "(Ljava/lang/String;)V");
        (env) -> CallStaticVoidMethod(cls, midNativeLog, _message);
    }
}

class CustomHandler : public IWinToastHandler {
public:
    JNIEnv *env;
    jint identifier;
    CustomHandler(JNIEnv *env, jint identifier) {
        this -> env = env;
        this -> identifier = identifier;
    }

    void toastActivated() const {
        if ((mVm)->AttachCurrentThread((void **)&env, NULL) == JNI_OK) {
            cout << "Attach success" <<endl;
        }
        jclass cls = (env) -> FindClass("main/NotificationManager");
        jmethodID handler = (env) -> GetStaticMethodID(cls, "handleNotificationClick", "(I)V");
        (env) -> CallStaticVoidMethod(cls, handler, identifier);
        (mVm) -> DetachCurrentThread();
    }

    void toastActivated(int actionIndex) const {
        if((mVm)->AttachCurrentThread((void **)&env, NULL) == JNI_OK) {
            cout << "Attach success" <<endl;
        }
        jclass cls = (env) -> FindClass("main/NotificationManager");
        jmethodID actionHandler = (env) -> GetStaticMethodID(cls, "handleNotificationActionClick", "(II)V");
        (env) -> CallStaticVoidMethod(cls, actionHandler, identifier, actionIndex);
        (mVm) -> DetachCurrentThread();
    }

    void toastDismissed(WinToastDismissalReason state) const {
        switch (state) {
        case UserCanceled:
            javaLog(env, "The user dismissed this toast");
            break;
        case TimedOut:
            javaLog(env, "The toast has timed out");
            break;
        case ApplicationHidden:
            javaLog(env, "The application hid the toast using ToastNotifier.hide()");
            break;
        default:
            javaLog(env, "Toast not activated");
            break;
        }
    }

    void toastFailed() const {
        javaLog(env, "Error showing current toast");
    }
};

JNIEXPORT void JNICALL
Java_main_NotificationManager_postNotification(JNIEnv *env, jobject obj, jstring title, jstring subtitle, jint identifier, jstring avatarPath, jstring sound, jobjectArray actions)  {
    WinToast::instance()->setAppName(appName(env));
    WinToast::instance()->setAppUserModelId(appUserModelId(env));
    if (!WinToast::instance()->initialize()) {
        javaLog(env, "Error, your system in not compatible!");
    }

    WinToastTemplate templ;
    if (avatarPath == NULL) {
        templ = WinToastTemplate::Text02;
    } else {
        templ = WinToastTemplate::ImageAndText02;
        templ.setImagePath(Java_To_WStr(env, avatarPath));
    }
    templ.setTextField(Java_To_WStr(env, title), WinToastTemplate::FirstLine);
    templ.setTextField(Java_To_WStr(env, subtitle), WinToastTemplate::SecondLine);
// TODO: fix this templ.setDuration(getDurationEnum(duration));
    templ.setDuration(WinToastTemplate::Duration::Short);
    templ.setAudioPath(getSoundEnum(Java_To_WStr(env,sound)));
    templ.setExpiration(7000);

    int actionCount = env->GetArrayLength(actions);

    for (int i = 0; i < actionCount; i++) {
        jstring action = (jstring) (env->GetObjectArrayElement(actions, i));
        templ.addAction(Java_To_WStr(env, action));
    }

    if (WinToast::instance()->showToast(templ, new CustomHandler(env, identifier)) < 0) {
        javaLog(env, "Could not launch your toast notification!");
        return;
    }
    return;
}

JNIEXPORT void JNICALL
Java_main_NotificationManager_clearNotifications(JNIEnv *env, jobject obj) {
    WinToast::instance()->clear();
}