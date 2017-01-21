package net.yepsoftware.chrono;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Thread thread;
    TextView timeTextView;
    Button button;
    int time;
    boolean started;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        button = (Button) findViewById(R.id.button);
        time = 0;
        started = false;
    }

    @Override
    protected void onDestroy() {
        if (thread != null){
            thread.interrupt();
        }
        super.onDestroy();
    }

    public void startChronometer(View v){
        if (started){
            stopThread(v);
        } else {
            startThread(v);
        }
    }

    public void startThread(View v){
        button.setText("STOP");
        timeTextView.setText("00:00:00:00");
        started = true;
        time = 0;
        thread = new Thread(new Runnable() {
            public void run() {
                do {
                    try {
                        Thread.sleep(10);
                        time++;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (started) {
                                    timeTextView.setText(convertHundredthToHMSC(time));
                                }
                            }
                        });
                    } catch (InterruptedException e) {
                        break;
                    }
                } while (true);
                return;
            }
        });
        if (!thread.isAlive()) {
            thread.start();
        }
    }

    public void stopThread(View v){
        started = false;
        button.setText("START");
        if (thread != null){
            thread.interrupt();
            thread = null;
        }
        time = 0;
    }

    public static String convertHundredthToHMSC(long houndredth) {
        long hnd = houndredth % 100;
        long s = (houndredth / 100) % 60;
        long m = (houndredth / (60 * 100)) % 60;
        long h = (s / (60 * 60 * 100)) % 24;
        return String.format("%02d:%02d:%02d:%02d", h,m,s,hnd);
    }

}
