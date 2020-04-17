package org.apache.jsp.maquette;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Test_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>TP JavaScript</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/cssSite.css\" />\n");
      out.write("        <script src=\"http://code.jquery.com/jquery-1.11.3.min.js\"></script>\n");
      out.write("        <script type=\"text/javascript\" src=\"Test.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <div id=\"titre\" class=\"info\">Contenu du TP Javascript</div>\n");
      out.write("        </header>\n");
      out.write("        <nav>Liens :\n");
      out.write("            <ul>\n");
      out.write("                <li><a href=\"http://google.fr\">Google</a></li>\n");
      out.write("                <li><a href=\"http://lemonde.fr\">Infos</a></li>\n");
      out.write("                <li><a href=\"http://victor-hugo-colomiers.entmip.fr\">LIVH</a></li>\n");
      out.write("            </ul>\n");
      out.write("        </nav>\n");
      out.write("        <section>\n");
      out.write("            Mise en oeuvre de script Javascript dans une page web en HTML 5\n");
      out.write("            <input id=\"monBouton\" type=\"button\" value=\"Répondre\"/>\n");
      out.write("        </section>\n");
      out.write("        <footer> \n");
      out.write("            <div id= \"pied\" class=\"info\">BTS SNIR - 2019</div>\n");
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
