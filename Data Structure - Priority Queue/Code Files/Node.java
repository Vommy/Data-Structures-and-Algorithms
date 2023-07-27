public class Node{
	public int priority;
	
	public Node(){
		priority = -1;
	}
	
	public Node(int value){
		if(value >= 0)
			priority = value; 
		else
			priority = -1;
	}
	
	public String toString(){
			return "Priority: " + priority; 
	}
}
