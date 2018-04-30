# NFA
The exact state to which the machine moves cannot be determined. It is called **Non-deterministic Automaton**(NFA).
### Formal Definition of an NDFA

A=(Q, ∑, δ, s, F) 

-   **Q** is finite states.
    
-   **∑** is alphabets (symbols).
    
-   **δ** is the transition function. δ: Q × ∑ → Q
    
-   **s** is a start state. 

-   **F** is final states. 

# TRANSITION DIAGRAM
![enter image description here](https://lh3.googleusercontent.com/G6ENZ45lDEXfhXAyUgJTz0sIbvf0CoADiymyHIUcrKL117L1GDYDaVcqD9xeO2Wi1jpK1B2eo-pb "Transition Diagram")
 ### According to the NFA diagram above:
 -   Q={a, b, c, d}
    
-   ∑={0,1}
    
-   s=a
    
-   F={c}


# TRANSITION MATRIS
![enter image description here](https://lh3.googleusercontent.com/-StQ-Ie2hEbzQpEA9548b5JRo5x5i8atumZHf8OIcuYoSJhcJ7Qui97INyNQbQdD_bZ2AN7TGy-Q "Transition Matris")

We can create a transition matrix by examining the transition diagram.


#### → :    this symbol indicates the start state

####  * :  this symbol indicates  finish states


# YIELD NOTATİON
-    **ⱶ**  :  we use this symbol for yield notation
- The yield notation is used to decide whether to accept the string in automata.
### (q, au) ⱶ (δ(q, a), u)
- **q** is current state
- **au** is current input
- **δ(q, a)** is new state

The yield notation is applied sequentially to all the string elements. If there is no element in the string and is in the final state, the string is accepted.
### Example:
Look at the transition diagram or transition matris.   
Our starting state is 'a'. 
The given string  is "1101".
Lets start!
(a, 1101) ⱶ (d, 101) ⱶ (a, 01) ⱶ (b, 1) ⱶ (c, ɛ)  Accept
'c' is final state and all elements of string visited.
So this string is accepted in this automata.

The given string  is "010010".
(a, 010010) ⱶ (b, 10010)ⱶ (c, 0010)ⱶ (b, 010)ⱶ (b, 10)ⱶ (c, 0)ⱶ (d, ɛ)  Reject
'd' is not final state and all elements of string visited.
So this string is reject in this automata.

If do you want to try some strings in this automata please 
[enter link](https://github.com/AyseSenaFeyiz/Automata/tree/master/docs/fa/AyseSena_NFAYieldNotation.jar)
