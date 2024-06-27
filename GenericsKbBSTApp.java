import java.util.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
* A class representing a knowledge base implemented by a Binary Search Tree (BST).
* Each node in a tree contains information about a specific term, sentence and confidence score.
* The class contains methods for inserting a node into the BST, searching, and updating the knowledge base.
*
* @author Nikita Martin
* @version 1.0
* @since 24/02/2024
**/
public class GenericsKbBSTApp{
   //1private static String term, sentence;
   private static Node root;
   //private class Listener extends
   /**
   * Constructs an empty knowledge base represented as a Binary Search Tree
   **/
   public GenericsKbBSTApp(){
      this.root = null; //initialised the root node to null
   }
   
   /**
   * Parses a string of data from the knowledgeBase, to create a new node with a term, sentence and confidence score.
   * 
   * @param data  The input string containing the term, sentence and confidence score, separated by tabs.
   * @return A new node with the parsed information.
   **/
   public static Node makeData(String data){
      // Create a scanner to read in the input String
      Scanner scanner = new Scanner(data.trim());
      // Set the delimiter to tab
      scanner.useDelimiter("\t");
      
      // Create a node to store the parsed data
      Node dataInput = new Node();
      String score;
      
      //Extract the term, sentence and confidence score from the input string
      dataInput.setTerm(scanner.next());
      dataInput.setSentence(scanner.next());
      score = scanner.next();
      dataInput.setScore(Double.parseDouble(score));
      
      return dataInput;
   }
   
   /**
   * Creates a new node with the given term, sentence and confidence score, and inserts it into the BST.
   * 
   * @param root The root of the current subtree
   * @param newNode  The new node to be inserted into the BST.
   * @return THe root of the updated subtree.
   **/
   private static Node insert(Node root, Node newNode){
      //if tree is empty, return a new node
      if (root == null){
         return newNode;
      }
      
      //otherwise recur down the subtrees by inserting logic based on binary search tree rules
      if ((newNode.getTerm()).compareTo(root.getTerm())<0){
         //nodes with terms less than the rootnode term, gets placed on the left subtree
         root.setLeftNode(insert(root.getLeftNode(), newNode));
      }
      else if((newNode.getTerm()).compareTo(root.getTerm())>0){
         //nodes with terms more than the rootnode term, gets placed on the right subtree
         root.setRightNode(insert(root.getRightNode(), newNode));
      }
      else{
         root.update(newNode.getSentence(), newNode.getScore());
      }
      //return the (unchanged) node pointer
      return root;
   }
   
   /**
   * Searches for a node with the specified term in the BST recursively.
   *
   * @param node The rootNode of the current subtree
   * @param term The term to search for.
   * @return The node containing the specified term or null if not found.
   **/
   public static Node search( Node node, String term){
      // Search for the node with the given term
      if (node == null||node.getTerm().equalsIgnoreCase(term)){
         //base case: root is null or term is present at root
         return node;
      }
      //recursively search for a node
      if (term.compareTo(node.getTerm())==0){
         return node;
      }
      else if (term.compareTo(node.getTerm())<0){
         //recursively search the left branch of the node as the term comes before the root's term
         if (node.getLeftNode() == null)
            return null;//the left node if a leaf
         else
            return search(node.getLeftNode(), term);
      }
      else{
         //recurively search the right branch of the node, as the term comes after the root's term
         if(node.getRightNode() == null)
            return null;//the right node is a leaf
         else
            return search(node.getRightNode(), term);
      }
   }
   
