package rikkei.academy.business.model;

import java.io.Serializable;

public class CartItem implements Serializable {
    private int cartItemId;
    private Product product;
    private double price;
    private int quantity;
    private boolean status;

    public CartItem(  int cartItemId, Product product, double price, int quantity,boolean status) {

        this.cartItemId = cartItemId;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }

    public CartItem(Product product, int quantity) {
    }

    public CartItem() {

    }


    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s %s %s",
                "Mã sản phẩm: ", cartItemId,
                "Tên sản phẩm: ", product.getProductName(),
                "Giá: ", price,
                "Số lượng: ", quantity);
    }


}
