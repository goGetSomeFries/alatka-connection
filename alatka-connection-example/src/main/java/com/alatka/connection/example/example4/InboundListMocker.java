package com.alatka.connection.example.example4;

import com.alatka.connection.test.support.InboundMocker;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InboundListMocker implements InboundMocker<List<String>> {

    private final AtomicInteger index = new AtomicInteger(0);

    @Override
    public List<String> mockInbound() {
        return IntStream.range(0, 10)
                .mapToObj(i -> "inbound message-" + index.getAndIncrement())
                .collect(Collectors.toList());
    }
}
