package javaio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by lucien on 15/5/26.
 */
public class IODemo {

    //读取文本文件
    public static void readFile(String path){
        File f = new File(path);
        if (f.exists()) {
            try {
                FileReader reader = new FileReader(path);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = bufferedReader.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("file is not exists");
        }
    }


    public static void main(String[] args) {
        String path = "/Users/lucien/.gitconfig";
//        readFile(path);
        List<Integer> list = new ArrayList<Integer>();
        list.add(7498);
        list.add(7444);
        list.add(7478);
        Collections.sort(list);
        System.out.println(list);
        Collections.reverse(list);
        System.out.println(list);

        List<Integer> offsetList = new ArrayList<Integer>();
        offsetList.addAll(list.subList(0, 3));
        System.out.println(offsetList);
        for (Integer id : offsetList) {
            System.out.println(id);
        }
    }
}
