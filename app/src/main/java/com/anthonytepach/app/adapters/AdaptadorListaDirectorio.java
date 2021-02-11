package com.anthonytepach.app.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.anthonytepach.app.R;
import com.anthonytepach.app.data.model.M_Directorio;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AdaptadorListaDirectorio extends BaseAdapter {

    private Context contexto;
    private int layout;
    private List<M_Directorio> lista_modelo;

    public AdaptadorListaDirectorio(Context c, int layou, List list) {
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
        v = layoutinflater.inflate(R.layout.list_contactos, null);

        String name_full = lista_modelo.get(position).getNombre() + " " + lista_modelo.get(position).getApat() + " " + lista_modelo.get(position).getAmat();


        String departamento = lista_modelo.get(position).getDepartamento();
        String email = lista_modelo.get(position).getEmail();
        String telefono = lista_modelo.get(position).getCel();

        TextView name_tv = (TextView) v.findViewById(R.id.txt_list_nombre);
        name_tv.setText(name_full);
        TextView tv_cel = (TextView) v.findViewById(R.id.txt_list_telefono);
        tv_cel.setText(telefono);
        TextView tv_dep = (TextView) v.findViewById(R.id.txt_list_area);
        tv_dep.setText(departamento + " - " + lista_modelo.get(position).getPuesto());
        TextView tv_email = (TextView) v.findViewById(R.id.txt_list_correo);
        tv_email.setText(email.toString());
        ImageView imageView = (ImageView) v.findViewById(R.id.imageView_list_item);
        String imagen_s = lista_modelo.get(position).getImageProfile();
        Uri img = Uri.parse(imagen_s);

        loadImageGlide(img, imageView, v.getContext());
        ImageButton btn_whats= v.findViewById(R.id.btn_list_whats);
        btn_whats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isWhatsappInstalled = whatsappInstalledOrNot("com.whatsapp");
                if (isWhatsappInstalled) {
                    String smsNumber = lista_modelo.get(position).getCel(); // E164 format without '+' sign
                    Intent sendIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("smsto:" + "" + smsNumber));
                    sendIntent.setPackage("com.whatsapp");
                    contexto.startActivity(sendIntent);
                }else{
                    AlertDialog.Builder adb=new AlertDialog.Builder(contexto);
                    adb.setTitle("WhatsApp no instalado");
                    adb.setMessage("Instala Whatsapp desde PlayStore");
                    adb.setPositiveButton("Ok", null);
                    adb.show();
                }
            }
        });
        ImageButton btn_call= v.findViewById(R.id.btn_list_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tel = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+lista_modelo.get(position).getCel()));
                contexto.startActivity(tel);
                //return true;
            }
        });
        ImageButton btn_mail= v.findViewById(R.id.btn_list_email);
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correos=lista_modelo.get(position).getEmail();
                composeEmail(correos,"Hola, "+lista_modelo.get(position).getNombre()+" me puedes apoyar en..");
            }
        });
        return v;
    }


    private void loadImageGlide(Uri photoUrl, ImageView imageView, Context c) {

        RequestOptions options = new RequestOptions()
                .circleCropTransform()
                .centerInside()
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH);
        Glide.with(c).load(photoUrl)
                .apply(options)
                .into(imageView);

    }

    public void composeEmail(String addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+addresses)); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(contexto.getPackageManager()) != null) {
            contexto.startActivity(intent);
        }
    }

    private boolean whatsappInstalledOrNot(String uri) {
        PackageManager pm = contexto.getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}
