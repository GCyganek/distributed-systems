package services;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.45.1)",
    comments = "Source: services.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DivisibleBy5ServiceGrpc {

  private DivisibleBy5ServiceGrpc() {}

  public static final String SERVICE_NAME = "services.DivisibleBy5Service";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<services.DivisibleBy5Request,
      services.DivisibleBy5Result> getIsDivisibleBy5Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "isDivisibleBy5",
      requestType = services.DivisibleBy5Request.class,
      responseType = services.DivisibleBy5Result.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<services.DivisibleBy5Request,
      services.DivisibleBy5Result> getIsDivisibleBy5Method() {
    io.grpc.MethodDescriptor<services.DivisibleBy5Request, services.DivisibleBy5Result> getIsDivisibleBy5Method;
    if ((getIsDivisibleBy5Method = DivisibleBy5ServiceGrpc.getIsDivisibleBy5Method) == null) {
      synchronized (DivisibleBy5ServiceGrpc.class) {
        if ((getIsDivisibleBy5Method = DivisibleBy5ServiceGrpc.getIsDivisibleBy5Method) == null) {
          DivisibleBy5ServiceGrpc.getIsDivisibleBy5Method = getIsDivisibleBy5Method =
              io.grpc.MethodDescriptor.<services.DivisibleBy5Request, services.DivisibleBy5Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "isDivisibleBy5"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  services.DivisibleBy5Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  services.DivisibleBy5Result.getDefaultInstance()))
              .setSchemaDescriptor(new DivisibleBy5ServiceMethodDescriptorSupplier("isDivisibleBy5"))
              .build();
        }
      }
    }
    return getIsDivisibleBy5Method;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DivisibleBy5ServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DivisibleBy5ServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DivisibleBy5ServiceStub>() {
        @java.lang.Override
        public DivisibleBy5ServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DivisibleBy5ServiceStub(channel, callOptions);
        }
      };
    return DivisibleBy5ServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DivisibleBy5ServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DivisibleBy5ServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DivisibleBy5ServiceBlockingStub>() {
        @java.lang.Override
        public DivisibleBy5ServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DivisibleBy5ServiceBlockingStub(channel, callOptions);
        }
      };
    return DivisibleBy5ServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DivisibleBy5ServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DivisibleBy5ServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DivisibleBy5ServiceFutureStub>() {
        @java.lang.Override
        public DivisibleBy5ServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DivisibleBy5ServiceFutureStub(channel, callOptions);
        }
      };
    return DivisibleBy5ServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class DivisibleBy5ServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void isDivisibleBy5(services.DivisibleBy5Request request,
        io.grpc.stub.StreamObserver<services.DivisibleBy5Result> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getIsDivisibleBy5Method(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getIsDivisibleBy5Method(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                services.DivisibleBy5Request,
                services.DivisibleBy5Result>(
                  this, METHODID_IS_DIVISIBLE_BY5)))
          .build();
    }
  }

  /**
   */
  public static final class DivisibleBy5ServiceStub extends io.grpc.stub.AbstractAsyncStub<DivisibleBy5ServiceStub> {
    private DivisibleBy5ServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DivisibleBy5ServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DivisibleBy5ServiceStub(channel, callOptions);
    }

    /**
     */
    public void isDivisibleBy5(services.DivisibleBy5Request request,
        io.grpc.stub.StreamObserver<services.DivisibleBy5Result> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getIsDivisibleBy5Method(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class DivisibleBy5ServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DivisibleBy5ServiceBlockingStub> {
    private DivisibleBy5ServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DivisibleBy5ServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DivisibleBy5ServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public services.DivisibleBy5Result isDivisibleBy5(services.DivisibleBy5Request request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getIsDivisibleBy5Method(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class DivisibleBy5ServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DivisibleBy5ServiceFutureStub> {
    private DivisibleBy5ServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DivisibleBy5ServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DivisibleBy5ServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<services.DivisibleBy5Result> isDivisibleBy5(
        services.DivisibleBy5Request request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getIsDivisibleBy5Method(), getCallOptions()), request);
    }
  }

  private static final int METHODID_IS_DIVISIBLE_BY5 = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DivisibleBy5ServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DivisibleBy5ServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_IS_DIVISIBLE_BY5:
          serviceImpl.isDivisibleBy5((services.DivisibleBy5Request) request,
              (io.grpc.stub.StreamObserver<services.DivisibleBy5Result>) responseObserver);
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

  private static abstract class DivisibleBy5ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DivisibleBy5ServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return services.Services.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DivisibleBy5Service");
    }
  }

  private static final class DivisibleBy5ServiceFileDescriptorSupplier
      extends DivisibleBy5ServiceBaseDescriptorSupplier {
    DivisibleBy5ServiceFileDescriptorSupplier() {}
  }

  private static final class DivisibleBy5ServiceMethodDescriptorSupplier
      extends DivisibleBy5ServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DivisibleBy5ServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (DivisibleBy5ServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DivisibleBy5ServiceFileDescriptorSupplier())
              .addMethod(getIsDivisibleBy5Method())
              .build();
        }
      }
    }
    return result;
  }
}
