package DAO;

import Entities.Category;
import GUI.Main;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasse for å aksessere Kategoritabellen i databasen.
 */
public class CategoryDAO {
    private static Connection conn = Main.connextion;

    /**
     * Legger til en kategori i databasen.
     * @param category kategorien som skal legges til.
     */
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

    /**
     * Hent en kategori fra databasen.
     * @param id id'en til kategorien som skal hentes
     * @return Kategorien som skal hentes.
     */
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

    /**
     * Hent alle kategoriene i tabellen.
     * @return en liste med alle kategoriene i databasen.
     */
    public static List<Category> getAllCategories() {
        Category currCategory = new Category();
        List<Category> categories = new LinkedList<Category>();


        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM category");
            while (rs.next()) {
                currCategory = new Category();

                currCategory.setCategoryId(rs.getInt("category_id"));
                currCategory.setCategoryName(rs.getString("category_name"));

                categories.add(currCategory);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    /**
     * Endre en eksisterende kategori i databasen.
     * @param category Kategorien med evt nye egenskaper.
     */
    public static void editCategory(Category category){
        try {
            PreparedStatement categoryResult = conn.prepareStatement("UPDATE category SET " +
                    "category_name = ?" +
                    "WHERE category_id = " + category.getCategoryId());
            categoryResult.setString(1, category.getCategoryName());

            categoryResult.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
