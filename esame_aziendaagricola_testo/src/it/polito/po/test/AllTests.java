package it.polito.po.test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
        //$JUnit-BEGIN$
        suite.addTestSuite(TestR1_Zone.class);
        suite.addTestSuite(TestR2_Coltivazioni.class);
        suite.addTestSuite(TestR3_Raccolti.class);
        suite.addTestSuite(TestR4_CaricamentoDaFile.class);
        //$JUnit-END$
        return suite;
    }

}
