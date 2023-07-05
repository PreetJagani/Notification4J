
#include "helper.h"

#include <unordered_map>

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

WinToastTemplate::AudioSystemFile getSoundEnum(wstring sound) {
    static const std::unordered_map<std::wstring,WinToastTemplate::AudioSystemFile> Files = {
        {L"Default", WinToastTemplate::AudioSystemFile::DefaultSound },
        {L"IM", WinToastTemplate::AudioSystemFile::IM },
        {L"Mail", WinToastTemplate::AudioSystemFile::Mail },
        {L"Reminder", WinToastTemplate::AudioSystemFile::Reminder },
        {L"SMS", WinToastTemplate::AudioSystemFile::SMS },
        {L"Alarm", WinToastTemplate::AudioSystemFile::Alarm },
        {L"Alarm2", WinToastTemplate::AudioSystemFile::Alarm2 },
        {L"Alarm3", WinToastTemplate::AudioSystemFile::Alarm3 },
        {L"Alarm4", WinToastTemplate::AudioSystemFile::Alarm4 },
        {L"Alarm5", WinToastTemplate::AudioSystemFile::Alarm5 },
        {L"Alarm6", WinToastTemplate::AudioSystemFile::Alarm6 },
        {L"Alarm7", WinToastTemplate::AudioSystemFile::Alarm7 },
        {L"Alarm8", WinToastTemplate::AudioSystemFile::Alarm8 },
        {L"Alarm9", WinToastTemplate::AudioSystemFile::Alarm9 },
        {L"Alarm10", WinToastTemplate::AudioSystemFile::Alarm10 },
        {L"Call", WinToastTemplate::AudioSystemFile::Call },
        {L"Call1", WinToastTemplate::AudioSystemFile::Call1 },
        {L"Call2", WinToastTemplate::AudioSystemFile::Call2 },
        {L"Call3", WinToastTemplate::AudioSystemFile::Call3 },
        {L"Call4", WinToastTemplate::AudioSystemFile::Call4 },
        {L"Call5", WinToastTemplate::AudioSystemFile::Call5 },
        {L"Call6", WinToastTemplate::AudioSystemFile::Call6 },
        {L"Call7", WinToastTemplate::AudioSystemFile::Call7 },
        {L"Call8", WinToastTemplate::AudioSystemFile::Call8 },
        {L"Call9", WinToastTemplate::AudioSystemFile::Call9 },
        {L"Call10", WinToastTemplate::AudioSystemFile::Call10 },
    };
    const auto iter = Files.find(sound);
    if (iter == Files.end()){
        return WinToastTemplate::AudioSystemFile::DefaultSound;
    }
    return iter->second;
}