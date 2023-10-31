package com.helpfox.main.app;

import com.helpfox.main.core.components.component.RippleViewRow;
import com.helpfox.main.core.components.component.SVGFactory;
import com.helpfox.main.core.components.layout.DrawerLayout;
import com.helpfox.main.core.components.layout.RecyclerView;
import com.helpfox.main.core.components.layout.SlidingTabLayout;
import com.helpfox.main.core.components.layout.ViewPager;
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
                new NavItem("Motoristas", getClass().getResource("/img/driver.svg").getFile(), DriverFragment.class),
                new NavItem("Portaria", getClass().getResource("/img/gateway.svg").getFile(), GatewayFragment.class),
                new NavItem("Perfil", getClass().getResource("/img/profile.svg").getFile(), ProfileFragment.class)
        );

        nav_items.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            try {
                Fragment fragment = (Fragment) newValue.fragment.getDeclaredConstructor().newInstance();
                transaction.replace("content", fragment);
                transaction.commit();
                toolbar.setTitle(newValue.title);
                drawer.closeDrawer();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
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
        protected String title;
        protected String item;
        protected Class<?> fragment;

        public NavItem(String title, String item, Class<?> fragment) {
            this.title = title;
            this.item = SVGFactory.getSVGContent(new File(item));
            this.fragment = fragment;
        }
    }
}

