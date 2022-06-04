package services.server;

import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import services.server.services_impl.DivisibleBy5ServiceImpl;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class divisibleBy5Server {
    private static final Logger logger = Logger.getLogger(divisibleBy5Server.class.getName());

    private Server server;

    private void start(int port) throws IOException
    {
        server = NettyServerBuilder.forPort(port).executor(Executors.newFixedThreadPool(16))
                .addService(new DivisibleBy5ServiceImpl())
                .build()
                .start();
        logger.info("divisibleBy5Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down divisibleBy5Server since JVM is shutting down");
                divisibleBy5Server.this.stop();
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
        final divisibleBy5Server server = new divisibleBy5Server();
        int port = Integer.parseInt(args[0]);
        server.start(port);
        server.blockUntilShutdown();
    }
}
