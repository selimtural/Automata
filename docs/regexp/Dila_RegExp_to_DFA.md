
# Regex To Dfa 

Regular expressions are used to represent the language of finite automata.
This package takes a regular expression and transforms it into a deterministic finite automata (DFA)

# Features
Regular expression can be converted into DFA by the following methods:

   (i) Thompson's subset construction
   
         • Given regular expression is converted into NFA

         • Resultant NFA is converted into DFA

(ii) Direct Method

         • In direct method, given regular expression is converted directly into DFA.

# Steps
Rules for Conversion of Regular Expression to NFA
 
• Union
                                     
                                     r = r1 + r2

             Convert Regular Expression to DFA - Compiler Design

Concatenation

                                            r = r1 r2

             Convert Regular Expression to DFA - Compiler Design

Closure
                                            
                                             r = r1*

             Convert Regular Expression to DFA - Compiler Design

Ɛ –closure
Ɛ - Closure is the set of states that are reachable from the state concerned on taking empty string as input. 
It describes the path that consumes empty string (Ɛ) to reach some states of NFA.

# What is NFA ?

> NFA state transitions are determined 
> by the current state and partial use of 
> the input information. In many future
> cases there may be the same entry
> and the same situation. The machine 
> may pass any of these situations.

 ### What does the NFA do ?
* They are not used in real modeling of computers.
* Used to simplify the definition of Automata.
* Each non-deterministic automaton has a deterministic counterpart.

### DFA vs NFA

* Both have input alphabet, transition function, initial state and result state.
* NFA and DFA are separated from each other in function transfer type.
* NFA application and software are much easier. Therefore, NFA use is more than DFA.
* Regular languages are defined by the NFA. Since NFA is the DFA language, it can also identify the NFA if it defines the language.

### Regex To DFA Tech

This application is a java application and includes 11 java classes - 1 java form

Converter.java - the class which converting 
Helper.java - Auxiliary functions
Main.java - display showing converting operations form
Relation.java - class holding associations between Nfa states
State.java - Class in which user-entered Nfa states are kept
State_Relation.java - class holding associations between Nfa states
State_Relation_Dfa.java - class holding associations between Dfa states
StateDfa.java - Class in which created Dfa states are kept
StateDfaList.java - List of Dfa states created by the program
StateList.java - List of Nfa states entered by users  
StateNfa.java - Class in which created Nfa states are kept
m
       And the program working with this screen:

![enter image description here](https://scontent-vie1-1.xx.fbcdn.net/v/t1.0-9/31486938_10214539887164128_3025872635616709190_n.jpg?_nc_cat=0&oh=d5e15894994ececd77389232608e4cc7&oe=5B6205F3)



   [nfa information]:  <https://tr.scribd.com/doc/74752180/Otomata-Regex-NFA-DFA-Sunumu>
   [Converting a regex to dfa]: <https://stackoverflow.com/questions/30056643/converting-a-regular-expression-to-a-dfa>
   [Converting a regex to dfa]: <https://cs.stackexchange.com/questions/13599/convert-regular-expression-to-dfa>
   [Converting a regex to dfa]: <http://ecomputernotes.com/compiler-design/convert-regular-expression-to-dfa>
  