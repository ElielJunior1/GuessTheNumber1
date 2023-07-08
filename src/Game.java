import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Game extends Thread {
    private Socket player1Socket;
    private Socket player2Socket;

    private BufferedReader player1In;
    private PrintWriter player1Out;
    private BufferedReader player2In;
    private PrintWriter player2Out;

    public Game(Socket player1Socket, Socket player2Socket) {
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
    }

    @Override
    public void run() {
        try {
            player1In = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            player1Out = new PrintWriter(player1Socket.getOutputStream(), true);
            player2In = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
            player2Out = new PrintWriter(player2Socket.getOutputStream(), true);

            player1Out.println("Digite seu número de 1 a 10: ");
            player1Out.flush();
            int player1Number = Integer.parseInt(player1In.readLine());

            player2Out.println("Digite seu número de 1 a 10: ");
            player2Out.flush();
            int player2Number = Integer.parseInt(player2In.readLine());

            player1Out.println("Adivinhe o número do jogador 2 (1 a 10):");
            player2Out.println("Adivinhe o número do jogador 1 (1 a 10):");

            while (true) {
                String player1Guess = player1In.readLine();
                System.out.println("Jogador 1 adivinhou: " + player1Guess);

                if (Integer.parseInt(player1Guess) == player2Number) {
                    player1Out.println("Parabéns! Você adivinhou o número.");
                    player2Out.println("Game Over! O jogador 1 venceu.");
                    break;
                } else if (Integer.parseInt(player1Guess) > player2Number) {
                    player1Out.println("Tente um número menor.");
                } else {
                    player1Out.println("Tente um número maior.");
                }

                String player2Guess = player2In.readLine();
                System.out.println("Jogador 2 adivinhou: " + player2Guess);

                if (Integer.parseInt(player2Guess) == player1Number) {
                    player2Out.println("Parabéns! Você adivinhou o número.");
                    player1Out.println("Game Over! O jogador 2 venceu.");
                    break;
                } else if (Integer.parseInt(player2Guess) > player1Number) {
                    player2Out.println("Tente um número menor.");
                } else {
                    player2Out.println("Tente um número maior.");
                }
            }

            player1Socket.close();
            player2Socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
