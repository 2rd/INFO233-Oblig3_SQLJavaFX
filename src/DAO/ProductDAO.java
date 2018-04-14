package DAO;

import Entities.Product;
import GUI.Main;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasse for å aksessere Produkt-tabellen i databasen.
 */
public class ProductDAO {
    private static Connection conn = Main.connextion;

    /**
     * Legger til et produkt i databasen.
     * @param product produktet som skal legges til.
     */
    public static void addProduct(Product product) {

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
        }
    }

    /**
     * Hent et produkt fra databasen.
     * @param id id'en til produktet som skal hentes
     * @return Produktet som skal hentes.
     */
    public static Product getProductById(int id){

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

        return product;
    }

    /**
     * Hent alle produktene i tabellen.
     * @return en liste med alle produktene i databasen.
     */
    public static List<Product> getAllProducts() {
        List<Product> products = new LinkedList<Product>();

        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM product");
            while (rs.next()) {
                Product currProd = new Product();

                currProd.setProductId(rs.getInt("product_id"));
                currProd.setProductName(rs.getString("product_name"));
                currProd.setDescription(rs.getString("description"));
                currProd.setPrice(rs.getFloat("price"));
                currProd.setCategory(rs.getInt("category"));

                products.add(currProd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    /**
     * Endre en eksisterende produkt i databasen.
     * @param product Produktet med evt nye egenskaper.
     */
    public static void editProduct(Product product){

        try {

            PreparedStatement productResult = conn.prepareStatement("UPDATE product SET " +
                    "product_name = ?," +
                    " description = ?," +
                    " price = ?," +
                    " category = ?" +
                    "WHERE product_id = " + product.getProductId());
            productResult.setString(1, product.getProductName());
            productResult.setString(2, product.getDescription());
            productResult.setFloat(3, product.getPrice());
            productResult.setInt(4, product.getCategory());

            productResult.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
