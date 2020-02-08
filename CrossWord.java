package mjCrossWord;
//Crossword class stores the length and width of the crossword. Then stores the letters in a two dimensional array.

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class CrossWord {
	private int length; // Vertical length of crossword
	private int width; // Horizontal length of crossword
	private String crossWordArray[][]; //Holds the array of strings the make up the crossword
	private String wordList[]; // The list of words to search for
	private int wordListLength; //The length of the list of words to find in the crossword
	private boolean searchUp; // boolean for up crossword search
	private boolean searchDown; //boolean for down crossword search
	private boolean searchLeft; //boolean for left crossword search
	private boolean searchRight; //boolean for right crossword search
	private boolean searchUpLeft; //boolean for up left crossword search
	private boolean searchUpRight; //boolean for up right crossword search
	private boolean searchDownLeft; //boolean for down left crossword search
	private boolean searchDownRight; //boolean for down right crossword search
	private Label crossArray [][]; //Array of labels to match array of strings for the crossword
	
	CrossWord(int columnCount, int rowCount, int wordListLength, String wordlist[], String wordArray[][])
	{
		this.setLength(columnCount);
		this.setWidth(rowCount);
		this.setWordListLength(wordListLength);
		wordList = new String[100];
		wordList = wordlist;
		crossWordArray = new String[100][100];
		crossWordArray = wordArray;
		crossArray = new Label[100][100];
		this.stringToLabel();
	}
	
	//Sets the vertical length of the crossword array
	public void setLength(int crossLength)
	{
		this.length = crossLength;
	}
	
	//Gets the vertical length of the crossword array
	public int getLength()
	{
		return this.length;
	}
	
	//Sets the horizontal width of the crossword array
	public void setWidth(int crossWidth)
	{
		this.width = crossWidth;
	}
	
	//Returns the horizontal width of the crossword array
	public int getWidth()
	{
		return this.width;
	}
	
	//Sets the length of the list of words to be searched
	public void setWordListLength(int wordsToSearch)
	{
		this.wordListLength = wordsToSearch;
	}
	
	//Gets the length of the list of words to be searched
	public int getWordListLength()
	{
		return this.wordListLength;
	}
	
	//Gets the list of words to be searched
	public String[] getWordList()
	{
		return wordList;
	}
	
	//Sets the list of words to be searched
	public void setWordList(String [] words)
	{
		this.wordList = words;
	}
	
	//Returns the two dimensional crossword array
	public String [][] getCrossWord()
	{
		return crossWordArray;
				
	}
	
	//Sets the crossword array to a two dimensional array
	public void setCrossWord(String [][] crossArray)
	{
		this.crossWordArray = crossArray;
	}
	
	//Prints the elements of the crossword array
	public void printCrossWord()
	{
		for(int a=0; a<length; a++)
		{
			for(int b=0; b<width; b++)
			{
				System.out.print(crossWordArray[a][b]);
			}
			System.out.println();
		}
	}
	//Copies the string crossword array into the label array to be displayed by gui
	public void stringToLabel()
	{
		for(int a=0; a<length; a++)
		{
			for(int b=0; b<width; b++)
			{
				crossArray[a][b] = new Label();
				crossArray[a][b].setText(crossWordArray[a][b]);
			}
		}
	}
	
	//Returns the two dimensional array of labels.
	public Label [][] getCrossLabel()
	{
		return this.crossArray;
	}
	
	//Finds the words in the crossword array. First it finds the first letter of a word being searched. Then it checks all 8 possible directions. If a word was found in a direction. It then highlights the word from that direction.
	public void findWord(String word)
	{
		for(int a=0; a<length; a++)
		{
			for(int b=0; b<width; b++)
			{
				String firstLetter = word.substring(0, 1);
				if(crossWordArray[a][b].equals(firstLetter))
				{
					if(horizontalCheckRight(crossWordArray, a, b, "", word)==true)
					{
						highlightWordRight(a, b, word.length());
					}
					if(horizontalCheckLeft(crossWordArray, a, b, "", word)==true)
					{
						highlightWordLeft(a, b, word.length());
					}
					if(verticalCheckUp(crossWordArray, a, b, "", word)==true)
					{
						highlightWordUp(a, b, word.length());
					}
					if(verticalCheckDown(crossWordArray, a, b, "", word)==true)
					{
						highlightWordDown(a, b, word.length());
					}
					
					//Diagnol checks
					if(diagnolCheckDownRight(crossWordArray, a, b, "", word)==true)
					{
						highlightWordDiagnolDownRight(a, b, word.length());
					}
					if(diagnolCheckDownLeft(crossWordArray, a, b, "", word)==true)
					{
						highlightWordDiagnolDownLeft(a, b, word.length());
					}
					if(diagnolCheckUpRight(crossWordArray, a, b, "", word)==true)
					{
						highlightWordDiagnolUpRight(a, b, word.length());
					}
					if(diagnolCheckUpLeft(crossWordArray, a, b, "", word)==true)
					{
						highlightWordDiagnolUpLeft(a, b, word.length());
					}	
				}
			}
		}
	}
	
	//Loops through all words in the wordlist array. Highlights each word in red.
	public void findAllWords()
	{
		for(int a=0; a<this.getWordListLength(); a++)
		{
			findWord(wordList[a]);
		}
	}
	
	
	//Horizontal check right
	public boolean horizontalCheckRight(String crossWord[][], int row, int col, String wordBuilder, String targetWord)
    {
		
		while(col<this.width)
		{
			if(wordBuilder.equals(targetWord)){
				return true;}
			
			wordBuilder = wordBuilder + crossWord[row][col];
			return horizontalCheckRight(crossWord, row, col+1, wordBuilder, targetWord);
		}
		return false;
    }
	
	//Horizontal check left
	public boolean horizontalCheckLeft(String crossWord[][], int row, int col, String wordBuilder, String targetWord)
    {
		
		while(col>0)
		{
			if(wordBuilder.equals(targetWord)){
				return true;}
			
			wordBuilder = wordBuilder + crossWord[row][col];
			return horizontalCheckLeft(crossWord, row, col-1, wordBuilder, targetWord);
		}
		return false;
    }
	
	//Vertical check down
	public boolean verticalCheckDown(String crossWord[][], int row, int col, String wordBuilder, String targetWord)
    {
		
		while(row<this.length)
		{
			if(wordBuilder.equals(targetWord)){
				return true;}
			
			wordBuilder = wordBuilder + crossWord[row][col];
			return verticalCheckDown(crossWord, row+1, col, wordBuilder, targetWord);
		}
		return false;
    }
	//Vertical check up
	public boolean verticalCheckUp(String crossWord[][], int row, int col, String wordBuilder, String targetWord)
    {
		
		while(row>0)
		{
			if(wordBuilder.equals(targetWord)){
				return true;}
			
			wordBuilder = wordBuilder + crossWord[row][col];
			return verticalCheckUp(crossWord, row-1, col, wordBuilder, targetWord);
		}
		return false;
    }
	
	//Diagonal check up right
	
	public boolean diagnolCheckUpRight(String crossWord[][], int row, int col, String wordBuilder, String targetWord)
    {
		
		while(row>0&&col<this.width)
		{
			if(wordBuilder.equals(targetWord)){
				return true;}
			
			wordBuilder = wordBuilder + crossWord[row][col];
			return diagnolCheckUpRight(crossWord, row-1, col+1, wordBuilder, targetWord);
		}
		return false;
    }
    
	//Diagonal check up left
		public boolean diagnolCheckUpLeft(String crossWord[][], int row, int col, String wordBuilder, String targetWord)
	    {
			
			while(row>0&&col>0)
			{
				if(wordBuilder.equals(targetWord)){
					return true;}
				
				wordBuilder = wordBuilder + crossWord[row][col];
				return diagnolCheckUpLeft(crossWord, row-1, col-1, wordBuilder, targetWord);
			}
			return false;
	    }
	
	
	//Diagonal check down right
		public boolean diagnolCheckDownRight(String crossWord[][], int row, int col, String wordBuilder, String targetWord)
	    {
			
			while(row<this.length&&col<this.width)
			{
				if(wordBuilder.equals(targetWord)){
					return true;}
				
				wordBuilder = wordBuilder + crossWord[row][col];
				return diagnolCheckDownRight(crossWord, row+1, col+1, wordBuilder, targetWord);
			}
			return false;
	    }
		
	//Diagonal check down left
				public boolean diagnolCheckDownLeft(String crossWord[][], int row, int col, String wordBuilder, String targetWord)
			    {
					
					while(row<this.length&&col>0)
					{
						if(wordBuilder.equals(targetWord)){
							return true;}
						
						wordBuilder = wordBuilder + crossWord[row][col];
						return diagnolCheckDownLeft(crossWord, row+1, col-1, wordBuilder, targetWord);
					}
					return false;
			    }
				
				
				//Highlights the word going up vertically
				public void highlightWordUp(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getCrossLabel()[row-a][column].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going down vertically
				public void highlightWordDown(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getCrossLabel()[row+a][column].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going left horizontally
				public void highlightWordLeft(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getCrossLabel()[row][column-a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going right horizontally
				public void highlightWordRight(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getCrossLabel()[row][column+a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going up right diagonally
				public void highlightWordDiagnolUpRight(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getCrossLabel()[row-a][column+a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going up left diagonally
				public void highlightWordDiagnolUpLeft(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getCrossLabel()[row-a][column-a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going down right diagonally
				public void highlightWordDiagnolDownRight(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getCrossLabel()[row+a][column+a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
				//Highlights the word going down left diagonally
				public void highlightWordDiagnolDownLeft(int row, int column, int wordLength)
				{
					for(int a=0; a<wordLength; a++)
					{
						this.getCrossLabel()[row+a][column-a].setTextFill(Color.web("#ff0000", 0.8));
					}
				}
	//Prints the length of the row - vertically. column - horizontally, length of the list of words to search for, and the words to be searched for
	public String toString()
	{
		String test="";
		
		for(int a=0; a<wordListLength; a++)
		{
			test = test + wordList[a]+" ";
		}
		return "rows " + length + " columns " + width + " wordCount " + wordListLength + " Words " + test;
	}
	
	
}
