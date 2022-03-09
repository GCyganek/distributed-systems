package gcyganek;

public class Main {

    public static void main(String[] args) {
        String hostName = args[0];
        try {
            int port = Integer.parseInt(args[1]);
            Client client = new Client(hostName, port);
            client.run();
        } catch (NumberFormatException e) {
            System.err.println("Invalid port number argument. Client initialization rejected");
        }
    }

}
