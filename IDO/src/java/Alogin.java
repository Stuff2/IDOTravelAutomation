
import com.sun.rowset.internal.Row;
import javax.faces.bean.ManagedBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;

import javax.sql.rowset.CachedRowSet;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.Serializable;

import javax.faces.application.FacesMessage;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "AloginBean")
@SessionScoped
public class Alogin implements Serializable {

    private String usr;
    private String pwd;
    private String gorev;
    private String isim;
    private String s_isim;
    private int TC;
    private String eposta;
    private static final long serialVersionUID = 1094801825228386363L;

    DataSource dataSource;

    public String getUsr() {
        return usr;
    }

    public void setUsr(String usr) {
        this.usr = usr;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Alogin() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/IDO");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String check() throws SQLException {
        boolean valid = validate(usr, pwd);
        if (valid) {
            HttpSession session = SessionBean.getSession();
            session.setAttribute("username", usr);
            session.setAttribute("gorev", gorev);
            session.setAttribute("isim", isim);
            session.setAttribute("s_isim", s_isim);
            session.setAttribute("eposta", eposta);
            session.setAttribute("TC", TC);
            return "index";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }

    public boolean validate(String user, String password) throws SQLException {

        if (dataSource == null) {
            throw new SQLException("Kaynak Bulunamadı");
        }

        Connection connection = dataSource.getConnection();

        if (connection == null) {
            throw new SQLException("Kaynağa Bağlanılamadı");
        }
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement("Select eposta,tc,sifre,gorev,isim,s_isim from personel where eposta = ? and sifre = ? and GOREV=?");
            ps.setString(1, user);
            ps.setString(2, password);
             ps.setString(3, "Admin");

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.gorev = rs.getString("gorev");//result found, means valid inputs
                this.isim = rs.getString("isim");//result found, means valid inputs
                this.s_isim = rs.getString("s_isim");//result found, means valid inputs
                this.TC = rs.getInt("TC");//result found, means valid inputs
                this.eposta = rs.getString("eposta");//result found, means valid inputs

                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Giris  -->" + ex.getMessage());
            return false;
        } finally {
            connection.close();
        }
        return false;
    }

    //logout event, invalidate session

    public String logout() {
        HttpSession session = SessionBean.getSession();
        session.invalidate();
        return "login";
    }
}
