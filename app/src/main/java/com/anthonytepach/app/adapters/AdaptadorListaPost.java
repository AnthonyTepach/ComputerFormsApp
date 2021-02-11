package com.anthonytepach.app.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.anthonytepach.app.R;
import com.anthonytepach.app.data.model.M_Posts;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AdaptadorListaPost extends BaseAdapter {

    private Context contexto;
    private int layout;
    private List<M_Posts> lista_modelo;

    public AdaptadorListaPost(Context c, int layou, List list) {
        this.contexto = c;
        this.layout = layou;
        this.lista_modelo = list;
    }

    @Override
    public int getCount() {
        return this.lista_modelo.size();
    }

    @Override
    public Object getItem(int position) {
        return this.lista_modelo.get(position);
    }

    @Override
    public long getItemId(int id) {
        return id;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater layoutinflater = LayoutInflater.from(this.contexto);
        v = layoutinflater.inflate(R.layout.list_item, null);
        String currentName = lista_modelo.get(position).getTitulo();
        Uri url_imagen = Uri.parse(lista_modelo.get(position).getImagePost());
        TextView textView = (TextView) v.findViewById(R.id.textView_list_item);
        textView.setText(currentName);
        TextView autor = (TextView) v.findViewById(R.id.txt_correo);

        autor.setText(lista_modelo.get(position).getCuerpo().substring(0, 30) + "..... ver más");
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView_list_item);
        loadImageGlide(url_imagen, imageView, this.contexto);

        return v;
    }


    private void loadImageGlide(Uri photoUrl, ImageView imageView, Context c) {
        RequestOptions options = new RequestOptions()
                .circleCropTransform()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(c).load(photoUrl)
                .apply(options)
                .into(imageView);

    }
}
