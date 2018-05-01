# Nondeterministic Finite Automaton(NFA)

   
 **NFA** may not go to any state from the current state on reading an input symbol
So this automata is called non deterministic automata.There are language rules for **NFA**.
That rules accept or reject case check for string.


# **Definition of nondeterministic finite automaton**

   **A**= (Q, δ, Σ , s, F)
   
 - **Σ**=input alphabet
 - **Q**=States
 - **δ**=QxE (transition function)
 - **s** is start state
 - **f** is final states(there may be more than one )



## Example:

 The Regular Expression  M=(101+10)*

**Define** NFA for M ;
State(s,p,q)
start state s,final state s.



## Transition  Matrix:

|                |0                         |1                        |
|----------------|-------------------------------|-----------------------------|
|s|Ø          |p           |
|p          |{s,q}          |Ø           |
|q          |Ø  |s|





My application:
https://ummugulsumcan.github.io/Automata/fa/ummugulsum_nfa.jar
## Transition  Diagram:


![enter image description here](https://scontent-otp1-1.xx.fbcdn.net/v/t1.0-9/31531418_2035738919975931_554771270537838592_n.jpg?_nc_cat=0&oh=6b0412042085ccfdbd23bf4ed8cd9ce1&oe=5B53C382)
```
( s,1010)->(p,010)->({s,q},10)->({s,p},0)->({s,q})  Accept
(s,111)->(p,11)->()  Reject

