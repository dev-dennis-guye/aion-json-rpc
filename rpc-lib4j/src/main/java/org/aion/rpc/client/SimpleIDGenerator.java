package org.aion.rpc.client;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Generates request in the range of 1 to {@link Short#MAX_VALUE} -1
 * and then resets to 1.
 */
public final class SimpleIDGenerator implements IDGeneratorStrategy {

    private final AtomicInteger atomicInteger = new AtomicInteger(1);
    @Override
    public int generateID() {
        return atomicInteger.getAndAccumulate(1 , (a,b)-> Math.max(((a+b)%Short.MAX_VALUE),  1));
    }
}
