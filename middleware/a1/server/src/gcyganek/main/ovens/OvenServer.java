package gcyganek.main.ovens;

import com.zeroc.Ice.Communicator;
import com.zeroc.Ice.Identity;
import com.zeroc.Ice.ObjectAdapter;
import com.zeroc.Ice.Util;

public class OvenServer {

    public static void main(String[] args)
    {
        OvenServer app = new OvenServer();
        app.run(args);
    }

    public void run(String[] args) {
        int status = 0;
        Communicator communicator = null;

        try	{
            communicator = Util.initialize(args);

            ObjectAdapter adapter = communicator.createObjectAdapter("OvensAdapter");

            BasicOven basicOvenServant1 = new BasicOven("BasicOvenServant1");
            BasicOven basicOvenServant2 = new BasicOven("BasicOvenServant2");

            ProOven proOvenServant1 = new ProOven("ProOvenServant1");
            ProOven proOvenServant2 = new ProOven("ProOvenServant2");

            adapter.add(basicOvenServant1, new Identity("basicOven1", "ovens"));
            adapter.add(basicOvenServant2, new Identity("basicOven2", "ovens"));
            adapter.add(proOvenServant1, new Identity("proOven1", "ovens"));
            adapter.add(proOvenServant2, new Identity("proOven2", "ovens"));

            adapter.activate();

            System.out.println("Entering event processing loop...");

            communicator.waitForShutdown();

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            status = 1;
        }
        if (communicator != null) {
            try {
                communicator.destroy();
            }
            catch (Exception e) {
                System.err.println(e.getMessage());
                status = 1;
            }
        }
        System.exit(status);
    }
}
