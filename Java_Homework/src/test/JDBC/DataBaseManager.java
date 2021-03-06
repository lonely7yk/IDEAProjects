package test.JDBC;

import java.sql.*;

public class DataBaseManager {
    Connection con;
    ResultSet rs;
    Statement stmt;

    public DataBaseManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/shengliyi?serverTimezone=UTC";
            con = DriverManager.getConnection(url, "root", "123456");
            stmt = con.createStatement();
        } catch (ClassNotFoundException cnfex) {
            System.out.println("Failed to load JDBC/ODBC driver.");
            cnfex.printStackTrace();
            System.exit(1);
        } catch (SQLException sqle) {
            System.out.println(sqle.toString());
        }
    }

    public ResultSet getResult(String strSQL) {
        try {
            rs = stmt.executeQuery(strSQL);
            return rs;
        } catch (SQLException sqle) {
            System.out.println(sqle.toString());
            return null;
        }
    }

    public boolean updateSql(String strSQL) {
        try {
            stmt.executeUpdate(strSQL);
            con.commit();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public void closeConnection() {
        try {
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.toString());
        }
    }

    public static void main(String[] args) {
        Connection con = null;
        Statement stmt;

    }
}
