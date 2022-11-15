package com.example.tareaconjunta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    private HashMap<Double, CheckBox> valores;
    private TextView valor;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        valor = findViewById(R.id.etiquetaOculta);
        direccion = findViewById(R.id.entradaDireccion);
        nombre = findViewById(R.id.entradaNombre);

        tamano = findViewById(R.id.radioGroup);

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
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        nombre.setText(usuario.getUsuario());

        calcularPedido.setOnClickListener(view -> {
            Log.d("MainActivity_Pizzeria", "Pasando a la siguiente actividad");
            Intent intent = new Intent(MainActivity_Pizzeria.this, MainActivity_Pedido.class);
            Pizza pizza = new Pizza();
            String recuperarNombre = String.valueOf(nombre.getText());
            String recuperarDire = String.valueOf(direccion.getText());
            double precio = comprobarTamano(pizza);
            if (precio > 0) {
                pizza.setPrecioTamano(precio);
                precio += sumarIngredientes(pizza);
                intent.putExtra("nombre", recuperarNombre);
                intent.putExtra("direccion", recuperarDire);
                intent.putExtra("precio", precio);
                intent.putExtra("Pizza", pizza);
                intent.putExtra("usuario", usuario);
                valor.setVisibility(View.INVISIBLE);
                startActivity(intent);
            } else {
                valor.setText("Ingrese datos para calcular el valor de la pizza");
                valor.setVisibility(View.VISIBLE);
            }
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

}