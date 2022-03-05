package lapr.project.spapp.model;

import java.io.Serializable;
import java.util.Objects;

public class ZipCode implements Serializable {

    /**
     * Zip code.
     */
    private String zipCode;

    /**
     * Initializes ZipCode with the zip code received by parameter.
     *
     * @param zipCode zip code.
     */
    public ZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Returns the zip code.
     *
     * @return zip code string.
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Method Equals in ZipCode that overrides Equals.
     *
     * @param obj zip code object.
     * @return true if the zip code is the same, false if not.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ZipCode o = (ZipCode) obj;
        return (Objects.equals(zipCode, o.zipCode));
    }

}