package com.example.lab1_androidnetworking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Listener {

    ImageView imgloadimage;
    TextView tvMessage;
    Button btnloadimage;
    private ProgressDialog progressDialog;
    public static final String IMAGE_URL= "https://tse1.mm.bing.net/th?id=OIP.ttMjn3iJg9Pdu8Y6_sedeAHaF7&pid=Api&P=0";
    private String url = ("https://tse1.mm.bing.net/th?id=OIP.ttMjn3iJg9Pdu8Y6_sedeAHaF7&pid=Api&P=0");
    private Bitmap bm = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgloadimage = findViewById(R.id.imgdowload);
        tvMessage = findViewById(R.id.tvmessage);
        btnloadimage = findViewById(R.id.btnloadimage);
        btnloadimage.setOnClickListener(this);


//        btnloadimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {


        // Bai2
//                progressDialog = progressDialog.show(MainActivity.this, "", "Downloading...");
//                Runnable runnable = new Runnable() {
//                    @Override
//                    public void run() {
//                        bm = downloadBitmap(url);
//                        Message message = mesHandler.obtainMessage();
//                        Bundle bundle = new Bundle();
//                        String threadmessage = "Image DownLoaded";
//                        bundle.putString("message",threadmessage);
//                        message.setData(bundle);
//                        mesHandler.sendMessage(message);
//                    }
//                };
//                Thread th2 = new Thread(runnable);
//                th2.start();



        //bai1
//                final Thread th1 = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        final Bitmap bm = loadImageFromnetwork("https://tse1.mm.bing.net/th?id=OIP.ttMjn3iJg9Pdu8Y6_sedeAHaF7&pid=Api&P=0");
//                        imgloadimage.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                tvMessage.setText("Image Downloaded");
//                                imgloadimage.setImageBitmap(bm);
//                            }
//                        });
//                    }
//                });
//                th1.start();
//            }
//        });

    }

    private Handler mesHandler = new Handler(){
      public void handleMessage(Message ms){
          super.handleMessage(ms);
          Bundle bundle = ms.getData();
          String message = bundle.getString("message");
          tvMessage.setText(message);
          imgloadimage.setImageBitmap(bm);
          progressDialog.dismiss();
      }
    };

    private Bitmap downloadBitmap(String link){
        try {
            URL url1 = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap loadImageFromnetwork(String link){
        URL url;
        Bitmap bm = null;
        try {
            url = new URL(link);
            bm = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
        return bm;
    }

    @Override
    public void OnImageLoader(Bitmap bitmap) {
        try {
            Thread.sleep(5000);

        }catch (Exception e){
            e.printStackTrace();
        }
        imgloadimage.setImageBitmap(bitmap);
        tvMessage.setText("Image is Downloaded");
    }

    @Override
    public void OnError() {
        tvMessage.setText("Error");
    }

    @Override
    public void onClick(View argo) {
        switch (argo.getId()){
            case R.id.btnloadimage:
                new LoadImagetask(this,this).execute(IMAGE_URL);
                break;
        }
    }
}