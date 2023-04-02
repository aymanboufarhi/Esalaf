package com.example.esalaf;

import com.exemple.model.Client;
import com.exemple.model.ClientDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ClientController implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField ido;

    @FXML
    private TextField tele;

    @FXML
    private TextField cin;

    @FXML
    private TextField filterField;

    ObservableList<Client> dataList;

    @FXML
    private TableView<Client> mytab;

    @FXML
    private TableColumn<Client, String> col_nom;

    @FXML
    private TableColumn<Client, String> col_tele;

    @FXML
    private TableColumn<Client, String> col_cin;

    @FXML
    protected void onSaveButtonClick(){
        Client cli = new Client(0l , nom.getText() , tele.getText() , cin.getText());
        try {
            ClientDAO clidao = new ClientDAO();
            clidao.save(cli);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
        search();
    }

    @FXML
    protected void onDeleteButtonClick() {
        Client cli = new Client((long) Integer.parseInt(ido.getText()), nom.getText(), tele.getText(), cin.getText());
        try {
            ClientDAO clidao = new ClientDAO();
            clidao.delete(cli);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
      UpdateTable();
        search();
    }
    @FXML
    protected void onUpdateButtonClick() {
        Client cli = new Client((long) Integer.parseInt(ido.getText()), nom.getText(), tele.getText(), cin.getText());
        try {
            ClientDAO clidao = new ClientDAO();
            clidao.update(cli);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
        search();
    }

    public void UpdateTable(){
        col_nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Client,String>("telepehone"));
        col_cin.setCellValueFactory(new PropertyValueFactory<Client,String>("cin"));


        mytab.setItems(getDataClients());
    }

    public void clear(){
        ido.setText(null);
        nom.setText(null);
        tele.setText(null);
        cin.setText(null);
        filterField.setText(null);
    }

    public static ObservableList<Client> getDataClients(){

        ClientDAO clidao = null;

        ObservableList<Client> listfx = FXCollections.observableArrayList();

        try {
            clidao = new ClientDAO();
            for(Client ettemp : clidao.getAll())
                listfx.add(ettemp);

       } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listfx ;
    }

    @FXML
     void search() {
        col_nom.setCellValueFactory(new PropertyValueFactory<Client,String>("nom"));
        col_tele.setCellValueFactory(new PropertyValueFactory<Client,String>("telepehone"));
        col_cin.setCellValueFactory(new PropertyValueFactory<Client,String>("cin"));
        for (Client client : dataList = getDataClients()) {
            mytab.setItems(dataList);
            FilteredList<Client>filterdData = new FilteredList<>(dataList, b -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterdData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {return true;}
                String lowerCaseFilter = newValue.toLowerCase();
                if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {return true;}
                else if (person.getTelepehone().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {return true;}
                else if (person.getCin().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                return true;
                else
                    return false;});
            });
            SortedList<Client> sortedData = new SortedList<>(filterdData);
            sortedData.comparatorProperty().bind(mytab.comparatorProperty());
            mytab.setItems(sortedData);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UpdateTable();
        search();
    }

    private Stage stage;
    private Scene scene;
    public void switchToMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menu.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void tablehandleButtonAction(MouseEvent event) {
        Client cl = mytab.getSelectionModel().getSelectedItem();
        ido.setText(String.valueOf(cl.getId_client()));
        nom.setText(cl.getNom());
        tele.setText(cl.getTelepehone());
        cin.setText(cl.getCin());
    }
}