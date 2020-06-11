package jmeter.grpc.client;

import jmeter.grpc.utils.Config;
import org.apache.jmeter.testbeans.BeanInfoSupport;
import org.apache.jmeter.testbeans.gui.TypeEditor;

import java.beans.PropertyDescriptor;

public class GrpcClientSamplerBeanInfo extends BeanInfoSupport {

    private static final String UNDEFINDED = "notUndefined";
    private static final String DEFAULT = "default";

    public GrpcClientSamplerBeanInfo() {
        super(jmeter.grpc.client.GrpcClientSampler.class);

        createPropertyGroup("Server", new String[]{"hostname", "port", "useSsl"});
        createPropertyGroup("Service", new String[]{"packageN", "service"});
        createPropertyGroup("Execute", new String[]{"method", "request", "timeout", "metaData", "requestBuilderCode"});

        PropertyDescriptor props = property("requestBuilderCode", TypeEditor.TextAreaEditor);
        props.setValue(UNDEFINDED, Boolean.TRUE);
        props.setValue("textLanguage", "java");
        props.setValue(DEFAULT, Config.REQUEST_CODE);

        setPropertyDescriptor(props, "hostname", Config.HOST_NAME);
        setPropertyDescriptor(props, "port", Config.PORT);
        setPropertyDescriptor(props, "useSsl", Config.USE_SSL);
        setPropertyDescriptor(props, "packageN", Config.PACKAGE_NAME);
        setPropertyDescriptor(props, "service", Config.SERVICE);
        setPropertyDescriptor(props, "method", Config.METHOD);
        setPropertyDescriptor(props, "request", Config.REQUEST);
        setPropertyDescriptor(props, "timeout", Config.TIME_OUT);
        setPropertyDescriptor(props, "metaData", Config.META_DATA);
    }

    private void setPropertyDescriptor(PropertyDescriptor props, String propName, Object obj) {
        props = property(propName);
        props.setValue(UNDEFINDED, Boolean.TRUE);
        props.setValue(DEFAULT, obj);
    }
}
