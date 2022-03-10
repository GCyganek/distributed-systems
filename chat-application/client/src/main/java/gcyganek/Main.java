package gcyganek;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) {
        String hostName = args[0];
        String multicastAddressString = args[2];
        try {
            int port = Integer.parseInt(args[1]);
            int multicastPort = Integer.parseInt(args[3]);
            InetAddress multicastAddress = InetAddress.getByName(multicastAddressString);
            Client client = new Client(hostName, port, multicastAddress, multicastPort);
            client.run();
        } catch (NumberFormatException e) {
            System.err.println("Invalid port number argument. Client initialization rejected");
        } catch (UnknownHostException e) {
            System.err.println("Unknown multicast host. Client initialization rejected");
        }
    }

}
