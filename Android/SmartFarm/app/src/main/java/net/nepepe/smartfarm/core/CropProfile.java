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

import org.mockito.internal.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Business Object Class that represents a Crop Profile.
 */
public class CropProfile implements Validator {

    private String cropName;
    private float minTemperature;
    private float maxTemperature;
    private float minSoilMoistureContent;
    private float maxSoilMoistureContent;
    private float minLight;
    private float maxLight;
    private List<String> errorMessages;

    public CropProfile(){
        cropName = new String();
        minTemperature = 0;
        maxTemperature = 0;
        minSoilMoistureContent =0;
        maxSoilMoistureContent =0;
        minLight = 0;
        maxLight = 0;
        errorMessages = new ArrayList<String>() ;
    }

    /**
     * Class instance validation checker
     *
     * Crop Profile is valid if and only if,
     * Crop Name is not null or Empty.
     * and if deference between max and min values are greater than zero
     * and if min Value is lower than max values
     */
    @Override
    public boolean isValid(){
        boolean result = true;
        errorMessages.clear();

        if(cropName==null || cropName.isEmpty()) {
            result = false;
            errorMessages.add(Errors.INVALID_CROP_NAME);
        }

        //check for valid range differences
        if(maxTemperature - minTemperature == 0){
            result = false;
            errorMessages.add(Errors.INVALID_TEMP_RANGE);
        }

        if(maxSoilMoistureContent - minSoilMoistureContent == 0){
            result = false;
            errorMessages.add(Errors.INVALID_SOIL_MOISTURE_CONTENT_RANGE);
        }

        if(maxLight - minLight == 0){
            result = false;
            errorMessages.add(Errors.INVALID_LIGHT_RANGE);
        }

        //check for inverse values
        if(maxTemperature <= minTemperature){
            result = false;
            errorMessages.add(Errors.INVALID_TEMP_VALUE);
        }

        if(maxSoilMoistureContent <= minSoilMoistureContent ){
            result = false;
            errorMessages.add(Errors.INVALID_SOIL_MOISTURE_VALUE);
        }

        if(maxLight <= minLight ){
            result = false;
            errorMessages.add(Errors.INVALID_LIGHT_VALUE);
        }

        return result;
    }

    @Override
    public List<String> errorMessages() {
        return errorMessages;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public void setTemperatureRange(float min, float max) {
        minTemperature = min;
        maxTemperature = max;
    }

    public void setLightRange(float min, float max) {
        minLight = min;
        maxLight = max;
    }

    public void setSoilMoistureContentRange(float min, float max) {
        minSoilMoistureContent = min;
        maxSoilMoistureContent = max;
    }
}
