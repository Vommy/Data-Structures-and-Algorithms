import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/*
 * @author Veren Villegas 1574646
 * @author Ben Clark 1574645
 */
public class KMPtable{
    /**
     * Takes a pattern and computes it's string array to use in KMPsearch, putting the result into a file. 
     * @param args Command line arguments. Expects a pattern as args[0]
     */
    public static void main(String[] args){
        String pattern = null;
        //Take command line argument for the string we are searching
        try{
            if(args.length > 0)
                pattern = args[0];
            else{
                System.out.println("EXPECTED USAGE: java KMPtable <\"pattern\">");
            }
        }catch(Exception e){
            System.err.println("Error: Error in Main... " + e.getMessage());
            return;
        }
        if(pattern != null){
            //Split the pattern into individual elements. This will form the top of our skip array,
            String[] patternElements = pattern.split("");
            //We now need to get each unique element of the pattern to use as the side of our skip array. 
            ArrayList<String>uniqueElements = getUniqueElements(patternElements);
            //Compute the skip array and then output it.
            ArrayList<Integer> skipArrayNum = calculateSkipArray(uniqueElements, patternElements);
            outputSkipArray(uniqueElements, patternElements, skipArrayNum);
        }
    }

    /**
     * Outputs the skip array calculated by calculateSkipArray() into an output file skipArray.txt
     * @param uniqueElements The unique elements of the pattern
     * @param patternElements The pattern itself
     * @param skipArrayNum The skip array numbers for each element
     */
    private static void outputSkipArray(ArrayList<String> uniqueElements, String[] patternElements, ArrayList<Integer> skipArrayNum){
        File file = new File("skipArray.txt");
        try{
            FileOutputStream fileWriter = new FileOutputStream(file);
            byte[] stuff2Print = new byte[256];
            //Output the first row, which is the pattern itself
            stuff2Print = (" "+ "\t").getBytes();
            fileWriter.write(stuff2Print);
            for(int i = 0; i < patternElements.length; i++){
                try{
                    stuff2Print = (patternElements[i] + "\t").getBytes();
                    fileWriter.write(stuff2Print);
                }catch(Exception e){}
            }
            stuff2Print = "\n".getBytes();
            fileWriter.write(stuff2Print);
            //Output the unique characters along with the corresponding skip values
            for(int i = 0; i < uniqueElements.size(); i++){
                stuff2Print = (uniqueElements.get(i) + "\t").getBytes();
                fileWriter.write(stuff2Print);
                for(int j = 0; j < patternElements.length; j++){
                    stuff2Print = ((skipArrayNum.get(0)+"\t").getBytes());
                    fileWriter.write(stuff2Print);
                    skipArrayNum.remove(0);
                }
                stuff2Print = "\n".getBytes();
                fileWriter.write(stuff2Print);
            }
            fileWriter.flush();
            fileWriter.close();
        }catch(FileNotFoundException e){ System.err.println("Error: FileNotFoundException occured in outputSkipArray() " + e.getMessage());}
        catch(IOException e){System.err.println("Error: IOException occurred in outputSkipArray()" + e.getMessage());}
    }

    /**
     * Calculates the skip array for each element in the pattern
     * @param uniqueElement The unique elements of the pattern
     * @param patternElements The pattern to search for
     * @return
     */
    private static ArrayList<Integer> calculateSkipArray(ArrayList<String> uniqueElement, String[] patternElements){
        ArrayList<Integer> skipArrayNum = new ArrayList<Integer>();
        //Calculate the skip values for each unique element.
        for (String uniqueElem : uniqueElement) {
            //Compare it against each character in the pattern.
            for(int point = 1; point <= patternElements.length; point++){
                //We have found a match
                if(uniqueElem.compareTo(patternElements[point - 1]) == 0)
                    skipArrayNum.add(0);
                //We have found a mismatch
                else{
                    //The unique element is the wildcard (Item at the end of the list)
                    if(uniqueElement.indexOf(uniqueElem) == uniqueElement.size() -1)
                        skipArrayNum.add(point);
                    //Check to see if there is any good prefix from everything up to this mismatch. Everything up to the mismatch has been a match. 
                    //The number of iterations (represented by the iterations variable) we have to go through is the skip value we return
                    else{
                        int iterations = 1;
                        String stringWindow = "";
                        String test[];
                        //Initializing the string window to be everything we have seen so far + the mismatch character.
                        for(int k = 0; k < point - 1; k++)
                            stringWindow += patternElements[k];
                        stringWindow += uniqueElem;
                        //Compare the string window against the pattern until there is no good match. 
                        while(stringWindow != ""){
                            test = stringWindow.split("");
                            if(test.length == 1){
                                stringWindow = test[0];
                            }
                            else{
                                stringWindow = "";
                                for(int h = 1; h < test.length; h++){
                                    stringWindow += test[h];
                                }
                            }
                            String pattern2Compare = "";
                            for(int h = 0; h < test.length - 1; h++){
                                pattern2Compare += patternElements[h];
                            }
                            if(pattern2Compare.compareTo("") == 0 || pattern2Compare.compareTo(stringWindow) == 0){
                                skipArrayNum.add(iterations);
                                break;
                            }   
                            iterations++;
                        }
                    }
                }
            }
        }
        return skipArrayNum;
    }

    /**
     * Gets the unique elements of a supplied pattern, ensuring there are no duplicates.
     * @param patternElements The pattern to look for
     * @return An array list of unique elements from the pattern.
     */
    private static ArrayList<String> getUniqueElements(String[] patternElements){
        ArrayList<String> uniqueElements = new ArrayList<String>();
        for (String patternElement : patternElements) {
            if(!(uniqueElements.contains(patternElement)))
                uniqueElements.add(patternElement);
        }
        //We will use an asteriks to represent a wildcard pattern.
        uniqueElements.add("*");
        return uniqueElements;
    }
}