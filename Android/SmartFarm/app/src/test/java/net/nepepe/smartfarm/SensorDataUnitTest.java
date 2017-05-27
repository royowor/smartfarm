package net.nepepe.smartfarm;

import net.nepepe.smartfarm.core.CropProfile;

import org.junit.Test;

import static java.lang.System.currentTimeMillis;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


import net.nepepe.smartfarm.core.CropProfile;
import net.nepepe.smartfarm.core.SensorData;

import org.junit.Test;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


public class SensorDataUnitTest {

    @Test
    public void CropProfile_IsValid_ReturnsTrue() {

        //Arrange
        SensorData sensorData = new SensorData();

        //Act
        sensorData.setType("soil_moisture_sensor");
        Date date = new java.util.Date();
        sensorData.setOutputValue(34);
        sensorData.setTimestamp(date);

        //Assert
        assertThat(sensorData.isValid(), is(true));
    }

    @Test
    public void SensorData_Is_Not_Valid_On_Initialization() {

        //Arrange
        SensorData sensorData = new SensorData();

        //Assert
        assertThat(sensorData.isValid(), is(false));
    }

    @Test
    public void SensorData_Expects_Two_Of_Its_Business_Rules_To_Be_Mate() {

        //Arrange
        SensorData sensorData = new SensorData();

        //Act
        sensorData.isValid();

        //Assert
        assertThat(sensorData.errorMessages().size(), is(2));
    }
}

