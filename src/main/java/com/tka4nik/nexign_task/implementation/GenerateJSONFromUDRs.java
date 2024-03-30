package com.tka4nik.nexign_task.implementation;

import com.tka4nik.nexign_task.model.UDRsConsumer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class GenerateJSONFromUDRs implements UDRsConsumer {

    private final Map<String, Map<String,String>> udrs;
    private final String filepath;

    public GenerateJSONFromUDRs(String filepath, Map<String, Map<String, String>> udrs) {
        this.udrs = udrs;
        this.filepath = filepath;
    }

    @Override
    public void generateReport() {

        for (Map.Entry<String, Map<String, String>> entry : udrs.entrySet()) {
            StringBuilder jsonBuilder = new StringBuilder("{\n");
            jsonBuilder.append("    \"msisdn\": \"").append(entry.getKey()).append("\",\n");
            jsonBuilder.append("    \"incomingCall\": {\n");
            jsonBuilder.append("        \"totalTime\": \"").append(entry.getValue().getOrDefault("incomingCall", "00:00:00")).append("\"\n");
            jsonBuilder.append("    },\n");
            jsonBuilder.append("    \"outgoingCall\": {\n");
            jsonBuilder.append("        \"totalTime\": \"").append(entry.getValue().getOrDefault("outgoingCall", "00:00:00")).append("\"\n");
            jsonBuilder.append("    }\n");
            jsonBuilder.append("}\n");
            System.out.println(jsonBuilder);
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath + entry.getKey() + ".json"))) {
                writer.write(String.valueOf(jsonBuilder));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public void generateReport(String msisdn) {

    }

    @Override
    public void generateReport(String msisdn, int month) {

    }
}
