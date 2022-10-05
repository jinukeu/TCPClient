import java.io.*;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Base64;

public class Main {

    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;

    public void serverConnection() {
        try {
            clientSocket = new Socket(Inet4Address.getByName("127.0.0.1"), 3000);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        try {
            out.println(message);
            String response = in.readLine();
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 추가한 코드 입니다!
    public String getImageBase64(String path) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(path));
        byte[] imageBytes = is.readAllBytes();
        String imageBase64 = Base64.getEncoder().encodeToString(imageBytes);
        return imageBase64;
    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.serverConnection();
        main.sendMessage(main.getImageBase64("wallpaperbetter.jpg")); // 820KB 까지는 정상 전송 됩니다. (그 이상의 파일은 안해봤어요)
    }

}