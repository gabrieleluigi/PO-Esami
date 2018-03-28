package it.polito.po.test;
import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
        TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(TestR1_TorneiSquadre.class);
		suite.addTestSuite(TestR2_Tesserati.class);
		suite.addTestSuite(TestR3_Incontri.class);
		suite.addTestSuite(TestR4_PuntiClassifica.class);
		//$JUnit-END$
		return suite;
	}

}
