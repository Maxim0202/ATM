package Uniform.Connect;


import lombok.Getter;
import lombok.NonNull;
import Processing.Transaction.Server;

@Getter
@NonNull
public class Connect {
    private final int port;
    private final String host;
    private boolean isOpen;

    public Connect(String host, int port) {
        this.host = host;
        this.port = port;
        this.isOpen = false;
    }

    public Server open() {
        return new Server();
    }

    public void close(){

        isOpen = false;
    }

}
