package com.java2novice.jdbc;
 
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
 
public class MyFunctionCallableStmt {
 
    public static void main(String a[]){
         
        Connection con = null;
        CallableStatement callSt = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.
                    getConnection("jdbc:oracle:thin:@<hostname>:<port num>:<DB name>"
                        ,"user","password");
            callSt = con.prepareCall("{?= call myfunction(?,?)}");
            callSt.setInt(1,200);
            //below method used to register data type of the out parameter
            callSt.registerOutParameter(2, Types.DOUBLE);
            callSt.execute();
            Double output = callSt.getDouble(2);
            System.out.println("The output returned from sql function: "+output);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally{
            try{
                if(callSt != null) callSt.close();
                if(con != null) con.close();
            } catch(Exception ex){}
        }
    }
}