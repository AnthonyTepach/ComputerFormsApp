package com.anthonytepach.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anthonytepach.app.adapters.AdaptadorListaDirectorio;
import com.anthonytepach.app.data.interfaces.ComputerFormsDirectorio;
import com.anthonytepach.app.data.model.M_Directorio;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Directorio extends Fragment {
    private Retrofit retrofit;
    private ListView listaDirectorio;
    private AdaptadorListaDirectorio adaptadorListaDirectorio;

    public Directorio() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDirectorio();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listaDirectorio = view.findViewById(R.id.listview_contactos);
        listaDirectorio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_directorio, container, false);
    }

    private void getDirectorio() {
        String api_url = "https://computerforms.com.mx/App/api/v1/";
        retrofit = RetrofitClient.getClient(api_url);
        ComputerFormsDirectorio computerFormsDirectorio = retrofit.create(ComputerFormsDirectorio.class);
        Call<List<M_Directorio>> call = computerFormsDirectorio.getUsers();

        call.enqueue(new Callback<List<M_Directorio>>() {
            @Override
            public void onResponse(Call<List<M_Directorio>> call, Response<List<M_Directorio>> response) {
                if (response.isSuccessful()) {
                    List<M_Directorio> model_directorio = response.body();
                    adaptadorListaDirectorio = new AdaptadorListaDirectorio(getActivity(), R.layout.list_contactos, model_directorio);
                    listaDirectorio.setAdapter(adaptadorListaDirectorio);
                }
            }

            @Override
            public void onFailure(Call<List<M_Directorio>> call, Throwable t) {

            }
        });
    }
}