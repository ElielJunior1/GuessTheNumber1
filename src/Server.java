import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("Servidor iniciado. Aguardando jogadores...");

            Socket player1Socket = serverSocket.accept();
            System.out.println("Jogador 1 conectado.");

            Socket player2Socket = serverSocket.accept();
            System.out.println("Jogador 2 conectado.");

            Game game = new Game(player1Socket, player2Socket);
            game.start();

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
