/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import model.Masyarakat;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ASUS
 */
public class MasyarakatDAOImpl implements MasyarakatDAO {
    private Connection conn;

    public MasyarakatDAOImpl() {
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(Masyarakat m) {
        String sql = "INSERT INTO masyarakat (id_akun, nama, alamat, kontak, jenis_kelamin, tanggal_daftar) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, m.getIdAkun());
            ps.setString(2, m.getNama());
            ps.setString(3, m.getAlamat());
            ps.setString(4, m.getKontak());
            ps.setString(5, m.getJenisKelamin());
            ps.setDate(6, m.getTanggalDaftar());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Masyarakat m) {
        String sql = "UPDATE masyarakat SET nama=?, alamat=?, kontak=?, jenis_kelamin=?, tanggal_daftar=? WHERE id_akun=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, m.getNama());
            ps.setString(2, m.getAlamat());
            ps.setString(3, m.getKontak());
            ps.setString(4, m.getJenisKelamin());
            ps.setDate(5, m.getTanggalDaftar());
            ps.setInt(6, m.getIdAkun());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int idAkun) {
        String sql = "DELETE FROM masyarakat WHERE id_akun=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAkun);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Masyarakat getByIdAkun(int idAkun) {
        String sql = "SELECT * FROM masyarakat WHERE id_akun=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAkun);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Masyarakat m = new Masyarakat();
                m.setIdAkun(rs.getInt("id_akun"));
                m.setNama(rs.getString("nama"));
                m.setAlamat(rs.getString("alamat"));
                m.setKontak(rs.getString("kontak"));
                m.setJenisKelamin(rs.getString("jenis_kelamin"));
                m.setTanggalDaftar(rs.getDate("tanggal_daftar"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Masyarakat> getAll() {
        List<Masyarakat> list = new ArrayList<>();
        String sql = "SELECT * FROM masyarakat";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Masyarakat m = new Masyarakat();
                m.setIdAkun(rs.getInt("id_akun"));
                m.setNama(rs.getString("nama"));
                m.setAlamat(rs.getString("alamat"));
                m.setKontak(rs.getString("kontak"));
                m.setJenisKelamin(rs.getString("jenis_kelamin"));
                m.setTanggalDaftar(rs.getDate("tanggal_daftar"));
                list.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
