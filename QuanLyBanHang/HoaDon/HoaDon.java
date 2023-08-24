package HoaDon;

public class HoaDon {
    private String maKH;
    private String maNhanVien;
    private String maSP;
    private int soLuongMua;
    private double donGia;
    private double thanhTien;
    private String ngayBan;
    
    public HoaDon() {
    }

    public HoaDon( String maKH, String maNhanVien, String maSP, int soLuongMua, double donGia, double thanhTien,
            String ngayBan) {
        this.maKH = maKH;
        this.maNhanVien = maNhanVien;
        this.maSP = maSP;
        this.soLuongMua = soLuongMua;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.ngayBan = ngayBan;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getmaNhanVien() {
        return maNhanVien;
    }

    public void setmaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public String getNgayBan() {
        return ngayBan;
    }

    public void setNgayBan(String ngayBan) {
        this.ngayBan = ngayBan;
    }
    @Override
    public String toString() {
        return maKH + "\t" + maSP + "\t" + soLuongMua + "\t" + donGia + "\t" +
               thanhTien + "\t" + ngayBan + "\t" + maNhanVien;
    }    
}