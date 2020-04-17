<%-- 
    Document   : reqInscription
    Created on : 31 mars 2020, 08:49:05
    Author     : Lorenzo
--%>
<%@page import="com.metier.*"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.persistence.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%><!DOCTYPE html>

<%
    Connection con = (Connection) session.getAttribute("con");
    if (con == null) {
        con = ConnexionMySQL.newConnexion();
        session.setAttribute("con", con);
    }
    String eMail    = request.getParameter("inscriptionEMail");
    Inscription.create(con, eMail, Utils.getDateDuJour());
    boolean ok = SmtpSend.sendMessage("Validation de la page d'inscription", "Bonjour,\nSuite à votre inscription, nous vous envoyons ci-dessous un lien vers le site de l'imprimante 3D.\nhttp://localhost:8084/imp3Dgit/formulaire-nouveau-client.html\nAprès avoir rempli le formulaire d'inscription, vous pourrez nous transmettre les pièces que vous souhaitez imprimer.", eMail);
%>