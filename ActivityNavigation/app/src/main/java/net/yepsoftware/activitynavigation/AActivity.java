package net.yepsoftware.activitynavigation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class AActivity extends AppCompatActivity {

    private ArrayList<ImageButton> imageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        imageList = new ArrayList<>();

        imageList.add((ImageButton) findViewById(R.id.imageA));
        imageList.add((ImageButton) findViewById(R.id.imageB));
        imageList.add((ImageButton) findViewById(R.id.imageC));
        imageList.add((ImageButton) findViewById(R.id.imageD));
        imageList.add((ImageButton) findViewById(R.id.imageE));
        imageList.add((ImageButton) findViewById(R.id.imageF));
        imageList.add((ImageButton) findViewById(R.id.imageG));
        imageList.add((ImageButton) findViewById(R.id.imageH));
        imageList.add((ImageButton) findViewById(R.id.imageI));
        imageList.add((ImageButton) findViewById(R.id.imageJ));

        final Intent intent = new Intent(AActivity.this, BActivity.class);

        for(ImageButton imageButton : imageList){
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
        }
    }
}
