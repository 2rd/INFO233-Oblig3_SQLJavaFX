package DAO;

import Entities.Category;

import java.sql.*;

public class CategoryDAO {
    private static ConnectionDAO connection = new ConnectionDAO();

    public void addCategory(Category category) {
        Connection conn = connection.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            PreparedStatement newInvoice = conn.prepareStatement("INSERT INTO category (category_id, category_name) VALUES (?,?)");
            newInvoice.setInt(1, category.getCategoryId());
            newInvoice.setString(2, category.getCategoryName());
            newInvoice.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection.closeConnection();
        }
    }

    public static Category getAddressById(int id){
        Connection conn = connection.getConnection();
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
        finally {
            connection.closeConnection();
        }
        return category;
    }
}
