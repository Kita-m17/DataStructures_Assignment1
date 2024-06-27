import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
* A class representing a knowledge base implementing as an array.
* THe knowledge base stores information about terms, sentences and confidence scores.
* 
* This class provides funtionality to load data from a file, add new statements to the knowledge base,
* search for information by term or term and sentence, display a menu for user interaction.
* The data is stored in an array if Generics objects.
*
* The class also supports updating the knowledge base with new statements, treating them as updates
* excuted in the order they in a file.
*
* @author Nikita Martin
* @version 1.0
* @since 24/02/2024
*/
public class GenericsKbArrayApp{
   
   //Instance variables
   private static int counter;
   private static Generics[] knowledgeBase;
   
   /**
   * Default constructor for GenericsKbArrayApp.
   * Initializes the knowledge base to null and counter to 0.
   */ 
   public GenericsKbArrayApp(){}
   
   /**
   * Parameterized constructor for GenericsKbArrayApp
   * Initializes the knowledge base to null and sets the counter based on the provided values.
   *
   * @param counter The initial value for the counter.
   */
   public GenericsKbArrayApp(int counter){
      this.knowledgeBase = null;
      this.counter = counter;
   }
   
   /**
   * Parses a string containing term, sentence, and confidence score separated by tabs.
   * 
   * @param data String containing the term, sentence and confidence score, to be converted into a Generics object
   * @return A Generics object containing the parsed information.
   **/
   public static Generics makeData(String data){
      // Create a scanner to read in the input String
      Scanner scanner = new Scanner(data.trim());
      // Set the delimiter to tab
      scanner.useDelimiter("\t");
      
      // Create a node to store the parsed data
      Generics dataInput = new Generics();
      String score;
      
      //Extract the term, sentence and confidence score from the input string
      dataInput.setTerm(scanner.next());
      dataInput.setSentence(scanner.next());
      score = scanner.next();
      dataInput.setScore(Double.parseDouble(score));
      
      return dataInput;
   }
   
   /**
   * Loads data from a file and populated the knowledge base array.
   * The file should contain lines with tab-separated values for term, sentence, and confidence score.
   *
   * @param file The name of the file to read data from.
   */
   private static void loadFromFile(String file) throws FileNotFoundException{
      Scanner fileData = null;
      
      try{
         //opens the specified file for reading
         fileData = new Scanner(new File(file));
         
         //counts the number of lines in the file
         counter = 0;
         while(fileData.hasNextLine()){
            fileData.nextLine();
            counter++;
         } 
      }
      catch(FileNotFoundException e){
         //handles a case where the file is not found
         throw e;
      }finally{
         if (fileData != null) {
            fileData.close(); // closes the file
         }
      }
      
      Scanner fileReader = null;
      try{
         fileReader = new Scanner(new File(file));
         //creates the array of the size of the counter(the number of lines in the file)
         knowledgeBase = new Generics[counter];
         
         if (fileReader.hasNextLine()){
            fileReader.reset();//reads in the file from the beginning of the file
            for (int i = 0;  i<counter; i++){
               //creates a Generics class object of each of the data read from the file
               knowledgeBase[i] = makeData(fileReader.nextLine());
            }
         }
      }
      catch(FileNotFoundException e){
         throw e;
      }
      finally{
         if (fileReader!=null){
            fileReader.close();
         }
      }
   }
      
   /**
   * Searches for a term in the knowledge base and print the associated information.
   *
   * @param searchTerm The term to search for.
   * @param knowledgeBase The array representing the knowledge base.
   **/
   public static void searchTerm(String searchTerm, Generics[] knowledgeBase){
      // Flag to track if the term is found in the knowledge base.
      boolean termFound = false;
      
      // Iterate through each Generics object in the knowledgeBase array.
      for(Generics data: knowledgeBase){
         // Check if the Generics object is not null and its term matches the searchTerm (case-insensitive).
         if (data != null && data.getTerm().equalsIgnoreCase(searchTerm)){
            // Print the statement and confidence score if the term is found.
            System.out.println("Statement found: " + data.getSentence() + " (Confidence score: " + data.getScore() + ").\n");
            termFound = true;
            break; // Break out of the loop once the term is found.
         }
      }
      // If the term is not found, print a message indicating so.
      if (!termFound){
         System.out.println("No statement found for term: " + searchTerm + ".\n");
      }
   }
   
