package com.example.a04_creacionelementosporcodigo;

import android.content.Intent;
import android.os.Bundle;

import com.example.a04_creacionelementosporcodigo.databinding.ActivityMainBinding;
import com.example.a04_creacionelementosporcodigo.modelos.Piso;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // 1. Contenedor donde Mostrar Información -> Scroll con Linear dentro.

    // 2. Lógica para pintar los elementos -> pintarElementos();.
    // 3. Conjunto de datos.
    // 4. Plantilla para los datos.
    private ArrayList<Piso> pisoList;

    private ActivityResultLauncher<Intent> launcherCrearPisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        pisoList = new ArrayList<>();
        
        inicializaLaunchers();

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearPisos.launch(new Intent(MainActivity.this, AddPisoActivity.class));
            }
        });
    }

    private void inicializaLaunchers() {
        launcherCrearPisos = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK){
                    if (result.getData() != null && result.getData().getExtras() != null){
                        Piso piso = (Piso) result.getData().getExtras().getSerializable("PISO");
                        pisoList.add(piso);
                        Toast.makeText(MainActivity.this, pisoList.get(0).toString() + "", Toast.LENGTH_SHORT).show();
                        pintarElementos();
                    }
                }
            }
        });
    }

    private void pintarElementos() {
        binding.content.contenedor.removeAllViews();
        for (Piso piso : pisoList) {
            View alumnoView = LayoutInflater.from(MainActivity.this).inflate(R.layout.piso_model_view, null);
            TextView lblCalle = alumnoView.findViewById(R.id.lblCallePisoView);
            TextView lblNumero = alumnoView.findViewById(R.id.lblNumeroPisoView);
            TextView lblProvincia = alumnoView.findViewById(R.id.lblProvinciaPisoView);
            RatingBar rbValoracion = alumnoView.findViewById(R.id.rbValoracionPisoView);

            lblCalle.setText(piso.getDireccion());
            lblNumero.setText(piso.getNumero() + "");
            lblProvincia.setText(piso.getProvincia());
            rbValoracion.setRating(piso.getValoracion());

            binding.content.contenedor.addView(alumnoView);
        }
    }
}