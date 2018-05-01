package work;
/**
* This abstract class extends Automaton abstract class and contains fundamentals of a Push-Down Automaton.
* 
*
* @author  MAEyler
* @version 1.0
* @since   2014-03-31
*/
public abstract class PDA extends Automaton {

   /**
   * This abstract method determines and returns the next state of automaton for given char inputs.
   * @param c Input string's next char.
   * @param p Char that poped from stack.
   * @return Finds valid transition by given inputs. If no transition, null returns.
   */
    abstract public String delta(char c, char p);
    
   /**
   * This method checks the input string for automaton and prints result step by step. Also, if input is accepted, returns true. If not, returns false.
   * @param w Input to check if it's accepted by automaton and print.
   * @param init Initial char to push into stack.
   * @return Checks string. If accepted, true returns. If not, false returns.
   */
    public boolean accept(String w, char init) {
        int m = w.length(); 
        Stack s = new Stack();
        s.push(init); int i=0;
        while (!s.isEmpty()) {
            char c = (i<m ? w.charAt(i) : 0);
            //System.out.printf("%s %c %s %n", i, c, s);
            char p = s.pop();
            if (p == c) { //stack matches input
                i++; System.out.printf("%5s", ' ');
            } else { //find a valid transition
                String d = delta(c, p);
                if (d == null) return false;
                s.push(d);
            }
        }
        //System.out.printf("%s   %s %n", i, s);
        return (i == m);
    }
}
