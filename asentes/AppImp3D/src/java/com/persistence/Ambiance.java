/*
 * Projet  : imp3D
 * Fichier : Ambiance.java
 * Description : Classe interface de la table Ambiance
 * Cette table stocke les température et hygrométrie sur les Ambiances du fabLab
 */

package com.persistence;

import java.sql.*;
import java.util.ArrayList;

public class Ambiance {
    private double  Temperature;
    private double  Humidite;
    private Timestamp Datation;
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param Temperature
     * @param Humidite
     * @param Datation
     * @ return retourne une ambiance
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     * 
     */
    static public Ambiance create(Connection con, double  Temperature,
            double  Humidite, Timestamp Datation)  throws Exception {
        
        Ambiance Ambiance = new Ambiance(Temperature, Humidite, Datation);
        
        String queryString =
         "insert into Ambiance (Temperature, Humidite, Datation) values ("
                + Utils.toString(Temperature) + ", " 
                + Utils.toString(Humidite) + ", " 
                + Utils.toString(Datation)
            + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return Ambiance;
    }
    
    /**
     * update les valeurs d'ambiance d'une date donnée (les dates sont uniques)
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update Ambiance set "
                + " Temperature =" + Utils.toString(Temperature) + "," 
                + " Humidite =" + Utils.toString(Humidite)
                + " where Datation = " + Utils.toString(Datation);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
        
    /**
     * suppression de l'objet loueur dans la BD
     * @param con
     * @return 
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from Ambiance where Datation=" + Utils.toString(Datation);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    
    // retourne le dernier de la table
    public static Ambiance getLast(Connection con) throws Exception {
        String queryString = "select * from Ambiance order by id desc limit 1";
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
    
    // retourne une collection de valeurs entre deux dates
    public static ArrayList<Ambiance> getBetweenDates(Connection con, 
                    String dateDepart, String dateFin) throws Exception {
        String queryString = "select * from Ambiance"
                + " where Datation between '" + dateDepart + " 00:00:00'"
                + " and '" + dateFin + " 23:59:59'"
                + " order by Datation";

        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<Ambiance> lstAmbiance = new ArrayList<>();
        // y en a t'il au moins un ?
        while (lResult.next()) {
            lstAmbiance.add(creerParRequete(lResult));
        }
        return lstAmbiance;
    }    
    
    /**
     * Indique le nb de valeurs dans la table Ambiance
     * @param con
     * @return le nombre valeurs historiques
     * @throws java.lang.Exception
     */
    public static int size(Connection con) throws Exception {
        String queryString = "select count(*) as count from Ambiance";
        Statement lStat = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next())
            return (lResult.getInt("count"));
        else 
            return 0;
    }
    
    private static Ambiance creerParRequete(ResultSet result) throws Exception {
            double    lTemperature = result.getDouble("Temperature");
            double    lHumidite = result.getDouble("Humidite");
            Timestamp lDatation = result.getTimestamp("Datation");
            return    new Ambiance(lTemperature, lHumidite, lDatation);
    }
    
    /**
     * Cree et initialise completement Loueur
     */
    private Ambiance(double Temperature, double  Humidite, Timestamp Datation) {
        this.Temperature = Temperature;
        this.Humidite = Humidite;
        this.Datation = Datation;
    }
    
    /* ----------------- assesseurs ----------------------- */

    public double getTemperature() {
        return Temperature;
    }

    public void setTemperature(double Temperature) {
        this.Temperature = Temperature;
    }

    public double getHumidite() {
        return Humidite;
    }

    public void setHumidite(double Humidite) {
        this.Humidite = Humidite;
    }

    public Timestamp getDatation() {
        return Datation;
    }

    public void setDatation(Timestamp Datation) {
        this.Datation = Datation;
    }

    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Temperature = " + Utils.toString(Temperature) + "\t" +
                " Humidite = " + Utils.toString(Humidite) + "\t" +
                " Datation = " + Utils.toString(Datation);
    }
}