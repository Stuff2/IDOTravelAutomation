
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
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;


@ManagedBean
@SessionScoped
public class DropdownView implements Serializable {

    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private Map<String, String> nerden;
    private Map<String, String> nereye;
    DataSource dataSource;
    private String kalkis;
    private String varis;
    private String tarih;
    private int tam;
    private int indirimli;
    private int is_id;
    private int id;
    private String k_yeri;
    private double ucret;
    private int kisiSayisi;
    private int seferID;
    private int musteriID;
    private int biletID;
    private int iptalID;
    private int onaylaRID;
    private int yolcuSay;
    private int musteriBiletSay;
    private String isim;
    private String sisim;
    private int TC;
    private String dtarihi;
    private String eposta;
    private String sifre;
    private String k_vakit;
    private String v_yeri;
    private int vpr_id;
    private int bos_koltuk;
    private double n_ucret;
    private double i_ucret;
    private String y_sure;
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
   

    public int getOnaylaRID() {
        return onaylaRID;
    }

    
    public void setOnaylaRID(int onaylaRID) {
        this.onaylaRID = onaylaRID;
    }
    
    
        public int getMusteriID() {
        return musteriID;
    }

    
    public void setMusteriID(int musteriID) {
        this.musteriID = musteriID;
    }
    

    public int getIptalID() {
        return iptalID;
    }

    public void setIptalID(int iptalID) {
        this.iptalID = iptalID;
    }

    public int getBiletID() throws SQLException {
        biletID = biletID();
        return biletID;
    }

    public void setVapurSay_id(int biletID) {
        this.biletID = biletID;
    }

    public int getMusteriBiletSay() {
        return musteriBiletSay;
    }

    public void setMusteriBiletSay(int musteriBiletSay) {
        this.musteriBiletSay = musteriBiletSay;
    }
    
    public int getYolcuSay() throws SQLException {
        yolcuSay = yolcuSay();
        return yolcuSay;
    }

    public void setYolcuSay(int yolcuSay) {
        this.yolcuSay = yolcuSay;
    }
    
     public int getSeferID() {
        return seferID;
    }

    public void setSeferID(int seferID) {
        this.seferID = seferID;
    }

    

    public int getKisiSayisi() {
        kisiSayisi = kisiSayisi();
        return kisiSayisi;
    }

    public void setKisiSayisi(int kisiSayisi) {
        this.kisiSayisi = kisiSayisi;
    }

    public int kisiSayisi() {

        kisiSayisi = (this.indirimli + this.tam);

        return kisiSayisi;

    }

    public String rezervasyon() {

        return "rezerv";
    }

    public String odeme() {

        return "odeme";
    }

    public double getUcret() {
        ucret = ucret();
        return ucret;
    }

