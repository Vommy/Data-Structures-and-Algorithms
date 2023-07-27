import java.util.*;
import java.io.*;

public class MedicalCentre{

	public static PriorityQueue patientQueue = new PriorityQueue();
	
	public MedicalCentre(){
		patientQueue = new PriorityQueue();
	}

	public static void main(String[] args){
		if(args.length != 0){
			for(String file : args){
				processQueue(file);
			}
		}
		else{
			System.out.println("Please Provide A File: ");
		}
	}
	
	
	/*
	*processFile(String file)
	*Reads in a file of patients
	*Adds them to the priority queue using processPatient(String[] patientDetails)
	*Then prints the patients using printPatients()
	*@param file The file to read
	*/
	public static void processQueue(String file){
		Scanner reader = null;
		try{
			reader = new Scanner(new FileReader(file));
			while(reader.hasNextLine()){
				String record = reader.nextLine().trim();
				String[] patientDetails = record.split(",");
				if(patientDetails.length == 3)
					processPatient(patientDetails);
			}
		}
		catch(IOException error){
			error.printStackTrace();
		}
		finally{
			if(reader != null)
				reader.close();
			printPatients();
		}
	}
	
	
	/*
	*printPatients()
	*Deletes/dequeues each patient in the queue
	*Then prints their details using toStringLong()
	*/
	public static void printPatients(){
		while(patientQueue.isEmpty() == false){
			Patient patient2Prnt = (Patient)patientQueue.delete();
			System.out.println(patient2Prnt.toStringLong());
		}
	}
	
	
	/*
	*processPatient(String[] patientDetails)
	*Gets all the details of a patient
	*Creates the patient and adds it to the queue of patients
	*@param patientDetails A string array holding the patient's name, age and severity
	*/
	public static void processPatient(String[] patientDetails){
		try{
			if(notNullString(patientDetails)){
				String patientName = patientDetails[0].trim();
				int patientAge = Integer.parseInt(patientDetails[1].trim());
				String patientSeverity = patientDetails[2].toLowerCase().trim();
				int patientPriority = calculatePriority(patientSeverity, patientAge);
				if(patientPriority != -1){
					int patientNumber = patientQueue.size() + 1;
					Patient newPatient = new Patient(patientPriority, patientName, patientAge, patientSeverity, patientNumber);
					patientQueue.insert(newPatient);
				}
			}	
		}
		catch(Exception error){
			return;
		}
	}
	
	
	/*
	*notNullString(String[] details)
	*@return true If the string contains a null value
	*/
	private static boolean notNullString(String[] details){
		for(String string : details){
			if(string == null)
				return false;
		}
		return true;
	}
	
	/*
	*calculatePriority(String severity, int age)
	*Calculates the priority of a patient based of certain criteria
	*@param severity The severity of the patient as a string
	*@param age The age of the patient as an intger
	*@return The priority of a patient
	*1 is the highest priority
	*2 is medium priority
	*3 is low priority
	*/
	public static int calculatePriority(String severity, int age){
		if(severity != null && age >= 0){
			if(severity.compareTo("high") == 0)
				return 1;
			else if(severity.compareTo("medium") == 0  && age >= 65)
				return 1;
			else if(severity.compareTo("medium") == 0 && age < 65)
				return 2;
			else if(severity.compareTo("low") == 0)
				return 3;
		}
		return -1;
	}
	
}
