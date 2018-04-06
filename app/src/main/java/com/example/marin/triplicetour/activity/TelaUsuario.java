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
    private Button btnListar;
    private Button btnProcurar;
    private EditText edtId;
    private EditText edtEditNome;
    private EditText edtEditLogin;
    private EditText edtEditSenha;
    private EditText edtEditTipo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_usuario);

        edtId = (EditText) findViewById(R.id.edtId);
        edtEditNome = (EditText) findViewById(R.id.edtEditNome);
        edtEditLogin = (EditText) findViewById(R.id.edtEditLogin);
        edtEditSenha = (EditText) findViewById(R.id.edtEditSenha);
        edtEditTipo = (EditText) findViewById(R.id.edtEditTipo);

        btnVoltar = (Button) findViewById(R.id.btnVoltar);
        btnListar = (Button) findViewById(R.id.btnlistarTodos);
        btnProcurar = (Button) findViewById(R.id.btnConsultarId);

        btnVoltar.setOnClickListener(this);
        btnListar.setOnClickListener(this);
        btnProcurar.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnlistarTodos){
            Intent intent = new Intent(TelaUsuario.this, ListarActivity.class);
            startActivityForResult(intent, TESTE);

        }else if (view.getId() == R.id.btnVoltar){
            Intent intent = new Intent(TelaUsuario.this, MainActivity.class);
            startActivityForResult(intent, TESTE);
        }
        /* Buscar por id ainda não finalizado, mas o mesmo pode ser usado na opção de editar usuario.
        else if (view.getId() == R.id.btnConsultarId){

            int ids;
            ids = Integer.parseInt(edtId.getText().toString());

            final Intent intent = getIntent();
            final String id = Integer.toString(intent.getIntExtra("ID", ids));
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
        }
        */
    }
}
