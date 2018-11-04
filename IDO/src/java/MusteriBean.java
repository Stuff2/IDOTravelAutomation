
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
 @ManagedBean( name="MusteriBean" )
 @SessionScoped
 
public class MusteriBean {
    private int id;
    private String isim;
    private String sisim;
    private Long TC;
    private String dtarihi;
    private String eposta;
    private String sifre;
    private int sil_id;
    private int is_id;
    
    DataSource dataSource;
    
    public MusteriBean()
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
    public Long getTC() {
        return TC;
    }

    public void setTC(Long TC) {
        this.TC = TC;
    }

    public String getDtarihi() {
        return dtarihi;
    }

    public void setDtarihi(String dtarihi) {
        this.dtarihi = dtarihi;
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
    
  public Date tipcevir(String tarih) throws ParseException{
 SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
 java.util.Date date = sdf1.parse(tarih);
 java.sql.Date sqlDate = new java.sql.Date(date.getTime());
 return sqlDate;
 }
  
     public ResultSet getMusteriler() throws SQLException
 {

 if ( dataSource == null )
 throw new SQLException( "Kaynak Bulunamadı" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Kaynağa Bağlanılamadı" );
 
 try
 {
 PreparedStatement object2 = connection.prepareStatement(
         "SELECT ID,ISIM, S_ISIM, TC, D_TARIH, EPOSTA, SIFRE FROM MUSTERI " );
 
 CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
  resultSet1.populate( object2.executeQuery());
 
 
  
  return resultSet1; 
 }
 finally
 {   
     
 connection.close();
 } 

 }
     public String ekle() throws SQLException, ParseException
 {
  if ( dataSource == null )
 throw new SQLException( "Kaynak Bulunamadı" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Kaynağa Bağlanılamadı" );

 try
 {
    
 PreparedStatement object1 =
 connection.prepareStatement( "INSERT INTO MUSTERI " +
 "(ISIM, S_ISIM, TC, D_TARIH, EPOSTA, SIFRE)" +
 "VALUES ( ?, ?, ?, ?, ?, ? )" );

 object1.setString( 1, getIsim() );
 object1.setString( 2, getSisim() );
 object1.setLong( 3, getTC() );
 object1.setDate( 4, tipcevir(getDtarihi() ));
 object1.setString( 5, getEposta() );
 object1.setString( 6, getSifre() );
 object1.executeUpdate(); 
 return "musteriler"; // 
 } 
 finally
 {
 connection.close(); 
 }
 }
     
     
     
       public String ekleKullanici() throws SQLException, ParseException
 {
  if ( dataSource == null )
 throw new SQLException( "Kaynak Bulunamadı" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Kaynağa Bağlanılamadı" );

 try
 {
    
 PreparedStatement object1 =
 connection.prepareStatement( "INSERT INTO MUSTERI " +
 "(ISIM, S_ISIM, TC, D_TARIH, EPOSTA, SIFRE)" +
 "VALUES ( ?, ?, ?, ?, ?, ? )" );

 object1.setString( 1, getIsim() );
 object1.setString( 2, getSisim() );
 object1.setLong( 3, getTC() );
 object1.setDate( 4, tipcevir(getDtarihi() ));
 object1.setString( 5, getEposta() );
 object1.setString( 6, getSifre() );
 object1.executeUpdate(); 
 return "index"; // 
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
 connection.prepareStatement( "delete from MUSTERI where id=?");

  
 myObject.setInt( 1, getSil_id() );
 
myObject.executeUpdate();
 return "musteriler"; // 
 } 
 finally
 {
 connection.close(); 
 }
 }
    
  
     
     
     
     public String guncelle() throws SQLException, ParseException
 {
  if ( dataSource == null )
 throw new SQLException( "Kaynak Bulunamadı" );

 Connection connection = dataSource.getConnection();

 if ( connection == null )
 throw new SQLException( "Kaynağa Bağlanılamadı" );

 try
 {
    
 PreparedStatement myObject =
 connection.prepareStatement( "UPDATE MUSTERI SET ISIM=?,S_ISIM=?,TC=?,D_TARIH=?,EPOSTA=?,SIFRE=? WHERE ID=?");
 
 myObject.setString( 1, getIsim() );
 myObject.setString( 2, getSisim() );
 myObject.setLong( 3, getTC() );
 myObject.setDate( 4, tipcevir(getDtarihi()) );
 myObject.setString( 5, getEposta() );
 myObject.setString( 6, getSifre() );
 myObject.setInt( 7, getIs_id() );
 
myObject.executeUpdate();
 return "musteriler"; // 
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
         "SELECT ID,ISIM,S_ISIM, TC, D_TARIH, EPOSTA, SIFRE FROM MUSTERI where id=?" );
 
    object2.setInt( 1, getIs_id() );
 
 CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
  resultSet1.populate( object2.executeQuery());
  
 resultSet1.first();
 this.id=resultSet1.getInt("ID");
 this.isim=resultSet1.getString("ISIM");
 this.sisim=resultSet1.getString("S_ISIM");
 this.TC=resultSet1.getLong("TC");
 this.dtarihi=resultSet1.getString("D_TARIH");
 this.eposta=resultSet1.getString("EPOSTA");
 this.sifre=resultSet1.getString("SIFRE");
  return "musterilerguncelle"; 
 }
 finally
 {   
     
 connection.close();
 } 

 }
    
}

