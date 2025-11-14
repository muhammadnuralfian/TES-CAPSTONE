package controller;

import javax.swing.JOptionPane;
import model.AkunPengguna;
import model.Admin;
import model.Stakeholder;
import dao.AkunPenggunaDAO;
import dao.AkunPenggunaDAOImpl;
import dao.AdminDAO;
import dao.AdminDAOImpl;
import dao.StakeholderDAO;
import dao.StakeholderDAOImpl;
import view.LoginForm;
import view.LoginStakeholderForm;
import view.PelaporanMasyarakatForm;
import view.AdminForm;
import view.KelolaLaporanForm;

public class LoginController {
    private final LoginForm view;
    private final AkunPenggunaDAO akunDAO = new AkunPenggunaDAOImpl();
    private final AdminDAO adminDAO = new AdminDAOImpl();
    private final StakeholderDAO stakeholderDAO = new StakeholderDAOImpl();

    public LoginController(LoginForm view) {
        this.view = view;
    }

    // dipakai LoginStakeholderForm kalau mau cari akun by username (optional)
    public AkunPengguna loginStakeholder(String username) {
        return akunDAO.getByUsername(username);
    }

    public void login(String username, String password) {
        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            JOptionPane.showMessageDialog(view, "Harap isi username dan password!");
            return;
        }

        AkunPengguna akun = akunDAO.login(username, password);
        if (akun == null) {
            JOptionPane.showMessageDialog(view, "Username atau password salah!");
            return;
        }

        String role = akun.getRole() == null ? "" : akun.getRole().toLowerCase();
        switch (role) {
            case "admin" -> handleAdmin(akun);
            case "stakeholder" -> handleStakeholder(akun);
            case "masyarakat" -> handleMasyarakat(akun);
            default -> JOptionPane.showMessageDialog(view, "Role tidak dikenali!");
        }
    }

    // ===== ADMIN =====
    private void handleAdmin(AkunPengguna akun) {
        Admin admin = adminDAO.getByIdAkun(akun.getIdAkun());
        if (admin == null) {
            JOptionPane.showMessageDialog(view, "Data admin tidak ditemukan!");
            return;
        }
        if (!"aktif".equalsIgnoreCase(admin.getStatusAdmin())) {
            JOptionPane.showMessageDialog(view, "Akun admin tidak aktif!");
            return;
        }

        String wewenang = admin.getWewenang() == null ? "" : admin.getWewenang().toLowerCase();
        if (wewenang.contains("terbatas")) {
            new KelolaLaporanForm("subadmin").setVisible(true);
        } else {
            new AdminForm().setVisible(true);
        }
        view.dispose();
    }

    // ===== STAKEHOLDER =====
    private void handleStakeholder(AkunPengguna akun) {
        // Ambil data stakeholder (kalau belum ada biarkan null; nanti di form diisi)
        Stakeholder s = stakeholderDAO.getByIdAkun(akun.getIdAkun());
        String jenis = (s == null) ? null : s.getJenisStakeholder();

        // BUKA LoginStakeholderForm terlebih dahulu (sesuai permintaan)
        LoginStakeholderForm f = new LoginStakeholderForm(akun, jenis);
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        view.dispose();
    }

    // ===== MASYARAKAT =====
    private void handleMasyarakat(AkunPengguna akun) {
        PelaporanMasyarakatForm f = new PelaporanMasyarakatForm(akun);
        f.setVisible(true);
        view.dispose();
    }
}
