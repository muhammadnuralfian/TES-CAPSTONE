/*
 * Click nbfs:nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs:nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;
import model.Masyarakat;
import java.util.List;
/**
 *
 * @author ASUS
 */
public interface MasyarakatDAO {
    Masyarakat getByIdAkun(int idAkun);
    List<Masyarakat> getAll();
    boolean insert(Masyarakat m);
    boolean update(Masyarakat m);
    boolean delete(int idMasyarakat);
}
