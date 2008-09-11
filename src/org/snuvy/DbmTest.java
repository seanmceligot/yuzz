package org.snuvy;

import java.io.IOException;


import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseException;

import junit.framework.Assert;
import junit.framework.TestCase;

public class DbmTest extends TestCase {
	public final void testSequence() throws DatabaseException, IOException {
		Dbm dbm = new Dbm();
		dbm.open();
		try {
			TableSequence seq = dbm.getSequence("test");
			long i = seq.next();
			long i2 = seq.next();
			Assert.assertTrue(i2 - i == 1L);
			dbm.close("test");
		} finally {
			dbm.close();
		}
	}


	  public final void testDbm() throws DatabaseException {
			Dbm dbm = new Dbm();
			dbm.open();
			try {
			} finally {
				dbm.close();
			}
		}

		
		public final void testDb() throws DatabaseException {
			Dbm dbm = new Dbm(".");
			dbm.open();
			try {
				Database db = dbm.open(false, "test");
				dbm.close("test");
			} finally {
				dbm.close();
			}
		}

	/*
	public static void main(String[] args) {
		try {
			//ConsoleHandler handler = new ConsoleHandler();
			//Logger.getLogger("").addHandler(handler);
			//Dbm.log.setLevel(Level.FINE);
			Dbm.log("started)");
			final RunNotifier runNotifier = new RunNotifier();
			RunListener rl = new PrintListener(System.err);
			runNotifier.addListener(rl);
			new TestClassRunner(DbmTest.class).run(runNotifier);
		} catch (Throwable th) {
			th.printStackTrace();
		}
	}*/

}
