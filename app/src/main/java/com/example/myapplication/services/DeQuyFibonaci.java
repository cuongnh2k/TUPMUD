package com.example.myapplication.services;

import android.util.Log;

public class DeQuyFibonaci {

    int day_fibonaci(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == 1) {
            return 1;
        }
        return day_fibonaci(i - 1) + day_fibonaci(i - 2);
    }

    public long run(int N) {
        long start, end;
        start = System.currentTimeMillis();  // start lấy thời gian theo nanosecond

        for (int i = 0; i < N; i++) {
            Log.v("Java ", day_fibonaci(i) + "");
        }

        end = System.currentTimeMillis();    // start lấy thời gian theo nanosecond
        return end - start;
    }
}
