/*
 * Projet  : Alfox
 * Fichier : User.java
 * Description : Classe interface de la table Job
 * Cette table stocke les données enregistrées par la SD
 */

package com.persistence;

import java.sql.*;
import java.util.ArrayList;

public class Job {
    private String    Nom;
    private String    FileRef;
    private Timestamp DateDemande; 
    private Timestamp DateRealisation; 
    private String    Etat; 
    private int       DureeConsommee; 
    private int       ResteAFaireEstimee;
    private double    SupportConsomme;
    private double    MatiereConsommee;
    private double    SupportEstime;
    private double    MatiereEstimee;
    private int       Prix;
    private int       ClientID;
    private int       Imprimante3dID;
    
    /**
     * Créer un nouvel objet persistant 
     * @param con
     * @param Nom
     * @param FileRef
     * @param DateDemande
     * @param DateRealisation
     * @param Etat
     * @param DureeConsommee
     * @param ResteAFaireEstimee
     * @param SupportConsomme
     * @param MatiereConsommee
     * @param SupportEstime
     * @param MatiereEstimee
     * @param Prix
     * @param ClientID
     * @param Imprimante3dID
     * @return 
     * @ return retourne une Job si la date est unique sinon null
     * @throws Exception    impossible d'accéder à la ConnexionMySQL
     *                      ou la date est deja dans la BD
     * 
     */
    static public Job create(Connection con, String Nom, String FileRef, 
        Timestamp DateDemande, Timestamp DateRealisation,
        String Etat, int DureeConsommee, int ResteAFaireEstimee, 
        double SupportConsomme, double MatiereConsommee, double SupportEstime, 
        double MatiereEstimee, int Prix, int ClientID, int Imprimante3dID) throws Exception {
        Job Job = new Job(Nom, FileRef, DateDemande, DateRealisation, Etat, 
            DureeConsommee, ResteAFaireEstimee, SupportConsomme, MatiereConsommee, 
            SupportEstime, MatiereEstimee, Prix, ClientID, Imprimante3dID);
        
        String queryString =
         "insert into Job (Nom, FileRef, DateDemande, DateRealisation, Etat, DureeConsommee, "
            + "ResteAFaireEstimee, SupportConsomme, MatiereConsommee, SupportEstime, "
            + "MatiereEstimee, Prix, ClientID, Imprimante3dID) values ("
                + Utils.toString(Nom) + ", " 
                + Utils.toString(FileRef) + ", " 
                + Utils.toString(DateDemande) + ", " 
                + Utils.toString(DateRealisation) + ", " 
                + Utils.toString(Etat) + ", " 
                + Utils.toString(DureeConsommee) + ", "
                + Utils.toString(ResteAFaireEstimee) + ", " 
                + Utils.toString(SupportConsomme) + ", " 
                + Utils.toString(MatiereConsommee) + ", " 
                + Utils.toString(SupportEstime) + ", " 
                + Utils.toString(MatiereEstimee) + ", "
                + Utils.toString(Prix) + ", " 
                + Utils.toString(ClientID) + ", " 
                + Utils.toString(Imprimante3dID)
            + ")";
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
        return Job;
    }
    
