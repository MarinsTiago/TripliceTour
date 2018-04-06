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

public class EditarUsuario extends AppCompatActivity {

    private EditText edtEditNome;
    private EditText edtEditLogin;
    private EditText edtEditSenha;
    private EditText edtEditTipo;
    private static final int VOLTAR = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);

        edtEditNome = (EditText) findViewById(R.id.edtEditNome);
        edtEditLogin = (EditText) findViewById(R.id.edtEditLogin);
        edtEditSenha = (EditText) findViewById(R.id.edtEditSenha);
        edtEditTipo = (EditText) findViewById(R.id.edtEditTipo);

        final Intent intent = getIntent();
        final String id = Integer.toString(intent.getIntExtra("ID", 0));

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
                Toast.makeText(getBaseContext(), "Erro ao atualizar", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnEditar = (Button) findViewById(R.id.btnAtualizar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Usuario usuario = new Usuario();
                usuario.setId(Integer.parseInt(id));
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
                            Intent intent1= new Intent(EditarUsuario.this, ListarActivity.class);
                            startActivityForResult(intent1, VOLTAR);
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
        });

        Button btnRemover = (Button) findViewById(R.id.excluirUser);
        btnRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Void> call = cadastroService.excluirUsuario(id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(getBaseContext(), "Usuario removido", Toast.LENGTH_SHORT).show();
                                Intent intent2= new Intent(EditarUsuario.this, ListarActivity.class);
                                startActivityForResult(intent2, VOLTAR);
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getBaseContext(), "Erro ao excluir usuario", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
