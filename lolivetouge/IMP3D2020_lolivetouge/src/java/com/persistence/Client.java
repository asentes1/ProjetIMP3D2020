/*
 * Projet  : imp3D
 * Fichier : Client.java
 * Description : Classe interface de la table Client
 * Cette table stocke les infos sur les Clients inscrit dans le logiciel
 */

package com.persistence;

import java.sql.*;

public class Client {
    private int       ID;           // clef primaire
    private String    Nom;
    private String    Prenom;
    private String    MotDePasse;
    private String    Mail;        // not null, unique
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param Nom
     * @param Prenom
     * @param MotDePasse
     * @param Mail
     * @return  un Client si le Nom est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le Nom est deja dans la BD
     * 
     */
    static public Client create(Connection con, String Nom, String Prenom,
                        String MotDePasse, String Mail)  throws Exception {
        Client Client = new Client(Nom, Prenom, MotDePasse, Mail);
        
        String queryString =
         "insert into Client (Nom, Prenom, MotDePasse, Mail) values ("
                + Utils.toString(Nom) + ", "
                + Utils.toString(Prenom) + ", "
                + Utils.toString(MotDePasse) + ", " 
                + Utils.toString(Mail)
            + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return Client;
    }
    
    /**
     * suppression de l'objet Client dans la BD par le mail unique
     * @param con
     * @return 
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from Client where Mail='" + Mail + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    /**
     * update de l'objet Client dans la ConnexionMySQL par le mail 
     * donc il ne peut pas changer son mail.
     * Le MotDePasse est déjà encrypté dans l'objet Client
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update Client set "
                + " Nom =" + Utils.toString(Nom) + "," 
                + " Prenom =" + Utils.toString(Prenom) + "," 
                + " MotDePasse =" + Utils.toString(MotDePasse)
                + " where Mail ='" + Mail + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * Retourne un Client trouve par son Mail
     * @param con
     * @param  ID clef primaire du Client à trouver
     * @return Client trouve par ID, null si ce mail n'existe pas encore
     * @throws java.lang.Exception
     */
    public static Client getByID(Connection con, int ID) throws Exception {
        String queryString = "select * from Client where ID='" + ID + "'";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        if (lResult.next()) {
            return creerParRequete(lResult);
        }
        else
            return null;
    }
    
    /**
     * Retourne un Client trouve par son Mail
     * @param con
     * @param  Mail du Client à trouver
     * @return Client trouve par MotDePasse, null si ce mail n'existe pas encore
     * @throws java.lang.Exception
     */
    public static Client getByMail(Connection con, String Mail) throws Exception {
        String queryString = "select * from Client where Mail='" + Mail + "'";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        if (lResult.next()) {
            return creerParRequete(lResult);
        }
        else
            return null;
    }
    
    private static Client creerParRequete(ResultSet result) throws Exception {
        String    lNom  = result.getString("Nom");
        String    lPrenom  = result.getString("Prenom");
        String    lMotDePasse = result.getString("MotDePasse");
        String    lMail = result.getString("Mail");
        return    new Client(lNom, lPrenom, lMotDePasse,lMail);
    }
    
    /**
     * Cree et initialise completement un client
     */
    private Client(String Nom, String Prenom, String MotDePasse, String Mail) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.MotDePasse = MotDePasse;
        this.Mail = Mail;
    }
    
    public int getID() {
        return  ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getPrenom() {
        return Prenom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }

    public String getMotDePasse() {
        return MotDePasse;
    }

    public void setMotDePasse(String MotDePasse) {
        this.MotDePasse = MotDePasse;
    }

    public String getMail() {
        return Mail;
    }

    // --------------------- les assesseurs ----------------------------
    public void setMail(String Mail) {    
        this.Mail = Mail;
    }

    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Nom =  " + Nom + "\t" +
                " Prenom = " + Prenom + "\t" +
                " MotDePasse = " + MotDePasse + "\t" +
                " Mail = " + Mail + " ";
    }
}