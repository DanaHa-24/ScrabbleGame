package Backend;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {

    private InputStream inFromclient;
    private OutputStream outToClient;
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        this.inFromclient = inFromclient;
        this.outToClient = outToClient;
        Scanner in = new Scanner(inFromclient);
       String[] query = in.nextLine().split(",");
       String[] books = new String[query.length-1];
       System.arraycopy(query,1,books,0,query.length-1);
       boolean flag = false;

       if(query[0].equals("Q"))
           if(DictionaryManager.query(books))
               flag = true;
       if (query[0].equals("C")) {
           if (DictionaryManager.challenge(books))
               flag = true;
       }
       PrintWriter out = new PrintWriter(outToClient);
       out.println(flag ? "true" : "false");
       out.flush();
       close();
       }

    @Override
    public void close() {
        try {
            this.inFromclient.close();
            this.outToClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
