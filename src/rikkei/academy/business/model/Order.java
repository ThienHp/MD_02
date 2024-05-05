package rikkei.academy.business.model;

import rikkei.academy.business.design.ReceiptStatus;
import rikkei.academy.business.until.IOFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order implements Serializable {
    private static int orderCount = 0;
    private String id;
    private String userId;
    private String address;
    private String phoneNumber;
    private List<CartItem> cartItems;
    private ReceiptStatus status;
    private LocalDateTime paymentTime;


    public Order(String userId, String address, String phoneNumber, List<CartItem> cartItems) {
        this.id = String.valueOf(++orderCount);;
        this.userId = userId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.cartItems = cartItems;
        this.status = ReceiptStatus.PENDING;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public ReceiptStatus getStatus() {
        return status;
    }

    public void setStatus(ReceiptStatus status) {
        this.status = status;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Override
    public String toString() {
        String userName = IOFile.readDataLogin(IOFile.USERLOGIN_PATH).getName();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDateTime = paymentTime.format(formatter);

        return "Order " +
                "id='" + id + '\'' +
                ", Name='" + userName + '\'' +
                " address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", information=" + cartItems +
                ", status=" + status +
                ", paymentTime=" + formattedDateTime
                +
                "\n ======================================";
    }
}
