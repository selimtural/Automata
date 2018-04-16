package expr;
 
public class Checker2 extends Checker {

   public Checker2(String s) { super(s); }
   public void check() { //override
      tok = lex.nextToken();
      while (tok != Token.EOF)
         statement();
      match(Token.EOF);
      System.out.println(); 
   }
   void statement() {
      String id = lex.sval;
      match(Token.IDENT);
      if (id.equals("print")) {
         expr();
      } else { //assignment
         match(Token.EQUAL); expr();
      }
      match(Token.SEMCOL);
   }
   void factor() { //override
      if (tok != Token.IDENT) 
         super.factor();
      else {
         match(Token.IDENT);
         if (tok != Token.LEFT)
            return;
         match(Token.LEFT);
         expr();
         match(Token.RIGHT); 
      }
   }
   
   public static void main(String[] args) {
      new Checker2(Runtime.sample).check();
      System.out.println("Accepted");
   }
}
