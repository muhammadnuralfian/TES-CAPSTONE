//package dao;
//
//import java.util.List;
//import model.ProgramKolaborasi;
//
//public interface ProgramKolaborasiDAO {
//    boolean addProgram(ProgramKolaborasi program);
//    boolean updateProgram(ProgramKolaborasi program);
//    boolean deleteProgram(int idProgram);
//    List<ProgramKolaborasi> getAllPrograms();
//}

package dao;

import java.util.List;
import model.ProgramKolaborasi;

public interface ProgramKolaborasiDAO {
    boolean addProgram(ProgramKolaborasi program);
    boolean updateProgram(ProgramKolaborasi program);
    boolean deleteProgram(int id);
    List<ProgramKolaborasi> getAllPrograms();
}
