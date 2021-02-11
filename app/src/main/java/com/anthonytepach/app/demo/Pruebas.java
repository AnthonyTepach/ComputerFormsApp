package com.anthonytepach.app.demo;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Pruebas {

    public String convertirStringIMG(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();
        String img = Base64.encodeToString(imageByte, Base64.DEFAULT);
        return img;
    }
}
