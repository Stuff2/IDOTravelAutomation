import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
 
@ManagedBean
@SessionScoped
public class vericekView implements Serializable {
     
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private String kalkis; 
    
   
   
    private Map<String,String> nerden;
    
    DataSource dataSource;
     
    @PostConstruct
    public void init()  {
        nerden  = new HashMap<String, String>();
         try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/IDO");
        } catch (NamingException e) {
            e.printStackTrace();
        }
      
        try {
              if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT ID, ISIM FROM VAPUR");

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());
            while(resultSet1.next()){
                
             
             nerden.put(resultSet1.getString("ISIM"), resultSet1.getString("ID"));
            }
            

           
         connection.close();
        } 
        catch(Exception ex ){
            
        
        
        }
        
        
        

           
        
      
    }
 
    public Map<String, Map<String, String>> getData() {
        return data;
    }

   

   
    public String getKalkis() {
        return kalkis;
    }

    

    

    public Map<String, String> getNerden() {
        return nerden;
    }

    
 
   
 
    
}