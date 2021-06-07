package com.example.leetcode.part4;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * @author : Yang Jian
 * @date : 2021/6/7 0007 22:24
 */
public class Practice {
    public static void main(String[] args) throws IOException {
        /*User user = User.builder().age(12).name("aa").build();
        User user1 = User.builder().age(22).name("bb").build();
        User user2 = User.builder().age(32).name("ee").build();
        User user3 = User.builder().age(42).name("cc").build();
        User user4 = User.builder().age(52).name("dd").build();
        List<User> users = Arrays.asList(user, user1, user2, user3, user4);
        String fileName = "src\\main\\resources\\a.txt";
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        for (User user5 : users) {
            bw.write(user5.toString());
            bw.newLine();
        }
        bw.flush();*/
        /*String line3 = Files.readAllLines(Paths.get("src\\main\\resources\\a.txt")).get(3);
        System.out.println(line3);*/
        String fileName = "src\\main\\resources\\a.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String str;
        int index = 0;
        while (true) {
            str = br.readLine();
            if (str == null) {
                break;
            }
            if (index == 3) {
                break;
            }
            index++;
        }
        System.out.println(str);
    }
}
