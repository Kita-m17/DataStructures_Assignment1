# Name: Nikita Martin
# Date: 1 March 2024
# DataStructures_Assignment1
This assignment aims to create a Java program to query and update knowledge in GenericsKB, focusing on core functionality and potential additional features for a more elaborate application.

# Introduction
The creation of appropriate data structures is important for enabling efficient information retrieval and analysis in dynamic areas of information management and knowledge representation. This report explores the creation, use, searching, and testing of a knowledge base management that makes use of many data structures and object-oriented concepts. In this assignment, two of the same applications will be created that allow a user to interact with a knowledge base, however, these applications will use different data structures. Building a strong platform that can store, query, and update data-related terms, sentences, and related confidence scores is the main goal of this assignment. By combining algorithmic efficiency with object-oriented concepts, the system seeks to offer user-friendly features for efficiently maintaining knowledge bases. The architecture of the implemented system, the relationship between its component classes, and the reasoning behind design decisions will all be covered in detail in this report. It will also provide insights into the testing process, demonstrating the system’s robustness in a variety of scenarios and the subtleties of its behaviour.

# Aim:
The aims of the ‘GenericsKbBSTApp’ and the ‘GenericsKbArrayApp’ are to provide 
platforms for managing knowledge bases, but they use different underlying data structures, leading to distinct advantages and use cases.

# Object-Oriented Design Overview
Classes:
1. ‘Generics’ class:
Purpose: Represents a Generics object in the Generics type array, containing 
information about a specific term, sentence, and confidence score.
Key methods: 
• Getters and setters for the term, sentence, and confidence score of the 
Generics object.
• 'update’: Updates the sentence and confidence score of a term of the 
Generics object.
Relationships: Used as the data structure for storing information in the 
‘GenericsKbArrayApp’ class.
2. ‘Node’ class:
Purpose: Represents a Node object in the binary search tree (BST), containing 
information about a specific term, sentence, and confidence score.

Key methods:
• Getters and setters for the term, sentence, and confidence score of the Node 
object.
• 'update’: Updates the sentence and confidence score of a term of the Node 
object.
Relationships: Used as the data structure for storing information in the 
‘GenericsKbBSTApp’ class where it is connected in a hierarchical structure within the binary search tree.
3. ‘GenericsKbArrayApp’ class:
Purpose: This class implements a knowledge base using a simple array of ‘Generics’ objects. It provides functionality to load data from a file, add or update the statements in the knowledge base, search for items by term, and search for items by both term and sentence. Below is an overview of the methods and functionalities:
Instance Variables:
• ‘private static int counter’: this variable counts the number of the lines in the file, to create the array size of the counter.
• ‘private static Generics[ ] knowledgebase’: The array holds the knowledge base where the elements are of the instance of the ‘Generics’ class.
Constructor: initializes the knowledge base to null.

Methods:
• ‘loadFromFile’: 
‘public static void loadFromFile(String file)’: Reads data from a file, and inserts it into a knowledge base array and creates ‘Generics’ objects of each line read into the file.
• ‘makeData’:
‘public static Generic makeData(String data)’: Parses a string of data to create a new ‘Generics’ object containing a term, sentence, and confidence score that are separated by tabs. This method is used when loading data from a file.
• ‘addStatementToKnowledgeBase’: 
‘public static void addStatementToKnowledgeBase(String term, String sentence, double score)’: Updates a sentence of an existing ‘Generics’ object in the knowledge base for a specific term, and updates the confidence score of the given term.
• ‘searchTerm’: 
‘public static void searchTerm(String term)’: Linear searches for a specific term in the knowledge base and prints the corresponding sentence and confidence score.
• ‘similarSearches’:
‘public static List<Generics> similarSearches(String term)’: Linear searches for partial matches to a term in the knowledge base. Returns a list of nodes containing partial matches or an empty list if not found.
• ‘searchForTermAndSentence’:
‘public static void searchForTermAndSentence(String term, String sentence)’: Linear searches for a ‘Generics’ object in the knowledge base array containing a specified term and sentence. If found, print the confidence score, otherwise, print an appropriate message.
• ‘displayMenu’:
‘public static void displayMenu(Scanner input)’: Displays a method that allows for user interaction with the knowledge base.
• ‘main’:
‘public static void main(String [ ] args)’: The main method to execute the program.

Error handling:
- If the user inserts an option that is not in the given list such as a number outside of 1 and 5, or a letter, the program will print that the user should only enter a number between 1 and 5.
- File Not Found Exception: When attempting to load data from the file using the ‘loadFromFile’ method, the class catches a ‘FileNotFoundException’ if the specified file is not found, which is then rethrown, indicating to the calling code that the file cannot be located.
- File Closing in Finally Block: in the ‘loadFromFile’ method, the file is closed in the ‘finally’ block to ensure that the resources are released, even if an exception occurs during the file processing.
- Null Pointer Check Before Accessing the Knowledge Base: Before performing operations on the knowledge base array, the class checks is the array is null to avoid the ‘NullPointerException’
Relationships: Utilizes the ‘Generics’ class for storing information in the array. It leverages the capabilities provided by the ‘Generics’ class to manage the knowledge base data efficiently. The ‘Generics’ class incapsulates the knowledge base entries, providing a clear and reusable structure to implement high-level knowledgebase functionality efficiently.
Usage Overview: The user interacts with the program through a menu, where they can load a knowledge base file, add new statements, search for items by terms, or quit the program. The knowledge base is stored in an array, and the methods handle operations line insert, search, and partial matching.
4. ‘GenericsKbBSTApp’ class
Purpose: This class implements a knowledge base using a Binary Search Tree (BST) of ‘Node’ objects. Each node in the tree holds a piece of information, and its functionality is to load data from a file, add or update the statements in the knowledge base, search for items by term, and search for items by both term and sentence. Below is an overview of the methods and functionalities:
Instance Variables:
• ‘public static Node root’: This variable represents the root in the Binary Search Tree.
Constructor: Initializes the ‘root’ to null, representing an empty tree.

