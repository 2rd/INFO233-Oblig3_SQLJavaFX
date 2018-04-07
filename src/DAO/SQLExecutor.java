package DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Klasse for å kjøre en SQL-fil mot en database.
 */
public class SQLExecutor {

    /**
     * Gjør innholdet i en fil om til InputStream.
     * @param file Filen som skal konverteres
     * @return Filen som InputStream-objekt
     */
    private static InputStream toInputStream(File file){
        try {
            InputStream in = new FileInputStream(file);
            return in;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Parser en SQL-fil og kjører hver linje mot en gitt databasetilkobling.
     * @param conn Databasetilkoblingen filen skal kjøres mot.
     * @param file Filen som skal kjøres.
     */
    public static void executeSQL(Connection conn, File file)
    {
        InputStream input = toInputStream(file);
        Scanner scanner = new Scanner(input);
        scanner.useDelimiter("(;(\r)?\n)|(--\n)");

        Statement statement = null;

        try{
            statement = conn.createStatement();

            while (scanner.hasNext()){
                String line = scanner.next();

                if (line.startsWith("/*!") && line.endsWith("*/")) {
                    int i = line.indexOf(' ');
                    line = line.substring(i + 1, line.length() - " */".length());
                }

                if (line.trim().length() > 0){
                    statement.execute(line);
                }
            }
            statement.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
