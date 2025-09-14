package com.apartment.management.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * A ChallanGenerator
 */
public class ChallanGenerator {

    private ChallanGenerator() {
    }

    /**
     * Generate Challan Number
     *
     * @return Challan NUmber
     */
    public static String generateChallanNumber() {
        // Get the current timestamp in the format YYYYMMDDHHMMSS
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = sdf.format(new Date());
        // Generate a random UUID and take the first 6 characters for a unique identifier
        String uuidPart = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        // Combine timestamp with UUID and return a formatted Challan Number
        return "CH-" + timestamp + "-" + uuidPart;
    }
}
