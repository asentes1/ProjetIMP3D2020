package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.persistence.*;
import java.sql.Connection;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/includes/header.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"fr\">\n");
      out.write("    <head>\n");
      out.write("        <title>Acceuil</title> \n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, minimum-scale=1, maximum-scale=1\">\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" href=\"js/jquery.mobile/jquery.mobile-1.4.5.min.css\">\n");
      out.write("\n");
      out.write("<link rel=\"stylesheet\" href=\"css/themes/jquery.mobile.icons.min.css\" />\n");
      out.write("<link rel=\"stylesheet\" href=\"css/imp3Dtheme.css\">\n");
      out.write("<link rel=\"stylesheet\" href=\"css/imp3D.css\">\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery/jquery-1.11.2.min.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"js/jquery.mobile/jquery.mobile-1.4.5.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div data-role=\"page\" id=\"page1\">\n");
      out.write("            <div class=\"header\" data-role=\"header\" data-theme=\"b\" data-position=\"fixed\">\n");
      out.write("                <h1>Accueil</h1>\n");
      out.write("                <p></p>\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            <div role=\"main\" class=\"ui-content\"><br/><br/><br/>\n");
      out.write("                <center>\n");
      out.write("                    <h2>Suivi d'impression 3D au FabLab<br/>\n");
      out.write("                        du Lycée international Victor Hugo de Colomiers</h2>\n");
      out.write("                    <br/><br/>\n");
      out.write("                    <div class=\"logo\"><img src=\"images/imp3D.png\" /></div>  \n");
      out.write("                    <br/>\n");
      out.write("                    <div>\n");
      out.write("                        ");

                            String message = request.getParameter("message");
                            if (message != null) {
                                if (message.equalsIgnoreCase("pbLogin")) {
                                    out.print("Vérifiez l'identifiant ou le mot de passe");
                                }
                            }
                        
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    <form id=\"formLogin\" class=\"form\" method=\"post\" action=\"login_req.jsp\">\n");
      out.write("                        <div data-role=\"fieldcontain\">\n");
      out.write("                            <label for=\"mail\">Adresse mail :</label>\n");
      out.write("                            <input type=\"text\" name=\"mail\" id=\"mail\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div data-role=\"fieldcontain\">\n");
      out.write("                            <label for=\"mdp\">Mot de passe :</label>\n");
      out.write("                            <input type=\"password\" name=\"mdp\" id=\"mdp\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <input name=\"action\" id=\"login\" type=\"hidden\" data-theme=\"b\" value=\"login_req\"/><br/>\n");
      out.write("                        <button type=\"submit\" id=\"submitOK\" name=\"submitOK\">OK</button>\n");
      out.write("                    </form>\n");
      out.write("                    <p class=\"mini\">V0.3 - Mai 2019<br/>Développement : BTS SNIR Lycée V.Hugo Colomiers</p>\n");
      out.write("                </center>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
