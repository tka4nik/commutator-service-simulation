package com.tka4nik.nexign_task.implementation;

import com.tka4nik.nexign_task.model.AllCDRs;
import com.tka4nik.nexign_task.model.CDR;

import java.util.Iterator;

class FileCallsDB implements AllCDRs {

    //TODO: Подумать?? в CDRsSupplier supply принимает месяц, что как будто бы не слишком обще
    @Override
    public Iterator<CDR> supply(int month) {
        return null;
    }
}
