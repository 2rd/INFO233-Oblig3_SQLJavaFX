package DAO;

import java.io.*;
import java.sql.*;

/**
 * Klasse for å koble til en database.
 * @author Tord Kvifte
 */
public class ConnectionDAO {

    private static File schema = new File("oblig3v1_database.sql");

    /**
     * Oppretter tilkobling til databasen.
     * Legger til schema dersom databasen er tom.
     */
    public static Connection getConnection() {

        Connection conn = null;
        try {
            String DBUrl = "jdbc:sqlite:oblig3.db";
            conn = DriverManager.getConnection(DBUrl);

            if (conn != null) {
                setSchema(conn);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Stenger tilkoblingen til databasen.
     */
    public static void closeConnection(){
        try{
            if(getConnection() != null){
                getConnection().close();
            }
        }
        catch (SQLException e){
            e.getMessage();
        }
    }

    private static void setSchema(Connection conn){
        if(countTables(conn) == 0){
            SQLExecutor.executeSQL(conn, schema);
            System.out.println("schema lagt til...");
        }
    }
    /**
     * Metode for å telle antall tabeller i en database.
     * @param conn tilkoblingen til databasen.
     * @return antall tabeller i databasen.
     */
    private static int countTables(Connection conn){

        int count = 0;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM sqlite_master WHERE type='table'");
            while (rs.next()){
                count++;
            }
            return count;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return count;
    }
}
