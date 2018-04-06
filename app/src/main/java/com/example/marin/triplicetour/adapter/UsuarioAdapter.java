package com.example.marin.triplicetour.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.marin.triplicetour.R;
import com.example.marin.triplicetour.model.Usuario;

import java.util.List;

/**
 * Created by marin on 04/04/2018.
 */

public class UsuarioAdapter extends ArrayAdapter<Usuario> {


    private final Context context;
    private final List<Usuario> elementos;

    public UsuarioAdapter(Context context, List<Usuario> elementos){
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.linha, parent, false);

        TextView nome = (TextView) rowView.findViewById(R.id.mostraNome);
        TextView login = (TextView) rowView.findViewById(R.id.mostraLogin);
        TextView senha = (TextView) rowView.findViewById(R.id.mostrarSenha);
        TextView tipo = (TextView) rowView.findViewById(R.id.mostrarTipo);

        nome.setText(elementos.get(position).getNome());
        login.setText(elementos.get(position).getLogin());
        senha.setText(elementos.get(position).getSenha());
        tipo.setText(Integer.toString(elementos.get(position).getTipoUsuario()));

        return rowView;
    }
}
