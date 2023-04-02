package com.example.esalaf;

import com.exemple.model.Commande;
import com.exemple.model.CommandeDAO;
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

public class CommandeController implements Initializable {
    @FXML
    private TextField cin;

    @FXML
    private TextField ido;

    @FXML
    private TextField prod;

    @FXML
    private TextField quan;

    @FXML
    private TextField etat;

    @FXML
    private TextField filterField;

    ObservableList<Commande> dataList;


    @FXML
    private TableView<Commande> mytab;

    @FXML
    private TableColumn<Commande, String> col_cin;

    @FXML
    private TableColumn<Commande, String> col_produit;

    @FXML
    private TableColumn<Commande, String> col_quan;

    @FXML
    private TableColumn<Commande, String> col_etat;

    @FXML
    protected void onSaveButtonClick(){
        Commande com = new Commande(0l , cin.getText() , prod.getText() , quan.getText(), etat.getText());
        try {
            CommandeDAO comDAO = new CommandeDAO();
            comDAO.save(com);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
        search();
    }

    @FXML
    protected void onDeleteButtonClick() {
        Commande com = new Commande((long) Integer.parseInt(ido.getText()), cin.getText(), prod.getText(),
                quan.getText(), etat.getText());
        try {
            CommandeDAO comDAO = new CommandeDAO();
            comDAO.delete(com);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
        search();
    }
    @FXML
    protected void onUpdateButtonClick() {
        Commande com = new Commande((long) Integer.parseInt(ido.getText()), cin.getText(),
                prod.getText(), quan.getText(), etat.getText());
        try {
            CommandeDAO comDAO = new CommandeDAO();
            comDAO.update(com);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
        search();
    }

    public void UpdateTable(){
        col_cin.setCellValueFactory(new PropertyValueFactory<Commande,String>("cin"));
        col_produit.setCellValueFactory(new PropertyValueFactory<Commande,String>("produit"));
        col_quan.setCellValueFactory(new PropertyValueFactory<Commande,String>("quantite"));
        col_etat.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat"));
        mytab.setItems(getDataOrders());
    }

    public void clear(){
        ido.setText(null);
        cin.setText(null);
        prod.setText(null);
        quan.setText(null);
        etat.setText(null);
        filterField.setText(null);
    }

    public static ObservableList<Commande> getDataOrders(){

        CommandeDAO comDAO = null;

        ObservableList<Commande> listfx = FXCollections.observableArrayList();
        try {
            comDAO = new CommandeDAO();
            for(Commande ettemp : comDAO.getAll())
                listfx.add(ettemp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listfx ;
    }

    void search() {
        col_cin.setCellValueFactory(new PropertyValueFactory<Commande,String>("cin"));
        col_produit.setCellValueFactory(new PropertyValueFactory<Commande,String>("produit"));
        col_quan.setCellValueFactory(new PropertyValueFactory<Commande,String>("quantite"));
        col_etat.setCellValueFactory(new PropertyValueFactory<Commande,String>("etat"));


        for (Commande commande : dataList = getDataOrders()) {
            mytab.setItems(dataList);
            FilteredList<Commande> filterdData = new FilteredList<>(dataList, b -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filterdData.setPredicate(person -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getCin().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true;
                    }else if (person.getProduit().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true;
                    } else if (person.getQuantite().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true;
                    } else if (person.getEtat().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                        return true;
                    else
                        return false;
                });
            });
            SortedList<Commande> sortedData = new SortedList<>(filterdData);
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
        Commande pri = mytab.getSelectionModel().getSelectedItem();
        ido.setText(String.valueOf(pri.getId()));
        cin.setText(pri.getCin());
        prod.setText(String.valueOf(pri.getProduit()));
        quan.setText(pri.getQuantite());
        etat.setText(pri.getEtat());
    }
}
