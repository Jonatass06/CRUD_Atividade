import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonagemDAO extends DAO<Personagem, Integer> {


    public PersonagemDAO() throws SQLException {
        super("personagem");
    }

    @Override
    public void defineInserir() {
        super.setComando("INSERT INTO personagem VALUES (?,?,?,?)");
    }

    @Override
    public void defineAtualizar() {
        super.setComando("UPDATE personagem SET nome = ?, idade = ?, item = ? WHERE id = ?");
    }

    @Override
    public Integer pegarId(Personagem obj) {
        return obj.getId();
    }

    @Override
    public Personagem converter(ResultSet resultSet) throws SQLException {
        return new Personagem(resultSet);
    }

    @Override
    public void setValues(PreparedStatement statement, Personagem obj, boolean atualizando) throws SQLException {
        if (atualizando) {
            statement.setInt(4, obj.getId());
            statement.setString(1, obj.getNome());
            statement.setInt(2, obj.getIdade());
            try{
                statement.setInt(3, obj.getItem().getId());
            }catch(NullPointerException e){
                statement.setNull(3, 0);
            }
        }else{
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getNome());
            statement.setInt(3, obj.getIdade());
            try{
                statement.setInt(4, obj.getItem().getId());
            }catch(NullPointerException e){
                statement.setNull(4, 0);
            }
        }
    }

}
