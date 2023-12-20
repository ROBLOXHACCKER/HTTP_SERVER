package httpserver;

import java.io.*;
import java.net.*;
public class Handler {

    public BufferedReader H_Reader;
    public DataOutputStream outputStream;

    public void handleRequest(Socket clientSocket) throws IOException {
        try {

        //var

            H_Reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outputStream = new DataOutputStream(clientSocket.getOutputStream());

            String request = H_Reader.readLine();
            System.out.println(request);

            if (request != null && "GET".equals(request.substring(0, 3))) {

                //BUILD URI

                String uri = request.split(" ")[1];
                System.out.println(uri);

                String contentType = ""; 

                //RISPOSTA SITO
                System.out.println("stampa: " + uri);
                switch (uri) {
                    case "/":
                    case "/?":
                        contentType = "text/html";
                        respond(contentType, "site/index.html");
                        break;
                    case "/style.css":
                        contentType = "text/css";
                        respond(contentType, "site/css/style.css");

                    case "/script.js":
                        contentType = "application/javascript";
                        respond(contentType, "site/js/script.js");
                        break;
                    default:
                        contentType = "text/html";
                        respond(contentType, "site/error.html");
                        break;
                    }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }
    }

    public String readFile(File htmlFile) throws IOException {

        //var

        String line = null;
        String ls = "\n";

        BufferedReader H_Reader = new BufferedReader(new FileReader(htmlFile));
        StringBuilder strBuilder = new StringBuilder();
        
        //STR BUILD

        while ((line = H_Reader.readLine()) != null) {
            strBuilder.append(line);
            strBuilder.append(ls);
        }

        // RM SEPARATOR

        H_Reader.close();

        String content = strBuilder.toString();
        return content;
    }

    public void respond(String contentType, String filePath) {
        try {
            // Variabili
            File htmlFile = new File(filePath);
            String file = readFile(htmlFile);
            System.out.println(filePath);
    
            // Risposta
            outputStream.writeBytes("HTTP/1.1 200 OK" + "\r\n");
            outputStream.writeBytes("Content-Type: " + contentType + "\r\n");
            outputStream.writeBytes("Content-Length: " + file.getBytes().length + "\r\n");
            outputStream.writeBytes("\r\n");
            outputStream.writeBytes(file);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace(); 
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }
    

}
