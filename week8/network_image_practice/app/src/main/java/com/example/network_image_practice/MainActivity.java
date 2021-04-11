package com.example.network_image_practice;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // REFERENCES TO OBJECTS
        button = (Button) findViewById(R.id.btn);
        imageView = (ImageView) findViewById(R.id.imageView);
    }

    public void getImage (View view){
        new Thread() { // retrieving the image on another thread
            public void run() {
                try {
                    //Step 1: Create and send a HTTP request to a URL.
                    Message msg = Message.obtain();
                    InputStream inputStream = null;
                    URL url = new URL("http://facweb1.redlands.edu/fac/patriciacornez/cs222/ass6mockupbefore.png");
                    URLConnection urlConn = url.openConnection();
                    HttpURLConnection httpConnection = (HttpURLConnection)  urlConn;
                    httpConnection.setRequestMethod("GET");
                    httpConnection.connect();
                    inputStream = httpConnection.getInputStream();

                    //Step 2: Once the server replies, receive the response and parse it.
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bitmap", bitmap);
                    msg.setData(bundle);
                    inputStream.close();

                    //Step 3: Using the main thread, update the UI
                    mHandler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

    private Handler mHandler = new Handler() {
        public void handleMessage (Message msg){
            imageView.setImageBitmap((Bitmap) (msg.getData().getParcelable("bitmap")));
        }
    };
}