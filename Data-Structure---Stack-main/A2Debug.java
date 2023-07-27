public class A2Debug {
    public static void main(String [] args){
        
        Stack stack = new Stack();
        
        stack.dump(); //output: blank new line
        System.out.println("Stack length: " + stack.length()); //output: Stack length: 0
        System.out.println("Stack empty=" + stack.isEmpty()); //output: Stack empty=true
        System.out.println(stack.peek()); //output: empty string
        
        stack.push("123");
        stack.dump(); //output: 123->
        System.out.println("Stack length: " + stack.length()); //output: Stack length: 1
        System.out.println(stack.peek()); //output: 123
        
        stack.push("$#$q*(*(*)(*()!");
        stack.dump(); //output: $#$q*(*(*)(*()!->123->
        System.out.println("Stack length: " + stack.length()); //output: Stack length: 2
        System.out.println(stack.peek()); //output: $#$q*(*(*)(*()!
        
        stack.push("asdfw");
        stack.dump(); //output: asdfw->$#$q*(*(*)(*()!->123->
        System.out.println("Stack length: " + stack.length()); //output: Stack length: 3
        System.out.println("Stack empty=" + stack.isEmpty()); //output: Stack empty=false
        System.out.println(stack.peek()); //output: asdfw
        
        Node data = stack.pop();
        System.out.println("Removed node: " + data.value); //output: Removed node: asdfw
        stack.dump(); //output: $#$q*(*(*)(*()!->123->
        
        data = stack.pop();
        System.out.println("Removed node: " + data.value); //output: Removed node: $#$q*(*(*)(*()!
        stack.dump(); //output: 123->
        
        data = stack.pop();
        System.out.println("Removed node: " + data.value); //output: Removed node: 123
        stack.dump(); //output: blank new line
        
        Parser parser = new Parser();

        String equation = "";
        System.out.println(equation + " : " + parser.check(equation)); //output:  : true
        
        equation = "(";
        System.out.println(equation + " : " + parser.check(equation)); //output: ( : false
        
        equation = ")";
        System.out.println(equation + " : " + parser.check(equation)); //output: ) : false
        
        equation = "()";
        System.out.println(equation + " : " + parser.check(equation)); //output: () : true
        
        equation = "(()";
        System.out.println(equation + " : " + parser.check(equation)); //output: (() : false
        
        equation = "(((())()))";
        System.out.println(equation + " : " + parser.check(equation)); //output: (((())())) : true
        
        equation = "(((()(()))";
        System.out.println(equation + " : " + parser.check(equation)); //output: (((()(())) : false
        
        equation = null;
        System.out.println(equation + " : " + parser.check(equation)); //output: null : false
        
    }
}