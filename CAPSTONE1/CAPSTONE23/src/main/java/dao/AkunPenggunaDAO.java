/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import model.AkunPengguna;
import java.util.List;
/**
 *
 * @author ASUS
 */
public interface AkunPenggunaDAO {
    // ✅ Login & Register
    AkunPengguna getByUsername(String username);

    AkunPengguna login(String username, String password);
    boolean register(AkunPengguna akun);

    // ✅ Tambahan khusus form lama (CRUD)
    boolean tambahAkun(AkunPengguna akun);
    boolean updateAkun(AkunPengguna akun);
    boolean hapusAkun(int idAkun);

    // ✅ Untuk daftar akun
    List<AkunPengguna> getAll();
    List<AkunPengguna> getAllAkun();

    // ✅ Untuk proses registrasi otomatis
    boolean isUsernameExists(String username);
    int getLastInsertedId();
}