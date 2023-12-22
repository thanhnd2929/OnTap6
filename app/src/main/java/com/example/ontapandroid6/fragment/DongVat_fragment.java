package com.example.ontapandroid6.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapandroid6.Adapter.DongVatAdapter;
import com.example.ontapandroid6.DAO.DongVatDAO;
import com.example.ontapandroid6.DTO.DongVatDTO;
import com.example.ontapandroid6.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class DongVat_fragment extends Fragment {

    DongVatAdapter dongVatAdapter;
    DongVatDAO dongVatDAO;
    LinearLayoutManager linearLayoutManager;
    RecyclerView rv_dongVat;
    FloatingActionButton fb_add;
    ArrayList<DongVatDTO> listDV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dongvat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_dongVat = view.findViewById(R.id.rv_dongVat);
        fb_add = view.findViewById(R.id.fb_add);

        linearLayoutManager = new LinearLayoutManager(getContext());
        rv_dongVat.setLayoutManager(linearLayoutManager);

        dongVatDAO = new DongVatDAO(getContext());
        listDV = dongVatDAO.getList();

        dongVatAdapter = new DongVatAdapter(getContext(), listDV);
        rv_dongVat.setAdapter(dongVatAdapter);

        fb_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                View view1 = getLayoutInflater().inflate(R.layout.dialog_them, null);
                builder.setView(view1);
                Dialog dialog = builder.create();
                dialog.show();
                dialog.getWindow().setBackgroundDrawable(null);

                EditText edt_ten = view1.findViewById(R.id.edt_ten);
                EditText edt_loai = view1.findViewById(R.id.edt_loai);
                EditText edt_soLuong = view1.findViewById(R.id.edt_soLuong);
                Button btn_them = view1.findViewById(R.id.btn_them);
                Button btn_huythem = view1.findViewById(R.id.btn_huyThem);

                btn_them.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String ten = edt_ten.getText().toString().trim();
                        String loai = edt_loai.getText().toString().trim();
                        String soluong = edt_soLuong.getText().toString().trim();

                        if (ten.isEmpty()||loai.isEmpty()||soluong.isEmpty()) {
                            Toast.makeText(getContext(), "Không bỏ trống dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            int sl = Integer.parseInt(soluong);
                            if (sl < 0) {
                                Toast.makeText(getContext(), "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            int kq = dongVatDAO.addRow(new DongVatDTO(ten, loai, sl));
                            if (kq>0) {
                                listDV.clear();
                                listDV.addAll(dongVatDAO.getList());
                                dongVatAdapter.notifyDataSetChanged();
                                dialog.dismiss();
                            }
                        }
                        catch (NumberFormatException e) {
                            Toast.makeText(getContext(), "Số lượng phải là số", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                btn_huythem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

            }
        });


    }
}
