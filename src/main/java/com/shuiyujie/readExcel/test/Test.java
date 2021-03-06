package com.shuiyujie.readExcel.test;

import com.shuiyujie.readExcel.exception.RepeatCreateObject;
import com.shuiyujie.readExcel.readExcel.ReadExcel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Test {
	public static void main(String[] args) {
	
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connection connection = null;
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?userUnicode=true&characterEncoding=UTF-8","root","root");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ReadExcel readExcel = null;
			try {
				readExcel = ReadExcel.newInstance(connection);
			} catch (RepeatCreateObject e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = 0;
	
			try {
				i = readExcel.readExcelToMysql("/Users/shui/Downloads/tm_good.xls");
			} catch (Exception e) {
				e.printStackTrace();
				try {
					System.out.println(readExcel.getLogs());
					System.out.println("-------");
					System.out.println(readExcel.getNewLog());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
			System.out.println(i);
	}
}
