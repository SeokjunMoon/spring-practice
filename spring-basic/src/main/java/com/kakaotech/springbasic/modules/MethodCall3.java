package com.kakaotech.springbasic.modules;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

public class MethodCall3 {
    public static void main(String[] args) throws Exception {
        Map map = new HashMap();
        map.put("year", "2023");
        map.put("month", "5");
        map.put("date", "26");

        Model model = null;
        Class clazz = Class.forName("com.kakaotech.springbasic.controller.getDay");
        Object object = clazz.newInstance();

        Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);

        Parameter[] parameters = main.getParameters();
        Object[] arguments = new Object[main.getParameterCount()];

        for (int i = 0; i < parameters.length; i++) {
            String parameter_name = parameters[i].getName();
            Class parameter_type = parameters[i].getType();
            Object value = map.get(parameter_name);

            if (parameter_type == Model.class) {
                arguments[i] = model = new BindingAwareModelMap();
            }
            else if (value != null) {
                arguments[i] = convertTo(value, parameter_type);
            }
        }

        System.out.println("parameters = " + Arrays.toString(parameters));
        System.out.println("arguments = " + Arrays.toString(arguments));

        String viewName = (String) main.invoke(object, arguments);
        System.out.println("viewName = " + viewName);

        System.out.println("[ after ] model = " + model);

        render(model, viewName);
    }

    private static Object convertTo(Object value, Class type) {
        if (type == null || value == null || type.isInstance(value)) {
            return value;
        }

        if (String.class.isInstance(value) && type == int.class) {
            return Integer.valueOf((String) value);
        }
        else if (String.class.isInstance(value) && type == double.class) {
            return Double.valueOf((String) value);
        }

        return value;
    }

    private static void render(Model model, String viewName) throws IOException {
        String result = "";

        Scanner scanner = new Scanner(new File("src/main/webapp/WEB-INF/views/" + viewName + ".jsp"), "utf-8");
        while (scanner.hasNextLine()) {
            result += scanner.nextLine() + System.lineSeparator();
        }

        Map map = model.asMap();
        Iterator iterator = map.keySet().iterator();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            result = result.replace("&{" + key + "}", map.get(key).toString());
        }

        System.out.println(result);
    }
}
