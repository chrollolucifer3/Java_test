package DangNhap;

import NhanVien.NhanVien;

public class UserSingleton {
    private static UserSingleton instance;
    private NhanVien user;

    private UserSingleton() {
        // Private constructor to prevent instantiation
    }

    public static UserSingleton getInstance() {
        if (instance == null) {
            instance = new UserSingleton();
        }
        return instance;
    }

    public NhanVien getUser() {
        return user;
    }

    public void setUser(NhanVien user) {
        this.user = user;
    }
}
