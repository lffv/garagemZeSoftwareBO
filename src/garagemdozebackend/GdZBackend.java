
package garagemdozebackend;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.net.aso.i;

public class GdZBackend {

   private ModelUtilizadores modelUtilizadores;
   private ModelCategorias modelCategorias;
   private ModelAnuncios modelAnuncios;
   private ModelMovimentos modelMovimentos;
           
   private Map<String, Utilizador> utilizadores;
   private Map<Integer, Categoria> categorias;
   private Map<Integer, Anuncio> anuncios;
   private Map<Integer, Movimento> movimentos;

   public GdZBackend() throws ClassNotFoundException, SQLException{
       
       this.modelUtilizadores = new ModelUtilizadores();
       this.modelCategorias = new ModelCategorias();
       this.modelAnuncios = new ModelAnuncios();
       this.modelMovimentos = new ModelMovimentos();
       
       this.utilizadores = this.modelUtilizadores.getUtilizadores();
       this.categorias = this.modelCategorias.getCategorias();
       this.anuncios = this.modelAnuncios.getAnuncios();
       this.movimentos = this.modelMovimentos.getMovimentos();
   }

   public Administrador login(String user, String pass) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException{
        return this.modelUtilizadores.verifcaLogin(user, pass);
   }
   
   public Utilizador getUtilizador(String user){
       return this.utilizadores.get(user);
   }

   public Map getUtilizadores(){
        return this.utilizadores;
   }
   
   public Map getAnuncios(){
        return this.anuncios;
   }   
   
   public Map getMovimentos(){
       return this.movimentos;
   }

   public int editUtilizador(String user, String nome, String morada, String codpostal, String email) throws ClassNotFoundException{

       Utilizador tmp = this.utilizadores.get(user);
       if(tmp!=null){
           try {
               this.modelUtilizadores.editarUtilizador(user, nome, morada, codpostal, email);
           } catch (SQLException ex) {
               return ex.getErrorCode();
           }
        tmp.setNome(nome);
        tmp.setMorada(morada);
        tmp.setCodPostal(codpostal);
        tmp.setEmail(email);
        return -1;
       } return -2;
       
       
   }

   public boolean removeUtilizadores(Collection<String> users) throws SQLException, ClassNotFoundException{
       if(this.modelUtilizadores.removeUtilizadores(users)){
           
           for(String s : users){
               this.utilizadores.remove(s);
           }
           
           return true;
       }
       
       return false;
   }
   
   public Map<Integer,Categoria> getCategorias() throws ClassNotFoundException, SQLException{
       return this.categorias;
   }
   
   public int addCategoria(int codMae, String nome, String nomeMae){
        try {

            int cod = this.modelCategorias.addCategoria(codMae,nome);

            Categoria c = new Categoria(cod, codMae, nome, nomeMae);

            this.categorias.put(cod, c);

            return cod * (-1); // > 0 reservado para os erros do oracle

        } catch (SQLException ex) {
            
            return ex.getErrorCode();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GdZBackend.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
       
   }
   
   // SE Mãe elimina/actualiza tambném os filhos
   public void actCategoria(int cod, int cod2, int tipo){
        
       try {
            // actualizar na BD
            this.modelCategorias.actCategoria(cod,cod2,tipo);

            this.categorias = this.modelCategorias.getCategorias();
            this.anuncios = this.modelAnuncios.getAnuncios();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GdZBackend.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GdZBackend.class.getName()).log(Level.SEVERE, null, ex);
        }

       
   }
   
   public double getTotalValorMovimentos(){
   
       double ret=0;
       
       for(Movimento m : this.movimentos.values()){
           ret += m.getValor();
       }
       
       return ret;
       
   }

   public double getTotalGanhoMovimentos(){

       double ret=0;

       for(Movimento m : this.movimentos.values()){
           ret += m.getGanho();
       }

       return ret;

   }
  
}

