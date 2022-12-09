package com.example.miconsumo20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miconsumo20.Room.PlasticoDao;
import com.example.miconsumo20.Room.PlasticoDataBase;
import com.example.miconsumo20.Room.Plasticos;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener{

    EditText txtNombre, txtDescripcion, txtUsuario, txtOrigen, txtUbicacion, txtCategoria, txtFotografia;
    Button btnGuardar, btnActualizar;
    int idActualizar = 0;
    RecyclerView plasticosRecycler;

    private PlasticoDataBase plasticoDataBase;
    private PlasticoDao plasticoDao;

    private PlasticoAdapter plasticoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Room Database
        plasticoDataBase = PlasticoDataBase.getInstance(this);
        plasticoDao = plasticoDataBase.getDao();

        txtNombre = findViewById(R.id.txtNombre);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtOrigen = findViewById(R.id.txtOrigen);
        txtUbicacion = findViewById(R.id.txtUbicacion);
        txtCategoria = findViewById(R.id.txtCategoria);
        txtFotografia = findViewById(R.id.txtFotografia);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnActualizar = findViewById(R.id.btnActualizar);
        plasticosRecycler = findViewById(R.id.plasticosRecycler);

        plasticoAdapter = new PlasticoAdapter(this, this);
        plasticosRecycler.setAdapter(plasticoAdapter);
        plasticosRecycler.setLayoutManager(new LinearLayoutManager(this));

        fetchData();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nombre = txtNombre.getText().toString();
                String descripcion = txtDescripcion.getText().toString();
                String usuario = txtUsuario.getText().toString();
                String origen = txtOrigen.getText().toString();
                String ubicacion = txtUbicacion.getText().toString();
                String categoria = txtCategoria.getText().toString();
                String fotografia = txtFotografia.getText().toString();

                Plasticos plasticos = new Plasticos(0,nombre,descripcion,usuario,origen,ubicacion,categoria,fotografia);
                plasticoAdapter.addPlastico(plasticos);
                plasticoDao.insert(plasticos);

                txtNombre.setText("");
                txtDescripcion.setText("");
                txtUsuario.setText("");
                txtOrigen.setText("");
                txtUbicacion.setText("");
                txtCategoria.setText("");
                txtFotografia.setText("");

                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //txtNombre.setText("Hola actualizar");
                //txtDescripcion.setText(String.valueOf(idActualizar));
                String nombre = txtNombre.getText().toString();
                String descripcion = txtDescripcion.getText().toString();
                String usuario = txtUsuario.getText().toString();
                String origen = txtOrigen.getText().toString();
                String ubicacion = txtUbicacion.getText().toString();
                String categoria = txtCategoria.getText().toString();
                String fotografia = txtFotografia.getText().toString();

                Plasticos plasticosActualizar = new Plasticos(idActualizar,nombre,descripcion,usuario,origen,ubicacion,categoria,fotografia);
                plasticoDao.update(plasticosActualizar);

                txtNombre.setText("");
                txtDescripcion.setText("");
                txtUsuario.setText("");
                txtOrigen.setText("");
                txtUbicacion.setText("");
                txtCategoria.setText("");
                txtFotografia.setText("");
            }
        });

    }

    private void fetchData(){
        plasticoAdapter.clearData();
        List<Plasticos> plasticosList = plasticoDao.getAllPlasticos();
        for (int i = 0; i < plasticosList.size(); i++){
            Plasticos plasticos = plasticosList.get(i);
            plasticoAdapter.addPlastico(plasticos);
        }
    }

    @Override
    public void OnUpdate(int id, int pos) {
        //Prueba
        txtNombre.setText(plasticoAdapter.getItem(pos).getNombre());
        txtDescripcion.setText(plasticoAdapter.getItem(pos).getDescripcion());
        txtUsuario.setText(plasticoAdapter.getItem(pos).getUsuario());
        txtOrigen.setText(plasticoAdapter.getItem(pos).getOrigen());
        txtUbicacion.setText(plasticoAdapter.getItem(pos).getUbicacion());
        txtCategoria.setText(plasticoAdapter.getItem(pos).getCategoria());
        txtFotografia.setText(plasticoAdapter.getItem(pos).getFotografia());

        idActualizar = id;
    }

    @Override
    public void OnDelete(int id, int pos) {
        plasticoDao.delete(id);
        plasticoAdapter.removePlastico(pos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
}