/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InsuranceSystem;

import java.util.ArrayList;
import javax.swing.JLabel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lroyg
 */
public class InsuranceSystemTest {
    
    public InsuranceSystemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testInvalidNameCreateCustomer() {
        InsuranceSystem instance = InsuranceSystem.getInstance();
        String firstName = "Jane";
        String lastName = "Doe3";
        int birthYear = 2004;
        String phoneNumber = "0211234567";
        String email = "janedoe@gmail.com";
        
        ArrayList<JLabel> expResult = new ArrayList<>();
        expResult.add(new JLabel());
        
        ArrayList<JLabel> result = instance.createCustomer(firstName, lastName, birthYear, phoneNumber, email);
        assertEquals(expResult.size(), result.size());
    }
    
    @Test
    public void testInvalidEmailCreateCustomer() {
        InsuranceSystem instance = InsuranceSystem.getInstance();
        String firstName = "Jane";
        String lastName = "Doe";
        int birthYear = 2004;
        String phoneNumber = "0211234567";
        String email = "janedoegmail.com";
        
        ArrayList<JLabel> expResult = new ArrayList<>();
        expResult.add(new JLabel());
        
        ArrayList<JLabel> result = instance.createCustomer(firstName, lastName, birthYear, phoneNumber, email);
        assertEquals(expResult.size(), result.size());
    }

    @Test
    public void testInvalidPhoneCreateCustomer() {
        InsuranceSystem instance = InsuranceSystem.getInstance();
        String firstName = "Jane";
        String lastName = "Doe";
        int birthYear = 2004;
        String phoneNumber = "021122323134567";
        String email = "janedoe@gmail.com";
        
        ArrayList<JLabel> expResult = new ArrayList<>();
        expResult.add(new JLabel());
        
        ArrayList<JLabel> result = instance.createCustomer(firstName, lastName, birthYear, phoneNumber, email);
        assertEquals(expResult.size(), result.size());
    }
    
    @Test
    public void testAllValidCreateCustomer() {
        InsuranceSystem instance = InsuranceSystem.getInstance();
        String firstName = "Jane";
        String lastName = "Doe";
        int birthYear = 2004;
        String phoneNumber = "0211234567";
        String email = "janedoe@gmail.com";
        
        ArrayList<JLabel> expResult = new ArrayList<>();
        expResult.add(new JLabel());
        expResult.add(new JLabel());
        
        ArrayList<JLabel> result = instance.createCustomer(firstName, lastName, birthYear, phoneNumber, email);
        assertEquals(expResult.size(), result.size());
    }
    
    @Test
    public void testInvalidNameCreateStaff() {
        InsuranceSystem instance = InsuranceSystem.getInstance();
        String firstName = "J3ane";
        String lastName = "Doe";
        int birthYear = 2000;
        String extension = "100";
        String password = "JaneDoe1!";
        boolean manager = false;

        ArrayList<JLabel> expResult = new ArrayList<>();
        expResult.add(new JLabel());
        
        ArrayList<JLabel> result = instance.createStaff(firstName, lastName, birthYear, extension, password, manager);
        assertEquals(expResult.size(), result.size());
    }
    
    @Test
    public void testInvalidExtensionCreateStaff() {
        InsuranceSystem instance = InsuranceSystem.getInstance();
        String firstName = "Jane";
        String lastName = "Doe";
        int birthYear = 2000;
        String extension = "1030";
        String password = "JaneDoe1!";
        boolean manager = false;

        ArrayList<JLabel> expResult = new ArrayList<>();
        expResult.add(new JLabel());
        
        ArrayList<JLabel> result = instance.createStaff(firstName, lastName, birthYear, extension, password, manager);
        assertEquals(expResult.size(), result.size());
    }
    
    @Test
    public void testInvalidPasswordCreateStaff() {
        InsuranceSystem instance = InsuranceSystem.getInstance();
        String firstName = "Jane";
        String lastName = "Doe";
        int birthYear = 2000;
        String extension = "100";
        String password = "Jane!";
        boolean manager = false;

        ArrayList<JLabel> expResult = new ArrayList<>();
        expResult.add(new JLabel());
        expResult.add(new JLabel());
        expResult.add(new JLabel());
        expResult.add(new JLabel());

        
        ArrayList<JLabel> result = instance.createStaff(firstName, lastName, birthYear, extension, password, manager);
        assertEquals(expResult.size(), result.size());
    }
    
    @Test
    public void testInvalidEverythingCreateStaff() {
        InsuranceSystem instance = InsuranceSystem.getInstance();
        String firstName = "3";
        String lastName = "3";
        int birthYear = 2000;
        String extension = "3";
        String password = "3";
        boolean manager = false;

        ArrayList<JLabel> expResult = new ArrayList<>();
        expResult.add(new JLabel());
        expResult.add(new JLabel());
        expResult.add(new JLabel());
        expResult.add(new JLabel());
        expResult.add(new JLabel());
        expResult.add(new JLabel());

        
        ArrayList<JLabel> result = instance.createStaff(firstName, lastName, birthYear, extension, password, manager);
        assertEquals(expResult.size(), result.size());
    }

    @Test
    public void testAllValidCreateStaff() {
        InsuranceSystem instance = InsuranceSystem.getInstance();
        String firstName = "Jane";
        String lastName = "Doe";
        int birthYear = 2000;
        String extension = "100";
        String password = "JaneDoe1!";
        boolean manager = false;

        ArrayList<JLabel> expResult = new ArrayList<>();
        expResult.add(new JLabel());
        expResult.add(new JLabel());
        
        ArrayList<JLabel> result = instance.createStaff(firstName, lastName, birthYear, extension, password, manager);
        assertEquals(expResult.size(), result.size());
    }
    
    
}
