package client;

import javax.swing.event.MenuListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Client {
    MeuListener listener = null;
    public void setListener(MeuListener meuListener) {
        listener = meuListener;
    }
    public Client(String id, String localhost, int port) throws IOException {
        Socket s = new Socket(localhost, port);
        PrintStream ps = new PrintStream(s.getOutputStream());
        ps.println(id);
        //
        InputStreamReader is = new InputStreamReader(s.getInputStream());
        BufferedReader bf = new BufferedReader(is);
        String mensagem = "";
        do {
            mensagem = bf.readLine();
            if(listener != null) listener.chegouAMensagem(mensagem);
        }while(true);

        ps.close();
    }
}
