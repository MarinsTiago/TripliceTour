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
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import com.example.marin.triplicetour.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;
    private static final int TESTE = 100;
    private Button btnList;
    private EditText edtSenha;
    private EditText edtNome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btnNovaConta);
        btnList = (Button) findViewById(R.id.listarMain);

        edtNome = (EditText) findViewById(R.id.edtLog);
        edtSenha = (EditText) findViewById(R.id.edtSen);

        btn.setOnClickListener(this);
        btnList.setOnClickListener(this);
       // btnLogar.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btnNovaConta){
            Intent intent = new Intent(MainActivity.this, Cadastro.class);
            startActivityForResult(intent, TESTE);
        }else if (view.getId() == R.id.listarMain){
            Intent intent2 = new Intent(MainActivity.this, ListarActivity.class);
            startActivityForResult(intent2, TESTE);
        }

        /*Login ainda não finalizado
        else if (view.getId() == R.id.btnLogar){

          //  String nome = edtNome.getText().toString();
            //String senha = edtSenha.getText().toString();

           // final Intent intent = getIntent();
            //final String nome = intent.getStringExtra("NOME");
           // final String senha = intent.getStringExtra("SENHA");
            String nome = edtNome.getText().toString();
            String senha = edtSenha.getText().toString();


            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                    if (response.isSuccessful()){
                        Intent intent = new Intent(MainActivity.this, ListarActivity.class);
                        startActivityForResult(intent, TESTE);
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t) {
                    Toast.makeText(getBaseContext(), "Erro ao logar", Toast.LENGTH_SHORT).show();
                }
            });
        }*/


    }
}
