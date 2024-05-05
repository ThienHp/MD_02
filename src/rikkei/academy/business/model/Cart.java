package rikkei.academy.business.model;


import rikkei.academy.business.design.ReceiptStatus;
import rikkei.academy.business.designImpl.CartService;
import rikkei.academy.business.designImpl.ProductService;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart implements Serializable {
    private int id;
    private String userId;
    private Double total;
    private List<CartItem> cartItems;


    public Cart(int id, Double total, List<CartItem> cartItems, String userId,User user) {
        this.id = id;
        this.userId = userId;
        this.total = total != null ? total : 0f;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public static void addProductToCart(User user) {
        String userLogin = IOFile.readDataLogin(IOFile.USERLOGIN_PATH).getId();
        ProductService productService = new ProductService();
        System.out.println("Nhập id sản phẩm muốn thêm: ");
        String id = InputMethods.getString();
        // Tìm sản phẩm dựa trên id
        Product product = productService.findProductById(id);
        if (product == null) {
            System.out.println("Không tìm thấy sản phẩm");
            return;
        }
        System.out.println("Nhập số lượng: ");
        int quantity = InputMethods.getInteger();
        // Kiểm tra số lượng
        while (quantity <= 0) {
            System.out.println("Số lượng không hợp lệ. Vui lòng nhập lại: ");
            quantity = InputMethods.getInteger();
        }
        //kiểm tra số lượng tồn kho
        while (!productService.checkQuantity(id, quantity)) {
            Product products = new Product();
            System.out.println("Số lượng không đủ. Số lượng sản phẩm còn lại trong kho là: " + product.getQuantity());
            System.out.println("Vui lòng nhập lại: ");
            quantity = InputMethods.getInteger();
        }
        // Lấy giỏ hàng của người dùng đã đăng nhập
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUserId(user.getId());
            user.setCart(cart);
        }
        // Kiểm tra xem sản phẩm đã tồn tại trong giỏ hàng chưa
        boolean found = false;
        for (CartItem cartItem : cart.getCartItems()) {
            if (cartItem.getProduct().getProductId().equals(product.getProductId())) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
                found = true;
                break;
            }
        }
        // Nếu sản phẩm chưa tồn tại trong giỏ hàng, thêm một CartItem mới
        if (!found) {
            CartItem newCartItem = new CartItem(product, quantity);
            cart.getCartItems().add(newCartItem);
        }
        // Tính tổng tiền
        cart.setTotal(cart.getTotal() + product.getPrice() * quantity);
        String userCartPath = "src/rikkei/academy/business/data/CartUser/" + userLogin  + "CartUser.txt";
        System.out.println( "dddd"+userLogin);


        // Ghi giỏ hàng vào file mới
        IOFile.writeToFile(userCartPath, Collections.singletonList(cart));
        System.out.println("Thêm sản phẩm vào giỏ hàng thành công");
    }

}


