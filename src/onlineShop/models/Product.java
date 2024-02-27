package onlineShop.models;

import onlineShop.models.enums.ProductType;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {
    private String id;
    private String name;
    private String description;
    private double price;
    private int stockQty;
    private ProductType type;
    private boolean isRemoved = false;

    public Product(String id, String name, String description, double price, int stockQty, ProductType type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQty = stockQty;
        this.type = type;
    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(price, product.price) == 0 && stockQty == product.stockQty && isRemoved == product.isRemoved && Objects.equals(id, product.id) && Objects.equals(name, product.name) && Objects.equals(description, product.description) && type == product.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, stockQty, type, isRemoved);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stockQty=" + stockQty +
                ", type=" + type +
                '}';
    }


}
