/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import java.util.List;
import model.Laporan;
/**
 *
 * @author ASUS
 */

public interface LaporanDAO {
    boolean tambahLaporan(Laporan laporan);
    boolean updateLaporan(Laporan laporan);
    boolean hapusLaporan(int idLaporan);
    List<Laporan> getAllLaporan();

    public Laporan getById(int idLaporan);
}