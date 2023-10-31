package com.helpfox.main.app.fragments;

import com.helpfox.main.app.tableview.DriverTableModel;
import com.helpfox.main.core.components.component.MaterialButton;
import com.helpfox.main.core.manager.Fragment;
import com.helpfox.main.core.manager.FragmentTransaction;
import com.helpfox.main.server.entity.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.skin.ButtonSkin;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colSelect.setCellValueFactory(new PropertyValueFactory<>("selected"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colRG.setCellValueFactory(new PropertyValueFactory<>("rg"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        colSelect.setCellFactory(CheckBoxTableCell.forTableColumn(colSelect));

        addButtonToTable();

        tblDriver.setItems(driverList());
    }

    private ObservableList<DriverTableModel> driverList() {
        return FXCollections.observableArrayList(
                new DriverTableModel("Pedro", "123456789", "123456789"),
                new DriverTableModel("Joao", "123456789", "123456789"),
                new DriverTableModel("Maria", "123456789", "123456789"),
                new DriverTableModel("José", "123456789", "123456789")
        );
    }

    private void addButtonToTable() {
        TableColumn<DriverTableModel, Void> colAction = new TableColumn();

        colAction.setSortable(false);
        Callback<TableColumn<DriverTableModel, Void>, TableCell<DriverTableModel, Void>> cellFactory = new Callback<TableColumn<DriverTableModel, Void>, TableCell<DriverTableModel, Void>>() {
            @Override
            public TableCell<DriverTableModel, Void> call(final TableColumn<DriverTableModel, Void> param) {
                final TableCell<DriverTableModel, Void> cell = new TableCell<DriverTableModel, Void>() {
                    private final MaterialButton btnAddVehicle = new MaterialButton();
                    private final Button btnEdit = new MaterialButton();
                    private final Button btnDelete = new MaterialButton();

                    public static final PseudoClass addStyle = PseudoClass.getPseudoClass("add");
                    public static final PseudoClass editStyle = PseudoClass.getPseudoClass("edit");
                    public static final PseudoClass deleteStyle = PseudoClass.getPseudoClass("delete");

                    {
                        btnAddVehicle.pseudoClassStateChanged(addStyle, true);
                        btnEdit.pseudoClassStateChanged(editStyle, true);
                        btnDelete.pseudoClassStateChanged(deleteStyle, true);

                        btnAddVehicle.setText("+ Veículo");
                        btnEdit.setText("Editar");
                        btnDelete.setText("Excluir");

                        btnAddVehicle.setMaxHeight(12);
                        btnEdit.setMaxHeight(12);
                        btnDelete.setMaxHeight(12);

                        btnAddVehicle.setOnAction((ActionEvent event) -> {
                            DriverTableModel driverTableModel = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDriver: " + driverTableModel);
                        });

                        btnEdit.setOnAction((ActionEvent event) -> {
                            DriverTableModel driverTableModel = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDriver: " + driverTableModel);
                        });

                        btnDelete.setOnAction((ActionEvent event) -> {
                            DriverTableModel driverTableModel = getTableView().getItems().get(getIndex());
                            System.out.println("selectedDriver: " + driverTableModel);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                            return;
                        }
                        HBox pane = new HBox(btnAddVehicle, btnEdit, btnDelete);
                        pane.setSpacing(20);
                        pane.setAlignment(Pos.CENTER_RIGHT);
                        pane.setPadding(new Insets(5, 0, 5, 0));
                        setGraphic(pane);
                    }
                };
                return cell;
            }
        };

        colAction.setCellFactory(cellFactory);
        colAction.styleProperty();

        tblDriver.getColumns().add(colAction);
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

    public List<Driver> getDrivers() {
        //Get all drivers from database\server
        return null;
    }

    public void getSelectedItem(MouseEvent event) {

    }

    public void startTransition(MouseEvent event) {
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
