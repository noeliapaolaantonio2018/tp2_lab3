package com.example.tp2_lab3.request;

import android.content.Context;

import com.example.tp2_lab3.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ApiClient {

    private static File archivo;

    private static File conectar(Context context){
        if(archivo==null){
            archivo = new File(context.getFilesDir(), "usuario.dat");
        }
        return archivo;
    }

    public static void guardar(Context context, Usuario usuario){
        try(DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(conectar(context))))) {
            dataOutputStream.writeInt(usuario.getDni());
            dataOutputStream.writeUTF(usuario.getApellido());
            dataOutputStream.writeUTF(usuario.getNombre());
            dataOutputStream.writeUTF(usuario.getEmail());
            dataOutputStream.writeUTF(usuario.getContrasena());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally { }
    }

    public static Usuario leer(Context context){
        Usuario usuario = null;
        try(DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(conectar(context))))){
            int dni = dataInputStream.readInt();
            String apellido = dataInputStream.readUTF();
            String nombre = dataInputStream.readUTF();
            String email = dataInputStream.readUTF();
            String contrasena = dataInputStream.readUTF();
            usuario = new Usuario(dni,apellido,nombre,email,contrasena);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally { }
        return usuario;
    }

    public static Usuario login(Context context, String correo, String password){
        Usuario usuario = null;
        try(DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(conectar(context))))){
            int dni = dataInputStream.readInt();
            String apellido = dataInputStream.readUTF();
            String nombre = dataInputStream.readUTF();
            String email = dataInputStream.readUTF();
            String contrasena = dataInputStream.readUTF();
            if(correo.equals(email) & password.equals(contrasena)){
                usuario = new Usuario(dni,apellido,nombre,email,contrasena);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally { }
        return usuario;
    }

}