/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */
import java.util.LinkedList;
import java.util.List;

public class ClosestWords {
  LinkedList<String> closestWords = null;
  int[][] dynProgMatrix = new int[40][40];
  int closestDistance = -1;
  String oldWord = "";

  int partDist(String w1, String w2, int w1len, int w2len) {
    int count = 0;
    for(int i = 0; i < Math.min(oldWord.length(), w2len); i++){
      if(oldWord.charAt(i) != w2.charAt(i)){
        count = i;
        break;
      }
    }
    
    int res, addLetter, deleteLetter;
    for(int i = 1; i <= w1len; i++) {
      for(int j = count + 1; j <= w2len; j++){
        res = dynProgMatrix[i-1][j-1] + (w1.charAt(i-1) == w2.charAt(j-1) ? 0 : 1);
        addLetter =  dynProgMatrix[i-1][j]+1;
        deleteLetter =  dynProgMatrix[i][j-1]+1;
        dynProgMatrix[i][j] = Math.min(Math.min(res, addLetter), deleteLetter);
      }
    }
    
    oldWord = w2;
    return dynProgMatrix[w1len][w2len];
  }

  int distance(String w1, String w2) {
    return partDist(w1, w2, w1.length(), w2.length());
  }
  
  public void initiateArray(){
    for(int i = 0; i < 40; i++) {
      dynProgMatrix[0][i] = i;
      dynProgMatrix[i][0] = i;
    }
  }

  public ClosestWords(String w, List<String> wordList) {
    initiateArray();
    for (String s : wordList) {
      if(Math.abs(w.length() - s.length()) > closestDistance && closestDistance != -1) {
        continue;
      }
      int dist = distance(w, s);
      // System.out.println("d(" + w + "," + s + ")=" + dist);
      if (dist < closestDistance || closestDistance == -1) {
        closestDistance = dist;
        closestWords = new LinkedList<String>();
        closestWords.add(s);
      }
      else if (dist == closestDistance)
        closestWords.add(s);
    }
  }

  int getMinDistance() {
    return closestDistance;
  }

  List<String> getClosestWords() {
    return closestWords;
  }
}
