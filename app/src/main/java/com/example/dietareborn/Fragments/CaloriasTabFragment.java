package com.example.dietareborn.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.dietareborn.Utils.GlobalUserId;
import com.example.dietareborn.R;
import com.example.dietareborn.bd.DataBase;
import com.example.dietareborn.bd.Usuario;

public class CaloriasTabFragment extends Fragment {

    EditText peso;
    EditText idade;
    EditText altura;
    RadioGroup radioGrp;
    Button meta_calorias;
    int v = 0;
    DataBase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.calorias_tab_fragment, container, false);

        //db
        db = DataBase.getDatabase(getActivity());
        int idUsuario = GlobalUserId.idUsuario;
        Usuario usuario = db.UsuarioDao().userById(idUsuario);
        // animações

        peso = root.findViewById(R.id.peso);
        idade = root.findViewById(R.id.idade);
        altura = root.findViewById(R.id.altura);
        radioGrp = root.findViewById(R.id.radioGrp);
        meta_calorias = root.findViewById(R.id.meta_calorias);

        peso.setTranslationX(800);
        idade.setTranslationX(800);
        altura.setTranslationX(800);
        radioGrp.setTranslationX(800);
        meta_calorias.setTranslationX(800);

        peso.setAlpha(v);
        idade.setAlpha(v);
        altura.setAlpha(v);
        radioGrp.setAlpha(v);
        meta_calorias.setAlpha(v);

        peso.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        idade.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        altura.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        radioGrp.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        meta_calorias.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        //fim das animações

        zeroFilter(peso);
        zeroFilter(idade);
        zeroFilter(altura);

        meta_calorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputPeso = peso.getText().toString();
                String inputIdade = idade.getText().toString();
                String inputAltura = altura.getText().toString();
                int selectedRadioId = radioGrp.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) root.findViewById(selectedRadioId);
                String selec_radioButton = radioButton.getText().toString();
                double meta_calorias;

                if(inputPeso.isEmpty()){
                    showError(peso);
                }
                if(inputIdade.isEmpty()){
                    showError(idade);
                }
                if(inputAltura.isEmpty()){
                    showError(altura);
                }
                if(radioGrp.getCheckedRadioButtonId() == -1){
                    Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
                    radioGrp.startAnimation(shake);
                }
                if(inputPeso.isEmpty() || inputIdade.isEmpty() || inputAltura.isEmpty() || radioGrp.getCheckedRadioButtonId() == -1){
                    Toast toast = Toast.makeText(getContext(), "Ao menos uma das informações está incorreta!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else if(selec_radioButton.equals("Masculino")){
                    Toast toast = Toast.makeText(getContext(), "Sua meta de calorias foi atualizada!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    meta_calorias = 1.25*(13.75 * Integer.parseInt(inputPeso) + (5 * Integer.parseInt(inputAltura)) - (6.76 * Integer.parseInt(inputIdade)) + 66.5);
                    //System.out.println(meta_calorias);
                    int meta_cal = (int) java.lang.Math.round(meta_calorias);
                    usuario.setMetaCal(meta_cal);
                    db.UsuarioDao().updateUsuario(usuario);
                    getActivity().recreate();
                } else{
                    Toast toast = Toast.makeText(getContext(), "Sua meta de calorias foi atualizada!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    meta_calorias = 1.2875*(9.56 * Integer.parseInt(inputPeso) + (1.85 * Integer.parseInt(inputAltura)) - (4.68 * Integer.parseInt(inputIdade)) + 665);
                    //System.out.println(meta_calorias);
                    int meta_cal = (int) java.lang.Math.round(meta_calorias);
                    usuario.setMetaCal(meta_cal);
                    db.UsuarioDao().updateUsuario(usuario);
                    getActivity().recreate();
                }
                System.out.println(usuario.metaCal);
            }
        });

        return root;
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
        Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        mEditText.startAnimation(shake);
    }
}