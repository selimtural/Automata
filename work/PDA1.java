package work;

/**
* This class extends PDA class and completes the abstract metods. Works as a Push-Down Automaton.
* 
*
* @author  MAEyler
* @version 1.0
* @since   2014-03-31
*/
public class PDA1 extends PDA {

   /**
   * This method determines and returns the next state of automaton for given char inputs.
   * @param c Current State.
   * @param p Next char in stack.
   * @return Calculated next state by given inputs. If no transition, null returns.
   */
    public String delta(char c, char p) {
        if (c=='a' && p=='S') return "aSa";
        if (c=='+' && p=='S') return "+aEa";
        if (c=='a' && p=='E') return "aEa";
        if (c=='=' && p=='E') return "=";
        return null;  //default is null -- no transition
    }
    
    /**
   * Checks and prints the input string for acceptance. Initial char is 'S'. If string is accepted, true returns. If not, false returns.
   * @param w String to be checked for automaton.
   * @return Acceptance result.
   */
    public boolean accept(String w) {
        return accept(w, 'S');
    } 

    /**
   * This is the main method which makes use of test method. PDA is tested with various inputs.
   * @param args Unused.
   */
    public static void main(String[] args) {
        Automaton a = new PDA1();
        a.test("+a=a");
        a.test("a+a=aa");
        a.test("a+aa=aaa");
        a.test("a+aa=aaaaa");
        a.test("a+aa=aa");
        a.test("a+aa=baa");
        a.test("aa+=aa");
    }
}
