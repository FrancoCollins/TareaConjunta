package com.example.tareaconjunta;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button calcularPedido;
    private EditText direccion;
    private EditText nombre;
    private RadioGroup tamano;
    private TextView pedidoNombre;
    private TextView pedidoDireccion;
    private TextView precioTotal;
    private CheckBox tomate;
    private CheckBox mozza;
    private CheckBox emmen;
    private CheckBox atun;
    private CheckBox berenjena;
    private CheckBox salmon;
    private CheckBox pina;
    private CheckBox pepperoni;
    private CheckBox aceituna;
    private TextView etiquetaNombre;
    private TextView etiquetaDireccion;
    private TextView etiquetaPrecio;
    private TextView tituloPedido;
    private View lineaSuperior;
    private View lineaInferior;
    private HashMap<Double, CheckBox> valores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pina = findViewById(R.id.pina);
        tomate = findViewById(R.id.tomate);
        mozza = findViewById(R.id.mozzarella);
        emmen = findViewById(R.id.emmental);
        aceituna = findViewById(R.id.aceitunas);
        atun = findViewById(R.id.atun);
        berenjena = findViewById(R.id.berenjena);
        salmon = findViewById(R.id.salmon);
        pepperoni = findViewById(R.id.pepperoni);
        calcularPedido = findViewById(R.id.realizarPedido);
        direccion = findViewById(R.id.entradaDireccion);
        nombre = findViewById(R.id.entradaNombre);
        pedidoDireccion = findViewById(R.id.salidaDireccion);
        pedidoNombre = findViewById(R.id.salidaNombre);
        precioTotal = findViewById(R.id.salidaPrecio);
        tamano = findViewById(R.id.radioGroup);
        etiquetaDireccion = findViewById(R.id.labelSalidaD);
        etiquetaNombre = findViewById(R.id.labelSalidaN);
        etiquetaPrecio = findViewById(R.id.labelSalidaP);
        tituloPedido = findViewById(R.id.titulosDatosCompra);
        lineaInferior = findViewById(R.id.barraInferiorEscondida);
        lineaSuperior = findViewById(R.id.barraSuperiorEscondida);
        valores = new HashMap<>();
        valores.put(1.5, tomate);
        valores.put(1.6, mozza);
        valores.put(1.8, emmen);
        valores.put(1.7, atun);
        valores.put(1.9, salmon);
        valores.put(2.05, pepperoni);
        valores.put(2.15, pina);
        valores.put(2.25, aceituna);
        valores.put(2.75, berenjena);
        calcularPedido.setBackgroundColor(8396569);

        calcularPedido.setOnClickListener(view -> {
            String recuperarNombre = String.valueOf(nombre.getText());
            pedidoNombre.setText(recuperarNombre);
            pedidoNombre.setVisibility(View.VISIBLE);
            String recuperarDire = String.valueOf(direccion.getText());
            pedidoDireccion.setText(recuperarDire);
            pedidoDireccion.setVisibility(View.VISIBLE);
            double precio = comprobarTamano();
            precio += sumarIngredientes();
            mostrarPedido(precio);

        });

    }

    private double sumarIngredientes() {
        double ingredientes = 0;
        for (Map.Entry<Double, CheckBox> entry : valores.entrySet()
        ) {
            if (entry.getValue().isChecked()) {
                ingredientes += entry.getKey();
            }
        }
        return ingredientes;
    }

    private double comprobarTamano() {
        int id = tamano.getCheckedRadioButtonId();
        View radioButton = tamano.findViewById(id);
        int indice = tamano.indexOfChild(radioButton);
        RadioButton rb = (RadioButton) tamano.getChildAt(indice);
        double precio = 0;
        if (rb != null) {
            if (rb.getText().equals("pequena")) {
                precio = 5;
            } else if (rb.getText().equals("mediana")) {
                precio = 10;
            } else {
                precio = 15;
            }
        }
        return precio;
    }

    private void mostrarPedido(double precio) {
        precioTotal.setText(String.valueOf(String.format("%.2f", precio)) + "€");
        etiquetaPrecio.setVisibility(View.VISIBLE);
        etiquetaNombre.setVisibility(View.VISIBLE);
        etiquetaDireccion.setVisibility(View.VISIBLE);
        tituloPedido.setVisibility(View.VISIBLE);
        lineaSuperior.setVisibility(View.VISIBLE);
        lineaInferior.setVisibility(View.VISIBLE);
    }
}