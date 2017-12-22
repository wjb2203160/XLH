 package com.mtr.psn.transmit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
 
import java.util.Date;
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;  
  
public class orderImport
{   
    private static Connection con;  
  
    public static void main(String[] args) throws SQLException, NumberFormatException, IOException  
    {  
  
        long startTime = System.currentTimeMillis();  
        File file = new File("D:/10.csv");  
        
        InputStreamReader read = null;
        BufferedReader bfr=null;
		try {
			read = new InputStreamReader(new FileInputStream(file),"utf-8");
			bfr=new BufferedReader(read);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
      /*  Scanner in = new Scanner(file);  */
  
        getConnect();  
        System.out.println("数据库连接成功");  
        insert_data(bfr);  
  
        long EndTime = System.currentTimeMillis();  
        long time = (EndTime - startTime) / 1000;  
  
        System.out.println("导入数据共用时：" + time);  
    }  
  
    private static void insert_data(BufferedReader bfr) throws SQLException, NumberFormatException, IOException  
    {   //导入配送订单
        int count = 0;  
        con.setAutoCommit(false);  
        String sql = "insert IGNORE into orderform ("
        		+ "orderDate,"
        		+ "franchiseeID,"
        		+ "teamID,"
        		+ "merchantID,"
        		+ "orderFrom,"
        		+ "orderNum,"
        		+ "billNum,"
        		+ "riderID,"
        		+ "overTime,"
        		+ "status,"
        		+ "fastDelivery,"
        		+ "reason,"
        		+ "result,"
        		+ "illegal,"
        		+ "orderTime,"
        		+ "endTime,"
        		+ "tdFee,"
        		+ "jcFee,"
        		+ "hdSubsidy,"
        		+ "djSubsidy,"
        		+ "sxSubsidy,"
        		+ "jlSubsidy,"
        		+ "sdSubsidy,"
        		+ "tqSubsidy,"
        		+ "yxsSubsidy,"
        		+ "xtDebit,"
        		+ "psDebit,"
        		+ "ddDebit,"
        		+ "sxDebit,"
        		+ "wgDebit,"
        		+ "weightMarkup,"
        		+ "jjDebit,"
        		+ "llDebit,"
        		+ "wgfDebit"
        		+ ")"  
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";  
  
        
        PreparedStatement pstmt = con.prepareStatement(sql);  
        //导入骑手
        sql = "insert IGNORE into rider ("
        		+ "riderID,"
        		+ "teamID,"
        		+ "riderName,"
        		+ "createTime,"
        		+ "createBy"
        		+ ")"  
                + "values(?,?,?,?,?)";  
       
       PreparedStatement rider = con.prepareStatement(sql);  
      
        sql = "insert IGNORE into team ("
          		+ "teamID,"
          		+ "teamName,"
          		+ "createTime,"
          		+ "createBy"
          		+ ")"  
                  + "values(?,?,?,?)";  
        PreparedStatement team = con.prepareStatement(sql); 
        
        sql = "insert IGNORE into franchisee ("
        		+ "franchiseeID,"
        		+ "franchiseeName,"
        		+ "createTime,"
        		+ "createBy"
        		+ ")"  
                + "values(?,?,?,?)";  
        PreparedStatement franchisee = con.prepareStatement(sql); 
        
        sql = "insert ignore into merchant ("
          		+ "merchantID,"
          		+ "merchantName,"
          		+ "createTime,"
          		+ "createBy"
          		+ ")"  
                  + "values(?,?,?,?)";  
        PreparedStatement merchant = con.prepareStatement(sql);
        String line="";
        bfr.readLine();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
       
        while ((line=bfr.readLine())!=null)  
        {     
        	  String[] temp = null;
              temp = line.split(",");  
              int index=1;
                pstmt.setString(index++,   temp[0]);  
                pstmt.setInt(index++, Integer.valueOf(temp[1]));  
                pstmt.setInt(index++, Integer.valueOf(temp[3]));  
                pstmt.setInt(index++, Integer.valueOf(temp[5]));  
                pstmt.setString(index++, temp[7]);  
                pstmt.setString(index++, temp[8].substring(1,temp[8].length()));  
                pstmt.setString(index++, temp[9].substring(1,temp[9].length()));  
                pstmt.setInt(index++, Integer.valueOf(temp[10]));  
                pstmt.setString(index++, temp[12]);  
                pstmt.setString(index++, temp[13]);  
                pstmt.setString(index++, temp[14]); 
                pstmt.setString(index++, temp[15]); 
                pstmt.setString(index++, temp[16]); 
                pstmt.setString(index++, temp[17]); 
                pstmt.setString(index++, temp[18]); 
                pstmt.setString(index++, temp[19]); 
                pstmt.setDouble(index++, Double.valueOf(temp[20]));
                pstmt.setDouble(index++, Double.valueOf(temp[21]));
                pstmt.setDouble(index++, Double.valueOf(temp[22]));
                pstmt.setDouble(index++, Double.valueOf(temp[23]));
                pstmt.setDouble(index++, Double.valueOf(temp[24]));
                pstmt.setDouble(index++, Double.valueOf(temp[25]));
                pstmt.setDouble(index++, Double.valueOf(temp[26]));
                pstmt.setDouble(index++, Double.valueOf(temp[27]));
                pstmt.setDouble(index++, Double.valueOf(temp[28]));
                pstmt.setDouble(index++, Double.valueOf(temp[29]));
                pstmt.setDouble(index++, Double.valueOf(temp[30]));
                pstmt.setDouble(index++, Double.valueOf(temp[31]));
                pstmt.setDouble(index++, Double.valueOf(temp[32]));
                pstmt.setDouble(index++, Double.valueOf(temp[33]));
                //pstmt.setDouble(index++, Double.valueOf(temp[34]));
                pstmt.setDouble(index++, Double.valueOf(temp[35]));
                pstmt.setDouble(index++, Double.valueOf(temp[36]));
                pstmt.setDouble(index++, Double.valueOf(temp[37]));
                pstmt.setDouble(index++, Double.valueOf(temp[38]));
               
                //导入骑手
                index=1;
		        rider.setInt(index++,    Integer.valueOf(temp[10].toString()));  
		        rider.setInt(index++,    Integer.valueOf(temp[3].toString()));  
		        rider.setString(index++, temp[11].toString() );  
		        rider.setString(index++,  sdf.format(new Date()));  
		        rider.setInt(index++, 1);  
		        
		       //导入团队
		        index=1;
		        team.setInt(index++,    Integer.valueOf(temp[3].toString()));  
		        team.setString(index++,temp[4] );  
		        team.setString(index++,  sdf.format(new Date()));  
		        team.setInt(index++, 1);  
		       
		        //导入加盟商
		        index=1;
		        franchisee.setInt(index++,    Integer.valueOf(temp[1].toString()));  
		        franchisee.setString(index++,temp[2]);  
		        franchisee.setString(index++,  sdf.format(new Date()));  
		        franchisee.setInt(index++, 1);  
		        
		        //导入商户
		        index=1;
		        merchant.setInt(index++,    Integer.valueOf(temp[5].toString()));  
		        merchant.setString(index++, temp[6]);  
		        merchant.setString(index++,  sdf.format(new Date()));  
		        merchant.setInt(index++, 1); 
		        merchant.addBatch();
		        pstmt.addBatch();  
		        franchisee.addBatch();  
		        team.addBatch();
		        rider.addBatch();
            count++;  
  
            if (count == 20000)  
            {  
                count = execute(pstmt, count);  
                count = execute(merchant, count);  
                count = execute(team, count);  
                count = execute(rider, count);  
                count = execute(franchisee, count);  
            }  
        }
        
       pstmt.executeBatch();  
        con.commit();  
        
          
        merchant.executeBatch();  
        con.commit();  
        team.executeBatch();  
        con.commit();  
        
        franchisee.executeBatch();  
        con.commit(); 
        
        rider.executeBatch();  
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
            		"jdbc:mysql://127.0.0.1:3306/pay_state_nuclear_db?useUnicode=true&useOldAliasMetadataBehavior=true&characterEncoding=UTF-8&useServerPrepStmts=false&rewriteBatchedStatements=true&useSSL=true",  
                    "root", "123456");    
        }  
        catch (ClassNotFoundException | SQLException e)  
        {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
  
}  