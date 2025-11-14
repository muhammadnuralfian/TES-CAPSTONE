/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import database.DatabaseConnection;
import model.AkunPengguna;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ASUS
 */

public class AkunPenggunaDAOImpl implements AkunPenggunaDAO {
    private Connection conn;
    private int lastInsertedId = -1;

    public AkunPenggunaDAOImpl() {
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ LOGIN
   @Override
public AkunPengguna login(String username, String password) {
    String sql = "SELECT * FROM akun_pengguna WHERE username=? AND password=?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new AkunPengguna(
                rs.getInt("id_akun"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
                rs.getString("alamat"),
                rs.getString("kontak")
            );
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

    // ✅ REGISTER
    @Override
    public boolean register(AkunPengguna akun) {
        String sql = "INSERT INTO akun_pengguna (username, password, role, alamat, kontak) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, akun.getUsername());
            ps.setString(2, akun.getPassword());
            ps.setString(3, akun.getRole());
            ps.setString(4, akun.getAlamat());
            ps.setString(5, akun.getKontak());
            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) lastInsertedId = keys.getInt(1);
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ TAMBAH AKUN (untuk form)
    @Override
    public boolean tambahAkun(AkunPengguna akun) {
        return register(akun); // alias dari register()
    }

    // ✅ UPDATE AKUN
    @Override
    public boolean updateAkun(AkunPengguna akun) {
        String sql = "UPDATE akun_pengguna SET username=?, password=?, role=?, alamat=?, kontak=? WHERE id_akun=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, akun.getUsername());
            ps.setString(2, akun.getPassword());
            ps.setString(3, akun.getRole());
            ps.setString(4, akun.getAlamat());
            ps.setString(5, akun.getKontak());
            ps.setInt(6, akun.getIdAkun());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ HAPUS AKUN
    @Override
    public boolean hapusAkun(int idAkun) {
        String sql = "DELETE FROM akun_pengguna WHERE id_akun=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAkun);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ✅ CEK USERNAME
    @Override
    public boolean isUsernameExists(String username) {
        String sql = "SELECT 1 FROM akun_pengguna WHERE username = ? LIMIT 1";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    // ✅ GET ID TERAKHIR
    @Override
    public int getLastInsertedId() {
        return lastInsertedId;
    }

    // ✅ GET SEMUA AKUN
    @Override
    public List<AkunPengguna> getAll() {
        List<AkunPengguna> list = new ArrayList<>();
        String sql = "SELECT * FROM akun_pengguna";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new AkunPengguna(
                        rs.getInt("id_akun"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getString("alamat"),
                        rs.getString("kontak")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ alias untuk form lama
    @Override
    public List<AkunPengguna> getAllAkun() {
        return getAll();
    }

    @Override
public AkunPengguna getByUsername(String username) {
    AkunPengguna akun = null;
    String sql = "SELECT * FROM akun_pengguna WHERE username = ?";
    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            akun = new AkunPengguna();
            akun.setIdAkun(rs.getInt("id_akun"));
            akun.setUsername(rs.getString("username"));
            akun.setPassword(rs.getString("password"));
            akun.setRole(rs.getString("role"));
            akun.setAlamat(rs.getString("alamat"));
            akun.setKontak(rs.getString("kontak"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return akun;
}
}