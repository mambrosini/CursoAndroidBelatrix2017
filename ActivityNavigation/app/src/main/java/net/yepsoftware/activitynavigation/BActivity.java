package net.yepsoftware.activitynavigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
    }

    public void openC(View v){
        Intent intent = new Intent(BActivity.this, CActivity.class);
        startActivity(intent);
    }

    public void openD(View v){
        Intent intent = new Intent(BActivity.this, DActivity.class);
        startActivity(intent);
    }

    public void openE(View v){
        Intent intent = new Intent(BActivity.this, EActivity.class);
        startActivity(intent);
    }
}
