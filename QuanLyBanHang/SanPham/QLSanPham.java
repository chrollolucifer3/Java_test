package SanPham;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class QLSanPham {
    private static ArrayList<SanPham> dsSanPham = new ArrayList<>();
    private static SanPham sp = new SanPham();
    public void main() {
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("----Quan Ly San Pham----");
            System.out.println("1. Xem toan bo san pham");
            System.out.println("2. Tim kiem san pham");
            System.out.println("3. Them moi san pham");
            System.out.println("4. Sua thong tin san pham");
            System.out.println("5. Xoa thong tin san pham");
            System.out.println("6. Thoat");
            System.out.print("Nhap vao lua chon ban muon nhap: ");
            int chon = sc.nextInt();
            sc.nextLine();
            switch (chon) {
                case 1:
                    xemDanhSachSanPham(dsSanPham);
                    break;
                case 2:
                    timKiemSanPham();
                    break;
                case 3:
                    themMoiSanPham();
                    break;
                case 4:
                    suaThongTinSamPham();
                    break;
                case 5: 
                    xoaThongTinSamPham();
                    break;
                case 6:
                    luuSanPhamVaoFile();
                    System.out.println("Da thoat khoi chuong trinh");
                    return;
                default:
                    System.out.println("Nhap sai dinh dang, yeu cau nhap lai!");
            }
        }
    }

    static void xemDanhSachSanPham(ArrayList<SanPham> danhSachSanPham){
        System.out.println("  | STT |    Ma San Pham    |    Ten San Pham    |  So Luong  |        Don Gia        ");
        System.out.println("--------------------------------------------------------------------------------------");
    
        for (int i = 0; i < danhSachSanPham.size(); i++) {
            SanPham sp = danhSachSanPham.get(i);
            int stt = i + 1;
    
            System.out.printf("  | %3d | %-16s  | %-18s | %-10s | %-16s ",
            stt, sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getDonGia());
            System.out.println();
        }
    }

    static void timKiemSanPham(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao ma san pham: ");
        String value = sc.nextLine();
         boolean found = false;
        for (SanPham sp : dsSanPham) {
            if(sp.getMaSP().equals(value)){
                System.out.println("Ma san pham: " + sp.getMaSP());
                System.out.println("Ten san pham: " + sp.getTenSP());
                System.out.println("So luong: " + sp.getSoLuong());
                System.out.println("Don gia: " + sp.getDonGia());
                found = true;
            }
        }
        if(!found){
                System.out.println("Khong tim thay ket qua phu hop!");
            }
    }

    static void themMoiSanPham(){
        Scanner sc = new Scanner(System.in);
        System.out.println("----Them moi san pham----");
        System.out.println();
        boolean isMaSPHopLe;
        do{
        System.out.print("Nhap ma san pham: ");
        sp.setMaSP(sc.nextLine());
        isMaSPHopLe = kiemTraMaSP(sp.getMaSP());
        }while(!isMaSPHopLe);

        System.out.print("Ten san pham: "); 
        sp.setTenSP(sc.nextLine());
        sp.setTenSP(chuanHoaChuoi(sp.getTenSP()));   

        System.out.print("So luong: ");
        do {
            sp.setSoLuong(sc.nextInt());
            sc.nextLine();
            if(sp.getSoLuong() <= 0){
                System.out.println("So luong ban vua nhap khong hop le!");
            }
        } while (sp.getSoLuong() <= 0);

        System.out.print("Don Gia: ");
        do {
            sp.setDonGia(sc.nextDouble());
            sc.nextLine();
            if(sp.getDonGia() <= 0){
                System.out.println("Don Gia ban vua nhap khong hop le!");
            }
        } while (sp.getDonGia() <= 0);

        SanPham sanPhamMoi = new SanPham(sp.getMaSP(), sp.getTenSP(), sp.getSoLuong(), sp.getDonGia());
        dsSanPham.add(sanPhamMoi);
    }

    static void suaThongTinSamPham(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ma khach hang can chinh sua: ");
        String maSP = sc.nextLine(); 
        boolean found = false;
        for (SanPham sp : dsSanPham) {
            if (sp.getMaSP().equals(maSP)) {
                System.out.println("Nhap thong tin moi cho San Pham:");
                System.out.print("Ten San Pham: "); 
                sp.setTenSP(sc.nextLine());
                sp.setTenSP(chuanHoaChuoi(sp.getTenSP()));   

                System.out.print("So luong: ");
                do {
                    sp.setSoLuong(sc.nextInt());
                    sc.nextLine();
                    if(sp.getSoLuong() <= 0){
                        System.out.println("So luong ban vua nhap khong hop le!");
                    }
                } while (sp.getSoLuong() <= 0);

                System.out.print("Don Gia: ");
                do {
                    sp.setDonGia(sc.nextDouble());
                    sc.nextLine();
                    if(sp.getDonGia() <= 0){
                        System.out.println("Don Gia ban vua nhap khong hop le!");
                    }
                } while (sp.getDonGia() <= 0);

                System.out.println("Chinh sua thong tin thanh cong!");
                found = true;
                break;
            }
        
        if (!found) {
            System.out.println("Khong tim thay San Pham co ma " + maSP);
            }
        }
    }

    static void xoaThongTinSamPham(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap vao ma san pham muon xoa: ");
        String maSP = sc.nextLine();
        boolean found = false;
        for (SanPham sp : dsSanPham) {
            if(sp.getMaSP().equals(maSP)){
                dsSanPham.remove(sp);
                found = true;
                break;
            }
            if(found){
                System.out.println("Da xoa san pham co ma la: " + maSP);
            }
            else{
                System.out.println("Khong ton tai san pham co ma vua nhap!");
            }
        }
    }

    static boolean kiemTraMaSP(String maSP){
        boolean hopLe = true;
        if(!maSP.matches("SP\\d{3}")){
            System.out.println("Ma SP ban vua nhap khong hop le!");
            hopLe = false;
        } else{
            boolean tonTai = false;
            for (SanPham sp : dsSanPham) {
                if(sp.getMaSP().equals(maSP)){
                    System.out.println("Ma SP ban vua nhap da ton tai!");
                    tonTai = true;
                    break;
                }
            }
            if(tonTai){
                hopLe = false;
            }
        }
        return hopLe;
    }

    static String chuanHoaChuoi(String s){
        s = s.trim();
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
    
        for (String word : words) {
            if (!word.isEmpty()) {  // Skip empty words
                String firstLetter = word.substring(0, 1).toUpperCase();
                String restOfWord = word.substring(1).toLowerCase();
                sb.append(firstLetter).append(restOfWord).append(' ');
            }
        }
    
        sb.deleteCharAt(sb.length() - 1); // Remove the trailing space
        return sb.toString();
    }

    public static void loadSanPhamFromFile() {
            try (BufferedReader reader = new BufferedReader(new FileReader("DanhSachSanPham.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                // if (parts.length != 4) {
                //     System.out.println("Dòng không đu thông tin: " + line);
                //     continue; // Bỏ qua dòng không hợp lệ
                // }
                    String ID = parts[0];
                    String tenSP = parts[1];
                    int soLuong = Integer.parseInt(parts[2]);
                    double donGia = Double.parseDouble(parts[3]);
                    dsSanPham.add(new SanPham(ID, tenSP, soLuong, donGia));
                }
            }
            catch (IOException e) {
            System.out.println("Lỗi khi đọc file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    static void luuSanPhamVaoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("DanhSachSanPham.txt"))) {
            for (SanPham sp : dsSanPham) {
                writer.write(sp.getMaSP() + "\t" + sp.getTenSP() + "\t" + sp.getSoLuong() + "\t" + sp.getDonGia());
                writer.newLine();
            }
            System.out.println("Luu danh sach san pham thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }

    public static ArrayList<SanPham> getDsSanPham() {
        return dsSanPham;
    }

    public static void truSoLuongSanPham(String maSanPham, int soLuongMua) {
    for (SanPham sp : dsSanPham) {
        if (sp.getMaSP().equals(maSanPham)) {
            int soLuongCu = sp.getSoLuong();
            if (soLuongCu <= 0) {
                System.out.println("Hang da het.");
                return;
            }
            int soLuongMoi = soLuongCu - soLuongMua;
            if (soLuongMoi >= 0) {
                sp.setSoLuong(soLuongMoi);
                break;
            } else {
                System.out.println("Khong du so luong trong kho.");
            }
        }
    }
    luuSanPhamVaoFile(); // Sau khi cập nhật số lượng, lưu lại vào file
}
}

