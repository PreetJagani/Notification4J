// Save as "HelloJNI.c"
#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header
#include "notification.h"   // Generated
#include "wintoastlib.h"

using namespace WinToastLib;
using namespace std;

class CustomHandler : public IWinToastHandler {
public:

    CustomHandler(JNIEnv *env) {

    }

    CustomHandler() {
    }

    void toastActivated() const {
        std::wcout << L"The user clicked in this toast" << std::endl;
    }

    void toastActivated(int actionIndex) const {
        std::wcout << L"The user clicked on action #" << actionIndex << std::endl;
    }

    void toastDismissed(WinToastDismissalReason state) const {
        switch (state) {
        case UserCanceled:
            std::wcout << L"The user dismissed this toast" << std::endl;
            break;
        case TimedOut:
            std::wcout << L"The toast has timed out" << std::endl;
            break;
        case ApplicationHidden:
            std::wcout << L"The application hid the toast using ToastNotifier.hide()" << std::endl;
            break;
        default:
            std::wcout << L"Toast not activated" << std::endl;
            break;
        }
    }

    void toastFailed() const {
        std::wcout << L"Error showing current toast" << std::endl;
    }
};

void nativeLog(JNIEnv *env, char * message) {

}

std::wstring Java_To_WStr(JNIEnv *env, jstring string) {
    std::wstring value;
    const jchar *raw = env->GetStringChars(string, 0);
    jsize len = env->GetStringLength(string);

    value.assign(raw, raw + len);
    env->ReleaseStringChars(string, raw);
    return value;
}

// Implementation of the native method sayHello()
JNIEXPORT void JNICALL Java_main_NotificationManager_sayHello(JNIEnv *, jobject) {
   printf("Hello World from cpp!\n");
   return;
}

JNIEXPORT void JNICALL
Java_main_NotificationManager_helloNotification(JNIEnv *env, jobject obj, jstring title)  {
    WinToast::instance()->setAppName(L"Hello Notification");
    WinToast::instance()->setAppUserModelId(L"Notification.Hello");
    if (!WinToast::instance()->initialize()) {
        nativeLog(env, "Error, your system in not compatible!");
    }

    WinToastTemplate templ(WinToastTemplate::Text02);
    	templ.setTextField(Java_To_WStr(env, title), WinToastTemplate::FirstLine);
//    	templ.setTextField(Java_To_WStr(env, sound), WinToastTemplate::SecondLine);
        templ.setDuration(WinToastTemplate::Duration::Short);
//        templ.setAudioPath(getSoundEnum(Java_To_WStr(env,sound)));
        templ.setExpiration(7000);

        if (WinToast::instance()->showToast(templ, new CustomHandler(env)) < 0) {
            nativeLog(env, "Could not launch your toast notification!");
        	return;
        }
    return;
}