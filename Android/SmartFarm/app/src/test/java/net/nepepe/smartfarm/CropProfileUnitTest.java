package net.nepepe.smartfarm;

import net.nepepe.smartfarm.core.CropProfile;

import org.junit.Test;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;



public class CropProfileUnitTest {

    @Test
    public void CropProfile_IsValid_ReturnsTrue() {

        //Arrange
        CropProfile cropProfile = new CropProfile();

        //Act
        cropProfile.setCropName("Tomatoes");
        cropProfile.setTemperatureRange(23, 30);
        cropProfile.setSoilMoistureContentRange(35,69);
        cropProfile.setLightRange(0,50);

        //Assert
        assertThat(cropProfile.isValid(), is(true));
    }

    @Test
    public void CropProfile_Is_Not_Valid_On_Initialization() {

        //Arrange
        CropProfile cropProfile = new CropProfile();

        //Assert
        assertThat(cropProfile.isValid(), is(false));
    }

    @Test
    public void CropProfile_Expects_Seven_Of_Its_Business_Rules_To_Be_Mate() {

        //Arrange
        CropProfile cropProfile = new CropProfile();

        //Act
        cropProfile.isValid();

        //Assert
        assertThat(cropProfile.errorMessages().size(), is(7));
    }
}
