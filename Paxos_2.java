/**
 * How to run the sample program?
 *
 * javac -d . Paxos_2.java
 *
 * java -cp . Paxos_2 pairs.txt 7500
 *
 * java -cp . Paxos_2 pairs.txt 10000
 *
 * java -cp . Paxos_2 pairs.txt 1000
 *
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

class Paxos_2 {
  public static void main(String[] args) {
    HashMap <Double, Product> prodList = new HashMap<Double, Product> ();
    ArrayList<Double> priSet = new ArrayList<Double>();

    String line = null;
    String fileName = args[0];
    double maxValue = Double.parseDouble(args[1]);

    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      String[] items;

      while((line = bufferedReader.readLine()) != null) {
        items = line.split(",");
        prodList.put(Double.parseDouble(items[1]), new Product(items[0], Double.parseDouble(items[1])));
        priSet.add(Double.parseDouble(items[1]));
      }

      bufferedReader.close();
      fileReader.close();

      // Select the best gifts within the price range for two people.
      getBestGiftForTwo(prodList, priSet, maxValue);
      // Select the best gifts within the price range for three people.
      getBestGiftForThree(prodList, priSet, maxValue);

    } catch(FileNotFoundException ex) {
      System.out.println("Unable to open file '" + fileName + "'");
    } catch(IOException ex) {
      System.out.println("Error reading file '" + fileName + "'");
    }
  }

  public static void getBestGiftForTwo(HashMap <Double, Product> productList, ArrayList<Double> priceSet, double maxVal) {
    boolean maxFound = false;
    int maxOne = 0, maxTwo = 0;
    outer:
    for (int i = 0; i < priceSet.size() - 2; i++) {
      for (int j = i + 1; j < priceSet.size(); j++) {
        // If the minimum two values in the list are more than the the max amount, no gift will be available.
        if ((priceSet.get(i) + priceSet.get(j)) > maxVal) {
          break outer;
        }
        // If the maximum two values in the list are less than the the max amount, they are the best gifts.
        if ((priceSet.get(priceSet.size() - 1) + priceSet.get(priceSet.size() - 2)) <= maxVal) {
          maxOne = priceSet.size() - 1;
          maxTwo = priceSet.size() - 2;
          maxFound = true;
          break outer;
        }
        if ((priceSet.get(i) + priceSet.get(j)) == maxVal) {
          maxOne = i;
          maxTwo = j;
          maxFound = true;
          break outer;
        } else if ((priceSet.get(i) + priceSet.get(j)) < maxVal) {
          maxOne = i;
          maxTwo = j;
          maxFound = true;
        }
      }
    }

    if (maxFound) {
      System.out.println("The best gifts for the two friends will be: ");
      System.out.println(" " + productList.get(priceSet.get(maxOne)) + ", " + productList.get(priceSet.get(maxTwo)));
    } else {
      System.out.println("Not possible!!!");
    }
  }

  public static void getBestGiftForThree(HashMap <Double, Product> productList, ArrayList<Double> priceSet, double maxVal) {
    boolean maxFound = false;
    int maxOne = 0, maxTwo = 0, maxThree = 0;
    outer:
    for (int i = 0; i < priceSet.size() - 3; i++) {
      for (int j = i + 1; j < priceSet.size(); j++) {
        for (int k = j + 1; k < priceSet.size(); k++) {
        // If the minimum three values in the list are more than the the max amount, no gift will be available.
          if ((priceSet.get(i) + priceSet.get(j) + priceSet.get(k)) > maxVal) {
            break outer;
          }
          // If the maximum three values in the list are less than the the max amount, they are the best gifts.
          if ((priceSet.get(priceSet.size() - 1) + priceSet.get(priceSet.size() - 2) + priceSet.get(priceSet.size() - 3)) <= maxVal) {
            maxOne = priceSet.size() - 1;
            maxTwo = priceSet.size() - 2;
            maxThree = priceSet.size() - 3;
            maxFound = true;
            break outer;
          }
          if ((priceSet.get(i) + priceSet.get(j) + priceSet.get(k)) == maxVal) {
            maxOne = i;
            maxTwo = j;
            maxThree = k;
            maxFound = true;
            break outer;
          } else if ((priceSet.get(i) + priceSet.get(j) + priceSet.get(k)) < maxVal) {
            maxOne = i;
            maxTwo = j;
            maxThree = k;
            maxFound = true;
          }
        }
      }
    }

    if (maxFound) {
      System.out.println("The best gifts for the three friends will be: ");
      System.out.println(" " + productList.get(priceSet.get(maxOne)) + ", " + productList.get(priceSet.get(maxTwo)) + ", " + productList.get(priceSet.get(maxThree)));
    } else {
      System.out.println("Not possible!!!");
    }
  }

  public static class Product {
    protected String name;
    protected double price;

    public Product(String nm, double value) {
      name = nm;
      price = value;
    }

    public double getPrice() {
      return price;
    }

    @Override
    public String toString() {
      return String.format("%s %s", name, price);
    }
  }
}
