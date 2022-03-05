/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.spapp.model;

import java.io.Serializable;
import java.util.Objects;

public class PostalAddress implements Serializable {
    /**
     * Address.
     */
    private String m_strAddress;

    /**
     * Locality.
     */
    private String m_strLocal;

    /**
     * Zip Code.
     */
    private ZipCode m_oZipCode;

    /**
     * Creates an instance of ZipCode.
     */
    public PostalAddress(String address, String zipCode, String local)
    {
        if ( (address == null) || (zipCode == null) || (local == null) ||
                (address.isEmpty()) || zipCode.isEmpty() ||(local.isEmpty()))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        
        this.m_strAddress = address;
        this.m_strLocal = local;
        this.m_oZipCode = new ZipCode(zipCode);
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 23 * hash + Objects.hash(this.m_strAddress,this.m_oZipCode, this.m_strLocal);
        return hash;
    }

    /**
     * Method Equals in PostalAddress that overrides Equals.
     * @param o postal address object.
     * @return true if the addresses, zip codes, and localities are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        PostalAddress obj = (PostalAddress) o;
        return (Objects.equals(m_strAddress, obj.m_strAddress) &&
                Objects.equals(m_oZipCode, obj.m_oZipCode) &&
                Objects.equals(m_strLocal, obj.m_strLocal));
    }

    /**
     * Returns the textual representation of a Postal Address.
     * @return textual representation of a postal address.
     */
    @Override
    public String toString()
    {
        return String.format("%s, %s, %s", this.m_strAddress, this.m_oZipCode.getZipCode(), this.m_strLocal);
    }
    
}
