
#include <jni.h>
#include <stdio.h>

#include <string>

#include "wintoastlib.h"

using namespace std;
using namespace WinToastLib;

std::wstring Java_To_WStr(JNIEnv *env, jstring string);

std::wstring appName(JNIEnv *env);

std::wstring appUserModelId(JNIEnv *env);

WinToastTemplate::Duration getDurationEnum(jint duration);