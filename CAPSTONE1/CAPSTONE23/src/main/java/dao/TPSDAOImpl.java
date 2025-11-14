package dao;

import database.DatabaseConnection;
import model.TPS;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TPSDAOImpl implements TPSDAO {

    private Connection conn;

    public TPSDAOImpl() {
        try {
            conn = DatabaseConnection.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(TPS tps) {
        String sql = "INSERT INTO tps (nama_tps, lokasi, kapasitas_m3, status, id_stakeholder) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tps.getNamaTps());
            ps.setString(2, tps.getLokasi());
            ps.setDouble(3, tps.getKapasitasM3());
            ps.setString(4, tps.getStatus());
            ps.setInt(5, tps.getIdStakeholder());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(TPS tps) {
        String sql = "UPDATE tps SET nama_tps=?, lokasi=?, kapasitas_m3=?, status=?, id_stakeholder=? WHERE id_tps=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tps.getNamaTps());
            ps.setString(2, tps.getLokasi());
            ps.setDouble(3, tps.getKapasitasM3());
            ps.setString(4, tps.getStatus());
            ps.setInt(5, tps.getIdStakeholder());
            ps.setInt(6, tps.getIdTps());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int idTps) {
        String sql = "DELETE FROM tps WHERE id_tps=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTps);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TPS> getAll() {
        List<TPS> list = new ArrayList<>();
        String sql = "SELECT * FROM tps";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TPS t = new TPS();
                t.setIdTps(rs.getInt("id_tps"));
                t.setNamaTps(rs.getString("nama_tps"));
                t.setLokasi(rs.getString("lokasi"));
                t.setKapasitasM3(rs.getDouble("kapasitas_m3"));
                t.setStatus(rs.getString("status"));
                t.setIdStakeholder(rs.getInt("id_stakeholder"));
                list.add(t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public TPS getById(int idTps) {
        String sql = "SELECT * FROM tps WHERE id_tps=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTps);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                TPS t = new TPS();
                t.setIdTps(rs.getInt("id_tps"));
                t.setNamaTps(rs.getString("nama_tps"));
                t.setLokasi(rs.getString("lokasi"));
                t.setKapasitasM3(rs.getDouble("kapasitas_m3"));
                t.setStatus(rs.getString("status"));
                t.setIdStakeholder(rs.getInt("id_stakeholder"));
                return t;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
