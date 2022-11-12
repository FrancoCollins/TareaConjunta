package com.example.tareaconjunta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity_Pedido extends AppCompatActivity {

    private TextView pedidoNombre;
    private TextView pedidoDireccion;
    private TextView precioTotal;
    private String nombre;
    private String direccion;
    private Button confirmar;
    private Button modificar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pedido);
        confirmar = findViewById(R.id.confirmar_pedido);
        modificar = findViewById(R.id.Modificar_pedido);
        pedidoDireccion = findViewById(R.id.salidaDireccion);
        pedidoNombre = findViewById(R.id.salidaNombre);
        precioTotal = findViewById(R.id.salidaPrecio);
        TextView etiquetaDireccion = findViewById(R.id.labelSalidaD);
        TextView etiquetaNombre = findViewById(R.id.labelSalidaN);
        TextView etiquetaPrecio = findViewById(R.id.labelSalidaP);
        TextView tituloPedido = findViewById(R.id.titulosDatosCompra);
        nombre = getIntent().getStringExtra("nombre");
        direccion = getIntent().getStringExtra("direccion");
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity_Pedido.this, MainActivity_Pizzeria.class);
                Usuario usuario = (Usuario) getIntent().getSerializableExtra("usuario");
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            }
        });


        pedidoDireccion.setText(direccion);
        pedidoNombre.setText(nombre);
        Double precio = getIntent().getDoubleExtra("precio", 0);
        mostrarPedido(precio);
    }

    private void mostrarPedido(double precio) {
        String error = String.format(Locale.ENGLISH, "%.2f€", precio);
        if (precio > 0) precioTotal.setText(error);
    }

}