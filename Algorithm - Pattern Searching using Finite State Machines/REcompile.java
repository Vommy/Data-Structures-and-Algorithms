/**
 *@author Veren Villegas 1574646 
 *@author Ben Clark 1574645
 */
public class REcompile{
    public static void main(String[] args){
        if(args.length > 0){
            String regexp = args[0];
            if(regexp != null){
                PatternParser parser = PatternParser.getInstance(regexp);
                // Verify the input regular expression   
                expression(parser);
                // Build a FSM table from the input regular expression
                new Compiler(regexp); 
            }
        }
        else{
            System.err.println("Usage: java REcompile <regex>");
        }
    }

    /**
     * Handles the expression part of the grammer. 
     * If this method returns without any errors, then the regexp is valid. 
     * @param parser
     */
    private static void expression(PatternParser parser){
        term(parser);
        if(parser.hasMoreData()){
            if(parser.getNextSymbol().equals(")") && parser.getNumBrackets() > 0){
                return;
            }
            expression(parser);
        }
    }

    /**
     * Responsible for the term part of the grammer. 
     * If this method returns without an error, then this part of the grammer is valid. 
     * @param parser
     */
    private static void term(PatternParser parser){
        factor(parser);
        if(parser.hasMoreData()){
            if(parser.isLit(parser.getNextSymbol())){
                term(parser);
            }
            else{
                String nextSymbol = parser.getNextSymbol();
                if(nextSymbol.equals("*") || nextSymbol.equals("+") || nextSymbol.equals("|") || nextSymbol.equals("?")){
                    parser.incrementCounter();
                    if(nextSymbol.equals("|"))
                        term(parser);
                    return;
                }
            }
        }
    }

    /**
     * Responsible for the grammar behind a factor. 
     * If this method returns without an error then the current part of the regex is valid.
     * @param parser
     */
    private static void factor(PatternParser parser){
        if(parser.isLit(parser.getNextSymbol())){
            parser.incrementCounter();
        }
        else{
            if(parser.getNextSymbol().equals("\\")){
                parser.incrementCounter();
                if(parser.hasMoreData()){
                    parser.incrementCounter();
                    return;
                }
                throw new RuntimeException("Invalid Regex. \\ Not followed by a character");
            }
            else if(parser.getNextSymbol().equals("(")){
                parser.incrementCounter();
                parser.incrementNumBrackets();
                expression(parser);
                if(parser.hasMoreData() && parser.getNextSymbol().equals(")")){
                    parser.incrementCounter();
                    parser.decrementNumBrackets();
                    return;
                }
                throw new RuntimeException("Invalid Regex. Brackets improperly formed.");
            }
            else if(parser.getNextSymbol().equals("[")){
                parser.incrementCounter();
                if(parser.hasMoreData()){
                    parser.incrementCounter();
                    if(!parser.hasMoreData())
                        throw new RuntimeException("Invalid expression. Square brackets improperly formed.");
                    while(!parser.getNextSymbol().equals("]"))
                        parser.incrementCounter();
                    parser.incrementCounter();
                }
            }
            else if(parser.getNextSymbol().equals("|")){
                parser.incrementCounter();
                if(parser.hasMoreData()){
                    term(parser);
                    return;
                }
                throw new RuntimeException("Invalid Regex. No expression following infix operator.");

            }
            else{
                throw new RuntimeException("Invalid Regex. Special character improperly placed.");
            }
        }
    }
}