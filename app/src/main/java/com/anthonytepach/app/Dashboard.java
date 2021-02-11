package com.anthonytepach.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.anthonytepach.app.data.interfaces.ComputerFormsUsers;
import com.anthonytepach.app.data.model.M_Personal;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Dashboard extends AppCompatActivity {
    private ImageView image_profile;
    private TextView tv_nombre;
    private TextView tv_correo;
    private TextView tv_uid;
    private Retrofit retrofit;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        image_profile = findViewById(R.id.iv_img_user);
        tv_correo = findViewById(R.id.tv_correo);
        tv_nombre = findViewById(R.id.tv_nombre_usuario);
        tv_uid = findViewById(R.id.tv_uid);
        mAuth = FirebaseAuth.getInstance();
        getInfo();
    }

    private void getInfo() {
        String email, uid, lastLogin;
        email = getIntent().getStringExtra("email");
        uid = getIntent().getStringExtra("uid");
        loadInfo(email, uid);
    }

    private void loadInfo(String email, String uid) {
        tv_correo.setText(email);
        tv_uid.setText(uid);
        //email.replaceAll("@","%40");
        String emailFormat1 = email.replaceAll("@", "_AT_");
        char myCharpunto = (char) 46;
        char myCharGuionM = (char) 95;
        String emailFormat2 = emailFormat1.replace(myCharpunto, myCharGuionM);

        getInfo(emailFormat2);
    }

    void getInfo(String email) {
//anthony.t@computerforms.com.mx
        String api_url = "https://computerforms.com.mx/App/api/v1/";
        retrofit = RetrofitClient.getClient(api_url);
        ComputerFormsUsers computerFormsUsers = retrofit.create(ComputerFormsUsers.class);
        Call<M_Personal> call = computerFormsUsers.getInfoPersona(email);
        call.enqueue(new Callback<M_Personal>() {
            @Override
            public void onResponse(Call<M_Personal> call, Response<M_Personal> response) {
                if (response.isSuccessful()) {
                    M_Personal mPersonal = response.body();
                    String nombre_completo = mPersonal.getNombre() + " " + mPersonal.getApat() + " " + mPersonal.getAmat();
                    tv_nombre.setText(nombre_completo);
                    loadImageGlide(Uri.parse(mPersonal.getImageProfile()));
                } else {
                    System.err.println(response.message());
                    tv_nombre.setText(response.message() + "  -  " + response.code());
                }
            }

            @Override
            public void onFailure(Call<M_Personal> call, Throwable t) {
                System.err.println(t.getMessage());
                tv_nombre.setText(call.toString());
                tv_nombre.setText(tv_nombre.getText() + "    +        " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void loadImageGlide(Uri photoUrl) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .autoClone()
                .placeholder(R.drawable.avatar_cf)
                .error(R.drawable.avatar_cf)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(this).load(photoUrl)
                .apply(options)
                .into(image_profile);

    }


    public void logOut(View view) {
        mAuth.signOut();
        startActivity(new Intent(Dashboard.this, Login.class));
        finish();

    }
}