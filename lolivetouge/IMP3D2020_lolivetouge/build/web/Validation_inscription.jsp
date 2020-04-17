<%-- 
    Document   : Validation_inscription
    Created on : 7 avr. 2020, 17:35:55
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
    String eMail = request.getParameter("inscriptionEMail");
    Client.create(con, "text-1", "text-2", "url-1", eMail);
    Inscription uneInscription = (Inscription) Inscription.getByMail(con, eMail);
    uneInscription.delete(con);
%>
