package KhachHang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Nguoi.QuanLy;

public class QLKhachHang extends QuanLy {
    private static ArrayList<KhachHang> dsKhachHang = new ArrayList<>();
    private static KhachHang kh = new KhachHang();
    public void main() {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("----Quan ly thong tin khach hang-----");
            System.out.println("1. Xem danh sach khach hang");
            System.out.println("2. Tim kiem khach hang");
            System.out.println("3. Them moi khach hang");
            System.out.println("4. Sua thong tin khach hang");
            System.out.println("5. Xoa thong tin khach hang");
            System.out.println("6. Thoat");
            System.out.println("Vui long nhap vao lua chon cua ban!");
            int chon = sc.nextInt();
            sc.nextLine();
            
            switch (chon) {
                case 1:
                    hienDanhSachKhachHang(dsKhachHang);
                    break;
                case 2:
                    timKiemKhachHang();
                    break;
                case 3:
                    themMoiKhachHang();
                    break;
                case 4:
                    suaThongTinKhachHang();
                    break;
                case 5:
                    xoaThongTinKhachHang();
                    break;
                case 6:
                    luuKhachHangVaoFile();
                    System.out.println("Da thoat khoi chuong trinh!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le, yeu cau nhap lai!");
            }
        }
    }

    private static void hienDanhSachKhachHang(ArrayList<KhachHang> danhSachKhachHang){
        System.out.println("  | STT |    Ma Khach Hang    |    Ten Khach Hang    |  So Dien Thoai  |        Email        |   Dia Chi   ");
        System.out.println("-----------------------------------------------------------------------------------------------------------");
    
        for (int i = 0; i < danhSachKhachHang.size(); i++) {
            KhachHang kh = danhSachKhachHang.get(i);
            int stt = i + 1;
    
            System.out.printf("  | %3d | %-18s  | %-20s | %15s | %-20s | %-11s ",
            stt, kh.getMaKH(), kh.getHoTen(), kh.getSdt(), kh.getEmail(), kh.getDiaChi());
            System.out.println();
        }
    }

    public static void timKiemKhachHang(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao ma khach hang hoac email/SDT: ");
        String value = sc.nextLine();
         boolean found = false;
        for (KhachHang kh : dsKhachHang) {
            if(kh.getMaKH().equals(value) || kh.getEmail().equals(value) || kh.getSdt().equals(value)){
                System.out.println("Ma khach hang: " + kh.getMaKH());
                System.out.println("Ten khach hang: " + kh.getHoTen());
                System.out.println("So dien thoai: " + kh.getSdt());
                System.out.println("Email: " + kh.getEmail());
                System.out.println("Dia chi: " + kh.getDiaChi());
                found = true;
            }
        }
        if(!found){
                System.out.println("Khong tim thay ket qua phu hop!");
            }
    }

    public static void themMoiKhachHang(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----Them moi khach hang----");
        System.out.println();
        boolean isMaKHHopLe;
        do {
            System.out.print("Nhap ma khach hang: ");
            kh.setMaKH(sc.nextLine());
            isMaKHHopLe = kiemTraMaKH(kh.getMaKH());
        } while (!isMaKHHopLe);

        do{
            System.out.print("Ten khach hang: ");
            kh.setHoTen(sc.nextLine());
            if(!kiemTraTen(kh.getHoTen())){
                System.out.println("Ten khong duoc chua so!");
            }
        }while(!kiemTraTen(kh.getHoTen()));
        kh.setHoTen(chuanHoaChuoi(kh.getHoTen()));   

        do{
            System.out.print("So dien thoai: ");
            kh.setSdt(sc.nextLine());
            if(!kiemTraSDT(kh.getSdt())){
                System.out.println("So dien thoai ban vua nhap khong hop le!");
            }
        }while(!kiemTraSDT(kh.getSdt()));

        do {
            System.out.print("email: ");
            kh.setEmail(sc.nextLine());
            if(!kiemTraEmail(kh.getEmail())){
                System.out.println("Email ban vua nhap khong hop le!");
            }
        } while (!kiemTraEmail(kh.getEmail()));

        System.out.print("Nhap vao dia chi: ");
        kh.setDiaChi(sc.nextLine());
        kh.setDiaChi(chuanHoaChuoi(kh.getDiaChi()));

        KhachHang khachHangMoi = new KhachHang(kh.getMaKH(), kh.getHoTen(), kh.getSdt(), kh.getEmail(), kh.getDiaChi());
        dsKhachHang.add(khachHangMoi);
    }

    static void suaThongTinKhachHang(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma khach hang can chinh sua: ");
        String maKH = sc.nextLine(); 
        boolean found = false;
        for (KhachHang kh : dsKhachHang) {
            if (kh.getMaKH().equals(maKH)) {
                // Nhập thông tin mới của nhân viên
                System.out.println("Nhap thong tin moi cho Khach hang:");
                System.out.print("Ten Khach hang: "); 
                do{
                    kh.setHoTen(sc.nextLine());
                    if(!kiemTraTen(kh.getHoTen())){
                    System.out.println("Ten khong duoc chua so!");
                }
                }while(!kiemTraTen(kh.getHoTen()));   

                System.out.print("So dien thoai: ");
                do{
                    kh.setSdt(sc.nextLine());
                    if(!kiemTraSDT(kh.getSdt())){
                    System.out.println("So dien thoai ban vua nhap khong hop le!");
                    }
                }while(!kiemTraSDT(kh.getSdt()));

                System.out.print("email: ");
                do {
                    kh.setEmail(sc.nextLine());
                    if(!kiemTraEmail(kh.getEmail())){
                    System.out.println("Email ban vua nhap khong hop le!");
                }
                } while (!kiemTraEmail(kh.getEmail()));

                System.out.print("Nhap vao dia chi: ");
                kh.setDiaChi(sc.nextLine());
            
                System.out.println("Chinh sua thong tin thanh cong!");
                found = true;
                break;
            }
        
        if (!found) {
            System.out.println("Khong tim thay khach hang co ma " + maKH);
            }
        }
    }

    static void xoaThongTinKhachHang(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao ma khach hang muon xoa: ");
        String maKH = sc.nextLine();
        boolean found = false;
        for (KhachHang kh : dsKhachHang) {
            if(kh.getMaKH().equals(maKH)){
                dsKhachHang.remove(kh);
                found = true;
                break;
            }
            if(found){
                System.out.println("Da xoa khach hang co ma la: " + maKH);
            }
            else{
                System.out.println("Khong ton tai khach hang co ma vua nhap!");
            }
        }
    }
    static boolean kiemTraMaKH(String maKH) {
        boolean hopLe = true; // Giả định mã khách hàng hợp lệ ban đầu
    
        if (!maKH.matches("KH\\d{3}")) {
            System.out.println("Ma khach hang khong hop le!");
            hopLe = false; // Mã không hợp lệ
        } else {
            boolean tonTai = false; // Biến để kiểm tra mã đã tồn tại hay chưa
            for (KhachHang kh : dsKhachHang) {
                if (kh.getMaKH().equals(maKH)) {
                    tonTai = true;
                    break; // Không cần kiểm tra tiếp
                }
            }
            if (tonTai) {
                System.out.println("Ma khach hang da ton tai: " + maKH);
                hopLe = false; // Mã đã tồn tại
            }
        }
        return hopLe;
    }
        
    public static void loadKhachHangFromFile() {
            try (BufferedReader reader = new BufferedReader(new FileReader("DanhSachKhachHang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {            
                String[] parts = line.split("\t");
                    String ID = parts[0];
                    String hoTen = parts[1];
                    String sdt = parts[2];
                    String email = parts[3];
                    String diaChi = parts[4];
                    dsKhachHang.add(new KhachHang(ID, hoTen, sdt, email, diaChi));
                }
            }
            catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void luuKhachHangVaoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DanhSachKhachHang.txt"))) {
            for (KhachHang kh : dsKhachHang) {

                    writer.write(kh.getMaKH() + "\t" + kh.getHoTen() + "\t" + kh.getSdt() + "\t" + kh.getEmail() + "\t" + kh.getDiaChi());
                    writer.newLine();
                    // Đánh dấu khách hàng là đã được ghi hoặc đã được lưu
            }
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    public static ArrayList<KhachHang> getDsKhachHang() {
        return dsKhachHang;
    }
}

