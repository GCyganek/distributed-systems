package services;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.1)",
    comments = "Source: services.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SubtractOneServiceGrpc {

  private SubtractOneServiceGrpc() {}

  public static final String SERVICE_NAME = "services.SubtractOneService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<services.SubtractOneRequest,
      services.SubtractOneResult> getSubtractOneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "subtractOne",
      requestType = services.SubtractOneRequest.class,
      responseType = services.SubtractOneResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<services.SubtractOneRequest,
      services.SubtractOneResult> getSubtractOneMethod() {
    io.grpc.MethodDescriptor<services.SubtractOneRequest, services.SubtractOneResult> getSubtractOneMethod;
    if ((getSubtractOneMethod = SubtractOneServiceGrpc.getSubtractOneMethod) == null) {
      synchronized (SubtractOneServiceGrpc.class) {
        if ((getSubtractOneMethod = SubtractOneServiceGrpc.getSubtractOneMethod) == null) {
          SubtractOneServiceGrpc.getSubtractOneMethod = getSubtractOneMethod =
              io.grpc.MethodDescriptor.<services.SubtractOneRequest, services.SubtractOneResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "subtractOne"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  services.SubtractOneRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  services.SubtractOneResult.getDefaultInstance()))
              .setSchemaDescriptor(new SubtractOneServiceMethodDescriptorSupplier("subtractOne"))
              .build();
        }
      }
    }
    return getSubtractOneMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SubtractOneServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubtractOneServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubtractOneServiceStub>() {
        @java.lang.Override
        public SubtractOneServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubtractOneServiceStub(channel, callOptions);
        }
      };
    return SubtractOneServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SubtractOneServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubtractOneServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubtractOneServiceBlockingStub>() {
        @java.lang.Override
        public SubtractOneServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubtractOneServiceBlockingStub(channel, callOptions);
        }
      };
    return SubtractOneServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SubtractOneServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SubtractOneServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SubtractOneServiceFutureStub>() {
        @java.lang.Override
        public SubtractOneServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SubtractOneServiceFutureStub(channel, callOptions);
        }
      };
    return SubtractOneServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SubtractOneServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void subtractOne(services.SubtractOneRequest request,
        io.grpc.stub.StreamObserver<services.SubtractOneResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubtractOneMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubtractOneMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                services.SubtractOneRequest,
                services.SubtractOneResult>(
                  this, METHODID_SUBTRACT_ONE)))
          .build();
    }
  }

  /**
   */
  public static final class SubtractOneServiceStub extends io.grpc.stub.AbstractAsyncStub<SubtractOneServiceStub> {
    private SubtractOneServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubtractOneServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubtractOneServiceStub(channel, callOptions);
    }

    /**
     */
    public void subtractOne(services.SubtractOneRequest request,
        io.grpc.stub.StreamObserver<services.SubtractOneResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSubtractOneMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SubtractOneServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<SubtractOneServiceBlockingStub> {
    private SubtractOneServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubtractOneServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubtractOneServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public services.SubtractOneResult subtractOne(services.SubtractOneRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSubtractOneMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SubtractOneServiceFutureStub extends io.grpc.stub.AbstractFutureStub<SubtractOneServiceFutureStub> {
    private SubtractOneServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SubtractOneServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SubtractOneServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<services.SubtractOneResult> subtractOne(
        services.SubtractOneRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSubtractOneMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SUBTRACT_ONE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SubtractOneServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SubtractOneServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBTRACT_ONE:
          serviceImpl.subtractOne((services.SubtractOneRequest) request,
              (io.grpc.stub.StreamObserver<services.SubtractOneResult>) responseObserver);
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

  private static abstract class SubtractOneServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SubtractOneServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return services.Services.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SubtractOneService");
    }
  }

  private static final class SubtractOneServiceFileDescriptorSupplier
      extends SubtractOneServiceBaseDescriptorSupplier {
    SubtractOneServiceFileDescriptorSupplier() {}
  }

  private static final class SubtractOneServiceMethodDescriptorSupplier
      extends SubtractOneServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SubtractOneServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (SubtractOneServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SubtractOneServiceFileDescriptorSupplier())
              .addMethod(getSubtractOneMethod())
              .build();
        }
      }
    }
    return result;
  }
}
