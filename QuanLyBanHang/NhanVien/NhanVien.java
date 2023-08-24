package NhanVien;

import Nguoi.Nguoi;

public class NhanVien extends Nguoi {
    private String maNV;
    private String matKhau;
    private String gioiTinh;
    private String nhom;
    private String trangThai;
    
    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTen, String sdt, String email, String matKhau, String diaChi, String gioiTinh, String nhom, String trangThai) {
        super(hoTen, sdt, email, diaChi);
        this.maNV = maNV;
        this.matKhau = matKhau;
        this.gioiTinh = gioiTinh;
        this.nhom = nhom;
        this.trangThai = trangThai;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNhom() {
        return nhom;
    }

    public void setNhom(String nhom) {
        this.nhom = nhom;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}