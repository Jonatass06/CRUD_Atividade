import javax.management.openmbean.KeyAlreadyExistsException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class DAO<T, ID> implements ICRUD<T, ID>{

    private Connection connection;
    private String comando;
    private String tabela;
    public DAO(String tabela) throws SQLException {
        this.tabela = tabela;
        this.connection = Banco.conectar();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
    @Override
    public void inserir(T obj) throws SQLException {
        try{
            buscarUm(pegarId(obj));
            throw new KeyAlreadyExistsException();
        }catch(NoSuchElementException e){
            defineComando();
            PreparedStatement statement = this.connection.prepareStatement(this.comando);
            setValues(statement, obj);
            statement.execute();
        }
    }

    @Override
    public void deletar(ID id) throws SQLException{
        this.comando = "DELETE FROM " + this.tabela + " WHERE id = ?";
        PreparedStatement statement = this.connection.prepareStatement(this.comando);
        statement.setInt(1, (Integer)id);
        statement.execute();
    }

    @Override
    public Set<T> buscarTodos() throws SQLException{
        this.comando = "SELECT * FROM " + this.tabela;
        Set<T> set = new HashSet<>();
        PreparedStatement statement = this.connection.prepareStatement(this.comando);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            set.add(converter(resultSet));
        }
        return set;
    }

    @Override
    public T buscarUm(ID id) throws SQLException{
        this.comando = "SELECT * FROM " + this.tabela + " WHERE id = ?";
        PreparedStatement statement = this.connection.prepareStatement(this.comando);
        statement.setInt(1, (Integer)id);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return converter(resultSet);
        }
        throw new NoSuchElementException();
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public abstract void defineComando();
    public abstract ID pegarId(T obj);
    public abstract T converter(ResultSet resultSet) throws SQLException;
    public abstract void setValues(PreparedStatement statement, T obj) throws SQLException;
}
