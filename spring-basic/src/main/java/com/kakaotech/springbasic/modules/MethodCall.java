package com.kakaotech.springbasic.modules;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


class ModelController {
    public String main(HashMap hashMap) {
        hashMap.put("id", "asdf");
        hashMap.put("pwd", "1234");

        return "txtView1";
    }
}


public class MethodCall {
    public static void main(String[] args) throws Exception {
        HashMap hashMap = new HashMap();
        System.out.println("before: " + hashMap);

        ModelController mc = new ModelController();
        String viewName = mc.main(hashMap);

        System.out.println("after: " + hashMap);

        render(hashMap, viewName);
    }

    static void render(HashMap hashMap, String viewName) throws IOException {
        String result = "";

        Scanner scanner = new Scanner(new File(viewName + ".txt"));
        while (scanner.hasNextLine()) {
            result += scanner.nextLine() + System.lineSeparator();
        }

        Iterator iterator = hashMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            result = result.replace("${" + key + "}", (String) hashMap.get(key));
        }

        System.out.println(result);
    }
}
