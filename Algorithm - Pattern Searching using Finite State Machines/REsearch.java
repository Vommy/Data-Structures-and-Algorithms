import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Uses the FSM table from standard input to search for 
 * patterns in the file passed in as a command line argument
 * @author Veren Villegas 1574646 
 * @author Ben Clark 1574645
 */
public class REsearch {

    //Class scope arrays to store the pattern's mismatches characters and branching states
    private static String[] misMatchChar = new String[0];
    private static Integer[] next1 = new Integer[0];
    private static Integer[] next2 = new Integer[0];

    /**
     * Accepts from standard input the table produced by Compiler.java, THEN search the text file passed in the command line.
     * For each line of text in the file, search for the pattern, if we found the pattern print out that line of text.
     * @param args accepts a file containing text to read in to search for a pattern
     */
    public static void main(String[] args){
        //Open the text file passed in
        if(args.length > 0){
            try{
                File file = new File(args[0]);
                //Read in the compiler output from standard input
                String line;
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                line = reader.readLine(); line = reader.readLine();
                while((line = reader.readLine()) != null){
                    String[] lineArray = line.split(" ");
                    String test = "";
                    //Remove any formatting to get the characters and next states.
                    for(int i = 0; i < lineArray.length; i++){
                        if(!lineArray[i].isBlank()){
                            test += lineArray[i] + " ";
                        }
                    }
                    //Split the formatted line by the spaces to get each individual element of the table.
                    lineArray = test.split(" ");
                    String[] tempMismatch = misMatchChar;
                    Integer[] tempNext1 = next1;
                    Integer[] tempNext2 = next2;
                    //Increment the size of the arrays used in searchText
                    misMatchChar = new String[misMatchChar.length + 1];
                    next1 = new Integer[next1.length + 1];
                    next2 = new Integer[next2.length + 1];
                    //Copy the original values of the arrays
                    for(int i = 0; i < tempMismatch.length; i++){
                        misMatchChar[i] = tempMismatch[i];
                        next1[i] = tempNext1[i];
                        next2[i] = tempNext2[i];
                    }
                    //This line has a character that is not a space.
                    if(lineArray.length == 5){
                        misMatchChar[misMatchChar.length - 1] = lineArray[2];
                    }
                    //The character was a space that was removed by the formatting earlier.
                    //Presume that the character was a space and add it to the array of mismatched characters.
                    else if(lineArray.length == 4){
                        misMatchChar[misMatchChar.length - 1] = " ";
                    }
                    next1[next1.length - 1] = Integer.parseInt(lineArray[lineArray.length - 2]);
                    next2[next2.length - 1] = Integer.parseInt(lineArray[lineArray.length - 1]);
                }
                //For each line in the file, read the line in and search for the pattern.
                //If the pattern exists in a line, print the line out.
                int lineNum = 1;
                reader = new BufferedReader(new FileReader(file));
                while((line = reader.readLine()) != null){
                    String[] textArray = line.split("");
                    if(searchText(textArray)){
                        System.out.println("Line " + lineNum + " : " + line);
                    }
                    lineNum++;
                }
                reader.close();
            }catch(Exception e){
                System.err.println(e.getMessage());
            }

        }
        else{
            System.out.println("Exepected usage: java REsearch <textfile>");
        }

        /* TESTING COMMENTS
        //textArray should contain a line of text from the file
        String text = "moooooon is rising tonigmoo";
        String[] textArray = text.split("");

        //Initialise variables (these should be read in from Compiler.java)
        misMatchChar = new String[]{"~", "m","~","o","n","~"};
        next1 = new Integer[]{1,2,3,2,5,0};
        next2 = new Integer[]{1,2,4,2,5,0};

        //Search the array of text and print the line if it contains the string
        if(searchText(textArray)){
            System.out.println(text);
        }
        */
    }

    /**
     * Search the array of text using the FSM table stored in misMatchChar, next1, and nex1
     * @param text to search for the pattern
     * @return TRUE IF we found the pattern, ELSE FALSE
     */
    private static Boolean searchText(String[] text){

        int pointText = 0;
        int state = 0;

        //FOR each text character, try to match it with the pattern, if fail reset pattern index to 0, and text index to begining +1
        for(int markText = 0; markText < text.length; markText++){
            //Reset the text pointer to the new text marker
            pointText = markText;

            //While we are successfully matching out pattern to the text, else break out
            while(true){

            
                //IF we have reached the final branch then finish
                if(misMatchChar[state].equals("~") && next1[state] == 0){
                    return true;
                }

                String currTextChar = text[pointText];
                String currPatternChar = misMatchChar[state];
                Integer next1State = next1[state];
                Integer next2State = next2[state];
                String next1StateMismatch = misMatchChar[next1State];
                String next2StateMismatch = misMatchChar[next2State];


                //IF the mis matched character is a branch
                if(misMatchChar[state].equals("~")){

                    //IF next1 state is a branch OR a charcter matching our text THEN change our state to the state in next1
                    if(next1StateMismatch.equals("~") || next1StateMismatch.equals(currTextChar)){
                        state = next1State;
                        continue;
                    }
                    //IF next2 state is a branch OR a charcter matching our text THEN change our state to the state in next2
                    else if(next2StateMismatch.equals("~") || next2StateMismatch.equals(currTextChar)){
                        state = next2[state];
                        continue;
                    }
                    //ELSE FAIL, we can't go to next1 or next2, reset
                    else{
                        break;
                    }
                }
                //Else the mis matched character is a literal
                else{
                    //IF the text character matches the pattern literal, continue
                    if(currPatternChar.equals(currTextChar)){

                        //IF we have matched the whole pattern, return true (next n1 are 0)
                        if(next1State == 0){
                            return true;
                        }
 
                        //IF we haven't reached the end of the patttern go to the next1 state
                        pointText++;
                        if(pointText < text.length){
                            state = next1[state];
                            continue;
                        }
                        else{
                            break;
                        }
                    }
                    //Else its a fail
                    else{
                        break;
                    }
                }
            }
            //Could't match input from that text character, reset state and move to next text character
            state = 0;
        }
        //We searched the whole string and didn't find the pattern
        return false;
    }
}
