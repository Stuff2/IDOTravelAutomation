

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.sql.Date;

import javax.faces.application.FacesMessage;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "MAloginBean")
@SessionScoped
public class MAlogin implements Serializable {
    private int id;
    private String isim;
    private String s_isim;
    private int TC;
    private Date dtarih;
    private String eposta;
    private String sifre;
    private static final long serialVersionUID = 1094801825228386363L;

    DataSource dataSource;

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
    

    public MAlogin() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("jdbc/IDO");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String check() throws SQLException {
        boolean valid = validate(eposta, sifre);
        if (valid) {
            HttpSession session = MSessionBean.getSession();
            session.setAttribute("isim", isim);
            session.setAttribute("s_isim", s_isim);
            session.setAttribute("TC", TC);
            session.setAttribute("dtarih", dtarih);
            session.setAttribute("eposta", eposta);
            session.setAttribute("sifre", sifre);
            session.setAttribute("id", id);


            return "musteri";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Hatalı Eposta yada Şifre!",
                            "Lütfen eposta ve şifrenizi doğru giriniz!"));
            return "hata";
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

            ps = connection.prepareStatement("Select id, isim, s_isim, tc, d_tarih, eposta, sifre from musteri where eposta = ? and sifre = ?");
            ps.setString(1, user);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                this.id = rs.getInt("id");//result found, means valid inputs
                this.isim = rs.getString("isim");//result found, means valid inputs
                this.s_isim = rs.getString("s_isim");//result found, means valid inputs
                this.TC = rs.getInt("TC");//result found, means valid inputs
                this.dtarih = rs.getDate("d_tarih");//result found, means valid inputs
                this.eposta = rs.getString("eposta");//result found, means valid inputs
                this.sifre = rs.getString("sifre");//result found, means valid inputs

                return true;
            }
        } catch (SQLException ex) {
            System.out.println("Login error -->" + ex.getMessage());
            return false;
        } finally {
            connection.close();
        }
        return false;
    }

    //logout event, invalidate session

    public String logout() {
        HttpSession session = MSessionBean.getSession();
        session.invalidate();
        return "index";
    }
}
