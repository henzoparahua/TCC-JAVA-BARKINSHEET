package com.helpfox.main.app.fragments;

import com.helpfox.main.app.tableview.DriverTableModel;
import com.helpfox.main.core.manager.Fragment;
import com.helpfox.main.core.manager.FragmentTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import static com.helpfox.main.core.View.DRIVERFRAGMENT;

public class DriverFragment extends Fragment implements Initializable {
    @FXML
    StackPane content;
    @FXML
    Label title;
    @FXML
    private TableView<DriverTableModel> tblDriver;
    @FXML
    private TableColumn<DriverTableModel, Boolean> colSelect;
    @FXML
    private TableColumn<DriverTableModel, String> colName;
    @FXML
    private TableColumn<DriverTableModel, String> colRG;
    @FXML
    private TableColumn<DriverTableModel, String> colPhone;
    @FXML
    private TableColumn<DriverTableModel, String> colAddVehicle;
    @FXML
    private TableColumn<DriverTableModel, String> colEdit;
    @FXML
    private TableColumn<DriverTableModel, String> colDelete;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colSelect.setCellValueFactory(new PropertyValueFactory<>("selected"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colAddVehicle.setCellValueFactory(new PropertyValueFactory<>("addVehicle"));
        colEdit.setCellValueFactory(new PropertyValueFactory<>("edit"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));

        colSelect.setCellFactory(CheckBoxTableCell.forTableColumn(colSelect));

        tblDriver.setItems(driverList());
    }

    private ObservableList<DriverTableModel> driverList() {
        return FXCollections.observableArrayList(
                new DriverTableModel( "Pedro", "123456789", "123456789", "123456789", "123456789", "123456789"),
                new DriverTableModel( "Joao", "123456789", "123456789", "123456789", "123456789", "123456789"),
                new DriverTableModel( "Maria", "123456789", "123456789", "123456789", "123456789", "123456789"),
                new DriverTableModel( "José", "123456789", "123456789", "123456789", "123456789", "123456789")
        );
    }
    @Override
    public void onCreateView(FXMLLoader loader) {
        loader.setLocation(getClass().getResource(DRIVERFRAGMENT.toString()));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }


        HashMap arguments = getArguments();

        if (arguments != null) {
            if (arguments.containsKey("textLabel")) {
                title.setText(arguments.get("textLabel").toString());
            }
        }
    }

    public void getSelectedItem(MouseEvent event){

    }

    public void startTransition(MouseEvent event)
    {
        Region region = (Region) event.getSource();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        Fragment fragment = new DriverFragment();

        HashMap<String, Object> params = new HashMap<>();
        params.put("x", region.getLayoutX());
        params.put("y", region.getLayoutY());
        params.put("w", region.getWidth());
        params.put("h", region.getHeight());
        params.put("text", title.getText());
        params.put("tableview", tblDriver.getItems());
        params.put("colName", colName.getCellObservableValue(tblDriver.getSelectionModel().getSelectedItem()).getValue());
        params.put("colRG", colRG.getCellObservableValue(tblDriver.getSelectionModel().getSelectedItem()).getValue());

        fragment.setArguments(params);

        transaction.add("content", fragment);
        transaction.addToBackStack("");
        transaction.commit();


    }
}
