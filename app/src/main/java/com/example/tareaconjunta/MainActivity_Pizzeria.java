package com.example.tareaconjunta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity_Pizzeria extends AppCompatActivity {

    private EditText direccion;
    private EditText nombre;
    private RadioGroup tamano;
    private TextView pedidoNombre;
    private TextView pedidoDireccion;
    private TextView precioTotal;
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
        Intent intent = new Intent(MainActivity_Pizzeria.this, MainActivity_Pedido.class);
        setContentView(R.layout.activity_main_pizzeria);
        CheckBox pina = findViewById(R.id.pina);
        CheckBox tomate = findViewById(R.id.tomate);
        CheckBox mozza = findViewById(R.id.mozzarella);
        CheckBox emmen = findViewById(R.id.emmental);
        CheckBox aceituna = findViewById(R.id.aceitunas);
        CheckBox atun = findViewById(R.id.atun);
        CheckBox berenjena = findViewById(R.id.berenjena);
        CheckBox salmon = findViewById(R.id.salmon);
        CheckBox pepperoni = findViewById(R.id.pepperoni);
        Button calcularPedido = findViewById(R.id.realizarPedido);
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
        Usuario usuario = (Usuario) getIntent().getSerializableExtra("Objeto");
        nombre.setText(usuario.getUsuario());

        calcularPedido.setOnClickListener(view -> {
            Pizza pizza = new Pizza();
            String recuperarNombre = String.valueOf(nombre.getText());
            pedidoNombre.setText(recuperarNombre);
            pedidoNombre.setVisibility(View.VISIBLE);
            String recuperarDire = String.valueOf(direccion.getText());
            pedidoDireccion.setText(recuperarDire);
            pedidoDireccion.setVisibility(View.VISIBLE);
            double precio = comprobarTamano(pizza);
            if (precio > 0) {
                precio += sumarIngredientes(pizza);
            } else {
                precioTotal.setText(R.string.faltaIngrediente);
            }
            intent.putExtra("precio", precio);
            intent.putExtra("Pizza", pizza);
            mostrarPedido(precio);

        });

    }

    private double sumarIngredientes(Pizza pizza) {
        double ingredientes = 0;
        for (Map.Entry<Double, CheckBox> entry : valores.entrySet()
        ) {
            if (entry.getValue().isChecked()) {
                ingredientes += entry.getKey();
                pizza.getIngredientes().put(String.valueOf(entry.getValue().getText()), entry.getKey());
            }
        }
        return ingredientes;
    }

    private double comprobarTamano(Pizza pizza) {
        int id = tamano.getCheckedRadioButtonId();
        View radioButton = tamano.findViewById(id);
        int indice = tamano.indexOfChild(radioButton);
        RadioButton rb = (RadioButton) tamano.getChildAt(indice);
        double precio = 0;
        if (rb != null) {
            if (rb.getText().equals("pequeña")) {
                precio = 5;
                pizza.setPrecioTamano(precio);
            } else if (rb.getText().equals("mediana")) {
                precio = 10;
                pizza.setPrecioTamano(precio);
            } else {
                pizza.setPrecioTamano(precio);
                precio = 15;
            }
        }
        return precio;
    }

    private void mostrarPedido(double precio) {
        String error = String.format(Locale.ENGLISH, "%.2f", precio);
        if (precio > 0) precioTotal.setText(error);
        etiquetaPrecio.setVisibility(View.VISIBLE);
        etiquetaNombre.setVisibility(View.VISIBLE);
        etiquetaDireccion.setVisibility(View.VISIBLE);
        tituloPedido.setVisibility(View.VISIBLE);
        lineaSuperior.setVisibility(View.VISIBLE);
        lineaInferior.setVisibility(View.VISIBLE);
    }
}