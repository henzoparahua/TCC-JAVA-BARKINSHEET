package com.helpfox.main.app.fragments;

import com.helpfox.main.app.tableview.GatewayTableModel;
import com.helpfox.main.core.components.component.ImageButton;
import com.helpfox.main.core.components.layout.FlexBox;
import com.helpfox.main.core.manager.Fragment;
import com.helpfox.main.core.manager.FragmentTransaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.HashMap;

import static com.helpfox.main.core.View.GATEWAYFRAGMENT;

public class GatewayFragment extends Fragment {
    @FXML
    StackPane content;
    @FXML
    FlexBox header_container;
    @FXML
    TextField search;
    @FXML
    private ImageButton btn_add_gateway;
    @FXML
    private TableView<GatewayTableModel> table_gateway;
    @FXML
    private TableColumn<GatewayTableModel, Boolean> colSelect;
    @FXML
    private TableColumn<GatewayTableModel, String> colName;
    @FXML
    private TableColumn<GatewayTableModel, String> colCar;
    @FXML
    private TableColumn<GatewayTableModel, String> colPlate;
    @FXML
    private TableColumn<GatewayTableModel, String> colDate;
    @FXML
    private TableColumn<GatewayTableModel, String> colHour;
    @FXML
    private TableColumn<GatewayTableModel, String> colType;
    @Override
    public void onCreateView(FXMLLoader loader) {
        loader.setLocation(getClass().getResource(GATEWAYFRAGMENT.toString()));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getSelectedItem(MouseEvent event) {

    }

    public void startTransition(MouseEvent event)
    {
        Region region = (Region) event.getSource();

        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        Fragment fragment = new DetailsFragment();

        HashMap<String, Object> params = new HashMap<>();
        params.put("x", region.getLayoutX());
        params.put("y", region.getLayoutY());
        params.put("w", region.getWidth());
        params.put("h", region.getHeight());

        fragment.setArguments(params);

        transaction.add("content", fragment);
        transaction.addToBackStack("");
        transaction.commit();


    }
}
