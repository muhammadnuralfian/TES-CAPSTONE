/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author aliya
 */
public class AkunPengguna {
    protected int idAkun;
    protected String username;
    protected String password;
    protected String role;
    protected String alamat;
    protected String kontak;

    public AkunPengguna() {}

    public AkunPengguna(int idAkun, String username, String password, String role, String alamat, String kontak) {
        this.idAkun = idAkun;
        this.username = username;
        this.password = password;
        this.role = role;
        this.alamat = alamat;
        this.kontak = kontak;
    }

    // Getters dan Setters
    public int getIdAkun() { return idAkun; }
    public void setIdAkun(int idAkun) { this.idAkun = idAkun; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }

    public String getKontak() { return kontak; }
    public void setKontak(String kontak) { this.kontak = kontak; }
}