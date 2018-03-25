package work;

class PDA1 extends PDA {

    public String delta(char c, char p) {
        if (c=='a' && p=='S') return "aSa";
        if (c=='+' && p=='S') return "+aEa";
        if (c=='a' && p=='E') return "aEa";
        if (c=='=' && p=='E') return "=";
        return null;  //default is null -- no transition
    } 
    public boolean accept(String w) {
        return accept(w, 'S');
    } 

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
