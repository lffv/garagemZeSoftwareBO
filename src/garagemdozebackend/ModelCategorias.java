
package garagemdozebackend;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/* AO ELIMINAR, UTILIZAR O PROCEDURE JÁ CRIADO */

public class ModelCategorias {
    
    private Connection con;
    
    public Map<Integer,Categoria> getCategorias() throws ClassNotFoundException, SQLException{

        HashMap<Integer, Categoria> ret = new HashMap<>();
        Categoria ca;

        this.ligar();

        String sql = "select * from dadosCategorias";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet res = ps.executeQuery();

        while(res.next()){

           ca = new Categoria(res.getInt(1), res.getInt(2), res.getString(3), res.getString(4));
           ret.put(res.getInt(1), ca);

        }
        
        this.desligar();
        
        return ret;
        
    }
    
    public int addCategoria(int code, String nome) throws SQLException, ClassNotFoundException{
    
        this.ligar();
        
        int ret=0;
        
        String sql = "INSERT INTO CATEGORIAS (pc,nc) VALUES(?,?)";

        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setInt(1, code);
        ps.setString(2, nome);
        
        ps.execute();
        
        sql = "SELECT COALESCE(MAX(c),0) from CATEGORIAS";
        
        ps = con.prepareStatement(sql);
        
        ResultSet res = ps.executeQuery();
        
        while(res.next()){
            ret = res.getInt(1);
        }
        
        this.desligar();
        
        return ret;
        
    }
    
    public void actCategoria(int cod,int cod2, int tipo) throws ClassNotFoundException, SQLException{
        
        this.ligar();
        
        String sql = "{ call actCategoriasEFilhos(?,?,?) }";

        CallableStatement cs = con.prepareCall(sql);
        
        cs.setInt(1, cod);
        cs.setInt(2, cod2);
        cs.setInt(3, tipo);

        cs.execute();
        
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
