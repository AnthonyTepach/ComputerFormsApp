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

import com.anthonytepach.app.adapters.AdaptadorListaPost;
import com.anthonytepach.app.data.interfaces.ComputerFormsPost;
import com.anthonytepach.app.data.model.M_Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PostsFragment extends Fragment {

    private Retrofit retrofit;
    private ListView listaPost;
    private AdaptadorListaPost adaptadorListaPost2;

    public PostsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getAnuncios();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listaPost = view.findViewById(R.id.listview_anun);
        listaPost.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    private void getAnuncios() {
        String api_url = "https://computerforms.com.mx/App/api/v1/";
        retrofit = RetrofitClient.getClient(api_url);
        ComputerFormsPost computerFormsPost = retrofit.create(ComputerFormsPost.class);
        Call<List<M_Posts>> call = computerFormsPost.getPosts();

        call.enqueue(new Callback<List<M_Posts>>() {
            @Override
            public void onResponse(Call<List<M_Posts>> call, Response<List<M_Posts>> response) {
                if (response.isSuccessful()) {
                    List<M_Posts> model_anunico = response.body();
                    adaptadorListaPost2 = new AdaptadorListaPost(getActivity(), R.layout.list_item, model_anunico);
                    listaPost.setAdapter(adaptadorListaPost2);
                }
            }

            @Override
            public void onFailure(Call<List<M_Posts>> call, Throwable t) {

            }
        });
    }

    private void imprimeJSON(List<M_Posts> model_anunico) {
        for (M_Posts post : model_anunico) {
            System.out.println(post.getTitulo());
            System.out.println(post.getCuerpo());
            System.out.println(post.getFecha());
            System.out.println(post.getImagePost());
        }
    }


}

