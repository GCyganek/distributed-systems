package services.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import services.*;

import services.DivisibleBy5ServiceGrpc.DivisibleBy5ServiceBlockingStub;
import services.SubtractOneServiceGrpc.SubtractOneServiceBlockingStub;
import services.NextDayServiceGrpc.NextDayServiceBlockingStub;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Client {
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    private final ManagedChannel channel;

    private final DivisibleBy5ServiceBlockingStub divisibleBy5Stub;
    private final SubtractOneServiceBlockingStub subtractOneStub;
    private final NextDayServiceBlockingStub nextDayStub;

    public Client(String remoteHost, int remotePort)
    {
        channel = ManagedChannelBuilder.forAddress(remoteHost, remotePort)
                .usePlaintext() // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid needing certificates.
                .build();

        nextDayStub = NextDayServiceGrpc.newBlockingStub(channel);
        subtractOneStub = SubtractOneServiceGrpc.newBlockingStub(channel);
        divisibleBy5Stub = DivisibleBy5ServiceGrpc.newBlockingStub(channel);
    }



    public static void main(String[] args) throws Exception
    {
        Client client = new Client("127.0.0.1", 50050);
        client.test();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


    public void test() throws InterruptedException
    {
        String line = null;
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        do
        {
            try
            {
                System.out.print("==> ");
                System.out.flush();
                line = in.readLine();
                if (line == null)
                {
                    break;
                }
                if(line.startsWith("getNextDay"))
                {
                    String arg = line.substring(line.indexOf(" ") + 1);
                    System.out.println("Sending subtractOne (" + arg + ") request");
                    NextDayRequest request = NextDayRequest.newBuilder().setCurrentDay(arg).build();
                    NextDayResult result = nextDayStub.getNextDay(request);
                    System.out.println(result.getNextDay());
                }
                else if(line.startsWith("subtractOne"))
                {
                    try {
                        long arg = Long.parseLong(line.substring(line.indexOf(" ") + 1));
                        System.out.println("Sending subtractOne (" + arg + ") request");
                        SubtractOneRequest request = SubtractOneRequest.newBuilder().setNumber(arg).build();
                        SubtractOneResult result = subtractOneStub.subtractOne(request);
                        System.out.println(result.getResult());
                    } catch (NumberFormatException e) {
                        System.out.println("Numerical arg must be provided. try again");
                    }
                }
                else if(line.startsWith("divisibleBy5"))
                {
                    try {
                        long arg = Long.parseLong(line.substring(line.indexOf(" ") + 1));
                        System.out.println("Sending divisibleBy5 (" + arg + ") request");
                        DivisibleBy5Request request = DivisibleBy5Request.newBuilder().setRequest(arg).build();
                        DivisibleBy5Result result = divisibleBy5Stub.isDivisibleBy5(request);
                        System.out.println(result.getResult());
                    } catch (NumberFormatException e) {
                        System.out.println("Numerical arg must be provided. try again");
                    }
                }
                else {
                    System.out.println("Unknown command, try again");
                }
            }
            catch (java.io.IOException ex)
            {
                System.err.println(ex);
            }
            catch (StatusRuntimeException e) {
                System.err.println(e.getMessage());
            }
        }
        while (!line.equals("q"));

        shutdown();
    }
}
