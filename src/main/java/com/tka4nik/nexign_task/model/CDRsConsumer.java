package com.tka4nik.nexign_task.model;

import java.util.Iterator;

public interface CDRsConsumer {
    void consumeCdrs(Iterator<CDR> iterator);
}
