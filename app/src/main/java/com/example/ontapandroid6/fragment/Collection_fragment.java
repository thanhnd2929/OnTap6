package com.example.ontapandroid6.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ontapandroid6.Adapter.CollectionAdapter;
import com.example.ontapandroid6.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Collection_fragment extends Fragment {

    TabLayout tabLayout;
    ViewPager2 viewPager2;

    CollectionAdapter collectionAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.viewPager2);

        collectionAdapter = new CollectionAdapter(this);
        viewPager2.setAdapter(collectionAdapter);

        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Động Vật");
                        break;
                    case 1:
                        tab.setText("Thông Tin");
                        break;
                    default:
                        tab.setText("Động Vật");
                        break;

                }
            }
        });
        mediator.attach();

    }
}
