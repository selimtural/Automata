package work;
/**
* This class extends Automaton abstract class and work as a Deterministic Finite Automaton for (1+0)*10.
* 
*
* @author  MAEyler
* @version 1.0
* @since   2014-03-31
*/
public class DFA extends Automaton {
   //deterministic finite automaton for (1+0)*10
   
   /**
   * This enumerated class determines the states of automaton.
   */
   static enum State {A, B, C} //enumerated class
    
   /**
   * This static method returns the enumerated class.
   * @return Class object of enum State.
   */
   public static Class getStates() { return State.class; } 

   /**
   * This method determines and returns the next state of automaton for given state and input char.
   * @param q Current State.
   * @param c Input char of step.
   * @return Calculated next state by given inputs. If no transition, null returns.
   */
   public State delta(State q, char c) {
      if (q==State.A && c=='0') return State.A;
      if (q==State.B && c=='0') return State.C;
      if (q==State.C && c=='0') return State.A; 
      if (c=='1') return State.B;
      return null;  //default is null -- no transition
   }
   
   /**
   * This method checks the input string and prints result step by step. Also, if input is accepted, returns true. If not, returns false.
   * @param w Input to check if it's accepted by automaton and print.
   * @return Check result. If accepted, returns true. If not, returns false.
   */
   public boolean accept(String w) {
      State q = State.A;  //initial State
      System.out.printf("  %s", q);
      for (int j=0; j<w.length(); j++) {
         char c = w.charAt(j);
         State t = delta(q, c);
         //System.out.println(j+": ("+q+", "+c+") -> "+t);
         System.out.printf(" -> %s", t);
         if (t == null) return false;
         q = t;
      }
      return (q==State.C);  //acceptable?
   } 

   /**
   * Contains various inputs for testing.
   */
   static final String[] DEFAULT = {"10", "0101", "01001010"};
    
   /**
   * An instance of DFA to test.
   */
   public static final Automaton dfa = new DFA();
    
   /**
   * This is the main method which makes use of test method.
   * @param args Unused.
   */
   public static void main(String[] args) {
      dfa.test(args, DEFAULT); 
   }
}
