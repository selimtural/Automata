# Deterministic Finite Automata


  Deterministic Finite Automata is a kind of Finite Automata that has only one state for each input. So, every state can determine one state. For instance: 
  - for input 1, State A only can determine State B
  - for input 0, State A only can determine State C

  Therefore, we can say this Finite Automata is deterministic because of that rule and also DFA has an initial state and final states like other kind of Finite Automata. The only difference of DFA is that it doesn't have more than one initial state in a grammer. 

  Deterministic Automata is identified by this 5 properties which are (Q, ∑, δ, q0, F)
  - Q  is a finite set of states 
  - ∑ is a finite set of input symbols
  - δ is a transaction that indicatives the next state with given an input and current state. 
  - q0 is initial state and also one of the states of Q
  - F is set of final states, it can be more than one and also subset of Q 
  
# Transition Diagram

  Transition diagram represent what is the next state for each states of Q. It is based on the grammer that is given before.   

 ![Transition Diagram](https://compilandoconocimiento.files.wordpress.com/2017/01/captura-de-pantalla-2017-03-02-a-las-5-24-23-p-m.png?w=363&h=319)
 
 As you see shown above, there is a sample transition diagram that has one initial state and one final state and each state (to make it easier we use ( a,b,c,d ) instead of  (q0 ,q1,q2,q3) ) goes one state for each input(0,1). This grammer is:
  - w has both an even number of 0's and an even number of 1's

# Transition Matrix

Transition matrix is a table that do the same function with transition diagram. It shows the next statement for current statement with an input which is given.



|  Current State  |  Next State for Input 0   |  Next State for Input 1  |
| ------ | ------ | ------ |
|-> * a | c  | b |
|   b | d  | a |
|   c | a  | d |
|  d | b  | c |




# Accept or Reject 

These values of Transition Diagram and Matrix consist of a grammer that is generate with rules. We give an input value to program to see this input is accepted or rejected for the grammer. 
To do this, we can use transition diagram or transition matrix easily but to specify the operation step by step, we use yield notations.

Here is an example of yield notation for given grammer above:
 
      -Q: {a,b,c,d}
      -∑: {0,1}
      -start state : a
      -final state : a
  
      input: 1001
       (a, 1001)  ⊢  (b, 001)  ⊢ (d, 01)  ⊢ (b, 1)  ⊢ (a, ε) 
                                  a is final state, then ACCEPT

      input: 11001
       (a, 11001)  ⊢  (b, 1001)  ⊢ (a, 001)  ⊢ (c, 01)  ⊢ (a, 1) ⊢ (b, ε) 
                                  b is not final state, then REJECT


You can practice with my DFA sampling application which is written in JAVA. On this link you can download the jar file:  [DFA Sampling Application](https://merveozdemir.github.io/Automata/fa/Merveozdemir_dfa.jar)

Also, visit my GitHub profile page:  [https://github.com/merveozdemir](https://github.com/merveozdemir)


# REFERENCES

For transition diagram:

[https://compilandoconocimiento.com/author/compilandoconocimiento/page/4/](https://compilandoconocimiento.com/author/compilandoconocimiento/page/4/)

[https://compilandoconocimiento.files.wordpress.com/2017/01/captura-de-pantalla-2017-03-02-a-las-5-24-23-p-m.png?w=363&h=319](https://compilandoconocimiento.files.wordpress.com/2017/01/captura-de-pantalla-2017-03-02-a-las-5-24-23-p-m.png?w=363&h=319)


Other:

[http://bilgisayarkavramlari.sadievrenseker.com/2008/11/11/belirli-sonlu-otomat-deterministic-finite-automat/](http://bilgisayarkavramlari.sadievrenseker.com/2008/11/11/belirli-sonlu-otomat-deterministic-finite-automat/)



**This page was made by Merve Özdemir**

