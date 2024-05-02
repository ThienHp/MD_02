package rikkei.academy.business.designImpl;

import rikkei.academy.business.design.IUserService;
import rikkei.academy.business.model.User;
import rikkei.academy.business.model.UserRoles;
import rikkei.academy.business.until.IOFile;
import rikkei.academy.business.until.InputMethods;
import rikkei.academy.business.until.UserValidator;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class UserService implements IUserService {
    public static List<User> userList;
    public UserService() {
        //đọc từ file
        userList = IOFile.readFromFile(IOFile.USERS_PATH);

    }

    @Override
    public List<User> findAll() {
        for (User user : userList) {
            System.out.println(user.toString());
        }
        return userList;
    }
    @Override
    public User findById(Integer id) {
        return (User) userList.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }
    @Override
    public void save(User user) {
        if (findById(user.getId()) != null) {
            //cập nhật
            userList.set(userList.indexOf(findById(user.getId())), user);
        } else {
            //thêm mới
            user.setId(getNewId());
            userList.add(user);

        }
        //luu lai
        IOFile.writeToFile(IOFile.USERS_PATH, userList);
    }
    public int getNewId(){
        int idMax=0;
        for (User user : userList) {
            if (user.getId()>idMax){
                idMax= user.getId();
            }
        }
        idMax+=1;
        return idMax;
    }
    @Override
    public void deleteById(Integer id) {
        userList.remove(findById(id));
        //luu lai
        IOFile.writeToFile(IOFile.USERS_PATH, userList);

    }
    @Override
    public void registerUser(User newUser) {
        // Thêm người dùng mới vào danh sách
        newUser.setId(getNewId());
        userList.add(newUser);
        System.out.println("Đăng ký thành công");
        // Lưu lại danh sách người dùng vào tệp tin
        IOFile.writeToFile(IOFile.USERS_PATH, userList);
    }


    public static User loginUser(String email, String password) {
        // Đảm bảo userList được khởi tạo
        if (userList == null) {
            System.err.println("người dùng không tồn tại.");
            return null;
        }
        // Kiểm tra đăng nhập
        for (User user : userList) {

            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                if (user.isStatus()) {
                    System.out.println("Đăng nhập thành công");
                    return user;
                } else {
                    System.out.println("Tài khoản của bạn đã bị khóa bởi admin.");
                    return null;
                }
            }
        }
        return null;
    }
    public void changeUserStatus() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập ID của người dùng cần thay đổi trạng thái:");
        int userId = scanner.nextInt();
        System.out.println("Nhập trạng thái mới (true = Đang hoạt động, false = Không hoạt động):");
        boolean newStatus = scanner.nextBoolean();

        User user = findById(userId);
        if (user != null) {
            user.setStatus(newStatus);
            System.out.println("Đã thay đổi trạng thái của người dùng có ID: " + userId);
            // Lưu lại danh sách người dùng vào tệp tin sau khi thay đổi trạng thái
            IOFile.writeToFile(IOFile.USERS_PATH, userList);
        } else {
            System.out.println("Không tìm thấy người dùng với ID đã nhập.");
        }
    }
    public void forgotPassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập email của bạn:");
        String email = InputMethods.getString();
        if (!UserValidator.validateEmail(email)) {
            System.out.println("Email không hợp lệ. Vui lòng nhập lại:");
            return;
        }

        User user = findUserByEmail(email);
        if (user != null) {
            System.out.println("Nhập mật khẩu mới:");
            String newPassword = InputMethods.getString();
            if (!UserValidator.validatePassword(newPassword)) {
                System.out.println("Mật khẩu không hợp lệ. Vui lòng nhập lại:");
                return;
            }
            user.setPassword(newPassword);
            System.out.println("Mật khẩu đã được cập nhật thành công.");
            // Lưu lại danh sách người dùng vào tệp tin sau khi thay đổi mật khẩu
            IOFile.writeToFile(IOFile.USERS_PATH, userList);
        } else {
            System.out.println("Không tìm thấy người dùng với email đã nhập.");
        }
    }

    public User findUserByEmail(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
