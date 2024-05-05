package rikkei.academy.business.design;

import rikkei.academy.business.model.Cart;
import rikkei.academy.business.model.Product;
import rikkei.academy.business.model.User;

public interface ICartService {
    void addProductToCart(Product product, int quantity);
    void removeProductFromCart( Product product);
    void displayCart();
    void checkOut();
    void displayOrderHistory();
}
