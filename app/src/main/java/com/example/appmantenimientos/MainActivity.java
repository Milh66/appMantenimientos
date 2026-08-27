package com.example.appmantenimientos;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText txtcodigo, txtProducto, txtPrecio, txtCantidad;
    private Button btnGrabar, btnEditar, btnEliminar, btnNuevo;
    private ListView ListProforma;
    private TextView txtResultado;

    ArrayList<ProformaItem> lista = new ArrayList<>();
    ArrayAdapter<ProformaItem> adaptador;
    int posicionSeleccionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Inicio Programacion - Referencia de Vistas
        txtcodigo = findViewById(R.id.txtcodigo);
        txtProducto = findViewById(R.id.txtProducto);
        txtPrecio = findViewById(R.id.txtPrecio);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtResultado = findViewById(R.id.txtResultado);

        btnNuevo = findViewById(R.id.btnNuevo);
        btnGrabar = findViewById(R.id.btnGrabar);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);

        ListProforma = findViewById(R.id.ListProforma);

        // Adaptador para el ListView
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        ListProforma.setAdapter(adaptador);

        // Botón NUEVO
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCampos();
                txtcodigo.requestFocus(); // Cursor en el primer campo
            }
        });

        // Botón GRABAR
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dni = txtcodigo.getText().toString();
                String prod = txtProducto.getText().toString();
                double precio = Double.parseDouble(txtPrecio.getText().toString());
                int cant = Integer.parseInt(txtCantidad.getText().toString());

                // Operación Aritmética
                double total = precio * cant;
                txtResultado.setText("Total: S/. " + total);

                lista.add(new ProformaItem(dni, prod, precio, cant));
                adaptador.notifyDataSetChanged();
                limpiarCampos();
            }
        });

        // Seleccionar ítem del ListView
        ListProforma.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                posicionSeleccionada = position;
                ProformaItem item = lista.get(position);

                txtcodigo.setText(item.getCodigo());
                txtProducto.setText(item.getProducto());
                txtPrecio.setText(String.valueOf(item.getPrecio()));
                txtCantidad.setText(String.valueOf(item.getCantidad()));
                txtResultado.setText("Total: S/. " + item.getTotal());
            }
        });

        // Botón EDITAR (ACTUA.)
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posicionSeleccionada != -1) {
                    ProformaItem item = lista.get(posicionSeleccionada);
                    item.setProducto(txtProducto.getText().toString());
                    item.setPrecio(Double.parseDouble(txtPrecio.getText().toString()));
                    item.setCantidad(Integer.parseInt(txtCantidad.getText().toString()));

                    adaptador.notifyDataSetChanged();
                    limpiarCampos();
                }
            }
        });

        // Botón ELIMINAR
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posicionSeleccionada != -1) {
                    lista.remove(posicionSeleccionada);
                    adaptador.notifyDataSetChanged();
                    limpiarCampos();
                }
            }
        });

        // Ajuste de márgenes del sistema (EdgeToEdge)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Método para limpiar campos del formulario
    private void limpiarCampos() {
        txtcodigo.setText("");
        txtProducto.setText("");
        txtPrecio.setText("");
        txtCantidad.setText("");
        txtResultado.setText("Total: S/. 0.00");
        posicionSeleccionada = -1;
    }
}