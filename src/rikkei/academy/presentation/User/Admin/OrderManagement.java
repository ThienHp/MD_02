package rikkei.academy.presentation.User.Admin;

import rikkei.academy.business.design.ReceiptStatus;
import rikkei.academy.business.model.Order;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static rikkei.academy.presentation.run.Main.*;

public class OrderManagement {
    static ReceiptStatus status = ReceiptStatus.PENDING;
    static String orderId;

    public void orderManagement() {

        while (true) {
            System.out.println(ANSI_BLUE + "╔══════════════════════════════════════════════════════╗" + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "  Quản lý đơn hàng " + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╠══════════════════════════════════════════════════════╣" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 1. Hiển thị tất cả đơn hàng                     ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 2. Hiển thị đơn hàng đang chờ xử lý (PENDING)    ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 3. Hiển thị đơn hàng đã chấp nhận (ACCEPTED)     ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 4. Hiển thị đơn hàng đang giao (SHIPPING)        ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 5. Hiển thị đơn hàng đã hoàn thành (DONE)         ║" + ANSI_RESET);
            System.out.println(ANSI_YELLOW + "║ 6. Cập nhật trạng thái đơn hàng                  ║" + ANSI_RESET);
            System.out.println(ANSI_RED + "║ 0. Thoát                                         ║" + ANSI_RESET);
            System.out.println(ANSI_BLUE + "╚══════════════════════════════════════════════════════╝" + ANSI_RESET);
            int choice = InputMethods.getInteger();
            switch (choice) {
                case 1:
                    displayAllOrders();
                    break;
                case 2:
                    displayOrdersByStatuses(Collections.singletonList(ReceiptStatus.PENDING));
                    break;
                case 3:
                    displayOrdersByStatuses(Collections.singletonList(ReceiptStatus.ACCEPTED));
                    break;
                case 4:
                    displayOrdersByStatuses(Collections.singletonList(ReceiptStatus.SHIPPING));
                    break;
                case 5:
                    displayOrdersByStatuses(Collections.singletonList(ReceiptStatus.DONE));
                    break;
                case 6:
                    updateOrderStatus();
                    break;
                case 0:
                    System.out.println("Thoát thành công");
                    return;
                default:
                    System.out.println("vui long chon lai");
            }
        }

    }

    public void displayAllOrders() {
        // Đọc danh sách đơn hàng từ file
        List<Order> orders = IOFile.readFromFile(IOFile.ORDER_PATH);
        // In ra tất cả đơn hàng
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    // Hiển thị đơn hàng theo trạng thái
    public void displayOrdersByStatuses(List<ReceiptStatus> statuses) {
        // Đọc danh sách đơn hàng từ file
        List<Order> orders = IOFile.readFromFile(IOFile.ORDER_PATH);
        // Lọc và in ra đơn hàng theo trạng thái
        for (Order order : orders) {
            if (statuses.contains(order.getStatus())) {
                System.out.println(order);
            }
        }
    }

    // Hiển thị chi tiết đơn hàng
    public void displayOrderDetails(String orderId) {
        // Đọc danh sách đơn hàng từ file
        List<Order> orders = IOFile.readFromFile(IOFile.ORDER_PATH);
        // Tìm và in ra chi tiết đơn hàng
        for (Order order : orders) {
            if (order.getUserId().equals(orderId)) {
                System.out.println(order);
                break;
            }
        }
    }

    public void updateOrderStatus() {
        String orderId = getOrderIdFromUser();
        ReceiptStatus newStatus = getStatusFromUser();
        // Đọc danh sách đơn hàng từ file
        List<Order> orders = IOFile.readFromFile(IOFile.ORDER_PATH);
        // Tìm đơn hàng và cập nhật trạng thái
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                order.setStatus(newStatus);
                break;
            }
        }
        // Ghi danh sách đơn hàng đã cập nhật vào file
        IOFile.writeToFile(IOFile.ORDER_PATH, orders);
    }

    public String getOrderIdFromUser() {
        System.out.println("Nhập ID đơn hàng cần chỉnh sửa: ");
        String orderId = InputMethods.getString();
        return orderId;
    }

    public ReceiptStatus getStatusFromUser() {
        System.out.println("Chọn trạng thái mới cho đơn hàng: ");
        System.out.println("1. PENDING");
        System.out.println("2. ACCEPTED");
        System.out.println("3. REJECTED");
        System.out.println("4. SHIPPING");
        System.out.println("5. DONE");
        int choice = InputMethods.getInteger();
        switch (choice) {
            case 1:
                return ReceiptStatus.PENDING;
            case 2:
                return ReceiptStatus.ACCEPTED;
            case 3:
                return ReceiptStatus.REJECTED;
            case 4:
                return ReceiptStatus.SHIPPING;
            case 5:
                return ReceiptStatus.DONE;
            default:
                System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                return getStatusFromUser();
        }
    }
}


