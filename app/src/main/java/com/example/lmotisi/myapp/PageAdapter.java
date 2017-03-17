package com.example.lmotisi.myapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class PageAdapter extends FragmentPagerAdapter {

    private ArrayList<Categorie> categories;

    public PageAdapter(FragmentManager fm, ArrayList<Categorie> categories) {

        super(fm);
        this.categories = categories;

    }

    @Override
    public int getCount() {

        return categories.size();
    }

    @Override
    public Fragment getItem(int position) {

        return ListFragment.newInstance(categories.get(position).id);
    }


    @Override
    public CharSequence getPageTitle(int position) {

        return categories.get(position).title;
    }
}

