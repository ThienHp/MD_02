package rikkei.academy.business.model;


import rikkei.academy.business.designImpl.ProductService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private int id;

    private int userId;

    private Double total;

    private List<CartItem> cartItems;

    public Cart(int id, Double total, List<CartItem> cartItems, int userId) {
        this.id = id;
        this.userId = userId;
        this.total = total != null ? total : 0.0;
        this.cartItems = cartItems != null ? cartItems : new ArrayList<>();
    }


    public Cart() {
        this.cartItems = new ArrayList<>();
        this.total = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        updateTotal();
    }
    private void updateTotal() {
        total = 0.0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getPrice() * cartItem.getQuantity();
        }
    }
    public void addProductToCart(String productId, int quantity, ProductService productService) {
        Product product = productService.findProductById(productId);
        if (product != null) {
            CartItem cartItem = new CartItem(product, quantity);
            addCartItem(cartItem);
            System.out.println("Thêm sản phẩm thành công");
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID " + productId);
        }
    }
    public void displayCartItems() {
        if (cartItems.isEmpty()) {
            System.out.println("Giỏ hàng của bạn đang trống.");
        } else {
            System.out.println("Sản phẩm trong giỏ hàng của bạn:");
            for (CartItem cartItem : cartItems) {
                System.out.println("ID sản phẩm: " + cartItem.getProduct().getProductId());
                System.out.println("Tên sản phẩm: " + cartItem.getProduct().getProductName());
                System.out.println("Số lượng: " + cartItem.getQuantity());
                System.out.println("Giá: " + cartItem.getProduct().getPrice());
                System.out.println("-------------------------");
            }
        }
    }

}