   /**
   * Searches for the knowledge base for partial matches to a term.
   * 
   * @param term  The term to search for.
   * @return A list of Generic objects that are partial matches to a term, or an empty list of not found.
   **/
   public static List<Generics> similarSearches(String term){
      List<Generics> results = new ArrayList<>(); //creates an array to store the partial matches
      if (knowledgeBase== null){
         // base case: the root is null or the term is present at the root
         return results;
         
      } else{
         //checks for partial matches
         for (Generics data: knowledgeBase){
            StringTokenizer termCompare = new StringTokenizer(data.getTerm(), " ");
            //String similarTerms = termCompare.nextToken(" ");
            while (termCompare.hasMoreElements())   
           {    
               if (termCompare.nextToken().equalsIgnoreCase(term))
                  results.add(data);
           }
        }
      }
      return results;
   }
   
   /**
   * Searches for a term and sentence in the knowledge base and prints the associated confidence score.
   *
   * @param term The term to search for.
   * @param sentence The sentence to search for.
   * @param knowledgeBase The array representing the knowledge base.
   **/
   public static void searchForTermAndSentence(String term, String sentence, Generics[] knowledgeBase){
      boolean termFound = false;
      double score = -1;
      boolean sentenceFound = false;
      
      // Check if the knowledge base is not null.
      if (knowledgeBase != null){
         // Iterate through each Generics object in the knowledgeBase array
         for(Generics data: knowledgeBase){
            // Check if the Generics object's term and sentence match the specified term and sentence (case-insensitive)
            if (data.getTerm().equalsIgnoreCase(term)){
               termFound = true;
               // Update the score and set the found flag to true
               if (data.getSentence().equalsIgnoreCase(sentence)||(data.getSentence().toLowerCase()).contains(sentence.toLowerCase())){
                  //checks if the sentence that the user searches for matches or us similar to the sentence of the term
                  sentenceFound = true;
                  score = data.getScore();
                  break; // Break out of the loop once the term and sentence are found
               }
            }
         }
         // If the term and sentence combination is not found, print a message indicating so
         if (termFound==false){
            // Print the confidence score if the term and sentence combination is found
            System.out.println("The term was not found in the knowledge base.\n");
         }
         else if(termFound == true && sentenceFound == false){
            System.out.println("The sentence was not found in the knowledge base.\n");
         }
         else{
            // Print a message if the knowledge base is empty or not found
            System.out.println("The statement was found and has a confidence score of " + score + ".\n");
         }
      }
      else{
         System.out.println("The knowledge base is empty or not found.\n");
      }
   }
   
   /**
   * updates the sentence of an existing Generics object in a knowledge base.
   * If the term is found in the knowledge base, the method updates the associated sentence and confidence score,
   * unless the new statement has a lower confidence score.
   * The update functionality is supported through the user interface and file loading.
   *
   * @param term The term to which the statement is related.
   * @param sentence The statement to be added or updated.
   * @param score The confidence score for th statement.
   */
   public static void addStatementToKnowledgeBase(String term, String sentence, double score){
      boolean found = false;//boolean variable to check if the term is found in the knowledge base
      // Iterate through each Generics object in the knowledgeBase array
      for (Generics data: knowledgeBase){
         // Check if the term matches the specified term (case-insensitive)
         if ((data.getTerm()).equalsIgnoreCase(term)){
            // update the statement and confidence score.
            data.update(sentence, score);
            found = true;//term is found in the knowledge base
         }
      }
      //prints the appropriate statement depending on whether the term has been found
      if(found){
         System.out.println("Statement for term " + term + " has been updated.\n");
      }
      else{
         System.out.println("The term " + term + " is not found in the knowledge base\n");
      }
   }
   
