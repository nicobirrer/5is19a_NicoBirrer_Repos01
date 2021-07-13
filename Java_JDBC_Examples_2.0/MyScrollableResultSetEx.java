package com.java2novice.jdbc;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class MyScrollableResultSetEx {
 
    public static void main(String a[]){
         
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.
                getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>"
                    ,"user","password");
            st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);
            rs = st.executeQuery("select accno, bal from bank");
            System.out.println("ResultSet Curson is at before first: "+rs.isBeforeFirst());
            while(rs.next()){
                System.out.println(rs.getInt(1)+"   "+rs.getDouble(2));
            }
            //now result set cursor reached the last position
            System.out.println("Is After Last: "+rs.isAfterLast());
            while(rs.previous()){
                System.out.println(rs.getInt(1)+"   "+rs.getDouble(2));
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(rs != null) rs.close();
                if(st != null) st.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}
