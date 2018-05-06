# Nondeterministic Finite Automata(NFA)

   
 **NFA** may not go to any state from the current state on reading an input symbol
So this automata is called non deterministic automata.There are language rules for **NFA**.
That rules accept or reject case check for string.


# **Definition of Nondeterministic Finite Automata**

   **A**= (Q, δ, Σ , s, F)
   
 - **Σ**=input alphabet
 - **Q**=States
 - **δ**=QxE (transition function)
 - **s** is start state
 - **f** is final states(there may be more than one )



## Example:


 The Regular Expression    M=(0+1)*01
 
 
**Define** NFA for M ;
State(s,p,q)
start state s,final state q.
 **{q0,q1,q2}  changed {s,p,q}**
 **Language {a,b}  changed {0,1}**

 **Java Application:** [enter link description here](https://ummugulsumcan.github.io/Automata/fa/ummugulsum_nfa.jar)

## Transition  Matrix:

|                |0                         |1                        |
|----------------|-------------------------------|-----------------------------|
|s|s,p         |s           |
|p          |Ø         |q           |
|q          |Ø  |Ø|



( s,101)->(s,01)->({p,s},1)->(q)  Accept


## Transition  Diagram:


![https://www.geeksforgeeks.org/wp-content/uploads/nfatofdfa_Figure1.png](https://www.geeksforgeeks.org/wp-content/uploads/nfatofdfa_Figure1.png)



# Reference  :
[https://www.geeksforgeeks.org/theory-of-computation-conversion-from-nfa-to-dfa/](https://www.geeksforgeeks.org/theory-of-computation-conversion-from-nfa-to-dfa/)

## This page was made by *Ümmügülsüm Can*