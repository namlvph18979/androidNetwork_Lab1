package com.example.lab1_androidnetworking;

import android.graphics.Bitmap;
import android.view.View;

public interface Listener {

    void OnImageLoader(Bitmap bitmap);
    void OnError();

    void onClick(View argo);
}
