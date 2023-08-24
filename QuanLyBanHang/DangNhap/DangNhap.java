package DangNhap;

import java.util.ArrayList;
import java.util.Scanner;

import HoaDon.QLBanHang;
import NhanVien.NhanVien;
import NhanVien.QLNhanVien;
import SanPham.QLSanPham;
import KhachHang.QLKhachHang;

public class DangNhap {
    private static ArrayList<NhanVien> dsNhanVien = new ArrayList<>();
    public static NhanVien user = new NhanVien(); 
    static QLNhanVien qlNhanVien = new QLNhanVien();
    static QLKhachHang qlKhachHang = new QLKhachHang();
    static QLSanPham qlSanPham = new QLSanPham();
    static QLBanHang qlBanHang = new QLBanHang();
    public static void main(String[] args) {
        QLNhanVien.loadNhanVienFromFile();
        QLKhachHang.loadKhachHangFromFile();
        QLSanPham.loadSanPhamFromFile();
        dsNhanVien = QLNhanVien.getDsNhanVien();
        Scanner sc = new Scanner(System.in);
        boolean loggedIn = false;
        while (!loggedIn) {
            try {
                System.out.println("----GIAO DIEN DANG NHAP HE THONG----");
                System.out.print("Nhap ma nhan vien: ");
                String maNV = sc.nextLine();
                System.out.print("Nhap mat khau: ");
                String matKhau = sc.nextLine();
                
                // Thực hiện kiểm tra đăng nhập ở đây
                if (kiemTraDangNhap(maNV, matKhau)) {
                    System.out.println("Dang nhap thanh cong!");
                    System.out.println("Chao mung " + UserSingleton.getInstance().getUser().getHoTen() + " da dang nhap vao he thong!");
                    loggedIn = true;
                } else {
                    System.out.println("Sai ma nhan vien hoac mat khau. Vui long thu lai.");
                }
            } catch (Exception e) {
                System.out.println("Co loi xay ra trong qua trinh dang nhap. Vui long thu lai.");
            }
        }
        
        // Sau khi đăng nhập thành công, hiển thị menu và thực hiện các chức năng quản lý
        while (loggedIn) {
            System.out.println("----GIAO DIEN----");
            System.out.println("1. Quan ly nhan vien");
            System.out.println("2. Quan ly khach hang");
            System.out.println("3. Quan ly san pham");
            System.out.println("4. Chuc nang ban hang");
            System.out.println("5. Thong ke");
            System.out.println("6. Thoat");
            System.out.print("Nhap vao lua chon cua ban: ");
            int chon = sc.nextInt();
            sc.nextLine();
            
            switch (chon) {
                case 1:
                    qlNhanVien.main();
                    break;
                case 2:
                    qlKhachHang.main();
                    break;
                case 3:
                    qlSanPham.main();
                    break;
                case 4:
                    qlBanHang.main();
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("Da thoat khoi chuong trinh!");
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Lua chon khong hop le, yeu cau nhap lai!");
            }
        }
    }
    
    static boolean kiemTraDangNhap(String maNV, String matKhau) {
        for (NhanVien nv : dsNhanVien) {
            if (nv.getMaNV().equals(maNV)) {
                String matKhauMaHoa = QLNhanVien.maHoaMD5(matKhau);
                if (nv.getMatKhau().equals(matKhauMaHoa)) {
                    UserSingleton.getInstance().setUser(nv); // Lưu thông tin người dùng vào UserSingleton
                    return true;
                }
            }
        }
        return false;
    }
}