package com.example.ontapandroid6.DTO;

public class DongVatDTO {

    private int ma;
    private String ten;
    private String loai;
    private int soLuong;

    public DongVatDTO(int ma, String ten, String loai, int soLuong) {
        this.ma = ma;
        this.ten = ten;
        this.loai = loai;
        this.soLuong = soLuong;
    }

    public DongVatDTO() {
    }

    public DongVatDTO(String ten, String loai, int soLuong) {
        this.ten = ten;
        this.loai = loai;
        this.soLuong = soLuong;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
