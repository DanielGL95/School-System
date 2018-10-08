package admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import dbUtil.dbConnection;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private TextField id;
    @FXML
    private TextField fisrstname;
    @FXML
    private TextField lastname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;

    @FXML
    private TableView<StudentData> studenttable;

    @FXML
    private TableColumn<StudentData, String> idcolumn;
    @FXML
    private TableColumn<StudentData, String> firstnamecolumn;
    @FXML
    private TableColumn<StudentData, String> lastnamecolumn;
    @FXML
    private TableColumn<StudentData, String> emailcolumn;
    @FXML
    private TableColumn<StudentData, String> dobcolumn;

    private dbConnection dbConnection;
    private ObservableList<StudentData> data;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.dbConnection = new dbConnection();
    }

    @FXML
    private void loadData(){
        String sql = "SELECT * FROM students";
        try {
            Connection conn = dbConnection.getConnection();
            this.data = FXCollections.observableArrayList();

            ResultSet rs  = conn.createStatement().executeQuery(sql);

            while(rs.next()){
                this.data.add(new StudentData(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }

        }catch (SQLException e){
            System.out.println("ERRO: "+ e);
        }

        this.idcolumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        this.emailcolumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.dobcolumn.setCellValueFactory(new PropertyValueFactory<>("DOB"));

        this.studenttable.setItems(null);
        this.studenttable.setItems(this.data);
    }

    @FXML
    private void addStudent(){
        String sql = "INSERT INTO students(id, fname,lname,email,DOB) " +
                     "VALUES(?,?,?,?,?)";

        Connection conn = dbConnection.getConnection();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1,this.id.getText());
            statement.setString(2,this.fisrstname.getText());
            statement.setString(3,this.lastname.getText());
            statement.setString(4,this.email.getText());
            statement.setString(5,this.dob.getEditor().getText());

            statement.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clearForm(){
        this.id.setText("");
        this.fisrstname.setText("");
        this.lastname.setText("");
        this.email.setText("");
        this.dob.getEditor().setText("");
    }
}
