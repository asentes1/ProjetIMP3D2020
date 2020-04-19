/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.persistence;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jean-pierredumas
 */
public class JobTest {
    
    public JobTest() {
    }

    /**
     * Test of create method, of class Job.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Connection con = ConnexionMySQL.newConnexion();
        String expResult = "AFAIRE";
        Job result = Job.create(con, "Piece2", "/home/real/Piece2.stl", 
                        Utils.stringToTimestamp("2019/05/17 11:00:00"), 
                        Utils.stringToTimestamp("2019/05/18 11:00:00"),
                        "AFAIRE", 0, 0, 0, 0, 0, 0, 0, 1, 1);
        assertEquals(expResult, result.getEtat());
        result.delete(con);
    }

    /**
     * Test of delete method, of class Job.
     */
    /*Cette methode ne fonctionne pas elle efface tous les jobs*/
    /*
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Connection con = ConnexionMySQL.newConnexion();
        String Nom = "piece1";
        String FileRef = "/home/real/test.stl";
        Job result = Job.create(con, Nom, "/home/real/Piece2.stl", 
                        Utils.stringToTimestamp("2019/05/17 11:00:00"), 
                        Utils.stringToTimestamp("2019/05/18 11:00:00"),
                        "AFAIRE", 0, 0, 0, 0, 0, 0, 0, 1, 1);
        assertEquals(Nom, result.getNom());
        result.delete(con);
        Job test = Job.getLast(con);
        assertNotEquals(Nom, test.getNom());
    }
    */
    /**
     * Test of save method, of class Job.
     */
    
    /*
    @Test
    public void testSave() throws Exception {
        System.out.println("save");
        Connection con = ConnexionMySQL.newConnexion();
        String Nom = "piece1";
        String FileRef = "/home/real/test.stl";
        Job result = Job.create(con, Nom, FileRef, Utils.stringToTimestamp("2019/05/17 11:00:00"), Utils.stringToTimestamp("2019/05/17 11:00:00"), "IMPR", 0, 0, 0, 0, 0, 0, 0, 1, 1);
        String Nom2 = "ls";
        result.setNom(Nom2);
        result.save(con);
        Job test = Job.getLast(con);
        assertEquals(Nom2, test.getNom());
        result.delete(con);  
    }
    */
    
    /**
     * Test of size method, of class Job.
     */
    @Test
    public void testSize() throws Exception {
        System.out.println("size");
            Connection con = ConnexionMySQL.newConnexion();
        int expResult = 5;
        int result = Job.size(con);
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetLast() throws Exception{
        System.out.println("GetLast");
        Connection con = ConnexionMySQL.newConnexion();
        String Nom = "salut2.stp";
        String FileRef = "/home/alex/";
        Job result = Job.create(con, Nom, FileRef, Utils.stringToTimestamp("2019/05/17 11:00:00"), Utils.stringToTimestamp("2019/05/17 11:00:00"), "IMPR", 0, 0, 0, 0, 0, 0, 0, 1, 1);
        Job test = Job.getLast(con);
        assertEquals(Nom, test.getNom());
        result.delete(con);
    }

    /**
     * Test of getListByClientID method, of class Job.
     */
    @Test
    public void testGetListByClientID() throws Exception {
        System.out.println("getListByClientID");
        Connection con = null;
        int ClientID = 0;
        ArrayList<Job> expResult = null;
        ArrayList<Job> result = Job.getListByClientID(con, ClientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListByEtat method, of class Job.
     */
    @Test
    public void testGetListByEtat() throws Exception {
        System.out.println("getListByEtat");
        Connection con = null;
        String Etat = "";
        ArrayList<Job> expResult = null;
        ArrayList<Job> result = Job.getListByEtat(con, Etat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Job.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Job instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
