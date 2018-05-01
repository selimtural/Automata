package work;
/**
* This abstract class contains the fundamental methods of an automaton.
* 
*
* @author  MAEyler
* @version 1.0
* @since   2014-03-31 
*/

public abstract class Automaton {
   /**
   * This is an abstract method for checking the acceptance of input string.
   * @param w String input that checked for acceptance.
   * @return Returns boolean result that shows is input string accepted.
   */
   public abstract boolean accept(String w);
   
   /**
   * This method checks the inputs and prints the result step by step.
   * @param w Input string of Automaton.
   */
   public void test(String w) {
      header(w);
      boolean result = accept(w);
      System.out.printf("  %s", (result ? "Accept" : "Reject"));
      System.out.println(); System.out.println();
   }
    
   /**
   * Overloaded method for multiple input as array.
   * @param args String array that contains the strings to test.
   * @param def String array that contains the strings to test.
   * @return Nothing.
   */
   void test(String[] args, String[] def) {
      if (args.length==0) args = def;
      for (String w: args) test(w);
   }
   
   /**
   * This static method prints steps of Automaton.
   * @param w Input string of Automaton to print steps.
   */
   static void header(String w) {
      for (int j=0; j<w.length(); j++) 
          System.out.printf("%5s", w.charAt(j));
      System.out.println();
   }
}
