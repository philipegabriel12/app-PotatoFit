package com.example.dietareborn.Adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.dietareborn.Fragments.CaloriasTabFragment;
import com.example.dietareborn.Fragments.DietaTabFragment;
import com.example.dietareborn.Fragments.RendimentoTabFragment;

public class PotatoFitAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalTabs;

    public PotatoFitAdapter(FragmentManager fm, Context context, int totalTabs){
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                DietaTabFragment dietaTabFragment = new DietaTabFragment();
                return dietaTabFragment;
            case 1:
                CaloriasTabFragment caloriasTabFragment  = new CaloriasTabFragment();
                return caloriasTabFragment;
            case 2:
                RendimentoTabFragment rendimentoTabFragment  = new RendimentoTabFragment();
                return rendimentoTabFragment;
            default:
                return null;
        }
    }
    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}