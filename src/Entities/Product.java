package Entities;

/**
 * Klasse for entiteten product, med alle dens attributter, setters og getters.
 */
public class Product {
    public int getProductId() { return productId; }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    private int productId;
    private String productName;
    private String description;
    private float price;
    private int category;

}
