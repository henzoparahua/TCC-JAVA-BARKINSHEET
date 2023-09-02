package com.helpfox.main.Model;

import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.User.User;
import com.helpfox.main.View.ViewFactory;

public class Model {
    private final SQLiteDriverDAO sqLiteDriverDAO;
    private final ViewFactory viewFactory;
    private static Model model;

    private User user;
    private boolean userLoginSuccessFlag;

    private Model(){

        this.viewFactory = new ViewFactory();
        this.sqLiteDriverDAO = new SQLiteDriverDAO();
        this.userLoginSuccessFlag = false;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public static synchronized Model getInstance(){
        if (model == null){
            model = new Model();
        }
        return model;
    }
}
