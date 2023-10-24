package com.helpfox.main;

import com.helpfox.main.core.components.layout.DrawerLayout;
import com.helpfox.main.core.components.layout.SlidingTabLayout;
import com.helpfox.main.core.components.layout.ViewPager;
import com.helpfox.main.core.framework.ActionBarDrawerToggle;
import com.helpfox.main.core.framework.FragmentStatePagerAdapter;
import com.helpfox.main.core.framework.Toolbar;
import com.helpfox.main.core.manager.Activity;
import com.helpfox.main.core.manager.Fragment;
import com.helpfox.main.core.manager.FragmentManager;
import com.helpfox.main.fragments.AppFragment;
import javafx.fxml.FXML;

import java.util.ArrayList;
import java.util.HashMap;

public class AppActivity extends Activity {
    @FXML
    Toolbar toolbar;
    @FXML
    private SlidingTabLayout tabLayout;
    @FXML
    private ViewPager viewPager;
    @FXML
    private DrawerLayout drawer;

    @Override
    public void onCreate() {
        super.onCreate();
        setContentView(getClass().getResource("app_activity.fxml"));

        toolbar.setTitle("App Activity");
        toolbar.setDisplayShowHomeEnabled(true);

        setActionBar(toolbar);

        drawer.setDrawerListener(new ActionBarDrawerToggle(this,drawer,toolbar));

        ViewPagerAdapter pagerAdapter=new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.addTab(new Tab("Fragment", AppFragment.class));
        pagerAdapter.addTab(new Tab("Fragment 1", AppFragment.class));
        tabLayout.setViewPager(viewPager);

        viewPager.setCurrentItem(0);

    }


    private static class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private ArrayList<Tab> tabs;
        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            tabs=new ArrayList<>();
        }

        @Override
        public Fragment getItem(int i) {
            try {
                Fragment fragment=tabs.get(i).fragment.newInstance();

                HashMap arguments=new HashMap();
                arguments.put("textLabel","Fragment #"+(i+1));
                fragment.setArguments(arguments);

                return fragment;

            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        public String getPageTitle(int i) {
            return tabs.get(i).title;
        }

        @Override
        public int getCount() {
            return tabs.size();
        }

        public void addTab(Tab tab){
            tabs.add(tab);
        }


    }

    private static class Tab{
        protected String title;
        protected Class<? extends Fragment> fragment;

        public Tab(String title,Class<? extends Fragment > fragment){
            this.title=title;
            this.fragment=fragment;
        }
    }

}

