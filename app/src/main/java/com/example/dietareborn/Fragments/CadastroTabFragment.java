package com.example.dietareborn.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.dietareborn.R;
import com.example.dietareborn.bd.DataBase;
import com.example.dietareborn.Utils.HashSenha;
import com.example.dietareborn.bd.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CadastroTabFragment extends Fragment {

    EditText email;
    EditText nome;
    EditText sobrenome;
    EditText senha;
    float v = 0;
    Button cadastrar;
    FloatingActionButton addFoto;
    ImageView foto;
    DataBase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.cadastro_tab_fragment, container, false);

        //db
        db = DataBase.getDatabase(getActivity());

        // animações
        email = root.findViewById(R.id.email);
        nome = root.findViewById(R.id.nome);
        sobrenome = root.findViewById(R.id.sobrenome);
        senha = root.findViewById(R.id.senha);
        cadastrar = root.findViewById(R.id.cadastrar);
        addFoto = root.findViewById(R.id.addFoto);
        foto = root.findViewById(R.id.foto);

        email.setTranslationX(800);
        nome.setTranslationX(800);
        sobrenome.setTranslationX(800);
        senha.setTranslationX(800);
        cadastrar.setTranslationX(800);
        addFoto.setTranslationX(800);
        foto.setTranslationX(800);

        email.setAlpha(v);
        nome.setAlpha(v);
        sobrenome.setAlpha(v);
        senha.setAlpha(v);
        cadastrar.setAlpha(v);
        addFoto.setAlpha(v);
        foto.setAlpha(v);

        email.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(200).start();
        nome.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        sobrenome.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        senha.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(600).start();
        cadastrar.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        addFoto.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        foto.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        // encontrar um jeito de salvar a foto na db
        addFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selecione uma foto"), 1);
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputNome = nome.getText().toString();
                String inputSenha = senha.getText().toString();
                String inputSobrenome = sobrenome.getText().toString();
                String inputEmail = email.getText().toString();
                Boolean erroSenha;
                int inputMetaCal = 2000;
                int inputMetaAgua = 3000;
                int inputAguaDieta = 0;

                Boolean emailValido = emailValidator(email);

                if (inputSenha.isEmpty()){
                    showError(senha);
                    erroSenha = true;
                } else{
                    erroSenha = false;
                }
                if (inputNome.isEmpty()){
                    showError(nome);
                }

                if (inputEmail.isEmpty() || !emailValido){
                    showError(email);
                }
                if (inputSobrenome.isEmpty()){
                    showError(sobrenome);
                }

                if (inputNome.isEmpty() || inputSenha.isEmpty() || inputEmail.isEmpty() || inputSobrenome.isEmpty() || !emailValido || erroSenha){
                    Toast toast = Toast.makeText(getContext(), "Existem espaços vazios ou incorretos!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                } else if(db.UsuarioDao().emailExists(inputEmail) == 0) {
                    Toast toast = Toast.makeText(getContext(), "Usuário cadastrado!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();

                    // hash senha
                    String salt = inputEmail;
                    String hashSenha = HashSenha.get_SHA_256_SecurePassword(inputSenha, salt);

                    //cadastrar o usuario e a dieta na database

                    Usuario usuario = new Usuario();
                    usuario.setEmail(inputEmail);
                    usuario.setSenha(hashSenha);
                    usuario.setFirstName(inputNome);
                    usuario.setLastName(inputSobrenome);
                    usuario.setMetaCal(inputMetaCal);
                    usuario.setMetaAgua(inputMetaAgua);
                    usuario.setAguaDieta(inputAguaDieta);

                    db.UsuarioDao().insertUsuario(usuario);
                } else{
                    Toast toast = Toast.makeText(getContext(), "Usuário já existe!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast.show();
                    showError(email);
                }
            }
        });

        return root;
    }
    public boolean emailValidator(EditText etMail) {

        String emailToText = etMail.getText().toString();

        if (!emailToText.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailToText).matches()) {
            return true;
        } else {
            return false;
        }
    }
    private void showError(EditText mEditText){
        Animation shake = AnimationUtils.loadAnimation(getContext(), R.anim.shake);
        mEditText.startAnimation(shake);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            foto.setImageURI(selectedImage);
        }
    }
}