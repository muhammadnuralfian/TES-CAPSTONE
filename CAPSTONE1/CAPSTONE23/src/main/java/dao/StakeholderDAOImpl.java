/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import database.DatabaseConnection;
import model.Stakeholder;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ASUS
 */
public class StakeholderDAOImpl implements StakeholderDAO {
    private Connection conn;

    public StakeholderDAOImpl() {
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(Stakeholder s) {
        String sql = "INSERT INTO stakeholder (id_akun, nama_stakeholder, jenis_stakeholder, deskripsi_kegiatan, status_stakeholder) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getIdAkun());
            ps.setString(2, s.getNamaStakeholder());
            ps.setString(3, s.getJenisStakeholder());
            ps.setString(4, s.getDeskripsiKegiatan());
            ps.setString(5, s.getStatusStakeholder());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Stakeholder s) {
        String sql = "UPDATE stakeholder SET nama_stakeholder=?, jenis_stakeholder=?, deskripsi_kegiatan=?, status_stakeholder=? WHERE id_akun=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getNamaStakeholder());
            ps.setString(2, s.getJenisStakeholder());
            ps.setString(3, s.getDeskripsiKegiatan());
            ps.setString(4, s.getStatusStakeholder());
            ps.setInt(5, s.getIdAkun());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int idAkun) {
        String sql = "DELETE FROM stakeholder WHERE id_akun=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAkun);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

 
    @Override
public Stakeholder getByIdAkun(int idAkun) {
    Stakeholder s = null;
    String sql = "SELECT * FROM stakeholder WHERE id_akun = ?";
    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, idAkun);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            s = new Stakeholder();
            s.setIdAkun(rs.getInt("id_akun"));
            s.setJenisStakeholder(rs.getString("jenis_stakeholder"));
            s.setDeskripsiKegiatan(rs.getString("deskripsi_tugas")); // sesuai kolom DB kamu
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return s;
}




    @Override
    public List<Stakeholder> getAll() {
        List<Stakeholder> list = new ArrayList<>();
        String sql = "SELECT * FROM stakeholder";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Stakeholder s = new Stakeholder();
                s.setIdAkun(rs.getInt("id_akun"));
                s.setNamaStakeholder(rs.getString("nama_stakeholder"));
                s.setJenisStakeholder(rs.getString("jenis_stakeholder"));
                s.setDeskripsiKegiatan(rs.getString("deskripsi_kegiatan"));
                s.setStatusStakeholder(rs.getString("status_stakeholder"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
