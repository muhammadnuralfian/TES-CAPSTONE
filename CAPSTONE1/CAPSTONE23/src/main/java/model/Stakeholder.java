/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Stakeholder extends AkunPengguna {
    private String namaStakeholder;
    private String jenisStakeholder;
    private String deskripsiKegiatan;
    private String statusStakeholder;

    public Stakeholder() {
        super();
    }

    public Stakeholder(String username, String password, String alamat, String kontak) {
    super(0, username, password, "stakeholder", alamat, kontak);
    this.namaStakeholder = username;
    this.jenisStakeholder = "Umum";
    this.deskripsiKegiatan = "Belum diisi";
    this.statusStakeholder = "Aktif";
}
    public Stakeholder(int idAkun, String username, String password, String role,
    String alamat, String kontak, String namaStakeholder,
    String jenisStakeholder, String deskripsiKegiatan) {
    super(idAkun, username, password, role, alamat, kontak);
    this.namaStakeholder = namaStakeholder;
    this.jenisStakeholder = jenisStakeholder;
    this.deskripsiKegiatan = deskripsiKegiatan;
    this.statusStakeholder = "Aktif";
}


    public String getNamaStakeholder() {
        return namaStakeholder;
    }

    public void setNamaStakeholder(String namaStakeholder) {
        this.namaStakeholder = namaStakeholder;
    }

    public String getJenisStakeholder() {
        return jenisStakeholder;
    }

    public void setJenisStakeholder(String jenisStakeholder) {
        this.jenisStakeholder = jenisStakeholder;
    }

    public String getDeskripsiKegiatan() {
        return deskripsiKegiatan;
    }

    public void setDeskripsiKegiatan(String deskripsiKegiatan) {
        this.deskripsiKegiatan = deskripsiKegiatan;
    }

    public String getStatusStakeholder() {
        return statusStakeholder;
    }

    public void setStatusStakeholder(String statusStakeholder) {
        this.statusStakeholder = statusStakeholder;
    }
}