   /**
   * Displays a menu for the user to interact with the knowledge base.
   * The user can choose actions like loading a knowledge base, adding a new statement,
   * searching for items, and quitting the program.
   *
   * @param input The scanner object for user input.
   **/
   public static void displayMenu(Scanner input){
      int choice;
      do{
         System.out.println("Choose an action from the menu:");
         System.out.println("1. Load a knowledge base from a file");
         System.out.println("2. Add a new statement to the knowledge base");
         System.out.println("3. Search for an item in the knowledge base by term");
         System.out.println("4. Search for a item in the knowledge base by term and sentence");
         System.out.println("5. Quit");
         
         System.out.print("Enter your choice: ");
         
         if (input.hasNextInt()){
         
            choice = input.nextInt();
            input.nextLine();//consumes newLine charachter
            System.out.println();
            
            switch(choice){
               case 1:
                  System.out.print("Enter File name: ");
                  String fileName = input.nextLine();
                  try{
                  //checks if file is found
                     loadFromFile(fileName);
                     if (knowledgeBase != null){
                        System.out.println();
                        System.out.println("Knowledge base loaded successfully.\n");
                     }
                     else{
                        System.out.println("Error loading knowledge base.\n");
                     }

                  }
                  catch(FileNotFoundException e){
                     //Handles the case where the file is not found
                     System.out.println("\nFile '" + fileName + "' not found.\n");
                  }
                  break;
            
               case 2:
                  //Add a new statement to the knowledge base
                  GenericsKbArrayApp genData = new GenericsKbArrayApp();
                  if (knowledgeBase != null) {
                     System.out.print("Enter the term: ");
                     String term = input.nextLine();
                     System.out.print("Enter the statement: ");
                     String statement = input.nextLine();
                     System.out.print("Enter the confidence score: ");
                     double score = input.nextDouble();
                     genData.addStatementToKnowledgeBase(term, statement, score);
                  }
                  else{
                     System.out.println("No knowledge base loaded. Please load a knowledge base first.\n");
                  }
                  break;
                  
               case 3:
                  //Search for an item in the knowledge base by term
                  if (knowledgeBase != null) {
   
                     System.out.print("Enter the term to search: ");
                     String searchTerm = input.nextLine();
                     searchTerm(searchTerm, knowledgeBase);
                     //retrieves partial matches of the item
                     List<Generics> results = similarSearches(searchTerm);
                     if (results != null && !results.isEmpty()){
                        //prints out the partial matches if they are found
                        System.out.println("Partial matches are: ");
                        for (Generics result: results){
                           System.out.println("Term: " + result.getTerm() + ". Statement: " + result.getSentence() + " (Confidence score: " + result.getScore() + ").");
                        }
                     }
                     System.out.println();

                  }
                  else{
                     System.out.println("No knowledge base loaded. Please load a knowledge base first.\n");
                  }
                  
                  break;
                  
               case 4:
                  //Search for an item in the knowledge base by term and sentence
                  if (knowledgeBase != null) {
                     System.out.print("Enter the term: ");
                     String termForSearch = input.nextLine();
                     System.out.print("Enter the statement to search for: ");
                     String sentence = input.nextLine();
                     searchForTermAndSentence(termForSearch, sentence, knowledgeBase);
                     List<Generics> matches= similarSearches(termForSearch);
                     if (matches != null && !matches.isEmpty()){
                        //prints out the partial matches if they are found
                        System.out.println("Partial matches are: ");
                        for (Generics result: matches){
                           System.out.println("Term: " + result.getTerm() + ". Statement: " + result.getSentence() + " (Confidence score: " + result.getScore() + ").");
                        }
                        System.out.println();
                     }
                  }
                  else{
                     System.out.println("No knowledge base loaded. Please load a knowledge base first.\n");
                  }
                  break;
                  
               case 5:
                  //Exit program once the user chooses quit
                  System.out.println("Exiting the program. Goodbye!");
                  break;
                  
               default:
                 System.out.println("Invalid choice. Please enter a number between 1 and 5.\n");
                 break;
            }
         }
         else{
            System.out.println("Invalid choice. Please enter a number from 1 to 5.\n");
            input.nextLine(); // Consume invalid input
            choice = 0; // Set choice to an invalid value to continue the loop
         }
      }
      while (choice!=5); //loop until the user quits the program
   }
   
    /**
   * The main entry point for the program which displays the menu for interacting with the knowledge base implemented using a Binary Search Tree (BST) and closes the scanner afterward.
   *
   * @param args The command-line arguments (not used in this program).
   */   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      GenericsKbArrayApp.displayMenu(scanner);
      scanner.close();
   }
}