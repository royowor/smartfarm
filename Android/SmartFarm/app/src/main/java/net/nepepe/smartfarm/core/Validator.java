package net.nepepe.smartfarm.core;

import java.util.List;

/**
 * Created by roy on 5/27/2017.
 */
public interface Validator {
    public boolean isValid();
    public List<String> errorMessages();
}
