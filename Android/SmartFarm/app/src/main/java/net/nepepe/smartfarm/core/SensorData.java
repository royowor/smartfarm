/**
 * Copyright (C) 2017 Smart Farm Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.nepepe.smartfarm.core;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Business Object Class that represents a Crop Profile.
 */
public class SensorData implements Validator {

    private String sensorType;
    private float outputValue;
    private String timestamp;
    private List<String> errorMessages;

    public SensorData(){
        sensorType = new String();
        outputValue = 0;
        timestamp = null;
        errorMessages = new ArrayList<String>();
    }

    /**
     * Class instance validation checker
     *
     * Sensor Data is valid if and only if,
     * sensorType is not null or Empty.
     * and timestamp is not null
     */
    @Override
    public boolean isValid(){
        boolean result = true;
        errorMessages.clear();

        if(sensorType.isEmpty()) {
            result = false;
            errorMessages.add(Errors.INVALID_SENSOR_TYPE);
        }

        if(timestamp == null){
            result = false;
            errorMessages.add(Errors.INVALID_TIMESTAMP);
        }

        return result;
    }

    @Override
    public List<String> errorMessages() {
        return errorMessages;
    }

    public void setType(String type) {
        sensorType = type;
    }

    public void setOutputValue(float outputValue) {
        this.outputValue = outputValue;
    }

    public  void setTimestamp(Date date){
        this.timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
    }
}
