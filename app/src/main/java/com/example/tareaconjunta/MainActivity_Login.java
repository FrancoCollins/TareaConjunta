package com.example.tareaconjunta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity_Login extends AppCompatActivity {


    private Button botonPulsame;
    private TextView labelUsuario;
    private TextView labelPassword;
    private TextView mensajeEmergente;
    private EditText textoPasssword;
    private EditText textoUsuario;
    private ArrayList<Usuario> usuariosRegistrados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        usuariosRegistrados = new ArrayList<>();
        usuariosRegistrados.add(new Usuario("Franco", "admin", "casa"));
        usuariosRegistrados.add(new Usuario("Pepe", "admin1", "casa mia"));
        usuariosRegistrados.add(new Usuario("Federico", "admin2","donde vivo?"));
        usuariosRegistrados.add(new Usuario("Felipe", "admin3" , "paseo de eso"));
        usuariosRegistrados.add(new Usuario("Facundo1", "admin4", "programar me gusta"));
        usuariosRegistrados.add(new Usuario("admin", "admin", "direccion"));

        botonPulsame = findViewById(R.id.botonIniciar);
        textoUsuario = findViewById(R.id.textoInicioSesion);
        labelUsuario = findViewById(R.id.labelInicioSesion);
        textoPasssword = findViewById(R.id.textoPass);
        labelPassword = findViewById(R.id.labelPass);
        mensajeEmergente = findViewById(R.id.mensajeEmergente);


        botonPulsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.d("MainActivity_Login", "Pasando a la siguiente actividad");

                Intent intent = new Intent(MainActivity_Login.this, MainActivity_Pizzeria.class);

                String usuario = String.valueOf(textoUsuario.getText());
                String pass = String.valueOf(textoPasssword.getText());
                boolean correcto = false;

                for (Usuario usuarioRegistrado : usuariosRegistrados) {
                    if (usuarioRegistrado.getUsuario().equals(usuario) && usuarioRegistrado.getPassword().equals(pass)) {
                        mensajeEmergente.setText("Credenciales correctas, pasamos a iniciar sesion");
                        String direccion = usuarioRegistrado.getDireccion();
                        mensajeEmergente.setTextColor(Color.GREEN);
                        mensajeEmergente.setVisibility(View.VISIBLE);
                        Usuario usuarioObj = new Usuario(usuario, pass, direccion);
                        intent.putExtra("Usuario", usuario);
                        intent.putExtra("Password", pass);
                        intent.putExtra("direccion", usuarioRegistrado.getDireccion());
                        intent.putExtra("usuario", usuarioObj);
                        startActivity(intent);
                        correcto = true;
                    }
                    if(!correcto){
                        System.out.println(usuario + " " + pass);
                        mensajeEmergente.setText("Credenciales incorrectas incorrectas");
                        mensajeEmergente.setTextColor(Color.RED);
                        mensajeEmergente.setVisibility(View.VISIBLE);
                    }
                }


                //Decimos a android que vaya a dicha Activity
            }
        });
    }
}