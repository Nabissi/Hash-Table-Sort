import java.util.*;
class ArticleData {
  	int id;     //variable declaration for article information
	String title;
	String author;
	ArrayList<String> keywords; //arraylist for keywords

	ArticleData(int id, String title, String author, int keywordCount){ //constructor
		this.id=id; //set values for information
		this.title=title;
		this.author=author;
		keywords=new ArrayList<String>(keywordCount); //dynamically create new list for keywords
	}

  void addKeyword(String keyword) { //method to add keywords
    keywords.add(keyword);
  }
}