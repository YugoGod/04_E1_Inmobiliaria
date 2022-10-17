package com.example.a04_creacionelementosporcodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.a04_creacionelementosporcodigo.databinding.ActivityAddPisoBinding;
import com.example.a04_creacionelementosporcodigo.modelos.Alumno;
import com.example.a04_creacionelementosporcodigo.modelos.Piso;

public class AddPisoActivity extends AppCompatActivity {

    //  1. Activa el Binding para la Activity
    private ActivityAddPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Construye el Binding
        binding = ActivityAddPisoBinding.inflate(getLayoutInflater());

        // Asocia el Binding al Activity
        setContentView(binding.getRoot());

        binding.btnCancelarAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnCrearAddAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso piso = createPiso();
                if (piso != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PISO", piso);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(AddPisoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private Piso createPiso() {
        if (binding.txtCiudadAddPiso.getText().toString().isEmpty() || binding.txtCPAddPiso.getText().toString().isEmpty() || binding.txtNumeroAddPiso.getText().toString().isEmpty() || binding.txtDireccionAddPiso.getText().toString().isEmpty()){
            return null;
        }

        return new Piso(binding.txtDireccionAddPiso.getText().toString(), Integer.parseInt(binding.txtNumeroAddPiso.getText().toString()), binding.txtCiudadAddPiso.getText().toString(), binding.txtProvinciaAddPiso.getText().toString(),binding.txtCPAddPiso.getText().toString(), binding.rbAddPiso.getRating());
    }
}