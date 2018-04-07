package DAO;

import Entities.Product;

import java.sql.*;

public class ProductDAO {
    private static ConnectionDAO connection = new ConnectionDAO();

    public void addProduct(Product product) {
        Connection conn = connection.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);
            PreparedStatement newProduct = conn.prepareStatement("INSERT INTO product (product_id, product_name, description, price, category) VALUES (?,?,?,?,?)");
            newProduct.setInt(1, product.getProductId());
            newProduct.setString(2, product.getProductName());
            newProduct.setString(3, product.getDescription());
            newProduct.setFloat(4, product.getPrice());
            newProduct.setInt(5, product.getCategory());
            newProduct.execute();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            connection.closeConnection();
        }
    }

    public static Product getProductById(int id){
        Connection conn = connection.getConnection();
        Product product = new Product();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            ResultSet prodResult = statement.executeQuery("SELECT * FROM product WHERE product_id = " + id);

            product.setProductId(prodResult.getInt("product_id"));
            product.setProductName(prodResult.getString("product_name"));
            product.setDescription(prodResult.getString("description"));
            product.setPrice(prodResult.getFloat("price"));
            product.setCategory(prodResult.getInt("category"));

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeConnection();
        }
        return product;
    }
    public static void removeProduct(int id){
        Connection conn = connection.getConnection();
        try {
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(30);

            statement.executeQuery("DELETE * FROM product WHERE product_id = " + id);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            connection.closeConnection();
        }
    }
}
