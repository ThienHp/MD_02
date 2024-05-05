package rikkei.academy.business.model;

import rikkei.academy.business.until.InputMethods;
import rikkei.academy.business.until.UserValidator;

import java.io.Serializable;
import java.time.LocalDate;

import static rikkei.academy.business.model.UserRoles.USER;

public class User implements Serializable {
    private String id;
    private String name;
    private String email;
    private String password;
    private UserRoles roles = USER;
    private boolean status = true;
    private Cart cart;
    private String invoiceNumber;



    public User() {
        this.cart = new Cart();
    }

    public User(String id, String name, String email, String password) {
        this(id, name, email, password, UserRoles.ADMIN, true, new Cart());
    }

    public User(String id, String name, String email, String password, UserRoles roles, boolean status, Cart cart) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles=roles;
        this.status = status;
        this.cart = cart != null ? cart : new Cart();
        this.invoiceNumber = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles getRoles() {
        return roles;
    }

    public void setRoles(UserRoles roles) {
        this.roles = roles;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void inputUser(boolean isCreate) {
        System.out.println("=== Đăng ký tài khoản mới ===");
        String name, email,inputPassword;
        while (true) {
            System.out.print("Nhập tên đăng nhập: ");
            String inputUsername = InputMethods.getString();
            if (UserValidator.validateUsername(inputUsername)) {
                this.name = inputUsername;
                break;
            } else {
                System.err.println("Tên tài khoản phải có ít nhất 4 ký tự và ít nhất 1 chữ in hoa.");
            }
        }

        do {
            System.out.print("Nhập email: ");
            email = InputMethods.getString();
            if (!UserValidator.validateEmail(email)) {
                System.out.println("Email không hợp lệ.");
            } else if (UserValidator.isEmailUnique(email)) {
                this.email=email;
                break;
            }else {
                System.out.println("Lỗi: Email đăng ký đã tồn tại. Vui lòng chọn lại.");
            }
        } while (!UserValidator.isEmailUnique(email) || (!UserValidator.validateEmail(email)));

        while (true) {
            System.out.print("Nhập mật khẩu: ");
            inputPassword = InputMethods.getString();

            if (UserValidator.validatePassword(inputPassword)) {
                this.password = inputPassword;
                break;
            } else {
                System.out.println("Mật khẩu phải có ít nhất 4 ký tự.");
            }
        }



    }


    @Override
    public String toString() {
        String statusString = status ? "Đang hoạt động" : "Không hoạt động";
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + statusString +
                "} ";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;

        return false;
    }


}
