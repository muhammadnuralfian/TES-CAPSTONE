package dao;

import model.Stakeholder;
import java.util.List;

public interface StakeholderDAO {
    boolean insert(Stakeholder stakeholder);
    boolean update(Stakeholder stakeholder);
    boolean delete(int idAkun);
    Stakeholder getByIdAkun(int idAkun);
    List<Stakeholder> getAll();
}
