
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 @ManagedBean( name="PersonelBean" )
 @SessionScoped
 
public class PersonelBean {
    private int id;
    private String isim;
    private String sisim;
    private int TC;
    private String gorev;
    private String eposta;
    private String sifre;
    private int sil_id;
    private int is_id;
    private int personelsay;
    
    DataSource dataSource;
    
    public PersonelBean()
{
try
{
Context ctx = new InitialContext();
dataSource = (DataSource) ctx.lookup("jdbc/IDO");
}
catch (NamingException e) 
{
e.printStackTrace();
}
}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }
    public String getSisim() {
        return sisim;
    }

    public void setSisim(String sisim) {
        this.sisim = sisim;
    }
    public int getTC() {
        return TC;
    }

    public void setTC(int TC) {
        this.TC = TC;
    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

    public String getEposta() {
        return eposta;
    }

    public void setEposta(String eposta) {
        this.eposta = eposta;
    }
   
     public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
    
    public int getSil_id() {
        return sil_id;
    }

    public void setSil_id(int sil_id) {
        this.sil_id = sil_id;
    }

    public int getIs_id() {
        return is_id;
    }

    public void setIs_id(int is_id) {
        this.is_id = is_id;
    }
    
    
     public ResultSet getPersonel() throws SQLException
 {

 if ( dataSource == null )
 throw new SQLException( "Kaynak Bulunamadı" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Kaynağa Bağlanılamadı" );
 
 try
 {
 PreparedStatement object2 = connection.prepareStatement(
         "SELECT ID,ISIM, S_ISIM, TC, GOREV, EPOSTA, SIFRE FROM PERSONEL" );
 
 CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
  resultSet1.populate( object2.executeQuery());
 
 
  
  return resultSet1; 
 }
 finally
 {   
     
 connection.close();
 } 

 }
     public String ekle() throws SQLException
 {
  if ( dataSource == null )
 throw new SQLException( "Kaynak Bulunamadı" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Kaynağa Bağlanılamadı" );

try{
 
        PreparedStatement object1 =
        connection.prepareStatement( "INSERT INTO PERSONEL " +
        "(ISIM, S_ISIM, TC, GOREV, EPOSTA, SIFRE)" +
        "VALUES ( ?, ?, ?, ?, ?, ? )" );

        object1.setString( 1, getIsim() );
        object1.setString( 2, getSisim() );
        object1.setInt( 3, getTC() );
        object1.setString( 4, getGorev() );
        object1.setString( 5, getEposta() );
        object1.setString( 6, getSifre() );
       object1.executeUpdate(); 
  

   
 return "personel"; // 
 } 
 finally
 {
 connection.close(); 
 }
 }
 
     public String sil() throws SQLException
 {
  if ( dataSource == null )
 throw new SQLException( "Kaynak Bulunamadı" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Kaynağa Bağlanılamadı" );

 try
 {
    
 PreparedStatement myObject =
 connection.prepareStatement( "delete from PERSONEL where id=?");

  
 myObject.setInt( 1, getSil_id() );
 
myObject.executeUpdate();
 return "personel"; // 
 } 
 finally
 {
 connection.close(); 
 }
 }
    
  
     
     
     
     public String guncelle() throws SQLException
 {
  if ( dataSource == null )
 throw new SQLException( "Kaynak Bulunamadı" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Kaynağa Bağlanılamadı" );

 try
 {
    
 PreparedStatement myObject =
 connection.prepareStatement( "UPDATE PERSONEL SET ISIM=?,S_ISIM=?,TC=?,GOREV=?,EPOSTA=?,SIFRE=? WHERE ID=?");
 
 myObject.setString( 1, getIsim() );
 myObject.setString( 2, getSisim() );
 myObject.setInt( 3, getTC() );
 myObject.setString( 4, getGorev() );
 myObject.setString( 5, getEposta() );
 myObject.setString( 6, getSifre() );
 myObject.setInt( 7, getIs_id() );
 
myObject.executeUpdate();
 return "personel"; // 
 } 
 finally
 {
 connection.close(); 
 }
 }
     public String sec() throws SQLException
 {

 if ( dataSource == null )
 throw new SQLException( "Kaynak Bulunamadı" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Kaynağa Bağlanılamadı" );
 
 try
 {
 PreparedStatement object2 = connection.prepareStatement(
         "SELECT ID,ISIM,S_ISIM, TC, GOREV, EPOSTA, SIFRE FROM PERSONEL where id=?" );
 
    object2.setInt( 1, getIs_id() );
 
 CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
  resultSet1.populate( object2.executeQuery());
  
 resultSet1.first();
 this.id=resultSet1.getInt("ID");
 this.isim=resultSet1.getString("ISIM");
 this.sisim=resultSet1.getString("S_ISIM");
 this.TC=resultSet1.getInt("TC");
 this.gorev=resultSet1.getString("GOREV");
 this.eposta=resultSet1.getString("EPOSTA");
 this.sifre=resultSet1.getString("SIFRE");
  return "personelguncelle"; 
 }
 finally
 {   
     
 connection.close();
 } 

 }
     
     public int getPersonelSay() throws SQLException {
        personelsay = personelsay();
        return personelsay;
    }

    public void setPersonelSay_id(int personelsay) {
        this.personelsay = personelsay;
    }
 
private int personelsay() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }
        try {
            PreparedStatement object3 = connection.prepareStatement(
                    "SELECT COUNT(ID) as TOTALSHIP FROM PERSONEL");

            ResultSet deger = object3.executeQuery();
            while (deger.next()) {
                return deger.getInt("TOTALSHIP");
            }
            return 0;
            
        } finally {
            connection.close();
        }
    }

     

    
}

