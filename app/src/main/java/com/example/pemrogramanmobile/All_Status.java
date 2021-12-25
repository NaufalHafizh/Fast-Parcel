package com.example.pemrogramanmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class All_Status extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_status);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);

        tabLayout.setupWithViewPager(viewPager);

        Adapter adapter = new Adapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new Tabs_Packing(), "Packing");
        adapter.addFragment(new Tabs_Delivered(), "Delivered");
        adapter.addFragment(new Tabs_Arived(), "Arrived");
        viewPager.setAdapter(adapter);


        String id = null;
//        String packing1 = getIntent().getStringExtra("Packing");
//        String delivered1 = getIntent().getStringExtra("Delivered");
//        String arrived1 = getIntent().getStringExtra("Arrived");
//
//        if (id.equals(packing1)){
//            TabLayout.Tab tab = tabLayout.getTabAt(1);
//            tab.select();
//        }
//        else if (id.equals(delivered1)){
//            TabLayout.Tab tab2 = tabLayout.getTabAt(2);
//            tab2.select();
//
//        }else if (id.equals(arrived1)){
//            TabLayout.Tab tab3 = tabLayout.getTabAt(2);
//            tab3.select();
//
//        }

        int packing = getIntent().getIntExtra("Packing", 0);
        int delivered = getIntent().getIntExtra("Delivered", 0);
        int Arrived = getIntent().getIntExtra("Arrived", 0);

        viewPager.setCurrentItem(packing);
        viewPager.setCurrentItem(delivered);
        viewPager.setCurrentItem(Arrived);

    }
}