package com.levancam.demo6.models;
import com.levancam.demo6.controllers.LoginController;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class User extends ConnectDatabase{
    //---------------UpLoad user---------------------
    public void UpLoadlogin( String usernamethaythe, String passwordthaythe,String maNVthaythe) throws SQLException, ClassNotFoundException {
        try (Connection con = getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement("UPDATE login SET username = ?, password = ? WHERE maNV = ?")) {
            pstmt.setString(1, usernamethaythe);
            pstmt.setString(2, passwordthaythe);
            pstmt.setString(3, maNVthaythe);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successfully!");
            } else {
                System.out.println("No row updated. MaNV not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //----------------------Upload dự án mới ---------------------------------------
    public void UpLoadNewProject( String tieude, String mota,String thoigiankhoicong, String thoigianhoanthanh) throws SQLException, ClassNotFoundException {
        try (Connection con = getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement("UPDATE du_an SET tieu_de = ?, mo_ta = ?,thoi_gian_khoi_cong=?, thoi_gian_hoan_thanh=? WHERE id = 1" )) {
            pstmt.setString(1, tieude);
            pstmt.setString(2, mota);
            pstmt.setString(3, thoigiankhoicong);
            pstmt.setString(4,thoigianhoanthanh);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successfully!");
            } else {
                System.out.println("No row updated. MaNV not found.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //-------------------------Upload thongtin--------------------------------
    public void UpLoadThongTin(boolean quanli, String manv, String hoten, String sdt, String diachi, String cccd) throws SQLException, ClassNotFoundException {
        if (quanli) {
            System.out.println("Upload quản lí");
            try (Connection con = getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement pstmt = con.prepareStatement("UPDATE quanli SET ten = ?, diachi = ?, sdt = ?, CCCD = ? WHERE ma_ql = ?")) {
                pstmt.setString(1, hoten);
                pstmt.setString(2, diachi);
                pstmt.setString(3, sdt);
                pstmt.setString(4, cccd);
                pstmt.setString(5, manv); // Đặt giá trị cho tham số thứ 5
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Update successfully!");
                } else {
                    System.out.println("No row updated. MaNV not found.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Upload nhân viên");
            try (Connection con = getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement pstmt = con.prepareStatement("UPDATE nhanvien SET name = ?, diachi = ?, sdt = ?, CCCD = ? WHERE ma_nv = ?")) {
                pstmt.setString(1, hoten);
                pstmt.setString(2, diachi);
                pstmt.setString(3, sdt);
                pstmt.setString(4, cccd);
                pstmt.setString(5, manv); // Đặt giá trị cho tham số thứ 5
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Update successfully!");
                } else {
                    System.out.println("No row updated. MaNV not found.");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //----------------------Upload Tiến độ công việc---------------------------------------
    public void UpLoadTienDo( String trangthai, String thoigiankhoidiem,String thoigianketthuc, String ghichu) throws SQLException, ClassNotFoundException {
        try (Connection con = getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = con.prepareStatement("UPDATE tiendocongviec SET trangThai = ?, thoiGianKhoiDiem = ?, thoiGianKetThuc = ?, ghiChu= ? WHERE id = 1")) {
            pstmt.setString(1, trangthai);
            pstmt.setString(2, thoigiankhoidiem);
            pstmt.setString(3, thoigianketthuc);
            pstmt.setString(4,ghichu);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Update successfully!");

            } else {
                System.out.println("No row updated. MaNV not found.");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //-----------------Upload nghỉ phép------------------------------------
        public void UpLoadNghiPhep(String manv, long songay, String ngaybd, String ngaykt)
        {
            try (Connection con = getConnection(URL, USERNAME, PASSWORD);
                 PreparedStatement pstmt = con.prepareStatement("UPDATE nghi_phep SET so_phep_con_lai = ?,ngay_bat_dau=?,ngay_ket_thuc=? WHERE ma = ?")) {
                songay = new User().songayconlai(manv)-songay;
                pstmt.setLong(1,songay);
                pstmt.setString(4, manv);
                pstmt.setString(2,ngaybd);
                pstmt.setString(3,ngaykt);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Update successfully!");

                } else {
                    System.out.println("No row updated. MaNV not found.");

                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

 //------------------------------------get nghi phep------------------------------------
 public  String[] getNghiPhep(String manv) throws SQLException, ClassNotFoundException {
     List<String[]> nghiphepList = getAllNghiPhep();
     for (String[] nghiphep : nghiphepList) {
         if (nghiphep[1].equals(manv)){
             System.out.println("Get nghỉ phéo thành công!");
             return nghiphep;
         }
     }
     System.out.println("hàm get nghỉ phép có vấn đề");
     return null;
 }

    //----------------------get tiến độ ---------------------------------------
    public  String[] getTienDo() throws SQLException, ClassNotFoundException {
        Connection con = getConnection(URL, USERNAME, PASSWORD);
        String[] tiendo = new String[0];
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM  tiendocongviec")) {
            while (rs.next()) {
                tiendo = new String[4];
                tiendo[0] = rs.getString("trangThai");
                tiendo[1] = rs.getString("thoiGianKhoiDiem");
                tiendo[2] = rs.getString("thoiGianKetThuc");
                tiendo[3] = rs.getString("ghiChu");
//                }
            }
        }
        return tiendo;
    }
    //--------------------get dự án----------------------------------
    public  String[] getDuAn() throws SQLException, ClassNotFoundException {
        Connection con = getConnection(URL, USERNAME, PASSWORD);
        String[] duan = new String[0];
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM  du_an")) {
            while (rs.next()) {
                duan = new String[4];
                duan[0] = rs.getString("tieu_de");
                duan[1] = rs.getString("mo_ta");
                duan[2] = rs.getString("thoi_gian_khoi_cong");
                duan[3] = rs.getString("thoi_gian_hoan_thanh");
//                }
            }
        }
        return duan;
    }

    //-------------------------get tài khoản-----------------------------------
    public  String[] getTaiKhoan(String manv) throws SQLException, ClassNotFoundException {
        List<String[]> userList = getAllUser();
        for (String[] user : userList) {
            if (user[0].equals(manv)){
                System.out.println(user[0]+ " "+user[1]+" "+user[2]);
                return user;
            }
        }
        System.out.println("không tìm thấy tài khoản");
        return null;
    }

    //---------------------get All User----------------------------------------------
    public static List<String[]> getAllUser() throws SQLException, ClassNotFoundException {
        Connection con = getConnection(URL, USERNAME, PASSWORD);
        List<String[]> userList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM  login")) {
            while(rs.next()) {
                String[] user = new String[3];
                user[0] = rs.getString("maNV");
                user[1] = rs.getString("username");
                user[2] = rs.getString("password");
//                if(user[1] != null && user[2] != null) {
                    userList.add(user);
//                }
            }
        }
        return userList;
    }

    //----------------------------------------Get all nghỉ phép----------------------------------
    public static List<String[]> getAllNghiPhep() throws SQLException, ClassNotFoundException {
        Connection con = getConnection(URL, USERNAME, PASSWORD);
        List<String[]> nghiphepList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM  nghi_phep")) {
            while(rs.next()) {
                String[] nghiphep = new String[6];
                nghiphep[0] = String.valueOf(rs.getInt("id"));
                nghiphep[1] = rs.getString("ma");
                nghiphep[2] = rs.getString("so_phep_duoc_nghi");
                nghiphep[3] = rs.getString("so_phep_con_lai");
                nghiphep[4] = rs.getString("ngay_bat_dau");
                nghiphep[5] = rs.getString("ngay_ket_thuc");
                nghiphepList.add(nghiphep);
//                }
            }
        }
        return nghiphepList;
    }
    /*-----------------get số ngày còn lại----------------------------------*/
    public long songayconlai(String manv) throws SQLException, ClassNotFoundException {
        List<String[]> nghiphepList = getAllNghiPhep();
        for (String[] nghiphep : nghiphepList) {
            if (nghiphep[1].equals(manv)){
                System.out.println("Số mã còn lại: có mã");
               // int getSoNgayConLai = Integer.parseInt(nghiphep[3]);
                return Integer.parseInt(nghiphep[3]);
            }
        }
        System.out.println("số ngày còn lại : không có mã");
        return -1;

    }
//-------------------------------get thong tin người dùng-----------------------------------------
    public String[] getthongtin(String matext, boolean quanli) throws SQLException, ClassNotFoundException {
        if (quanli){
            System.out.println("TH1");
            String[] TTquanli = new String[6];
            List<String[]> quanliList = getAllquanli();
            for (String[] ql : quanliList){
                for (int i=0;i<6;i++){
                    System.out.println(ql[i]);
                }
                if (ql[1].equals(matext)){
                    TTquanli[0]=ql[0];
                    TTquanli[1]=ql[1];
                    TTquanli[2]=ql[2];
                    TTquanli[3]=ql[3];
                    TTquanli[4]=ql[4];
                    TTquanli[5]=ql[5];
                    System.out.println("QUản lí");
                    return TTquanli;

                }
            }

        }
        else {
            System.out.println("TH2");
            String[] TTnhanvien = new String[6];
            List<String[]> nhanvienList = getAllNhanVien();
            for (String[] nv : nhanvienList){
                if (nv[1].equals(matext)){
                    TTnhanvien[0]= nv[0];
                    TTnhanvien[1]= nv[1];
                    TTnhanvien[2]= nv[3];
                    TTnhanvien[3]= nv[5];
                    TTnhanvien[4]= nv[4];
                    TTnhanvien[5]= nv[6];
                    System.out.println("Nhân viên;");
                    return TTnhanvien;
                }
            }

        }
        System.out.println(" ko tìm thấy");
        return null;

    }

    //-----------------------get all NhanVien----------------------------------------------------------
    public static List<String[]> getAllNhanVien() throws SQLException, ClassNotFoundException {
        Connection con = getConnection(URL, USERNAME, PASSWORD);
        List<String[]> nhanvienList = new ArrayList<>();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM nhanvien")) {
            while (rs.next()) {
                String[] nhanvien = new String[7];
//                Integer tam = rs.getInt("stt");
//                String Stringtam = tam.toString();
//                 nhanvien[0] = rs.getString(Stringtam);
                nhanvien[0] = String.valueOf(rs.getInt("stt"));
                nhanvien[1] = rs.getString("ma_nv");
                nhanvien[2] = rs.getString("ma_ql");
                nhanvien[3] = rs.getString("name");
                nhanvien[4] = rs.getString("sdt");
                nhanvien[5] = rs.getString("diachi");
                nhanvien[6] = rs.getString("CCCD");
                nhanvienList.add(nhanvien);
            }
        }
        return nhanvienList;
    }

//--------------------------------Get all QuanLi------------------------------------------------
public static List<String[]> getAllquanli() throws SQLException, ClassNotFoundException {
    Connection con = getConnection(URL, USERNAME, PASSWORD);
    List<String[]> quanliList = new ArrayList<>();
    try (Statement stmt = con.createStatement();
         ResultSet rs = stmt.executeQuery("SELECT * FROM  quanli")) {
        while(rs.next()) {
            String[] quanli = new String[6];
         // //quanli[0] = stt;
            quanli[0] = String.valueOf(rs.getInt("stt"));
            quanli[1] = rs.getString("ma_ql");
            quanli[2] = rs.getString("ten");
            quanli[3] = rs.getString("diachi");
            quanli[4] = rs.getString("sdt");
            quanli[5] = rs.getString("CCCD");
            quanliList.add(quanli);
        }
    }
    return quanliList;
}

//--------------------------------------Get số lượng nhân viên------------------------
    public long getSoLuongNhanVien() throws SQLException, ClassNotFoundException {
        List<String[]> nhanvienList = getAllNhanVien();
        long tam =0;
        for (String[] nhanvien : nhanvienList){
            if (nhanvien[4]!=null){
                tam+=1;
            }
        }
        return tam;
    }

//--------------------------------Get số lượng nhân còn trống------------------------
    public Long getSoLuongNhanVienCanTuyen() throws SQLException, ClassNotFoundException {
        List<String[]> nhanvienList = getAllNhanVien();
        long tam =0;
        tam = nhanvienList.size()-getSoLuongNhanVien();
        return tam;
    }
 //----------------Get số lượng nhân viên chuyên cần--------------------------

    public long getSoLuongNhanVienChuyenCan() throws SQLException, ClassNotFoundException {
        List<String[]> nghiphepList = getAllNghiPhep();
        long tam=0;
        for (String[] nghiphep :nghiphepList){
            if (nghiphep[3].equals("10") && checkEmployee(nghiphep[1])){
                tam+=1;
            }
        }
        return tam;
    }
    public boolean kiemtramanhanvien(String macheck) throws SQLException, ClassNotFoundException {

        List<String[]> nhanvienList = getAllNhanVien();
        for (String[] nhanvien : nhanvienList){
            if (nhanvien[1].equals(macheck)){
                return true;
            }
        }
        return false;
    }
    public boolean checkEmployee(String macheck) throws SQLException, ClassNotFoundException {

        List<String[]> nhanvienList = getAllNhanVien();
        for (String[] nhanvien : nhanvienList){
            if (nhanvien[1].equals(macheck) && nhanvien[3]!=null){
                return true;
            }
        }
        return false;
    }
//--------------kiểm tra mã (true= quản lí, false = nhân viên)----------------
    public  boolean kiemtraql(String ma) throws SQLException, ClassNotFoundException {
        List<String[]> quanliList = getAllquanli();
        for (String[] quanli: quanliList){
            if (ma.equals(quanli[1])){
                return true;
            }
        }
        return false;
    }
//    public  String kiemtralogin(String username, String password) throws SQLException, ClassNotFoundException {
//        List<String[]> userList = getAllUser();
//        for (String[] user : userList) {
////            System.out.println("DB Username: '" + user[1] + "' DB Password: '" + user[2] + "'");
//            if (username.equals(user[1]) && password.equals(user[2])) {
//                new LoginController().setMa(user[0]);
//                System.out.println(new LoginController().getmaLogin());
//                System.out.println("User is valid!");
//                return user[0];
//
//            }
//        }
//        return null;
//    }

//    ------------------ kiểm tra đăng nhập ---------------------------
public  boolean kiemtraDangNhap(String username, String password) throws SQLException, ClassNotFoundException {
    List<String[]> userList = getAllUser();
    for (String[] user : userList) {
//            System.out.println("DB Username: '" + user[1] + "' DB Password: '" + user[2] + "'");
        if (username.equals(user[1]) && password.equals(user[2])) {
            new LoginController().setMa(user[0]);
            System.out.println(new LoginController().getmaLogin());
            System.out.println("User is valid!");
            return true;

        }
    }
    return false;
}
    //--------------------kiểm tra mã thêm vào database-------------------------------
    public String kiemtraMaUseSign(String ma) throws SQLException, ClassNotFoundException {
        if (ma==null){
            return null;
        }
        List<String[]> userList = getAllUser();
        for (String[] user : userList){
            if (ma.equals(user[0]) && user[1]==null && user[2]==null){
                System.out.println(" có mã trong user");
                return user[0];
            }
        }
        return null;
    }
    public static void main(String[] args) {
        try {

            System.out.println("User");
            List<String[]> userList = getAllUser();
            for (String[] user : userList) {
                System.out.println(user[0] + " " + user[1] + " " + user[2]);
            }
            /*------------------------*/
            System.out.println("Nhân Viên");
            List<String[]> nhanvienList = getAllNhanVien();
            for (String[] nhanvien : nhanvienList) {
                System.out.println(nhanvien[0] + " " + nhanvien[1] + " " + nhanvien[2] + " " + nhanvien[3] + " " + nhanvien[4] + " " + nhanvien[5]);
            }
            /*-----------------------------------------*/
                System.out.println("QUản Lí");
                List<String[]> quanliList = getAllquanli();
                for (String[] quanli : quanliList) {
                    System.out.println(quanli[0] + " " + quanli[1] + " " + quanli[2] + " " + quanli[3] + " " + quanli[4]  );
            }
            System.out.println("Nghỉ phép");
            List<String[]> nghiphepList = getAllNghiPhep();
            for (String[] nghiphep : nghiphepList) {
                System.out.println(nghiphep[0] + " " + nghiphep[1] + " " + nghiphep[2] + " " + nghiphep[3] + " " + nghiphep[4]+" "+nghiphep[5]  );
                System.out.println();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
