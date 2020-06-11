package jmeter.grpc.client;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class GrpcClientInterceptor implements ClientInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(jmeter.grpc.client.GrpcClientInterceptor.class);
    protected Map<String, String> headerMap;
    protected long timeoutInMs;

    public GrpcClientInterceptor(Map<String, String> headerMap, long timeoutInMs) {
        this.headerMap = headerMap;
        this.timeoutInMs = timeoutInMs;
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor,
                                                               CallOptions callOptions, Channel channel) {
        return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(
                channel.newCall(methodDescriptor, callOptions.withDeadlineAfter(timeoutInMs, TimeUnit.MILLISECONDS))) {
            @Override
            public void start(ClientCall.Listener<RespT> responseListener, Metadata headers) {

                headerMap.entrySet().stream().forEach(entry -> {
                    headers.put(Metadata.Key.of(entry.getKey(), Metadata.ASCII_STRING_MARSHALLER), entry.getValue());
                    LOGGER.debug("Header key: {}, value: {}", entry.getKey(), entry.getValue());
                });

                super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
                    @Override
                    public void onHeaders(Metadata headers) {
                        super.onHeaders(headers);
                    }
                }, headers);
            }
        };
    }
}
