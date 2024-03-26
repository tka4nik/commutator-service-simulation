package com.tka4nik.nexign_task.model;

import java.util.Objects;

public class CDR {
    private int callType;
    private String msisdn;
    private long startTime;
    private long endTime;

    public CDR(int callType, String msisdn, long startTime, long endTime) {
        this.callType = callType;
        this.msisdn = msisdn;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getCallType() {
        return callType;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }


    @Override
    public int hashCode() {
        return Objects.hash(callType, msisdn, startTime, endTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CDR cdr = (CDR) o;
        return callType == cdr.getCallType() && startTime == cdr.getStartTime() && endTime == cdr.getEndTime() && Objects.equals(msisdn, cdr.getMsisdn());
    }

    @Override
    public String toString() {
        return String.format("%02d,%s,%d,%d\n", callType, msisdn, startTime, endTime);
    }
}
