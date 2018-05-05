package work;

import java.util.Arrays;

/**
* This class works as stack data structure contains char. Contains methods of operations a stack needs.
* 
*
* @author  MAEyler
* @version 1.0
* @since   2014-03-31
*/
public class Stack {
    /**
   * This static final int contains the maximum size of stack.
   */
    final static int M = 10;
    /**
   * This variable contains the immediate size of stack.
   */
    int n; 
    
    /**
   * This array contains the char data of stack.
   */
    char[] data; 

    /**
   * This constructer method clear the stack.
   */
    public Stack() { 
        clear();
    }
    
    /**
   * This method empty the stack.
   */
    public void clear() {
        n = 0; data = new char[M];  
    }
    
    /**
   * This method checks the stack emptiness.
   * @return If stack is empty, true returns. If not, false returns.
   */
    public boolean isEmpty() {
        return (n == 0);
    }
    
    /**
   * This method adds new char to the stack's top.
   * @param c Char that will be added to stack.
   */
    public void push(char c) {
        if (n == data.length)  //larger array is needed
            data = Arrays.copyOf(data, n+M);
        data[n++] = c;  //increment n after data access
    }
    
    /**
   * This method adds a string's chars separately into the stack.
   * @param s String that chars will be added into stack.
   */
    public void push(String s) { 
        for (int i=s.length()-1; i>=0; i--) 
            push(s.charAt(i));  //push chars in reverse
    }
    
    /**
   * This method returns the char on top of the stack and deletes char from stack.
   * @return Char on the top of the stack.
   */
    public char pop() {
        return data[--n];  //decrement n before data access
    }
    
    /**
   * This method returns the elements of stack as a string. Chars parsed in order.
   * @return String created by stack data.
   */
    public String toString() {
        String s = "";
        if (n > 2*M) s += data[n-1]+"..."+data[0];
        else for (int i=n-1; i>=0; i--) 
            s += data[i];  //add chars in reverse
        return "["+s+"] "+n;
    }
    
    /**
   * This main method pushes and pops various inputs to test stack.
   * @param args Unused.
   */
    public static void main(String[] args) {
        Stack s = new Stack();
        s.push("Z");
        System.out.println(s);
        s.push("bir"); s.push("iki"); s.push("uc");
        System.out.println(s);
        s.pop(); s.pop(); s.push("son");
        System.out.println(s);
        s.push("X");
        System.out.println(s);
    }
}
