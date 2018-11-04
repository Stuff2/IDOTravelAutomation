
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

@ManagedBean(name = "SeferBean")
@SessionScoped
public class SeferBean {

    private int id;
    private String k_yeri;
    private String k_vakit;
    private String v_yeri;
    private String y_sure;
    private int vpr_id;
    private int bos_koltuk;
    private double n_ucret;
    private double i_ucret;
    private int sil_id;
    private int is_id;
    private int sefersay;

    DataSource dataSource;

    public SeferBean() {
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

    public String getK_Yeri() {
        return k_yeri;
    }

    public void setK_Yeri(String k_yeri) {
        this.k_yeri = k_yeri;
    }

    public String getK_Vakit() {
        return k_vakit;
    }

    public void setK_Vakit(String k_vakit) {
        this.k_vakit = k_vakit;
    }

    public String getV_Yeri() {
        return v_yeri;
    }

    public void setV_Yeri(String v_yeri) {
        this.v_yeri = v_yeri;
    }

    public String getY_Sure() {
        return y_sure;
    }

    public void setY_Sure(String y_sure) {
        this.y_sure = y_sure;
    }
    
    
    public int getVpr_ID() {
        return vpr_id;
    }

    public void setVpr_ID(int vpr_id) {
        this.vpr_id = vpr_id;
    }
    
    
      
    public int getBos_Koltuk() {
        return bos_koltuk;
    }

    public void setBos_Koltuk(int bos_koltuk) {
        this.bos_koltuk = bos_koltuk;
    }
    
    
      public double getN_Ucret() {
        return n_ucret;
    }

    public void setN_Ucret(double n_ucret) {
        this.n_ucret = n_ucret;
    }
    
    
      public double getI_Ucret() {
        return i_ucret;
    }

    public void setI_Ucret(double i_ucret) {
        this.i_ucret = i_ucret;
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
 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 java.util.Date date = sdf.parse(tarih);
 java.sql.Date sqlDate = new java.sql.Date(date.getTime());
 return sqlDate;
 }
    /*
    SimpleDateFormat sdf7 = new SimpleDateFormat("h:mm a");
		System.out.println(sdf7.format(new Date()));
    */
    
    public Time tipcevir1(String saat) throws ParseException{
 
 java.sql.Time sqlTime = java.sql.Time.valueOf(saat);
 return sqlTime;
 }
    
    public ResultSet getSefer() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT s.ID,s.K_VAKIT, s.K_YERI, s.V_YERI, s.Y_SÜRE, s.N_UCRET, s.I_UCRET, v.ISIM, s.BOS_KOLTUK FROM SEFER s INNER JOIN VAPUR v ON s.VPR_ID = v.ID ORDER BY s.K_VAKIT DESC");

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
            PreparedStatement object2
                    = connection.prepareStatement("SELECT KAPASITE FROM VAPUR WHERE ID =?");
            object2.setInt(1, getVpr_ID());
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());
            resultSet1.first();
            PreparedStatement object1
                    = connection.prepareStatement("INSERT INTO SEFER "
                            + "(K_YERI, K_VAKIT, V_YERI, VPR_ID, BOS_KOLTUK, N_UCRET, I_UCRET, Y_SÜRE)"
                            + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)");

            object1.setString(1, getK_Yeri());
            object1.setDate(2, tipcevir(getK_Vakit()));
            object1.setString(3, getV_Yeri());
            object1.setInt(4, getVpr_ID());
            //object1.setInt(5, 55);
            object1.setInt(5, resultSet1.getInt("KAPASITE"));
            object1.setDouble(6, getN_Ucret());
            object1.setDouble(7, getI_Ucret()); 
            object1.setTime(8, tipcevir1(getY_Sure())); 
            object1.executeUpdate();
            return "seferler"; // 
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
                    = connection.prepareStatement("delete from SEFER where id=?");

            myObject.setInt(1, getSil_id());

            myObject.executeUpdate();
            return "seferler"; 
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
                    = connection.prepareStatement("UPDATE SEFER SET K_YERI=?,K_VAKIT=?, V_YERI=?, VPR_ID=?, BOS_KOLTUK=?, N_UCRET=?, I_UCRET=?, Y_SÜRE=? WHERE ID=?");
           
            myObject.setString(1, getK_Yeri());
            myObject.setDate(2, tipcevir(getK_Vakit()));
            myObject.setString(3, getV_Yeri());
            myObject.setInt(4, getVpr_ID());
            myObject.setInt(5, getBos_Koltuk());
            myObject.setDouble(6, getN_Ucret());
            myObject.setDouble(7, getI_Ucret()); 
            myObject.setTime(8, tipcevir1(getY_Sure()));
            myObject.setInt(9, getIs_id());
            myObject.executeUpdate();
            
            
            
            return "seferler"; // 
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
                    "SELECT ID,K_YERI, K_VAKIT, V_YERI, VPR_ID, BOS_KOLTUK, N_UCRET, I_UCRET, Y_SÜRE FROM SEFER where ID=?");

            object2.setInt(1, getIs_id());

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            resultSet1.first();
            this.id=resultSet1.getInt("ID");
            this.k_yeri = resultSet1.getString("K_YERI");
            this.k_vakit = resultSet1.getString("K_VAKIT");
            this.v_yeri = resultSet1.getString("V_YERI");
            this.vpr_id = resultSet1.getInt("VPR_ID");
            this.bos_koltuk = resultSet1.getInt("BOS_KOLTUK");
            this.n_ucret = resultSet1.getDouble("N_UCRET");
            this.i_ucret = resultSet1.getDouble("I_UCRET");
            this.y_sure = resultSet1.getString("Y_SÜRE");
                                    

            return "seferlerguncelle";
        } finally {

            connection.close();
        }

    }

   
    
    public int getSeferSay() throws SQLException {
        sefersay = sefersay();
        return sefersay;
    }

    public void setSeferSay_id(int sefersay) {
        this.sefersay = sefersay;
    }
 
private int sefersay() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }
        try {
            PreparedStatement object3 = connection.prepareStatement(
                    "SELECT COUNT(ID) as TOTALSHIP FROM SEFER");

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
