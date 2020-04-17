<%-- 
    Document   : ajax_jobDelete.jsp
    Description: page de la suppression dans la BD d'un job
    Created on : Mai 2019
    Author     : Fabre
--%>

<%@page import="java.sql.Timestamp"%>
<%@page import="com.metier.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.persistence.*"%>
<%@page pageEncoding="UTF-8"%>

<%
    Connection con = (Connection) session.getAttribute("con");
    if (con == null) {
        con = ConnexionMySQL.newConnexion();
        session.setAttribute("con", con);
    }
    Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");
    Operateur operateur = (Operateur) session.getAttribute("operateur");
    // une personne autorisée est connecté ?
    if ((operateur != null) || (utilisateur != null)) {
        String sDate = request.getParameter("date").substring(18);
        Timestamp date = Utils.string2ToTimestamp(sDate);
        Job job = Job.getByDate(con, date);
        // le job existe t'il ?
        if (job != null) {
            job.delete(con);
            out.print("Suivi supprimé OK");
        }
        else {
            out.print("Suppression impossible !");
        }
    }
%>
