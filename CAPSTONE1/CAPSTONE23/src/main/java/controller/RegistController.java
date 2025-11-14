/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.*;
import model.*;
import javax.swing.JOptionPane;
/**
 *
 * @author ASUS
 */
public class RegistController {

    private final AkunPenggunaDAO akunDAO;
    private final MasyarakatDAO masyarakatDAO;
    private final StakeholderDAO stakeholderDAO;

    public RegistController() {
        akunDAO = new AkunPenggunaDAOImpl();
        masyarakatDAO = new MasyarakatDAOImpl();
        stakeholderDAO = new StakeholderDAOImpl();
    }

    public boolean register(String username, String password, String alamat, String kontak, String role) {
        if (username.isEmpty() || password.isEmpty() || alamat.isEmpty() || kontak.isEmpty() || role.isEmpty()) {
            JOptionPane.showMessageDialog(null, "❌ Semua field wajib diisi!");
            return false;
        }

        if (akunDAO.isUsernameExists(username)) {
            JOptionPane.showMessageDialog(null, "⚠️ Username sudah digunakan!");
            return false;
        }

        AkunPengguna akun = new AkunPengguna(0, username, password, role, alamat, kontak);
        if (!akunDAO.register(akun)) {
            JOptionPane.showMessageDialog(null, "❌ Gagal mendaftarkan akun ke database!");
            return false;
        }

        int idAkunBaru = akunDAO.getLastInsertedId();
        if (idAkunBaru <= 0) {
            JOptionPane.showMessageDialog(null, "❌ Gagal mendapatkan ID akun baru!");
            return false;
        }

        switch (role.toLowerCase()) {
            case "masyarakat" -> masyarakatDAO.insert(
    new Masyarakat(idAkunBaru, username, password, role, alamat, kontak,
                   "Tidak Diketahui", new java.sql.Date(System.currentTimeMillis()))
);

            case "stakeholder" -> stakeholderDAO.insert(
                new Stakeholder(idAkunBaru, username, password, role, alamat, kontak, "Umum", "Belum Diisi", "-")
            );
            case "admin" -> new AdminDAOImpl().insert(
                new Admin(idAkunBaru, username, password, role, alamat, kontak, "Petugas", "Aktif", "Penuh")
            );
            default -> {
                JOptionPane.showMessageDialog(null, "⚠️ Role tidak dikenali!");
                return false;
            }
        }

        JOptionPane.showMessageDialog(null, "✅ Registrasi berhasil!");
        return true;
        }    
} 
