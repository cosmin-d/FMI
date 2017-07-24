/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Madalina
 */
public class DB {
     private String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   private String DB_URL = "jdbc:mysql://localhost/";

   //  Database credentials
   static final String USER = "root";
   static final String PASS = "password";
    Connection conn = null;
   PreparedStatement stmt = null;
   private static int index;
   
  public DB() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } 
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
        catch (SQLException e) {
            
        }
    }
   
   public void CreateDB()
   {
       try{
    
      Class.forName("com.mysql.jdbc.Driver");

   
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

 
      System.out.println("Creating database...");
      
      
      String sql = "CREATE DATABASE APELURI";
      stmt = conn.prepareStatement(sql);
      stmt.executeUpdate(sql);
      System.out.println("Database created successfully...");
      CreateTable();
   }catch(SQLException se){
 if(se.getErrorCode()==1007)
 {
     System.out.println("Baza de date deja existenta!");
     return;
 }//    se.printStackTrace();
   }catch(Exception e){
   
      e.printStackTrace();
   }finally{
  
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   System.out.println("Goodbye!");
}
   
   public void DropDB()
   {
       try{
  
      Class.forName("com.mysql.jdbc.Driver");

      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      System.out.println("Deleting database...");
      
      String sql = "DROP DATABASE APELURI";
       stmt = conn.prepareStatement(sql);
      stmt.executeUpdate(sql);
     
      System.out.println("Database deleted successfully...");
   }catch(SQLException se){
    
      se.printStackTrace();
   }catch(Exception e){
     
      e.printStackTrace();
   }finally{
   
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   System.out.println("Goodbye!");
}
   
   public void CreateTable()
   {
       try{
      //STEP 2: Register JDBC driver
       DB_URL = "jdbc:mysql://localhost/APELURI";
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      System.out.println("Creating table in given database...");
      
      
      String sql = "CREATE TABLE INREGISTRARI " +
                   "(id INTEGER not NULL, " +
                   " data VARCHAR(255), " +
                   " nume VARCHAR(255)," +
                   " numar VARCHAR(255), " + 
                   " durata VARCHAR(255), " +
                   " PRIMARY KEY ( id ))"; 
 stmt = conn.prepareStatement(sql);
      stmt.executeUpdate(sql);
      System.out.println("Created table in given database...");
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
   }
   
   public void Insert(  String d, String nr,String drt) throws ClassNotFoundException
   {
        try{
      //STEP 2: Register JDBC driver
       DB_URL = "jdbc:mysql://localhost/APELURI";
      Class.forName("com.mysql.jdbc.Driver");

      index++;
      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
     
      
     String query=" INSERT INTO INREGISTRARI(id,data,nume,numar,durata)"+
             "values(?,?,?,?,?)";
     stmt=conn.prepareStatement(query);
     
     stmt.setInt(1, index);
     stmt.setString(2,d);
     stmt.setString(3,"Unknown");
     stmt.setString(4,nr);
     stmt.setString(5,drt);
    
     
     stmt.execute();
     conn.close();
     
     
      System.out.println("Inserted records into the table...");

   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
}//end main
   
   public String Search(String s)
   {
       String n="-";
       try{
       DB_URL = "jdbc:mysql://localhost/APELURI";
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      System.out.println("Fetching records with condition...");
     String sql = "SELECT id, data , nume,numar,durata  FROM INREGISTRARI" +
                   " WHERE numar = '"+s +"'";
        stmt = conn.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery(sql);
    
        while(rs.next()){
     
         int id  = rs.getInt("id");
         String d=rs.getString("data");
           n=rs.getString("nume");
         
           String nr=rs.getString("numar");
            String drt=rs.getString("durata");
             System.out.println(n);
       
      }

      rs.close();
     
   }catch(SQLException se){
  
      se.printStackTrace();
   }catch(Exception e){
  
      e.printStackTrace();
   }finally{

      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }
   }
   System.out.println("Goodbye!");
   return n;
}
   
   public void Update(String s,String s2)
   {
 
   try{
        DB_URL = "jdbc:mysql://localhost/APELURI";
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");

      //STEP 3: Open a connection
      System.out.println("Connecting to a selected database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASS);
      System.out.println("Connected database successfully...");
      
      //STEP 4: Execute a query
      System.out.println("Creating statement...");
     
      String sql = "UPDATE INREGISTRARI " +
                   "SET nume = '"+ s2 +"' WHERE numar = '"+s +"'";
       stmt = conn.prepareStatement(sql);
      stmt.executeUpdate(sql);

      // Now you can extract all the records
      // to see the updated records
      sql = "SELECT id, data , nume,numar,durata  FROM INREGISTRARI";
      ResultSet rs = stmt.executeQuery(sql);

       while(rs.next()){
     
         int id  = rs.getInt("id");
         String d=rs.getString("data");
         String n=rs.getString("nume");
         
           String nr=rs.getString("numar");
            String drt=rs.getString("durata");
           
             
             System.out.println(n);
             System.out.println(nr);
       
      }
      rs.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
   System.out.println("Goodbye!");
//end main
   }
   
   
}
