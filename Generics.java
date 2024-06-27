/**
* Class that represents a data structure for storing information about a term,
* sentence, and confidence score. Instances of this class are used in the
* {@link GenericsKbArrayApp} class.
*
* @author Nikita Martin
* @version 1.0
* @since 24/02/2024
**/
public class Generics{
   private String term, sentence;
   private double confidenceScore;
   
   /**
   * Default constructor for Generics.
   * Initializes the term and sentence to "", and the confidenceScore to 0.
   */ 
   public Generics(){
      this.term = "";
      this.sentence = "";
      this.confidenceScore = 0;
   }
   
   /**
   * Parameterized constructor for Generics.
   * Sets the term, sentence and score based on the provided values.
   *
   * @param term  The initial value for the term.
   * @param sentence The initial value for the sentence.
   * @param score The initial value for the score.
   */
   public Generics(String term, String sentence, double score){
      this.term = term;
      this.sentence = sentence;
      confidenceScore = score;
   }
   
   /**
   * Retrieves the term associated with this Generics object.
   *
   * @return The term of this Generics object.
   */
   public String getTerm(){
      return term;
   }
   
   /**
   * Retrieves the sentence associated with this Generics object.
   *
   * @return The sentence of this Generics object.
   */
   public String getSentence(){
      return sentence;
   }
   
   /**
   * Retrieves the confidence score associated with this Generics object.
   *
   * @return The confidence score of this Generics object.
   */
   public double getScore(){
      return confidenceScore;
   }
   
   /**
   * Sets the term for this Generics object.
   *
   * @param term  The new value for the term.
   */
   public void setTerm(String term){
      this.term = term;
   }
   
   /**
   * Sets the sentence for this Generics object.
   *
   * @param sentence  The new value for the sentence.
   */
   public void setSentence(String sentence){
      this.sentence = sentence;
   }
   
   /**
   * Adds a new statement with an updated sentence and confidence score.
   *
   * @param newSentence The new sentence to be added or updated
   * @param newScore The new confidence score associated with the statement
   **/
   public void update(String newSentence, double newScore){
      //updates the sentence
      setSentence(newSentence);
      // Check if the new score is greater than or equal to the existing score.
      // If yes, set with the new confidence score
      if (newScore >= this.confidenceScore){
         setScore(newScore);
      }
   }
   
   /**
   * Sets the confidence score for this Generics object.
   *
   * @param score  The new value for the confidence score.
   */ 
   public void setScore(double score){
      confidenceScore = score;
   }
}