package com.levancam.demo6.controllers;

import com.levancam.demo6.LoginView;
import com.levancam.demo6.MainView;
import com.levancam.demo6.encode.EnCodeMK;
import com.levancam.demo6.models.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    public AnchorPane login;
    @FXML
    public AnchorPane suport;
    @FXML
    public Stage stage;
    @FXML
    public AnchorPane main1;
    @FXML
    public AnchorPane SignUp;
    @FXML
    private TextField UserName;
    @FXML
    private PasswordField PassWord;
    @FXML
    private Label dangnhapthatbai;
    @FXML
    private Label dangkithatbai;
    @FXML
    private Label dangkithanhcong;
    @FXML
    private Label dangnhapthanhcong;
    @FXML
    private TextField UserNameSign;
    @FXML
    private PasswordField PassWordSign;
    @FXML
    private PasswordField AganPassWordSign;
    @FXML
    private TextField maNVSign;
    @FXML
    private  TextField linkHTML;
    @FXML
    private TextField linkFaceBook;
    private static String ma;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Stage getStage() {
        return stage;
    }

    public void initialize() {
        linkFaceBook.setOnMouseClicked(event -> {
            // Lấy đường dẫn từ TextField
            String url = linkFaceBook.getText();

            // Kiểm tra xem đường dẫn có hợp lệ không
            if (isValidURL(url)) {
                try {
                    // Mở trình duyệt mặc định và mở đường dẫn
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // Kiểm tra xem một chuỗi có phải là URL hợp lệ hay không
    private boolean isValidURL(String url) {
        try {
            new URI(url).toURL();
            return true;
        } catch (URISyntaxException | IllegalArgumentException | NullPointerException | MalformedURLException e) {
            return false;
        }
    }

    // ------------------exit-----------------------------------------
    public void ExitMain1(ActionEvent even) {
        System.exit(0);
    }
    public void ExitLogin(ActionEvent even) {

        System.out.println("bạn đã click vào exit login");
        login.setVisible(false);
        dangnhapthatbai.setVisible(false);
        dangkithatbai.setVisible(false);
        UserNameSign.setText(null);
        PassWordSign.setText(null);
        AganPassWordSign.setText(null);
        PassWord.setText(null);
        UserName.setText(null);
        maNVSign.setText(null);
        dangnhapthanhcong.setVisible(false);
        dangnhapthatbai.setVisible(false);
    }

    public void ExitSignUp(ActionEvent even) {
        SignUp.setVisible(false);
        dangnhapthatbai.setVisible(false);
        dangkithatbai.setVisible(false);
        UserNameSign.setText(null);
        PassWordSign.setText(null);
        AganPassWordSign.setText(null);
        PassWord.setText(null);
        UserName.setText(null);
        maNVSign.setText(null);
        dangkithanhcong.setVisible(false);
        dangkithatbai.setVisible(false);

    }

    public void ExitSuport(ActionEvent even) {
        suport.setVisible(false);
        dangnhapthatbai.setVisible(false);
        dangkithatbai.setVisible(false);
        UserNameSign.setText(null);
        PassWordSign.setText(null);
        AganPassWordSign.setText(null);
        PassWord.setText(null);
        UserName.setText(null);
        maNVSign.setText(null);
    }

    public String getmaLogin() {
        return ma;
    }

    //--------------------------xử lí login----------------------
    public void DangNhap(ActionEvent even) throws SQLException, ClassNotFoundException, IOException {

        System.out.println(UserName.getText() + "   " + "PassWord :" + PassWord.getText());

        if (new User().kiemtraDangNhap(UserName.getText(), new EnCodeMK().encodePassword(PassWord.getText()))) {
            String maDN = getmaLogin();
            dangnhapthanhcong.setVisible(true);
            dangnhapthatbai.setVisible(false);
            showMainview();
            getStage().close();
            System.out.println("Mã login :" + maDN);
        } else {
            System.out.println("No");
            dangnhapthatbai.setVisible(true);
            dangnhapthanhcong.setVisible(false);
        }
    }

    //------------xử lí Sign up---------------------------
    public void DangKi(ActionEvent e) throws SQLException, ClassNotFoundException {
        System.out.println("'" + UserNameSign.getText() + "' '" + PassWordSign.getText() + "' '" + AganPassWordSign.getText() + "' '" + maNVSign.getText() + "'");
        String maSign = new User().kiemtraMaUseSign(maNVSign.getText());
        System.out.println("Yes :" + maSign);
        if (UserNameSign.getText() != null && PassWordSign.getText() != null && AganPassWordSign.getText() != null && AganPassWordSign.getText().equals(PassWordSign.getText()) == true) {
            if (maSign != null) {
                System.out.println("Yes :" + maSign);
                System.out.println("Đăng kí đúng mã");
                dangkithatbai.setVisible(false);
                //upload user vào database
                new User().UpLoadlogin(UserNameSign.getText(), new EnCodeMK().encodePassword(PassWordSign.getText()), maNVSign.getText());

                SignUp.setVisible(false);
                UserNameSign.setText(null);
                PassWordSign.setText(null);
                AganPassWordSign.setText(null);
                maNVSign.setText(null);
                dangkithatbai.setVisible(false);
                //dangkithanhcong.setVisible(true);
                login.setVisible(true);
            } else {
                System.out.println(" Đăng kí sai mã");
                dangkithatbai.setVisible(true);
                dangkithanhcong.setVisible(false);
            }
        } else {
            dangkithatbai.setVisible(true);
            System.out.println("Thiếu dữ kiện hoặc ko khớp PassWord");
        }
    }

    //-----------------sử lí nhập sai-----------------------------
    public void QuenMaNV(ActionEvent even) {
        SignUp.setVisible(false);
        suport.setVisible(true);
        dangkithanhcong.setVisible(false);
        dangkithatbai.setVisible(false);
    }

    public void QuenMatKhau(ActionEvent even) {
        login.setVisible(false);
        dangnhapthanhcong.setVisible(false);
        dangnhapthatbai.setVisible(false);
        suport.setVisible(true);
    }

    //-----------hiện đăng nhập, đăng kí, hỗ trợ----------------
    public void dangnhapview(ActionEvent even) {
        login.setVisible(true);
    }

    public void dangkiview(ActionEvent even) {
        SignUp.setVisible(true);
    }

    //------------show main view-------------------
    public void showMainview() {
        try {
            new MainView().start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
