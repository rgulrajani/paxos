/**
 * How to run the sample program?
 *
 * javac -d . Paxos_3.java
 *
 * java -cp . Paxos_3 10X1
 *
 * java -cp . Paxos_3 X10X1
 *
 */

import java.util.Arrays;
import java.util.ArrayList;

class Paxos_3 {
  public static void main(String[] args) {
    String pattern = args[0];
    // Converting the original String to a char Array.
    char[] charArr = pattern.toCharArray();

    ArrayList<String> results = new ArrayList<String>();
    results = patterns(charArr, results);

    System.out.println("Final set of values = ");
    for (int i = 0; i < results.size(); i++) {
      System.out.println(results.get(i));
    }
  }

  public static ArrayList<String> patterns(char[] charArr, ArrayList<String> strList) {
    if (charArr.length == 0) { return strList; }
    // If the character is 0 or 1, then append it to each string the list.
    if (charArr[0] == '0' || charArr[0] == '1') {
      // Initially the array list is empty, so create the first element.
      if (strList.size() == 0) {
        strList.add(0, "" + charArr[0]);
      } else {
        for (int i = 0; i < strList.size(); i++) {
          strList.set(i, strList.get(i) + charArr[0]);
        }
      }
    }

    // If an X is present, make a copy of each element in the list.
    // For half the list append 0 to the strings.
    // For half the list append 1 to the strings.
    if (charArr[0] == 'X') {
      int len = strList.size();
      // If the first character is X, the array list will be empty,
      // So we create two elements. i.e one string with 0 and one string with 1.
      if (len == 0) {
        strList.add(0, "" + '0');
        strList.add(1, "" + '1');
      } else {
        for (int i = 0; i < len; i++) {
          strList.add(strList.get(i));
        }
        for (int i = 0; i < strList.size(); i++) {
          if (i < strList.size()/2) {
            strList.set(i, strList.get(i) +'0');
          } else {
            strList.set(i, strList.get(i) +'1');
          }
        }
      }
    }

    // Recursively calculate all string sets.
    return patterns(Arrays.copyOfRange(charArr, 1, charArr.length), strList);
  }
}
