import ...;

class Puzzle extends Automaton {
   //deterministic finite automaton for CW3
   
   static enum State {s, a, b, c} //enumerated class
   public static Class getStates() { return State.class; } 

   public State delta(State q, char c) {
      ...
      return null;  //default is null -- no transition
   } 
   public boolean accept(String w) {
      State q = ...;  //initial State
      //System.out.printf("   %s", q);
      for (int j=0; j<w.length(); j++) {
         char c = w.charAt(j);
         q = delta(q, c);
         //System.out.printf(" --> %s", q);
         if (q == null) return false;
      }
      return (...);  //acceptable?
   } 

   public static final Automaton dfa = new Puzzle();
   public static void main(String[] args) {
     //String REG_EXP = "..."
     Set<Integer> a = ...
     //Set<Integer> r = ... 
     for (int n=1; n<50; n++) {
          String w = Integer.toBinaryString(n);
          if (dfa.accept(w)) a.add(n);
          //if (w.matches(REG_EXP)) r.add(n);
     }
     System.out.println(a); 
     //System.out.println(r);
   }
}
