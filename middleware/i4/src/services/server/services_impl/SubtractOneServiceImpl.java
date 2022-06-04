package services.server.services_impl;

import io.grpc.stub.StreamObserver;
import services.SubtractOneRequest;
import services.SubtractOneResult;
import services.SubtractOneServiceGrpc;

public class SubtractOneServiceImpl extends SubtractOneServiceGrpc.SubtractOneServiceImplBase {
    @Override
    public void subtractOne(SubtractOneRequest request, StreamObserver<SubtractOneResult> responseObserver) {
        System.out.println("subtractOne (" + request.getNumber() + ")");
        long val = request.getNumber() - 1;
        SubtractOneResult result = SubtractOneResult.newBuilder().setResult(val).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
