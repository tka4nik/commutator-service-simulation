package com.tka4nik.nexign_task.impl;

import com.tka4nik.nexign_task.CDR;
import com.tka4nik.nexign_task.CDRsConsumer;

import java.util.Iterator;

class CDRsFileStorage implements CDRsConsumer {
    //в конструкторе путь? (из ServiceBus)
    @Override
    public void consume(Iterator<CDR> iterator) {
        // Сюда перенести из CDRSerbvice
    }
}
