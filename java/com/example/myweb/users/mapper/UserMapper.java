package com.example.myweb.users.mapper;

import org.apache.ibatis.annotations.*;

import com.example.myweb.users.UserDTO;

import java.util.List;

public interface UserMapper {
    
    @Select("SELECT * FROM users WHERE id = #{id}")
    UserDTO getUserById(int id);

    @Select("SELECT * FROM users")
    List<UserDTO> getAllUsers();

    @Insert("INSERT INTO users(name, email) VALUES(#{name}, #{email})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void addUser(UserDTO user);

    @Update("UPDATE users SET name = #{name}, email = #{email} WHERE id = #{id}")
    void updateUser(UserDTO user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(int id);
}
