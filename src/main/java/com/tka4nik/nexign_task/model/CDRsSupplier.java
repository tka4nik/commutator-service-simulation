package com.tka4nik.nexign_task.model;

import java.util.Iterator;

public interface CDRsSupplier {
    Iterator<CDR> supply(int month);
}
