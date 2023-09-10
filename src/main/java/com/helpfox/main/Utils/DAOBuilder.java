package com.helpfox.main.Utils;

import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Model.Vehicle.VehicleDAO;

import java.util.ArrayList;
import java.util.List;

public class DAOBuilder {
    public static List<Object> daoInstances = new ArrayList<>();
    public static String pkgName = "com.helpfox.main.Model.SQLite";

    public static List<Object> buildDAO(List<String> daoClassNames) {
        try {
            ClassLoader classLoader = DAOBuilder.class.getClassLoader();

            for (String daoClassName : daoClassNames) {
                try {
                    Class<?> clazz = classLoader.loadClass(pkgName + "." + daoClassName);
                    Object daoInstance = clazz.getDeclaredConstructor().newInstance();
                    daoInstances.add(daoInstance);
                } catch (ClassNotFoundException e) {
                    System.err.println("Class not found: " + daoClassName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return daoInstances;
    }

    public static List<Object> buildAllDAO() {
        try {
            // Get the class loader
            ClassLoader classLoader = DAOBuilder.class.getClassLoader();

            // Use the class loader to load all classes in the package
            Class<?>[] classes = classLoader.loadClass(pkgName).getDeclaredClasses();

            // Iterate through the classes and instantiate DAOs
            for (Class<?> clazz : classes) {
                // Check if the class name ends with "DAO"
                if (clazz.getSimpleName().endsWith("DAO")) {
                    Object daoInstance = clazz.getDeclaredConstructor().newInstance();
                    daoInstances.add(daoInstance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return daoInstances;
    }

    public static Object findDao(List<Object> daoInstances, SetDAO setDAO) {
        Object EMPTY, userDAO, driverDAO, vehicleDAO;
        EMPTY = new Object();
        userDAO = new Object();
        driverDAO = new Object();
        vehicleDAO = new Object();

        for (Object dao : daoInstances) {
            switch (setDAO) {
                case USERDAO -> {
                    if (dao instanceof UserDAO) {
                        return dao = userDAO;
                    }
                }
                case DRIVERDAO -> {
                    if (dao instanceof DriverDAO) {
                        dao = driverDAO;
                    }
                }
                case VEHICLEDAO -> {
                    if (dao instanceof VehicleDAO) {
                        dao = vehicleDAO;
                    }
                }
            }

            return dao;
        }

        return EMPTY;
    }
}
