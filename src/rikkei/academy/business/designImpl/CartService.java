package rikkei.academy.business.designImpl;

import rikkei.academy.business.design.ICartService;
import rikkei.academy.business.design.IGenericServive;
import rikkei.academy.business.model.Cart;
import rikkei.academy.business.model.Product;
import rikkei.academy.business.model.User;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;

import java.util.ArrayList;
import java.util.List;

public class CartService implements ICartService {
    private static List<Cart> cart = new ArrayList<>();
    public CartService(){
        cart= IOFile.readFromFile(IOFile.CART_PATH);
    }


    //k
    @Override
    public void addProductToCart(Cart carts) {
        // Thêm sản phẩm vào giỏ hàng
        cart.add(carts);
        // Lưu lại danh sách giỏ hàng
        IOFile.writeToFile(IOFile.CART_PATH, carts);
    }

    @Override
    public void removeProductFromCart(Product product) {
        // Implement logic to remove a product from the cart
    }

    @Override
    public void displayCart() {
        // Implement logic to display the cart
    }

    @Override
    public void checkOut() {
        // Implement logic to checkout
    }

    @Override
    public void displayOrderHistory() {
        // Implement logic to display order history
    }


}