   /**
   * Searches for nodes with partial matches to a term in the BST.
   * 
   * @param node  The root of the current subTree
   * @param term  The term to search for.
   * @return A list of nodes containing the list of the partial matches, or an empty list of not found.
   **/
   public static List<Node> similarSearches(Node node, String term){
      List<Node> results = new ArrayList<>(); //creates an array to store the partial matches
      if (node== null)
         // base case: the root is null or the term is present at the root
         return results;
      else{
         StringTokenizer termCompare = new StringTokenizer(node.getTerm(), " ");
         //checks for partial matches
         while (termCompare.hasMoreElements())
           {    
            //checks if terms are equal
               if (termCompare.nextToken().equalsIgnoreCase(term) && !node.getTerm().equalsIgnoreCase(term))
                  results.add(node);
           }
         
         //recursively searches for nodes through the subtrees
         results.addAll(similarSearches(node.getLeftNode(), term));
         results.addAll(similarSearches(node.getRightNode(), term));
      }
      return results;
   }
   
         
   /**
   * Searches for a specific term in the knowledge base and prints the corresponding sentence and confidence score.
   * This method utilizes the 'seach' method to find the relevent information in the Binary Search Tree.
   *
   * @param term The term to searcg for in the knowledge base
   **/
   public static void searchTerm(String term){
      boolean termFound = false;
      // Searcg for the node with the given term
      Node result = search(root, term);
      
      if (result != null && result.getTerm().equalsIgnoreCase(term)){
         // if the node is found, print the corresponding sentence and confidence score
         System.out.println("Statement found: " + result.getSentence() + " (Confidence score: " + result.getScore() + ").\n");
         termFound = true;
      }
      if (!termFound){
         // prints the appropriate statement if the term is not found
         System.out.println("No statement found for term: " + term + ".\n");
      }
   }
   
   /**
   * Read data from a file and inserts it into the BST.
   *
   * @param file The name of the file to read.
   * @throws FileNotFoundException if file if not found
   **/
   public static void loadFromFile(String file) throws FileNotFoundException{
      Scanner fileData = null;
      
      try{
         //opens the specified file for reading
         fileData = new Scanner(new File(file));
         
         //read each line from the file and insert into th BST
         while (fileData.hasNext()){
            String dataLine = fileData.nextLine();
            Node newNode = makeData(dataLine); //creates a new node from the data
            root = insert(root, newNode); //inserts node into the BST
         }
      }
      catch(FileNotFoundException e){
         //Handles the case where the file is not found
         throw e; //Re-throw the exception to be handled by the calling code
      }
      finally{
         if (fileData!=null){
            fileData.close(); //closes the file
         }
      }
      
   }
   
   /**
   *  Adds or updates a node in the knowledge base for a specific term, and updates the confidence score fr a given term.
   *
   * @param node  The node to search for
   * @param term The term to which the statement will be added.
   * @param sentence The statement to be added.
   * @param score The confidence score of the statement.
   * @return The root of the updated BST
   **/
   public static Node addOrUpdate(Node node, String term, String sentence, double score){
      if (node == null){
         // if the root is null, create a new node and return it as the new root
         Node newNode =  new Node(term, sentence, score);
         insert(node, newNode);
         System.out.println("New node with the term " + term + " added to the knowledge base.\n");
         return newNode;
      }    
      else{  
         //compare the term with the current node's term
         if ((term).compareTo(node.getTerm())<0){
            // If the term is smaller, go to the left subtree.
            node.setLeftNode(addOrUpdate(node.getLeftNode(), term, sentence, score));
         }
         else if((term).compareTo(node.getTerm())>0){
            // If the term is larger, go to the right subtree.
            node.setRightNode(addOrUpdate(node.getRightNode(), term, sentence, score));
         }
         else{
            // If the term is equal, update the existing node with the new statement and score.
            node.update(sentence, score);
            System.out.println("Statement for term " + term + " has been updated.\n");
         }
      }
      return node;
   }
   
   /**
   * Adds or updates a node in the knowledfe base for a specific term, sentence, and updates the confidence score for a given term.
   *
   * @param term  The term to which the statement will be added.
   * @param sentence The statement to be added
   * @param score The confidence scpre of the statement
   */
   public static void addOrUpdate(String term, String sentence, double score){
      root = addOrUpdate(root, term, sentence, score);
   }
   
