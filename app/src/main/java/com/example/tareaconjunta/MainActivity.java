package com.example.tareaconjunta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private Button calcularPedido;
    private EditText direccion;
    private EditText nombre;
    private RadioGroup tamano;
    private HashMap<Integer, CheckBox> ingredientes;
    private TextView pedidoNombre;
    private TextView pedidoDireccion;
    private TextView precioTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}