Methods:
• ‘loadFromFile’: 
‘public static void loadFromFile(String file)’: Reads date from a file into a binary search tree and creates ‘Node’ objects of each line read into the file.
• ‘makeData’:
‘public static Node makeDate(String data)’: Parses a string of data to create a new ‘Node’ objects from a string containing a term, sentence, and confidence score that are separated by tabs. This method is used when loading data from a file.
• ‘insert’:
‘public static Node insert(Node root, Node newNode)’: Inserts a new node into the BST based on the binary search tree rules and logic. If the tree is empty, it return a new node, otherwise it recursively finds the correct position for insertion.
• ‘search’:
‘public static Node search(Node node, String term)’: Recursively searches for a node with a specified term in the BST. Return a node if found, otherwise return null.
• ‘addOrUpdate’:
‘public static Node addOrUpdate(Node node, String term, String sentence, double score)’: Adds or updates a node in the knowledge basefor a specified term.
• ‘searchTerm’:
‘public static void searchTerm(String term)’: Recursively searches for a specific term in the BST using the ‘search’ method and prints the corresponding sentence and confidence score.
• ‘similarSearches’:
‘public static List<Node> similarSearches(Node node, String term)’:
Recursively searches for partial matches to a term in the BST. Return a list of nodes containing partial matches or an empty list if not found.

Error handling:
- If the user inserts an option that is not in the given list such as a number outside of 1 and 5, or a letter, the program will print that the user should only enter a number between 1 and 5.
- File Not Found Exception: Like the array implementation, this class catches a ‘FileNotFoundException’ when attempting to load data from a file in the ‘loadFromFile’ method, which is then rethrown for the calling code to handle.
- Null Pointer Check Before Tree Operations: Before performing tree-related operations(searching and inserting) in methods like ‘search’ and ‘insert’, the class checks if the root node is null to avoid the ‘NullPointerException’.
Relationships: Utilizes the ‘Node’ class for building and managing the Binary Search Tree in the context of the knowledge bases. Each instance of the ‘Node’ class encapsulates information about a specific term, sentence, and confidence score. The class is specifically tailored for use within the hierarchical structure of the BST. The hierarchical structure of the BST allows for quicker retrieval of knowledge base entries compared to linear search methods. The ‘Node’ class provides the necessary structure to facilitate efficient searching, insertion, and retrieval of knowledge base entries within a hierarchical tree.
Usage Overview: Like the array implementation whereby the user interacts with a menu, where they can load a knowledge base from a file, add new statements, search for items by term, and search for items by term and sentence, or quit the program. The knowledge base is stored in a Binary Search Tree, and methods handle operations like insertion, search, and partial matching.

User-Friendly Output: The BST and Array classes incorporate checks and provide messages to the user when certain events occur such as successfully 
loading data from the file or when certain conditions are not met such as not loading the knowledge base before performing operations. This helps in avoiding silent failures and enhances the user experience by providing feedback on the outcome of operations. Both classes also make use of print statements to provide informative information about the execution of various methods, which aid in understanding the flow of the program and identifying potential issues.

Traditional Array Implementation:
This implementation provides a console-based interface for managing a knowledge base using an array data structure. The standard array data structure stores and manages data from the knowledge base, whereby each element in the array corresponds to a piece of information (term, sentence, confidence score).
Key Points:
• Data is stored in memory locations.
• Accessing elements is done through indexing.
• Insertion happens at the end of the array.

Binary Search Tree implementation:
Description: This implementation provides a console-based interface for managing a knowledge base using a Binary Search Tree data structure. The Binary Search Tree data structure involves using nodes to organize and store the information. Each node in the tree holds a piece of information, which is the term, sentence, and confidence score, and the tree provides efficient searching, inserting, and retrieval capabilities. The use of a Binary Search Tree allows for efficient searching and organizing of the knowledge base.

Key Points:
• Nodes are organized in a hierarchical tree structure.
• Searching, and insertion have O(log n) complexity on worst.
• It is well-suited for tasks involving frequent search operations.

Interactions:
• GenericsKbArrayApp and GenericsKbBSTApp:
Both classes handle the knowledge base but have different ways of dealing with them.
• GenericsKbArrayApp and Generics:
Generics is the fundamental building block of the array in the GenericsKbArrayApp, 
as it is used for storing information about terms, sentences, and confidence scores.
• GenericsKbBSTApp and Node:
Node is the fundamental building block of the array in the GenericsKbBSTApp, as it is used for storing information about terms, sentences, and confidence scores.
