package com.example.myweb.users;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private List<UserDTO> users;

    public UserDAO() {
        users = new ArrayList<>();
    }

    // 사용자 추가
    public void addUser(UserDTO user) {
        users.add(user);
    }

    // 사용자 조회 (ID로)
    public UserDTO getUserById(int id) {
        for (UserDTO user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // 모든 사용자 조회
    public List<UserDTO> getAllUsers() {
        return new ArrayList<>(users);
    }

    // 사용자 업데이트
    public void updateUser(UserDTO updatedUser) {
        for (UserDTO user : users) {
            if (user.getId() == updatedUser.getId()) {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                break;
            }
        }
    }

    // 사용자 삭제
    public void deleteUser(int id) {
        users.removeIf(user -> user.getId() == id);
    }
}
