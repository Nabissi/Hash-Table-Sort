import java.util.ArrayList;
import java.io.*;

/* Pending Items        DONE DONE DONE I STAYED FOCUSED!!!!!!!!!!
   1. Add each key to its potential index based on collision handling CHECK
   2. Add a new key or append the article node to each existing key in the hashtable CHECK
   3. Print in a similar format as BinarySearchTree <add Print method inside the HashTable class> CHEC
      0 key
        article node --> article node
      1 key
        ...
      2 key
        ...

*/

class Project3Testing {
    BufferedReader fileReader; //declare filereader
    HashTable hTable; //declare hashtable
    int tableSize; //declare table size
    public static void main(String [] args) {
      Project3Testing Test = new Project3Testing("src/datafile.txt"); //create new project3Testing with datafile as argument
      //System.out.println("I will stay focused!"); //stay focussssed!!!!!!!!!
      Test.hTable.print();
    }

    public Project3Testing(String filename) { //constructor
      try { //tryblock for filereading
        fileReader = new BufferedReader(new FileReader(filename)); //dynamically create bufferedreader object
        ArrayList<String> tempArray = new ArrayList<String>(); //create temp array
        ArticleData article; //declare article
        while ((article = readNextRecord()) != null) { //while next line is not null read in article
          //System.out.printf("Test %d\n", article.keywords.size());
          for (int i=0; i<article.keywords.size(); i++) { //for each keyword
            if (!tempArray.contains(article.keywords.get(i))) { //if array doesnt contain keyword
              tempArray.add(article.keywords.get(i)); //add keyword
              tableSize++; //increment table size
            }
          }
        }
        //System.out.printf("Test %d\n", tableSize);
        hTable = new HashTable(tableSize); //create new hashtable dynamically
        fileReader = new BufferedReader(new FileReader(filename)); //dynamically create bufferedreader object
        while ((article = readNextRecord()) != null) { //while next line is not null
          hTable.addArticle(article); //add article
        }
      }
      catch (IOException e) { //catch for exceptions
        e.printStackTrace();
      }
    }

    public ArticleData readNextRecord() { //method to read next record
      if (fileReader == null) { //if file is empty print error
        System.out.println("Error: You must open the file first.");
        return null;
      }
      else {
        ArticleData article; //declare article
        try{ //try block for file reading
          String data = fileReader.readLine(); //read in data
          if (data==null) //if data is null return null
            return null;
          int titleId = Integer.parseInt(data); //change titleID to int
          String title = fileReader.readLine(); //read title
          String author = fileReader.readLine(); //read author
          int numKeys = Integer.parseInt(fileReader.readLine()); //read num keys
          article = new ArticleData(titleId, title, author, numKeys); //create new articledata object

          String keyword; //create keyword
          for (int  i=0; i<numKeys; i++) { //for numkeys
            keyword = fileReader.readLine(); //read in each key and add to list
            article.addKeyword(keyword);
          }
          // we can add testing for space later; for now read the blank line
          fileReader.readLine(); //formatting garbage
        }
        catch(NumberFormatException e) { //catch block for exceptions
          System.out.println("Error: Number expected!");
          return null;
        }
        catch(Exception e){ //catch block for exceptions
          System.out.println("Fatal Error: " + e);
          return null;
        }
        return article; //return article
      }
    }
}