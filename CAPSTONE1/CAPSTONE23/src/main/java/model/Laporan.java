package model;

import java.util.Date;

public class Laporan {
    private int idLaporan;
    private int idPelapor;
    private Date tanggalLaporan;
    private String lokasiLaporan;
    private String jenisMasalah;
    private String deskripsi;       // ✅ diwajibkan oleh DAO/Form
    private String statusLaporan;
    private int idMasyarakat;
    private int idStakeholder;

    public Laporan() {}

    // ✅ dipakai di PelaporanMasyarakatForm
    public Laporan(Date tanggal, String lokasi, String jenis, String deskripsi,
                   String status, int idMasyarakat, int idStakeholder) {
        this.tanggalLaporan = tanggal;
        this.lokasiLaporan = lokasi;
        this.jenisMasalah = jenis;
        this.deskripsi = deskripsi;
        this.statusLaporan = status;
        this.idMasyarakat = idMasyarakat;
        this.idStakeholder = idStakeholder;
    }

    // ===== Getter/Setter lengkap =====
    public int getIdLaporan() { return idLaporan; }
    public void setIdLaporan(int idLaporan) { this.idLaporan = idLaporan; }

    public int getIdPelapor() { return idPelapor; }
    public void setIdPelapor(int idPelapor) { this.idPelapor = idPelapor; }

    public Date getTanggalLaporan() { return tanggalLaporan; }
    public void setTanggalLaporan(Date tanggalLaporan) { this.tanggalLaporan = tanggalLaporan; }

    public String getLokasiLaporan() { return lokasiLaporan; }
    public void setLokasiLaporan(String lokasiLaporan) { this.lokasiLaporan = lokasiLaporan; }

    public String getJenisMasalah() { return jenisMasalah; }
    public void setJenisMasalah(String jenisMasalah) { this.jenisMasalah = jenisMasalah; }

    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }

    public String getStatusLaporan() { return statusLaporan; }
    public void setStatusLaporan(String statusLaporan) { this.statusLaporan = statusLaporan; }

    public int getIdMasyarakat() { return idMasyarakat; }
    public void setIdMasyarakat(int idMasyarakat) { this.idMasyarakat = idMasyarakat; }

    public int getIdStakeholder() { return idStakeholder; }
    public void setIdStakeholder(int idStakeholder) { this.idStakeholder = idStakeholder; }
}
