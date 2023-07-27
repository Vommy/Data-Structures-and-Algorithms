/**
 * Produces a table for a FSM from the regular expression passed in
 * @author Veren Villegas 1574646 
 * @author Ben Clark 1574645
 */
public class Compiler {

    private String[] exprList, charList;
    private Integer[] statesList, next1List, next2List;

    private PatternParser patPsr;

    //The state we are currently building
    private int state = 0;
    //The expression value we are looking at
    private int j = 0;

    /**
     * The class takes a regular expression as input and prints a FSM table
     * @param expression the regular expression 
     */
    public Compiler(String expression){
        //Initialise arrays to store the FSM table
        patPsr = PatternParser.getInstance(expression);
        exprList = expression.split("");

        charList = new String[exprList.length+1];
        statesList = new Integer[exprList.length+1];
        next1List = new Integer[exprList.length+1]; 
        next2List = new Integer[exprList.length+1];

        //add an empty value at the start of charList for the starting state
        setState(0, "~", null, null);
        state++;

        //Build the FSM table and get the start state
        int startState = expression();

        //Set n1 and n2 of state 0 to the start state of the entire machine
        next1List[0] = startState;
        next2List[0] = startState;

        printTable();
    }

    /**
     * Creates the state(s) for a expression in our grammar
     * @return the start state of the finished expression
     */
    private int expression(){
        int r = term();
        //If theres still more to read repeat
        if(isInBounds()){
            expression();
        }
        return r;
    }

    /**
     * Creates the state(s) for a term in our grammar
     * @return the start state of the finished term
     */
    private int term(){
        int r;
        String currChar = exprList[j];

        // Conditions ordered by precedence

        // Escaped characters (1st Precedence)
        if(currChar.equals("\\")){
            //move past '\' THEN set state of whatever is passed in
            j++;
            currChar = exprList[j];
            setState(state, currChar, state+1, state+1);
            r = state;
            state++; j++;
        }
        // Parentheses (2nd Precedance): NOT IMPLEMENTED
        // Repetition (3rd Precedance)
        else if(currChar.equals("*") || currChar.equals("?")){
            // Called here from factor, set the branch to jump to the preceeding expression or continue, then return
            setState(state, "~", state+1, state+2);
            r = state;
            state++; j++;
        }
        else if(currChar.equals("+")){
            // set a branch to loop back or continue
            setState(state, "~", state-1, state+1);
            r = state;
            state++; j++;
        }
        // Concatenation (4th Precedance): NOT IMPLEMENTED PROPERLY
        // Alternation (5th Precedance)
        else if(currChar.equals("|")){
            // Called here from factor, set the branch to be either one of the next states, then return
            setState(state, "~", state+1, state+2);
            r = state;
            state++; j++;
        }
        else{
            r = factor();
        }
        return r;
    }

    /**
     * Creates the state(s) for our factor in our grammar
     * @return the start state of this finished factor
     */
    private int factor(){
        String currChar = exprList[j];
        int r = -1;

        //IF we are not at the end of our expression, look ahead to match operators
        if(hasMoreSpace()){

            String nextChar = exprList[j+1];

            if(nextChar.equals("*")){
                // increment text index and call term to deal with the * branch
                j++;
                r = term();
                // use the character from the previous state (stored in currChar) and build a state for it in the current state
                setState(state, currChar, state-1, state-1);
                state++;
                return r;
            }
            else if(nextChar.equals("?")){
                // increment text index and call term to deal with the ? branch
                j++;
                r = term();
                // use the character from the previous state (stored in currChar) and build a state for it in the current state
                setState(state, currChar, state+1, state+1);
                state++;
                return r;
            }
            else if(nextChar.equals("|")){
                // increment text index and call term to deal with the | branch
                j++;
                r = term();
                // build the two alternative states
                setState(state, currChar, state+2, state+2);
                state++;
                currChar = exprList[j];
                factor();
                return r;
            }
        }
        //IF we have found a literal, build it, set its next state to the next state built
        if(patPsr.isLit(currChar)){
            setState(state, currChar, state+1, state+1);
            r = state;
            state++; j++;
        }
        return r;
    }

    /**
     * Configures the arrays representing the FSM table to set a state with the values passed
     * @param s state we want to set the values for
     * @param ch mis matched character we want to set for this sate
     * @param next1 the state of the first branch option
     * @param next2 the sate of the second branch option
     */
    private void setState(Integer s, String ch, Integer next1, Integer next2){
        statesList[s] = s;
        charList[s] = ch;
        next1List[s] = next1;
        next2List[s] = next2; 
    }

    /**
     * Determines if we can index the current position without going out of bounds
     * @return true if we can look at the current expression character, else false
     */
    private Boolean isInBounds(){
        if(j < exprList.length){
            return true;
        }
        return false;
    }

    /**
     * Determines if we can look ahead without going out of bounds of our expression passed in.
     * @return true if theres more space, else false
     */
    private Boolean hasMoreSpace(){
        if(j < exprList.length-1){
            return true;
        }
        return false;
    }

    /**
     * Print out the table of the FSM
     * Each branch is printed as '~', every other ch is treted as a literal
     * States increment up from 0 by 1
     */
    private void printTable(){
        System.out.println(" s | ch  1  2");
        System.out.println("---+---+--+--+");
        int currState;

        //FOR the length of the expression passed in, print out the sates, break if we run out of states
        for(currState = 0; currState < charList.length; currState++){
            if(statesList[currState] == null){
                break;
            }
            System.out.println(String.format("%2d", statesList[currState]) + " |  " + charList[currState] + " " + String.format("%2d", next1List[currState]) + " " + String.format("%2d", next2List[currState]));
        }

        //print the final state
        System.out.println(String.format("%2d", currState) + " |  ~  0  0");
    }
}