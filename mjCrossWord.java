package mjCrossWord;

import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;


public class mjCrossWord extends Application {

	
	public static void main(String[] args) {		
		//Launch the gui
		Application.launch(args);	
	}
	
	public void start(Stage primaryStage)
	{
		//Create main crossword object
		CrossWord test;
		//Set crossword object to data found in text file
		test = readFile();
		
		//Create tile pane due to evenly spaced nodes
		TilePane tile = new TilePane();
		tile.setPadding(new Insets(5, 0, 5, 0));
		tile.setVgap(10);
		tile.setHgap(10);
		tile.setPrefColumns(test.getWidth());
		tile.setPrefRows(test.getLength());
		tile.setStyle("-fx-background-color: DAE6F3;");
		
		//Adds the data from the crossword object to the pane
		for (int a=0; a<test.getLength(); a++) {
		     for(int b=0; b<test.getWidth(); b++)
		     {
		    	 tile.getChildren().add(test.getCrossLabel()[a][b]);
		     }
		     
		}
		
		//Main function to find all of the words in a crossword.
		test.findAllWords();
		
		
		Scene sceneMain = new Scene(tile);
		
		primaryStage.setTitle("CrossWord Solver");
		primaryStage.setScene(sceneMain);
		primaryStage.show();
	}

	
	public static CrossWord readFile()
	{
		String fileName="crossword.txt";
	       
	       try{

	          //Create object of FileReader
	          FileReader inputFile = new FileReader(fileName);

	          //Instantiate the BufferedReader Class
	          BufferedReader bufferReader = new BufferedReader(inputFile);
	          
	          //Read in row count for crossword
	          int row = Integer.parseInt(bufferReader.readLine());
	          //Read in column count for crossword
	          int column = Integer.parseInt(bufferReader.readLine());
	          //Read in number of words to search crossword for
	          int wordNum = Integer.parseInt(bufferReader.readLine());
	          
	          //Holds the words to be searched for
	          String words[] = new String [100];
	          
	          //Reads in the words based on the number of words
	          for(int a=0; a<wordNum; a++)
	        	{
	        		words[a]=bufferReader.readLine();
	        	}
	          
	          //Array to hold the crossword array after converting the horizontal lines of strings into characters.
	          String crossArray[][] = new String[100][100];
	          //An array of strings to be converted to an array of chars to be placed in the two dimensional crossword array.
	          String toConvert[] = new String[100];
	          
	          for(int k=0; k<row; k++){
	        		  toConvert[k]=bufferReader.readLine();
	          }
	         
	     
	          //Populates the crossArray using the moving substring on each line of the crossword.
	          for(int b=0; b<row; b++){
	        	  String lineConvert = toConvert[b];
	        	  for(int c=0; c<column; c++)
	        	  {
	        		  crossArray[b][c] = lineConvert.substring(c, c+1);
	        	  }
	          }
	          
	            //Create crossword object
	        	CrossWord test = new CrossWord(row, column, wordNum, words, crossArray);
	        	
	        	
	           
	          
	          //Close the buffer reader
	          bufferReader.close();
	          
	          return test;
	       }catch(Exception e){
	          System.out.println("Error while reading file line by line:" + e.getMessage());                      
	       }
		return null;
	}
}
