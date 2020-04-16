/*
 * Projet  : imp3D
 * Fichier : Cartouche.java
 * Description : Classe interface de la table Cartouche
 */

package com.persistence;

import java.sql.*;
import java.util.ArrayList;

public class Cartouche {
    private String    Type;
    private boolean   EnUtilisation;
    private Timestamp DateRemplacement;
    private Timestamp DateFabrication;
    private String    IdentifiantType;
    private String    NumeroDeSerie;
    private double    QuantiteRestante;
    private int       CoutAuCm3;
    private int       Imprimante3dID;
    
    /**
     * Créer une nouvelle cartouche dans la BD
     * @param con
     * @param Type
     * @param EnUtilisation
     * @param DateRemplacement
     * @param DateFabrication
     * @param IdentifiantType
     * @param NumeroDeSerie
     * @param QuantiteRestante
     * @param CoutAuCm3
     * @param Imprimante3dID
     * @return 
     * @ return la cartouche créée
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou le numero est deja dans la BD
     * 
     */
    static public Cartouche create(Connection con, String Type, boolean EnUtilisation,
        Timestamp DateRemplacement, Timestamp DateFabrication, 
        String IdentifiantType, String NumeroDeSerie,
        double QuantiteRestante, int CoutAuCm3, int Imprimante3dID) throws Exception {
        Cartouche Cartouche = new Cartouche(Type, EnUtilisation, DateRemplacement, 
                DateFabrication, IdentifiantType, NumeroDeSerie, QuantiteRestante, CoutAuCm3, Imprimante3dID);
        
        String queryString =
         "insert into Cartouche (Type, EnUtilisation, DateRemplacement,"
            + " DateFabrication, IdentifiantType, NumeroDeSerie, QuantiteRestante,"
            + "  CoutAuCm3, Imprimante3dID) values ("
                + Utils.toString(Type) + ", " 
                + Utils.toString(EnUtilisation) + ", " 
                + Utils.toString(DateRemplacement) + ", " 
                + Utils.toString(DateFabrication) + ", " 
                + Utils.toString(IdentifiantType) + ", " 
                + Utils.toString(NumeroDeSerie) + ", " 
                + Utils.toString(QuantiteRestante) + ", " 
                + Utils.toString(CoutAuCm3) + ", " 
                + Utils.toString(Imprimante3dID) 
          + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return Cartouche;
    }
    
