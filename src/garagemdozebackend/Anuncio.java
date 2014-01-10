
package garagemdozebackend;

import java.util.GregorianCalendar;

public class Anuncio {
    
    private int id;
    private String vendedor;
    private int codCategoria;
    private String categoria;
    private double preco;
    private String titulo;
    private String descricao;
    private GregorianCalendar dataCriado;
    private GregorianCalendar dataPublicacao;
    private GregorianCalendar dataFinal;
    private int estado;

    public Anuncio(int id, String vendedor, int codCategoria, String categoria, double preco, String titulo, String descricao, GregorianCalendar dataCriado, GregorianCalendar dataPublicacao, GregorianCalendar dataFinal, int estado) {
        this.id = id;
        this.vendedor = vendedor;
        this.codCategoria = codCategoria;
        this.categoria = categoria;
        this.preco = preco;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriado = dataCriado;
        this.dataPublicacao = dataPublicacao;
        this.dataFinal = dataFinal;
        this.estado = estado;
    }
    
    public int getId() {
        return id;
    }

    public String getVendedor() {
        return vendedor;
    }
    
    public int getCodCategoria() {
        return codCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public GregorianCalendar getDataCriado() {
        return dataCriado;
    }

    public GregorianCalendar getDataPublicacao() {
        return dataPublicacao;
    }

    public GregorianCalendar getDataFinal() {
        return dataFinal;
    }

    public int getEstado() {
        return estado;
    }
    
    public String getNomeEstado(){
        
        GregorianCalendar g = new GregorianCalendar();
        
        if(g.after(this.dataFinal) && this.estado!=2){
            return "Expirado";
        }
        
        if(this.estado==0) {
            return "Em curso";
        }
        else if(this.estado==1) {
            return "Em leilão";
        }
        else {
            return "Terminado";
        }
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }
    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setDataCriado(GregorianCalendar dataCriado) {
        this.dataCriado = dataCriado;
    }

    public void setDataPublicacao(GregorianCalendar dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public void setDataFinal(GregorianCalendar dataFinal) {
        this.dataFinal = dataFinal;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
}
