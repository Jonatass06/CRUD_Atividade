public class Main {

    public static void main(String[] args) {

        try(ICRUD<Personagem, Integer> crudPersonagem  = new PersonagemDAO();
            ICRUD<Item, Integer> crudItem  = new ItemDAO()){
            

        }catch(Exception e){
            System.out.println(e);
        }

    }
}