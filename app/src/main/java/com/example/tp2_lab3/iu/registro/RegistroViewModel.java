package com.example.tp2_lab3.iu.registro;

import android.app.Application;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.tp2_lab3.model.Usuario;
import com.example.tp2_lab3.request.ApiClient;

public class RegistroViewModel extends AndroidViewModel {

    private MutableLiveData<String> mensajeUsuarioGuardarMutableLiveData;
    private MutableLiveData<Integer> dniMLD;
    private MutableLiveData<String> apellidoMLD;
    private MutableLiveData<String> nombreMLD;
    private MutableLiveData<String> emailMLD;
    private MutableLiveData<String> contrasenaMLD;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMensajeUsuarioGuardarMutableLiveData(){
        if(mensajeUsuarioGuardarMutableLiveData == null){
            mensajeUsuarioGuardarMutableLiveData = new MutableLiveData<>();
        }
        return mensajeUsuarioGuardarMutableLiveData;
    }
    public LiveData<Integer> getDniMLD(){
        if(dniMLD == null){
            dniMLD = new MutableLiveData<>();
        }
        return dniMLD;
    }
    public LiveData<String> getApellidoMLD(){
        if(apellidoMLD == null){
            apellidoMLD = new MutableLiveData<>();
        }
        return apellidoMLD;
    }
    public LiveData<String> getNombreMLD(){
        if(nombreMLD == null){
            nombreMLD = new MutableLiveData<>();
        }
        return nombreMLD;
    }
    public LiveData<String> getEmailMLD(){
        if(emailMLD == null){
            emailMLD = new MutableLiveData<>();
        }
        return emailMLD;
    }
    public LiveData<String> getContrasenaMLD(){
        if(contrasenaMLD == null){
            contrasenaMLD = new MutableLiveData<>();
        }
        return contrasenaMLD;
    }

    public void guardarUsuario(EditText etDni, EditText etApellido, EditText etNombre, EditText etEmail, EditText etContrasena){
        if("".equals(etDni.getText().toString()) ||"".equals(etApellido.getText().toString()) ||"".equals(etNombre.getText().toString()) ||"".equals(etEmail.getText().toString()) ||"".equals(etContrasena.getText().toString())) {
            mensajeUsuarioGuardarMutableLiveData.setValue("Complete el formulario correctamente");
        } else {
            Usuario usuario = new Usuario(Integer.parseInt(etDni.getText().toString()), etApellido.getText().toString(), etNombre.getText().toString(), etEmail.getText().toString(), etContrasena.getText().toString());
            ApiClient.guardar(getApplication(), usuario);
            etDni.setText("");
            etApellido.setText("");
            etNombre.setText("");
            etEmail.setText("");
            etContrasena.setText("");
            mensajeUsuarioGuardarMutableLiveData.setValue("Usuario Guardado");
        }
    }
    public void cargarDatos() {
        Usuario usuario = ApiClient.leer(getApplication());
        dniMLD.setValue(usuario.getDni());
        apellidoMLD.setValue(usuario.getApellido());
        nombreMLD.setValue(usuario.getNombre());
        emailMLD.setValue(usuario.getEmail());
        contrasenaMLD.setValue(usuario.getContrasena());
    }
}