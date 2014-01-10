
package garagemdozebackend;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class ModelUtilizadores {
    
    private Connection con;
    
    public Administrador verifcaLogin(String user, String pass) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException{
        
        this.ligar();
        
        Administrador b = null;
        
        String sql = "SELECT pd, nd FROM ADMINISTRADORES WHERE ad = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, user);
        
        ResultSet res = ps.executeQuery();
        
        while(res.next()){
            String tmp = res.getString(1);
            String hashpass = MD5Crypt.getMd5Hash(pass);
            if (tmp.equals(hashpass)) {
                String nc = res.getString(2);
                b = new Administrador(user,nc);
            }
        }

        res.close();
        ps.close();

        this.desligar();
        
        return b;
        
    }
    
    public Map<String, Utilizador> getUtilizadores() throws ClassNotFoundException, SQLException{
        
        HashMap<String,Utilizador> ret = new HashMap<>();
        Utilizador u;
        GregorianCalendar g;
        
        this.ligar();
        
        String sql = "SELECT u,nu,dn,m,cp,nf,e,idade(dn) FROM UTILIZADORES uti, FREGUESIAS fre WHERE uti.f = fre.f";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet res = ps.executeQuery();

        while(res.next()){
            
            g = new GregorianCalendar();
            //g.setTime(res.getDate(3));

            u = new Utilizador(
                    res.getString(1), 
                    res.getString(2), 
                    g, 
                    res.getString(4), 
                    res.getString(6), 
                    res.getString(5), 
                    res.getString(7),
                    res.getInt(8));
            
            ret.put(res.getString(1), u);
        
        }
        
        res.close();
        ps.close();

        this.desligar();
        
        return ret;
    
    }
    
   public boolean removeUtilizadores(Collection<String> users) throws SQLException, ClassNotFoundException{
       
        if(users.isEmpty()) return false;
        String cond = "";
        
        for(String us : users){
          cond += " u = ? OR";
        }

        cond = cond.substring(0, cond.length()-3);

        String sql = "DELETE FROM UTILIZADORES WHERE" + cond;
        
        this.ligar();

        PreparedStatement ps = con.prepareStatement(sql);
        int i = 1;
        for(String us : users){
          ps.setString(i, us);
          i++;
        }
        
        boolean b = ps.execute();
        
        ps.close();

        this.desligar();
        
        return !b;
       
    }
   
    public void editarUtilizador(String user, String nome, String morada, String codpostal, String email) throws ClassNotFoundException, SQLException{

        String sql = "UPDATE UTILIZADORES SET nu=?, m=?, cp=?, e=? WHERE u=?";

        this.ligar();

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nome);
        ps.setString(2, morada);
        ps.setString(3, codpostal);
        ps.setString(4, email);
        ps.setString(5, user);

        ps.execute();
        
        ps.close();

        this.desligar();

    }
    
    private void ligar() throws ClassNotFoundException, SQLException{
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        this.con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:BD","garagemze","novogaragemze");
        
    }
    
    private void desligar() throws SQLException{
        this.con.close();
    }
     
}
