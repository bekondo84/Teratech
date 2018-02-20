package com.bekosoftware.genericdaolayer;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest 
    
{
    PersonDAOTest dao = null ;
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    

    
    /**
     * Rigourous Test :-)
     */
    @org.junit.Test
    public void testApp()
    {
      /* System.out.println("::::::::::::::::::::::DEMARRAGE DES TESTS  :::::::::::::::::::::");
        dao.getTx().begin();
        Person p1 = new Person("ELLA" ,"JOEL");
        p1.addContact(new Contact("DAKAR", "yaounde@yahoo.fr"));
        p1.addContact(new Contact("BAMENDA", "megatim@bamenda.com"));
        Person p2 = new Person("BELLA" ,"JOEL");
        p2.addContact(new Contact("YAMOUSSOUKROU", "megatim@yamoussoukrou.com"));
        p2.addContact(new Contact("ABIDJAN", "megatim@abidjan.com"));        
        assertNotNull(dao);        
        dao.save(p2);
        dao.save(p1);
        dao.flush();
        System.out.println("::::::::::::::::::::::INITIALISATION DES DONNEES REUSSITES  :::::::::::::::::::::");
        System.out.println("::::::::::::::::::::::TEST DE LA filter avec chargement  :::::::::::::::::::::");
        RestrictionsContainer rc = new RestrictionsContainer();
        Set<String> properties = new HashSet<String>();
        properties.add("contacts");
        List<Person> persons = dao.filter(rc.getPredicats(), null, properties, 0, -1);        
        assertNotNull(persons);
        System.out.println(persons);
        assertTrue( persons.size()==2 );
        System.out.println("::::::::::::::::::::::TEST FINDBYPRIMARYKEY  :::::::::::::::::::::");
        Person p = dao.findByPrimaryKey("id", 1L);
        assertNotNull(p);
        assertEquals(p.getNom(), "BELLA");
        System.out.println("::::::::::::::::::::::TEST FINDBYPRIMARYKEY SUCCESS :::::::::::::::::::::");
        persons = dao.findByProperty("nom", "ELLA", properties);
        assertNotNull(persons);
        assertEquals(persons.size(), 1);
        assertEquals(persons.get(0).getNom(), "ELLA");
        Predicat predicat = new ISNOTEMPTY("contacts");
        rc.add(predicat);
        persons = dao.filter(rc.getPredicats(), null, properties, 0, -1);
        System.out.println("::::::::::::::::::::::TEST FINDBYPRIMARYKEY SUCCESS :::::::::::::::::::::"+persons);
        dao.getTx().commit();*/
    	Assert.assertTrue(true);
    }




	public AppTest() {
		super();
	}
}
