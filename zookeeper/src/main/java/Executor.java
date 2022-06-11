import ch.qos.logback.classic.Level;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Scanner;

public class Executor {

    static Logger root = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

    public static void main(String[] args) {
        root.setLevel(Level.INFO);

        try {
            if (args.length != 2) {
                new Executor();
            } else {
                new Executor(args[0], args[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean running = true;
    private final String node;
    private final ZooKeeper zooKeeper;
    private final NodeDescendantsWatcher nodeDescendantsWatcher;
    private final NodeWatcher nodeWatcher;

    public Executor() throws IOException {
        this("127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183", "mspaint");
    }

    public Executor(String connectString, String executable) throws IOException {
        this.node = "/z";
        this.zooKeeper = new ZooKeeper(connectString, 3000, null);
        this.nodeDescendantsWatcher = new NodeDescendantsWatcher(zooKeeper, node);
        this.nodeWatcher = new NodeWatcher(zooKeeper, node, nodeDescendantsWatcher, new String[]{executable});

        init();
        reader();
    }

    private void init() {
        try {
            if (zooKeeper.exists(node, nodeWatcher) != null) {
                nodeDescendantsWatcher.watchDescendants(node);
            }
        } catch (KeeperException | InterruptedException e) {
            System.err.println("Error while setting watch on " + node +": " + e.getMessage());
        }
    }

    private void reader() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String command = in.nextLine();
            processCommand(command);
            if (!running) return;
        }
    }

    private void processCommand(String command) {
        switch (command) {
            case "quit" -> handleQuit();
            case "print" -> handlePrint(node);
        }
    }

    private void handleQuit() {
        try {
            running = false;
            zooKeeper.close();
        } catch (InterruptedException e) {
            System.err.println("Error while closing the zookeeper: " + e.getMessage());
        }
    }

    private void handlePrint(String path) {
        try {
            if (zooKeeper.exists(path, false) == null) {
                System.out.println(path + " node does not exist");
                return;
            }
            System.out.println(path);
            for (String child : zooKeeper.getChildren(path, false)) {
                handlePrint(path + "/" + child);
            }
        } catch (KeeperException | InterruptedException e) {
            System.err.println("Error while printing tree on path " + path + ": " + e.getMessage());
        }
    }

}
