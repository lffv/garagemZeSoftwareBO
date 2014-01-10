
package garagemdozebackend;

import java.util.GregorianCalendar;

public class Utilizador {
    
    private String user;
    private String nome;
    private GregorianCalendar datanasc;
    private String morada;
    private String freguesia;
    private String codPostal;
    private String email;
    private int idade;
    
    public Utilizador(String user, String nome, GregorianCalendar datanasc, String morada, String freguesia, String codPostal, String email, int idade) {
        this.user = user;
        this.nome = nome;
        this.datanasc = datanasc;
        this.morada = morada;
        this.freguesia = freguesia;
        this.codPostal = codPostal;
        this.email = email;
        this.idade = idade;
    }
    
    public String getUser() {
        return user;
    }

    public String getNome() {
        return nome;
    }

    public GregorianCalendar getDatanasc() {
        return datanasc;
    }

    public String getMorada() {
        return morada;
    }

    public String getFreguesia() {
        return freguesia;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public String getEmail() {
        return email;
    }
    
    public int getIdade() {
        return idade;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDatanasc(GregorianCalendar datanasc) {
        this.datanasc = datanasc;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setFreguesia(String freguesia) {
        this.freguesia = freguesia;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setEmail(int idade) {
        this.idade = idade;
    }
    
}
