package com.example.tp2_lab3.iu.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp2_lab3.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginVM;
    private EditText etEmail, etContrasena;
    private  Button botonLogin, botonRegistro;
    private TextView tvMensaje;
    private  ProgressBar barraLoading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        iniciarVistas();
    }

    private void iniciarVistas() {
        loginVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        etEmail = findViewById(R.id.etLEmail);
        etContrasena = findViewById(R.id.etLContraseña);
        botonLogin = findViewById(R.id.btnLogin);
        botonRegistro = findViewById(R.id.btnRegistro);
        barraLoading = findViewById(R.id.pbLoading);
        tvMensaje = findViewById(R.id.tvMansaje);

        loginVM.getTvMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvMensaje.setText(s);
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Ignorar
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Ignorar
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginVM.inputsCambian(etEmail, etContrasena, botonLogin);
            }
        };
        etEmail.addTextChangedListener(afterTextChangedListener);
        etContrasena.addTextChangedListener(afterTextChangedListener);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                loginVM.login(etEmail.getText().toString(), etContrasena.getText().toString(), barraLoading);
                break;
            case  R.id.btnRegistro:
                loginVM.registro();
                break;
        }
    }
}
