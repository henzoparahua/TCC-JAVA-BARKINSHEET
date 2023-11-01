package com.helpfox.main.app;

import com.helpfox.main.core.components.component.RippleViewRow;
import com.helpfox.main.core.components.component.SVGFactory;
import com.helpfox.main.core.components.layout.DrawerLayout;
import com.helpfox.main.core.components.layout.RecyclerView;
import com.helpfox.main.core.framework.ActionBarDrawerToggle;
import com.helpfox.main.core.framework.Toolbar;
import com.helpfox.main.core.manager.Activity;
import com.helpfox.main.app.fragments.DriverFragment;
import com.helpfox.main.app.fragments.GatewayFragment;
import com.helpfox.main.app.fragments.ProfileFragment;
import com.helpfox.main.core.manager.Fragment;
import com.helpfox.main.core.manager.FragmentTransaction;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.shape.SVGPath;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import static com.helpfox.main.core.View.APPACTIVITY;
import static com.helpfox.main.core.View.NAVITEM;

public class AppActivity extends Activity {
    @FXML
    Toolbar toolbar;
    @FXML
    private DrawerLayout drawer;
    @FXML
    private RecyclerView<NavItem> nav_items;

    @Override
    public void onCreate() {
        super.onCreate();
        setContentView(getClass().getResource(APPACTIVITY.toString()));

        toolbar.setDisplayShowHomeEnabled(true);
        toolbar.setTitle("Barkin");
        setActionBar(toolbar);

        drawer.setDrawerListener(new ActionBarDrawerToggle(this, drawer, toolbar));

        Adapter adapter = new Adapter();

        nav_items.setAdapter(adapter);

        nav_items.getItems().addAll(
                new NavItem(getClass().getResource("/img/driver.svg").getFile(), "Motoristas", DriverFragment.class),
                new NavItem(getClass().getResource("/img/gateway.svg").getFile(), "Portaria", GatewayFragment.class),
                new NavItem(getClass().getResource("/img/profile.svg").getFile(), "Perfil", ProfileFragment.class)
        );

        nav_items.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            try {
                Fragment fragment = newValue.fragment.getDeclaredConstructor().newInstance();
                transaction.replace("content", fragment);
                transaction.commit();
                drawer.closeDrawer();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void refresh(){
        DriverFragment fragment = (DriverFragment) getFragmentManager().findFragmentByTag("DriverFragment");
        //Needed to refresh the list | I'll create it later
        fragment.getDrivers();
    }

    public static class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        @Override
        public RecyclerView.ViewRow call(ListView param) {
            return new RippleViewRow(this);
        }

        @Override
        public Holder onCreateViewHolder(FXMLLoader loader) {
            loader.setLocation(getClass().getResource(NAVITEM.toString()));
            return new Holder(loader);
        }

        @Override
        public void onBindViewHolder(Holder holder, Object item) {
            NavItem object = (NavItem) item;
            holder.title.setText(object.title);
            holder.svg.setContent(object.item);
        }

        public static class Holder extends RecyclerView.ViewHolder {
            @FXML
            Label title;
            @FXML
            SVGPath svg;

            public Holder(FXMLLoader loader) {
                super(loader);
            }
        }
    }


    public static class NavItem {
        protected String item;
        protected String title;
        protected Class<? extends Fragment> fragment;

        public NavItem(String item, String title, Class<? extends Fragment> fragment) {
            this.item = SVGFactory.getSVGContent(new File(item));
            this.title = title;
            this.fragment = fragment;
        }
    }
}

