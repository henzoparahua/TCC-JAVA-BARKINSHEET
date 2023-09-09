package com.helpfox.main.Utils;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class DAOFinder {
        public static List<Object> daoInstances = new ArrayList<>();
        public static String pkgName = "com.helpfox.main.Model.SQLite";
    public static List<Object> buildDAO(List<String> daoClassNames) {

        try {
            ClassLoader classLoader = DAOFinder.class.getClassLoader();
            Package pkg = classLoader.getDefinedPackage(pkgName);

            for (String daoClassName : daoClassNames) {
                try {
                    Class<?> clazz = Class.forName(pkg + "." + daoClassName);
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    Object daoInstance = constructor.newInstance();
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
            ClassLoader classLoader = DAOFinder.class.getClassLoader();

            // Use the class loader to load all classes in the package
            Package pkg = classLoader.getDefinedPackage(pkgName);
            Class<?>[] classes = Class.forName(pkg.toString()).getDeclaredClasses();

            // Iterate through the classes and instantiate DAOs
            for (Class<?> clazz : classes) {
                // Check if the class name ends with "DAO"
                if (clazz.getSimpleName().endsWith("DAO")) {
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    Object daoInstance = constructor.newInstance();
                    daoInstances.add(daoInstance);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return daoInstances;
    }
}
