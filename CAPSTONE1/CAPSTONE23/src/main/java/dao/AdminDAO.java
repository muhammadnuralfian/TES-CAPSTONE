package dao;

import model.Admin;
import java.util.List;

public interface AdminDAO {
    boolean insert(Admin admin);
    Admin getByIdAkun(int idAkun);
    List<Admin> getAll();
}
