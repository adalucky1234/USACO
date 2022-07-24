/*
ID: adaluck1
LANG: JAVA
TASK: gift1
*/
import java.io.*;
import java.util.*;

class gift1 {
  public static void main (String [] args) throws IOException {
    // Use BufferedReader rather than RandomAccessFile; it's much faster
    BufferedReader f = new BufferedReader(new FileReader("gift1.in"));
                                                  // input file name goes above
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));

    StringTokenizer st = new StringTokenizer(f.readLine());

    int np = Integer.parseInt(st.nextToken());    // first integer

    // build gifts map: name -> money
    HashMap<String, Integer> gifts = new HashMap<>(); 
    String[] inputGiver = new String[np];

    for (int i = 0; i < np; i++) {
       String name = f.readLine();
       gifts.putIfAbsent(name, 0);     
       inputGiver[i] = name;
    }

    // record each giver/receiver money flow
    String giver = f.readLine();
    int amount;
    int receiverCount;

    while (giver != null) {
      String line2 = f.readLine();

      // Parse amount, receiverCount;
      StringTokenizer tmp = new StringTokenizer(line2);
      amount = Integer.parseInt(tmp.nextToken()); 
      receiverCount = Integer.parseInt(tmp.nextToken()); 

      gifts.put(giver, gifts.get(giver) - amount);

      for (int i = 0; i < receiverCount; ++i) {
        String receiver = f.readLine();
        gifts.put(receiver, gifts.get(receiver) + amount / receiverCount);
      }

      if (receiverCount != 0) {
        if (amount % receiverCount != 0) {
          gifts.put(giver, gifts.get(giver) + amount % receiverCount);
        }
      }
     
      giver = f.readLine();
    }
    
    for (int i = 0; i < inputGiver.length; i++) {
      out.println(inputGiver[i] + " " + gifts.get(inputGiver[i]));
    }
    out.close();                            // close the output file
  }
}