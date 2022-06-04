package services;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.1)",
    comments = "Source: services.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class NextDayServiceGrpc {

  private NextDayServiceGrpc() {}

  public static final String SERVICE_NAME = "services.NextDayService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<services.NextDayRequest,
      services.NextDayResult> getGetNextDayMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getNextDay",
      requestType = services.NextDayRequest.class,
      responseType = services.NextDayResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<services.NextDayRequest,
      services.NextDayResult> getGetNextDayMethod() {
    io.grpc.MethodDescriptor<services.NextDayRequest, services.NextDayResult> getGetNextDayMethod;
    if ((getGetNextDayMethod = NextDayServiceGrpc.getGetNextDayMethod) == null) {
      synchronized (NextDayServiceGrpc.class) {
        if ((getGetNextDayMethod = NextDayServiceGrpc.getGetNextDayMethod) == null) {
          NextDayServiceGrpc.getGetNextDayMethod = getGetNextDayMethod =
              io.grpc.MethodDescriptor.<services.NextDayRequest, services.NextDayResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getNextDay"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  services.NextDayRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  services.NextDayResult.getDefaultInstance()))
              .setSchemaDescriptor(new NextDayServiceMethodDescriptorSupplier("getNextDay"))
              .build();
        }
      }
    }
    return getGetNextDayMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NextDayServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NextDayServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NextDayServiceStub>() {
        @java.lang.Override
        public NextDayServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NextDayServiceStub(channel, callOptions);
        }
      };
    return NextDayServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NextDayServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NextDayServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NextDayServiceBlockingStub>() {
        @java.lang.Override
        public NextDayServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NextDayServiceBlockingStub(channel, callOptions);
        }
      };
    return NextDayServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NextDayServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NextDayServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NextDayServiceFutureStub>() {
        @java.lang.Override
        public NextDayServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NextDayServiceFutureStub(channel, callOptions);
        }
      };
    return NextDayServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class NextDayServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getNextDay(services.NextDayRequest request,
        io.grpc.stub.StreamObserver<services.NextDayResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetNextDayMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetNextDayMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                services.NextDayRequest,
                services.NextDayResult>(
                  this, METHODID_GET_NEXT_DAY)))
          .build();
    }
  }

  /**
   */
  public static final class NextDayServiceStub extends io.grpc.stub.AbstractAsyncStub<NextDayServiceStub> {
    private NextDayServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NextDayServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NextDayServiceStub(channel, callOptions);
    }

    /**
     */
    public void getNextDay(services.NextDayRequest request,
        io.grpc.stub.StreamObserver<services.NextDayResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetNextDayMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class NextDayServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<NextDayServiceBlockingStub> {
    private NextDayServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NextDayServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NextDayServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public services.NextDayResult getNextDay(services.NextDayRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetNextDayMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class NextDayServiceFutureStub extends io.grpc.stub.AbstractFutureStub<NextDayServiceFutureStub> {
    private NextDayServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NextDayServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NextDayServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<services.NextDayResult> getNextDay(
        services.NextDayRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetNextDayMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_NEXT_DAY = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NextDayServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NextDayServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_NEXT_DAY:
          serviceImpl.getNextDay((services.NextDayRequest) request,
              (io.grpc.stub.StreamObserver<services.NextDayResult>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class NextDayServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NextDayServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return services.Services.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NextDayService");
    }
  }

  private static final class NextDayServiceFileDescriptorSupplier
      extends NextDayServiceBaseDescriptorSupplier {
    NextDayServiceFileDescriptorSupplier() {}
  }

  private static final class NextDayServiceMethodDescriptorSupplier
      extends NextDayServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NextDayServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NextDayServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NextDayServiceFileDescriptorSupplier())
              .addMethod(getGetNextDayMethod())
              .build();
        }
      }
    }
    return result;
  }
}
