package rikkei.academy.business.designImpl;

import rikkei.academy.business.design.ReceiptStatus;
import rikkei.academy.business.model.*;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;
import rikkei.academy.business.until.UserValidator;


import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CartService {
    private static List<Cart> cart = new ArrayList<>();

    public CartService() {
        cart = IOFile.readFromFile(IOFile.CART_PATH);
    }


    public static void viewCart(User user) {
        String userLogin = IOFile.readDataLogin(IOFile.USERLOGIN_PATH).getId();
        DecimalFormat df = new DecimalFormat("##,###,###");
        Cart cart;


        // Tạo một đường dẫn file mới dựa trên ID người dùng
        String userCartPath = IOFile.CARTUSER_PATH + userLogin + "CartUser.txt";

        // Đọc giỏ hàng từ file nếu file tồn tại
        List<Cart> carts = IOFile.readFromFile(userCartPath);
        if (!carts.isEmpty()) {
            cart = carts.get(0);
            user.setCart(cart);
        } else {
            System.out.println("Giỏ hàng trống");
            return;
        }
        System.out.println("Danh sách sản phẩm trong giỏ hàng: ");
        for (CartItem cartItem : cart.getCartItems()) {
            int index = cart.getCartItems().indexOf(cartItem);

            double total = cartItem.getProduct().getPrice() * cartItem.getQuantity();
            System.out.println((index + 1) + ". " + cartItem.getProduct().getProductName() + " -  Số lượng :" + cartItem.getQuantity() + " -  Giá : " + df.format(cartItem.getProduct().getPrice()) + "đ - thành tiền " + df.format(total));
        }
        System.out.println("Tổng tiền: " + df.format(cart.getTotal()) + "đ");
        while (true){
            System.out.println("1. Thanh toán");
            System.out.println("2. Thay đổi số lượng sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("0. Quay lại");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    checkout(user);
                    return;
                case 2:
                    changeProductQuantity(user);
                    break;
                case 3:
                    removeProduct(user);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Vui lòng chọn lai");
                    break;
            }
        }
    }

    public static void checkout(User user) {
        String userLogin = IOFile.readDataLogin(IOFile.USERLOGIN_PATH).getId();
        Cart cart = user.getCart();
        if (cart == null || cart.getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng trống, không thể thanh toán");
            return;
        }

        // Yêu cầu người dùng nhập địa chỉ và số điện thoại
        System.out.println("Nhập địa chỉ: ");
        String address = InputMethods.getString();
        System.out.println("Nhập số điện thoại: ");
        String phoneNumber = InputMethods.getString(); // Sử dụng phương thức lấy chuỗi từ người dùng
        while (!UserValidator.validatePhoneNumber(phoneNumber)) {
            System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại: ");
            phoneNumber = InputMethods.getString();
        }
        ProductService productService = new ProductService();
        for (CartItem cartItem : cart.getCartItems()) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            // Trừ số lượng sản phẩm đã mua từ kho
            productService.reduceQuantity(product.getProductId(), quantity);
        }
        // Tạo đơn hàng mới
        Order order = new Order(userLogin, address, phoneNumber, new ArrayList<>(cart.getCartItems()));
        // Gán thời gian thanh toán là thời gian hiện tại
        order.setPaymentTime(LocalDateTime.now());
        // Đọc danh sách đơn hàng hiện tại từ file và tạo một danh sách mới có thể thay đổi
        List<Order> orders = new ArrayList<>(IOFile.readFromFile(IOFile.ORDER_PATH));

        // Thêm đơn hàng mới vào danh sách
        orders.add(order);
        // Ghi danh sách đơn hàng đã cập nhật vào file
        IOFile.writeToFile(IOFile.ORDER_PATH, orders);
        // Xóa giỏ hàng sau khi thanh toán
        cart.getCartItems().clear();
        cart.setTotal(0.0);
        System.out.println("Đã tạo đơn thành thành công!");

        // Cập nhật giỏ hàng trong file
        String userCartPath = IOFile.CARTUSER_PATH + userLogin + "CartUser.txt";
        IOFile.writeToFile(userCartPath, Collections.singletonList(cart));
    }
    public static void removeProduct(User user) {
        String userLogin = IOFile.readDataLogin(IOFile.USERLOGIN_PATH).getId();

        Cart cart = user.getCart();
        if (cart == null || cart.getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng trống, không có sản phẩm để xóa");
            return;
        }
        System.out.println("Nhập ID sản phẩm cần xóa: ");
        String productId = InputMethods.getString();

        CartItem itemToRemove = null;
        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().getProductId().equals(productId)) {
                itemToRemove = item;
                break;
            }
        }
        if (itemToRemove != null) {
            cart.getCartItems().remove(itemToRemove);
            System.out.println("Đã xóa sản phẩm khỏi giỏ hàng");
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID: " + productId);
        }

        // Cập nhật giỏ hàng trong file
        String userCartPath = IOFile.CARTUSER_PATH+ userLogin + "CartUser.txt";
        IOFile.writeToFile(userCartPath, Collections.singletonList(cart));
    }
    public static void displayOrdersByUser() {
        String userName = IOFile.readDataLogin(IOFile.USERLOGIN_PATH).getName();
        // Đọc userLogin từ file
        String userLogin = IOFile.readDataLogin(IOFile.USERLOGIN_PATH).getId();

        // Đọc danh sách đơn hàng từ file
        List<Order> orders = IOFile.readFromFile(IOFile.ORDER_PATH);
        // Lọc ra những đơn hàng của userLogin
        List<Order> userOrders = orders.stream()
                .filter(order -> order.getUserId().equals(userLogin))
                .collect(Collectors.toList());

        // Hiển thị đơn hàng của userLogin
        if (userOrders.isEmpty()) {
            System.out.println("Không có đơn hàng nào của người dùng: " +userName);
        } else {
            System.out.println("Đơn hàng của người dùng: " + userName);
            for (Order order : userOrders) {
                System.out.println(order);
            }
        }
        while (true){
            System.out.println("1. Chỉnh sửa thông tin đơn hàng");
            System.out.println("2. Hủy đơn hàng");
            System.out.println("0. Quay lại");
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    editOrderDetails();
                    break;
                 case 2:
                    System.out.println("Nhập ID đơn hàng cần hủy: ");
                    String orderId = InputMethods.getString();
                    cancelOrder(orderId);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Vui lòng chọn lại");
                    break;
            }
        }


    }
    public static void editOrderDetails() {
        String orderId = getOrderIdFromUser();
        // Đọc danh sách đơn hàng từ file
        List<Order> orders = IOFile.readFromFile(IOFile.ORDER_PATH);
        // Tìm đơn hàng và kiểm tra trạng thái
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                if (order.getStatus() == ReceiptStatus.PENDING) {
                    // Thực hiện chỉnh sửa đơn hàng ở đây
                    String newAddress = getNewAddressFromUser();
                    String newPhoneNumber = getNewPhoneNumberFromUser();

                    order.setAddress(newAddress);
                    order.setPhoneNumber(newPhoneNumber);

                    // Ghi danh sách đơn hàng đã cập nhật vào file
                    IOFile.writeToFile(IOFile.ORDER_PATH, orders);
                    System.out.println("Chỉnh sửa thành thành công!");
                } else {
                    System.out.println("Chỉ có thể chỉnh sửa đơn hàng khi đơn hàng đang ở trạng thái PENDING.");
                }
                break;
            }
        }
    }
    public static String getOrderIdFromUser() {
        System.out.println("Nhập ID đơn hàng: ");
        String orderId = InputMethods.getString();
        return orderId;
    }

    public static String getNewAddressFromUser() {
        System.out.println("Nhập địa chỉ mới: ");
        String newAddress = InputMethods.getString();
        return newAddress;
    }

    public static String getNewPhoneNumberFromUser() {
        System.out.println("Nhập số điện thoại mới: ");
        String newPhoneNumber = InputMethods.getString();
        return newPhoneNumber;
    }




    public static void changeProductQuantity(User user) {
        String userLogin = IOFile.readDataLogin(IOFile.USERLOGIN_PATH).getId();

        Cart cart = user.getCart();
        if (cart == null || cart.getCartItems().isEmpty()) {
            System.out.println("Giỏ hàng trống, không có sản phẩm để thay đổi số lượng");
            return;
        }

        System.out.println("Nhập ID sản phẩm cần thay đổi số lượng: ");
        String productId = InputMethods.getString();

        CartItem itemToChange = null;
        for (CartItem item : cart.getCartItems()) {
            if (item.getProduct().getProductId().equals(productId)) {
                itemToChange = item;
                break;
            }
        }

        if (itemToChange != null) {
            System.out.println("Nhập số lượng mới: ");
            int newQuantity = InputMethods.getInteger();
            itemToChange.setQuantity(newQuantity);
            System.out.println("Đã thay đổi số lượng sản phẩm trong giỏ hàng");
        } else {
            System.out.println("Không tìm thấy sản phẩm với ID: " + productId);
        }

        // Cập nhật giỏ hàng trong file
        String userCartPath = "src/rikkei/academy/business/data/CartUser/" + userLogin + "CartUser.txt";
        IOFile.writeToFile(userCartPath, Collections.singletonList(cart));
    }
    public static void cancelOrder(String orderId) {
        // Đọc danh sách đơn hàng từ file
        List<Order> orders = IOFile.readFromFile(IOFile.ORDER_PATH);
        // Tìm đơn hàng và kiểm tra trạng thái
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                if (order.getStatus() == ReceiptStatus.PENDING) {
                    // Hủy đơn hàng
                    orders.remove(order);
                    System.out.println("Đơn hàng đã được hủy thành công.");
                } else {
                    System.out.println("Chỉ có thể hủy đơn hàng đang ở trạng thái PENDING.");
                }
                break;
            }
        }
        // Ghi danh sách đơn hàng đã cập nhật vào file
        IOFile.writeToFile(IOFile.ORDER_PATH, orders);
    }
}


