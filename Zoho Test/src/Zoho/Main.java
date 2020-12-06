package Zoho;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	static ArrayList<String> routes = new ArrayList<String>();						//Globally declared ArrayLists
	static ArrayList<String> passenger_details = new ArrayList<String>();			//Globally declared ArrayLists
	static String starting_point = "";												//Starting point string					
	static String[] spliter(String str) {
		String[] Splitted = str.split(" ");
		return Splitted;
	}
	static int string_to_int(String str) {
		int ans = Integer.parseInt(str);
		return ans;
	}
	static int find_the_elder_person(String str, String[] arr) {
		int index_of_elder =0;
		int max = 0;
		for(int i =0;i<passenger_details.size();i++) {
			String str1 = passenger_details.get(i);
			String[] pass = spliter(str1);
			String age = pass[1];
			int passenger_age = Integer.parseInt(age);
			if(passenger_age>max) {									//Finding the maximum age
				max = passenger_age;
				index_of_elder = i;
		}
		}
		return index_of_elder;											//Will return the eldest person Index of the passenger_details list
	}
	static String finding_the_route(String destination,String starting_point) {  //This function will find the route
		StringBuilder sb = new StringBuilder();
		
		for(int i =0;i<routes.size();i++) {
			String[] rte = spliter(routes.get(i));
			int size = rte.length-1;								//Will check to the destination in the routes list 
			if(rte[size].equals(destination)) {
				if(rte[0].equals(starting_point)) {
					sb.append(routes.get(i));
					break;
				}else {
					sb.append(starting_point+" "+routes.get(i));
					break;
				}
			}
		}
		return sb.toString();				//will return the route possible
	}
	static void START_POD(String str,String[] arr) {
		StringBuilder sb = new StringBuilder();						//StringBuilder is used to append the output
		String pods = arr[1];										//Finding the number of pods
		int no_of_pods = string_to_int(pods);
		for(int i=0;i<no_of_pods;i++) {
		int eldest = find_the_elder_person(str,arr);				//Finding the eldest person to send them first
		String person_to_travel = passenger_details.get(eldest);
		String[] person = person_to_travel.split(" ");
		passenger_details.remove(eldest);							//Removing the person who traveled
		sb.append(person[0]+" ");
		String route = finding_the_route(person[2],starting_point); //The Route he/she will travel
		sb.append(route+" ");
		System.out.println(sb.toString());							//Will return the passenger traveled
		sb.setLength(0);
		}
}
	static void PRINT_Q(String str) {
		int count_of_passengers_waiting = passenger_details.size();     
		System.out.println(count_of_passengers_waiting);             //This will print the size of the list (ie) No of passengers waiting
		for(int i =0;i<count_of_passengers_waiting;i++) {
			String passengers_name_age = passenger_details.get(i);   
			String[] arr = spliter(passengers_name_age);
			System.out.println(arr[0]+" "+arr[1]);					//This will print the name and the age of the passengers
		}
	}
	public static void main(String[] args) {
		try {
			File file = new File("D://Eclipse Workspace/HyperLoop_File/src/Zoho/inp.txt"); //To import the text file to the project
			FileReader fr=new FileReader(file);											  
			BufferedReader br=new BufferedReader(fr);
			String nextLine = br.readLine();
			while(nextLine!=null) {
				String[] Split_Line = nextLine.split(" ");		//The lines in the text file is been splitted to check whether for checking the commands such as INIT,PRINT_Q etc  
				if(Split_Line[0].equals("INIT")) {
					String[] rte = spliter(nextLine);
					starting_point = rte[2];					//The starting point is been assigned globally
					int count = string_to_int(rte[1]);
					while(count>0) {
						routes.add(br.readLine());				//The routes are been added to the routes list which is globally present
						count--;
					}
				}
				else if(Split_Line[0].equals("ADD_PASSENGER")) {	//ADD_PASSENGER command will be executed
					String[] pass_det = spliter(nextLine);			
					int count = string_to_int(pass_det[1]);
					while(count>0) {
						passenger_details.add(br.readLine());
						count--;
					}
				}
				else if(Split_Line[0].equals("START_POD")) {	//START_POD command will be executed
					START_POD(nextLine, Split_Line);
				}
				else if(Split_Line[0].equals("PRINT_Q")) {		//PRINT_Q command will be executed
					PRINT_Q(nextLine);
				}
				
				nextLine = br.readLine();
			}
			fr.close();						//Closing the File Reader
	}
		catch(IOException exception) {
		System.out.print(exception); //Incase the file is not found an exception will be thrown
	}

}
}
