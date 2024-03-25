package com.tka4nik.nexign_task;

public interface IUDRService {
    void generate_report();
    void generate_report(String telephone);
    void generate_report(String telephone, Integer month);
}
