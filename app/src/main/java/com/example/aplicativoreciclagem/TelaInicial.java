package com.example.aplicativoreciclagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;


public class TelaInicial extends AppCompatActivity {

    Button btPerfil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        btPerfil = findViewById(R.id.ButtonPerfil);

        btPerfil.setOnClickListener(view -> {
            startActivity(new Intent(TelaInicial.this, Perfil.class));
        });

    }
}