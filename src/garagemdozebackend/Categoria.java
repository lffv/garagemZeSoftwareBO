
package garagemdozebackend;

public class Categoria {
    
    private int cod;
    private int codMae;
    private String nome;
    private String nomeMae;

    public Categoria(int cod, int codMae, String nome, String nomeMae) {
        this.cod = cod;
        this.codMae = codMae;
        this.nome = nome;
        this.nomeMae = nomeMae;
    }

    public int getCod() {
        return cod;
    }

    public int getCodMae() {
        return codMae;
    }

    public String getNome() {
        return nome;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public void setCodMae(int codMae) {
        this.codMae = codMae;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }
    
    public boolean isMae(){
        return this.codMae==0;
    }
    
    @Override
    public String toString(){
        return this.nome;
    }
    
}
