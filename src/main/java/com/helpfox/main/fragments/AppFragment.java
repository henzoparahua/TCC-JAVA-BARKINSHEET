package com.helpfox.main.fragments;

import com.helpfox.main.core.manager.Fragment;
import com.helpfox.main.core.manager.FragmentTransaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.helpfox.main.core.View.APPACTIVITY;

public class AppFragment extends Fragment {
    @FXML
    private Label label;

    @Override
    public void onCreateView(FXMLLoader fxmlLoader) {
        fxmlLoader.setLocation(getClass().getResource(APPACTIVITY.toString()));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        HashMap arguments=getArguments();

        if(arguments!=null){
            if(arguments.containsKey("textLabel")){
                label.setText(arguments.get("textLabel").toString());
            }
        }
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
        params.put("text", label.getText());

        fragment.setArguments(params);

        transaction.add("content", fragment);
        transaction.addToBackStack("");
        transaction.commit();


    }
}
