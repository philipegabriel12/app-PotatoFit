package com.example.dietareborn.Fragments;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.dietareborn.Activities.AlterarDieta;
import com.example.dietareborn.Utils.GlobalUserId;
import com.example.dietareborn.R;
import com.example.dietareborn.bd.DataBase;
import com.example.dietareborn.bd.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DietaTabFragment extends Fragment {

    ObjectAnimator objectAnimator;
    ObjectAnimator objectAnimator2;
    ProgressBar calorias_bar;
    ProgressBar agua_bar;
    TextView calorias_textview;
    TextView calrestante_textview;
    TextView agua_textview;
    FloatingActionButton addAgua;
    FloatingActionButton menosAgua;
    Button alterar_dieta;
    float v = 0;
    DataBase db;
    int agua_select;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.dieta_tab_fragment, container, false);

        //db
        db = DataBase.getDatabase(getActivity());
        int idUsuario = GlobalUserId.idUsuario;
        Usuario usuario = db.UsuarioDao().userById(idUsuario);
        int metaCal = usuario.getMetaCal();
        int calDieta = usuario.getCalDieta();
        int MetaAgua = usuario.getMetaAgua();
        int AguaDieta = usuario.getAguaDieta();
        int restanteCal;
        double progressoCal;
        double progressoAgua;

        if(metaCal - calDieta >= 0){
            restanteCal = metaCal - calDieta;
        } else{
            restanteCal = 0;
        }
        if(((double) calDieta) / metaCal == 0){
            progressoCal = 0;
        } else if (calDieta / metaCal >= 1) {
            progressoCal = 100;
        } else{
            progressoCal = ((double) calDieta/metaCal)*100;
        }

        if(((double) AguaDieta) / MetaAgua == 0){
            progressoAgua = 0;
        } else if (AguaDieta / MetaAgua >= 1) {
            progressoAgua = 100;
        } else{
            progressoAgua = ((double) AguaDieta/MetaAgua)*100;
        }

        int inputProgressoCal = (int) java.lang.Math.ceil(progressoCal);
        int inputProgressoAgua = (int) java.lang.Math.ceil(progressoAgua);
        String inputRestanteCal = String.valueOf(restanteCal);

        calorias_bar = root.findViewById(R.id.calorias_bar);
        agua_bar = root.findViewById(R.id.agua_bar);
        calorias_textview = root.findViewById(R.id.calorias_textview);
        agua_textview = root.findViewById(R.id.agua_textview);
        addAgua = root.findViewById(R.id.addAgua);
        menosAgua = root.findViewById(R.id.menosAgua);
        alterar_dieta = root.findViewById(R.id.alterar_dieta);
        calrestante_textview = root.findViewById(R.id.calrestante_textview);
        Spinner spinnerSelectCopo = (Spinner) root.findViewById(R.id.spinnerSelectCopo);

        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(), R.array.copos, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSelectCopo.setAdapter(spinnerAdapter);

        spinnerSelectCopo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int agua_select = Integer.parseInt(adapterView.getItemAtPosition(i).toString());
                usuario.setAguaSelect(agua_select);

                db.UsuarioDao().updateUsuario(usuario);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                int agua_select = 200;
            }
        });

        objectAnimator = ObjectAnimator.ofInt(calorias_bar, "progress", inputProgressoCal);
        objectAnimator.setDuration(600);
        objectAnimator.setStartDelay(400);
        objectAnimator.start();

        objectAnimator2 = ObjectAnimator.ofInt(agua_bar, "progress", inputProgressoAgua);
        objectAnimator2.setDuration(600);
        objectAnimator2.setStartDelay(400);
        objectAnimator2.start();

        calorias_textview.setText(inputRestanteCal);

        // animações

        calorias_bar.setTranslationX(400);
        agua_bar.setTranslationX(800);
        calorias_textview.setTranslationX(400);
        agua_textview.setTranslationX(800);
        addAgua.setTranslationX(800);
        menosAgua.setTranslationX(800);
        spinnerSelectCopo.setTranslationX(400);
        alterar_dieta.setTranslationX(400);
        calrestante_textview.setTranslationX(400);

        calorias_bar.setAlpha(v);
        agua_bar.setAlpha(v);
        calorias_textview.setAlpha(v);
        agua_textview.setAlpha(v);
        addAgua.setAlpha(v);
        menosAgua.setAlpha(v);
        spinnerSelectCopo.setAlpha(v);
        alterar_dieta.setAlpha(v);
        calrestante_textview.setAlpha(v);

        calorias_bar.animate().translationX(0).alpha(1).setDuration(400).setStartDelay(300).start();
        agua_bar.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        calorias_textview.animate().translationX(0).alpha(1).setDuration(400).setStartDelay(300).start();
        agua_textview.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        addAgua.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        menosAgua.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        spinnerSelectCopo.animate().translationX(0).alpha(1).setDuration(400).setStartDelay(300).start();
        alterar_dieta.animate().translationX(0).alpha(1).setDuration(400).setStartDelay(300).start();
        calrestante_textview.animate().translationX(0).alpha(1).setDuration(400).setStartDelay(300).start();

        alterar_dieta.setOnClickListener(view -> {
            Intent it = new Intent(getActivity(), AlterarDieta.class);
            startActivity(it);
        });
        addAgua.setOnClickListener(view -> {
            int soma;
            int agua_select = usuario.getAguaSelect();
            int aguaDieta = usuario.getAguaDieta();
            int metaAgua = usuario.getMetaAgua();
            soma = aguaDieta + agua_select;

            if (soma >= metaAgua){
                usuario.setAguaDieta(metaAgua);
                db.UsuarioDao().updateUsuario(usuario);
                setAnimationAgua(metaAgua);
                Toast toast = Toast.makeText(getContext(), "Você atingiu sua meta de água! 3L.", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.show();
            } else{
                double aguaProgress = (((double) soma)/usuario.getMetaAgua())*100;
                int inputAguaProgress = (int) java.lang.Math.ceil(aguaProgress);
                usuario.setAguaDieta(soma);
                db.UsuarioDao().updateUsuario(usuario);
                setAnimationAgua(inputAguaProgress);
            }
        });
        menosAgua.setOnClickListener(view -> {
           int diferença;
           int agua_select = usuario.getAguaSelect();
           int aguaDieta = usuario.getAguaDieta();
           diferença = aguaDieta - agua_select;

           if (diferença <= 0){
               usuario.setAguaDieta(0);
               db.UsuarioDao().updateUsuario(usuario);
               setAnimationAgua(0);
               Toast toast = Toast.makeText(getContext(), "Por favor, tome água!", Toast.LENGTH_SHORT);
               toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
               toast.show();
           } else{
               double aguaProgress = (((double) diferença)/usuario.getMetaAgua())*100;
               int inputAguaProgress = (int) java.lang.Math.ceil(aguaProgress);
               usuario.setAguaDieta(diferença);
               db.UsuarioDao().updateUsuario(usuario);
               setAnimationAgua(inputAguaProgress);
           }
        });
        return root;
    }
    private void setAnimationAgua(int inputProgressoAgua){
        objectAnimator2 = ObjectAnimator.ofInt(agua_bar, "progress", inputProgressoAgua);
        objectAnimator2.setDuration(600);
        objectAnimator2.start();
    }
}
