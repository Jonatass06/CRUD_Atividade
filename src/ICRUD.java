import java.io.Closeable;
import java.sql.SQLException;
import java.util.Set;

public interface ICRUD<T, ID> extends AutoCloseable {

    void inserir(T obj) throws SQLException;

    void deletar(ID id) throws SQLException;

    Set<T> buscarTodos() throws SQLException;
    T buscarUm(ID id) throws SQLException;

}
