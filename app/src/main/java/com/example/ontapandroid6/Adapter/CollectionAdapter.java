package com.example.ontapandroid6.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ontapandroid6.fragment.DongVat_fragment;
import com.example.ontapandroid6.fragment.tt_fragment;

public class CollectionAdapter extends FragmentStateAdapter {

    int tabCount = 2;

    DongVat_fragment dv_frag;
    tt_fragment tt_frag;



    public CollectionAdapter(@NonNull Fragment fragment) {
        super(fragment);
        dv_frag = new DongVat_fragment();
        tt_frag= new tt_fragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position) {
           case 0:
               return dv_frag;
           case 1:
               return tt_frag;
           default:
               return dv_frag;
       }
    }

    @Override
    public int getItemCount() {
        return tabCount;
    }
}
