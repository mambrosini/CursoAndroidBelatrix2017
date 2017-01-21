package com.belatrixsf.cursoandroid.servicecontentproviderapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.content.LocalBroadcastManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by mambrosini on 1/18/17.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgView;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgView = (ImageView) findViewById(R.id.imageView2);

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int result = intent.getIntExtra(ImageDownloadService.MESSAGE, 0);
                String filePath = intent.getStringExtra(ImageDownloadService.FILEPATH);
                switch (result) {
                    case ImageDownloadService.DOWNLOAD_ERROR:
                        Toast.makeText(getApplicationContext(), "Error",
                                Toast.LENGTH_SHORT).show();
                        alternateLoadingIndicatorVisibility();
                        break;

                    case ImageDownloadService.DOWNLOAD_SUCCESS:
                        Bitmap bmp = BitmapFactory.decodeFile(filePath);
                        if (imgView != null && bmp != null) {
                            imgView.setImageBitmap(bmp);
                            Toast.makeText(getApplicationContext(),
                                    "Download finished",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Error",
                                    Toast.LENGTH_SHORT).show();
                        }
                        alternateLoadingIndicatorVisibility();

                        break;
                }
                stopService(new Intent(MainActivity.this, ImageDownloadService.class));
            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver((broadcastReceiver), new IntentFilter(ImageDownloadService.IMAGE_DOWLOADED_BROADCAST));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    public void downloadImage(View v){

        TextView urlTextView = (TextView) findViewById(R.id.editText3);
        String url = String.valueOf(urlTextView.getText());

        alternateLoadingIndicatorVisibility();

        Intent downloadIntent = new Intent(MainActivity.this,
                ImageDownloadService.class);
        downloadIntent.putExtra("url",url);
        startService(downloadIntent);

    }

    @Override
    public void onClick(View v) {

    }

    private void alternateLoadingIndicatorVisibility(){

        if (findViewById(R.id.overlay).getVisibility() == View.VISIBLE){

            findViewById(R.id.overlay).setVisibility(View.GONE);
            findViewById(R.id.progressBar).setVisibility(View.GONE);
            findViewById(R.id.editText3).setEnabled(true);
            findViewById(R.id.button8).setEnabled(true);

        } else {

            findViewById(R.id.overlay).setVisibility(View.VISIBLE);
            findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            findViewById(R.id.editText3).setEnabled(false);
            findViewById(R.id.button8).setEnabled(false);

        }

    }
}
