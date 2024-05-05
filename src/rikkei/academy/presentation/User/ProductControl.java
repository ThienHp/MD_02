package rikkei.academy.presentation.User;

import rikkei.academy.business.designImpl.CartService;
import rikkei.academy.business.designImpl.ProductService;
import rikkei.academy.business.model.Cart;
import rikkei.academy.business.model.CartItem;
import rikkei.academy.business.model.Product;
import rikkei.academy.business.model.User;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;
import rikkei.academy.presentation.run.Main;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.List;

import static rikkei.academy.presentation.run.Main.*;

public class ProductControl {
            static ProductService productService = new ProductService();
            static Cart cart = new Cart();
            static CartService cartService = new CartService();
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
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "2. Tìm kiếm sản phẩm " + ANSI_BRIGHT_BLACK + "                                          ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "3. Hiện thị giỏ hàng của bạn" + ANSI_BRIGHT_BLACK + "                                  ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "4. Hiện thị thông tin cá nhân" + ANSI_BRIGHT_BLACK + "                                ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "5. Chỉnh sửa thông tin cá nhân" + ANSI_BRIGHT_BLACK + "                             ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_YELLOW + "6. Lịch sử mua hàng " + ANSI_BRIGHT_BLACK + "                                             ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╠═════════════════════════════════════════╣" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "║     " + ANSI_RED + "0. Đăng xuất  " + ANSI_BRIGHT_BLACK + "                                                       ║" + ANSI_RESET);
                System.out.println(ANSI_BRIGHT_BLACK + "╚═════════════════════════════════════════╝" + ANSI_RESET);
                System.out.print("Nhập số cần chọn tại đây:  ");
                int choice = InputMethods.getInteger();
                switch (choice) {
                    case 1:
                        addCart(user);
                        break;
                    case 2:
                        searchProductName();
                        break;
                    case 3:
                        cartService.viewCart(user);
                        break;
                     case 4:
                         displayUserInfo();
                            break;
                    case 5:
                        updateUserInfo();
                        break;
                    case 6:
                        System.out.println("Lịch sử mua hàng");
                        cartService.displayOrdersByUser();
                        break;
                    case 0:
                        IOFile.deleteFile();
                        System.out.println("Đăng xuất thành công");
                        Main.phone();
                        return;
                    default:
                        System.out.println("vui long chon lai");

                }
            }
        }
        public static void addCart(User user) {
            productService.findAll();
            while (true){
                System.out.println("Lựa chọn");
                System.out.println("1. Thêm sản phẩm vào giỏ hàng");
                System.out.println("2. Danh sách sản phẩm theo danh mục");
                System.out.println("0. Thoát");
                int choice = InputMethods.getInteger();
                switch (choice){
                    case 1:
                        cart.addProductToCart(user);
                        break;
                    case 2:
                        displayProductsByCategory();
                    case 0:
                        return;
                    default:
                        System.out.println("Vui lòng chọn lại");
                }
            }


        }
    public static void displayProductsByCategory() {
        System.out.println("Nhập tên danh mục: ");
        String categoryName = InputMethods.getString();
        List<Product> productsByCategory = productService.getProductsByCategory(categoryName);
        if (!productsByCategory.isEmpty()) {
            System.out.println("Sản phẩm trong danh mục " + categoryName + ": ");
            for (Product product : productsByCategory) {
                System.out.println(product);
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm trong danh mục " + categoryName);
        }
    }
    public static void searchProductName() {
        System.out.println("Nhập tên sản phẩm: ");
        String productName = InputMethods.getString();
        List<Product> products = productService.findByName(productName);
        if (!products.isEmpty()) {
            System.out.println("Sản phẩm tìm thấy: ");
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("Không tìm thấy sản phẩm với tên đã cho.");
        }
    }
    public static void displayUserInfo() {
        // Đọc thông tin người dùng từ file
        User user = IOFile.readDataLogin(IOFile.USERLOGIN_PATH);

        // Hiển thị thông tin người dùng
        System.out.println("Thông tin người dùng: ");
        System.out.println("ID: " + user.getId());
        System.out.println("Tên: " + user.getName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());

    }
    public static void updateUserInfo() {
        // Đọc thông tin người dùng từ file
        User currentUser = IOFile.readDataLogin(IOFile.USERLOGIN_PATH);

        // Đọc danh sách người dùng từ file
        List<User> users = IOFile.readFromFile(IOFile.USERS_PATH);

        // Tìm người dùng trong danh sách
        User userToUpdate = users.stream()
                .filter(user -> user.getId().equals(currentUser.getId()))
                .findFirst()
                .orElse(null);

        if (userToUpdate == null) {
            System.out.println("Không tìm thấy người dùng trong danh sách.");
            return;
        }

        while (true) {
            System.out.println("Chọn thông tin cần thay đổi: ");
            System.out.println("1. Tên");
            System.out.println("2. Email");
            System.out.println("3. Mật khẩu");
            System.out.println("0. Thoát");
            int choice = InputMethods.getInteger();

            switch (choice) {
                case 1:
                    System.out.println("Nhập tên mới: ");
                    String newName = InputMethods.getString();
                    userToUpdate.setName(newName);
                    break;
                case 2:
                    System.out.println("Nhập email mới: ");
                    String newEmail = InputMethods.getString();
                    userToUpdate.setEmail(newEmail);
                    break;
                case 3:
                    System.out.println("Nhập mật khẩu mới: ");
                    String newPassword = InputMethods.getString();
                    userToUpdate.setPassword(newPassword);
                    break;
                case 0:
                    // Lưu thông tin người dùng đã cập nhật vào file
                    IOFile.writeToFile(IOFile.USERLOGIN_PATH, userToUpdate);
                    IOFile.writeToFile(IOFile.USERS_PATH, users);
                    System.out.println("Thông tin người dùng đã được cập nhật thành công.");
                    return;
                default:
                    System.out.println("Vui lòng chọn lại");
            }
        }
    }
}
