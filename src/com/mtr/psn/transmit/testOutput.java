 package com.mtr.psn.transmit;

import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class testOutput {

    public final static String outputFile="X:\\Users\\wjb\\Desktop\\123.xlsx";

    public final static String url="jdbc:mysql://127.0.0.1:3306/fiancesystem?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true";

    public final static String user="root";

    public final static String password="123456";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=(Connection) DriverManager.getConnection(url, user, password);
            Statement stat = (Statement) conn.createStatement();
            ResultSet resultSet = stat.executeQuery("select  * from orderform;");
            SXSSFWorkbook workbook=new SXSSFWorkbook(100);
            SXSSFSheet sheet=workbook.createSheet("countryDB");
            SXSSFRow row = sheet.createRow((short)0);
            SXSSFCell cell=null;
            cell=row.createCell((short)0);
            cell.setCellValue("orderDate");
            cell=row.createCell((short)1);
            cell.setCellValue("franchiseeID");
            cell=row.createCell((short)2);
            cell.setCellValue("teamID");
            cell=row.createCell((short)3);
            cell.setCellValue("merchantID");
            cell=row.createCell((short)4);
            cell.setCellValue("orderFrom");
            cell=row.createCell((short)5);
            cell.setCellValue("orderNum");
            cell=row.createCell((short)6);
            cell.setCellValue("billNum");
            cell=row.createCell((short)7);
            cell.setCellValue("riderID");
            cell=row.createCell((short)8);
            cell.setCellValue("overTime");
            cell=row.createCell((short)9);
            cell.setCellValue("status");
            cell=row.createCell((short)10);
            cell.setCellValue("fastDelivery");
            cell=row.createCell((short)11);
            cell.setCellValue("reason");
            cell=row.createCell((short)12);
            cell.setCellValue("result");
            cell=row.createCell((short)13);
            cell.setCellValue("illegal");
            cell=row.createCell((short)14);
            cell.setCellValue("orderTime");
            cell=row.createCell((short)15);
            cell.setCellValue("endTime");
            cell=row.createCell((short)16);
            cell.setCellValue("tdFee");
            cell=row.createCell((short)17);
            cell.setCellValue("jcFee");
            cell=row.createCell((short)18);
            cell.setCellValue("hdSubsidy");
            cell=row.createCell((short)19);
            cell.setCellValue("djSubsidy");
            cell=row.createCell((short)20);
            cell.setCellValue("sxSubsidy");
            cell=row.createCell((short)21);
            cell.setCellValue("jlSubsidy");
            cell=row.createCell((short)22);
            cell.setCellValue("sdSubsidy");
            cell=row.createCell((short)23);
            cell.setCellValue("tqSubsidy");
            cell=row.createCell((short)24);
            cell.setCellValue("yxsSubsidy");
            cell=row.createCell((short)25);
            cell.setCellValue("xtDebit");
            cell=row.createCell((short)26);
            cell.setCellValue("psDebit");
            cell=row.createCell((short)27);
            cell.setCellValue("ddDebit");
            cell=row.createCell((short)28);
            cell.setCellValue("sxDebit");
            cell=row.createCell((short)29);
            cell.setCellValue("wgDebit");
            cell=row.createCell((short)30);
            cell.setCellValue("weightMarkup");
            cell=row.createCell((short)31);
            cell.setCellValue("jjDebit");
            cell=row.createCell((short)32);
            cell.setCellValue("llDebit");
            cell=row.createCell((short)33);
            cell.setCellValue("wgfDebit");
            cell=row.createCell((short)34);
            cell.setCellValue("id");
             
            
            int i=1;
            while(resultSet.next())
            {
                row=sheet.createRow(i);
                cell=row.createCell(0);
                cell.setCellValue(resultSet.getString("orderDate"));
                cell=row.createCell(1);
                cell.setCellValue(resultSet.getString("franchiseeID"));
                cell=row.createCell(2);
                cell.setCellValue(resultSet.getString("teamID"));
                cell=row.createCell(3);
                cell.setCellValue(resultSet.getString("merchantID"));
                cell=row.createCell(4);
                cell.setCellValue(resultSet.getString("orderFrom"));
                cell=row.createCell(5);
                cell.setCellValue(resultSet.getString("orderNum"));
                cell=row.createCell(6);
                cell.setCellValue(resultSet.getString("billNum"));
                cell=row.createCell(7);
                cell.setCellValue(resultSet.getString("riderID"));
                cell=row.createCell(8);
                cell.setCellValue(resultSet.getString("overTime"));
                cell=row.createCell(9);
                cell.setCellValue(resultSet.getString("status"));
                cell=row.createCell(10);
                cell.setCellValue(resultSet.getString("fastDelivery"));
                cell=row.createCell(11);
                cell.setCellValue(resultSet.getString("reason"));
                cell=row.createCell(12);
                cell.setCellValue(resultSet.getString("result"));
                cell=row.createCell(13);
                cell.setCellValue(resultSet.getString("illegal"));
                cell=row.createCell(14);
                cell.setCellValue(resultSet.getString("orderTime"));
                cell=row.createCell(15);
                cell.setCellValue(resultSet.getString("endTime"));
                cell=row.createCell(16);
                cell.setCellValue(resultSet.getString("tdFee"));
                cell=row.createCell(17);
                cell.setCellValue(resultSet.getString("jcFee"));
                cell=row.createCell(18);
                cell.setCellValue(resultSet.getString("hdSubsidy"));
                cell=row.createCell(19);
                cell.setCellValue(resultSet.getString("djSubsidy"));
                cell=row.createCell(20);
                cell.setCellValue(resultSet.getString("sxSubsidy"));
                cell=row.createCell(21);
                cell.setCellValue(resultSet.getString("jlSubsidy"));
                cell=row.createCell(22);
                cell.setCellValue(resultSet.getString("sdSubsidy"));
                cell=row.createCell(23);
                cell.setCellValue(resultSet.getString("tqSubsidy"));
                cell=row.createCell(24);
                cell.setCellValue(resultSet.getString("yxsSubsidy"));
                cell=row.createCell(25);
                cell.setCellValue(resultSet.getString("xtDebit"));
                cell=row.createCell(26);
                cell.setCellValue(resultSet.getString("psDebit"));
                cell=row.createCell(27);
                cell.setCellValue(resultSet.getString("ddDebit"));
                cell=row.createCell(28);
                cell.setCellValue(resultSet.getString("sxDebit"));
                cell=row.createCell(29);
                cell.setCellValue(resultSet.getString("wgDebit"));
                cell=row.createCell(30);
                cell.setCellValue(resultSet.getString("weightMarkup"));
                cell=row.createCell(31);
                cell.setCellValue(resultSet.getString("jjDebit"));
                cell=row.createCell(32);
                cell.setCellValue(resultSet.getString("llDebit"));
                cell=row.createCell(33);
                cell.setCellValue(resultSet.getString("wgfDebit"));
                cell=row.createCell(34);
                cell.setCellValue(resultSet.getString("id"));
                
                i++;
             }
            FileOutputStream FOut = new FileOutputStream(outputFile);
            workbook.write(FOut);
            FOut.flush();
            FOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}