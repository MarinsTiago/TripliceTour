package com.example.marin.triplicetour.util;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by marin on 27/03/2018.
 */

public class Chamada extends AsyncTask<String, Void, String> {  //Usuario, Void, String

    private String servico;

    public Chamada(String servico) {
        super();
        this.servico = servico;
    }

        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection con = null;

            try {
                URL url = new URL(this.servico+strings[0]); //usuarios
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();

                InputStream ent = con.getInputStream();
                InputStreamReader trans = new InputStreamReader(ent);

                BufferedReader ler = new BufferedReader(trans);
                String linha;
                StringBuffer buf = new StringBuffer();
                while ((linha = ler.readLine()) != null){
                    buf.append(linha);
                    buf.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return servico;
            /*Teste
            Gson gson = new Gson();
            String x = gson.toJson(usuarios);
            return x;
            */
        }
    @Override
    protected void onPostExecute(String usuario) {
        System.out.println(usuario);
    }
}

