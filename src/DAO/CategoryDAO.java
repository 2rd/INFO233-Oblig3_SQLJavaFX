package DAO;

import Entities.Category;
import GUI.Main;

import java.sql.*;

public class CategoryDAO {
    private static Connection conn = Main.connextion;

    public static void addCategory(Category category) {

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            PreparedStatement newInvoice = conn.prepareStatement("INSERT INTO category (category_id, category_name) VALUES (?,?)");
            newInvoice.setInt(1, category.getCategoryId());
            newInvoice.setString(2, category.getCategoryName());
            newInvoice.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Category getCategoryById(int id){

        Category category = new Category();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            ResultSet categoryResult = statement.executeQuery("SELECT * FROM category WHERE category_id = " + id);

            category.setCategoryId(categoryResult.getInt("category_id"));
            category.setCategoryName(categoryResult.getString("category_name"));

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    public static void removeCategory(int id){

        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            statement.executeQuery("DELETE FROM category WHERE category_id = " + id);

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
