package com.example.myapplication.services;

public class SapXepNoiBot {

    public long run(int[] a) {
        long start, end;
        start = System.currentTimeMillis();  // start lấy thời gian theo nanosecond

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
        end = System.currentTimeMillis();    // start lấy thời gian theo nanosecond
        return end - start;
    }
}
