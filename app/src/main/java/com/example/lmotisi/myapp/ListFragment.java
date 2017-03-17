package com.example.lmotisi.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ListFragment extends Fragment implements OnListItemClickListener{

    private RecyclerView recyclerView;
    private OnListItemClickListener listener = this;
    private int categoryID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.list_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        this.recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), getResources().getInteger(R.integer.colonne)));
        this.categoryID = getArguments().getInt("category");
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://www.goglasses.fr/").addConverterFactory(JacksonConverterFactory.create())
                .build();

        APIInterface api = retrofit.create(APIInterface.class);

        Call<Response> call = api.getPostsByCategory(categoryID);

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                ArrayList<Post> news = response.body().posts;

                recyclerView.setAdapter(new MainAdapter(news, listener));
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static ListFragment newInstance(int categoryID) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("category", categoryID);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onItemClicked(Post news) {
        Intent intent = new Intent(getActivity(), WebActivity.class);
        intent.putExtra("news", news);
        startActivity(intent);
    }
}
