package com.example.lab1_androidnetworking;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;

import java.io.InputStream;
import java.net.URL;

public class LoadImagetask extends AsyncTask<String, Void, Bitmap> {
    private Listener mlistener;
    private ProgressDialog dialog;
    public static int time=5;

    public LoadImagetask(Listener listener, Context context) {
        mlistener = listener;
        dialog = new ProgressDialog(context);
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
          Thread thread = new Thread(new Runnable() {
              @Override
              public void run() {
                  for (int i =0;i <=4;i++){
                      time--;
                      dialog.setMessage("Wait for "+ time +" Second");
                      try {
                          Thread.sleep(5000);
                      }catch (Exception e){
                          e.printStackTrace();
                      }

                  }
              }
          });

        thread.start();
        dialog.show();

    }

    @Override
    protected Bitmap doInBackground(String... params) {
        try {
            return BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
        if (dialog.isShowing()){
            dialog.dismiss();
        }
        if (result != null){
            mlistener.OnImageLoader(result);
        }else {
            mlistener.OnError();
        }
    }
}
