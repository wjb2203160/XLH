 package com.mtr.psn.transmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;  
import java.util.Scanner;  
  
public class appealImport
{  
    private static Connection con;  
  
    public static void main(String[] args) throws SQLException, IOException  
    {  
  
        long startTime = System.currentTimeMillis();  
        File file = new File("D:/2017-10-01-2017-10-31申诉账单.csv");  
  
        InputStreamReader read = null;
        BufferedReader bfr=null;
		try {
			read = new InputStreamReader(new FileInputStream(file),"gbk");
			bfr=new BufferedReader(read);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  
        getConnect();  
        System.out.println("数据库连接成功");  
        insert_data(bfr);  
  
        long EndTime = System.currentTimeMillis();  
        long time = (EndTime - startTime) / 1000;  
  
        System.out.println("导入数据共用时：" + time);  
    }  
  
    private static void insert_data(BufferedReader bfr) throws SQLException, IOException  
    {  
        int count = 0;  
        String sql = "insert ignore into appealform (complaintDate,complaintNum,complaintType,franchiseeID,teamID,merchantID,id,billNum,complaintAmount,reason)"  
                + "values(?,?,?,?,?,?,?,?,?,?)";  
  
        con.setAutoCommit(false);  
        PreparedStatement pstmt = con.prepareStatement(sql);  
        String line="";
        bfr.readLine();
        while ((line=bfr.readLine())!=null)  
        {  
        	String[] temp = null;
            temp = line.split(",");
            int index=1;
                pstmt.setString(index++, temp[0]);  
                pstmt.setInt(index++, Integer.valueOf(temp[1]));  
                pstmt.setString(index++, temp[2]);  
                pstmt.setInt(index++, Integer.valueOf(temp[4]));  
                pstmt.setInt(index++, Integer.valueOf(temp[6]));  
                pstmt.setInt(index++, Integer.valueOf(temp[8]));  
                pstmt.setString(index++, temp[9].substring(1,temp[9].length()));  
                pstmt.setString(index++, temp[10].substring(1,temp[10].length()));  
                pstmt.setDouble(index++, Double.valueOf(temp[11]));  
                pstmt.setString(index++, temp[12]);  
             
  
            pstmt.addBatch();  
  
            count++;  
  
            if (count == 20000)  
            {  
                count = execute(pstmt, count);  
            }  
        }  
        pstmt.executeBatch();  
        con.commit();  
  
    }  
  
    public static int execute(PreparedStatement pstmt, int count) throws SQLException  
    {  
  
        pstmt.executeBatch();  
        con.commit();  
        return 0;  
  
    }  
  
    private static void getConnect()  
    {  
        try  
        {  
            Class.forName("com.mysql.jdbc.Driver");  
            con = DriverManager.getConnection(  
                    "jdbc:mysql://127.0.0.1:3306/pay_state_nuclear_db?useUnicode=true&characterEncoding=utf8&useServerPrepStmts=false&rewriteBatchedStatements=true&useSSL=true",  
                    "root", "123456");  
        }  
        catch (ClassNotFoundException | SQLException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
}  