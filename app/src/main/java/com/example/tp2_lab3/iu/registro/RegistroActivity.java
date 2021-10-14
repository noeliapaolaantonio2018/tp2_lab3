package com.example.tp2_lab3.iu.registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tp2_lab3.R;
import com.example.tp2_lab3.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private RegistroViewModel registroVM;
    private EditText etRegistroDni, etRegistroApellido, etRegistroNombre, etRegistroCorreo, etRegistroContrasena;
    private Button btnRegistroGuardar;
    private TextView tvGuardarUsuario;
    Bundle datos;
    private Usuario usuario;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        registroVM = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
        datos = getIntent().getExtras();
        inicializarVistas();
    }
    private void inicializarVistas() {

        tvGuardarUsuario = findViewById(R.id.tvMensajeGuardar);
        etRegistroDni = findViewById(R.id.etUsuarioDni);
        etRegistroApellido = findViewById(R.id.etUsuarioApellido);
        etRegistroNombre = findViewById(R.id.etUsuarioNombre);
        etRegistroCorreo = findViewById(R.id.etUsuarioEmail);
        etRegistroContrasena = findViewById(R.id.etUsuarioContraseña);
        btnRegistroGuardar = findViewById(R.id.btnGuardarUsuario);


        registroVM.getMensajeUsuarioGuardarMutableLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                tvGuardarUsuario.setText(s);
            }
        });
        registroVM.getDniMLD().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                etRegistroDni.setText(""+ integer);
            }
        });
        registroVM.getApellidoMLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etRegistroApellido.setText(s);
            }
        });
        registroVM.getNombreMLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etRegistroNombre.setText(s);
            }
        });
        registroVM.getEmailMLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etRegistroCorreo.setText(s);
            }
        });
        registroVM.getContrasenaMLD().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                etRegistroContrasena.setText(s);
            }
        });
        btnRegistroGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registroVM.guardarUsuario(etRegistroDni, etRegistroApellido, etRegistroNombre, etRegistroCorreo, etRegistroContrasena);
            }
         });

        if(datos != null){ registroVM.cargarDatos();
        }
    }
}

