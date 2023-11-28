package com.example.myapplication.services;

import android.util.Log;

public class SangSoNguyenTo {
    public long run(int N) {
        long start, end;
        start = System.currentTimeMillis();  // start lấy thời gian theo nanosecond

        boolean[] check = new boolean[N + 1];
        // Khởi tạo tất cả các số [2...N] đều là số nguyên tố
        for (int i = 2; i <= N; i++) {
            check[i] = true;
        }

        // Thuật toán sàng nguyên tố
        // Nếu một số là số nguyên tố, thì tất cả các bội của nó không phải số nguyên tố
        for (int i = 2; i <= N; i++) {
            if (check[i]) {
                for (int j = 2 * i; j <= N; j += i) {
                    check[j] = false;
                }
            }
        }
        // In ra các số là số nguyên tố
        for (int i = 2; i <= N; i++) {
            if (check[i]) {
                Log.v("Java ", i + " ");
            }
        }

        end = System.currentTimeMillis();    // start lấy thời gian theo nanosecond
        return end - start;
    }
}
