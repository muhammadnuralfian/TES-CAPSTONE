/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Masyarakat extends AkunPengguna {
    private String nama;
    private String jenisKelamin;
    private Date tanggalDaftar;

    public Masyarakat() {
        super();
    }

    public Masyarakat(int idAkun, String username, String password, String role,
                      String alamat, String kontak, String jenisKelamin, Date tanggalDaftar) {
        super(idAkun, username, password, role, alamat, kontak);
        this.nama = username;
        this.jenisKelamin = jenisKelamin;
        this.tanggalDaftar = tanggalDaftar;
    }

    public Masyarakat(String username, String password, String alamat) {
        super(0, username, password, "masyarakat", alamat, "");
        this.nama = username;
        this.jenisKelamin = "Tidak Diketahui";
        this.tanggalDaftar = new Date(System.currentTimeMillis());
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public Date getTanggalDaftar() {
        return tanggalDaftar;
    }

    public void setTanggalDaftar(Date tanggalDaftar) {
        this.tanggalDaftar = tanggalDaftar;
    }
}
