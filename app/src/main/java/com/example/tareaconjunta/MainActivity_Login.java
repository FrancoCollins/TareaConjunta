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

public class MainActivity_Login extends AppCompatActivity {


    private Button botonPulsame;
    private TextView labelUsuario;
    private TextView labelPassword;
    private TextView mensajeEmergente;
    private EditText textoPasssword;
    private EditText textoUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        botonPulsame = findViewById(R.id.botonIniciar);
        textoUsuario = findViewById(R.id.textoInicioSesion);
        labelUsuario = findViewById(R.id.labelInicioSesion);
        textoPasssword = findViewById(R.id.textoPass);
        labelPassword = findViewById(R.id.labelPass);
        mensajeEmergente = findViewById(R.id.mensajeEmergente);

        //Vamos a meterle logica al boton para que cuando le pulsemos nos cambie
        // compruebe el login
        botonPulsame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //La clase Log te permite crear mensajes del registro que se
                // muestran en logcat. Por lo general, debes usar los siguientes
                // métodos de registro, que se ordenan de mayor a menor prioridad
                // (o del más detallado al menos detallado).

                // Log.e(String, String) (error)
                // Log.w(String, String) (advertencia)
                // Log.i(String, String) (información)
                // Log.d(String, String) (depuración)
                // Log.v(String, String) (registro detallado)

                Log.d("MainActivity", "Pasando a la siguiente actividad");

                Intent intent = new Intent(MainActivity_Login.this, MainActivity_Pizzeria.class);
                //Si la actividad tiene un intent-filter, podemos lanzar la intención de la siguiente
                //manera:
                //Intent intent = new Intent("com.example.a04_intenciones.SecondActivity");
                //Esto puede servir para llamar actividades desde diferentes aplicaciones en las que
                //no tenemos su clase. El intent filter tenemos que añadirlo nosotros

                String usuario = String.valueOf(textoUsuario.getText());
                String pass = String.valueOf(textoPasssword.getText());

                if (usuario.equals("Franco") && pass.equals("123")) {
                    mensajeEmergente.setText("Credenciales correctas, pasamos a iniciar sesion");
                    mensajeEmergente.setTextColor(Color.GREEN);
                    mensajeEmergente.setVisibility(View.VISIBLE);
                    Usuario usuarioObj = new Usuario(usuario, pass);
                    intent.putExtra("Usuario", usuario);
                    intent.putExtra("Password", pass);
                    intent.putExtra("Objeto",usuarioObj) ;
                    startActivity(intent);
                } else {
                    System.out.println(usuario + " " + pass);
                    mensajeEmergente.setText("Credenciales incorrectas incorrectas");
                    mensajeEmergente.setTextColor(Color.RED);
                    mensajeEmergente.setVisibility(View.VISIBLE);
                }


                //Decimos a android que vaya a dicha Activity
            }
        });
    }
}