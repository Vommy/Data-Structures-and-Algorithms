public class Patient extends Node{
	String patientName;
	String patientSeverity;
	int patientAge; 
	int patientNumber;
	
	public Patient(int Priority, String Name, int Age, String Severity, int Number){
		super(Priority);
		patientName = Name;
		patientAge = Age;
		patientSeverity = Severity;
		patientNumber = Number;
	}
	
	public Patient(){
		super();
		patientName = "";
		patientSeverity = "";
		patientAge = -1;
		patientNumber = -1;
	}
	
	/*
	*toString()
	*@return The priority and name of a patient as a string
	*/
	public String toString(){
		String patientPriority = "Priority: " + priority;
		String name = "Name: " + patientName;
		String separator = ", 	";
		return patientPriority + separator + name;
	}
	
	
	/*
	*toStringLong()
	*@return The full details of a patient as a string
	*/
	public String toStringLong(){
		String patientPriority = "Priority: " + priority;
		String name = "Name: " + patientName;
		String age = "Age: " + patientAge;
		String severity = "Medical Severity Level: " + patientSeverity;
		String number = "Patient Number: " + patientNumber;
		String separator = ", 	";
		return patientPriority + separator + name + separator + age + separator + severity + separator + number;
	}
	
}
