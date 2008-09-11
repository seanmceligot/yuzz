
package org.snuvy;

import java.util.List;

import org.yuzz.functor.Compose;
import org.yuzz.functor.Fun;
import org.yuzz.functor.Procedure;
import org.yuzz.functor.Fun.F;
import org.yuzz.functor.Procedure.Proc1;

import com.sleepycat.je.DatabaseException;


public class FromRowOperations {
  public abstract static class ToRow<T> extends Procedure.Proc2<T, DbRow>{};
  
  public abstract static class FromRow<T> extends Fun.F<DbRow, T>{};

  public static class ComposeFromRow<B,C> extends Compose<DbRow,B,C> {
    public ComposeFromRow(F<B,C> f1, F <DbRow,B> f2) {
      super(f1, f2);
    }
  }
  private static final class ProcFromRow<T> extends Procedure.Proc1<DbRow> {
    private final Proc1<T> _withRow;
    private final FromRow<T> _fromRow;

    private ProcFromRow(Proc1<T> withRow, FromRow<T> fromRow) {
      _withRow = withRow;
      _fromRow = fromRow;
    }

    public void f(DbRow row) {
      T t = _fromRow.f(row);
      _withRow.f(t);
    }
  }

  private static final class ListFromRow<Any> extends Proc1<DbRow> {
    private final FromRow<Any> _fromRow;
    private final List<Any> _rows;

    public ListFromRow(FromRow<Any> fromRow, List<Any> rows) {
      _fromRow = fromRow;
      _rows = rows;
    }
    public void f(DbRow row) {
      _rows.add(_fromRow.f(row));
    }
  }

  
  public static <T> List<T> map(DbTable table, DbIndex index, final FromRow<T> fromRow,final List<T> rows) throws DatabaseException {
    FunctionalDbm.firstToLast(table, index, new ListFromRow<T>(fromRow, rows));
    return rows;
  }
  public static <B,C> List<C> compose(DbTable table, DbIndex index, final ComposeFromRow<B,C> composition,final List<C> rows) throws DatabaseException {
    FunctionalDbm.firstToLast(table, index, new Procedure.Proc1<DbRow>() {
      public void f(org.snuvy.DbRow row) {
        rows.add(composition.f(row));
      }
    });
    return rows;
  }
  public static <T> void firstToLast(DbTable table,DbIndex index, final FromRow<T> fromRow, final Procedure.Proc1<T> withRow) throws DatabaseException {
    FunctionalDbm.firstToLast(table, index, new ProcFromRow<T>(withRow, fromRow));
   
  }
  public static <T> void lastToFirst(DbTable table,DbIndex index, final FromRow<T> fromRow, final Procedure.Proc1<T> withRow) throws DatabaseException {
    FunctionalDbm.lastToFirst(table, index, new ProcFromRow<T>(withRow, fromRow));
  }
  public static <T> void findMatching(DbTable table,DbIndex index, DatabaseColumn key, final FromRow<T> fromRow, final Procedure.Proc1<T> withRow) throws DatabaseException {
    FunctionalDbm.findMatching(table, index, key, new Proc1<DbRow>() {
      public void f(DbRow row) {
        new ProcFromRow<T>(withRow, fromRow).f(row);
      }
    });
  }
  private static class ComposeOLD<A,B,C> extends 
  Fun.F3<
    Fun.F<B, C>, 
    Fun.F<A,B>,
    A,
    C // returns value of type C
    > {
  public C f(
      Fun.F<B, C> f1,
      Fun.F<A, B> f2, A a) {
    B b = f2.f(a);
    C c = f1.f(b);
    return c;
  }
  
}
}
