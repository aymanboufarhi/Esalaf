package com.example.esalaf;

import com.exemple.model.Produit;
import com.exemple.model.ProduitDAO;
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

import static com.example.esalaf.ClientController.getDataClients;

public class ProduitController implements Initializable {
    @FXML
    private TextField nom;

    @FXML
    private TextField ido;

    @FXML
    private TextField prix;

    @FXML
    private TextField quan;

    @FXML
    private TextField cat;

    @FXML
    private TextField filterField;

    ObservableList<Produit> dataList;


    @FXML
    private TableView<Produit> mytab;

    @FXML
    private TableColumn<Produit, String> col_nom;

    @FXML
    private TableColumn<Produit, String> col_prix;

    @FXML
    private TableColumn<Produit, String> col_quan;

    @FXML
    private TableColumn<Produit, String> col_cat;

    @FXML
    protected void onSaveButtonClick(){
        Produit pri = new Produit(0l , nom.getText() , Float.parseFloat(prix.getText()) , quan.getText(), cat.getText());
        try {
            ProduitDAO priDAO = new ProduitDAO();
            priDAO.save(pri);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
        search();
    }

    @FXML
    protected void onDeleteButtonClick() {
        Produit pri = new Produit((long) Integer.parseInt(ido.getText()), nom.getText(), Float.parseFloat(prix.getText()), quan.getText(), cat.getText());
        try {
            ProduitDAO priDAO = new ProduitDAO();
            priDAO.delete(pri);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
        search();
    }
    @FXML
    protected void onUpdateButtonClick() {
        Produit pri = new Produit((long) Integer.parseInt(ido.getText()), nom.getText(), Float.parseFloat(prix.getText()), quan.getText(), cat.getText());
        try {
            ProduitDAO priDAO = new ProduitDAO();
            priDAO.update(pri);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        UpdateTable();
        search();
    }

    public void UpdateTable(){

        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,String>("prix"));
        col_quan.setCellValueFactory(new PropertyValueFactory<Produit,String>("quantite"));
        col_cat.setCellValueFactory(new PropertyValueFactory<Produit,String>("categories"));
        mytab.setItems(getDataProducts());
    }

    public void clear(){
        ido.setText(null);
        nom.setText(null);
        prix.setText(null);
        quan.setText(null);
        cat.setText(null);
        filterField.setText(null);
    }

    public static ObservableList<Produit> getDataProducts(){

        ProduitDAO pridao = null;

        ObservableList<Produit> listfx = FXCollections.observableArrayList();
        try {
            pridao = new ProduitDAO();
            for(Produit ettemp : pridao.getAll())
                listfx.add(ettemp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listfx ;
    }

    void search() {
        col_nom.setCellValueFactory(new PropertyValueFactory<Produit,String>("nom"));
        col_prix.setCellValueFactory(new PropertyValueFactory<Produit,String>("prix"));
        col_quan.setCellValueFactory(new PropertyValueFactory<Produit,String>("quantite"));
        col_cat.setCellValueFactory(new PropertyValueFactory<Produit,String>("categories"));


        for (Produit produit : dataList = getDataProducts()) {
            mytab.setItems(dataList);
            FilteredList<Produit> filterdData = new FilteredList<>(dataList, b -> true);
            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filterdData.setPredicate(person -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true;
                    }else if (Float.toString(person.getPrix()).toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true;
                    } else if (person.getQuantite().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                        return true;
                } else if (person.getCategories().toLowerCase().indexOf(lowerCaseFilter) != -1 )
                    return true;
                    else
                        return false;
                });
            });
            SortedList<Produit> sortedData = new SortedList<>(filterdData);
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
        Produit pri = mytab.getSelectionModel().getSelectedItem();
        ido.setText(String.valueOf(pri.getIdProduit()));
        nom.setText(pri.getNom());
        prix.setText(String.valueOf(pri.getPrix()));
        quan.setText(pri.getQuantite());
        cat.setText(pri.getCategories());
    }
}