    /**
     * update de l'objet Cartouche dans la ConnexionMySQL
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update Cartouche set "
                + " Type = " + Utils.toString(Type) + "," 
                + " EnUtilisation = " + Utils.toString(EnUtilisation) + "," 
                + " DateRemplacement = " + Utils.toString(DateRemplacement) + ","  
                + " DateFabrication = " + Utils.toString(DateFabrication) + "," 
                + " IdentifiantType = " + Utils.toString(IdentifiantType) + "," 
                + " NumeroDeSerie = " + Utils.toString(NumeroDeSerie) + "," 
                + " QuantiteRestante = " + Utils.toString(QuantiteRestante) + "," 
                + " CoutAuCm3 = " + Utils.toString(CoutAuCm3) + "," 
                + " Imprimante3dID = " + Utils.toString(Imprimante3dID)
                + " where NumeroDeSerie ='" + NumeroDeSerie + "'";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    /**
     * suppression de l'objet Cartouche dans la BD par son NumeroDeSerie
     * @param con
     * @return 
     * @throws SQLException    impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from Cartouche where NumeroDeSerie=" + Utils.toString(NumeroDeSerie);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
        
    public static ArrayList<Cartouche> getList (Connection con) throws Exception {
        String queryString = "select * from Cartouche";
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<Cartouche> Cartouches = new ArrayList<>();
        while (lResult.next ()) {
            Cartouches.add (creerParRequete (lResult));
        }
        return Cartouches;
    }
    
    /**
     * Retourne une cartouche identifiée par son NumeroDeSerie
     * @param  con
     * @param  NumeroDeSerie
     * @return Cartouche trouvé par NumeroDeSerie
     * @throws java.lang.Exception
     */
    public static Cartouche getByNumeroDeSerie(Connection con, int NumeroDeSerie) throws Exception {
        String queryString = "select * from Cartouche"
                    + " where NumeroDeSerie=" + Utils.toString(NumeroDeSerie);
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
     * Retourne un Cartouche trouve par son numeroet son type
     * @param con
     * @param  Type de la cartouche en service à trouver
     * @return Contrat Cartouche trouvé par son type et en service
     * @throws java.lang.Exception
     */
    public static Cartouche getEnServiceAndType(Connection con, String Type) throws Exception {
        String queryString = "select * from Cartouche where EnUtilisation = TRUE"
                + " and Type = " + Utils.toString(Type);
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        if (lResult.next()) {
             creerParRequete(lResult);
        }
        return null;
    }
    
    /**
     * Indique le nb de Cartouche dans la base de données
     * @param con
     * @return le nombre de Cartouche
     * @throws java.lang.Exception
     */
    public static int size(Connection con) throws Exception {
        String queryString = "select count(*) as count from Cartouche";
        Statement lStat = con.createStatement(
                                            ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                            ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next())
            return (lResult.getInt("count"));
        else 
            return 0;
    }
      
    private static Cartouche creerParRequete(ResultSet result) throws Exception {
        String    lType  = result.getString("Type");
        boolean   lEnUtilisation  = result.getBoolean("EnUtilisation");
        Timestamp lDateRemplacement = result.getTimestamp("DateRemplacement");
        Timestamp lDateFabrication = result.getTimestamp("DateFabrication");
        String    lIdentifiantType = result.getString("IdentifiantType");
        String    lNumeroDeSerie = result.getString("NumeroDeSerie");
        double    lQuantiteRestante = result.getInt("QuantiteRestante");
        int       lCoutAuCm3 = result.getInt("CoutAuCm3");
        int       lImprimante3dID = result.getInt("Imprimante3dID");

        return new Cartouche(lType, lEnUtilisation, lDateRemplacement, 
                lDateFabrication, lIdentifiantType, lNumeroDeSerie,
                lQuantiteRestante, lCoutAuCm3, lImprimante3dID);
    }
    
    /**
     * Cree et initialise completement Contrat
     */
    private Cartouche(String Type, boolean EnUtilisation,
        Timestamp DateRemplacement, Timestamp DateFabrication, 
        String IdentifiantType, String NumeroDeSerie,
        double QuantiteRestante, int CoutAuCm3, int Imprimante3dID) {
        this.Type = Type;
        this.EnUtilisation = EnUtilisation;
        this.DateRemplacement = DateRemplacement;
        this.DateFabrication = DateFabrication;
        this.IdentifiantType = IdentifiantType;
        this.NumeroDeSerie = NumeroDeSerie;
        this.QuantiteRestante = QuantiteRestante;
        this.CoutAuCm3 = CoutAuCm3;
        this.Imprimante3dID = Imprimante3dID;
    }
    
    // --------------------- les assesseurs ----------------------------
    
    /**
     * toString() operator overload
     * @return   the result string
     */
    @Override
    public String toString() {
        return  " Type =  " + Type + "\t" +
                " EnUtilisation = " + Utils.toString(EnUtilisation) + 
                " DateRemplacement = " + Utils.toString(DateRemplacement) + 
                " DateFabrication = " + Utils.toString(DateFabrication) + 
                " DateRemplacement = " + Utils.toString(DateRemplacement) + 
                " IdentifiantType = " + Utils.toString(IdentifiantType) + 
                " NumeroDeSerie = " + Utils.toString(NumeroDeSerie) + 
                " QuantiteRestante = " + Utils.toString(QuantiteRestante) + 
                " CoutAuCm3 = " + Utils.toString(CoutAuCm3)
                + " ";
    }
    
    
    public static Cartouche getLast(Connection con) throws Exception {
        String queryString = "select * from Cartouche order by id desc limit 1";
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
    
    //getters
   
    
    public String getType(){
        return Type;
    }
    
    public boolean getUtilisation(){
        return EnUtilisation;
    }
    
    public Timestamp getDateRemplacement(){
        return DateRemplacement;
    }
    
    public Timestamp getDateFabrication(){
        return DateFabrication;
    }
    
    public String getIdentifiantType(){
        return IdentifiantType;
    }
    
    public String getNumeroDeSerie(){
        return NumeroDeSerie;
    }
    
    public double getQuantiteRestante(){
        return QuantiteRestante;
    }
    
    public int getCoutAuCm3(){
        return CoutAuCm3;
    }
    
    public int getImprimante3dID(){
        return Imprimante3dID;
    }
    
    //setters
    
    public void setType(String Type){
        this.Type = Type;
    }
    
    public void setUtilisation(boolean EnUtilisation){
        this.EnUtilisation = EnUtilisation;
    }
    
    public void setDateRemplacement(Timestamp DateRemplacement){
        this.DateRemplacement = DateRemplacement;
    }
    
    public void setDateFabrication(Timestamp DateFabrication){
        this.DateFabrication = DateFabrication;
    }
    
    public void setIdentifiantType(String IdentifiantType){
        this.IdentifiantType = IdentifiantType;
    }
    
    public void setNumeroDeSerie(String NumeroDeSerie){
        this.NumeroDeSerie = NumeroDeSerie;
    }
    
    public void setQuantiteRestante(double QuantiteRestante){
        this.QuantiteRestante = QuantiteRestante;
    }
    
    public void setCoutAuCm3(int CoutAuCm3){
        this.CoutAuCm3 = CoutAuCm3;
    }
    
    public void setImprimante3dID(int Imprimante3dID){
        this.Imprimante3dID = Imprimante3dID;
    }
    
}