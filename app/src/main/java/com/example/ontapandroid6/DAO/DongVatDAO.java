package com.example.ontapandroid6.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ontapandroid6.DTO.DongVatDTO;
import com.example.ontapandroid6.DbHelper.MyDbHelper;

import java.util.ArrayList;

public class DongVatDAO {

    MyDbHelper dbHelper;
    SQLiteDatabase db;

    public DongVatDAO(Context context) {
        dbHelper =new MyDbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public ArrayList<DongVatDTO> getList() {
        ArrayList<DongVatDTO> listDV = new ArrayList<>();
        Cursor c = db.rawQuery("Select * from dongvat", null);
        if (c.getCount() > 0 && c != null) {
            c.moveToFirst();
            do {
                int ma = c.getInt(0);
                String ten = c.getString(1);
                String loai = c.getString(2);
                int sl = c.getInt(3);
                DongVatDTO dongVatDTO = new DongVatDTO(ma, ten, loai, sl);
                listDV.add(dongVatDTO);
            } while (c.moveToNext());
        }
        return listDV;
    }

    public int addRow(DongVatDTO dongVatDTO) {
        ContentValues values = new ContentValues();
        values.put("ten", dongVatDTO.getTen());
        values.put("loai", dongVatDTO.getLoai());
        values.put("soluong", dongVatDTO.getSoLuong());
        return (int) db.insert("dongvat", null, values);
    }

    public int updateRow(DongVatDTO dongVatDTO) {
        ContentValues values = new ContentValues();
        values.put("ten", dongVatDTO.getTen());
        values.put("loai", dongVatDTO.getLoai());
        values.put("soluong", dongVatDTO.getSoLuong());
        String[] dk = new String[]{String.valueOf(dongVatDTO.getMa())};
        return db.update("dongvat", values, "ma=?", dk);
    }

    public int deleteRow(DongVatDTO dongVatDTO) {
        String[] dk = new String[]{String.valueOf(dongVatDTO.getMa())};
        return db.delete("dongvat", "ma=?", dk);
    }
}
