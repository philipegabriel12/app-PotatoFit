package com.example.dietareborn.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.dietareborn.Activities.PotatoFit;
import com.example.dietareborn.Utils.GlobalUserId;
import com.example.dietareborn.R;
import com.example.dietareborn.bd.DataBase;
import com.example.dietareborn.Utils.HashSenha;
import com.example.dietareborn.bd.Usuario;

public class LoginTabFragment extends Fragment{

    EditText email;
    EditText senha;
    Button login;
    float v = 0;
    DataBase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        email = root.findViewById(R.id.email);
        senha = root.findViewById(R.id.senha);
        login = root.findViewById(R.id.login);

        //db
        db = DataBase.getDatabase(getActivity());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputEmail = email.getText().toString();
                String inputSenha = senha.getText().toString();

                // hash senha
                String salt = inputEmail;
                String hashSenha = HashSenha.get_SHA_256_SecurePassword(inputSenha, salt);

                // conseguir usuario
                Usuario usuario = db.UsuarioDao().getUser(inputEmail, hashSenha);

                //tratamento de erro
                if(inputEmail.isEmpty()){
                    showError(email);
                }
                if(inputSenha.isEmpty()){
                    showError(senha);
                }
                if(inputEmail.isEmpty() || inputSenha.isEmpty()){
                    Toast toast = Toast.makeText(getContext(), "Preencha os campos vazios.", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else if(usuario != null){
                    Intent it = new Intent(getActivity(), PotatoFit.class);
                    int idUsuario = usuario.getId();
                    GlobalUserId.idUsuario = idUsuario;
                    startActivity(it);
                } else{
                    Toast toast = Toast.makeText(getContext(), "Usuário inválido!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                }
                //System.out.println(hashSenha);

            }
        });

        //animações
        email.setTranslationX(800);
        senha.setTranslationX(800);
        login.setTranslationX(800);

        email.setAlpha(v);
        senha.setAlpha(v);
        login.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        senha.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        login.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return root;
    }
    private void showError(EditText mEditText){
        Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        mEditText.startAnimation(shake);
    }
}
