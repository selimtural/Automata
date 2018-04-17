package expr;
 
import java.util.Map;
import java.util.HashMap;

interface Statement {    
    void run();
}
class Assignment implements Statement {
    final String id;  final Expression exp;
    public Assignment(String s, Expression e) { id = s; exp = e; }
    public void run() {
        float v = exp.fValue(); new Variable(id).setValue(v); 
        System.out.println(id+" <== "+v);
    }
    public String toString() { return id+" = "+exp; }
}
class PrintStat implements Statement {
    final Expression exp;
    public PrintStat(Expression e) { exp = e; }
    public void run() { System.out.println(exp.fValue()); }
    public String toString() { return "print "+exp; }
}
class Variable implements Expression  {
    final static Map<String, Float> map = new HashMap<>();
    final String id; 
    public Variable(String s) { id = s; }
    public void setValue(float v) { map.put(id, v); }
    public float fValue() { return map.get(id); }
    public String toPostfix() { return ' '+id; }
    public String toString() { return id; }
    public String toTree() { return id; }
}
class Program implements Runnable {
    final Statement[] prog;
    public Program(Statement[] a) { prog = a; }
    public void run() { for (Statement s : prog) s.run(); }
    public String toString() {
        String t = ""; 
        for (Statement s : prog) t = t+s+";\n"; 
        return t; 
    }
}
public class Runtime {
    public Program last;
    public Object getMap() { return Variable.map; }
    public Program getLast() { return last; }
    public Program parse(String txt) {
        return (last = new Parser2(txt).parse2());
    }
    public void parseAndRun(String txt) {
        System.out.println(txt); parse(txt).run(); 
    }
    public void main() { 
        parseAndRun("k=0; t=1; s=1;");
        parseAndRun("k=k+1; t=t/k; s=s+t;");
    }
    final static String sample = "a=3; sum=a+5; print sqrt(sum/2);";
    public static void main(String[] args) { new Runtime().main(); }
}
