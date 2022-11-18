package com.example.aplicativoreciclagem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Cadastro extends AppCompatActivity {


    Button btnCadastro;
    EditText etEmail;
    EditText etSenha;
    EditText etConfirmarSenha;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        Button backButton = (Button) findViewById(R.id.backbutton);
        btnCadastro = findViewById(R.id.buttonCadastrar);
        etEmail = findViewById(R.id.editTextEmail);
        etSenha = findViewById(R.id.editTextSenha);
        etConfirmarSenha = findViewById(R.id.editTextConfirmarSenha);



        mAuth = FirebaseAuth.getInstance();

        btnCadastro.setOnClickListener(view -> {
            createUser();
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cadastro.this, login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void createUser() {
        String email = etEmail.getText().toString();
        String senha = etSenha.getText().toString();
        String confirmarsenha = etConfirmarSenha.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etEmail.setError("Email não pode estar vazio!");
            etEmail.requestFocus();
        }
        else if (TextUtils.isEmpty(senha)) {
            etSenha.setError("Email não pode estar vazio!");
            etSenha.requestFocus();
        }
        else if (TextUtils.isEmpty(confirmarsenha)) {
            etConfirmarSenha.setError("Este campo não pode estar vazio!");
            etConfirmarSenha.requestFocus();
        }
        else if(!Objects.equals(confirmarsenha, senha)){
            etSenha.setError("As senhas não coincidem!");
            etSenha.requestFocus();
            etConfirmarSenha.setError("As senhas não coincidem!");
            etConfirmarSenha.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Cadastro.this, "O Usuário foi criado com Sucesso!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Cadastro.this, login.class));
                    }
                    else{
                        Toast.makeText(Cadastro.this, "Ocorreu um erro." + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}