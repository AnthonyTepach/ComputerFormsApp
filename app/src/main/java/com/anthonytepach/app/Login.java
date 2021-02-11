package com.anthonytepach.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText et_email;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        et_email = findViewById(R.id.editText_usuario);
        et_password = findViewById(R.id.editText_pass);

        if (mAuth.getCurrentUser() != null) {
            cambiarVista(mAuth.getCurrentUser());
            finish();
        }

    }

    public void iniciarSesion(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            cambiarVista(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    private void cambiarVista(FirebaseUser user) {
        //Toast.makeText(this, "Se esta inciando", Toast.LENGTH_SHORT).show();
        Intent cambiarVista = new Intent(this, Dashboard.class);
        cambiarVista.putExtra("email", user.getEmail());
        cambiarVista.putExtra("uid", user.getUid());
        startActivity(cambiarVista);
        finish();
    }

    public void login(View view) {
        String email = et_email.getText().toString(),
                password = et_password.getText().toString();
        if (validarCampos(email, password)) {
            if (validarEmail(email)) {
                iniciarSesion(email, password);
            } else {
                Toast.makeText(this, "Ingresa un email valido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Tienes que llenar todos los datos", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    private boolean validarCampos(String email, String password) {
        boolean is = false;
        if (email.isEmpty() || password.isEmpty() || email == "" || password == "") {
            is = is;
        } else {
            is = true;
        }
        return is;
    }

    public void goToFacebook(View view) {
        String facebookId = "https://facebook.com/computerformsmx";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId)));
        onPause();
    }

    public void goToTwitter(View view) {
        String facebookId = "https://twitter.com/ComputerForms";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId)));
        onPause();
    }

    public void goToLinkedin(View view) {
        String facebookId = "https://facebook.com/computerformsmx";
        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(facebookId)));

    }


}