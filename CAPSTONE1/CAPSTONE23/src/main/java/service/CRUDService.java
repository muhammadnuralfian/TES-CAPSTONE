///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package service;
//import database.DatabaseConnection;
//import model.AkunPengguna;
//import model.Admin;
//import model.Masyarakat;
//import model.Stakeholder;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// *
// * @author aliya
// */
//public class CRUDService {
//
//    // Cek apakah username sudah ada
//    public boolean isUsernameExists(String username) {
//        String sql = "SELECT username FROM akun_pengguna WHERE username = ?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, username);
//            ResultSet rs = stmt.executeQuery();
//            return rs.next();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return true;
//        }
//    }
//
//    // Registrasi
//    public String register(AkunPengguna akun) {
//        if (isUsernameExists(akun.getUsername())) {
//            return "exists";
//        }
//
//        String sql = "INSERT INTO akun_pengguna (nama, username, password, role, deskripsi_kegiatan) VALUES (?, ?, ?, ?, ?)";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, akun.getNama());
//            stmt.setString(2, akun.getUsername());
//            stmt.setString(3, akun.getPassword());
//            stmt.setString(4, akun.getRole());
//
//            if (akun instanceof Stakeholder st) {
//                stmt.setString(5, st.getDeskripsiKegiatan());
//            } else {
//                stmt.setNull(5, Types.VARCHAR);
//            }
//
//            stmt.executeUpdate();
//            return "success";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "error";
//        }
//    }
//
//    // Login
//    public String login(String username, String password) {
//        if (!isUsernameExists(username)) {
//            return "username_not_found";
//        }
//
//        String sql = "SELECT * FROM akun_pengguna WHERE username=? AND password=?";
//
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                return "success_" + rs.getString("role");
//            } else {
//                return "wrong_password";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "error";
//        }
//    }
//}
//
////public class CRUDService {
////
////    // CREATE akun
////    public boolean tambahAkun(AkunPengguna akun) {
////        String sql = "INSERT INTO akun_pengguna (nama, username, password, role, deskripsi_kegiatan) VALUES (?, ?, ?, ?, ?)";
////
////        try (Connection conn = DatabaseConnection.getConnection();
////             PreparedStatement stmt = conn.prepareStatement(sql)) {
////
////            stmt.setString(1, akun.getNama());
////            stmt.setString(2, akun.getUsername());
////            stmt.setString(3, akun.getPassword());
////            stmt.setString(4, akun.getRole());
////
////            // untuk masyarakat & admin = null
////            if (akun instanceof Stakeholder stakeholder) {
////                stmt.setString(5, stakeholder.getDeskripsiKegiatan());
////            } else {
////                stmt.setNull(5, Types.VARCHAR);
////            }
////
////            return stmt.executeUpdate() > 0;
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            return false;
////        }
////    }
////
////    // READ akun by username & password (untuk login)
////    public AkunPengguna login(String username, String password) {
////        String sql = "SELECT * FROM akun_pengguna WHERE username = ? AND password = ?";
////
////        try (Connection conn = DatabaseConnection.getConnection();
////             PreparedStatement stmt = conn.prepareStatement(sql)) {
////
////            stmt.setString(1, username);
////            stmt.setString(2, password);
////
////            ResultSet rs = stmt.executeQuery();
////
////            if (rs.next()) {
////                String role = rs.getString("role");
////
////                return switch (role) {
////                    case "Admin" -> new Admin(
////                            rs.getInt("id_akun"),
////                            rs.getString("nama"),
////                            rs.getString("username"),
////                            rs.getString("password")
////                    );
////                    case "Masyarakat" -> new Masyarakat(
////                            rs.getInt("id_akun"),
////                            rs.getString("nama"),
////                            rs.getString("username"),
////                            rs.getString("password")
////                    );
////                    case "Stakeholder" -> new Stakeholder(
////                            rs.getInt("id_akun"),
////                            rs.getString("nama"),
////                            rs.getString("username"),
////                            rs.getString("password"),
////                            rs.getString("deskripsi_kegiatan")
////                    );
////                    default -> null;
////                };
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return null;
////    }
////
////    // READ semua akun
////    public List<AkunPengguna> getSemuaAkun() {
////        List<AkunPengguna> list = new ArrayList<>();
////        String sql = "SELECT * FROM akun_pengguna";
////
////        try (Connection conn = DatabaseConnection.getConnection();
////             Statement stmt = conn.createStatement();
////             ResultSet rs = stmt.executeQuery(sql)) {
////
////            while (rs.next()) {
////                String role = rs.getString("role");
////
////                AkunPengguna akun = switch (role) {
////                    case "Admin" -> new Admin(
////                            rs.getInt("id_akun"),
////                            rs.getString("nama"),
////                            rs.getString("username"),
////                            rs.getString("password")
////                    );
////                    case "Masyarakat" -> new Masyarakat(
////                            rs.getInt("id_akun"),
////                            rs.getString("nama"),
////                            rs.getString("username"),
////                            rs.getString("password")
////                    );
////                    case "Stakeholder" -> new Stakeholder(
////                            rs.getInt("id_akun"),
////                            rs.getString("nama"),
////                            rs.getString("username"),
////                            rs.getString("password"),
////                            rs.getString("deskripsi_kegiatan")
////                    );
////                    default -> null;
////                };
////
////                if (akun != null) list.add(akun);
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        return list;
////    }
////
////    // UPDATE akun
////    public boolean updateAkun(AkunPengguna akun) {
////        String sql = "UPDATE akun_pengguna SET nama=?, username=?, password=?, role=?, deskripsi_kegiatan=? WHERE id_akun=?";
////
////        try (Connection conn = DatabaseConnection.getConnection();
////             PreparedStatement stmt = conn.prepareStatement(sql)) {
////
////            stmt.setString(1, akun.getNama());
////            stmt.setString(2, akun.getUsername());
////            stmt.setString(3, akun.getPassword());
////            stmt.setString(4, akun.getRole());
////
////            if (akun instanceof Stakeholder stakeholder) {
////                stmt.setString(5, stakeholder.getDeskripsiKegiatan());
////            } else {
////                stmt.setNull(5, Types.VARCHAR);
////            }
////
////            stmt.setInt(6, akun.getId());
////
////            return stmt.executeUpdate() > 0;
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            return false;
////        }
////    }
////
////    // DELETE akun
////    public boolean hapusAkun(int id) {
////        String sql = "DELETE FROM akun_pengguna WHERE id_akun=?";
////
////        try (Connection conn = DatabaseConnection.getConnection();
////             PreparedStatement stmt = conn.prepareStatement(sql)) {
////
////            stmt.setInt(1, id);
////            return stmt.executeUpdate() > 0;
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            return false;
////        }
////    }
////    
////    public boolean isUsernameExists(String username) {
////    String sql = "SELECT username FROM akun_pengguna WHERE username = ?";
////
////        try (Connection conn = DatabaseConnection.getConnection();
////            PreparedStatement stmt = conn.prepareStatement(sql)) {
////
////            stmt.setString(1, username);
////            ResultSet rs = stmt.executeQuery();
////            return rs.next(); // true jika username ditemukan
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            return false;
////        }
////    }
////}