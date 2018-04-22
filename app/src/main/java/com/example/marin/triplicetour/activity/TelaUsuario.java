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

public class TelaUsuario extends AppCompatActivity implements View.OnClickListener{

    private Button btnVoltar;
    private static final int TESTE = 100;
    private Button btnProcurar;
    private EditText edtId;
    private EditText edtEditNome;
    private EditText edtEditLogin;
    private EditText edtEditSenha;
    private EditText edtEditTipo;
    private Button btnAtualizar;
    private Button btnexcluir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario);

        edtId = (EditText) findViewById(R.id.edtProcuraId);
        edtEditNome = (EditText) findViewById(R.id.edtProcuraNome);
        edtEditLogin = (EditText) findViewById(R.id.edtProcuraLogin);
        edtEditSenha = (EditText) findViewById(R.id.edtProcuraSenha);
        edtEditTipo = (EditText) findViewById(R.id.edtProcuraTipo);
        btnProcurar = (Button) findViewById(R.id.btnProcuraId);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnAtualizar = (Button) findViewById(R.id.atualizar);
        btnexcluir = (Button) findViewById(R.id.excluir);

        btnVoltar.setOnClickListener(this);
        btnProcurar.setOnClickListener(this);
        btnAtualizar.setOnClickListener(this);
        btnexcluir.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.excluir){

            int id = Integer.parseInt(edtId.getText().toString());

            final CadastroService cadastroService = CadastroService.retrofit.create(CadastroService.class);

            Call<Void> call = cadastroService.excluirUsuario(id);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(getBaseContext(), "Usuario removido", Toast.LENGTH_SHORT).show();
                    Intent intent2= new Intent(TelaUsuario.this, TelaUsuario.class);
                    startActivityForResult(intent2, TESTE);
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Erro ao excluir usuario", Toast.LENGTH_SHORT).show();
                }
            });

        }else if (view.getId() == R.id.btnVoltar){
            Intent intent = new Intent(TelaUsuario.this, MainActivity.class);
            startActivityForResult(intent, TESTE);
        }
        else if (view.getId() == R.id.btnProcuraId){
            int id;
            id = Integer.parseInt(edtId.getText().toString());

            final CadastroService cadastroService = CadastroService.retrofit.create(CadastroService.class);
            final Call<Usuario> call = cadastroService.pegarPorId(id);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if (response.isSuccessful()){
                        Usuario u = response.body();
                        edtEditNome.setText(u.getNome());
                        edtEditLogin.setText(u.getLogin());
                        edtEditSenha.setText(u.getSenha());
                        edtEditTipo.setText(String.valueOf(u.getTipoUsuario()));
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Erro ao buscar por id", Toast.LENGTH_SHORT).show();
                }
            });
        }else if(view.getId() == R.id.atualizar){

            int id = Integer.parseInt(edtId.getText().toString());
            final CadastroService cadastroService = CadastroService.retrofit.create(CadastroService.class);

            Usuario usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome(edtEditNome.getText().toString());
            usuario.setLogin(edtEditLogin.getText().toString());
            usuario.setSenha(edtEditSenha.getText().toString());
            usuario.setTipoUsuario(Integer.parseInt(edtEditTipo.getText().toString()));

            Call<Void> call = cadastroService.alterarUsuario(usuario);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(getBaseContext(), "Atualização realizada com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent1= new Intent(TelaUsuario.this, TelaUsuario.class);
                        startActivityForResult(intent1, TESTE);
                    }else{
                        Toast.makeText(getBaseContext(), "Atualização não realizada!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
