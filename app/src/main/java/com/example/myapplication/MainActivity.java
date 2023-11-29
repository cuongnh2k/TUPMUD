package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.example.myapplication.services.DeQuyFibonaci;
import com.example.myapplication.services.SangSoNguyenTo;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'myapplication' library on application startup.
    static {
        System.loadLibrary("myapplication");
    }

    private ActivityMainBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSangSoNguyenTo.setOnClickListener(view -> {
            int n = 999999;
            long timeC = stringFromJNI(1, n, new int[]{}), timeJava = new SangSoNguyenTo().run(n);
            binding.timeC.setText(timeC + "");
            binding.timeJava.setText(timeJava + "");
            binding.cJava.setText(((double) timeC / (double) timeJava) + "");
        });

        binding.btnDeQuyFibonaci.setOnClickListener(view -> {
            int n = 35;
            long timeC = stringFromJNI(2, n, new int[]{}), timeJava = new DeQuyFibonaci().run(n);
            binding.timeC.setText(timeC + "");
            binding.timeJava.setText(timeJava + "");
            binding.cJava.setText(((double) timeC / (double) timeJava) + "");
        });

//        binding.btnSapXepNoiBot.setOnClickListener(view -> {
//            int[] arr = {17, 95, 49, 12, 36, 64, 13, 5, 73, 85, 34, 67, 82, 45, 25, 81, 7, 60, 28, 76, 19, 40, 51, 16
//                    , 26, 9, 31, 91, 78, 83, 74, 59, 50, 44, 56, 24, 35, 88, 10, 87, 46, 92, 66, 11, 42, 22, 3, 90, 33
//                    , 38, 47, 8, 27, 37, 20, 84, 72, 93, 79, 43, 77, 69, 6, 55, 14, 98, 71, 99, 54, 58, 1, 41, 61, 94
//                    , 53, 96, 39, 97, 68, 62, 75, 89, 2, 18, 63, 15, 29, 23, 30, 65, 70, 32, 4, 86, 80, 52, 100, 21, 57
//                    , 48
//            };
//            long timeJava = new SapXepNoiBot().run(arr);
//            long timeC = stringFromJNI(3, 1, arr);
//            binding.timeC.setText(timeC + "");
//            binding.timeJava.setText(timeJava + "");
//            binding.cJava.setText(((double) timeC / (double) timeJava) + "");
//        });

        binding.btnDoPinCoTruySuatBoNho.setOnClickListener(view -> {

            long totalTime = 0;
            while (totalTime < (60 * 60)) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored) {
                }
                totalTime++;
            }
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = this.registerReceiver(null, ifilter);

            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPct = level * 100 / (float) scale;

            binding.cJava.setText(totalTime + "s - " + batteryPct + "%");
        });

        binding.btnDoPinKhongTruySuatBoNho.setOnClickListener(view -> {

            long totalTime = 0;
            while (totalTime < (1)) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException ignored) {
                }
                totalTime++;
            }
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
            Intent batteryStatus = this.registerReceiver(null, ifilter);

            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

            float batteryPct = level * 100 / (float) scale;

            binding.cJava.setText(totalTime + "s - " + batteryPct + "%");
        });
    }

    /**
     * A native method that is implemented by the 'myapplication' native library,
     * which is packaged with this application.
     */
    public native long stringFromJNI(int i, int n, int[] a);

}