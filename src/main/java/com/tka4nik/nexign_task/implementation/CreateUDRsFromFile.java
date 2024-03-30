package com.tka4nik.nexign_task.implementation;

import com.tka4nik.nexign_task.model.UDRsSupplier;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

class CreateUDRsFromFile implements UDRsSupplier {
    private final String filepath;

    CreateUDRsFromFile(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public Map<String, Map<String, String>> supply() {
        Map<String, Map<String, String>> udrMap = new HashMap<>();

        File directory = new File(this.filepath);
        File[] cdrs = directory.listFiles();

        assert cdrs != null;
        for (File cdr: cdrs) {
            String filepath = cdr.getPath();
            parseFile(filepath, udrMap);
        }

        return udrMap;
    }

    private void parseFile(String filepath, Map<String, Map<String, String>> udrMap) {
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String msisdn = parts[1].trim();
                String callType = parts[0].trim();
                String startTime = parts[2].trim();
                String endTime = parts[3].trim();
                long totalTime = Integer.parseInt(endTime) - Integer.parseInt(startTime);

                Map<String, String> msisdnData = udrMap.getOrDefault(msisdn, new HashMap<>());
                if (callType.equals("01")) {
                    String currentTime = msisdnData.getOrDefault("incomingCall", "00:00:00");
                    long currentTimeUnix = getUnixTime(currentTime) + totalTime;
                    msisdnData.put("incomingCall", getDateTime(currentTimeUnix));
                } else if (callType.equals("02")) {
                    String currentTime = msisdnData.getOrDefault("outgoingCall", "00:00:00");
                    long currentTimeUnix = getUnixTime(currentTime);
                    currentTimeUnix += totalTime;
                    msisdnData.put("outgoingCall", getDateTime(currentTimeUnix));
                }
                udrMap.put(msisdn, msisdnData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getDateTime(long unixTimestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(unixTimestamp),
                ZoneId.of("UTC")
        );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dateTime.format(formatter);
    }

    private long getUnixTime(String dateTime) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = dateFormat.parse(dateTime);
            return date.getTime()/1000;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
