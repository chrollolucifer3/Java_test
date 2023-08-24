package KhachHang;

import Nguoi.Nguoi;

public class KhachHang extends Nguoi  {
    
    private String maKH;
    
    public KhachHang() {
    }
    
    public KhachHang(String maKH, String hoTen, String sdt, String email, String diaChi) {
        super(hoTen, sdt, email, diaChi);
        this.maKH = maKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }
}
