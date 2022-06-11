import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class NodeDescendantsWatcher implements Watcher {

    private final ZooKeeper zooKeeper;
    private final String node;
    public NodeDescendantsWatcher(ZooKeeper zooKeeper, String node) {
        this.zooKeeper = zooKeeper;
        this.node = node;
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged) {
            watchDescendants(node);
            int count = countDescendants(node);
            System.out.println(node + " has " + count + " descendants");
        }
    }

    public void watchDescendants(String path) {
        try {
            for (String child : zooKeeper.getChildren(path, this)) {
                watchDescendants(path + "/" + child);
            }
        } catch (KeeperException | InterruptedException e) {
            System.err.println("Error while setting watch for " + path + ": " + e.getMessage());
        }
    }

    private int countDescendants(String path) {
        int count = 0;
        try {
            for (String child : zooKeeper.getChildren(path, false)) {
                if (child != null) {
                    count += 1 + countDescendants(path + "/" + child);
                }
            }
        } catch (KeeperException | InterruptedException e) {
            System.err.println("Error while counting " + path + " descendants: " + e.getMessage());
        }
        return count;
    }
}
