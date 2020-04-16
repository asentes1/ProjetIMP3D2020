/*
 * Projet  : imp3D
 * Fichier : Operateur.java
 * Description : Classe interface de la table Operateur
 * Cette table stocke les infos e l'opérateur connu du logiciel
 * on ne peut pas le supprimer
 */

package com.persistence;

import java.sql.*;
        
public class Operateur {
    private String    Nom;              // non null
    private String    Prenom;           // non null
    private String    MotDePasse;        // non null
    private String    Mail;             // non null, unique
    
    /**
     * Créer un nouvel operateur persistant 
     * @param con
     * @param Nom
     * @param Prenom
     * @param MotDePasse    déjà encrypté
     * @param Mail
     * @return 
     * @ return retourne un Operateur si le Mail est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le Mail est deja dans la BD
     * 
     */
    static public Operateur create(Connection con, String Nom,
            String Prenom, String MotDePasse, String Mail)  throws Exception {
        Operateur Operateur = new Operateur(Nom, Prenom, MotDePasse, Mail);
        
        String queryString =
         "insert into Operateur(Nom, PreNom, MotDePasse, Mail) values ("
                + Utils.toString(Nom) + ", " 
                + Utils.toString(Prenom) + ", " 
                + Utils.toString(MotDePasse) + ", " 
                + Utils.toString(Mail)
            + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return Operateur;
    }
    
    /**
     * update de l'objet Operateur dans la BD par le mail
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update Operateur set "
                + " Nom =" + Utils.toString(Nom) + ","
                + " PreNom =" + Utils.toString(Prenom) + "," 
                + " MotDePasse =" + Utils.toString(MotDePasse)
                + " where Mail =" + Utils.toString(Mail);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    
    /**
     * suppression de l'objet Job par le client et le nom du job
     * @param con
     * @return 
     * @throws SQLException    impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from Operateur where Mail = " + Utils.toString(Mail);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    // un seul opérateur pour l'instant (ca peut évoluer)
    public static Operateur getByID(Connection con, int ID) throws Exception {
        String queryString = "select * from Operateur where ID = " + ID;
        Statement lStat = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next ()) {
            return creerParRequete (lResult);
        }
        return null;
    }
    
    /*
        retourne l'ID d'un objet d'après son mail
    */
    public int getID(Connection con) throws Exception {
        String queryString = "select ID from Operateur"
            + "  where Mail = " + Utils.toString(Mail);
        Statement lStat = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next()) {
            return lResult.getInt("ID");
        }
        else {
            return 0;
        }
    }
    
    /**
     * Indique le nb de Loueur dans la base de données
     * @param con
     * @return le Nombre de Loueur
     * @throws java.lang.Exception
     */
    public static int size(Connection con) throws Exception {
        String queryString = "select count(*) as count from Operateur";
        Statement lStat = con.createStatement(
                                            ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                            ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next())
            return (lResult.getInt("count"));
        else 
            return 0;
    }
    
    private static Operateur creerParRequete(ResultSet result) throws Exception {
        String    lNom  = result.getString("Nom");
        String    lPreNom = result.getString("PreNom");
        String    lMotDePasse = result.getString("MotDePasse");
        String    lMail = result.getString("Mail");
        return new Operateur(lNom,lPreNom, lMotDePasse, lMail);
    }
    
    /**
     * Cree et initialise completement Operateur
     */
    private Operateur(String Nom, String Prenom, String MotDePasse, String Mail) {
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.MotDePasse = MotDePasse;
        this.Mail = Mail;
    }

    // --------------------- les assesseurs ----------------------------
    public String getNom() {
        return Nom;
    }

    public String getPreNom() {
        return Prenom;
    }
    public String getMotDePasse() {
        return MotDePasse;
    }

    public String getMail() {
        return Mail;
    }

    public void setMotDePasse(String MotDePasse) throws Exception {
        this.MotDePasse = MotDePasse;
    }

    public void setMail(String Mail) throws Exception {
        this.Mail = Mail;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public void setPrenom(String Prenom) {
        this.Prenom = Prenom;
    }
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Nom =  " + Nom + "\t" +
                " PreNom = " + Utils.toString(Prenom) + 
                " MotDePasse = " + Utils.toString(MotDePasse) + 
                " Mail = " + Utils.toString(Mail)
                + " ";
    }
}
