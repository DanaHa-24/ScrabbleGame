package Model

import Backend.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HostHandler implements ClientHandler {
    private Socket serverSocket;

    public HostHandler(Socket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void handleClient(InputStream inputStream, OutputStream outputStream) {
        try {
            // Read input from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String clientInput = reader.readLine();

            // Process the client input
            String response = processClientInput(clientInput);

            // Send response to the client
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println(response);
            writer.flush();

            // Close the streams
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String processClientInput(String clientInput) {
        // Process the client input and generate a response
        // You can implement your logic here based on the requirements

        // For example, let's assume the response is the input reversed
        StringBuilder reversedInput = new StringBuilder(clientInput).reverse();
        return reversedInput.toString();
    }

    @Override
    public void close() throws IOException {
        // Clean up any resources used by the handler
        // This method will be called when the client connection is closed

        // You can close any open streams or perform any necessary cleanup tasks here
        // For example:
        serverSocket.close();
    }

    public static void main(String[] args) {
        // Create a MyServer instance to get the IP and port information
        MyServer server = new MyServer();
        String serverIp = server.getIp();
        int serverPort = server.getPort();

        try {
            // Open a socket to the server
            Socket serverSocket = new Socket(serverIp, serverPort);

            // Create an instance of HostHandler and pass the server socket
            HostHandler hostHandler = new HostHandler(serverSocket);

            // Send the IP and port information to the guests player
            OutputStream outputStream = serverSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream);
            writer.println("Server IP: " + serverIp);
            writer.println("Server Port: " + serverPort);
            writer.flush();

            // Start the client handling logic
            hostHandler.handleClient(serverSocket.getInputStream(), outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
