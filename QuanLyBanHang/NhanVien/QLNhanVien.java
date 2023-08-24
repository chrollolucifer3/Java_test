package NhanVien;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

import Nguoi.QuanLy;

public class QLNhanVien extends QuanLy {
    static ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
    static NhanVien nv = new NhanVien();
    public void main(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("----Quan ly Nhan Vien----");
            System.out.println("1. Xem danh sach toan bo nhan vien");
            System.out.println("2. Tim kiem nhan vien");
            System.out.println("3. Them moi nhan vien");
            System.out.println("4. Sua thong tin nhan vien");
            System.out.println("5. Xoa thong tin nhan vien");
            System.out.println("6. Thoat");
            System.out.print("Nhap vao lua chon cua ban: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    hienThiDanhSachNhanVien(dsNhanVien);
                    break;
                case 2:
                    timKiemNhanVien();
                    break;
                case 3:
                    themMoiNhanVien();
                    break;
                case 4:
                    suaThongTinNhanVien();
                    break;
                case 5:
                    xoaThongTinNhanVien();
                    break;
                case 6:
                    luuNhanVienVaoFile();
                    System.out.println("Da thoat khoi chuong trinh!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le, yeu cau nhap lai!");
            }
        }
    }
        public static void loadNhanVienFromFile() {
            try (BufferedReader reader = new BufferedReader(new FileReader("DanhSachNhanVien.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // System.out.println("Read line: " + line);
                String[] parts = line.split("\t");
                if (parts.length >= 9) { // Kiểm tra có đủ phần tử để tách hay không
                    String ID = parts[0];
                    String hoTen = parts[1];
                    String sdt = parts[2];
                    String email = parts[3];
                    String matKhau = parts[4];
                    String diaChi = parts[5];
                    String gioiTinh = parts[6];
                    String nhom = parts[7];
                    String trangThai = parts[8];
                    dsNhanVien.add(new NhanVien(ID, hoTen, sdt, email, matKhau, diaChi, gioiTinh, nhom, trangThai));
                } else {
                    System.out.println("Du lieu khong dung dinh dang: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    static void luuNhanVienVaoFile() {
        System.out.println("Saving data to file...");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DanhSachNhanVien.txt"))) {
            for (NhanVien nv : dsNhanVien) {

                    writer.write(nv.getMaNV() + "\t" + nv.getHoTen() + "\t" + nv.getSdt() + "\t" + nv.getEmail() +
                     "\t" + nv.getMatKhau() + "\t" + nv.getDiaChi() + "\t" + nv.getGioiTinh() + "\t" + nv.getNhom() + "\t" + nv.getTrangThai());
                    writer.newLine();
    
                    // Đánh dấu nhân viên là đã được ghi hoặc đã được lưu
            }
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }
    public static ArrayList<NhanVien> getDsNhanVien() {
        return dsNhanVien;
    }
    static void hienThiDanhSachNhanVien(ArrayList<NhanVien> danhSachNhanVien) {
        System.out.println("  | STT |    Ma Nhan Vien    |    Ten Nhan Vien    |  So Dien Thoai  |        Email        |   Dia Chi   |  Gioi Tinh  |   Nhom   |  Trang thai");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
    
        for (int i = 0; i < danhSachNhanVien.size(); i++) {
            NhanVien nv = danhSachNhanVien.get(i);
            int stt = i + 1;
    
            System.out.printf("  | %4d | %-17s | %-19s | %14s | %-20s | %-11s | %-10s | %-9s | %-14s%n",
            stt, nv.getMaNV(), nv.getHoTen(), nv.getSdt(), nv.getEmail(), nv.getDiaChi(), nv.getGioiTinh(), nv.getNhom(), nv.getTrangThai());
        }
    }
    
    static void timKiemNhanVien(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao ma nhan vien hoac email/SDT: ");
        String value = sc.nextLine();
         boolean found = false;
        for (NhanVien nv : dsNhanVien) {
            if(nv.getMaNV().equals(value) || nv.getEmail().equals(value) || nv.getSdt().equals(value)){
                System.out.println("Ma nhan vien: " + nv.getMaNV());
                System.out.println("Ten nhan vien: " + nv.getHoTen());
                System.out.println("So dien thoai: " + nv.getSdt());
                System.out.println("Email: " + nv.getEmail());
                System.out.println("Dia chi: " + nv.getDiaChi());
                System.out.println("Gioi tinh: " + nv.getGioiTinh());
                System.out.println("Nhom: " + nv.getNhom());
                System.out.println("Trang thai: " + nv.getTrangThai());
                found = true;
            }
        }
        if(!found){
                System.out.println("Khong tim thay ket qua phu hop!");
            }
    }

    static void themMoiNhanVien(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----Them moi nhan vien----");
        System.out.println();
        boolean isMaNVHopLe;
        do {
            System.out.print("Nhap ma nhan vien: ");
            nv.setMaNV(sc.nextLine());
            isMaNVHopLe = kiemTraMaNV(nv.getMaNV());
        } while (!isMaNVHopLe);

        do{
            System.out.print("Ten nhan vien: "); 
            nv.setHoTen(sc.nextLine());
            if(!kiemTraTen(nv.getHoTen())){
                System.out.println("Ten khong duoc chua so!");
            }
        }while(!kiemTraTen(nv.getHoTen()));
        nv.setHoTen(chuanHoaChuoi(nv.getHoTen()));   

        do{
            System.out.print("So dien thoai: ");
            nv.setSdt(sc.nextLine());
            if(!kiemTraSDT(nv.getSdt())){
                System.out.println("So dien thoai ban vua nhap khong hop le!");
            }
        }while(!kiemTraSDT(nv.getSdt()));

        do {
            System.out.print("email: ");
            nv.setEmail(sc.nextLine());
            if(!kiemTraEmail(nv.getEmail())){
                System.out.println("Email ban vua nhap khong hop le!");
            }
        } while (!kiemTraEmail(nv.getEmail()));

        do{
            System.out.print("Nhap mat khau nhan vien: ");
            nv.setMatKhau(sc.nextLine());
            if(!kiemTraMatKhau(nv.getMatKhau(), nv.getSdt(), nv.getMaNV())){
                System.out.println("Mat khau ban nhap vao khong hop le!");
            }
        }while(!kiemTraMatKhau(nv.getMatKhau(), nv.getSdt(), nv.getMaNV()));
        String matKhauMaHoa = maHoaMD5(nv.getMatKhau());
        nv.setMatKhau(matKhauMaHoa);

        System.out.print("Nhap vao dia chi: ");
        nv.setDiaChi(sc.nextLine());
        nv.setDiaChi(chuanHoaChuoi(nv.getDiaChi()));

        System.out.print("Nhap vao gioi tinh: ");
        nv.setGioiTinh(sc.nextLine());
        nv.setGioiTinh(chuanHoaChuoi(nv.getGioiTinh()));

        System.out.print("Nhap vao nhom: ");
        nv.setNhom(sc.nextLine());

        System.out.print("Nhap vao trang thai: ");
        nv.setTrangThai(sc.nextLine());
        nv.setTrangThai(chuanHoaChuoi(nv.getTrangThai()));

        NhanVien nhanVienMoi = new NhanVien(nv.getMaNV(), nv.getHoTen(), nv.getSdt(), nv.getEmail(), nv.getMatKhau(), nv.getDiaChi(), nv.getGioiTinh(), nv.getNhom(), nv.getTrangThai());
        dsNhanVien.add(nhanVienMoi);
    }

    static void suaThongTinNhanVien(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma nhan vien can chinh sua: ");
        String maNV = sc.nextLine(); 
        boolean found = false;
        for (NhanVien nv : dsNhanVien) {
            if (nv.getMaNV().equals(maNV)) {
                // Nhập thông tin mới của nhân viên
                System.out.println("Nhap thong tin moi cho nhan vien:");
                System.out.print("Ten nhan vien: "); 
                do{
                    nv.setHoTen(sc.nextLine());
                    if(!kiemTraTen(nv.getHoTen())){
                    System.out.println("Ten khong duoc chua so!");
                }
                }while(!kiemTraTen(nv.getHoTen()));   

                System.out.print("So dien thoai: ");
                do{
                    nv.setSdt(sc.nextLine());
                    if(!kiemTraSDT(nv.getSdt())){
                    System.out.println("So dien thoai ban vua nhap khong hop le!");
                    }
                }while(!kiemTraSDT(nv.getSdt()));

                System.out.print("email: ");
                do {
                    nv.setEmail(sc.nextLine());
                    if(!kiemTraEmail(nv.getEmail())){
                    System.out.println("Email ban vua nhap khong hop le!");
                }
                } while (!kiemTraEmail(nv.getEmail()));

                do{
                    System.out.print("Nhap mat khau nhan vien: ");
                    nv.setMatKhau(sc.nextLine());
                    if(!kiemTraMatKhau(nv.getMatKhau(), nv.getSdt(), nv.getMaNV())){
                    System.out.println("Mat khau ban nhap vao khong hop le!");
                }
                }while(!kiemTraMatKhau(nv.getMatKhau(), nv.getSdt(), nv.getMaNV()));
                String matKhauMaHoa = maHoaMD5(nv.getMatKhau());
                nv.setMatKhau(matKhauMaHoa);

                System.out.print("Nhap vao dia chi: ");
                nv.setDiaChi(sc.nextLine());

                System.out.print("Nhap vao gioi tinh: ");
                nv.setGioiTinh(sc.nextLine());

                System.out.print("Nhap vao nhom: ");
                nv.setNhom(sc.nextLine());

                System.out.print("Nhap vao trang thai: ");
                nv.setTrangThai(sc.nextLine());
            
                System.out.println("Chinh sua thong tin thanh cong!");
                found = true;
                break;
            }
        
        if (!found) {
            System.out.println("Khong tim thay nhan vien co ma " + maNV);
            }
        }
    }

    static void xoaThongTinNhanVien(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao ma nhan vien muon xoa: ");
        String maNV = sc.nextLine();
        boolean found = false;
        for (NhanVien nv : dsNhanVien) {
            if(nv.getMaNV().equals(maNV)){
                dsNhanVien.remove(nv);
                found = true;
                break;
            }
        }
        if(found){
            System.out.println("Da xoa nhan vien co ma nhan vien la: " + maNV);
        }
        else{
            System.out.println("Khong ton tai nhan vien co ma vua nhap!");
        }
    }

    static boolean kiemTraMaNV(String maNV){
        boolean hopLe = true; // Giả định mã khách hàng hợp lệ ban đầu
    
        if (!maNV.matches("NV\\d{3}")) {
            System.out.println("Ma khach hang khong hop le!");
            hopLe = false; // Mã không hợp lệ
        } else {
            boolean tonTai = false; // Biến để kiểm tra mã đã tồn tại hay chưa
            for (NhanVien nv : dsNhanVien) {
                if (nv.getMaNV().equals(maNV)) {
                    tonTai = false;
                    break; // Không cần kiểm tra tiếp
                }
            }
            if (tonTai) {
                System.out.println("Ma nhan vien da ton tai: " + maNV);
                hopLe = false; // Mã đã tồn tại
            }
        }
        return hopLe;
    }

    static boolean kiemTraMatKhau(String matKhau, String sdt, String maNV){
        if(matKhau.equals(maNV) || matKhau.equals(sdt)){
            return false;
        }
        if (matKhau.length() < 8 || matKhau.length() > 16) {
            return false;
        }
    
        // Kiểm tra chứa ít nhất một ký tự chữ
        if (!matKhau.matches(".*[a-zA-Z].*")) {
            return false;
        }
    
        // Kiểm tra chứa ít nhất một ký tự số
        if (!matKhau.matches(".*\\d.*")) {
            return false;
        }
    
        // Kiểm tra chứa ít nhất một ký tự đặc biệt
        if (!matKhau.matches(".*[@#$%^&+=].*")) {
            return false;
        }
    
        // Kiểm tra chứa ít nhất một ký tự viết hoa
        if (!matKhau.matches(".*[A-Z].*")) {
            return false;
        }
        return true; 
    }

    public static String maHoaMD5(String input) {
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();

        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    } catch (Exception e) {
        // Xử lý nếu có lỗi
        e.printStackTrace();
        return null;
        }
    }
}
