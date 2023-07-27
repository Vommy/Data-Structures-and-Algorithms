public class AssignmentFourDebugOne {

	/**
	* The entry point for AssignmentFourDebugOne
	* @param args String array of arguments
	*/
	public static void main(String[] args){

		PriorityQueue priorityQueue = new PriorityQueue(5);

		// Test adding to the priority queue
		priorityQueue.insert(new Node(2));
		priorityQueue.insert(new Node(1));
		priorityQueue.insert(new Node(5));
		priorityQueue.insert(new Node(4));
		priorityQueue.insert(new Node(3));

		// Print priority queue 
		System.out.println();
		priorityQueue.dump();

		// Testing removing from the priority queue
		Node node1 = priorityQueue.delete();
		Node node2 = priorityQueue.delete();
		Node node3 = priorityQueue.delete();
		System.out.println();
		System.out.println("Node with priority " + node1.priority + " was deleted");
		System.out.println("Node with priority " + node2.priority + " was deleted");
		System.out.println("Node with priority " + node3.priority + " was deleted");

		// Printing priority queue
		System.out.println();
		priorityQueue.dump();

		// EXPECTED OUTPUT
		// Root Priority: 1; left Priority: 2; right Priority: 5;
		// Root Priority: 2; left Priority: 4; right Priority: 3;
		// Root Priority: 5; left null; right null;
		// Root Priority: 4; left null; right null;
		// Root Priority: 3; left null; right null;
		//
		// Node with priority 1 was deleted
		// Node with priority 2 was deleted
		// Node with priority 3 was deleted
		//
		// Root Priority: 4; left Priority: 5; right null;
		// Root Priority: 5; left null; right null;
	}

}
