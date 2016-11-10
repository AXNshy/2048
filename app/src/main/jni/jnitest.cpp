//
// Created by Administrator on 2016/10/2

#define LOG_TAG "simplejni native.cpp"

#include <stdio.h>
#include <jni.h>


//java层声明native方法的类的完整名，com.jxm.test.NDK需要写成下面的形式；
static const char *classPathName = "com/jxm/test/NDK";

static void Toast(JNIEnv *env, jobject obj) {
    jclass clazz = env->GetObjectClass(obj);
    jmethodID mid = env->GetMethodID(clazz, "showToast", "(Ljava/lang/String;)V");

    if (mid == 0)
        return;
//    jobject m_object = env->CallVoidMethod(clazz,);
    char s[] = "c++ from java";
    jstring str = env->NewStringUTF(s);
    env->CallVoidMethod(obj, mid,str);
}

//对应Java层的native方法_sayHello
static jstring Udp_sayHello(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("from sayHello");
}

//对应Java层的native方法getStringFromC
static jstring getStringFromC(JNIEnv *env, jobject obj) {
    return env->NewStringUTF("getStringFromC");
}

//对应Java层的native方法getStringFromNative
static jstring getStringFromNative(JNIEnv *env, jobject obj) {

    return env->NewStringUTF("getStringFromNative");
}

static void onClick(JNIEnv *env, jobject obj) {
    Toast(env, obj);
}


//JNINativeMethod数组，用来将c层方法与java层方法进行映射
static JNINativeMethod methods[] = {
        {"_sayHello",           "()Ljava/lang/String;", (void *) Udp_sayHello},
        {"getStringFromC",      "()Ljava/lang/String;", (void *) getStringFromC},
        {"getStringFromNative", "()Ljava/lang/String;", (void *) getStringFromNative},
        {"onClick",             "()V",                  (void *) onClick}
};

//
static int registerNativeMethods(JNIEnv *env, const char *className,
                                 JNINativeMethod *gMethods, int numMethods) {
    jclass clazz;
    //通过className获取到jclass对象
    clazz = env->FindClass(className);
    if (clazz == NULL) {
        return JNI_FALSE;
    }
    //注册Native方法到JNI
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

static int registerNatives(JNIEnv *env) {
    if (!registerNativeMethods(env, classPathName, methods, sizeof(methods) / sizeof(methods[0]))) {
        return JNI_FALSE;
    }
    return JNI_TRUE;
}

//一个联合体，JNIEnv* 是指针指向Java环境，第二个暂时不清楚什么意思
typedef union {
    JNIEnv *env;
    void *venv;
} UnionJNIEnvToVoid;

//开始加载SO库
jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    UnionJNIEnvToVoid uenv;
    uenv.venv = NULL;
    jint result = -1;
    JNIEnv *env = NULL;

    //判断当前JNI的版本，如果版本不为1.4，则直接返回
    if (vm->GetEnv(&uenv.venv, JNI_VERSION_1_4) != JNI_OK) {

        goto bail;
    }
    //获取JNIEnv指针
    env = uenv.env;
    //注册native方法
    if (registerNatives(env) != JNI_TRUE) {

        goto bail;
    }

    result = JNI_VERSION_1_4;

    bail:
    return result;
}

void JNI_OnUnload(JavaVM *jvm, void *reserved) {
}
