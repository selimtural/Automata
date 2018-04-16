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
class Runtime {
    public static Statement[] last;
    final static String sample = "a=3; b=5; sum=a+b; print sqrt(sum/2);";
    public static Map<String, Float> getMap() { return Variable.map; }
    public static Statement[] parse2(String txt) {
        return (last = new Parser2(txt).parse2());
    }
    public static void parseAndRun(String txt) {
        System.out.println(txt);
        for (Statement s : parse2(txt)) s.run(); 
    }
    public static void main(String... args) { 
        if (args.length == 0) args = new String[] { sample };
        for (String txt : args) parseAndRun(txt);
    }
}
