package services.server;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import services.server.services_impl.NextDayServiceImpl;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class nextDayServer {
    private static final Logger logger = Logger.getLogger(nextDayServer.class.getName());

    private Server server;

    private void start(int port) throws IOException
    {
        server = NettyServerBuilder.forPort(port).executor(Executors.newFixedThreadPool(16))
                .addService(new NextDayServiceImpl())
                .build()
                .start();
        logger.info("nextDayServer started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down nextDayServer since JVM is shutting down");
                nextDayServer.this.stop();
                System.err.println("*** server shut down");
            }
        });
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    /**
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        final nextDayServer server = new nextDayServer();
        int port = Integer.parseInt(args[0]);
        server.start(port);
        server.blockUntilShutdown();
    }
}
