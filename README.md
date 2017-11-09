<h3>Paxos Interview Challenges</h3>

<h4>Challenge 1:</h4>

To run the Rest Service:

1. Make sure you have python, flask installed.
2. On the command prompt, go to the folder where paxos_1.py is downloaded.
3. Run the following command:
   python paxos_1.py
   
This should start your rest server.

Now you go use Postman or a similar tool.

POST the message to convert to SHA256 hash.

http://localhost:5000/messages/converttohexdigest
http://localhost:5000/messages/stringtohexdigest

Copy the hashes generated.

GET will return the corresponding message associated with the hash if present.
If not, it will return a 404.

http://localhost:5000/messages/{hash_here}

<br><h4>Challenge 2:</h4>

Reads a file which has item and price (comma separated) and returns the best items to purchase within the max value provided.

To run the program in the command prompt type:

javac -d . Paxos_2.java

java -cp . Paxos_2 pairs.txt 4500

<br><h4>Challenge 3:</h4>

Given an input of string in the form 100X01, returns all possible strings with the X value replaced by either 0 or 1.

To run the program in the command prompt type:

javac -d . Paxos_3.java

java -cp . Paxos_3 10X1


