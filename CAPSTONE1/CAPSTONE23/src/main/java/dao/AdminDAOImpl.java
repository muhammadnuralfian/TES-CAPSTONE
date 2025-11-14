/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import model.Admin;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class AdminDAOImpl implements AdminDAO {
    private Connection conn;

    public AdminDAOImpl() {
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(Admin admin) {
        String sql = "INSERT INTO admin (jabatan, wewenang, id_akun, status_admin) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, admin.getJabatan());
            ps.setString(2, admin.getWewenang());
            ps.setInt(3, admin.getIdAkun());
            ps.setString(4, admin.getStatusAdmin());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Admin getByIdAkun(int idAkun) {
        String sql = "SELECT * FROM admin WHERE id_akun=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAkun);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Admin a = new Admin();
                a.setIdAkun(idAkun);
                a.setJabatan(rs.getString("jabatan"));
                a.setWewenang(rs.getString("wewenang"));
                a.setStatusAdmin(rs.getString("status_admin"));
                return a;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> list = new ArrayList<>();
        String sql = "SELECT * FROM admin";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Admin a = new Admin();
                a.setIdAkun(rs.getInt("id_akun"));
                a.setJabatan(rs.getString("jabatan"));
                a.setWewenang(rs.getString("wewenang"));
                a.setStatusAdmin(rs.getString("status_admin"));
                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
