package com.helpfox.main.fragments;

import com.helpfox.main.core.components.component.MaterialButton;
import com.helpfox.main.core.components.component.RippleViewRow;
import com.helpfox.main.core.components.layout.RecyclerView;
import com.helpfox.main.core.manager.Fragment;
import com.helpfox.main.core.manager.FragmentTransaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.HashMap;

import static com.helpfox.main.core.View.DRIVERFRAGMENT;
import static com.helpfox.main.core.View.DRIVERITEM;

public class DriverFragment extends Fragment {
    @FXML
    Label title;
    @FXML
    RecyclerView<DriverItem> driver_items;
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

        Adapter adapter = new Adapter();
        driver_items.setAdapter(adapter);



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
        params.put("text", title.getText());

        fragment.setArguments(params);

        transaction.add("content", fragment);
        transaction.addToBackStack("");
        transaction.commit();


    }

    public static class Adapter extends RecyclerView.Adapter<Holder> {
        @Override
        public RecyclerView.ViewRow call(ListView param) {
            return new RippleViewRow(this);
        }

        @Override
        public Holder onCreateViewHolder(FXMLLoader loader) {
            loader.setLocation(getClass().getResource(DRIVERITEM.toString()));
            return new Holder(loader);
        }

        @Override
        public void onBindViewHolder(Holder holder, Object item) {
            DriverItem object = (DriverItem) item;
//            holder.chkDriver.setSelectedStateCallback();
            holder.driverName.setText(object.driverName);
            holder.rg.setText(object.rg);
            holder.phone.setText(object.phone);

        }
    }

    public static class Holder extends RecyclerView.ViewHolder {
        @FXML
        CheckBoxTableCell chkDriver;
        @FXML
        Label driverName;
        @FXML
        Label rg;
        @FXML
        Label phone;
        @FXML
        MaterialButton addVehicle;
        @FXML
        MaterialButton edit;
        @FXML
        MaterialButton delete;
        public Holder(FXMLLoader loader) {super(loader);}
    }

    public class DriverItem {
        Button chkDriver;
        String driverName;
        String rg;
        String phone;
        Button addVehicle;
        Button edit;
        Button delete;
        Class<?> fragment;

        public DriverItem(Button chkDriver, String driverName, String rg, String phone, Button addVehicle, Button edit, Button delete, Class<?> fragment) {
            this.chkDriver = chkDriver;
            this.driverName = driverName;
            this.rg = rg;
            this.phone = phone;
            this.addVehicle = addVehicle;
            this.edit = edit;
            this.delete = delete;
            this.fragment = fragment;
        }
    }
}
