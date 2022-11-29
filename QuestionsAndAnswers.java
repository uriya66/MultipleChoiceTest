import java.util.ArrayList;
import java.io.IOException; 
import java.util.Scanner;
import java.io.File;

public class QuestionsAndAnswers {

	private String fileName;
	ArrayList<String> lines = new ArrayList<String>();

	public QuestionsAndAnswers() {
		this.fileName = path();
	}

	//Get path
	private String path() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter File Path:");
		return scan.next();
	}

	//add file to array
	public ArrayList purseFile() {
		try{
			Scanner scan = new Scanner(new File(fileName));
			while (scan.hasNext()){
				lines.add(scan.nextLine());
			}
			scan.close();
		}catch (IOException e){
			System.out.println("ERROR NO 1");
		}
		return lines;
	}

	// print arrayList 
	public void printArryList(ArrayList<String> s){
		for (int i = 0; i < lines.size(); i++ ) {
			if (lines.get(i).isEmpty()) {
				break;
			}else {
				System.out.println(lines.get(i));
			}
		}
	}
}
