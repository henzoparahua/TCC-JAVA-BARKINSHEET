package com.helpfox.main.Model;

import com.helpfox.main.View.ViewFactory;

public class Model {
    private final ViewFactory viewFactory;
    private static Model model;

    private Model(){
        this.viewFactory = new ViewFactory();
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