   /**
   * Searches for a specific term and sentence in the knowledge base BST.
   * If found, prints the confidence score, otherwise prints an appropriate message
   *
   * @param term The term to search for.
   * @param sentence The sentence associated with the term.
   **/
   public static void searchForTermAndSentence(String term, String sentence){
      double score = -1;//initializes the confidence score to -1 (indicating not found)
      boolean found = false;
      
      //search for the specified term in the knowledge base
      Node node = search(root, term);
      
      if (node!=null){
         //checks if the sentence matches the searched term, and accounts for a case if the user forgot part of the sentence
         if (node.getTerm().equalsIgnoreCase(term) && (node.getSentence().equalsIgnoreCase(term) || (node.getSentence().toLowerCase()).contains(sentence.toLowerCase()))){
            score = node.getScore();//sets the confidence score
            found = true;
         }
      }
      
      //Displays the result
      if (!found){
         System.out.println("The term or statement was not found.\n");
      }
      else{
         System.out.println("The statement was found and has a confidence score of " + score + ".\n");
      }
   }
   
   /**
   * Displays a menu for the user to interact with the knowledge base
   *
   * @param input A Scanner object for user input.
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
                     System.out.println("\nKnowledge base loaded successfully.\n");
                  }
                  catch(FileNotFoundException e){
                     //Handles the case where the file is not found
                     System.out.println("\nFile '" + fileName + "' not found.\n");
                  }
                  
                  break;
            
               case 2:
                  //Add a new statement to the knowledge base
                  //GenericsKbBSTApp genData = new GenericsKbBSTApp();
                  System.out.print("Enter the term: ");
                  String term = input.nextLine();
                  System.out.print("Enter the statement: ");
                  String statement = input.nextLine();
                  System.out.print("Enter the confidence score: ");
                  double score = input.nextDouble();
                  addOrUpdate(term, statement, score);
                  break;
                  
               case 3:
                  if(root!= null){//checks if a knowledge base has been loaded
                     //Search for an item in the knowledge base by term
                     System.out.print("Enter the term to search: ");
                     String searchTerm = input.nextLine();
                     searchTerm(searchTerm);
                     
                     //retrieves partial matches of the item
                     List<Node> results = similarSearches(root, searchTerm);
                     if (results != null && !results.isEmpty()){
                        //checks if there are partial matches to the searched term                        System.out.println("Partial matches are: ");
                        for (Node result: results){
                           System.out.println("Term: " + result.getTerm() + ". Statement: " + result.getSentence() + " (Confidence score: " + result.getScore() + ").");
                        }
                        System.out.println();
                     }
                  } else{
                     System.out.println("No knowledge base loaded. Please load the knowledge base or add a statement to the Binary Search Tree.\n");
                  }
                  break;
                  
               case 4:
                  if(root!= null){
                     //Search for an item in the knowledge base by term and sentence
                     System.out.print("Enter the term: ");
                     String termForSearch = input.nextLine();
                     System.out.print("Enter the statement to search for: ");
                     String sentence = input.nextLine();
                     searchForTermAndSentence(termForSearch, sentence);
                     List<Node> matches= similarSearches(root, termForSearch);
                     if (matches != null && !matches.isEmpty()){
                        //prints out the partial matches if they are found
                        System.out.println("Partial matches are: ");
                        for (Node result: matches){
                           System.out.println("Term: " + result.getTerm() + ". Statement: " + result.getSentence() + " (Confidence score: " + result.getScore() + ")." );
                        }
                        System.out.println();
                     }
                  } else{
                     System.out.println("No knowledge base loaded. Please load the knowledge base or add a statement to the Binary Search Tree.\n");
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
      
      //loop until the user quits the program
      while (choice!=5);
      
   }
   
   /**
   * The main entry point for the program which displays the menu for interacting with the knowledge base implemented using a Binary Search Tree (BST) and closes the scanner afterward.
   *
   * @param args The command-line arguments (not used in this program).
   */
   public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      GenericsKbBSTApp.displayMenu(scanner);
      scanner.close();
   }
}