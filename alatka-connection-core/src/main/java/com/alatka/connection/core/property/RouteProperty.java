package com.alatka.connection.core.property;

import java.util.List;

public class RouteProperty extends Property {

    private InboundProperty inbound;
    private List<ProcessorProperty> processors;
    private OutboundProperty outbound;
}
