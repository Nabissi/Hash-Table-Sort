public class HashTable {
  ElementNode [] hashTable; //declare hashTable and tablesize
  int tableSize;

  public HashTable(int numKeyWords) { //constructor
    tableSize = nextPrime(numKeyWords * 2); //set tableSize
    hashTable = new ElementNode[tableSize]; //create hash table
  }
  public void addArticle(ArticleData article) { //method to add article
    ArticleNode newArticleNode; //declare article node and index
    int index;
    int openAddress = 1; // index = hash(key) + i where i = 1, 4, 9, 16, 25...
    int hashValue;
    for (String key:article.keywords) { //for each keyword
      //System.out.println("Testing1");
      newArticleNode = new ArticleNode(article.id, article.title, article.author, null); //dynamically create new article node
      hashValue = hash(key); //set hashvalue
      index = hashValue; //set index using hashvalue
      int newIndex; //create new index to hold new index after probing for find method
     // System.out.printf("Testing %s %d\n", key, hashValue); //print hash value
      //System.out.println("Testing " + 2189134861L % tableSize);
      if ((newIndex = find(key, index)) == -1) { //if not able to be found
        while (hashTable[index] != null) { //while spot is occupied
          //System.out.printf("%s conflicts with %s at index: %d\n", key, hashTable[index].keyword, index);
          index = (hashValue + (int) Math.pow(openAddress++, 2)) % tableSize; //use openaddress to get new index
        }
        hashTable[index] = new ElementNode(key, newArticleNode); //create element at index
      }
      else{ //keyword is found
        //System.out.printf("Current keyword: %s \t Current id %d\n", hashTable[newIndex].keyword, newArticleNode.id);
        newArticleNode.next = hashTable[newIndex].head; //insert record at head
        hashTable[newIndex].head = newArticleNode;
      }
    }
  }

  int find(String keyword, int hashValue) { //method to find object
    int index = hashValue % tableSize; //get index
    int openAddress = 1; //declare openAdress
    while (hashTable[index] != null) { //while not empty
      if (hashTable[index].keyword.compareTo(keyword) == 0){ //if found keyword
        //System.out.printf("keyword %s found at index %d which has current keyword %s\n", keyword, index, hashTable[index].keyword);
        return index;
      }
      index = (hashValue + (int) Math.pow(openAddress++, 2)) % tableSize; //find new index using probe
    }
    return -1; //return -1 if not found
  }

  private int nextPrime(int num) { //method to find next prime
      num++; //increment num
      for (int i = 2; i < num; i++) { //starting at 2
         if(num%i == 0) { //check if divisible
            num++;
            i=2;
         } else { //if not divisble continue loop
            continue;
         }
      }
      return num; //return number
   }

   private int hash(String keyword) { //hash method
    long sum = 0; //declare number and sum
    int number; 
    for (int i=0; i < keyword.length(); i++) { //for each character
      number = (int) keyword.charAt(i); //set number to value of char
      sum += number * Math.pow(tableSize, i); //add to sum with hash function
    }
    //System.out.printf("Test hash %s %d %d %d\n", keyword, sum, (int) (sum % tableSize), tableSize);
    return (int) (sum % tableSize); //return sum % table size as hash value
  }

  public void print() { //printing
    System.out.print("\n");
    for (int index=0; index<tableSize; index++) { //for each element
      if (hashTable[index] != null) { //if not empty
        System.out.printf("index [%d]: %s\n", index, hashTable[index].keyword); //print keyword
        ArticleNode article = hashTable[index].head; //set record to head of linked list
        System.out.print("\t\t"); //formatting
        while(article != null) { //while not at end of list
          System.out.printf("%d|%s|%s ---> \n\t\t", article.id, article.author, article.title); //print article information
          article = article.next; //move to next node
        }
        System.out.println("null\n"); //print null
      }
    }
  }
}