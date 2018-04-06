package com.example.marin.triplicetour.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.marin.triplicetour.R;
import com.example.marin.triplicetour.adapter.UsuarioAdapter;
import com.example.marin.triplicetour.model.Usuario;
import com.example.marin.triplicetour.util.CadastroService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
    }
    @Override
    protected void onStart() {
        super.onStart();
        final ListView listar = (ListView) findViewById(R.id.listarUsuarios);
        CadastroService cadastroService = CadastroService.retrofit.create(CadastroService.class);
        final Call<List<Usuario>> call = cadastroService.pegarTodos();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                final List<Usuario> listarUsuario = response.body();
                if(listarUsuario != null){
                    UsuarioAdapter adapter = new UsuarioAdapter(getBaseContext(), listarUsuario);
                    listar.setAdapter(adapter);
                    listar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(ListarActivity.this, EditarUsuario.class);
                            intent.putExtra("ID", listarUsuario.get(i).getId());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Erro ao listar usuarios", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_menu){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
