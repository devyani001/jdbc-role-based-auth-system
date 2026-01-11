package com.auth.dao;

import com.auth.config.DBConfig;
import com.auth.model.User;

import java.sql.*;

public class UserDAO {

    public User findByUsername(String username) throws Exception {
        Connection con = DBConfig.getConnection();
        String sql = "SELECT * FROM users WHERE username = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, username);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setUsername(rs.getString("username"));
            u.setPasswordHash(rs.getString("password_hash"));
            u.setRole(rs.getString("role"));
            u.setFailedAttempts(rs.getInt("failed_attempts"));
            u.setAccountLocked(rs.getBoolean("account_locked"));
            return u;
        }
        return null;
    }

    public void updateFailedAttempts(int id, int attempts, boolean lock) throws Exception {
        Connection con = DBConfig.getConnection();
        String sql = "UPDATE users SET failed_attempts=?, account_locked=? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, attempts);
        ps.setBoolean(2, lock);
        ps.setInt(3, id);
        ps.executeUpdate();
    }

    public void resetAttempts(int id) throws Exception {
        Connection con = DBConfig.getConnection();
        String sql = "UPDATE users SET failed_attempts=0 WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
