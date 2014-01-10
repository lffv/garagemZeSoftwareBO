
package garagemdozebackend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class ModelMovimentos {

    private Connection con;

    public Map<Integer,Movimento> getMovimentos() throws ClassNotFoundException, SQLException{

        HashMap<Integer, Movimento> ret = new HashMap<>();
        Movimento m;
        GregorianCalendar g;

        this.ligar();

        String sql = "select m, um, dm, v, ganho(v) from movimentos where a>0 AND v>0";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet res = ps.executeQuery();

        while(res.next()){

           g = new GregorianCalendar();
           g.setTime(res.getDate(3));
            
           m = new Movimento(res.getInt(1), res.getString(2), g, res.getDouble(4),res.getDouble(5));

           ret.put(res.getInt(1), m);

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

