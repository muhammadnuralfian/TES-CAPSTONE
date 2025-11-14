package dao;


import database.DatabaseConnection;
import model.ProgramKolaborasi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramKolaborasiDAOImpl implements ProgramKolaborasiDAO {

    @Override
    public boolean addProgram(ProgramKolaborasi p) {
        String sql = "INSERT INTO program_kolaborasi (nama_program, deskripsi, tanggal_mulai, tanggal_selesai, status_program) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNamaProgram());
            ps.setString(2, p.getDeskripsi());
            ps.setDate(3, new java.sql.Date(p.getTanggalMulai().getTime()));
            ps.setDate(4, new java.sql.Date(p.getTanggalSelesai().getTime()));
            ps.setString(5, p.getStatusProgram());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateProgram(ProgramKolaborasi p) {
        String sql = "UPDATE program_kolaborasi SET nama_program=?, deskripsi=?, tanggal_mulai=?, tanggal_selesai=?, status_program=? WHERE id_program=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getNamaProgram());
            ps.setString(2, p.getDeskripsi());
            ps.setDate(3, new java.sql.Date(p.getTanggalMulai().getTime()));
            ps.setDate(4, new java.sql.Date(p.getTanggalSelesai().getTime()));
            ps.setString(5, p.getStatusProgram());
            ps.setInt(6, p.getIdProgram());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteProgram(int idProgram) {
        String sql = "DELETE FROM program_kolaborasi WHERE id_program=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idProgram);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<ProgramKolaborasi> getAllPrograms() {
        List<ProgramKolaborasi> list = new ArrayList<>();
        String sql = "SELECT * FROM program_kolaborasi";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProgramKolaborasi p = new ProgramKolaborasi();
                p.setIdProgram(rs.getInt("id_program"));
                p.setNamaProgram(rs.getString("nama_program"));
                p.setDeskripsi(rs.getString("deskripsi"));
                p.setTanggalMulai(rs.getDate("tanggal_mulai"));
                p.setTanggalSelesai(rs.getDate("tanggal_selesai"));
                p.setStatusProgram(rs.getString("status_program"));
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

