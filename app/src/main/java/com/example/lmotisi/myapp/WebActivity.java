package com.example.lmotisi.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Post news = getIntent().getExtras().getParcelable("news");
        setContentView(R.layout.activity_web);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerWeb, WebFragment.newInstance(news.url))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
