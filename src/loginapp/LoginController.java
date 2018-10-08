package loginapp;

import admin.AdminController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import students.StudentsController;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    LoginModel loginModel = new LoginModel();


    @FXML
    private Label dbstatus;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ComboBox<option> combobox;
    @FXML
    private Button loginBtn;
    @FXML
    private Label loginStatus;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (this.loginModel.isDatabaseConnected()){
            this.dbstatus.setText("Connected to Database");
        }else{
            this.dbstatus.setText("Not Connected");
        }

        this.combobox.setItems(FXCollections.observableArrayList(option.values()));
    }

    @FXML
    public void Login(ActionEvent event){
        try {
            if (this.loginModel.isLogin(this.username.getText(), this.password.getText(), this.combobox.getValue().toString())){
                Stage stage = (Stage) this.loginBtn.getScene().getWindow();
                stage.close();

                switch (this.combobox.getValue().toString()){
                    case "Admin":
                        adminLogin();
                        break;
                    case "Student":
                        studentLogin();
                        break;
                }
            }else{
                this.loginStatus.setText("Invalid Credentials");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void studentLogin(){
        try{
            Stage userStage=  new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root =loader.load(getClass().getClassLoader().getResource("students/studentFXML.fxml"));

            StudentsController studentsController = loader.getController();
            Scene scene = new Scene(root);

            userStage.setScene(scene);
            userStage.setTitle("Student Dashboard");
            userStage.setResizable(false);
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void adminLogin(){
        try{
            Stage adminStage=  new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = loader.load(getClass().getClassLoader().getResource("admin/admin.fxml"));

            AdminController adminController = loader.getController();
            Scene scene = new Scene(root);

            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
