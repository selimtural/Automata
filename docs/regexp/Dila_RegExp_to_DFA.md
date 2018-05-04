
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

![enter image description here](https://scontent.fsaw1-3.fna.fbcdn.net/v/t1.0-9/31955074_10214578550530688_6519123849764667392_n.jpg?_nc_cat=0&oh=a9d0633c3b1ac88894e3be8b699a96dd&oe=5B9D814E)

Concatenation

                                            r = r1 r2

             Convert Regular Expression to DFA - Compiler Design

![enter image description here](https://scontent.fsaw1-3.fna.fbcdn.net/v/t1.0-9/31899278_10214578550570689_5665379519205212160_n.jpg?_nc_cat=0&oh=5b036aed7ec5ceaa7d11bf5cbcbd2454&oe=5B51EDC3)

Closure
                                            
                                             r = r1*

             Convert Regular Expression to DFA - Compiler Design

![enter image description here](https://scontent.fsaw1-3.fna.fbcdn.net/v/t1.0-9/31928670_10214578550490687_3026631696503013376_n.jpg?_nc_cat=0&oh=7935d228180437ff883ed136c2d9acd9&oe=5B8C2B21)

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


       And my program working with this screen:

![enter image description here](https://scontent.fsaw1-3.fna.fbcdn.net/v/t1.0-9/31919371_10214578694254281_8193370314416062464_n.jpg?_nc_cat=0&oh=1722f0dad4d62655e03d73ff4dee4a73&oe=5B560824)


### Java Project → [here](https://github.com/dilaerbakan/Automata/blob/master/docs/regexp/Dila_RegExp_to_DFA.jar)


   [nfa information]:  <https://tr.scribd.com/doc/74752180/Otomata-Regex-NFA-DFA-Sunumu>
   [Converting a regex to dfa]: <https://stackoverflow.com/questions/30056643/converting-a-regular-expression-to-a-dfa>
   [Converting a regex to dfa]: <https://cs.stackexchange.com/questions/13599/convert-regular-expression-to-dfa>
   [Converting a regex to dfa]: <http://ecomputernotes.com/compiler-design/convert-regular-expression-to-dfa>
  
  [Reference for NFA steps' pictures ](http://ecomputernotes.com/compiler-design/convert-regular-expression-to-dfa)
  
*This web page and program prepared by Dila Erbakan*