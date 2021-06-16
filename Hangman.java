import java.util.Scanner;

public class Hangman {

	//sets playing or not
	public static int playing = 0;
	public static void main(String[] args) {
		//loops till game is ended by lost or win
		while (playing == 0){
			setUp.setUp();
			pickingLetters.pickLetters();
		}
	}
}

class incorrectGuesses {
	
	public static int incorectGuesses = 0;
	public static void guesses() {
		//switch between how many wrong there is
		if (incorectGuesses < 6) {
			switch(incorectGuesses) {
			case 0:
				board.wrong0();
				break;
			case 1:
				board.wrong1();
				break;
			case 2:
				board.wrong2();
				break;
			case 3:
				board.wrong3();
				break;
			case 4:
				board.wrong4();
				break;
			case 5:
				board.wrong5();
				break;
			case 6:
				//trip the game over code
				board.wrong6();
				System.out.println("\nGAME OVER");
				gameOver.playAgain();
				break;
			}
		}
		else {
			//if it somehow keeps going on it trips it and ends the game
			System.out.println("\nGAME OVER");
			gameOver.playAgain();
		}
	}

}

class gameOver {
	public static void playAgain() {
		int menu;
		//resets the game if clicked yes
		System.out.println("Would you like to play again\n One for yes \n Two for no");
		Scanner option = new Scanner(System.in);
		menu = option.nextInt();
		if(menu == 1) {
			main.playing = 0;
		}
		else {
			System.out.println("Good Bye");
		}
	}
}

class board {

	//start board
	public static void wrong0() {
		System.out.println("    ----");
		System.out.println("    |");
		System.out.println("    |");
		System.out.println("    |");
		System.out.println("    |");
		System.out.println("---------");
	}
	//When you have one wrong letter
	public static void wrong1() {
		System.out.println("    ----");
		System.out.println("    |  O");
		System.out.println("    |");
		System.out.println("    |");
		System.out.println("    |");
		System.out.println("---------");
	}
	//When you have two wrong letters
	public static void wrong2() {
		System.out.println("    ----");
		System.out.println("    |  O");
		System.out.println("    |  |");
		System.out.println("    |");
		System.out.println("    |");
		System.out.println("---------");
	}
	//When you have three wrong letters
	public static void wrong3() {
		System.out.println("    ----");
		System.out.println("    |  O");
		System.out.println("    | /|");
		System.out.println("    |");
		System.out.println("    |");
		System.out.println("---------");
	}
	//When you have four wrong letters
	public static void wrong4() {
		System.out.println("    ----");
		System.out.println("    |  O");
		System.out.println("    | /|\\");
		System.out.println("    |");
		System.out.println("    |");
		System.out.println("---------");
	}
	//When you have five wrong letters
	public static void wrong5() {
		System.out.println("    ----");
		System.out.println("    |  O");
		System.out.println("    | /|\\");
		System.out.println("    | /");
		System.out.println("    |");
		System.out.println("---------");
	}
	//When you have six wrong letters
	public static void wrong6() {
		System.out.println("    ----");
		System.out.println("    |  O");
		System.out.println("    | /|\\");
		System.out.println("    | /\\");
		System.out.println("    |");
		System.out.println("---------");
	}
}

class pickingLetters {
	public static void pickLetters(){
		//makes the string for past guesses
		StringBuilder pastguesses = new StringBuilder();
		//prints the mystory word
		for (int i = 0; i < setUp.length; i++){
	        setUp.censoredWord[i] = '*';
	    }
		
		int attempts = 0;
		//loops till word is found out 
		while (String.valueOf(setUp.censoredWord).equals(setUp.storedWord)== false){

			//set up the varaiables 
	        char charguess;
	        String tempLetter;
	        String tempstring;
	        boolean correct = false; 
	        int times = 0; 
	        boolean repeated = false;
	        
	        //prints the letter that was chosen in the select word
	        for(int i= 0; i <setUp.length; i++){
	            System.out.print(setUp.censoredWord[i]);
	       	}
	        
	        //ask what letter you want to try
	        System.out.println();
	        System.out.println("Your past guesses are: "+pastguesses);
	       	System.out.println();
	       	incorrectGuesses.guesses();
	       	Scanner guess = new Scanner(System.in);
	       	System.out.println("Type your guess: ");
	        tempLetter = guess.next();
	        charguess = tempLetter.charAt(0);   
	        pastguesses.append(charguess);
	        tempstring = pastguesses.toString();
	        
	        //if you chose the same letter it does not let you 
	        if (tempstring.lastIndexOf(charguess, tempstring.length() -2 ) != -1){
                System.out.println("You already guessed this letter! \nGuess again.");
                repeated = true;
	        }
	        
	        //if it is not the same word
	        if (repeated == false){
	        	
	        	//checks if it in the letter
	            for (int i = 0; i<setUp.length; i++){
	            	if(setUp.wordArray[i] == Character.toUpperCase(charguess)) {
	            		setUp.censoredWord[i] = Character.toUpperCase(charguess);  //replaces * with guessed letter in caps             
	                    correct = true; 
	                    times++; 
	                }
	            }
	            //if it is then print 
	            if(correct == true){
	            	System.out.println("The letter " + charguess + " is in the secret word! \nThere are " + times +" " + charguess + " 's in the word. Revealing the letter(s): ");
	            }
	            //if it is not then print 
	            else if (correct == false){
	            	System.out.println("Sorry, the letter " +charguess+ " is not in the word. \nYour secret word:  ");
	            	incorrectGuesses.incorectGuesses++;
	            }
	            
	        }
	        attempts++;
		}
		System.out.println("You guessed the entire word "+ setUp.storedWord.toUpperCase() + " correctly! \nIt took you " + attempts + " attempts!\n");
		main.playing = 1;
		gameOver.playAgain();
	}
}

class setUp {
	public static String storedWord;
	public static int length;
	public static char[] wordArray;
	public static char[] censoredWord;
	public static int wrong = 0;
	
	public static void setUp(){
		//starts all the startup code
		//sets the word and length
		incorrectGuesses.incorectGuesses = 0;
		storedWord = wordBank.wordSet();
		storedWord = storedWord.toUpperCase();
		length = storedWord.length();
		wordArray = storedWord.toCharArray();
		censoredWord = storedWord.toCharArray();
		System.out.println("\nYour word is: ");
	    for (int i = 0; i < length; i++){
	        censoredWord[i] = '*';  
	    }
	}
}

class wordBank {

	static String word = null;
	static int number = 0;
	
	public static String wordSet() {
		int wordChoice = 1;
		//randomly choses a word
		wordChoice = (int) (Math.random()*(9));
		//sets the word based on the random number
		switch(wordChoice) {
		case 0:
			word = "mouse";
			break;
		case 1:
			word = "test";
			break;
		case 2:
			word = "computer";
			break;
		case 3:
			word = "dell";
			break;
		case 4:
			word = "screen";
			break;
		case 5:
			word = "keyboard";
			break;
		case 6:
			word = "samsung";
			break;
		case 7:
			word = "apple";
			break;
		case 8:
			word = "java";
			break;
		case 9:
			word = "meeting";
			break;
		case 10:
			word = "operation";
			break;
		}
		return word;
	}
}




