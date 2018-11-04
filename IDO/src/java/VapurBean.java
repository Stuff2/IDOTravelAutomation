
import com.sun.rowset.internal.Row;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

@ManagedBean(name = "VapurBean")
@SessionScoped
public class VapurBean {

    private int id;
    private String isim;
    private int kapasite;
    private String tanim;
    private int sil_id;
    private int is_id;
    private int vapurId;
    private int vapursay;

    DataSource dataSource;

    public VapurBean() {
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

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public String getTanim() {
        return tanim;
    }

    public void setTanim(String tanim) {
        this.tanim = tanim;
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

    public ResultSet getVapur() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT ID,ISIM, KAPASITE, TANIM FROM VAPUR");

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            return resultSet1;
        } finally {

            connection.close();
        }

    }

    public String ekle() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {

            PreparedStatement object1
                    = connection.prepareStatement("INSERT INTO VAPUR "
                            + "(ISIM,KAPASITE,TANIM)"
                            + "VALUES ( ?, ?, ? )");

            object1.setString(1, getIsim());
            object1.setInt(2, getKapasite());
            object1.setString(3, getTanim());
            object1.executeUpdate();
            return "vapurlar"; // 
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
                    = connection.prepareStatement("delete from VAPUR where id=?");

            myObject.setInt(1, getSil_id());

            myObject.executeUpdate();
            return "vapurlar"; // 
        } finally {
            connection.close();
        }
    }

    public String guncelle() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {

            PreparedStatement myObject
                    = connection.prepareStatement("UPDATE VAPUR SET ISIM=?,KAPASITE=?,TANIM=? WHERE ID=?");

            myObject.setString(1, getIsim());
            myObject.setInt(2, getKapasite());
            myObject.setString(3, getTanim());
            myObject.setInt(4, getIs_id());

            myObject.executeUpdate();
            return "vapurlar"; // 
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
                    "SELECT ID,ISIM, KAPASITE, TANIM FROM VAPUR where id=?");

            object2.setInt(1, getIs_id());

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            resultSet1.first();
            //this.id=resultSet1.getInt("ID");
            this.isim = resultSet1.getString("ISIM");
            this.tanim = resultSet1.getString("TANIM");
            this.kapasite = resultSet1.getInt("KAPASITE");

            return "vapurguncelle";
        } finally {

            connection.close();
        }

    }

    public int getVapurId() {
        return vapurId;
    }

    public void setVapurId(int vapurId) {
        this.vapurId = vapurId;
    }
    
    

    public List<SelectItem> vapurtest() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT ID,ISIM FROM VAPUR");

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            List<SelectItem> items = new ArrayList<>();
            SelectItem item;
            while (resultSet1.next()) {
                item = new SelectItem();
                item.setValue(resultSet1.getInt(0));
                item.setLabel(resultSet1.getString(1));
                items.add(item);
            }
            return items;
        } finally {

            connection.close();
        }

    }
    
    public int getVapurSay() throws SQLException {
        vapursay = vapursay();
        return vapursay;
    }

    public void setVapurSay_id(int vapursay) {
        this.vapursay = vapursay;
    }
 
private int vapursay() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }
        try {
            PreparedStatement object3 = connection.prepareStatement(
                    "SELECT COUNT(ID) as TOTALSHIP FROM VAPUR");

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
