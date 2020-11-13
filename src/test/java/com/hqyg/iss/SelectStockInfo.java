package com.hqyg.iss;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class SelectStockInfo {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://192.168.0.202:3307";
        String username = "iss_all";
        String password = "^V8E$#&2Cx";
        Connection con = DriverManager.getConnection(url,username,password);
        Scanner scanner =new Scanner(System.in);
        String  user =scanner.nextLine();
        String pass = scanner.nextLine();

        String sql= "select * from users where username =? and pass=?";

    }

}
