/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import java.sql.Connection;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jean-pierredumas
 */
public class Imprimante3dTest {
    
    public Imprimante3dTest() {
    }

    /**
     * Test of create method, of class Imprimante3d.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        String Nom = "nimp3d";
        String FablabNom = "test";
        String Etat = "IMPRESSION";
        double NbHeuresDeTravail = 0.0;
        int CoutHoraire = 0;
        int AmbianceID = 6;
        String expResult = "IMPRESSION";
        Imprimante3d result = Imprimante3d.create(con, Nom, FablabNom, Etat, NbHeuresDeTravail, CoutHoraire, AmbianceID);
        assertEquals(expResult, result.getEtat());
        result.delete(con);
    }

    /**
     * Test of save method, of class Imprimante3d.
     */
    /*
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        String Nom = "test2";
        String FablabNom = "test";
        String Etat = "IMPRESSION";
        double NbHeuresDeTravail = 0.0;
        int CoutHoraire = 0;
        int AmbianceID = 6;
        Imprimante3d result = Imprimante3d.create(con, Nom, FablabNom, Etat, NbHeuresDeTravail, CoutHoraire, AmbianceID);
        String Etat1 = "test";
        result.setEtat(Etat1);
        result.save(con);
        Imprimante3d imp = Imprimante3d.getLast(con);
        
        assertEquals(Etat1, imp.getEtat());
        result.delete(con);
        
    }
    */
    
    @Test
    public void testGetLast() throws Exception{
        System.out.println("getLast");
        Connection con = ConnexionMySQL.newConnexion();
        String Nom = "salut";
        Imprimante3d result = Imprimante3d.create(con, Nom, "testfablab", "IMPRESSION", 20, 10, 1);
        Imprimante3d imp = Imprimante3d.getLast(con);
        assertEquals(Nom, imp.getNom());
        result.delete(con);
    }
    /**
     * Test of delete method, of class Imprimante3d.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Connection con = ConnexionMySQL.newConnexion();
        String Nom = "gotaga";
        Imprimante3d result = Imprimante3d.create(con, Nom, "toto", "IMPR", 0, 0, 0);
        assertEquals(Nom, result.getNom());
        result.delete(con);
        Imprimante3d imp = Imprimante3d.getLast(con);
        assertNotEquals(Nom, imp.getNom());
    }

    /**
     * Test of getList method, of class Imprimante3d.
     */
    @Test
    public void testGetList() throws Exception {
        System.out.println("getList");
        Connection con = null;
        ArrayList<Imprimante3d> expResult = null;
        ArrayList<Imprimante3d> result = Imprimante3d.getList(con);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getByID method, of class Imprimante3d.
     */
    /*
    @Test
    public void testGetByID() throws Exception {
        System.out.println("getByID");
        Connection con = ConnexionMySQL.newConnexion();
        Imprimante3d result = Imprimante3d.create(con, "toto", "lab", "IMP", 20, 22, 24);
        //Imprimante3d test = Imprimante3d
        result.delete(con);
    }
    */
    /**
     * Test of getIDByNom method, of class Imprimante3d.
     */
    @Test
    public void testGetIDByNom() throws Exception {
        System.out.println("getIDByNom");
        Connection con = null;
        String Mail = "deodat@gmail.com";
        //result = Imprimante3d. 
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class Imprimante3d.
     */
    @Test
    public void testSize() throws Exception {
        System.out.println("size");
        Connection con = ConnexionMySQL.newConnexion();
        int expResult = 1;
        int result = Imprimante3d.size(con);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Imprimante3d.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Imprimante3d instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
