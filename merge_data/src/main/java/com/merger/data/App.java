package com.merger.data;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        try {
            Path left = new File("C:\\Users\\mingyuan.wang.QUNARSERVERS\\Desktop\\alipay\\dubbo-access-provider.2015-06-10.log\\dubbo-access-provider.2015-06-10_1.log").toPath();
            Path right = new File("C:\\Users\\mingyuan.wang.QUNARSERVERS\\Desktop\\alipay\\dubbo-access-provider.2015-06-10.log\\dubbo-access-provider.2015-06-10_2.log").toPath();
            BufferedReader readerLeft = Files.newBufferedReader(left, StandardCharsets.UTF_8);
            BufferedReader readerRight = Files.newBufferedReader(right, StandardCharsets.UTF_8);
            Map<String, String> result = new HashMap<String, String>();
            List<String> list = Lists.newArrayList();
            merge(readerLeft, list);
            merge(readerRight, list);
            int successful= 0;
//            for(Map.Entry entry:result.entrySet()){
//                if(entry.getValue().equals("true")){
//                    successful++;
//                }
//            }
            for(String s: list){
                if(s.equals("true")){
                    successful++;
                }
            }
            System.out.println("==========================================");
            System.out.println("total="+list.size()+";success="+successful);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void merge(BufferedReader reader, List<String> result) {
        try {
            String line = null;
            String line2 = null;
            for (; ; ) {
                line = reader.readLine();
                line2 = reader.readLine();
                if (line == null || line2 == null)
                    break;
                if (line.contains("isAlipayWhite")) {
                    int index = line.lastIndexOf("[");
                    String tmp = line.substring(index+1, line.length()-1);
                    Iterator parameters = Splitter.on(",").split(tmp).iterator();
                    String value = line2.substring(line2.indexOf("(")+1, line2.indexOf(")"));
                    String key = parameters.next() + "_" + parameters.next();
//                    result.put(key, value);
                    result.add(value);
                    System.out.println("key="+key+";value="+value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
