package com.anthonytepach.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.anthonytepach.app.adapters.AdaptadorListaValores;
import com.anthonytepach.app.data.model.M_Valores;

import java.util.ArrayList;

public class ValoresFragment extends Fragment {

    private ListView list_Valores;
    private ArrayList<M_Valores> arrayOfValores = new ArrayList<M_Valores>();

    public ValoresFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_valores, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String a[] = view.getResources().getStringArray(R.array.valores_array);
        String b[] = view.getResources().getStringArray(R.array.desc_valores_array);
        for (int i = 0; i < a.length; i++) {
            M_Valores mValores = new M_Valores(a[i], b[i]);
            arrayOfValores.add(mValores);
        }


        list_Valores = view.findViewById(R.id.list_valores);
        AdaptadorListaValores adaptadorListaValores = new AdaptadorListaValores(getActivity(), arrayOfValores);
        list_Valores.setAdapter(adaptadorListaValores);


    }
}