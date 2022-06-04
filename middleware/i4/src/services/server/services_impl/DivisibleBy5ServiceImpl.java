package services.server.services_impl;

import io.grpc.stub.StreamObserver;
import services.DivisibleBy5Request;
import services.DivisibleBy5Result;
import services.DivisibleBy5ServiceGrpc;

public class DivisibleBy5ServiceImpl extends DivisibleBy5ServiceGrpc.DivisibleBy5ServiceImplBase {
    @Override
    public void isDivisibleBy5(DivisibleBy5Request request, StreamObserver<DivisibleBy5Result> responseObserver) {
        System.out.println("isDivisibleBy5 (" + request.getRequest() + ")");
        boolean val = request.getRequest() % 5 == 0;
        DivisibleBy5Result result = DivisibleBy5Result.newBuilder().setResult(val).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
