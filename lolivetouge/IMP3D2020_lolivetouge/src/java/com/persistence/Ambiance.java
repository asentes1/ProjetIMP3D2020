/**
    Document    : Ambiance.java
    Description : Classe d'interface de la table Ambiance
    Created on  : Mars 2019
    Author      : Vraux
*/
package com.persistence;

import java.sql.*;

/**
 *
 * @author snir2g2
 */
public class Ambiance {
    //Déclaration des attributs de la classe Ambiance
    private double temperature;
    private double humidite;
    private Timestamp date;
    //Constructeur de la classe
    public Ambiance(double temperature, double humidite, Timestamp date) {
        this.temperature = temperature;
        this.humidite = humidite;
        this.date = date;
    }

    /**
     * Créer un nouvel objet persistant
     *
     * @param con
     * @param ID
     * @param fabnom
     * @param temperature
     * @param humidite
     * @param date // datation
     * @return
     * @ return une ambiance
     * @throws Exception impossible d'accéder à la ConnexionMySQL ou la date est
     * deja dans la BD
     *
     */
    
    static public Ambiance create(Connection con, String fabnom,
            double temperature, double humidite, Timestamp date) throws Exception {
        Ambiance jour = new Ambiance(temperature, humidite, date);

        String queryString
                = "insert into Ambiance (FabLabNom,Temperature,Humidite,Datation) "
                + " values ("
                + Utils.toString(fabnom) + ", "
                + Utils.toString(temperature) + ", "
                + Utils.toString(humidite) + ", "
                + Utils.toString(date)
                + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.RETURN_GENERATED_KEYS);
        return jour;
    }

    /**
     * suppression de l'objet ambiance dans la BD
     *
     * @param con
     * @return
     * @throws SQLException impossible d'accéder à la ConnexionMySQL
     */
    
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from Ambiance where Datation='" + date + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }

    /**
     * update de l'objet user dans la ConnexionMySQL
     *
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString
                = "update Ambiance set "
                + " Temperature =" + Utils.toString(temperature) + ","
                + " Humidite =" + Utils.toString(humidite) + ","
                + " Datation =" + Utils.toString(date)
                + " where Datation ='" + date + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.RETURN_GENERATED_KEYS);
    }

    /**
     * Retourne un user trouve par son pseudo, saved is true
     *
     * @param con
     * @param date
     * @return user trouve par mail
     * @throws java.lang.Exception
     */
    public static Ambiance getByDate(Connection con, Timestamp date) throws Exception {
        String queryString = "select * from Ambiance where Datation='" + date + "'";
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        if (lResult.next()) {
            return creerParRequete(lResult);
        } else {
            return null;
        }
    }

    private static Ambiance creerParRequete(ResultSet result) throws Exception {
        double ltemp = result.getDouble("Temperature");
        double lhum = result.getDouble("Humidite");
        Timestamp lDate = result.getTimestamp("Datation");

        return new Ambiance(ltemp, lhum, lDate);
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidite() {
        return humidite;
    }

    public Timestamp getDate() {
        return date;
    }

    /**
     * Retourne une ambiance trouve par id
     *
     * @param con
     * @param id
     * @return contrat trouvé par id
     * @throws java.lang.Exception
     */
}
