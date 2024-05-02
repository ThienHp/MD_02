package rikkei.academy.presentation.User;

import rikkei.academy.business.designImpl.CartService;
import rikkei.academy.business.designImpl.ProductService;
import rikkei.academy.business.model.Cart;
import rikkei.academy.business.model.CartItem;
import rikkei.academy.business.model.Product;
import rikkei.academy.business.model.User;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import static rikkei.academy.presentation.run.Main.*;

public class ProductControl {
            static ProductService productService = new ProductService();
            static Cart cart = new Cart();
    static CartItem cartItem = new CartItem();
            static User user = new User();
        public static  void userMenu(){

            String name = IOFile.readDataLogin(IOFile.USERLOGIN_PATH).getName();
            while (true) {
                System.out.println(ANSI_BRIGHT_BLACK + "╔═════════════════════════════════════════╗" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║" + ANSI_PURPLE + "Chào mừng " + name + " tới với của hàng" + ANSI_BRIGHT_BLACK + "                             ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "Lựa chọn của bạn:" + ANSI_BRIGHT_BLACK + "                                                 ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "1. Hiện thị tất cả các sản phẩm trong của hàng" + ANSI_BRIGHT_BLACK + "     ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "2. Tìm kiếm sản phẩm " + ANSI_BRIGHT_BLACK + "   ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "3. Hiện thị giỏ hàng của bạn" + ANSI_BRIGHT_BLACK + "                                  ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "4. Hiện thị thông tin cái nhân" + ANSI_BRIGHT_BLACK + "                                ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "5. Thay đổi mật khẩu" + ANSI_BRIGHT_BLACK + "                                             ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "6. Chỉnh sửa thông tin cá nhân" + ANSI_BRIGHT_BLACK + "                             ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "7. Lịch sử mua hàng " + ANSI_BRIGHT_BLACK + "                                             ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_RED + "0. Đăng xuất  " + ANSI_BRIGHT_BLACK + "                                                       ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╚═════════════════════════════════════════╝" + ANSI_RESET);
                System.out.print("Nhập số cần chọn tại đây:  ");
                int choice = InputMethods.getInteger();
                switch (choice) {
                    case 1:
                        productService.findAll();
                        System.out.println("Nhap id");
                        String productId = InputMethods.getString();
                        System.out.println("nhap so luong");
                        int quantity = InputMethods.getInteger();
                        cart.addProductToCart(productId, quantity, productService);
                        break;
                    case 2:
                        System.out.println("Nhập tên sản phẩm: ");
                        String nameProduct = InputMethods.getString();
                        Product product = productService.findByName(nameProduct);
                        if (product != null) {
                            System.out.println("Sản phẩm tìm thấy: " + product);
                        } else {
                            System.out.println("Không tìm thấy sản phẩm với tên đã cho.");
                        }
                        break;
                    case 3:
                        cart.displayCartItems();

                    case 0:
                        try {
                            PrintWriter writer = new PrintWriter(IOFile.USERLOGIN_PATH);
                            writer.print("");
                            writer.close();
                        } catch (FileNotFoundException e) {
                            System.out.println("Không tìm thấy file: " + IOFile.USERLOGIN_PATH);
                        }
                        return;
                    default:
                        System.out.println("vui long chon lai");

                }
            }
        }



}
