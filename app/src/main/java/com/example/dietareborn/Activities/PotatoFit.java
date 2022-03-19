package com.example.dietareborn.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.IntentCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.dietareborn.Adapters.PotatoFitAdapter;
import com.example.dietareborn.R;
import com.google.android.material.tabs.TabLayout;

public class PotatoFit extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    float v = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_potatofit);

        tabLayout = findViewById(R.id.tab_layout_main);
        viewPager = findViewById(R.id.view_pager_main);

        tabLayout.addTab(tabLayout.newTab().setText("Dieta"));
        tabLayout.addTab(tabLayout.newTab().setText("Calorias"));
        tabLayout.addTab(tabLayout.newTab().setText("Rendimento"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final PotatoFitAdapter adapter = new PotatoFitAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        tabLayout.setTranslationY(100);
        tabLayout.setAlpha(v);
        tabLayout.animate().translationY(0).alpha(1).setDuration(300).setStartDelay(100).start();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |  Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}