package gcyganek.main.lights;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

import java.util.Arrays;

public class LightServer {
    public static void main(String[] args)
    {
        LightServer app = new LightServer();
        app.run(args);
    }

    public void run(String[] args) {
        int status = 0;
        Communicator communicator = null;

        try	{
            communicator = Util.initialize(args);

            ObjectAdapter adapter = communicator.createObjectAdapter("LightsAdapter");

            BasicLight basicLightServant1 = new BasicLight("BasicLightServant1");
            BasicLight basicLightServant2 = new BasicLight("BasicLightServant2");

            ProLight proLightServant1 = new ProLight("ProLightServant1");
            ProLight proLightServant2 = new ProLight("ProLightServant2");

            adapter.add(basicLightServant1, new Identity("basicLight1", "lights"));
            adapter.add(basicLightServant2, new Identity("basicLight2", "lights"));
            adapter.add(proLightServant1, new Identity("proLight1", "lights"));
            adapter.add(proLightServant2, new Identity("proLight2", "lights"));

            adapter.activate();

            System.out.println("Entering event processing loop...");

            communicator.waitForShutdown();

        }
        catch (Exception e) {
            System.err.println(e);
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            }
            catch (Exception e) {
                System.err.println(e);
                status = 1;
            }
        }
        System.exit(status);
    }
}
