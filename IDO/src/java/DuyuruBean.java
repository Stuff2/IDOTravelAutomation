
import com.sun.rowset.internal.Row;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean(name = "DuyuruBean")
@SessionScoped
public class DuyuruBean {

    private int id;
    private String tarih;
    private String isim;
    private String baslik;
    private String aciklama;
    private int sil_id;
    private int is_id;
    

    DataSource dataSource;

    public DuyuruBean() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/IDO");
        } catch (NamingException e) {
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

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }
    
    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
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
    
    public ResultSet getDuyuru() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT ID, TARIH,ISIM, BASLIK, ACIKLAMA FROM DUYURU ORDER BY TARIH DESC");

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            return resultSet1;
        } finally {

            connection.close();
        }

    }

    public String ekle() throws SQLException, ParseException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {

            PreparedStatement object1
                    = connection.prepareStatement("INSERT INTO DUYURU "
                            + "(TARIH,ISIM,BASLIK,ACIKLAMA)"
                            + "VALUES (?, ?, ?, ? )");
            object1.setDate(1, tipcevir(getTarih()));
            object1.setString(2, getIsim());
            object1.setString(3, getBaslik());
            object1.setString(4, getAciklama());
            object1.executeUpdate();
            return "duyurular"; // 
        } finally {
            connection.close();
        }
    }

    public String sil() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {

            PreparedStatement myObject
                    = connection.prepareStatement("delete from DUYURU where id=?");

            myObject.setInt(1, getSil_id());

            myObject.executeUpdate();
            return "duyurular"; // 
        } finally {
            connection.close();
        }
    }

    public String guncelle() throws SQLException, ParseException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {

            PreparedStatement myObject
                    = connection.prepareStatement("UPDATE DUYURU SET TARIH=?,ISIM=?,BASLIK=?,ACIKLAMA=? WHERE ID=?");
            myObject.setDate(1, tipcevir(getTarih()));
            myObject.setString(2, getIsim());
            myObject.setString(3, getBaslik());
            myObject.setString(4, getAciklama());
            myObject.setInt(5, getIs_id());

            myObject.executeUpdate();
            return "duyurular"; // 
        } finally {
            connection.close();
        }
    }

    public String sec() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT ID,TARIH,ISIM, BASLIK, ACIKLAMA FROM DUYURU where id=?");

            object2.setInt(1, getIs_id());

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            resultSet1.first();
            //this.id=resultSet1.getInt("ID");
            this.tarih = resultSet1.getString("TARIH");
            this.isim = resultSet1.getString("ISIM");
            this.baslik = resultSet1.getString("BASLIK");
            this.aciklama = resultSet1.getString("ACIKLAMA");

            return "duyuruguncelle";
        } finally {

            connection.close();
        }

    }

    

    
     public ResultSet getDuyuruListele() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT ID, TARIH,ISIM, BASLIK, ACIKLAMA FROM DUYURU");

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            return resultSet1;
        } finally {

            connection.close();
        }

    }
    
    public ResultSet getDuyuruAltCek() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT * FROM DUYURU FETCH FIRST 2 ROWS ONLY");

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            return resultSet1;
        } finally {

            connection.close();
        }

    }
    
    
    public String sec2() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT ID,TARIH,ISIM, BASLIK, ACIKLAMA FROM DUYURU where id=?");

            object2.setInt(1, getIs_id());

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            resultSet1.first();
            //this.id=resultSet1.getInt("ID");
            this.tarih = resultSet1.getString("TARIH");
            this.baslik = resultSet1.getString("BASLIK");
            this.aciklama = resultSet1.getString("ACIKLAMA");

            return "duyuruGor";
        } finally {

            connection.close();
        }

    }
    
    
    
}
