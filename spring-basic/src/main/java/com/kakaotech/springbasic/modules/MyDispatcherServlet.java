package com.kakaotech.springbasic.modules;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

@WebServlet("/myDispatcherServlet")
public class MyDispatcherServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map    map = request.getParameterMap();
        Model  model = null;
        String viewName = "";

        try {
            Class clazz = Class.forName("com.kakaotech.springbasic.controller.getDay");
            Object object = clazz.newInstance();

            Method main = clazz.getDeclaredMethod("main", int.class, int.class, int.class, Model.class);

            Parameter[] parameters = main.getParameters();
            Object[] arguments = new Object[main.getParameterCount()];

            for(int i=0;i<parameters.length;i++) {
                String parameter_name = parameters[i].getName();
                Class  parameter_type = parameters[i].getType();
                Object value = map.get(parameter_name);

                if (parameter_type == Model.class) {
                    arguments[i] = model = new BindingAwareModelMap();
                }
                else if (parameter_type == HttpServletRequest.class) {
                    arguments[i] = request;
                }
                else if (parameter_type == HttpServletResponse.class) {
                    arguments[i] = response;
                }
                else if (value != null) {
                    String strValue = ((String[])value)[0];
                    arguments[i] = convertTo(strValue, parameter_type);
                }
            }

            viewName = (String) main.invoke(object, arguments);
        } catch(Exception e) {
            e.printStackTrace();
        }

        render(model, viewName, response);
    }

    private Object convertTo(Object value, Class type) {
        if (type == null || value == null || type.isInstance(value)) {
            return value;
        }

        // 타입이 다르면, 변환해서 반환
        if (String.class.isInstance(value) && type==int.class) { // String -> int
            return Integer.valueOf((String)value);
        }
        else if (String.class.isInstance(value) && type == double.class) { // String -> double
            return Double.valueOf((String)value);
        }

        return value;
    }

    private String getResolvedViewName(String viewName) {
        return getServletContext().getRealPath("/WEB-INF/views") + "/" + viewName + ".jsp";
    }

    private void render(Model model, String viewName, HttpServletResponse response) throws IOException {
        String result = "";

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();

        Scanner scanner = new Scanner(new File(getResolvedViewName(viewName)), "utf-8");

        while (scanner.hasNextLine()) {
            result += scanner.nextLine() + System.lineSeparator();
        }

        Map map = model.asMap();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String)iterator.next();
            result = result.replace("${" + key + "}", map.get(key).toString());
        }

        // 5.렌더링 결과를 출력한다.
        out.println(result);
    }
}