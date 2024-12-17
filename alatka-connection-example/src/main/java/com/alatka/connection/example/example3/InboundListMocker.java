package com.alatka.connection.example.example3;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InboundListMocker implements com.alatka.connection.test.support.InboundMocker<List<String>> {

    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public List<String> mockInbound() {
        return IntStream.range(0, 5)
                .mapToObj(i -> "inbound message" + index.getAndIncrement())
                .collect(Collectors.toList());
    }
}
