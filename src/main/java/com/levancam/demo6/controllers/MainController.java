package com.levancam.demo6.controllers;
import java.awt.*;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.levancam.demo6.LoginView;
import com.levancam.demo6.encode.EnCodeMK;
import com.levancam.demo6.models.ConnectDatabase;
import com.levancam.demo6.models.NpDatabase;
import com.levancam.demo6.models.NvDatabase;
import com.levancam.demo6.models.User;
import com.mysql.cj.protocol.ResultBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.annotation.Inherited;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class MainController implements Initializable {
    //--------------dẫn link-------------------
    @FXML
    private TextField linkFB;


    //-------------table view nghỉ phép-------------------------------------
    @FXML
    private TableView<NpDatabase> TableViewOnLeave;
    @FXML
    private TableColumn<NpDatabase,String>  TBVOL_employeeCode ;
    @FXML
    private TableColumn<NpDatabase,String>  TBVOL_maxNumber_Spells ;
    @FXML
    private TableColumn<NpDatabase,String>  TBVOL_RemainingLeave ;
    @FXML
    private TableColumn<NpDatabase,String> TBVOL_startDay;
    @FXML
    private TableColumn<NpDatabase,String> TBVOL_endDay;
    @FXML
    private Label TDdulieukhonghople;

    public ObservableList<NpDatabase> listNP(){
        ObservableList<NpDatabase> listDataNP = FXCollections.observableArrayList();
        String sqlNP= "SELECT * FROM nghi_phep";
        try{
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/nhansu","root","");
            prepare = connect.prepareStatement(sqlNP);
            result = prepare.executeQuery();
            NpDatabase NPD;
            while (result.next()){
                if (new User().checkEmployee(result.getString("ma"))) {
                    NPD = new NpDatabase(result.getString("ma")
                            , result.getString("so_phep_duoc_nghi")
                            , result.getString("so_phep_con_lai")
                            , result.getString("ngay_bat_dau")
                            , result.getString("ngay_ket_thuc")
                    );
                    listDataNP.add(NPD);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listDataNP;
    }
    private ObservableList<NpDatabase> listNP;
    public void NpShowListData() {
       // listNP = this.listNP();
        listNP = FXCollections.observableArrayList(this.listNP());
        if (listNP != null) {
            TBVOL_employeeCode.setCellValueFactory(new PropertyValueFactory<NpDatabase,String>("DTNPma"));
            TBVOL_maxNumber_Spells.setCellValueFactory(new PropertyValueFactory<NpDatabase,String>("DTNPsophepduocnghi"));
            TBVOL_RemainingLeave.setCellValueFactory(new PropertyValueFactory<NpDatabase, String>("DTNPsophepconlai"));
            TBVOL_startDay.setCellValueFactory(new PropertyValueFactory<NpDatabase,String>("DTNPngaybatdau"));
            TBVOL_endDay.setCellValueFactory(new PropertyValueFactory<NpDatabase,String>("DTNPngayketthuc"));
            TableViewOnLeave.setItems(listNP);
        }
        System.out.println("lỗi show listNP");
    }
    //-------------------------------nút chuyển đổi-----------------------
    public void List_seeList(ActionEvent even){
        if (TableViewOnLeave.isVisible()){//dang bật
            TableViewOnLeave.setVisible(false);
            List_seeList.setText("Xem danh sách");
        }
        else{// đang tắt
            TableViewOnLeave.setVisible(true);
            List_seeList.setText("Quay lại");
        }
    }

    //---------------table view nhân viên-------------------------------------
    @FXML
    private TableView<NvDatabase> tableview;
   // private static String ngaybatdauthaythe;
   // private static String ngayketthucthaythe;

    @FXML
    private TableColumn<NvDatabase,String> TBV_employeeCode;

    @FXML
    private TableColumn<NvDatabase,String> TBV_fullName;

    @FXML
    private TableColumn<NvDatabase,String> TBV_phoneNumber;

    @FXML
    private TableColumn<NvDatabase,String> TBV_Address;

    @FXML
    private TableColumn<NvDatabase,String> TBVcccd;
    @FXML
    private Button List_deleteEmployee;
    @FXML
    private Button List_seeList;
    @FXML
    private TextField List_search;
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    @FXML
    private TextField List_addEmployeeCode;
    @FXML
    private Label List_addComplete;
    @FXML
    private Button DSadd;
    @FXML
    private Label List_addEmployeeCodeExisted;


    public void initialize(URL location, ResourceBundle resources) {
        linkFB.setOnMouseClicked(event -> {
            String url = linkFB.getText();
            if (isValidURL(url)) {
                try {
                    // Mở trình duyệt mặc định và mở đường dẫn
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
        NvShowListData();
        NpShowListData();
        time();
        tableview.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Đã chọn nhân viên: " + newValue.getDTCodeEmployee());
            }
        });
        List_search.textProperty().addListener((observable, oldValue, newValue) -> {
            FilteredList<NvDatabase> searchTable = new FilteredList<>(listNV, nv -> true);
            searchTable.setPredicate(nv -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return (nv.getDTCodeEmployee() != null && nv.getDTCodeEmployee().toLowerCase().contains(lowerCaseFilter))
                        || (nv.getDTname() != null && nv.getDTname().toLowerCase().contains(lowerCaseFilter))
                        || (nv.getDTPhoneNumber() != null && nv.getDTPhoneNumber().toLowerCase().contains(lowerCaseFilter))
                        || (nv.getDTAdress() != null && nv.getDTAdress().toLowerCase().contains(lowerCaseFilter))
                        || (nv.getDTcccd() != null && nv.getDTcccd().toLowerCase().contains(lowerCaseFilter));
            });
            tableview.setItems(searchTable);
        });
    }
    public ObservableList<NvDatabase> listNV() {
        ObservableList<NvDatabase> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM nhanvien";
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/nhansu","root","");
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            NvDatabase NVD;
            while (result.next()) {
                NVD = new NvDatabase(result.getString("ma_nv")
                        , result.getString("name")
                        , result.getString("sdt")
                        , result.getString("diachi")
                        , result.getString("CCCD"));
                listData.add(NVD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) result.close();
                if (prepare != null) prepare.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return listData;
    }
    private ObservableList<NvDatabase> listNV;

    public void NvShowListData() {
       // listNV = this.listNV();
        listNV = FXCollections.observableArrayList(this.listNV());
        System.out.println(listNV);
        if (listNV != null) {
        TBV_employeeCode.setCellValueFactory(new PropertyValueFactory<NvDatabase,String>("DTCodeEmployee"));
        TBV_fullName.setCellValueFactory(new PropertyValueFactory<NvDatabase,String>("DTname"));
        TBV_phoneNumber.setCellValueFactory(new PropertyValueFactory<NvDatabase,String>("DTPhoneNumber"));
        TBV_Address.setCellValueFactory(new PropertyValueFactory<NvDatabase,String>("DTAdress"));
        TBVcccd.setCellValueFactory(new PropertyValueFactory<NvDatabase,String>("DTcccd"));
        tableview.setItems(listNV);
        }
        System.out.println("lỗi show listNV");
    }


//-----------------Xứ lí table view---------------------------------------------

    private void deleteEmployee(NvDatabase nv) {
        try {
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/nhansu", "root", "");

            // Thực hiện lệnh xóa trong cơ sở dữ liệu dựa trên mã nhân viên (ma_nv)
            String sqlnv = "DELETE FROM nhanvien WHERE ma_nv = ?";
            prepare = connect.prepareStatement(sqlnv);
            prepare.setString(1, nv.getDTCodeEmployee());
            prepare.executeUpdate();
            prepare.close();
            // xóa trong nghiphep
            String sqlnp = "DELETE FROM nghi_phep WHERE ma = ?";
            prepare = connect.prepareStatement(sqlnp);
            prepare.setString(1, nv.getDTCodeEmployee());
            prepare.executeUpdate();
            prepare.close();
            // xóa trong login
            String sqllg = "DELETE FROM login WHERE maNV = ?";
            prepare = connect.prepareStatement(sqllg);
            prepare.setString(1, nv.getDTCodeEmployee());
            prepare.executeUpdate();
            prepare.close();

            // Đóng kết nối
            connect.close();

            System.out.println("Đã xóa nhân viên với ID: " + nv.getDTCodeEmployee());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void List_deleteEmployee(ActionEvent event) {
        // Lấy nhân viên được chọn trong TableView
        NvDatabase selectedNv = tableview.getSelectionModel().getSelectedItem();

        // Kiểm tra xem có nhân viên nào được chọn hay không
        if (selectedNv != null) {
            // Xóa nhân viên từ cơ sở dữ liệu
            deleteEmployee(selectedNv);

            // Cập nhật ObservableList
            listNV.remove(selectedNv);
          //  listNP.remove(selectedNv);
            // Cập nhật ObservableList listNP
            listNP.removeIf(np -> np.getDTNPma().equals(selectedNv.getDTCodeEmployee()));

            // Cập nhật TableViewOnLeave
            TableViewOnLeave.setItems(listNP);

        }
    }
    public void DSadd(ActionEvent even) {
        try {
            System.out.println("Bạn đã ấn vàp thêm nhân viên");
            String maNv = List_addEmployeeCode.getText();

            if (new User().kiemtramanhanvien(maNv)) {
                List_addEmployeeCodeExisted.setVisible(true);
                List_addComplete.setVisible(false);
            } else {
                List_addEmployeeCodeExisted.setVisible(false);
                List_addComplete.setVisible(true);
                // Kết nối đến cơ sở dữ liệu
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/nhansu", "root", "");

                // Thêm nhân viên mới
                String sqlnv = "INSERT INTO nhanvien (ma_nv, ma_ql) VALUES (?, ?)";
                prepare = connect.prepareStatement(sqlnv);
                prepare.setString(1, maNv);
                prepare.setString(2, "Ql001");
                prepare.executeUpdate();
                prepare.close();

                // Thêm vào bảng nghi_phep
                String sqlnp = "INSERT INTO nghi_phep (ma,so_phep_duoc_nghi,so_phep_con_lai) VALUES (?,?,?)";
                prepare = connect.prepareStatement(sqlnp);
                prepare.setString(1, maNv);
                prepare.setString(2,"10");
                prepare.setString(3,"10");
                prepare.executeUpdate();
                prepare.close();
                //Thệm vào bảng login
                String sqllg = "INSERT INTO login (maNV) VALUES (?)";
                prepare = connect.prepareStatement(sqllg);
                prepare.setString(1, maNv);
                prepare.executeUpdate();
                prepare.close();
                // Đóng kết nối
                connect.close();

                // Hiển thị thông báo hoặc cập nhật UI
                System.out.println("Đã thêm nhân viên với ID: " + maNv);
                NvShowListData();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private AnchorPane mainchinh;

    @FXML
    private AnchorPane Profile;

    @FXML
    private TextField Profile_employeeCode;

    @FXML
    private TextField Profile_fullName;

    @FXML
    private TextField Profile_phoneNumber;

    @FXML
    private TextField Profile_Adress;
    @FXML
    private Label Profile_ConfirmEdit;

    @FXML
    private TextField HScccd;
    @FXML
    private TextField Progress_state;
    @FXML
    private Label DKCVvuilongnhapdayduthongtin;

    @FXML
    private TextField Progress_strartTime;

    @FXML
    private TextField Progress_endTime;

    @FXML
    private TextArea Progress_note;
    @FXML
    private TextField Onleave_maximumLeaveNumber;

    @FXML
    private TextField Onleave_endDay;

    @FXML
    private TextField OnLeave_startday;

    @FXML
    private TextField onLeave_remainingDaysOfLeave;
    @FXML
    private TextField changePassword_employeeCode;
    @FXML
    private TextField addProject_Title;
    @FXML
    private TextArea addProject_Describe;
    @FXML
    private TextField addProject_startDay;
    @FXML
    private TextField addProject_endDay;
    @FXML
    private TextField Salary_startDay;
    @FXML
    private TextField Salary_endDay;
    @FXML
    private TextField Salary_numberOfDaysOff;
    @FXML
    private TextField Salary_day;
    @FXML
    private TextField Salary_sumSalary;


    @FXML
    private Button Profile_Confirm;

    @FXML
    private Button Profile_edit;
    @FXML
    private Button progress_Edit;
    @FXML Button Onleave_confirm;
    @FXML
    private Button luu;
    @FXML
    private Button Salary_Complain;
    @FXML
    private Button nutienluong;
    @FXML
    private Button Account_changePassword;
    @FXML
    private Button changePassword_return;
    @FXML
    private Button changePassword_changePassword;
    @FXML
    private Button addProject_add;
    @FXML
    private PasswordField changePassword_currentPassword;
    @FXML
    private PasswordField DMKmkmoi;
    @FXML
    private PasswordField changePassword_aganPassword;

    @FXML
    private Label chucvunhanvien;
    @FXML
    private Label daquangaynghiphep;
    @FXML
    private Label NPsophepquahan;
    @FXML
    private Label hiendangtrongquatrinhnghiphep;
    @FXML
    private Label chucvuquanli;
    @FXML
    private Label DKCVtrongtieude;
    @FXML
    private Label DKCVdangkithanhcong;
    @FXML
    private Label nvchuyencan;
    @FXML
    private Label thuongchuyencan;
    @FXML
    private Label DKVCtrongmota;
    @FXML
    private Label DMKdoithanhcong;
    @FXML
    private Label ngay;
    @FXML
    private Label loichuc;
    @FXML
    private Label DKCVtrunglichvoiduandanglam;
    @FXML
    private Label gio;
    @FXML
    private Label DMKsaimk;
    @FXML
    private Label DMKmkkhongtrungkhop;
    @FXML
    private Label DMKsaimanv;
    @FXML
    private Label NPdaxacnhannghiphep;
    @FXML
    private Label NPngaybatdaulonhonngayketthuc;
    @FXML
    private Label TKmanv;
    @FXML
    private Label TKpassword;
    @FXML
    private Label TKusername;
    @FXML
    private Label DSslnv;
    @FXML
    private Label DSslct;
    @FXML
    private Label DSslnvcc;

    @FXML
    private AnchorPane Progress;

   // @FXML
 //   private TextArea Progress_note;

    @FXML
    private AnchorPane onLeave;

    @FXML
    private AnchorPane Account;

    @FXML
    private AnchorPane addProject;

    @FXML
    private AnchorPane Support;

    @FXML
    private AnchorPane DSnhanvien;
    @FXML
    private AnchorPane Salary;
    @FXML
    private AnchorPane changePassword;
    @FXML
    public Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public Stage getStage(){
        return stage;
    }
    private String NgayHienTai;
    public String getNgayHienTai(){
        return NgayHienTai;
    }
    public void setNgayHienTai(String ngay){
        NgayHienTai=ngay;
    }

    public void time() {
        // Tạo một Timeline với KeyFrame cập nhật mỗi giây
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            Date date = new Date();
            SimpleDateFormat tf = new SimpleDateFormat("hh:mm:ss aa");
            SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy");
            String time = tf.format(date);
            ngay.setText(df.format(date));
            setNgayHienTai(new SimpleDateFormat("dd-MM-yyyy").format(date));
            //System.out.println(getNgayHienTai());
            gio.setText(time.split(" ")[0] + " " + time.split(" ")[1]);
            //System.out.println("1 :'"+time.split(" ")[0]+"' 2 :'"+time.split(" ")[1]+"'" );
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            // Chuyển chuỗi thời gian thành đối tượng LocalTime
            LocalTime timechay = LocalTime.parse(time.split(" ")[0], formatter);
            LocalTime time12 = LocalTime.parse("12:00:00", formatter);
            LocalTime time11 = LocalTime.parse("11:00:00",formatter);
            LocalTime time01 = LocalTime.parse("01:00:00",formatter);
            LocalTime time06 = LocalTime.parse("06:00:00",formatter);
            LocalTime time00 = LocalTime.parse("00:00:00",formatter);

            if (timechay.isBefore(time11) &&  timechay.isAfter(time00)&& time.split(" ")[1].equals("SA"))
            {
                loichuc.setText("Chào buổi sáng !!");

            } else
            if (timechay.isBefore(time12) &&  timechay.isAfter(time11)&& time.split(" ")[1].equals("SA"))
            {
                loichuc.setText("Chào buổi trưa !!");
            } else
            if (timechay.isBefore(time01) &&  timechay.isAfter(time00)&& time.split(" ")[1].equals("CH"))
            {
                loichuc.setText("Chào buổi trưa !!");
            }
            else
            if (timechay.isBefore(time06) &&  timechay.isAfter(time01)&& time.split(" ")[1].equals("CH"))
            {
                loichuc.setText("Chào buổi chiều !!");
            } else{
                loichuc.setText("Chào Buổi tối !!");
            }
        }));

        // Lặp vô hạn
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    //-----------------kiểm tra đối tượng đăng nhập---------------------------------
    public static boolean mhquanli() throws SQLException, ClassNotFoundException {
        String tam= new LoginController().getmaLogin();
        if(new User().kiemtraql(tam)){
            return true;
        }
        else
            return false;
    }
    //-------------------Lấy mảng Dữ liệu từ database--------------------------------------------
    public static String[] thongtin;
    static {
        try {
            thongtin = new User().getthongtin(new LoginController().getmaLogin(), mhquanli());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static String[]   TTtiendo;
    static {
        try {
            TTtiendo = new User().getTienDo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] TaiKhoan;
    static {
        try {
            TaiKhoan = new User().getTaiKhoan(new LoginController().getmaLogin());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[]   TTduan;
    static {
        try {
            TTduan = new User().getDuAn();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[]   TTNghiPhep;
    static {
        try {
            TTNghiPhep = new User().getNghiPhep(new LoginController().getmaLogin());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //------------------refresh-------------------------------------------
    public static void refreshThongTin() {
        try {
            thongtin = new User().getthongtin(new LoginController().getmaLogin(), mhquanli());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void refreshTienDo() {
        try {
            TTtiendo = new User().getTienDo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void refreshTaiKhoan() {
        try {
            TaiKhoan = new User().getTaiKhoan(new LoginController().getmaLogin());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void refreshDuAn() {
        try {
            TTduan = new User().getDuAn();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void refreshNghiPhep() {
        try {
            TTNghiPhep = new User().getNghiPhep(new LoginController().getmaLogin());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //-----------Sử lí Menu------------------------------------------------

    public void button_Profile(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        NPsophepquahan.setVisible(false);
        DKCVvuilongnhapdayduthongtin.setVisible(false);
        TDdulieukhonghople.setVisible(false);
        NPngaybatdaulonhonngayketthuc.setVisible(false);
        DKCVtrongtieude.setVisible(false);
        DKVCtrongmota.setVisible(false);
        DKCVdangkithanhcong.setVisible(false);
        DKCVtrunglichvoiduandanglam.setVisible(false);
        DMKdoithanhcong.setVisible(false);
        DMKsaimk.setVisible(false);
        DMKmkkhongtrungkhop.setVisible(false);
        DMKsaimanv.setVisible(false);
        refreshThongTin();

        for (int i = 0; i < 6; i++) {
            System.out.println("Element " + i + ": " + thongtin[i]);
        }
        Profile_employeeCode.setText(thongtin[1]);
        Profile_fullName.setText(thongtin[2]);
        Profile_phoneNumber.setText(thongtin[4]);
        Profile_Adress.setText(thongtin[3]);
        HScccd.setText(thongtin[5]);
        System.out.println("----------------------------------------");
        if (mhquanli()){

            chucvuquanli.setVisible(true);
            chucvunhanvien.setVisible(false);
        }
        else {
            chucvuquanli.setVisible(false);
            chucvunhanvien.setVisible(true);
        }
        System.out.println("bạn đã ấn vào hồ sơ");
        Profile.setVisible(true);
        Progress.setVisible(false);
        changePassword.setVisible(false);
        onLeave.setVisible(false);
        Salary.setVisible(false);
        Account.setVisible(false);
        DSnhanvien.setVisible(false);
        addProject.setVisible(false);
        Support.setVisible(false);
        System.out.println("MA :"+new LoginController().getmaLogin()+ " "+mhquanli());
    }

    public void button_Progress(ActionEvent even) throws ParseException {
        DMKdoithanhcong.setVisible(false);
        DMKsaimk.setVisible(false);
        DKCVvuilongnhapdayduthongtin.setVisible(false);
        DMKmkkhongtrungkhop.setVisible(false);
        DMKsaimanv.setVisible(false);
        NPsophepquahan.setVisible(false);
        NPngaybatdaulonhonngayketthuc.setVisible(false);
        Profile_ConfirmEdit.setVisible(false);
        DKCVtrongtieude.setVisible(false);
        DKVCtrongmota.setVisible(false);
        DKCVdangkithanhcong.setVisible(false);
        DKCVtrunglichvoiduandanglam.setVisible(false);
        // refreshTienDo();
        for (int i =0; i<4;i++){
            System.out.println(TTtiendo[i]);
        }
        Progress_state.setText(TTtiendo[0]);
        Progress_strartTime.setText(TTtiendo[1]);
        Progress_endTime.setText(TTtiendo[2]);
        Progress_note.setText(TTtiendo[3]);
        if (!Progress_state.getText().equals("Đã hoàn thành dự án")) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate ngayhientai = LocalDate.parse(getNgayHienTai(), formatter);
            LocalDate ngayketthuctiendo = LocalDate.parse(Progress_endTime.getText(), formatter);
            if (ngayhientai.compareTo(ngayketthuctiendo) > 0) {
                Progress_state.setText("Đã hoàn thành dự án");
                Progress_strartTime.setText("đã kết thúc");
                Progress_endTime.setText("Đã kết thúc");
                Progress_note.setText("Thành công viên mãn");
            }
        }
        System.out.println("bn đã ấn vào nút tiendo");
        Profile.setVisible(false);
        Progress.setVisible(true);
        onLeave.setVisible(false);
        changePassword.setVisible(false);
        Account.setVisible(false);
        Salary.setVisible(false);
        DSnhanvien.setVisible(false);
        addProject.setVisible(false);
        Support.setVisible(false);
    }

    public void buttonOnLeave(ActionEvent event) throws SQLException, ClassNotFoundException {
        refreshNghiPhep();
        System.out.println(TTNghiPhep[2]);
        NPsophepquahan.setVisible(false);
        DKCVvuilongnhapdayduthongtin.setVisible(false);
        DMKdoithanhcong.setVisible(false);
        DMKsaimk.setVisible(false);
        DMKmkkhongtrungkhop.setVisible(false);
        DMKsaimanv.setVisible(false);
        DKCVtrongtieude.setVisible(false);
        DKVCtrongmota.setVisible(false);
        DKCVdangkithanhcong.setVisible(false);
        DKCVtrunglichvoiduandanglam.setVisible(false);
        Profile_ConfirmEdit.setVisible(false);
        NPngaybatdaulonhonngayketthuc.setVisible(false);
        onLeave_remainingDaysOfLeave.setText(String.valueOf(new User().songayconlai(new LoginController().getmaLogin())));
        Onleave_maximumLeaveNumber.setText(TTNghiPhep[2]);
        // System.out.println(TTNghiPhep[4]+"   "+TTNghiPhep[5]);
        OnLeave_startday.setText(TTNghiPhep[4]);
        Onleave_endDay.setText(TTNghiPhep[5]);
        System.out.println(TTNghiPhep[0]+" "+TTNghiPhep[1]+" "+TTNghiPhep[2]+" "+TTNghiPhep[3]+" "+TTNghiPhep[4]+" "+TTNghiPhep[5]);
        // Kiểm tra rỗng trước khi chuyển đổi
        if ( Onleave_endDay.getText()==null|| OnLeave_startday.getText()==null||OnLeave_startday.getText().isEmpty() || Onleave_endDay.getText().isEmpty()  )   {
            System.out.println("Ngày không được để trống");

        }
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate ngayhientai = LocalDate.parse(getNgayHienTai(), formatter);
            LocalDate ngayketthucnghiphep = LocalDate.parse(Onleave_endDay.getText(), formatter);
            LocalDate ngaybatdaunghiphep = LocalDate.parse(OnLeave_startday.getText(), formatter);

            if (ngayhientai.compareTo(ngaybatdaunghiphep) >= 0 && ngayhientai.compareTo(ngayketthucnghiphep) <= 0) {
                hiendangtrongquatrinhnghiphep.setVisible(true);
                NPdaxacnhannghiphep.setVisible(false);
            }
            else if (ngayhientai.compareTo(ngaybatdaunghiphep)<0){
                NPdaxacnhannghiphep.setVisible(true);
                hiendangtrongquatrinhnghiphep.setVisible(false);
            }
            else {
                OnLeave_startday.setText(null);
                Onleave_endDay.setText(null);
                new User().UpLoadNghiPhep(new LoginController().getmaLogin(),0,null,null);
                NPdaxacnhannghiphep.setVisible(false);
                hiendangtrongquatrinhnghiphep.setVisible(false);
            }
        }
        System.out.println("Bạn đã ấn vào nút nghỉ phép");


        Profile.setVisible(false);
        Progress.setVisible(false);
        onLeave.setVisible(true);
        Account.setVisible(false);
        DSnhanvien.setVisible(false);
        changePassword.setVisible(false);
        Salary.setVisible(false);
        addProject.setVisible(false);
        Support.setVisible(false);
    }

    public  void  buttonAccount(ActionEvent even){
        DMKdoithanhcong.setVisible(false);
        DKCVvuilongnhapdayduthongtin.setVisible(false);
        DMKsaimk.setVisible(false);
        DMKmkkhongtrungkhop.setVisible(false);
        DMKsaimanv.setVisible(false);
        NPsophepquahan.setVisible(false);
        Profile_ConfirmEdit.setVisible(false);
        DKCVtrongtieude.setVisible(false);
        DKVCtrongmota.setVisible(false);
        DKCVdangkithanhcong.setVisible(false);
        DKCVtrunglichvoiduandanglam.setVisible(false);
        NPngaybatdaulonhonngayketthuc.setVisible(false);
        refreshTaiKhoan();
        for (int i=0;i<3;i++){
            System.out.println("Thông tin tài khoản "+i+TaiKhoan[i]);
        }
        TKmanv.setText(TaiKhoan[0]);
        TKusername.setText(TaiKhoan[1]);
        TKpassword.setText(TaiKhoan[2]);
        System.out.println("bn đã ấn vào nút tài khoản");
        Profile.setVisible(false);
        Progress.setVisible(false);
        changePassword.setVisible(false);
        onLeave.setVisible(false);
        Account.setVisible(true);
        Salary.setVisible(false);
        DSnhanvien.setVisible(false);
        addProject.setVisible(false);
        Support.setVisible(false);
    }
    public  void  buttonList(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DMKdoithanhcong.setVisible(false);
        DMKsaimk.setVisible(false);
        DKCVvuilongnhapdayduthongtin.setVisible(false);
        DMKmkkhongtrungkhop.setVisible(false);
        DMKsaimanv.setVisible(false);
        NPsophepquahan.setVisible(false);
        Profile_ConfirmEdit.setVisible(false);
        DKCVtrongtieude.setVisible(false);
        DKVCtrongmota.setVisible(false);
        DKCVdangkithanhcong.setVisible(false);
        DKCVtrunglichvoiduandanglam.setVisible(false);
        NPngaybatdaulonhonngayketthuc.setVisible(false);

        System.out.println("quản lí đã ấn vào nút danh sách");
        if (mhquanli()) {
            DSslnv.setText(Long.toString(new User().getSoLuongNhanVien()));
            DSslct.setText(Long.toString(new User().getSoLuongNhanVienCanTuyen()));
            DSslnvcc.setText(Long.toString(new User().getSoLuongNhanVienChuyenCan()));
            Profile.setVisible(false);
            Progress.setVisible(false);
            onLeave.setVisible(false);
            changePassword.setVisible(false);
            Account.setVisible(false);
            Salary.setVisible(false);
            DSnhanvien.setVisible(true);
            addProject.setVisible(false);
            Support.setVisible(false);
        }
        else {

        }
    }
    public  void  buttonAddProject(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        DMKdoithanhcong.setVisible(false);
        DKCVvuilongnhapdayduthongtin.setVisible(false);
        DMKsaimk.setVisible(false);
        DMKmkkhongtrungkhop.setVisible(false);
        DMKsaimanv.setVisible(false);
        NPsophepquahan.setVisible(false);
        NPngaybatdaulonhonngayketthuc.setVisible(false);
        Profile_ConfirmEdit.setVisible(false);
        System.out.println("Quản lí đã ấn vào nút thêm cv");
        if (mhquanli()) {
            refreshDuAn();
            addProject_Title.setText(TTduan[0]);
            addProject_Describe.setText(TTduan[1]);
            addProject_startDay.setText(TTduan[2]);
            addProject_endDay.setText(TTduan[3]);
            Profile.setVisible(false);
            Progress.setVisible(false);
            onLeave.setVisible(false);
            Account.setVisible(false);
            changePassword.setVisible(false);
            Salary.setVisible(false);
            DSnhanvien.setVisible(false);
            addProject.setVisible(true);
            Support.setVisible(false);
        }
        else{

        }
    }
    public  void buttonSupport(ActionEvent event){
        DKCVvuilongnhapdayduthongtin.setVisible(false);
        DMKdoithanhcong.setVisible(false);
        DMKsaimk.setVisible(false);
        DMKmkkhongtrungkhop.setVisible(false);
        DMKsaimanv.setVisible(false);
        NPsophepquahan.setVisible(false);
        NPngaybatdaulonhonngayketthuc.setVisible(false);
        Profile_ConfirmEdit.setVisible(false);
        DKCVtrongtieude.setVisible(false);
        DKVCtrongmota.setVisible(false);
        DKCVdangkithanhcong.setVisible(false);
        DKCVtrunglichvoiduandanglam.setVisible(false);
        System.out.println("bn đã ấn vào nút hỗ trọ");

        Profile.setVisible(false);
        Progress.setVisible(false);
        onLeave.setVisible(false);
        changePassword.setVisible(false);
        Account.setVisible(false);
        DSnhanvien.setVisible(false);
        addProject.setVisible(false);
        Support.setVisible(true);
        Salary.setVisible(false);
    }
    public void buttonSalary() throws SQLException, ClassNotFoundException {
        DKCVvuilongnhapdayduthongtin.setVisible(false);
        DMKdoithanhcong.setVisible(false);
        DMKsaimk.setVisible(false);
        DMKmkkhongtrungkhop.setVisible(false);
        DMKsaimanv.setVisible(false);
        Profile_ConfirmEdit.setVisible(false);
        DKCVtrongtieude.setVisible(false);
        DKVCtrongmota.setVisible(false);
        DKCVdangkithanhcong.setVisible(false);
        DKCVtrunglichvoiduandanglam.setVisible(false);
        System.out.println("bn đã ấn vào nút Tiền Lương");
        Salary_startDay.setText(Progress_strartTime.getText());
        Salary_endDay.setText(Progress_endTime.getText());
        String sophepdanghi;
        LoginController loginController = new LoginController();

        if (mhquanli()) {
            sophepdanghi = Long.toString(5 - new User().songayconlai(loginController.getmaLogin()));
        } else {
            sophepdanghi = Long.toString(10 - new User().songayconlai(loginController.getmaLogin()));
        }
        Salary_numberOfDaysOff.setText(sophepdanghi);
        if (!mhquanli()) {
            Salary_day.setText("500000");
        }
        else {
            Salary_day.setText("1000000");
        }
        // Định dạng ngày
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Chuyển đổi chuỗi thành LocalDate
        LocalDate ngayBatDau = LocalDate.parse(Salary_startDay.getText(), formatter);
        LocalDate ngayKetThuc = LocalDate.parse(Salary_endDay.getText(), formatter);
        // Tính số ngày giữa hai ngày
        long soNgay = ChronoUnit.DAYS.between(ngayBatDau, ngayKetThuc);
        System.out.println("số ngày :"+soNgay);
        if (!mhquanli()) {
            Salary_sumSalary.setText(Long.toString((soNgay - Long.parseLong(Salary_numberOfDaysOff.getText())) * 500000));
        }
        else {
            Salary_sumSalary.setText(Long.toString((soNgay - Long.parseLong(Salary_numberOfDaysOff.getText())) * 1000000));
        }
        if (Salary_numberOfDaysOff.getText().equals("0")){
            nvchuyencan.setVisible(true);
            thuongchuyencan.setVisible(true);
        }
        else {
            nvchuyencan.setVisible(false);
            thuongchuyencan.setVisible(false);
        }
        Profile.setVisible(false);
        Progress.setVisible(false);
        onLeave.setVisible(false);
        changePassword.setVisible(false);
        Account.setVisible(false);
        DSnhanvien.setVisible(false);
        addProject.setVisible(false);
        Support.setVisible(false);
        Salary.setVisible(true);
    }
    public  void  exit(ActionEvent actionEvent) throws IOException {

        getStage().close();
        showLoginView();
    }
    //--------------------------------xử lí Hồ sơ------------------------------
    public void Profile_edit(ActionEvent even) {
        System.out.println(" Bạn đã ấn vào sửa hồ sơ");
        if (!Profile_employeeCode.isEditable() || !Profile_fullName.isEditable() || !Profile_phoneNumber.isEditable() || !Profile_Adress.isEditable() || !HScccd.isEditable()) {
            //  Profile_employeeCode.setEditable(!Profile_employeeCode.isEditable());
            Profile_fullName.setEditable(!Profile_fullName.isEditable());
            Profile_phoneNumber.setEditable(!Profile_phoneNumber.isEditable());
            Profile_Adress.setEditable(!Profile_Adress.isEditable());
            HScccd.setEditable(!HScccd.isEditable());
            Profile_ConfirmEdit.setVisible(false);
        }
    }
    public void Profile_Confirm(ActionEvent even) throws SQLException, ClassNotFoundException {
        System.out.println("bạn đã ấn vào Xác nhập chỉnh sửa hồ sơ");
        if (Profile_employeeCode.isEditable()  || Profile_fullName.isEditable()  || Profile_phoneNumber.isEditable() || Profile_Adress.isEditable()  || HScccd.isEditable() )
        {
            Profile_ConfirmEdit.setVisible(true);
            new User().UpLoadThongTin(mhquanli(),new LoginController().getmaLogin(),Profile_fullName.getText(),Profile_phoneNumber.getText(),Profile_Adress.getText(),HScccd.getText());
            //  Profile_employeeCode.setEditable(!Profile_employeeCode.isEditable());// trả về trạng thái ngược lại
            Profile_fullName.setEditable(!Profile_fullName.isEditable());
            Profile_phoneNumber.setEditable(!Profile_phoneNumber.isEditable());
            Profile_Adress.setEditable(!Profile_Adress.isEditable());
            HScccd.setEditable(!HScccd.isEditable());
            refreshThongTin();
        }
    }
    //--------------------------------------xử lí tiến độ-----------------------------------------------
    public void progress_Edit(ActionEvent even) throws SQLException, ClassNotFoundException {
        if (mhquanli()) {
            System.out.println("bạn đã ấn vào nút Chỉnh sửa tiến độ");
            if (!Progress_state.isEditable() || !Progress_strartTime.isEditable() || !Progress_endTime.isEditable() || !Progress_note.isEditable()) {
                Progress_state.setEditable(!Progress_state.isEditable());
                Progress_strartTime.setEditable(!Progress_strartTime.isEditable());
                Progress_endTime.setEditable(!Progress_endTime.isEditable());
                Progress_note.setEditable(!Progress_note.isEditable());
            }
        }
    }

    public void Progress_save(ActionEvent even) throws ParseException, SQLException, ClassNotFoundException {
        if (mhquanli()) {
            System.out.println("Bạn đã ấn vào nút Lưu tiến độ");
            if ((Progress_state.getText().equals(null) || Progress_strartTime.getText().equals(null) || Progress_endTime.getText().equals(null) || Progress_note.getText().equals(null))) {
                TDdulieukhonghople.setVisible(true);
            } else {
                TDdulieukhonghople.setVisible(false);
                if (Progress_state.isEditable() || Progress_strartTime.isEditable() || Progress_endTime.isEditable() || Progress_note.isEditable()) {
                    new User().UpLoadTienDo(Progress_state.getText(), Progress_strartTime.getText(), Progress_endTime.getText(), Progress_note.getText());
                    refreshTienDo();
                    for (int i = 0; i < 4; i++) {
                        System.out.println(TTtiendo[i]);
                    }
                    Progress_state.setText(TTtiendo[0]);
                    Progress_strartTime.setText(TTtiendo[1]);
                    Progress_endTime.setText(TTtiendo[2]);
                    Progress_note.setText(TTtiendo[3]);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    LocalDate ngayhientai = LocalDate.parse(getNgayHienTai(), formatter);
                    LocalDate ngayketthuctiendo = LocalDate.parse(Progress_endTime.getText(), formatter);
                    if (ngayhientai.compareTo(ngayketthuctiendo) > 0) {
                        Progress_state.setText("Đã hoàn thành dự án");
                        Progress_strartTime.setText("đã kết thúc");
                        Progress_endTime.setText("Đã kết thúc");
                        Progress_note.setText("Thành công viên mãn");
                        new User().UpLoadTienDo(Progress_state.getText(), Progress_strartTime.getText(), Progress_endTime.getText(), Progress_note.getText());
                        refreshTienDo();
                    }

                    Progress_state.setEditable(!Progress_state.isEditable());
                    Progress_strartTime.setEditable(!Progress_strartTime.isEditable());
                    Progress_endTime.setEditable(!Progress_endTime.isEditable());
                    Progress_note.setEditable(!Progress_note.isEditable());
                }
            }
        }
    }

    //-------------------xử lí nghỉ phép--------------------------------------
    public void Onleave_confirm(ActionEvent even) throws SQLException, ClassNotFoundException {
        // Định dạng ngày
        refreshNghiPhep();
        if (!OnLeave_startday.getText().equals(TTNghiPhep[4]) || !Onleave_endDay.getText().equals(TTNghiPhep[5])) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            // Chuyển đổi chuỗi thành LocalDate
            LocalDate ngayBatDau = LocalDate.parse(OnLeave_startday.getText(), formatter);
            LocalDate ngayKetThuc = LocalDate.parse(Onleave_endDay.getText(), formatter);
            LocalDate ngayhientai = LocalDate.parse(getNgayHienTai(), formatter);
            // Tính số ngày giữa hai ngày
            long soNgay = ChronoUnit.DAYS.between(ngayBatDau, ngayKetThuc);
            System.out.println("Số ngày: " + soNgay);
            // Kiểm tra số ngày nghỉ có vượt quá số ngày còn lại hay không
            if (new User().songayconlai(new LoginController().getmaLogin()) < soNgay) {
                hiendangtrongquatrinhnghiphep.setVisible(false);
                daquangaynghiphep.setVisible(false);
                NPsophepquahan.setVisible(true);
                NPngaybatdaulonhonngayketthuc.setVisible(false);
                NPdaxacnhannghiphep.setVisible(false);
            } else if (ngayBatDau.compareTo(ngayKetThuc) > 0) {
                hiendangtrongquatrinhnghiphep.setVisible(false);
                daquangaynghiphep.setVisible(false);
                NPsophepquahan.setVisible(false);
                NPngaybatdaulonhonngayketthuc.setVisible(true);
                NPdaxacnhannghiphep.setVisible(false);
            } else {
                if (ngayhientai.compareTo(ngayBatDau) >= 0 && ngayhientai.compareTo(ngayKetThuc) <= 0) {
                    new User().UpLoadNghiPhep(new LoginController().getmaLogin(), soNgay, OnLeave_startday.getText(), Onleave_endDay.getText());
                    onLeave_remainingDaysOfLeave.setText(Long.toString(new User().songayconlai(new LoginController().getmaLogin())));
                    refreshNghiPhep();
                    hiendangtrongquatrinhnghiphep.setVisible(true);
                    daquangaynghiphep.setVisible(false);
                    NPsophepquahan.setVisible(false);
                    NPngaybatdaulonhonngayketthuc.setVisible(false);
                    NPdaxacnhannghiphep.setVisible(false);
                } else if (ngayhientai.compareTo(ngayKetThuc) > 0) {
                    hiendangtrongquatrinhnghiphep.setVisible(false);
                    daquangaynghiphep.setVisible(true);
                    NPsophepquahan.setVisible(false);
                    NPngaybatdaulonhonngayketthuc.setVisible(false);
                    NPdaxacnhannghiphep.setVisible(false);
                    OnLeave_startday.setText(null);
                    Onleave_endDay.setText(null);
                    //        new User().UpLoadNghiPhep(new LoginController().getmaLogin(),soNgay,null,null);
                } else {
                    new User().UpLoadNghiPhep(new LoginController().getmaLogin(), soNgay, OnLeave_startday.getText(), Onleave_endDay.getText());
                    onLeave_remainingDaysOfLeave.setText(Long.toString(new User().songayconlai(new LoginController().getmaLogin())));
                    refreshNghiPhep();
                    hiendangtrongquatrinhnghiphep.setVisible(false);
                    daquangaynghiphep.setVisible(false);
                    NPsophepquahan.setVisible(false);
                    NPngaybatdaulonhonngayketthuc.setVisible(false);
                    NPdaxacnhannghiphep.setVisible(true);
                }
            }
        }
    }

    //------------------------xử lí tài khoản--------------------------------------------
    public void Account_changePassword(ActionEvent event) {
        changePassword.setVisible(true);
        Profile.setVisible(false);
        Progress.setVisible(false);
        onLeave.setVisible(false);
        Account.setVisible(false);
        DSnhanvien.setVisible(false);
        addProject.setVisible(false);
        Support.setVisible(false);
    }
    //--------------------xử lí đổi mật khẩu -------------------------------------
    public void changePassword_return(){
        DMKdoithanhcong.setVisible(false);
        DMKsaimk.setVisible(false);
        DMKmkkhongtrungkhop.setVisible(false);
        DMKsaimanv.setVisible(false);
        changePassword.setVisible(false);
        Profile.setVisible(false);
        Progress.setVisible(false);
        onLeave.setVisible(false);
        Account.setVisible(true);
        DSnhanvien.setVisible(false);
        addProject.setVisible(false);
        Support.setVisible(false);
        changePassword_currentPassword.setText(null);
        DMKmkmoi.setText(null);
        changePassword_aganPassword.setText(null);
        changePassword_employeeCode.setText(null);
    }
    public void changePassword_changePassword() throws SQLException, ClassNotFoundException {

        if (!new EnCodeMK().encodePassword(changePassword_currentPassword.getText()).equals(TaiKhoan[2])){
            DMKsaimk.setVisible(true);
            DMKmkkhongtrungkhop.setVisible(false);
            DMKsaimanv.setVisible(false);
            DMKdoithanhcong.setVisible(false);
        }
        else
        if (!DMKmkmoi.getText().equals(changePassword_aganPassword.getText())){
            DMKsaimk.setVisible(false);
            DMKmkkhongtrungkhop.setVisible(true);
            DMKsaimanv.setVisible(false);
            DMKdoithanhcong.setVisible(false);
        }
        else
        if (!changePassword_employeeCode.getText().equals(TaiKhoan[0])){
            DMKsaimk.setVisible(false);
            DMKmkkhongtrungkhop.setVisible(false);
            DMKsaimanv.setVisible(true);
            DMKdoithanhcong.setVisible(false);
        }
        else {
            DMKdoithanhcong.setVisible(true);
            DMKsaimk.setVisible(false);
            DMKmkkhongtrungkhop.setVisible(false);
            DMKsaimanv.setVisible(false);
            new User().UpLoadlogin(TKusername.getText(),new EnCodeMK().encodePassword(DMKmkmoi.getText()),new LoginController().getmaLogin());
            refreshTaiKhoan();
            for (int i=0;i<3;i++){
                System.out.println("Thông tin tài khoản "+i+TaiKhoan[i]);
            }
            DMKsaimk.setVisible(false);
            DMKmkkhongtrungkhop.setVisible(false);
            DMKsaimanv.setVisible(false);
            TKmanv.setText(TaiKhoan[0]);
            TKusername.setText(TaiKhoan[1]);
            TKpassword.setText(TaiKhoan[2]);
        }
    }
    //-----------------------xử lí thêm CV----------------------------------
    public void addProject_add() throws SQLException, ClassNotFoundException {
        if (addProject_startDay.getText()==null|| addProject_Title.getText()==null || addProject_Describe.getText()==null || addProject_startDay.getText()==null || addProject_endDay.getText()==null ){
            DKCVvuilongnhapdayduthongtin.setVisible(true);
        } else {
            DKCVvuilongnhapdayduthongtin.setVisible(false);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Chuyển đổi chuỗi thành LocalDate
        LocalDate ngayKhoiCong = LocalDate.parse(addProject_startDay.getText(), formatter);
        LocalDate ngayHoanThanh = LocalDate.parse(addProject_endDay.getText(), formatter);
        LocalDate ngayHienTai = LocalDate.parse(getNgayHienTai(), formatter);
        String tam = Progress_endTime.getText();
        if (tam.equals("Đã kết thúc")) {
            tam = "10-01-2023";
        }
        LocalDate ngayKetThucTienDo = LocalDate.parse(tam, formatter);
        if (addProject_Title.getText().equals(null)) {
            DKCVtrongtieude.setVisible(true);
            DKVCtrongmota.setVisible(false);
            DKCVdangkithanhcong.setVisible(false);
        } else if (addProject_Describe.getText().equals(null)) {
            DKCVtrongtieude.setVisible(false);
            DKVCtrongmota.setVisible(true);
            DKCVdangkithanhcong.setVisible(false);
            DKCVtrunglichvoiduandanglam.setVisible(false);
        } else if (ngayKhoiCong.compareTo(ngayKetThucTienDo) < 0) {
            DKCVtrongtieude.setVisible(false);
            DKVCtrongmota.setVisible(false);
            DKCVdangkithanhcong.setVisible(false);
            DKCVtrunglichvoiduandanglam.setVisible(true);
        } else {
            DKCVtrongtieude.setVisible(false);
            DKVCtrongmota.setVisible(false);
            DKCVdangkithanhcong.setVisible(true);
            DKCVtrunglichvoiduandanglam.setVisible(false);
            if (ngayHienTai.compareTo(ngayKhoiCong) >= 0 && ngayHienTai.compareTo(ngayHoanThanh) <= 0) {
                new User().UpLoadTienDo(addProject_Title.getText(), addProject_startDay.getText(), addProject_endDay.getText(), addProject_Describe.getText());
                refreshTienDo();
            }
            new User().UpLoadNewProject(addProject_Title.getText(), addProject_Describe.getText(), addProject_startDay.getText(), addProject_endDay.getText());
        }
    }
    }

    //--------------------xử lí dữ kiện tiền lương---------------------------
    public void Salary_Complain(){
        NPsophepquahan.setVisible(false);
        NPngaybatdaulonhonngayketthuc.setVisible(false);

        System.out.println("bn đã ấn vào nút tiền lương");
        Profile.setVisible(false);
        Progress.setVisible(false);
        onLeave.setVisible(false);
        changePassword.setVisible(false);
        Account.setVisible(false);
        DSnhanvien.setVisible(false);
        addProject.setVisible(false);
        Support.setVisible(true);
        Salary.setVisible(false);

    }
    //----------------------------------------------------------------
    public void showLoginView() {
        try {
            new LoginView().start(new Stage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //kiêm tra xem đường dẫn có lỗi hay không
    private boolean isValidURL(String url) {
        try {
            new URI(url).toURL();
            return true;
        } catch (URISyntaxException | IllegalArgumentException | NullPointerException | MalformedURLException e) {
            return false;
        }
    }
}
