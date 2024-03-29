import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
//@WebFilter(filterName = "AuthFilter2", urlPatterns = {"/faces/index/musteri.xhtml"})
@WebFilter({"/faces/index/musteri.xhtml","/faces/index/onayla.xhtml","/faces/index/biletlerigoruntule.xhtml","/faces/index/rezerv.xhtml","/faces/index/odeme.xhtml"})
public class MAuthorizationFilter implements Filter {

 
    public MAuthorizationFilter() {
    }
 
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
 
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {
 
            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);
            
            String reqURI = reqt.getRequestURI();
            if (reqURI.indexOf("/musteriGirisi.xhtml") >= 0
                    || (ses != null && ses.getAttribute("eposta") != null)
                    || reqURI.indexOf("/public/") >= 0
                    || reqURI.contains("javax.faces.resource"))
                chain.doFilter(request, response);
            else
                resp.sendRedirect(reqt.getContextPath() + "/faces/index/musteriGirisi.xhtml");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
 
    @Override
    public void destroy() {
 
    }
}