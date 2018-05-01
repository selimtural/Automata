package work;

import java.util.Set;
import java.util.EnumSet;
/**
* This class extends Automaton abstract class and work as a Nondeterministic Finite Automaton for (1+0)*10.
* 
*
* @author  MAEyler
* @version 1.0
* @since   2014-03-31
*/
public class NFA extends Automaton {
   //non-deterministic finite automaton for (1+0)*10
   
   /**
   * This enumerated class determines the states of automaton.
   */
   static enum State {a, b, c} //enumerated class
    
   /**
   * This static method returns the enumerated class.
   * @return Class object of enum State.
   */
   public static Class getStates() { return State.class; } 

   /**
   * This method determines and returns the next state of automaton for given state and input char.
   * @param q Current State.
   * @param c Input char of step.
   * @return Calculated next state or states by given inputs. If no transition, null returns.
   */
   public Set<State> delta(State q, char c) {
     if (q==State.a && c=='0') return EnumSet.of(State.a);
     if (q==State.a && c=='1') return EnumSet.of(State.a, State.b);
     if (q==State.b && c=='0') return EnumSet.of(State.c); 
     return null;  //default is null -- no transition
   }
   
   /**
   * This method checks the input string and prints result step by step. Also, if input is accepted, returns true. If not, returns false.
   * @param w Input to check if it's accepted by automaton and print.
   * @return Checks acceptance. If accepted, returns true. If not, returns false.
   */
   public boolean accept(String w) {
      Set<State> S = EnumSet.of(State.a);
      //System.out.printf("%6s", S); //initial State
      for (int j=0; j<w.length(); j++) {
         char c = w.charAt(j);
         Set<State> T = EnumSet.noneOf(State.class); //empty set
         for (State q: S) {
             Set<State> d = delta(q, c);
             if (d!=null) T.addAll(d);
         }
         System.out.println(j+": ("+S+", "+c+") -> "+T);
         //System.out.printf("->%6s", T);
         if (T.isEmpty()) return false;
         S = T;
      }
      return (S.contains(State.c));  //acceptable?
   } 

   /**
   * Contains various inputs for testing.
   */
   static final String[] DEFAULT = {"10", "0101", "01001010"};
   /**
   * An instance of NFA to test.
   */
   public static final Automaton nfa = new NFA();
   /**
   * This is the main method which makes use of test method.
   * @param args Unused.
   */
   public static void main(String[] args) {
      nfa.test(args, DEFAULT); 
   }
}
