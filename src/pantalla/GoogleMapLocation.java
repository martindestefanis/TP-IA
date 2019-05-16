package pantalla;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class GoogleMapLocation {
    List<Result> results;

    String status;

    /**
     * Gets the results.
     * 
     * @return <tt> the results.</tt>
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * Sets the results.
     * 
     * @param results
     *            <tt> the results to set.</tt>
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    /**
     * Gets the status.
     * 
     * @return <tt> the status.</tt>
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status.
     * 
     * @param status
     *            <tt> the status to set.</tt>
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "GoogleMapLocation [results=" + results + ", status=" + status
                + "]";
    }

}

class Result {
    @SerializedName("address_components")
    private List<AddressComponents> addressComponents;

    /**
     * Gets the addressComponents.
     * 
     * @return <tt> the addressComponents.</tt>
     */
    public List<AddressComponents> getAddressComponents() {
        return addressComponents;
    }

    /**
     * Sets the addressComponents.
     * 
     * @param addressComponents
     *            <tt> the addressComponents to set.</tt>
     */
    public void setAddressComponents(List<AddressComponents> addressComponents) {
        this.addressComponents = addressComponents;
    }

    /**
     * Gets the formattedAddress.
     * 
     * @return <tt> the formattedAddress.</tt>
     */
    public String getFormattedAddress() {
        return formattedAddress;
    }

    /**
     * Sets the formattedAddress.
     * 
     * @param formattedAddress
     *            <tt> the formattedAddress to set.</tt>
     */
    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    /**
     * Gets the geometry.
     * 
     * @return <tt> the geometry.</tt>
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * Sets the geometry.
     * 
     * @param geometry
     *            <tt> the geometry to set.</tt>
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * Gets the partialMatch.
     * 
     * @return <tt> the partialMatch.</tt>
     */
    public String getPartialMatch() {
        return partialMatch;
    }

    /**
     * Sets the partialMatch.
     * 
     * @param partialMatch
     *            <tt> the partialMatch to set.</tt>
     */
    public void setPartialMatch(String partialMatch) {
        this.partialMatch = partialMatch;
    }

    /**
     * Gets the types.
     * 
     * @return <tt> the types.</tt>
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * Sets the types.
     * 
     * @param types
     *            <tt> the types to set.</tt>
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

    @SerializedName("formatted_address")
    private String formattedAddress;

    private Geometry geometry;

    @SerializedName("partial_match")
    private String partialMatch; // not sure of the type

    private List<String> types;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Result [addressComponents=" + addressComponents
                + ", formattedAddress=" + formattedAddress + ", geometry="
                + geometry + ", partialMatch=" + partialMatch + ", types="
                + types + "]";
    }
}

class AddressComponents {
    private String long_name;
    private String short_name;
    private List<String> types;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AddressComponents [long_name=" + long_name + ", short_name="
                + short_name + ", types=" + types + "]";
    }

    /**
     * Gets the long_name.
     * 
     * @return <tt> the long_name.</tt>
     */
    public String getLong_name() {
        return long_name;
    }

    /**
     * Sets the long_name.
     * 
     * @param long_name
     *            <tt> the long_name to set.</tt>
     */
    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    /**
     * Gets the short_name.
     * 
     * @return <tt> the short_name.</tt>
     */
    public String getShort_name() {
        return short_name;
    }

    /**
     * Sets the short_name.
     * 
     * @param short_name
     *            <tt> the short_name to set.</tt>
     */
    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    /**
     * Gets the types.
     * 
     * @return <tt> the types.</tt>
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * Sets the types.
     * 
     * @param types
     *            <tt> the types to set.</tt>
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

}

class Geometry {
    LatLng location;

    Viewport viewport;

    Viewport bounds;

    @SerializedName("location_type")
    String locationType;

    /**
     * Gets the location.
     * 
     * @return <tt> the location.</tt>
     */
    public LatLng getLocation() {
        return location;
    }

    /**
     * Sets the location.
     * 
     * @param location
     *            <tt> the location to set.</tt>
     */
    public void setLocation(LatLng location) {
        this.location = location;
    }

    /**
     * Gets the viewport.
     * 
     * @return <tt> the viewport.</tt>
     */
    public Viewport getViewport() {
        return viewport;
    }

    /**
     * Sets the viewport.
     * 
     * @param viewport
     *            <tt> the viewport to set.</tt>
     */
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }

    /**
     * Gets the bounds.
     * 
     * @return <tt> the bounds.</tt>
     */
    public Viewport getBounds() {
        return bounds;
    }

    /**
     * Sets the bounds.
     * 
     * @param bounds
     *            <tt> the bounds to set.</tt>
     */
    public void setBounds(Viewport bounds) {
        this.bounds = bounds;
    }

    /**
     * Gets the locationType.
     * 
     * @return <tt> the locationType.</tt>
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * Sets the locationType.
     * 
     * @param locationType
     *            <tt> the locationType to set.</tt>
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Geometry [location=" + location + ", viewport=" + viewport
                + ", bounds=" + bounds + ", locationType=" + locationType + "]";
    }

}

class LatLng {
    @SerializedName("lat")
    private double latitude;

    @SerializedName("lng")
    private double longitude;

    /**
     * Gets the latitude.
     * 
     * @return <tt> the latitude.</tt>
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude.
     * 
     * @param latitude
     *            <tt> the latitude to set.</tt>
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude.
     * 
     * @return <tt> the longitude.</tt>
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude.
     * 
     * @param longitude
     *            <tt> the longitude to set.</tt>
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "LatLng [latitude=" + latitude + ", longitude=" + longitude
                + "]";
    }

}

class Viewport {
    private LatLng northeast;

    /**
     * Gets the northeast.
     * 
     * @return <tt> the northeast.</tt>
     */
    public LatLng getNortheast() {
        return northeast;
    }

    /**
     * Sets the northeast.
     * 
     * @param northeast
     *            <tt> the northeast to set.</tt>
     */
    public void setNortheast(LatLng northeast) {
        this.northeast = northeast;
    }

    /**
     * Gets the southwest.
     * 
     * @return <tt> the southwest.</tt>
     */
    public LatLng getSouthwest() {
        return southwest;
    }

    /**
     * Sets the southwest.
     * 
     * @param southwest
     *            <tt> the southwest to set.</tt>
     */
    public void setSouthwest(LatLng southwest) {
        this.southwest = southwest;
    }

    private LatLng southwest;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Viewport [northeast=" + northeast + ", southwest=" + southwest
                + "]";
    }

}
