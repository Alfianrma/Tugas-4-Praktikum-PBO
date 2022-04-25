package login;

import java.sql.*;

public class koneksi {
    String dbUrl = "jdbc:mysql://localhost/tugasjdbc";
    String dbUsername = "root";
    String dbPassword = "";
    static final String driver = "com.mysql.cj.jdbc.Driver";
    Connection connect;
    Statement statement;
    public koneksi() {
        try{
            Class.forName(driver);
            connect = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
            System.out.println("koneksi berhasil");
        } catch(Exception ex){
            System.out.println("koneksi gagal");
        }
    }
}
