    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package dao;

    import database.DatabaseConnection;
    import java.sql.*;
    import java.util.*;
    import model.Laporan;

    /**
     * Implementasi DAO untuk tabel pelaporan_masyarakat
     * @author ASUS
     */
    public class LaporanDAOImpl implements LaporanDAO {
        private final Connection conn;

        public LaporanDAOImpl() {
            Connection tempConn = null;
            try {
                tempConn = DatabaseConnection.getConnection();
            } catch (Exception e) {
                System.err.println("❌ Gagal mendapatkan koneksi: " + e.getMessage());
                e.printStackTrace();
            }
            conn = tempConn;

            if (conn == null) {
                System.err.println("❌ Koneksi database gagal dibuat di LaporanDAOImpl");
            }
        }

        @Override
        public boolean tambahLaporan(Laporan laporan) {
            String sql = """
                    INSERT INTO pelaporan_masyarakat 
                    (tanggal_laporan, lokasi_laporan, jenis_masalah, deskripsi, status_laporan, id_masyarakat, id_stakeholder)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                    """;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDate(1, new java.sql.Date(laporan.getTanggalLaporan().getTime()));
                stmt.setString(2, laporan.getLokasiLaporan());
                stmt.setString(3, laporan.getJenisMasalah());
                stmt.setString(4, laporan.getDeskripsi());
                stmt.setString(5, laporan.getStatusLaporan());

                // Hindari error FK (jika NULL)
                if (laporan.getIdMasyarakat() > 0) {
                    stmt.setInt(6, laporan.getIdMasyarakat());
                } else {
                    stmt.setNull(6, java.sql.Types.INTEGER);
                }

                if (laporan.getIdStakeholder() > 0) {
                    stmt.setInt(7, laporan.getIdStakeholder());
                } else {
                    stmt.setNull(7, java.sql.Types.INTEGER);
                }

                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("❌ Gagal tambah laporan: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public Laporan getById(int idPelapor) {
            Laporan l = null;
            String sql = "SELECT * FROM pelaporan_masyarakat WHERE id_pelapor=?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, idPelapor);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        l = new Laporan();
                        l.setIdPelapor(rs.getInt("id_pelapor"));
                        l.setTanggalLaporan(rs.getDate("tanggal_laporan"));
                        l.setLokasiLaporan(rs.getString("lokasi_laporan"));
                        l.setJenisMasalah(rs.getString("jenis_masalah"));
                        l.setDeskripsi(rs.getString("deskripsi"));
                        l.setStatusLaporan(rs.getString("status_laporan"));
                        l.setIdMasyarakat(rs.getInt("id_masyarakat"));
                        l.setIdStakeholder(rs.getInt("id_stakeholder"));
                    }
                }
            } catch (SQLException e) {
                System.err.println("❌ Gagal ambil laporan by ID: " + e.getMessage());
                e.printStackTrace();
            }
            return l;
        }

        @Override
        public List<Laporan> getAllLaporan() {
            List<Laporan> list = new ArrayList<>();
            String sql = "SELECT * FROM pelaporan_masyarakat";
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Laporan laporan = new Laporan();
                    laporan.setIdPelapor(rs.getInt("id_pelapor"));
                    laporan.setTanggalLaporan(rs.getDate("tanggal_laporan"));
                    laporan.setLokasiLaporan(rs.getString("lokasi_laporan"));
                    laporan.setJenisMasalah(rs.getString("jenis_masalah"));
                    laporan.setDeskripsi(rs.getString("deskripsi"));
                    laporan.setStatusLaporan(rs.getString("status_laporan"));
                    laporan.setIdMasyarakat(rs.getInt("id_masyarakat"));
                    laporan.setIdStakeholder(rs.getInt("id_stakeholder"));
                    list.add(laporan);
                }
            } catch (SQLException e) {
                System.err.println("❌ Gagal ambil semua data laporan: " + e.getMessage());
                e.printStackTrace();
            }
            return list;
        }

        @Override
        public boolean updateLaporan(Laporan laporan) {
            String sql = """
                    UPDATE pelaporan_masyarakat SET
                    tanggal_laporan=?, lokasi_laporan=?, jenis_masalah=?, deskripsi=?, 
                    status_laporan=?, id_masyarakat=?, id_stakeholder=? 
                    WHERE id_pelapor=?
                    """;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDate(1, new java.sql.Date(laporan.getTanggalLaporan().getTime()));
                stmt.setString(2, laporan.getLokasiLaporan());
                stmt.setString(3, laporan.getJenisMasalah());
                stmt.setString(4, laporan.getDeskripsi());
                stmt.setString(5, laporan.getStatusLaporan());

                if (laporan.getIdMasyarakat() > 0) {
                    stmt.setInt(6, laporan.getIdMasyarakat());
                } else {
                    stmt.setNull(6, java.sql.Types.INTEGER);
                }

                if (laporan.getIdStakeholder() > 0) {
                    stmt.setInt(7, laporan.getIdStakeholder());
                } else {
                    stmt.setNull(7, java.sql.Types.INTEGER);
                }

                stmt.setInt(8, laporan.getIdPelapor());
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("❌ Gagal update laporan: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean hapusLaporan(int idLaporan) {
            String sql = "DELETE FROM pelaporan_masyarakat WHERE id_pelapor=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idLaporan);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.err.println("❌ Gagal hapus laporan: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
    }
