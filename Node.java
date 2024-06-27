/**
* Class representing a Node class, that creates a Node object containing a term, sentence a confidence score. Instances of this class are used in the
* {@link GenericsKbBSTApp} class.
*
* @author Nikita Martin
* @version 1.0
* @since 24/02/2024
*/

public class Node{
   private String term, sentence;
   private double confidenceScore;
   private Node left, right;
   
   public Node(){}
   
   public Node(String term, String sentence, double score){
      this.term = term;
      this.sentence = sentence;
      this.confidenceScore = score;
      this.left = this.right = null;
   }
   
   /**
   * Retrieves the term associated with this Node object.
   *
   * @return The term of this Node object.
   */
   public String getTerm(){
      return term;
   }
   
   /**
   * Retrieves the sentence associated with this Node object.
   *
   * @return The sentence of this Node object.
   */
   public String getSentence(){
      return sentence;
   }
   
   /**
   * Retrieves the confidence score associated with this Node object.
   *
   * @return The confidence score of this Node object.
   */
   public double getScore(){
      return confidenceScore;
   }
   
   /**
   * Retrieves the left node associated with this Node object.
   *
   * @return The left node of this Node object.
   */
   public Node getLeftNode(){
      return left;
   }
   
   /**
   * Retrieves the right node associated with this Node object.
   *
   * @return The right node of this Node object.
   */
   public Node getRightNode(){
      return right;
   }
   
   /**
   * Sets the sentence for this Node object.
   *
   * @param sentence  The new value for the sentence.
   */
   public void setSentence(String sentence){
      this.sentence = sentence;
   }
   
   /**
   * Sets the confidence score for this Node object.
   *
   * @param score  The new value for the confidence score.
   */ 
   public void setScore(double score){
      confidenceScore = score;
   }
   
   /**
   * Sets the term for this Node object.
   *
   * @param term  The new value for the term.
   */
   public void setTerm(String term){
      this.term = term;
   }
   
   /**
   * Sets the right node for this Node object.
   *
   * @param right  The new value for the right subtree.
   */
   public void setRightNode(Node right){
      this.right = right;
   }
   
   /**
   * Sets the left node for this Node object.
   *
   * @param left  The new value for the left subtree.
   */
   public void setLeftNode(Node left){
      this.left = left;
   }
   
   /**
   * Adds a new statement with an updated sentence and confidence score.
   *
   * @param newSentence The new sentence to be added or updated
   * @param newScore The new confidence score associated with the statement
   **/
   public void update(String newSentence, double newScore){
      //updates the sentence
      this.sentence = newSentence;
      // Check if the new score is greater than or equal to the existing score.
      // If yes, set with the new confidence score
      if (newScore >= this.confidenceScore){
         this.confidenceScore = newScore;
      }
   }
  
}