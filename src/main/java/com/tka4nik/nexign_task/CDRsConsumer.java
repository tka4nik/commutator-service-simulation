package com.tka4nik.nexign_task;

import java.util.Iterator;

public interface CDRsConsumer {
    void consume(Iterator<CDR> iterator);
}
