package rikkei.academy.business.model;

import rikkei.academy.business.until.IOFile;

import java.io.Serializable;
import java.util.List;

public class CartItem implements Serializable {
    private Product product;
    private int quantity;
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return
                "quantity=" + quantity +
                ", product=" + product.getProductName();
    }

}
