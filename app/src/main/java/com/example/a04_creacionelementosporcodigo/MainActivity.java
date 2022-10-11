package com.example.a04_creacionelementosporcodigo;

import android.content.Intent;
import android.os.Bundle;

import com.example.a04_creacionelementosporcodigo.modelos.Alumno;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.view.LayoutInflater;
import android.view.View;

import com.example.a04_creacionelementosporcodigo.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // 1. Contenedor donde Mostrar Información -> Scroll con Linear dentro.

    // 2. Lógica para pintar los elementos -> pintarElementos();.
    // 3. Conjunto de datos.
    // 4. Plantilla para los datos.
    private ArrayList<Alumno> alumnoList;

    private ActivityResultLauncher<Intent> launcherCrearAlumnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        alumnoList = new ArrayList<>();
        
        inicializaLaunchers();

        setSupportActionBar(binding.toolbar);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcherCrearAlumnos.launch(new Intent(MainActivity.this, AddAlumnoActivity.class));
            }
        });
    }

    private void inicializaLaunchers() {
        launcherCrearAlumnos = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK){
                    if (result.getData() != null && result.getData().getExtras() != null){
                        Alumno alumno = (Alumno) result.getData().getExtras().getSerializable("ALUMNO");
                        alumnoList.add(alumno);
                        Toast.makeText(MainActivity.this, alumnoList.size() + "", Toast.LENGTH_SHORT).show();
                        pintarElementos();
                    }
                }
            }
        });
    }

    private void pintarElementos() {
        binding.content.contenedor.removeAllViews();
        for (Alumno alumno : alumnoList) {
            View alumnoView = LayoutInflater.from(MainActivity.this).inflate(R.layout.alumno_model_view, null);
            TextView lblNombre = alumnoView.findViewById(R.id.lblNombreAlumnoView);
            TextView lblApellidos = alumnoView.findViewById(R.id.lblApellidosAlumnoView);
            TextView lblCiclo = alumnoView.findViewById(R.id.lblCicloAlumnoView);
            TextView lblGrupo = alumnoView.findViewById(R.id.lblGrupoAlumnoView);

            lblNombre.setText(alumno.getNombre());
            lblApellidos.setText(alumno.getApellidos());
            lblCiclo.setText(alumno.getCiclo());
            lblGrupo.setText(String.valueOf(alumno.getGrupo()));

            binding.content.contenedor.addView(alumnoView);
        }
    }
}