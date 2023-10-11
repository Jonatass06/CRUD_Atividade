import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO extends DAO<Item, Integer>{

    public ItemDAO() throws SQLException {
        super("item");
    }

    @Override
    public void defineComando() {
        super.setComando("INSERT INTO item VALUES (?,?,?)");
    }

    @Override
    public Integer pegarId(Item obj) {
        return obj.getId();
    }

    @Override
    public Item converter(ResultSet resultSet) throws SQLException {
        return new Item(resultSet);
    }



    @Override
    public void setValues(PreparedStatement statement, Item obj) throws SQLException {
        statement.setInt(1, obj.getId());
        statement.setString(2, obj.getNome());
        statement.setString(3, obj.getRaridade());
    }
}
