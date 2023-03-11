# yuzz
Strong Typed HTML/XML Web framework and embedded nosql database for Java that I wrote in 2008


```java
  Table table = table( 
          a("border","1"),
    	    tr( 
    	     td( alist(a("align", "left")), 
    	      n("font", a("size", "+1"), 
    	       n("strong", t("Method")))) 
    	       ));
    F2<Table, String, Table> fun = new Fun.F2<Table, String, Table>() {
      public Table f(Table table, String str) {
        table.add(tr(td(t(str))));
        return table;
      }
      
    };
    List<String> methods = Functions.map(new F<Method,String>() {
      public String f(Method m) {
        return m.toString();
      }
      
    } , NodeStatics.class.getMethods());
    Functions.reduce(fun , table, methods );
    return table;
```
