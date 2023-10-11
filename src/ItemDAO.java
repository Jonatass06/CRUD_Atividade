import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO extends DAO<Item, Integer>{

    public ItemDAO() throws SQLException {
        super("item");
    }

    @Override
    public void defineInserir() {
        super.setComando("INSERT INTO personagem VALUES (?,?,?)");
    }

    @Override
    public void defineAtualizar() {
        super.setComando("UPDATE personagem SET nome = ?, raridade = ? WHERE id = ?");
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
    public void setValues(PreparedStatement statement, Item obj, boolean atualizando) throws SQLException {
        if(atualizando){
            statement.setInt(3, obj.getId());
            statement.setString(1, obj.getNome());
            statement.setString(2, obj.getRaridade());
        }else{
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getNome());
            statement.setString(3, obj.getRaridade());
        }
    }
}
