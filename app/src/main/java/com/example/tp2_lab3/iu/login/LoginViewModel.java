package com.example.tp2_lab3.iu.login;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2_lab3.iu.registro.RegistroActivity;
import com.example.tp2_lab3.model.Usuario;
import com.example.tp2_lab3.request.ApiClient;

import java.util.Timer;
import java.util.TimerTask;


public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<String> tvMensaje;
    private Timer tiempoPaInicia;

    public LoginViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getTvMensaje(){
        if(tvMensaje == null){
            tvMensaje = new MutableLiveData<>();
        }
        return tvMensaje;
    }

    public void inputsCambian(EditText email, EditText contrasena, Button btnLogin) {

        String emailInput = email.getText().toString().trim();
        String contrasenaInput = contrasena.getText().toString().trim();
        String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (!emailInput.matches(emailPattern)){
            email.setError("Ingrese Email valido..");
            email.requestFocus();
            email.setTextColor(Color.rgb(255, 0, 0));
        } else email.setTextColor(Color.rgb(0, 255 , 0));
        if (contrasenaInput.length() < 5 ) {
            contrasena.setError("La Contraseña debe contener 5 o más caracteres..");
            contrasena.setTextColor(Color.rgb(255, 0, 0));
        } else
            contrasena.setTextColor(Color.rgb(0, 255 , 0)); btnLogin.setEnabled(true);
    }

    public void login(final String email, final String password, ProgressBar pbProges){

        final Usuario usuario = ApiClient.login(getApplication(), email, password);
        if(usuario==null)
        {
            tvMensaje.setValue("Datos Incorrectos");
            Toast.makeText(getApplication(),"Tu Contraseña o Email Son Incorrectos", Toast.LENGTH_LONG).show();
            pbProges.setVisibility(View.INVISIBLE);

        } else{
            tvMensaje.setValue("Iniciando Sesion...");
            pbProges.setVisibility(View.VISIBLE);
            tiempoPaInicia = new Timer();
            tiempoPaInicia.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent= new Intent(getApplication(), RegistroActivity.class);
                    intent.putExtra("respuesta", "verdadero");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(intent);
                }
            },2000);
         }
    }

    public void registro(){
        Intent i = new Intent(getApplication(), RegistroActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(i);
    }
}

