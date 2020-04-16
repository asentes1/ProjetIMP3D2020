/*
 * Projet  : imp3D
 * Fichier : Imprimante3d.java
 * Description : Classe interface de la table Imprimante3d
 */
package com.persistence;

import java.sql.*;
import java.util.ArrayList;
import com.persistence.Utils.*;

public class Imprimante3d {
    private String Nom; 
    private String FablabNom; 
    private String Etat;
    private double NbHeuresDeTravail;
    private int CoutHoraire;  
    private int AmbianceID;

    /**
     * Créer un nouvelle imprimante
     *
     * @param con
     * @param Nom
     * @param FablabNom
     * @param Etat
     * @param NbHeuresDeTravail
     * @param CoutHoraire
     * @param AmbianceID
     * @return retourne une Imprimante3d
     * @throws Exception impossible d'accéder à la ConnexionMySQL 
     *                   ou le numero l'immatriculation est deja dans la BD
     */
    static public Imprimante3d create(Connection con, String Nom, String FablabNom,
        String Etat, double NbHeuresDeTravail, int CoutHoraire, int AmbianceID) throws Exception {
        Imprimante3d imprimante = new Imprimante3d(Nom, FablabNom, Etat, NbHeuresDeTravail, CoutHoraire, AmbianceID);

        String queryString =
          "insert into Imprimante3d (Nom, FablabNom, Etat, NbHeuresDeTravail, CoutHoraire, AmbianceID) values ("
                + Utils.toString(Nom) + ", "
                + Utils.toString(FablabNom) + ", "
                + Utils.toString(Etat) + ", "
                + Utils.toString(NbHeuresDeTravail) + ", "
                + Utils.toString(CoutHoraire) + ", "
                + Utils.toString(AmbianceID)
            + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return imprimante;
    }

    /**
     * update de l'objet Imprimante3d dans la ConnexionMySQL
     *
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
            "update Imprimante3d set "
                + " Nom =" + Utils.toString(Nom) + ","
                + " FablabNom =" + Utils.toString(FablabNom) + ","
                + " Etat =" + Utils.toString(Etat) + ","
                + " NbHeuresDeTravail =" + Utils.toString(NbHeuresDeTravail) + ","
                + " CoutHoraire =" + Utils.toString(CoutHoraire) + ","
                + " AmbianceID =" + Utils.toString(AmbianceID);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * suppression de l'objet Imprimante3d dans la BD
     *
     * @param con
     * @return
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from Imprimante3d"
                                + " where Nom=" + Utils.toString(Nom);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
        
    public static ArrayList<Imprimante3d> getList (Connection con) throws Exception {
        String queryString = "select * from Imprimante3d";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<Imprimante3d> Imprimante3ds = new ArrayList<>();
        while (lResult.next ()) {
            Imprimante3ds.add (creerParRequete (lResult));
        }
        return Imprimante3ds;
    }
    
    public static Imprimante3d getLast(Connection con) throws Exception {
        String queryString = "select * from Imprimante3d order by id desc limit 1";
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        if (lResult.next()) {
            return (creerParRequete(lResult));
        }
        return null;
    }

    /*
        retourne l'ID de imprimante concernée
    */
    public  Imprimante3d getByID(Connection con, int ID) throws Exception {
        String queryString = "select * from Imprimante3d where ID=" + ID;
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE,
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next()) {
            return creerParRequete (lResult);
        }
        else {
            return null;
        }
    }

    /*
        retourne l'ID de imprimante concernée
    */
    public int getIDByNom(Connection con) throws Exception {
        String queryString = "select ID from Imprimante3d where Nom=" + Utils.toString(Nom);
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
     * Indique le nb de Imprimante3ds dans la base de données
     * @param con
     * @return le nombre de Imprimante3ds
     * @throws java.lang.Exception
     */
    public static int size(Connection con) throws Exception {
        String queryString = "select count(*) as count from Imprimante3d";
        Statement lStat = con.createStatement(
                                            ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                            ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next())
            return (lResult.getInt("count"));
        else 
            return 0;
    }
    
    private static Imprimante3d creerParRequete(ResultSet result) throws Exception {
        String lNom= result.getString("Nom");
        String lFablabNom = result.getString("FablabNom");
        String lEtat = result.getString("Etat");
        double lNbHeuresDeTravail = result.getDouble("NbHeuresDeTravail");
        int lCoutHoraire = result.getInt("CoutHoraire");
        int lAmbianceID = result.getInt("AmbianceID");
        return new Imprimante3d(lNom, lFablabNom, lEtat, lNbHeuresDeTravail, lCoutHoraire, lAmbianceID);
    }

    /**
     * Cree et initialise completement Vehicule
     */
    private Imprimante3d(String Nom, String FablabNom, String Etat, 
            double NbHeuresDeTravail, int CoutHoraire, int AmbianceID) {
        this.Nom = Nom;
        this.FablabNom = FablabNom;
        this.Etat = Etat;
        this.NbHeuresDeTravail = NbHeuresDeTravail;
        this.CoutHoraire = CoutHoraire;
        this.AmbianceID = AmbianceID;
    }

    // --------------------- les assesseurs ----------------------------
   
    /**
     * toString() operator overload
     *
     * @return the result string
     */
    @Override
    public String toString() {
        return " Nom = " + Utils.toString(Nom) + "\t"
            + " FablabNom = " + Utils.toString(FablabNom)
            + " Etat = " + Utils.toString(Etat)
            + " NbHeuresDeTravail = " + Utils.toString(NbHeuresDeTravail)
            + " CoutHoraire = " + Utils.toString(CoutHoraire)
            + " AmbianceID = " + Utils.toString(AmbianceID);
    }
    
    //setters
    
    
    public void setNom(String Nom){
        this.Nom = Nom;
    }
    
    public void setFablabNom(String FablabNom){
        this.FablabNom = FablabNom;
    }
    
    public void setEtat(String Etat){
        this.Etat = Etat;
    }
    
    public void setNbHeuresDeTravail(double NbHeuresDeTravail){
        this.NbHeuresDeTravail = NbHeuresDeTravail;
    }
    
    public void setCoutHoraire(int CoutHoraire){
        this.CoutHoraire = CoutHoraire;
    }
    
    public void setAmbianceID(int AmbianceID){
        this.AmbianceID = AmbianceID;
    }
    
    
    
    
    //getters
    
    public String getNom(){
        return Nom;
    }
    
    public String getFablabNom(){
        return FablabNom;
    }
    
    public String getEtat(){
        return Etat;
    }
    
    public double getNbHeuresDeTravail(){
        return NbHeuresDeTravail;
    }
    
    public int getCoutHoraire(){
        return CoutHoraire;
    }
    
    public int getAmbianceID(){
        return AmbianceID;
    }
    
}
