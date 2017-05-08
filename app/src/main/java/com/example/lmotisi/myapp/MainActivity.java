package com.example.lmotisi.myapp;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import static android.R.attr.id;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getArticles();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            displayPreference();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void displayPreference() {
        Intent intent = new Intent(this, PreferenceActivity.class);
        startActivity(intent);
    }


    public void getArticles() {
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://www.goglasses.fr/").addConverterFactory(JacksonConverterFactory.create())
                .build();

        APIInterface api = retrofit.create(APIInterface.class);

        Call<ListCategories> call = api.getCategories();

        call.enqueue(new Callback<ListCategories>() {
            @Override
            public void onResponse(Call<ListCategories> call, retrofit2.Response<ListCategories> response) {
                ArrayList<Categorie> allCategories = response.body().categories;

                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

                String[] resCategories = sharedPrefs.getStringSet("pref_key", null).toArray(new String[] {});

                ArrayList<Categorie> categories = new ArrayList<>();
                Categorie lastPostsCategory = new Categorie();
                lastPostsCategory.id = -1;
                lastPostsCategory.title = getContext().getResources().getString(R.string.articles_category);
                categories.add(lastPostsCategory);

                for (Categorie categorie : allCategories) {
                    if (Arrays.asList(resCategories).contains(categorie.slug)) {
                        categories.add(categorie);
                    }
                }

                viewPager = (ViewPager) findViewById(R.id.view_pager);

                viewPager.setAdapter(new
                        PageAdapter(getSupportFragmentManager(), categories));

                TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
                tabLayout.setupWithViewPager(viewPager);
            }

            @Override
            public void onFailure(Call<ListCategories> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public Context getContext() {
        return (Context)this;
    }

}
