package com.example.aplicativoreciclagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {


    FirebaseUser User = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseAuth mAuth;
    TextView tvNome;
    TextView tvLogout;
    Button bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        tvLogout = findViewById(R.id.textViewLogout);
        tvNome = findViewById(R.id.textViewNome);
        mAuth = FirebaseAuth.getInstance();
        bt1 = findViewById(R.id.Button1);
        if (User != null){
            String email = User.getEmail();
            tvNome.setText(email);
        }
        else{
            //sem usuario
        }
        tvLogout.setOnClickListener(view -> {
            mAuth.signOut();
            startActivity(new Intent(Perfil.this, login.class));
        });

    }

}