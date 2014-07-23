package test.snuvy;

import junit.framework.TestCase;

import org.junit.Test;
import org.snuvy.Dbm;
import org.snuvy.FunctionalDbm;
import org.snuvy.FunctionalDbm.WithDb;
import org.snuvy.FunctionalDbm.WithDbm;
import org.snuvy.FunctionalDbm.WithSequence;
import org.snuvy.TableSequence;

public class FunctionalTest extends TestCase {
	private final class PopulateTable implements FunctionalDbm.WithDb {

		private PopulateTable() {
		}

    public void run(String name, Dbm db) throws Throwable {
			WithSequence withseq = new FunctionalDbm.WithSequence() {
		
				public void run(TableSequence db) throws Throwable {
					System.out.println(db.next());
					System.out.println(db.next());
				}
			};
			FunctionalDbm fdbm = new FunctionalDbm();
			fdbm.withSequence(db, withseq, "test");
		}

	}
	@Test
	public final void testWithDbm() throws Throwable {

		WithDbm withDbm = new FunctionalDbm.WithDbm() {
			public void run(final Dbm dbm) throws Throwable {
			  WithDb withdb = new PopulateTable();
			  withdb.run("testdb", dbm);
			}
			};
    FunctionalDbm fdbm = new FunctionalDbm();
		fdbm.withDbm(withDbm);
		
	}

	public final void testWithDb() {
	}

	public final void testWithSequence() {
	}
	/*
	public static void main(String[] args) {
		try {
			final RunNotifier runNotifier = new RunNotifier();
			RunListener rl = new PrintListener(System.err);
			runNotifier.addListener(rl);
			new TestClassRunner(FunctionalTest.class).run(runNotifier);
		} catch (Throwable th) {
			th.printStackTrace();
		}
		}*/

}
