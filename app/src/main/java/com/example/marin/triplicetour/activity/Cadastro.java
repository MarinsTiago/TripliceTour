package com.example.marin.triplicetour.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marin.triplicetour.R;
import com.example.marin.triplicetour.model.Usuario;
import com.example.marin.triplicetour.util.CadastroService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cadastro extends AppCompatActivity implements View.OnClickListener{

    private static final int TESTE = 100;
    private Button btnCad;
    private EditText nome;
    private EditText login;
    private EditText senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        btnCad = (Button) findViewById(R.id.btnCadastrar);
        nome = (EditText) findViewById(R.id.edtNome);
        login = (EditText) findViewById(R.id.edtLogin);
        senha = (EditText) findViewById(R.id.edtSenha);
        
        btnCad.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Usuario u = new Usuario();
        u.setNome(nome.getText().toString());
        u.setLogin(login.getText().toString());
        u.setSenha(senha.getText().toString());
        u.setTipoUsuario(DEFAULT_KEYS_SHORTCUT);
        CadastroService cadastroService = CadastroService.retrofit.create(CadastroService.class);
        final Call<Void> call = cadastroService.inserirUsuario(u);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Intent intent = new Intent(Cadastro.this, TelaUsuario.class);
                startActivityForResult(intent, TESTE);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro ao inserir Usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
