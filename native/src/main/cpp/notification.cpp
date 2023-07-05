// Save as "HelloJNI.c"
#include <jni.h>        // JNI header provided by JDK
#include <stdio.h>      // C Standard IO Header
#include "notification.h"   // Generated
#include "wintoastlib.h"
#include "helper.h"

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

JNIEXPORT void JNICALL
Java_main_NotificationManager_postNotification(JNIEnv *env, jobject obj, jstring title, jstring subtitle, jstring avatarPath, jstring sound)  {
    WinToast::instance()->setAppName(appName(env));
    WinToast::instance()->setAppUserModelId(appUserModelId(env));
    if (!WinToast::instance()->initialize()) {
        nativeLog(env, "Error, your system in not compatible!");
    }

    WinToastTemplate templ(WinToastTemplate::ImageAndText02);
    templ.setTextField(Java_To_WStr(env, title), WinToastTemplate::FirstLine);
    templ.setTextField(Java_To_WStr(env, subtitle), WinToastTemplate::SecondLine);
// TODO: fix this templ.setDuration(getDurationEnum(duration));
    templ.setDuration(WinToastTemplate::Duration::Short);
    templ.setImagePath(Java_To_WStr(env, avatarPath));
    templ.setAudioPath(getSoundEnum(Java_To_WStr(env,sound)));
    templ.setExpiration(7000);

    if (WinToast::instance()->showToast(templ, new CustomHandler(env)) < 0) {
        nativeLog(env, "Could not launch your toast notification!");
        return;
    }
    return;
}