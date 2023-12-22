package com.example.ontapandroid6.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ontapandroid6.DAO.DongVatDAO;
import com.example.ontapandroid6.DTO.DongVatDTO;
import com.example.ontapandroid6.R;
import com.example.ontapandroid6.RingService;

import java.util.ArrayList;

public class DongVatAdapter extends RecyclerView.Adapter<DongVatAdapter.viewHolder>{

    Context context;
    ArrayList<DongVatDTO> listDV;

    public DongVatAdapter(Context context, ArrayList<DongVatDTO> listDV) {
        this.context = context;
        this.listDV = listDV;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.item_dongvat, parent, false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        DongVatDAO dongVatDAO = new DongVatDAO(context);
        DongVatDTO dongVatDTO = listDV.get(position);

        holder.txt_ma.setText("Mã: "+dongVatDTO.getMa());
        holder.txt_loai.setText("Loại: "+dongVatDTO.getLoai());
        holder.txt_ten.setText("Tên: "+dongVatDTO.getTen());
        holder.txt_soluong.setText("Số Lượng: "+dongVatDTO.getSoLuong());


        holder.btn_sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                LayoutInflater inflater = ((Activity)context).getLayoutInflater();
                View view = inflater.inflate(R.layout.dialog_sua, null);
                builder.setView(view);
                Dialog dialog = builder.create();
                dialog.show();

                EditText edt_loai = view.findViewById(R.id.edt_loai);
                EditText edt_ten = view.findViewById(R.id.edt_ten);
                EditText edt_soLuong = view.findViewById(R.id.edt_soLuong);
                Button btn_SuaDialog = view.findViewById(R.id.btn_SuaDialog);
                Button btn_huySua = view.findViewById(R.id.btn_huySua);

                edt_loai.setText(dongVatDTO.getLoai());
                edt_ten.setText(dongVatDTO.getTen());
                edt_soLuong.setText(String.valueOf(dongVatDTO.getSoLuong()));

                btn_SuaDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String loai = edt_loai.getText().toString().trim();
                        String sl = edt_soLuong.getText().toString().trim();
                        String ten = edt_ten.getText().toString().trim();

                        dongVatDTO.setLoai(loai);
                        dongVatDTO.setTen(ten);

                        if (ten.isEmpty()||loai.isEmpty()||sl.isEmpty()) {
                            Toast.makeText(context, "Không bỏ trống dữ liệu", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        try {
                            int soluong = Integer.parseInt(sl);
                            if (soluong < 0) {
                                Toast.makeText(context, "Số lượng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                                return;
                            }
                            dongVatDTO.setSoLuong(soluong);

                            int kq = dongVatDAO.updateRow(dongVatDTO);
                            if (kq>0) {
                                notifyDataSetChanged();
                                dialog.dismiss();
                                Toast.makeText(context, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (NumberFormatException e) {
                            Toast.makeText(context, "Số lượng phải là số", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int kq = dongVatDAO.deleteRow(dongVatDTO);
                if (kq > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Thông báo");
                    builder.setMessage("Bạn đã xoá thành công");


                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            listDV.remove(dongVatDTO);
                            notifyDataSetChanged();
                            Intent intent = new Intent(context, RingService.class);
                            context.startService(intent);
                        }
                    });
                    Dialog dialog = builder.create();
                    dialog.show();
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDV.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txt_ma, txt_ten, txt_loai, txt_soluong;
        Button btn_sua, btn_xoa;

        DongVatAdapter dongVatAdapter;

        public viewHolder(@NonNull View itemView) {
            super(itemView);


            dongVatAdapter = new DongVatAdapter(context, listDV);

            txt_loai = itemView.findViewById(R.id.txt_loai);
            txt_ma = itemView.findViewById(R.id.txt_ma);
            txt_soluong = itemView.findViewById(R.id.txt_soluong);
            txt_ten = itemView.findViewById(R.id.txt_ten);
            btn_sua = itemView.findViewById(R.id.btn_sua);
            btn_xoa = itemView.findViewById(R.id.btn_xoa);
        }
    }
 }
