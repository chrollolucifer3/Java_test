package HoaDon;

import static KhachHang.QLKhachHang.themMoiKhachHang;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import DangNhap.UserSingleton;
import KhachHang.KhachHang;
import KhachHang.QLKhachHang;
import SanPham.QLSanPham;
import SanPham.SanPham;

public class QLBanHang {
    static ArrayList<HoaDon> gioHang = new ArrayList<>();
    static QLKhachHang qlKhachHang = new QLKhachHang();
    static QLSanPham qlSanPham = new QLSanPham();

    public void main() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("----QUAN LY BAN HANG----");
            System.out.println("1. Them moi hoa don ban hang");
            System.out.println("2. Thong ke hoa don ban trong 1 ngay");
            System.out.println("3. Hoa don ban trong 1 thang");
            System.out.println("4. Kiem tra hang hoa");
            System.out.println("5. Bao cao doanh thu cac nhan vien theo thang");
            System.out.println("6. Thoat");
            System.out.print("Nhap lua chon cua ban: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    themMoiHoaDon();
                    break;
                case 2:
                    System.out.print("Nhap ngay (dd/MM/yyyy): ");
                    String ngay = sc.nextLine();
                    xemDanhSachHoaDonTrongNgay(ngay);
                    break;
                case 3:
                    System.out.print("Nhap thang (theo dinh dang MM/yyyy): ");
                    String thang = sc.nextLine();
                    xemDanhSachHoaDonTrongThang(thang);
                    break;
                case 4:
                    xemDanhSachSanPham();
                    break;
                case 5:
                    System.out.print("Nhap vao thang: ");
                    String thangMuonNhap = sc.nextLine();
                    xemDoanhThuTheoNhanVienTrongThang(thangMuonNhap);
                    break;
                case 6:
                    System.out.println("Da thoat khoi chuong trinh!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le, vui long nhap lai!");
            }
        }
    }

    static void themMoiHoaDon() {
        Scanner sc = new Scanner(System.in);

        System.out.println("----THEM MOI HOA DON BAN HANG----");

        boolean tiepTucMuaHang = true;
        while (tiepTucMuaHang) {
            // Bước 2: Nhập mã khách hàng và tìm khách hàng
            System.out.print("Nhap ma khach hang: ");
            String maKhachHang = sc.nextLine();
            KhachHang khachHang = timKiemKhachHang(maKhachHang);
            if (khachHang == null) {
                System.out.println("Khach hang khong ton tai. Vui long them moi thong tin khach hang.");
                themMoiKhachHang();
                QLKhachHang.luuKhachHangVaoFile();
                continue; // Quay lại nhập mã khách hàng
            }

            System.out.println("Thong tin khach hang: ");
            System.out.println("Ma khach hang: " + khachHang.getMaKH());
            System.out.println("Ten khach hang: " + khachHang.getHoTen());
            System.out.println("So dien thoai: " + khachHang.getSdt());
            System.out.println("Dia chi: " + khachHang.getDiaChi());
            // Bước 3: Nhập mã sản phẩm và số lượng mua
            boolean tiepTucMuaHangSanPham = true;
            while (tiepTucMuaHangSanPham) {
                System.out.print("Nhap ma san pham: ");
                String maSanPham = sc.nextLine();
                SanPham sanPham = timKiemSanPham(maSanPham);
                if (sanPham == null) {
                    System.out.println("San pham khong ton tai. Vui long nhap lai.");
                    continue; // Quay lại nhập mã sản phẩm
                }

                System.out.println("Thong tin san pham:");
                // ... In thông tin sản phẩm
                System.out.println("Ma san pham: " + sanPham.getMaSP());
                System.out.println("Ten san pham: " + sanPham.getTenSP());
                System.out.println("Don gia: " + sanPham.getDonGia());
                System.out.println("So luong con lai: " + sanPham.getSoLuong());

                if (sanPham.getSoLuong() == 0) {
                    System.out.println("San pham da het hang.");
                    System.out.print("Ban co muon nhap ma san pham khac? (co/khong): ");
                    String luaChonNhapLai = sc.nextLine();
                    if (luaChonNhapLai.equalsIgnoreCase("khong")) {
                        tiepTucMuaHangSanPham = false;
                    }
                } else {
                    System.out.print("Nhap so luong mua: ");
                    int soLuongMua = sc.nextInt();
                    sc.nextLine();

                    if (soLuongMua <= 0 || soLuongMua > sanPham.getSoLuong()) {
                        System.out.println("So luong mua khong hop le. Vui long nhap lai.");
                        continue; // Quay lại nhập số lượng mua
                    }

                    QLSanPham.truSoLuongSanPham(maSanPham, soLuongMua);

                    // Tạo đối tượng HoaDon mới và thêm vào danh sách gioHang
                    HoaDon hoaDon = new HoaDon();
                    hoaDon.setMaKH(khachHang.getMaKH());
                    hoaDon.setMaSP(sanPham.getMaSP());
                    hoaDon.setmaNhanVien(UserSingleton.getInstance().getUser().getMaNV()); // Lấy mã nhân viên từ Singleton
                    hoaDon.setSoLuongMua(soLuongMua);
                    hoaDon.setDonGia(sanPham.getDonGia());
                    hoaDon.setThanhTien(soLuongMua * sanPham.getDonGia());
                    hoaDon.setNgayBan(getCurrentDate()); // Gán giá trị của biến ngày bán
                    gioHang.add(hoaDon);                    

                    System.out.print("Ban co muon tiep tuc mua hang? (co/khong): ");
                    String luaChon = sc.nextLine();
                    if (luaChon.equalsIgnoreCase("khong")) {
                        tiepTucMuaHangSanPham = false;
                    }
                }
            }

            // Bước 5: Chọn tiếp tục mua hàng hoặc thanh toán
            if (!gioHang.isEmpty()) {
                System.out.print("Chon tien hanh thanh toan hoac tiep tuc mua hang (thanh toan/mua hang/thoat): ");
                String luaChonTiepTuc = sc.nextLine();
                if (luaChonTiepTuc.equalsIgnoreCase("thanh toan")) {
                    hienThiThongTinHoaDon(gioHang);
                    double tongTien = tinhTongTienHoaDon(gioHang);
                    hienThiThongTinTongTien(tongTien);
                    luuHoaDonVaoFile(gioHang);
                    gioHang.clear(); // Xóa danh sách hàng hóa sau khi thanh toán
                    tiepTucMuaHang = false;
                } else if (luaChonTiepTuc.equalsIgnoreCase("mua hang")) {
                    System.out.println("Tiep tuc mua hang moi.");
                } else if (luaChonTiepTuc.equalsIgnoreCase("thoat")) {
                    System.out.println("Quay lai menu chinh.");
                    tiepTucMuaHang = false;
                } else {
                    System.out.println("Lua chon khong hop le.");
                }
            } else {
                System.out.println("Khong co san pham trong gio hang.");
                tiepTucMuaHang = false;
            }
            
        }
    }

    static void hienThiThongTinHoaDon(ArrayList<HoaDon> gioHang) {
        System.out.println("----THONG TIN HOA DON----");
        System.out.printf("| %-10s | %-20s | %-20s | %-15s | %-10s | %-15s |\n",
                "STT", "Ma san pham", "Ten san pham", "So luong mua", "Don gia", "Thanh tien");
        for (int i = 0; i < gioHang.size(); i++) {
            HoaDon hoaDon = gioHang.get(i);
            SanPham sanPham = timKiemSanPham(hoaDon.getMaSP());
            double thanhTien = hoaDon.getSoLuongMua() * hoaDon.getDonGia();
            System.out.printf("| %-10d | %-20s | %-20s | %-15d | %-10.2f | %-15.2f |\n",
                    i + 1, sanPham.getMaSP(), sanPham.getTenSP(), hoaDon.getSoLuongMua(),
                    hoaDon.getDonGia(), thanhTien);
        }
        System.out.println();
    }
    static KhachHang timKiemKhachHang(String maKhachHang) {
        for (KhachHang kh : QLKhachHang.getDsKhachHang()) {
            if (kh.getMaKH().equals(maKhachHang)) {
                return kh;
            }
        }
        return null; // Trả về null nếu không tìm thấy khách hàng
    }
    static SanPham timKiemSanPham(String maSanPham) {
        for (SanPham sp : QLSanPham.getDsSanPham()) {
            if (sp.getMaSP().equals(maSanPham)) {
                return sp; // Trả về sản phẩm nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }
    static double tinhTongTienHoaDon(ArrayList<HoaDon> gioHang) {
        double tongTien = 0;
        for (HoaDon hoaDon : gioHang) {
            tongTien += hoaDon.getThanhTien();
        }
        return tongTien;
    }
    
    static void hienThiThongTinTongTien(double tongTien) {
        System.out.println("Tong tien cua hoa don: " + tongTien);
    }

    public static void luuHoaDonVaoFile(ArrayList<HoaDon> gioHang) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("hoadon.txt", true))) {
            for (HoaDon hoaDon : gioHang) {
                String line = hoaDon.getMaKH() + "\t" + hoaDon.getMaSP() + "\t" +
                              hoaDon.getSoLuongMua() + "\t" + hoaDon.getDonGia() + "\t" +
                              hoaDon.getThanhTien() + "\t" + hoaDon.getNgayBan() +
                              "\t" + hoaDon.getmaNhanVien();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Luu hoa don vao file thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file: " + e.getMessage());
        }
    }
    
     
    static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date currentDate = new Date();
        return sdf.format(currentDate);
    }

    public void xemDanhSachHoaDonTrongNgay(String ngay) {
        String ngay_hd = ngay.substring(0,2);
        String outputFileName = "DanhSachHoaDonNgay" + ngay_hd + ".txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader("hoadon.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
    
            String line;
            double tongTienNgay = 0;
            writer.write("Danh sach hoa don da ban trong ngay " + ngay + ":\n");
            writer.write(String.format("| %-10s | %-20s | %-20s | %-15s | %-10s |\n",
                    "Ma KH", "Ma SP", "So luong mua", "Don gia", "Thanh tien"));

            System.out.println("Danh sach hoa don da ban trong ngay " + ngay + ":");
            System.out.printf("| %-10s | %-20s | %-20s | %-15s | %-10s |\n",
                    "Ma KH", "Ma SP", "So luong mua", "Don gia", "Thanh tien");
    
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 7) {
                    String ngayBan = parts[5];
    
                    if (ngayBan.equals(ngay)) {
                        String maKH = parts[0];
                        String maSP = parts[1];
                        int soLuongMua = Integer.parseInt(parts[2]);
                        double donGia = Double.parseDouble(parts[3]);
                        double thanhTien = Double.parseDouble(parts[4]);
    
                        System.out.printf("| %-10s | %-20s | %-20d | %-15.2f | %-10.2f |\n",
                                maKH, maSP, soLuongMua, donGia, thanhTien);
                        tongTienNgay += thanhTien;
                        String formattedLine = String.format("| %-10s | %-20s | %-20d | %-15.2f | %-10.2f |",
                                maKH, maSP, soLuongMua, donGia, thanhTien);
                        writer.write(formattedLine + "\n"); // Ghi thông tin hóa đơn vào tệp
                    }
                }
            }
    
            System.out.println("Tong so tien ban trong ngay " + ngay + ": " + tongTienNgay);
            writer.write("Tong so tien ban trong ngay " + ngay + ": " + tongTienNgay + "\n"); // Ghi tổng tiền vào tệp
    
            System.out.println("Xuat thong tin hoa don ngay " + ngay + " thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi doc/ghi file: " + e.getMessage());
        }
    }

    public void xemDanhSachHoaDonTrongThang(String thang) {
        String ngay_thang = thang.substring(0,2);
        String outputFileName = "DanhSachHoaDonThang" + ngay_thang + ".txt";
        
        try (BufferedReader reader = new BufferedReader(new FileReader("hoadon.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
    
            String line;
            double tongTienThang = 0;
    
            // Ghi header cho tệp DanhSachHoaDonThang.txt
            writer.write(String.format("| %-10s | %-20s | %-20s | %-15s | %-10s |\n",
                    "Ma KH", "Ma SP", "So luong mua", "Don gia", "Thanh tien"));
    
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length == 7) {
                    String ngayBan = parts[5];
                    String thangHoaDon = ngayBan.substring(3); // Lấy phần tháng từ ngày bán
    
                    if (thangHoaDon.equals(thang)) {
                        String maKH = parts[0];
                        String maSP = parts[1];
                        int soLuongMua = Integer.parseInt(parts[2]);
                        double donGia = Double.parseDouble(parts[3]);
                        double thanhTien = Double.parseDouble(parts[4]);
    
                        String formattedLine = String.format("| %-10s | %-20s | %-20d | %-15.2f | %-10.2f |",
                                maKH, maSP, soLuongMua, donGia, thanhTien);
    
                        System.out.println(formattedLine); // Hiển thị thông tin hóa đơn ra console
                        writer.write(formattedLine + "\n"); // Ghi thông tin hóa đơn vào tệp DanhSachHoaDonThang.txt
                        tongTienThang += thanhTien;
                    }
                }
            }
    
            System.out.println("Tong so tien ban trong thang " + thang + ": " + tongTienThang);
            // Ghi tổng số tiền bán trong tháng vào tệp DanhSachHoaDonThang.txt
            writer.write("Tong so tien ban trong thang " + thang + ": " + tongTienThang);
            
            System.out.println("Xuat thong tin hoa don thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi doc/ghi file: " + e.getMessage());
        }
    }
    
    // public void xemDanhSachHoaDonTrongThang(String thang) {
    //     try (BufferedReader reader = new BufferedReader(new FileReader("hoadon.txt"))) {
    //         String line;
    //         double tongTienThang = 0;

    //         System.out.println("Danh sach hoa don da ban trong thang " + thang + ":");
    //         System.out.printf("| %-10s | %-20s | %-20s | %-15s | %-10s |\n",
    //                 "Ma KH", "Ma SP", "So luong mua", "Don gia", "Thanh tien");

    //         while ((line = reader.readLine()) != null) {
    //             String[] parts = line.split("\t");
    //             if (parts.length == 7) {
    //                 String ngayBan = parts[5];
    //                 String thangHoaDon = ngayBan.substring(3); // Lấy phần tháng/năm từ ngày bán

    //                 if (thangHoaDon.equals(thang)) {
    //                     String maKH = parts[0];
    //                     String maSP = parts[1];
    //                     int soLuongMua = Integer.parseInt(parts[2]);
    //                     double donGia = Double.parseDouble(parts[3]);
    //                     double thanhTien = Double.parseDouble(parts[4]);

    //                     System.out.printf("| %-10s | %-20s | %-20d | %-15.2f | %-10.2f |\n",
    //                             maKH, maSP, soLuongMua, donGia, thanhTien);
    //                     tongTienThang += thanhTien;
    //                 }
    //             }
    //         }

    //         System.out.println("Tong so tien ban trong thang " + thang + ": " + tongTienThang);
    //     } catch (IOException e) {
    //         System.out.println("Loi khi doc file: " + e.getMessage());
    //     }
    // }

    public void xemDanhSachSanPham() {
        try (BufferedReader reader = new BufferedReader(new FileReader("DanhSachSanPham.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("ThongTinSanPham.txt"))) {
            
            String line;
            // Ghi header cho tệp ThongTinSanPham.txt
            writer.write(String.format("| %-10s | %-30s | %-10s | %-15s |\n","Ma SP", "Ten SP", "So luong", "Tinh trang"));
    
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                String maSP = parts[0];
                String tenSP = parts[1];
                int soLuong = Integer.parseInt(parts[2]);
    
                String tinhTrang = "";
                if (soLuong < 10) {
                    tinhTrang = "Sap het";
                } else if (soLuong > 100) {
                    tinhTrang = "Ton kho";
                }
    
                if (!tinhTrang.isEmpty()) {
                    String formattedLine = String.format("| %-10s | %-30s | %-10d | %-15s |", maSP, tenSP, soLuong, tinhTrang);
                    System.out.printf("| %-10s | %-30s | %-10s | %-15s |\n","Ma SP", "Ten SP", "So luong", "Tinh trang");
                    System.out.println(formattedLine); // Hiển thị thông tin sản phẩm ra console
                    writer.write(formattedLine + "\n"); // Ghi thông tin sản phẩm vào tệp ThongTinSanPham.txt
                }
            }
            System.out.println("Xuat thong tin san pham thanh cong!");
        } catch (IOException e) {
            System.out.println("Loi khi doc/ghi file: " + e.getMessage());
        }
    }
    
    public void xemDoanhThuTheoNhanVienTrongThang(String thangNam) {
        try (BufferedReader reader = new BufferedReader(new FileReader("hoadon.txt"));
             BufferedWriter reportWriter = new BufferedWriter(new FileWriter("baocao_doanhthu.txt", true))) {
    
            String line;
            String currentNhanVien = ""; // Biến để lưu mã nhân viên hiện tại
            double currentDoanhThu = 0; // Biến để lưu doanh thu hiện tại
    
            System.out.println("Doanh thu theo nhan vien trong thang " + thangNam + ":");
            System.out.printf("| %-10s | %-20s |\n", "Ma NV", "Doanh Thu");
    
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\t");
                String maNhanVien = parts[6];
                double thanhTien = Double.parseDouble(parts[4]);
                String ngayBan = parts[5];
                String thangBan = ngayBan.substring(3);
    
                if (thangBan.equals(thangNam)) {
                    if (!maNhanVien.equals(currentNhanVien)) {
                        if (!currentNhanVien.isEmpty()) {
                            double currentDoanhThuRounded = Math.round(currentDoanhThu * 100.0) / 100.0;
                            System.out.printf("| %-10s | %-20.2f |\n", currentNhanVien, currentDoanhThuRounded);
                            
                            // Xuất vào file báo cáo
                            reportWriter.write("Nhan vien " + currentNhanVien + " - Thang " + thangNam + ": " + currentDoanhThuRounded);
                            reportWriter.newLine();
                        }
    
                        currentNhanVien = maNhanVien;
                        currentDoanhThu = 0;
                    }
                    currentDoanhThu += thanhTien;
                }
            }
    
            // In thông tin cho nhân viên cuối cùng nếu có
            if (!currentNhanVien.isEmpty()) {
                double currentDoanhThuRounded = Math.round(currentDoanhThu * 100.0) / 100.0;
                System.out.printf("| %-10s | %-20.2f |\n", currentNhanVien, currentDoanhThuRounded);
                
                // Xuất vào file báo cáo
                reportWriter.write("Nhan vien " + currentNhanVien + " - Thang " + thangNam + ": " + currentDoanhThuRounded);
                reportWriter.newLine();
            }
    
        } catch (IOException e) {
            System.out.println("Loi khi doc file hoa don: " + e.getMessage());
        }
    }    
}

