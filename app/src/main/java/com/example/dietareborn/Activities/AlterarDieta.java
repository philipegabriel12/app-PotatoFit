package com.example.dietareborn.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupMenu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dietareborn.Utils.GlobalUserId;
import com.example.dietareborn.R;
import com.example.dietareborn.bd.Comida;
import com.example.dietareborn.bd.DataBase;
import com.example.dietareborn.bd.Dieta;
import com.example.dietareborn.bd.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class AlterarDieta extends AppCompatActivity {

    DataBase db;
    String Arroz = "Arroz";
    String Feijão = "Feijão";
    String Ovo = "Ovo";
    String Carne = "Carne";
    String Frango = "Frango";
    List<Dieta> lista_dieta;
    Dieta refeiçao_select;
    List<Dieta> lista_calorias;
    Dieta calorias_select;
    int soma = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_dieta);

        FloatingActionButton addDieta = findViewById(R.id.addDieta);
        FloatingActionButton resetDieta = findViewById(R.id.resetDieta);
        ListView listDieta = findViewById(R.id.listDieta);

        //db
        db = DataBase.getDatabase(getApplicationContext());
        int idUsuario = GlobalUserId.idUsuario;
        Usuario usuario = db.UsuarioDao().userById(idUsuario);
        //System.out.println(idUsuario);

        lista_calorias = db.DietaDao().listDietaByUser(idUsuario);

        lista_dieta = db.DietaDao().listDietaByUser(idUsuario);
        String[] Refeiçoes = new String[lista_dieta.size()];

        for(int i = 0; i < lista_calorias.size(); i++){
            calorias_select = lista_calorias.get(i);
            int caloria = db.DietaDao().caloriaById(calorias_select.getId());
            soma = caloria+soma;
        }
        usuario.setCalDieta(soma);
        db.UsuarioDao().updateUsuario(usuario);

        for(int i = 0; i < lista_dieta.size(); i++) {
            refeiçao_select = lista_dieta.get(i);
            String nomeComida = db.ComidaDao().nomeById(refeiçao_select.getId_comida());
            Refeiçoes[i] = nomeComida+", "+(int) refeiçao_select.getQuantidade()+"g, "+(int) refeiçao_select.getCal()+"cal";
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Refeiçoes);
        listDieta.setAdapter(adapter);

        listDieta.setOnItemClickListener((adapterView, v, position, id) -> {
            refeiçao_select = lista_dieta.get(position);
            PopupMenu popup = new PopupMenu(AlterarDieta.this, listDieta);
            popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(item -> {
                switch(item.getItemId()){
                    case R.id.Editar_menu:
                        Intent it2 = new Intent(AlterarDieta.this, EditDieta.class);
                        it2.putExtra("idDieta", refeiçao_select.getId());
                        startActivity(it2);
                        break;
                    case R.id.Excluir_menu:
                        db.DietaDao().deleteDieta(refeiçao_select);
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        break;
                }
                return false;
            });
            popup.show();
        });

        addDieta.setOnClickListener(v -> {
            Intent it1 = new Intent(getApplicationContext(), AddDieta.class);
            startActivity(it1);
        });

        resetDieta.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(AlterarDieta.this);
            builder.setTitle("Deseja reiniciar sua dieta?").setMessage("Tem certeza?").setPositiveButton("Sim", (dialogInterface, i) -> {
                db.DietaDao().deleteAllDietaFromUser(GlobalUserId.idUsuario);
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }).setNegativeButton("Não", null);

            AlertDialog alert = builder.create();
            alert.show();
        });

        // adicionar comidas default
        if(db.ComidaDao().comidaExists(Arroz) == 0){
            Comida arroz = new Comida();
            arroz.setNome(Arroz);
            arroz.setQtd(100);
            arroz.setCal(130);
            db.ComidaDao().insertComida(arroz);

            Comida frango = new Comida();
            frango.setNome(Frango);
            frango.setQtd(100);
            frango.setCal(240);
            db.ComidaDao().insertComida(frango);

            Comida feijao = new Comida();
            feijao.setNome(Feijão);
            feijao.setQtd(100);
            feijao.setCal(100);
            db.ComidaDao().insertComida(feijao);

            Comida ovo = new Comida();
            ovo.setNome(Ovo);
            ovo.setQtd(100);
            ovo.setCal(77);
            db.ComidaDao().insertComida(ovo);

            Comida carne = new Comida();
            carne.setNome(Carne);
            carne.setQtd(100);
            carne.setCal(240);
            db.ComidaDao().insertComida(carne);
        } else{
            System.out.println("OK");
        }
    }
    @Override
    public void onBackPressed() {
        Intent it = new Intent(getApplicationContext(), PotatoFit.class);
        finish();
        startActivity(it);
    }
}