    public void setUcret(double ucret) {
        this.ucret = ucret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getK_yeri() {
        return k_yeri;
    }

    public void setK_yeri(String k_yeri) {
        this.k_yeri = k_yeri;
    }

    public String getK_vakit() {
        return k_vakit;
    }

    public void setK_vakit(String k_vakit) {
        this.k_vakit = k_vakit;
    }

    public String getV_yeri() {
        return v_yeri;
    }

    public void setV_yeri(String v_yeri) {
        this.v_yeri = v_yeri;
    }

    public int getVpr_id() {
        return vpr_id;
    }

    public void setVpr_id(int vpr_id) {
        this.vpr_id = vpr_id;
    }

    public int getBos_koltuk() {
        return bos_koltuk;
    }

    public void setBos_koltuk(int bos_koltuk) {
        this.bos_koltuk = bos_koltuk;
    }

    public double getN_ucret() {
        return n_ucret;
    }

    public void setN_ucret(double n_ucret) {
        this.n_ucret = n_ucret;
    }

    public double getI_ucret() {
        return i_ucret;
    }

    public void setI_ucret(double i_ucret) {
        this.i_ucret = i_ucret;
    }

    public String getY_sure() {
        return y_sure;
    }

    public void setY_sure(String y_sure) {
        this.y_sure = y_sure;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
      public int getIs_id() {
        return is_id;
    }

    public void setIs_id(int is_id) {
        this.is_id = is_id;
    }

    public double ucret() {
        ucret = (this.n_ucret * this.tam) + (this.i_ucret * this.indirimli);
        return ucret;

    }
    
     public Map<String, Map<String, String>> getData() {
        return data;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getKalkis() {
        return kalkis;
    }

    public void setKalkis(String kalkis) {
        this.kalkis = kalkis;
    }

    public int getTam() {
        return tam;
    }

    public void setTam(int tam) {
        this.tam = tam;
    }

    public int getIndirimli() {
        return indirimli;
    }

    public void setIndirimli(int indirimli) {
        this.indirimli = indirimli;
    }

    public String getVaris() {
        return varis;
    }

    public void setVaris(String varis) {
        this.varis = varis;
    }

    public Map<String, String> getNerden() {
        return nerden;
    }

    public Map<String, String> getNereye() {
        return nereye;
    }

    public int bsay(int musteriID) throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }
        try {
            PreparedStatement object3 = connection.prepareStatement(
                    "SELECT SUM(KISILER) as TUT FROM BILET WHERE MUSTERI_ID=?  AND DURUM=1");
            object3.setInt(1, musteriID);
            ResultSet deger = object3.executeQuery();
            while (deger.next()) {
                return deger.getInt("TUT");
            }
            
            return 0;

        } finally {
            connection.close();
        }
    }
    
    public int rsay(int musteriID) throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }
        try {
            PreparedStatement object3 = connection.prepareStatement(
                    "SELECT SUM(KISILER) as TUT FROM BILET WHERE MUSTERI_ID=? AND DURUM=0");
            object3.setInt(1, musteriID);
            ResultSet deger = object3.executeQuery();
            while (deger.next()) {
                return deger.getInt("TUT");
            }
            
            return 0;

        } finally {
            connection.close();
        }
    }

    
    
    
    
    
    
    
    private int biletID() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }
        try {
            PreparedStatement object3 = connection.prepareStatement(
                    "SELECT MAX(ID) as TUT FROM BILET");

            ResultSet deger = object3.executeQuery();
            while (deger.next()) {
                return deger.getInt("TUT");
            }
            return 0;

        } finally {
            connection.close();
        }
    }
    
    
  
    
     

    private int yolcuSay() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }
        Connection connection = dataSource.getConnection();
        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }
        try {
            PreparedStatement object3 = connection.prepareStatement("SELECT SUM(KISILER) as TUT FROM BILET WHERE DURUM=1");

            ResultSet deger = object3.executeQuery();
            while (deger.next()) {
                return deger.getInt("TUT");
            }
            return 0;

        } finally {
            connection.close();
        }
    }

   
   

  

    

    @PostConstruct
    public void init() {
        nerden = new HashMap<String, String>();
        nereye = new HashMap<String, String>();
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
                    "SELECT K_YERI,V_YERI FROM SEFER");

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());
            while (resultSet1.next()) {

                nerden.put(resultSet1.getString("K_YERI"), resultSet1.getString("K_YERI"));
                nereye.put(resultSet1.getString("V_YERI"), resultSet1.getString("V_YERI"));
            }

            connection.close();
        } catch (Exception ex) {

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
            this.id = resultSet1.getInt("ID");
            this.k_yeri = resultSet1.getString("K_YERI");
            this.k_vakit = resultSet1.getString("K_VAKIT");
            this.v_yeri = resultSet1.getString("V_YERI");
            this.vpr_id = resultSet1.getInt("VPR_ID");
            this.bos_koltuk = resultSet1.getInt("BOS_KOLTUK");
            this.n_ucret = resultSet1.getDouble("N_UCRET");
            this.i_ucret = resultSet1.getDouble("I_UCRET");
            this.y_sure = resultSet1.getString("Y_SÜRE");
            
       
                            return "onayla";

            
            
        }
        finally {

            connection.close();
        }

    }

   

    public void onChange() {
        if (kalkis != null && !kalkis.equals("")) {
            try {
                if (dataSource == null) {
                    throw new SQLException("Kaynak Bulunamadı");
                }

                Connection connection = dataSource.getConnection();

                if (connection == null) {
                    throw new SQLException("Kaynağa Bağlanılamadı");
                }

                PreparedStatement object2 = connection.prepareStatement(
                        "SELECT V_YERI FROM SEFER where K_YERI=?");
                object2.setString(1, kalkis);
                CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
                resultSet1.populate(object2.executeQuery());
                Map<String, String> map = new HashMap<String, String>();
                while (resultSet1.next()) {
                    map.put(resultSet1.getString("V_YERI"), resultSet1.getString("V_YERI"));
                  
                }
                data.put(kalkis, map);

                connection.close();
            } catch (Exception ex) {

            }

            nereye = data.get(kalkis);
        } else {
            nereye = new HashMap<String, String>();
        }
    }

    public ResultSet getListe(String x, String y, String z,int a,int b) throws SQLException, ParseException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }
        int c=a+b;
        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT ID, K_VAKIT, K_YERI, V_YERI, Y_SÜRE, N_UCRET, I_UCRET, BOS_KOLTUK FROM SEFER where K_YERI=? and V_YERI=? and K_VAKIT=? and BOS_KOLTUK>=?");
            object2.setString(1, x);
            object2.setString(2, y);
            object2.setDate(3, tipcevir(z));
            object2.setInt(4, c);
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            return resultSet1;
        } finally {

            connection.close();
        }

    }

    public Date tipcevir(String tarih) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(tarih);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        return sqlDate;
    }

    public String check() throws SQLException {

        HttpSession session = SessionBean1.getSession();
        session.setAttribute("kalkis", kalkis);
        session.setAttribute("varis", varis);
        session.setAttribute("tarih", tarih);
        session.setAttribute("tam", tam);
        session.setAttribute("indirimli", indirimli);

        return "biletlerigoruntule";

    }

    public String rezervasyonuEkle(String tarih, int seferID, int musteriID, double ucret, int kisiSayisi) throws SQLException, ParseException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {

            PreparedStatement object1
                    = connection.prepareStatement("INSERT INTO BILET "
                            + "(TARIH,SEFER_ID,MUSTERI_ID, UCRET, DURUM, KISILER)"
                            + "VALUES ( ?, ?, ?, ?, ?, ?)");

            object1.setDate(1, tipcevir(tarih));
            object1.setInt(2, seferID);
            object1.setInt(3, musteriID);
            object1.setDouble(4, ucret);
            object1.setInt(5, 0);
            object1.setInt(6, kisiSayisi);
            object1.executeUpdate();

            PreparedStatement object2 = connection.prepareStatement("UPDATE SEFER SET BOS_KOLTUK = BOS_KOLTUK - ? WHERE ID=?");
            object2.setInt(1, kisiSayisi);
            object2.setInt(2, seferID);
            object2.executeUpdate();
            out();
            return "biletbilgileri";
        } finally {
            connection.close();
        }
    }

    public String odemeYap(String tarih, int seferID, int musteriID, double ucret, int kisiSayisi) throws SQLException, ParseException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {

            PreparedStatement object1
                    = connection.prepareStatement("INSERT INTO BILET "
                            + "(TARIH,SEFER_ID,MUSTERI_ID, UCRET, DURUM, KISILER)"
                            + "VALUES ( ?, ?, ?, ?, ?,?)");

            object1.setDate(1, tipcevir(tarih));
            object1.setInt(2, seferID);
            object1.setInt(3, musteriID);
            object1.setDouble(4, ucret);
            object1.setInt(5, 1);
            object1.setInt(6, kisiSayisi);
            object1.executeUpdate();

            PreparedStatement object2 = connection.prepareStatement("UPDATE SEFER SET BOS_KOLTUK = BOS_KOLTUK - ? WHERE ID=?");
            object2.setInt(1, kisiSayisi);
            object2.setInt(2, seferID);
            object2.executeUpdate();
            out();
            return "biletbilgileri"; // 
        } finally {
            connection.close();
        }

    }

    public ResultSet getBiletler() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT b.TARIH, b.MUSTERI_ID, b.SEFER_ID, b.UCRET, b.DURUM, b.KISILER, s.K_YERI, s.V_YERI, s.Y_SÜRE FROM BILET b INNER JOIN SEFER s ON b.SEFER_ID = s.ID WHERE b.DURUM=1  ORDER BY b.TARIH DESC");

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            return resultSet1;
        } finally {

            connection.close();
        }

    }
    
    public ResultSet getRezervasyonlariGoster(int musteriID) throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT b.ID, b.TARIH, b.MUSTERI_ID, b.SEFER_ID, b.UCRET, b.DURUM, b.KISILER, s.K_YERI, s.V_YERI, s.Y_SÜRE FROM BILET b INNER JOIN SEFER s ON b.SEFER_ID = s.ID WHERE b.MUSTERI_ID=? AND b.DURUM=0 ORDER BY b.TARIH DESC");
            
            object2.setInt(1, musteriID);
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            return resultSet1;
        } finally {

            connection.close();
        }

    }
    
    
    
    
    public ResultSet getMusteriBiletleri(int musteriID) throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT b.ID, b.TARIH, b.MUSTERI_ID, b.SEFER_ID, b.UCRET, b.DURUM, b.KISILER, s.K_YERI, s.V_YERI, s.Y_SÜRE FROM BILET b INNER JOIN SEFER s ON b.SEFER_ID = s.ID WHERE b.MUSTERI_ID=? AND b.DURUM=1 ORDER BY b.TARIH DESC");
            
            object2.setInt(1, musteriID);
            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            return resultSet1;
        } finally {

            connection.close();
        }

    }

    public ResultSet getRezervasyonGoster() throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {
            PreparedStatement object2 = connection.prepareStatement(
                    "SELECT b.ID, b.TARIH, b.MUSTERI_ID, b.SEFER_ID, b.UCRET, b.DURUM, b.KISILER, s.K_YERI, s.V_YERI, s.Y_SÜRE FROM BILET b INNER JOIN SEFER s ON b.SEFER_ID = s.ID WHERE b.DURUM=0 ORDER BY b.TARIH DESC");

            CachedRowSet resultSet1 = new com.sun.rowset.CachedRowSetImpl();
            resultSet1.populate(object2.executeQuery());

            return resultSet1;
        } finally {

            connection.close();
        }

    }
    
    public String iptalBiletKullanici(int iptalID, int kisiSayisi, int seferID) throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {

            PreparedStatement object2 = connection.prepareStatement("UPDATE SEFER SET BOS_KOLTUK = BOS_KOLTUK + ? WHERE ID=?");
            object2.setInt(1, kisiSayisi);
            object2.setInt(2, seferID);

            object2.executeUpdate();

            PreparedStatement myObject
                    = connection.prepareStatement("delete from BILET where id=?");

            myObject.setInt(1, iptalID);
            myObject.executeUpdate();

            return "musteri";
        } finally {
            connection.close();
        }
    }

    public String iptalBilet(int iptalID, int kisiSayisi, int seferID) throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {

            PreparedStatement object2 = connection.prepareStatement("UPDATE SEFER SET BOS_KOLTUK = BOS_KOLTUK + ? WHERE ID=?");
            object2.setInt(1, kisiSayisi);
            object2.setInt(2, seferID);

            object2.executeUpdate();

            PreparedStatement myObject
                    = connection.prepareStatement("delete from BILET where id=?");

            myObject.setInt(1, iptalID);
            myObject.executeUpdate();

            return "yapilanRezervasyonlar";
        } finally {
            connection.close();
        }
    }

    public String onayBilet(int onaylaRID) throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }

        try {

            PreparedStatement object2 = connection.prepareStatement("UPDATE BILET SET DURUM=1 WHERE ID=?");
            object2.setInt(1, onaylaRID);

            object2.executeUpdate();

            return "yapilanRezervasyonlar";
        } finally {
            connection.close();
        }
    }
    
    
    
    public String guncelleMusteri() throws SQLException, ParseException
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
 myObject.setInt( 3, getTC() );
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
     public void out() {
        HttpSession session = SessionBean1.getSession();
        session.invalidate();
        
    }

}
