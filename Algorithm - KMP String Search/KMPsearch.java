import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
/*
 * @author Veren Villegas 1574646
 * @author Ben Clark 1574645
 */
public class KMPsearch{

    //Global arrays that store the Skip Array Table
    private static String[] pattern;
    private static String[] characters;
    private static int[][] skipMatrix;

    public static void main(String[] args){

        if(args.length < 2){
            System.out.println("Incorrect command line input: you must input two filenames with their extensions");
            return;
        }

        //Read in the skip array calculated by KMPtable from a file and store it in global arrays
        File tableFile = new File(args[0]);
        setupSkip(tableFile);

        //Use the skip array to find occurences of the string inside a text file.
        int mark = 0;
        int point = 0;
        int patternPos = 0;

        try{
            File textFile = new File(args[1]);
            BufferedReader reader = new BufferedReader(new FileReader(textFile));
            String line = "";
            int lineCount = 0;

            //Read a line and search each character
            while((line = reader.readLine()) != null){
                lineCount++;
                String[] lineArray = line.split("");
                
                //Get the skip value for each character, THEN skip by the amount
                while(mark <= (lineArray.length - pattern.length)){
                    int skip = getSkip(patternPos, lineArray[point]);
                    
                    //BASE CASE: IF skip has equalled 0 for the length of the pattern, match
                    if(skip == 0 && patternPos == (pattern.length -1)){
                        System.out.print(lineCount + " " + (mark + 1) + "\n");
                        //reset values and read a new line
                        point = 0;
                        mark = 0;
                        break;
                    }
                    //IF the skip is 0, we found a match, don't skip ahead
                    else if(skip == 0){
                        point++;
                        patternPos++;
                    }
                    //ELSE skip ahead
                    else if(skip > 0){
                        patternPos = 0;
                        mark += skip;
                        point = mark;
                    }
                }
                mark = 0;
                point = 0;
                patternPos = 0;
            }
            reader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR: KMPsearch.main could not find the file");
        }
        catch(IOException e){
            System.out.println("ERROR: KMPsearch.main IOException occured when reading from the file");
        }
    }

    /**
     * Get the skip value for a character at a position in the pattern
     * @param patternIndex the position of the pattern we are currently in
     * @param character the characer we want to find the skip value for
     * @return the skip array value
     */
    private static int getSkip(int patternIndex, String character){
        int index = -1;
        //get the index of the matching character in characters[]
        for(int i = 0; i < characters.length; i++){
            if(characters[i].equals(character)){
                index = i;
                break;
            }
        }
        //if none of the characters match, get the index of the mismatched character
        if(index == -1){
            index = characters.length-1;
        }
        
        int[] rowArray = skipMatrix[index];
        return rowArray[patternIndex];
    }

    /**
     * Reads the skip array table and initialises the global arrays that store 
     * the Skip Array Table with the values
     * @param tableFile the file where the skip array table is stored
     */
    private static void setupSkip(File tableFile){
        try{
            int numSkipMatrixRows = (getFileLength(tableFile)) - 1;
            if(numSkipMatrixRows == -1){
                return;
            }
            BufferedReader reader = new BufferedReader(new FileReader(tableFile));
            
            //Read in the pattern which is at the first line
            StringBuilder sb = new StringBuilder(reader.readLine());

            //The pattern will be in for format of:  x y x y z
            //So we must get every second character after the first two spaces
            int length = sb.length();
            String tmpPattern = "";
            for(int i = 2; i < length; i = i + 2){
                tmpPattern += String.valueOf(sb.charAt(i));
            }

            //System.out.println("Stringbuilder: |" + tmpPattern + "|");
            pattern = tmpPattern.split("");
                        
            characters = new String[numSkipMatrixRows];
            skipMatrix = new int[numSkipMatrixRows][];

            int lineCount = 0;
            String line = "";
            //Every line read will be formatted as:x 0 1 2 3 4
            //store the unique character, and the array of skip values
            while((line = reader.readLine()) != null){

                //Get the unique character which is at the first postion
                sb = new StringBuilder(line);
                characters[lineCount] = String.valueOf(sb.charAt(0));

                //remove the first two characters
                sb.delete(0, 2);

                //turn the string building into an array of integers seperated by a tab space
                String[] lineArray = (sb.toString()).split("\t");

                //add the skip values to a skipRow array
                int[] skipRow = new int[pattern.length];
                for(int i = 0; i < lineArray.length; i++){
                    try{
                        skipRow[i] = Integer.parseInt(lineArray[i]);
                    }
                    catch(NumberFormatException e){
                        System.out.println("WARNING: KMPsearch.setupSkip(File) failed to convert string |" + lineArray[i] + "| to int");
                    }
                }
                skipMatrix[lineCount] = skipRow;

                lineCount++;
            }
            //TESTING INTERPRETED SKIP ARRAY TABLE
            /*System.out.println("  " + Arrays.toString(pattern));
            for(int i = 0; i < numSkipMatrixRows; i++){
                System.out.print(characters[i] + " ");
                System.out.print(Arrays.toString(skipMatrix[i]) + "\n");
            }*/
            reader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR: KMPsearch.main could not find the file");
        }
        catch(IOException e){
            System.out.println("ERROR: KMPsearch.main IOException occured when reading from the file");
        }
    }

    /**
     * Count the number of rows of text in the file 
     * @param filename the name of the file
     * @return the row count
     */
    private static int getFileLength(File file){
        int count = 0;
        //Read through the whole file counting each new line
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while(reader.readLine() != null){
                count++;
            }
            reader.close();
        }
        catch(FileNotFoundException e){
            System.out.println("ERROR: KMPsearch.getFileLength(filename) could not find the file");
            count = -1;
        }
        catch(IOException e){
            System.out.println("ERROR: KMPsearch.getFileLength(filename) IOException occured when reading from the file");
            count = -1;
        }
        return count;
    }

}