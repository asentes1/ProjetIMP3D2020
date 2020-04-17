package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Saisie_005fdu_005fmail_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Inscription d'un utilisateur</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <script src=\"http://localhost:8084/imp3Dgit/test_envoi_mail.jsp\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"test_envoi_mail.jsp\"></script>\n");
      out.write("        <script>\n");
      out.write("            //que des cars alphanumérique \n");
      out.write("            $.validator.addMethod(\"textOnly\", \n");
      out.write("                function(value, element) {  // un car n'est pas alphanumérique\n");
      out.write("                    return !/[^a-zA-Z0-9]/.test(value);\n");
      out.write("                }, \"Que des caractères alphanumériques.\"\n");
      out.write("            );\n");
      out.write("        </script>   \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <div id=\"titre\" class=\"info\">Projet de supervision imp 3D</div>\n");
      out.write("        </header>\n");
      out.write("        <section>\n");
      out.write("            Saisie du mail d'un client\n");
      out.write("            <form method=\"get\" action=\"test_envoi_mail.jsp\">\n");
      out.write("                <div data-role=\"fieldcontain\">\n");
      out.write("                <label for=\"inscriptionEMail\" data-theme=\"a\">EMail:</label>\n");
      out.write("                <input type=\"email\" name=\"inscriptionEMail\" id=\"inscriptionEMail\"\n");
      out.write("                      data-theme=\"a\" placeholder=\"Votre EMail\" /> \n");
      out.write("                </div>\n");
      out.write("                <a herf=\"#test_envoi_mail.jsp\"><button>Valider</button></a>\n");
      out.write("            </form>\n");
      out.write("        </section>\n");
      out.write("        <footer> \n");
      out.write("            <div id= \"pied\" class=\"info\">BTS SNIR - 2020</div>\n");
      out.write("        </footer>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
