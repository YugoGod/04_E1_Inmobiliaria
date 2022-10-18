package com.example.a04_creacionelementosporcodigo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.a04_creacionelementosporcodigo.databinding.ActivityAddPisoBinding;
import com.example.a04_creacionelementosporcodigo.databinding.ActivityEditPisoBinding;
import com.example.a04_creacionelementosporcodigo.modelos.Piso;

public class EditPisoActivity extends AppCompatActivity {


    //  1. Activa el Binding para la Activity
    private ActivityEditPisoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Construye el Binding
        binding = ActivityEditPisoBinding.inflate(getLayoutInflater());

        // Asocia el Binding al Activity
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Piso p = (Piso) bundle.getSerializable("PISO");
        int n = bundle.getInt("NUM");

        binding.txtCiudadEditPiso.setText(p.getCiudad());
        binding.txtCPEditPiso.setText(p.getCp());
        binding.txtDireccionEditPiso.setText(p.getDireccion());
        binding.txtProvinciaEditPiso.setText(p.getProvincia());
        binding.txtNumeroEditPiso.setText(p.getNumero() + "");
        binding.rbEditPiso.setRating(p.getValoracion());

        binding.btnCancelarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        binding.btnEditarEditAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Piso piso = createPiso();
                if (piso != null){
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("PISO", piso);
                    bundle.putInt("NUM", n);
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(EditPisoActivity.this, "FALTAN DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private Piso createPiso() {
        if (binding.txtCiudadEditPiso.getText().toString().isEmpty() || binding.txtCPEditPiso.getText().toString().isEmpty() || binding.txtNumeroEditPiso.getText().toString().isEmpty() || binding.txtDireccionEditPiso.getText().toString().isEmpty()){
            return null;
        }

        return new Piso(binding.txtDireccionEditPiso.getText().toString(), Integer.parseInt(binding.txtNumeroEditPiso.getText().toString()), binding.txtCiudadEditPiso.getText().toString(), binding.txtProvinciaEditPiso.getText().toString(),binding.txtCPEditPiso.getText().toString(), binding.rbEditPiso.getRating());
    }
}