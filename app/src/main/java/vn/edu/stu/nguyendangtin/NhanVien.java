package vn.edu.stu.nguyendangtin;

public class NhanVien {
    public int id;
    public String ten;
    public String maPhong;
    public byte[] anh;
    public String loai;
    public String diaChi;

    public NhanVien(int id, String ten, String maPhong, byte[] anh, String loai, String diaChi) {
        this.id = id;
        this.ten = ten;
        this.maPhong = maPhong;
        this.anh = anh;
        this.loai = loai;
        this.diaChi = diaChi;
    }
}