    /**
     * suppression de l'objet Job par le client et le nom du job
     * @param con
     * @return 
     * @throws SQLException    impossible d'accéder à la ConnexionMySQL
     */
    public boolean delete(Connection con) throws Exception {
        String queryString = "delete from Job" 
                + " where ClientID = " + ClientID
                     + " and Nom =" + Utils.toString(Nom);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString);
        return true;
    }
    
    /**
     * update de l'objet Job dans la ConnexionMySQL
     * @param con
     * @throws Exception impossible d'accéder à la ConnexionMySQL
     */
    public void save(Connection con) throws Exception {
        String queryString =
         "update Job set "
                + " Nom =" + Utils.toString(Nom) + ","
                + " FileRef =" + Utils.toString(FileRef) + ","
                + " DateDemande =" + Utils.toString(DateDemande) + "," 
                + " DateRealisation =" + Utils.toString(DateRealisation) + "," 
                + " Etat =" + Utils.toString(Etat) + "," 
                + " DureeConsommee =" + Utils.toString(DureeConsommee) + ","
                + " ResteAFaireEstimee =" + Utils.toString(ResteAFaireEstimee) + ","
                + " SupportConsomme =" + Utils.toString(SupportConsomme) + "," 
                + " MatiereConsommee =" + Utils.toString(MatiereConsommee) + ","
                + " SupportEstime =" + Utils.toString(SupportEstime) + ","
                + " MatiereEstimee =" + Utils.toString(MatiereEstimee) + ","
                + " Prix =" + Utils.toString(Prix) + ","
                + " ClientID =" + Utils.toString(ClientID) + ","
                + " Imprimante3dID =" + Utils.toString(Imprimante3dID);
        Statement lStat = con.createStatement();
        lStat.executeUpdate(queryString, Statement.NO_GENERATED_KEYS);
    }
    
    
    public static Job getLast(Connection con) throws Exception {
        String queryString = "select * from Job order by id desc limit 1";
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
    
    /**
     * Indique le nb de Job dans la base de données
     * @param con
     * @return le nombre de vehicules
     * @throws java.lang.Exception
     */
    public static int size(Connection con) throws Exception {
        String queryString = "select count(*) as count from Job";
        Statement lStat = con.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, 
                        ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        if (lResult.next())
            return (lResult.getInt("count"));
        else 
            return 0;
    }
    
    /**
     * Retourne les jobs d'un client donné
     * @param con
     * @param  ClientID du client
     * @return liste de job d'un client donné
     * @throws java.lang.Exception
     */
    public static ArrayList<Job> getListByClientID(Connection con, int ClientID) throws Exception {
        ArrayList<Job> lesJobs = new ArrayList<>();
        String queryString = "select * from Job where ClientID = " + ClientID;
        Statement lStat = con.createStatement(
                                ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        // y en a t'il au moins un ?
        while (lResult.next()) {
             lesJobs.add(creerParRequete(lResult));
        }
        return lesJobs;
    }

    // liste des Jobs (si Etat=AFAIRE : à faire par exemple) par ordre d'ancienneté
    public static ArrayList<Job> getListByEtat(Connection con, String Etat) throws Exception {
        String queryString = "select * from Job where Etat = " 
                        + Utils.toString(Etat) + " order by DateDemande";
        Statement lStat = con.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        ResultSet lResult = lStat.executeQuery(queryString);
        ArrayList<Job> lstJob = new ArrayList<>();
        // y en a t'il au moins un ?
        while (lResult.next()) {
            lstJob.add(creerParRequete(lResult));
        }
        return lstJob;
    }
    
    private static Job creerParRequete(ResultSet result) throws Exception {
        String    lNom  = result.getString("Nom");
        String    lFileRef  = result.getString("FileRef");
        Timestamp lDateDemande = result.getTimestamp("DateDemande");
        Timestamp lDateRealisation = result.getTimestamp("DateRealisation");
        String    lEtat = result.getString("Etat");
        int       lDureeConsommee = result.getInt("DureeConsommee");
        int       lResteAFaireEstimee  = result.getInt("ResteAFaireEstimee");
        double    lSupportConsomme = result.getDouble("SupportConsomme");
        double    lMatiereConsommee = result.getDouble("MatiereConsommee");
        double    lSupportEstime = result.getDouble("SupportEstime");
        double    lMatiereEstimee  = result.getDouble("MatiereEstimee");
        int       lPrix = result.getInt("Prix");
        int       lClientID = result.getInt("ClientID");
        int       lImprimante3dID = result.getInt("Imprimante3dID");
        return new Job(lNom, lFileRef, lDateDemande, lDateRealisation, lEtat, lDureeConsommee, 
            lResteAFaireEstimee, lSupportConsomme, lMatiereConsommee, 
            lSupportEstime, lMatiereEstimee, lPrix, lClientID, lImprimante3dID);
    }
    
    /**
     * Cree et initialise completement le Job
     */
    private Job(String Nom, String FileRef, 
        Timestamp DateDemande, Timestamp DateRealisation,
        String Etat, int DureeConsommee, int ResteAFaireEstimee, 
        double SupportConsomme, double MatiereConsommee, double SupportEstime, 
        double MatiereEstimee, int Prix, int ClientID, int Imprimante3dID) {
        this.Nom = Nom;
        this.FileRef = FileRef;
        this.DateDemande = DateDemande;
        this.DateRealisation = DateRealisation;
        this.Etat = Etat;
        this.DureeConsommee = DureeConsommee;
        this.ResteAFaireEstimee = ResteAFaireEstimee;
        this.SupportConsomme = SupportConsomme;
        this.MatiereConsommee = MatiereConsommee;
        this.SupportEstime = SupportEstime;
        this.MatiereEstimee = MatiereEstimee;
        this.Prix = Prix;
        this.ClientID = ClientID;
        this.Imprimante3dID = Imprimante3dID;
    }
    
    // --------------------- les assesseurs ----------------------------
    public String getNom() {
        return  Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getFileRef() {
        return FileRef;
    }

    public void setFileRef(String FileRef) {
        this.FileRef = FileRef;
    }

    public Timestamp getDateDemande() {
        return DateDemande;
    }

    public void setDateDemande(Timestamp DateDemande) {
        this.DateDemande = DateDemande;
    }

    public Timestamp getDateRealisation() {
        return DateRealisation;
    }

    public void setDateRealisation(Timestamp DateRealisation) {
        this.DateRealisation = DateRealisation;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public int getDureeConsommee() {
        return DureeConsommee;
    }

    public void setDureeConsommee(int DureeConsommee) {
        this.DureeConsommee = DureeConsommee;
    }

    public int getResteAFaireEstimee() {
        return ResteAFaireEstimee;
    }

    public void setResteAFaireEstimee(int ResteAFaireEstimee) {
        this.ResteAFaireEstimee = ResteAFaireEstimee;
    }

    public double getSupportConsomme() {
        return SupportConsomme;
    }

    public void setSupportConsomme(double SupportConsomme) {
        this.SupportConsomme = SupportConsomme;
    }

    public double getMatiereConsommee() {
        return MatiereConsommee;
    }

    public void setMatiereConsommee(double MatiereConsommee) {
        this.MatiereConsommee = MatiereConsommee;
    }

    public double getSupportEstime() {
        return SupportEstime;
    }

    public void setSupportEstime(double SupportEstime) {
        this.SupportEstime = SupportEstime;
    }

    public double getMatiereEstimee() {
        return MatiereEstimee;
    }

    public void setMatiereEstimee(double MatiereEstimee) {
        this.MatiereEstimee = MatiereEstimee;
    }

    public int getPrix() {
        return Prix;
    }

    public void setPrix(int Prix) {
        this.Prix = Prix;
    }

    public int getClientID() {
        return ClientID;
    }

    public void setClientID(int ClientID) {
        this.ClientID = ClientID;
    }

    public int getImprimante3dID() {
        return Imprimante3dID;
    }

    public void setImprimante3dID(int Imprimante3dID) {    
        this.Imprimante3dID = Imprimante3dID;
    }

    /**
     * toString() operator overload
     * @return  the result string
     */
    @Override
    public String toString() {
        return  " Nom = " + Utils.toString(Nom) + "\t" +
                " FileRef = " + Utils.toString(FileRef) +
                " DateDemande = " + Utils.toString(DateDemande) +
                " DateRealisation = " + Utils.toString(DateRealisation) +
                " Etat = " + Utils.toString(Etat) +
                " DureeConsommee = " + Utils.toString(DureeConsommee) +
                " ResteAFaireEstimee = " + Utils.toString(ResteAFaireEstimee) +
                " SupportConsomme = " + Utils.toString(SupportConsomme) +
                " MatiereConsommee = " + Utils.toString(MatiereConsommee) +
                " SupportEstime = " + Utils.toString(SupportEstime) +
                " MatiereEstimee = " + Utils.toString(MatiereEstimee) +
                " Prix = " + Utils.toString(Prix) +
                " ClientID = " + Utils.toString(ClientID) +
                " Imprimante3dID = " + Utils.toString(Imprimante3dID);
    }
    
}