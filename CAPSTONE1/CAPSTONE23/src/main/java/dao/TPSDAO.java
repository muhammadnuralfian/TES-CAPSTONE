package dao;

import java.util.List;
import model.TPS;

public interface TPSDAO {
    boolean insert(TPS tps);
    boolean update(TPS tps);
    boolean delete(int idTps);
    List<TPS> getAll();
    TPS getById(int idTps);
}
