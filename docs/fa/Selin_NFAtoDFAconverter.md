
# **NFA TO DFA CONVERTER**


In DFA, for each input symbol, one can determine the state to which the machine will move. Hence, it is called Deterministic Automaton. However all the state to the NFA is unclear.

**DFA and NFA definition**
**Q**=states

**∑**=input alphabet

**δ**=transition function δ: Q × ∑ → Q

**F**=final state F ⊆ Q

**S**=start state S ⊆ Q



**Note:**
 - The initial state is denoted by an empty single incoming arc.
-   The final state is indicated by double circles. 

## Example 1
Convert the following nfa to dfa.  
![enter image description here](https://www.tutorialspoint.com/automata_theory/images/ndfa.jpg)

Convert the following nfa to dfa
NFA

|            δ    |0                         |1                     |
|----------------|-------------------------------|-----------------------------|
|-> a  |     {a,b,c,d,e}    |       {d,e}   |
|b         |{c}         |{e}         |
|c          |∅|{b}|
| d |     {e}    |     ∅   |
|*e         |∅         |∅         |


We need to figure out where to go for each state.
NFA -> DFA





|            δ'    |0                         |1                     |
|----------------|-------------------------------|-----------------------------|
|-> a  |     a,b,c,d,e    |       d,e   |
|*a,b,c,d,e         |a,b,c,d,e        |b,d,e         |
|*d,e        |e|∅|
| *b,d,e |    c,e   |     e  |
|*e         |∅         |∅         |
|*c, e        |∅      |b        |
|b        |c|e|
| c |    ∅   |     b  |







Nfa ends in e state. Every state goes to e for dfa.
Drawing the dfa chart:
![enter image description here](https://www.tutorialspoint.com/automata_theory/images/dfa_state_diagram.jpg)


## Example 2

NFA
Convert nfa to regex to dfa.
 Regex :   (0+1)*10
 > 1 or 0 may come. 1 and 0 have to come

![
](https://scontent-otp1-1.xx.fbcdn.net/v/t1.0-9/31478694_608320696212201_6169858161520934912_n.jpg?_nc_cat=0&oh=816cfa36ea8f4fa576d04731b556c3b7&oe=5B5B0AF4)



This nfa table:


|            δ    |0                         |1                     |
|----------------|-------------------------------|-----------------------------|
|->q0  |     {q0}    |       {q0,q1}   |
|q1        |{q2}         |∅         |
|*q2         |∅|∅|



NFA -> DFA

|            δ'    |0                         |1                     |
|----------------|-------------------------------|-----------------------------|
|->q0  |     q0    |       q0,q1   |
|  q0,q1         |q0q2        |q0,q1        |
|  *q0q2        |q0      |q0,q1        |


This table drawing:

DFA

![enter image description here](https://scontent-otp1-1.xx.fbcdn.net/v/t1.0-9/31444991_608322076212063_1742860199292567552_n.jpg?_nc_cat=0&oh=569b6757e4b0ddfc2a90fdbe86abfbb1&oe=5B620214)


## Java Application
In my application, it is checked whether the appropriate string is entered in the table.

![enter image description here](https://scontent-frt3-1.xx.fbcdn.net/v/t1.0-9/31882745_609928856051385_5864962891353948160_n.jpg?_nc_cat=0&oh=961c1f066766e83705f23f2593463be8&oe=5B9CE705)


	
If we draw the diagrams here we can see input will be accepted by this regular expression. Start  and final states is known before starting to processing  for each input.

## Java Project : [here](https://github.com/SelinDaldaban/Automata/tree/master/docs/fa/Selin_NFAtoDFAconverter.jar)



![enter image description here](https://scontent-frt3-1.xx.fbcdn.net/v/t1.0-9/31768678_609928862718051_4319210831607758848_n.jpg?_nc_cat=0&oh=5dd4df5d388dc6926846dffc98a6682a&oe=5B4F09CF)

It is reject not suitable for Regex.  
DFA and NFA are shown step by step through which states they pass. At the very end it says it is accepted or rejected.




**References For Examples:**


[https://www.tutorialspoint.com/automata_theory/images/ndfa.jpg](https://www.tutorialspoint.com/automata_theory/images/ndfa.jpg)

[https://www.tutorialspoint.com/automata_theory/images/dfa_state_diagram.jpg](https://www.tutorialspoint.com/automata_theory/images/dfa_state_diagram.jpg)

[https://image.slidesharecdn.com/lec2nfa-140820041615-phpapp02/95/nfa-or-non-deterministic-finite-automata-48-638.jpg?cb=1408508296](https://image.slidesharecdn.com/lec2nfa-140820041615-phpapp02/95/nfa-or-non-deterministic-finite-automata-48-638.jpg?cb=1408508296)



This page made by ***Selin Daldaban***

