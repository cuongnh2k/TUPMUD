#include <jni.h>
#include <string>
#include <android/log.h>
#include <stdlib.h>
#include <time.h>
#include <sys/time.h>
#include <stdio.h>
#include <string.h>

#define TAG "TEST_HOOK_NATIVE"

#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)

long sangSoNguyenTo(int N){
    struct timeval t0;
    struct timeval t1;

    gettimeofday(&t0, 0);

    bool check[N + 1];
    // Khởi tạo tất cả các số [2...N] đều là số nguyên tố
    for (int i = 2; i <= N; i++) {
        check[i] = true;
    }

    // Thuật toán sàng nguyên tố
    // Nếu một số là số nguyên tố, thì tất cả các bội của nó không phải số nguyên tố
    for (int i = 2; i <= N; i++) {
        if (check[i] == true) {
            for (int j = 2 * i; j <= N; j += i) {
                check[j] = false;
            }
        }
    }
    // In ra các số là số nguyên tố
    for (int i = 2; i <= N; i++) {
        if (check[i] == true) {
            LOGI("C %d", i);
        }
    }

    gettimeofday(&t1, 0);

    return (t1.tv_sec - t0.tv_sec) * 1000.0f + (t1.tv_usec - t0.tv_usec) / 1000.0f;
}

int day_fibonaci(int i) {
    if (i == 0) {
        return 0;
    }
    if (i == 1) {
        return 1;
    }
    return day_fibonaci(i - 1) + day_fibonaci(i - 2);
}

long deQuyFibonaci(int N) {
    struct timeval t0;
    struct timeval t1;

    gettimeofday(&t0, 0);

    for (int i = 0; i < N; i++) {
        LOGI("C %d", day_fibonaci(i));
    }

    gettimeofday(&t1, 0);

    return (t1.tv_sec - t0.tv_sec) * 1000.0f + (t1.tv_usec - t0.tv_usec) / 1000.0f;
}

long sapXepNoiBot(int a[]) {
    struct timeval t0;
    struct timeval t1;

    gettimeofday(&t0, 0);

for (int i = 0; i < (int)sizeof(a); i++) {
            for (int j = i + 1; j < (int)sizeof(a); j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }

    gettimeofday(&t1, 0);

    return (t1.tv_sec - t0.tv_sec) * 1000.0f + (t1.tv_usec - t0.tv_usec) / 1000.0f;
}

extern "C" JNIEXPORT long JNICALL
Java_com_example_myapplication_MainActivity_stringFromJNI(JNIEnv* env, jobject thisObj, int choose, int N, int a[]) {
        switch(choose){
            case 1:
                return sangSoNguyenTo(N);
            case 2:
                return deQuyFibonaci(N);
            case 3:
                return sapXepNoiBot(a);
        }
}