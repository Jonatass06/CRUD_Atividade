import java.sql.ResultSet;
import java.sql.SQLException;

public class Item {

    private Integer id;
    private String nome;
    private String raridade;

    public Item(Integer id, String nome, String raridade) {
        this.id = id;
        this.nome = nome;
        this.raridade = raridade;
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Item(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.nome = resultSet.getString("nome");
        this.raridade = resultSet.getString("raridade");
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRaridade() {
        return raridade;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", raridade='" + raridade + '\'' +
                '}';
    }
}
