package jmeter.grpc.utils;

public class Config {
    private static final jmeter.grpc.utils.PropUtils props = new jmeter.grpc.utils.PropUtils();
    public static final String HOST_NAME = props.getOrDefault("sampler.host", props.getValue("sampler.host"));
    public static final int PORT = Integer.parseInt(props.getOrDefault("sampler.port", props.getValue("sampler.port")));
    public static final boolean USE_SSL = Boolean.getBoolean(props.getOrDefault("sampler.use.ssl", props.getValue("sampler.use.ssl")));
    public static final String PACKAGE_NAME = props.getOrDefault("sampler.package.name", props.getValue("sampler.package.name"));
    public static final String SERVICE = props.getOrDefault("sampler.service.name", props.getValue("sampler.service.name"));
    public static final String METHOD = props.getOrDefault("sampler.method.name", props.getValue("sampler.method.name"));
    public static final String REQUEST = props.getOrDefault("sampler.request", props.getValue("sampler.request"));
    public static final String META_DATA = props.getOrDefault("sampler.metadata", props.getValue("sampler.metadata"));
    public static final long TIME_OUT = Long.parseLong(props.getOrDefault("sampler.timeout", props.getValue("sampler.timeout")));
    public static final String REQUEST_CODE = props.getOrDefault("sampler.request.code", props.getValue("sampler.request.code"));

    private Config() {
    }
}
