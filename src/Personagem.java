import java.sql.ResultSet;
import java.sql.SQLException;

public class Personagem {

    private Integer id;
    private String nome;
    private Integer idade;

    private Item item;

    public Personagem(Integer id, String nome, Integer idade, Item item) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.item = item;
    }

    public Personagem(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getInt("id");
        this.nome = resultSet.getString("nome");
        this.idade = resultSet.getInt("idade");
        int idItem = resultSet.getInt("item");
        if (idItem != 0) {
            this.item = new Item(idItem);
        }
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public Item getItem() {
        return item;
    }
}
