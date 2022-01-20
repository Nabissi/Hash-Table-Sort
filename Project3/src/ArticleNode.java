class ArticleNode { 
    int id; //variable declaration for article information
    String title; 
    String author;
    ArticleNode next; //pointer to next node in linked list

    ArticleNode(int i, String t, String a, ArticleNode r){ //constructor
        id = i; //set values of information
        title = t; 
        author = a;
        next = r; //set pointer of next article
    }
}