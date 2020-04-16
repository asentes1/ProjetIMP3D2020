/*
 * Projet  : imp3D
 * Fichier : Inscription.java
 * Description : Classe interface de la table Inscription
 * Cette table stocke les mail en attente de confirmation
 */
package com.persistence;

import java.sql.*;

public class Inscription {
    private String Mail;
    private Timestamp DateInscription;

    /**
     * Créer un nouvel objet persistant
     *
     * @param con
     * @param Mail
     * @param DateInscription
     * @return
     * @ return retourne une nouvelle inscription en attente
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    static public Inscription create(Connection con, String Mail, Timestamp DateInscription) throws Exception {
        // instanciation de l'objet avec les données initiales
        Inscription _Inscription = new Inscription(Mail, DateInscription);
        String queryString = "insert into Inscription(Mail, DateInscription) values (" 
                + Utils.toString(Mail) + ", "
                + Utils.toString(DateInscription) + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return _Inscription;
    }

    // Ps de méthode save(), car on ne modifie jamais une inscription
    // on la crée puis on la supprime, c'eqst tout
    
    /**
     * suppression de l'inscription dans la BD par le mail
     * 
     * @param con
     * @return
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from Inscription where Mail=" + Utils.toString(Mail);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    /**
     * Indique l'inscription la plus ancienne ds la BD
     * @param con
     * @return la première inscription
     * @throws java.lang.Exception
     */
    public static Inscription getFirst(Connection con) throws Exception {
        String queryString = "select * from Inscription order by id limit 1";
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        if (lResult.next()) {
            return creerParRequete(lResult);
        }
        return null;
    }

    /**
     * Indique la dernière inscription ds la BD
     * @param con
     * @return la dernière inscription
     * @throws java.lang.Exception
     */
    public static Inscription getLast(Connection con) throws Exception {
        String queryString = "select * from Inscription order by id desc limit 1";
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        if (lResult.next()) {
            return creerParRequete(lResult);
        }
        return null;
    }
    
    /**
     * Indique l'inscription ds la BD par le mail
     * @param con
     * @return l'inscription d'un mail donné (unique)
     * @throws java.lang.Exception
     */
    public static Inscription getByMail(Connection con, String Mail) throws Exception {
        String queryString = "select * from Inscription where Mail=" + Utils.toString(Mail);
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        if (lResult.next()) {
            return creerParRequete(lResult);
        }
        return null;
    }

     /**
     * Indique le nb d'inscriptions en attente dans la base de données
     * @param con
     * @return le nombre de vehicules
     * @throws java.lang.Exception
     */
    public static int size(Connection con) throws Exception {
        String queryString = "select count(*) as count from Inscription";
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next())
            return (lResult.getInt("count"));
        else 
            return 0;
    }
     
    private static Inscription creerParRequete(ResultSet result) throws Exception {
        String lMail = result.getString("Mail");
        Timestamp lDateInscription = result.getTimestamp("DateInscription");
        return new Inscription(lMail, lDateInscription);
    }

    /**
     * Cree et initialise completement DonneesTR
     */
    private Inscription(String Mail, Timestamp DateInscription) {
        this.Mail = Mail;
        this.DateInscription = DateInscription;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public Timestamp getDateInscription() {
        return DateInscription;
    }

    // --------------------- les assesseurs ----------------------------
    public void setDateInscription(Timestamp DateInscription) {
        this.DateInscription = DateInscription;
    }

    /**
     * toString() operator overload
     *
     * @return the result string
     */
    @Override
    public String toString() {
        return " Mail = " + Mail + "\t"
                + " DateInscription = " + Utils.toString(DateInscription);
    }
}
