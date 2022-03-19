package com.example.dietareborn.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dietareborn.Utils.GlobalUserId;
import com.example.dietareborn.R;
import com.example.dietareborn.bd.Comida;
import com.example.dietareborn.bd.DataBase;
import com.example.dietareborn.bd.Dieta;

import java.util.List;

public class EditDieta extends AppCompatActivity {
    List<Comida> lista_comidas;
    Comida comida_select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_dieta);

        DataBase db;
        Button editarComida = findViewById(R.id.editarComida);
        Spinner spinnerSelectComida= findViewById(R.id.spinnerSelectComida);
        EditText qntComida = findViewById(R.id.qntComida);
        zeroFilter(qntComida);

        //db
        db = DataBase.getDatabase(getApplicationContext());
        int idUsuario = GlobalUserId.idUsuario;

        int idDieta = getIntent().getIntExtra("idDieta", 0);
        Dieta refeiçao_select = db.DietaDao().dietaEspecificaById(idDieta);
        int inputQntComidaInt = (int) refeiçao_select.getQuantidade();

        String inputQntComida = String.valueOf(inputQntComidaInt);
        qntComida.setText(inputQntComida);

        lista_comidas = db.ComidaDao().getComidaList();
        String[] Comidas = new String[lista_comidas.size()];

        for(int i = 0; i < lista_comidas.size(); i++) {
            Comidas[i] = lista_comidas.get(i).getNome();
        }

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, Comidas);
        spinnerSelectComida.setAdapter(spinnerAdapter);

        spinnerSelectComida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                comida_select = lista_comidas.get(i);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        editarComida.setOnClickListener(view -> {
            String inputQuantidade = qntComida.getText().toString();

            if(inputQuantidade.isEmpty()){
                Toast toast = Toast.makeText(getApplicationContext(), "Por favor, preencha a quantidade de comida.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
                showError(qntComida);
            } else{
                int caloriasComida = comida_select.getCal();
                int idComida = comida_select.getId();
                double multiplo = Integer.parseInt(inputQuantidade) / 100.0;
                System.out.println(multiplo);
                int caloriasUsuario = (int) Math.ceil(multiplo * caloriasComida);
                System.out.println(caloriasUsuario);

                // guardar informações novas
                Dieta dieta = refeiçao_select;

                dieta.setId_usuario(idUsuario);
                dieta.setId_comida(idComida);
                dieta.setCal(caloriasUsuario);
                dieta.setQuantidade(Integer.parseInt(inputQuantidade));

                db.DietaDao().updateDieta(dieta);

                Intent it1 = new Intent(getApplicationContext(), AlterarDieta.class);
                finish();
                startActivity(it1);
            }
        });
    }
    private void zeroFilter(EditText edtNumber){
        edtNumber.addTextChangedListener(new TextWatcher(){
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                if (edtNumber.getText().toString().matches("^0") )
                {
                    //não permite 0 no começo do edittext
                    edtNumber.setText("");
                }
            }
            @Override
            public void afterTextChanged(Editable arg0) { }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        });
    }
    private void showError(EditText mEditText){
        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        mEditText.startAnimation(shake);
    }
}