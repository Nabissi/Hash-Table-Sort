class ElementNode {
    String keyword; //declare keyword and head of article linked list
    ArticleNode head;

    public ElementNode(String key, ArticleNode rec){ //constructor
        keyword = key; //set keyword and head of list
        head = rec;
    }
}