package org.dimmik.cards.pref;

import junit.framework.Test;
import junit.framework.TestSuite;

public class PrefTestSuite extends TestSuite {

  public static Test suite(){
    TestSuite suite = new TestSuite();
    suite.addTestSuite(ContractTest.class);
    suite.addTestSuite(PrefDealTest.class);
    suite.addTestSuite(PrefGameTest.class);
    suite.addTestSuite(AllPassGameTest.class);
    suite.addTestSuite(ScoresTest.class);
    return suite;
  }
}