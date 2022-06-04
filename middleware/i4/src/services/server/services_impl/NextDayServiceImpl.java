package services.server.services_impl;

import io.grpc.stub.StreamObserver;
import services.NextDayRequest;
import services.NextDayResult;
import services.NextDayServiceGrpc;

public class NextDayServiceImpl extends NextDayServiceGrpc.NextDayServiceImplBase {
    @Override
    public void getNextDay(NextDayRequest request, StreamObserver<NextDayResult> responseObserver) {
        System.out.println("getNextDay (" + request.getCurrentDay() + ")");
        String val = switch (request.getCurrentDay().toLowerCase()) {
            case "monday" -> "tuesday";
            case "tuesday" -> "wednesday";
            case "wednesday" -> "thursday";
            case "thursday" -> "friday";
            case "friday" -> "saturday";
            case "saturday" -> "sunday";
            case "sunday" -> "monday";
            default -> "Invalid input. Possible inputs: monday, tuesday, wednesday, thursday, friday, saturday, sunday";
        };

        NextDayResult result = NextDayResult.newBuilder().setNextDay(val).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
