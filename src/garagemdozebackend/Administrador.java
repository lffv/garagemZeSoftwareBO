
package garagemdozebackend;

public class Administrador {
    
    private String user;
    private String nome;
    
    public Administrador(String user,String nome){
        this.user = user;
        this.nome = nome;
    }

    public String getUser(){ return this.user; }
    public String getNome(){ return this.nome; }
    
    public void setUser(String user){ this.user = user; }
    public void setNome(String nome){ this.nome = nome; }

}
