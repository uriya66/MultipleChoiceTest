import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Test {

	ArrayList<String> QaArray = new ArrayList<String>();
	Random rand = new Random();
	String question = "";
	String rightAnswer = "";
	String[] answers = new String[4];
	int finalGrade = 0;
	Scanner scan = new Scanner(System.in);
	//QuestionsAndAnswers constructor.
	QuestionsAndAnswers QA = new QuestionsAndAnswers(); 


	//Start the test
	public void startTest(){
		int i = 0, grade = 0;
		//Check if loaded
		if(QaArray.isEmpty()) {
			QaArray = QA.purseFile();
		}
		//Get average grade for each question
		grade = numberOfQuestions(QaArray);
		//Start loop
		while(i < QaArray.size()) {
			question = QaArray.get(i);
			rightAnswer =  QaArray.get(i+1);
			//Run on QaArray
			for(int j = i + 1, cnt = 0; j < QaArray.size() && cnt < 4; j++, cnt++) {
				answers[cnt] = QaArray.get(j);
			}
			i += 5;
			//Shuffle the answers
			answers = shuffle(answers);
			System.out.println(question);
			//Print the answers 
			for (int l=0; l<answers.length; l++)
				System.out.println(l+1 + ". " + answers[l]);
			System.out.println("\nPlease type your answer number:");

			int choose = 0;
			//Get answer from user
			while(true) {
				try {
					choose = scan.nextInt();
					if (choose > 4 || choose < 1) {
						System.out.println("You entered an incorrect character\n Enter numbers between 1-4: ");
					}else {
						if (answers[choose-1] == (rightAnswer)) {
							System.out.println("You right!\n");
							finalGrade += grade;
							break;
						}else {
							System.out.println("Worng!\n");
							break;
						}
					}
				}catch (InputMismatchException e) {
					System.out.println("\tInvalid input type (must be an integer)");
					scan.nextLine();  // Clear invalid input from scanner buffer.
				}
			}
		}
		//End game show grade and play again or exit 
		System.out.println("The test over!\nYour grade is: "+ finalGrade +"\n\nIf you want to try agian press 1, else to exit: ");
		while(true) {
			try {
				int numChoose = scan.nextInt();
				if (numChoose == 1) {
					startTest();
				}else {
					System.out.println("Goodbye!");
					System.exit(1);
				}
			}catch (InputMismatchException e) {
				System.out.println("\tInvalid input type (must be an integer)");
				scan.nextLine();  // Clear invalid input from scanner buffer.
			}
		}
	}

	//Shuffle the answers
	private String[] shuffle(String[] ans) {
		for (int shuf=0; shuf<20; shuf++) {
			int shuf1 = rand.nextInt(ans.length);
			int shuf2 = rand.nextInt(ans.length);
			String tmp = ans[shuf1];
			ans[shuf1] = ans[shuf2];
			ans[shuf2] = tmp;
		}
		return ans;	
	}

	//Get number of questions and return 100/number question (grade for each question)
	private int numberOfQuestions ( ArrayList<String> arr) {
		int cnt = 0;
		String str= "";
		for (int i = 0; i < arr.size(); i++) {
			str = arr.get(i);
			str = str.split(" ")[0];
			if (str.equals("question")) {cnt++;}
		}
		System.out.println("The test has begun!\nFor each question you have: " + 100/cnt + " points\n");
		return 100/cnt;
	}
}
