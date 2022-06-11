import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class NodeWatcher implements Watcher {

    private final ZooKeeper zooKeeper;
    private final String node;
    private final NodeDescendantsWatcher nodeDescendantsWatcher;
    private final String[] runApp;
    private Process app;

    public NodeWatcher(ZooKeeper zooKeeper, String node, NodeDescendantsWatcher nodeDescendantsWatcher, String[] runApp) {
        this.zooKeeper = zooKeeper;
        this.node = node;
        this.nodeDescendantsWatcher = nodeDescendantsWatcher;
        this.runApp = runApp;
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        switch (watchedEvent.getType()) {
            case NodeCreated -> {
                runGraphicApp();
                nodeDescendantsWatcher.watchDescendants(node);
            }
            case NodeDeleted -> stopGraphicApp();
        }
        try {
            zooKeeper.exists(node, this);
        } catch (KeeperException | InterruptedException e) {
            System.err.println("Error while setting watch on " + node + ": " + e.getMessage());
        }
    }

    private void runGraphicApp() {
        if (app == null || !app.isAlive()) {
            try {
                app = Runtime.getRuntime().exec(runApp);
            } catch (IOException e) {
                System.err.println("Error while running app: " + e.getMessage());
            }
        }
    }

    private void stopGraphicApp() {
        if (app != null && app.isAlive()) {
            app.destroy();
        }
    }
}
