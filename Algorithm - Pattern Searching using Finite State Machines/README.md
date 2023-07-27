# Running Format
To search a file using a regular expression
```
javac *.java
java REcompile "regular expression" | java REsearch "filename.txt"
```
# Running Examples
no need to test for anything else

Escaped character examples (search for 'they?') Note on linux you have to use 2 backslashes to prevent them from escaping in the terminal
```
java REcompile "they\\?" | java REsearch "MobyDick.txt"
```
Repetition examples (god or good), (son or soon), (year or years)
```
java REcompile "go*d" | java REsearch "MobyDick.txt"
```
```
java REcompile "so+n" | java REsearch "MobyDick.txt"
```
```
java REcompile "years?" | java REsearch "MobyDick.txt"
```
Alternation example ('these' or 'those')
```
java REcompile "the|ose" | java REsearch "MobyDick.txt"
```
Example containing multiple special characters (soon , noon , son , non , so , no )
```
java REcompile "s|no+n? " | java REsearch "MobyDick.txt"
```
# File Layout
REcompile.java
- Uses PatternParser.java which has methods to help with pattern parsing
- Verifys regular expression input
- Calls Compiler.java to compile a FSM table

Compiler.java
- Gets a regular expression passed in through the constructor
- Prints to standard output a table for a FSM from the regular expression 

PatternParser.java
- Defines a singleton class used for parsing a regular expression. 

REsearch.java
- Gets the file name passed in as a command line argument
- Gets the FSM table from standard input 
- Uses the table to search for patterns in the file

# Grammar
The context free grammar that we implemented in the current program.
E = Expression, T = Term, F = Factor
- E -> T
- E -> TE
- T -> F
- T -> F*
- T -> F+
- T -> F?
- T -> F | T
- F -> \anySymbol
- F -> anyLiteral
- F -> (E)

# Incomplete parts of our assignment
Our REcompiler.java does verify the regular expression is valid, but Compiler.java doesn't build a table properly for everything. 
Compiler.java and the Grammar listed above doesn't account for these:
- '(' and ')' are treated as literals instead of parenthes
- '[' and ']' are treated as literals instead of alternation
- '.' is treated as a literal instead of as a wildcard
- concatenation having higher precedence than alternation
