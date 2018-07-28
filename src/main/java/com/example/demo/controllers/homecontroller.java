package com.example.demo.controllers;

	
import com.mysql.jdbc.Connection;
import java.sql.*;
import java.sql.DriverManager;

import org.springframework.stereotype.Controller;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class homecontroller {
	
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}
    @RequestMapping("/login")
    public String login() {
    	return "login.html";
    }
    
   
  
    public final static class MysqlConnect {
        public Connection conn;
        private Statement statement;
        public static MysqlConnect db;
        private MysqlConnect() {
            String url= "jdbc:mysql://localhost:3306/";
            String dbName = "database_name";
            String driver = "com.mysql.jdbc.Driver";
            String userName = "root	";
            String password = "root";
            try {
                Class.forName(driver).newInstance();
                this.conn = (Connection)DriverManager.getConnection(url+dbName,userName,password);
            }
            catch (Exception sqle) {
                sqle.printStackTrace();
            }
        }
        
        public static synchronized MysqlConnect getDbCon() {
            if ( db == null ) {
                db = new MysqlConnect();
            }
            return db;
     
        }
       
        public ResultSet query(String query) throws SQLException{
            statement = db.conn.createStatement();
            ResultSet res = statement.executeQuery(query);
            return res;
        }
        
        public int insert(String insertQuery) throws SQLException {
            statement = db.conn.createStatement();
            int result = statement.executeUpdate(insertQuery);
            return result;
     
        }
     
    }

}

