# Deterministic Finite Automata


  Deterministic Finite Automata is a kind of Finite Automata that has only one state for each inputs. So, every state can determine one state. For instance: 
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

 ![Transition Diagram](http://scanftree.com/automata/images/DFA-for-string-ends-with-ab.png)
 
 As you see shown above, there is a sample transition diagram that has one initial state and one final state and each state(A,B,C) goes one state for each input(a,b).

# Transition Matrix
Transition matrix is a table that do the same function with transition diagram. It shows the next statement for current statement with an input whice is given.



|  Current State  |  Next State for Input b   |  Next State for Input a  |
| ------ | ------ | ------ |
|-> A | A  | B |
|   B | C  | B |
| * C | A  | B |



# Accept or Reject 
These values of Transition Diagram and Matrix consist of a grammer that is generate with rules. We give an input value to program to see this input is accepted or rejected for the grammer. 
To do this, we can use transition diagram or transition matrix easily but to specify the operation step by step, we use yield notations.

Here is a example of yield notation for given grammer above:
 
      -Q: {A,B,C}
      -∑: {a,b}
      -start state : A
      -final state : C
      
       input: ababb
       (A, ababb)  ˫  (B, babb)  ˫ (B, abb)  ˫ (B, bb) ˫ (C, b) ˫ (A, Ɛ) 
                                       A is not final state, then REJECT

       input: abab
      (A, abab)  ˫  (B, bab)  ˫ (C, ab)  ˫ (B, b) ˫ (C, Ɛ)
                                        C is final state, then ACCEPT


You can practice with my DFA sampling application. 
On this link you can download the jar file on GitHub:   [DFA Sampling Application](https://merveozdemir.github.io/Automata/fa/Merveozdemir_dfa.jar)

[Reference for diagram picture](http://scanftree.com/automata/dfa-string-example-15)
