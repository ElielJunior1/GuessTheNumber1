import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String message = in.readLine();
            System.out.println(message);

            String playerNumber = userInput.readLine();
            out.println(playerNumber);

            while (true) {
                message = in.readLine();
                System.out.println(message);

                if (message.startsWith("Parabéns")) {
                    break;
                }

                System.out.print("Digite sua próxima tentativa: ");
                String guess = userInput.readLine();
                out.println(guess);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
