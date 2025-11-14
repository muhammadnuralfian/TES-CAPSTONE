/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Admin extends AkunPengguna {
    private String jabatan;
    private String statusAdmin;
    private String wewenang;

    public Admin() {
        super();
    }
    
    public Admin(int idAkun, String username, String password, String role,
             String alamat, String kontak, String jabatan,
             String statusAdmin, String wewenang) {
    super(idAkun, username, password, role, alamat, kontak);
    this.jabatan = jabatan;
    this.statusAdmin = statusAdmin;
    this.wewenang = wewenang;
}

    public Admin(String username, String password) {
    super(0, username, password, "admin", "", "");
    this.jabatan = "Petugas";
    this.statusAdmin = "Aktif";
    this.wewenang = "Penuh";
}


    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getStatusAdmin() {
        return statusAdmin;
    }

    public void setStatusAdmin(String statusAdmin) {
        this.statusAdmin = statusAdmin;
    }

    public String getWewenang() {
        return wewenang;
    }

    public void setWewenang(String wewenang) {
        this.wewenang = wewenang;
    }
}
