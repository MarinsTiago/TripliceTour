package com.example.marin.triplicetour.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marin.triplicetour.model.Usuario;
import com.example.marin.triplicetour.util.CadastroService;
import com.facebook.FacebookButtonBase;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.example.marin.triplicetour.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;
    private static final int TESTE = 100;
    private EditText edtSenha;
    private EditText edtLogin;
    private Button btnLogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btnNovaConta);
        btnLogar = (Button) findViewById(R.id.btnLogar);

        edtLogin = (EditText) findViewById(R.id.edtLog);
        edtSenha = (EditText) findViewById(R.id.edtSen);

        btn.setOnClickListener(this);
        btnLogar.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnNovaConta){
            Intent intent = new Intent(MainActivity.this, Cadastro.class);
            startActivityForResult(intent, TESTE);
        }
        else if (view.getId() == R.id.btnLogar){

            String login = edtLogin.getText().toString();
            String senha = edtSenha.getText().toString();

            final CadastroService cadastroService = CadastroService.retrofit.create(CadastroService.class);
            final Call<Boolean> call = cadastroService.logar(login, senha);

            call.enqueue(new Callback<Boolean>() {
                @Override
                public void onResponse(Call<Boolean> call, Response<Boolean> response) {

                    if (response.isSuccessful()){
                        Intent intent = new Intent(MainActivity.this, ListarActivity.class);
                        startActivityForResult(intent, TESTE);
                    }
                }

                @Override
                public void onFailure(Call<Boolean> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Erro ao logar", Toast.LENGTH_SHORT).show();
                }
            });
        }


    }
}
