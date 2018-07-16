package ado.edu.itla.taskapp.vista;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import ado.edu.itla.taskapp.R;
import ado.edu.itla.taskapp.entidad.Categoria;

public class CategoriaListAdapter extends BaseAdapter{

    private Context context;
    private List<Categoria> categorias;



    public CategoriaListAdapter(Activity activity, List<Categoria> categorias) {
        this.context= activity;
        this.categorias = categorias;
    }

    @Override
    public int getCount() {
        return categorias.size();
    }

    @Override
    public Object getItem(int i) {
        return categorias.get(i);
    }

    @Override
    public long getItemId(int i) {
        return categorias.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        //TODO validar si view no es  nulo
        if (view==null)
        {

            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.categoria_listview_row, null, true);
        }
        TextView lbCategoriaId= view.findViewById(R.id.lbCategoriaId);
        TextView lbCategoriaNombre= view.findViewById(R.id.lbNombreCategoria);
        Categoria cat = categorias.get(i);

        lbCategoriaId.setText((cat.getId().toString()));

        lbCategoriaNombre.setText(cat.getNombre().toString());
        return view;
    }
}
