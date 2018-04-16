package expr;

import java.util.ArrayList;
   
public class Parser2 extends Parser {

   public Parser2(String s) { super(s); }
   public Statement[] parse2() { //cannot override parse()
      tok = lex.nextToken();
      ArrayList<Statement> a = new ArrayList<>();
      while (tok != Token.EOF)
         a.add(statement());
      match(Token.EOF); 
      return a.toArray(new Statement[0]); 
   }
   Statement statement() {
      String id = lex.sval;
      match(Token.IDENT);
      Statement s;
      if (id.equals("print")) {
         s = new PrintStat(expr());
      } else { //assignment
         match(Token.EQUAL); 
         s = new Assignment(id, expr());
      }
      match(Token.SEMCOL); return s;
   }
   Expression factor() { //override
      if (tok != Token.IDENT) 
         return super.factor();
      else {
         String f = lex.sval;
         match(Token.IDENT);
         if (tok != Token.LEFT)
            return new Variable(f);
         match(Token.LEFT);
         Expression e = expr();
         match(Token.RIGHT);
         return new Function(f, e);
      }
   }
}
