
package garagemdozebackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class ModelAnuncios {
    
    private Connection con;
    
    /* CRIAR DEPOIS UM ELIMINA EXPIRADOS QUE USA O PROCEDURE */
    
    public Map<Integer,Anuncio> getAnuncios() throws ClassNotFoundException, SQLException{

        HashMap<Integer, Anuncio> ret = new HashMap<>();
        Anuncio a;
        GregorianCalendar g1,g2,g3;

        this.ligar();

        String sql = "select an.*, ca.nc from anuncios an, categorias ca WHERE ca.c=an.c";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet res = ps.executeQuery();

        while(res.next()){

            g1 = new GregorianCalendar();
            g2 = new GregorianCalendar();
            g3 = new GregorianCalendar();

            g1.setTime(res.getDate("DC"));
            
            if(res.getDate("DP")==null) {
                g2.set(0, 0, 0);
            }else {
                g2.setTime(res.getDate("DP"));
            }            
            g3.setTime(res.getDate("DF"));
            
            a=new Anuncio(res.getInt("A"), 
                      res.getString("U"),
                      res.getInt("C"),
                      res.getString("NC"),
                      res.getDouble("P"),
                      res.getString("T"),
                      res.getString("D"),
                      g1,
                      g2,
                      g3,
                      res.getInt("E"));
                      
           ret.put(res.getInt("A"), a);

        }
        
        this.desligar();
        
        return ret;
        
    }
    
    private void ligar() throws ClassNotFoundException, SQLException{
        
        Class.forName("oracle.jdbc.driver.OracleDriver");
        this.con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:BD","garagemze","novogaragemze");
        
    }
    
    private void desligar() throws SQLException{
        this.con.close();
    }
    
}
