package org.entities;

///  Used Builder

public class Location {
    private String county;
    private Integer sirutaCode;
    private String locality;
    private String adress;
    private Integer longitude;
    private Integer latitude;

    public static class Builder {
        private String county;
        private Integer sirutaCode;
        private String locality = "";
        private String adress = "";
        private Integer longitude = null;
        private Integer latitude = null;

        public Builder(String county, Integer sirutaCode) {
            this.county = county;
            this.sirutaCode = sirutaCode;
        }

        public Builder setLocality(String locality) {
            this.locality = locality;
            return this;
        }
        public Builder setAdress(String adress) {
            this.adress = adress;
            return this;
        }
        public Builder setLongitude(Integer longitude) {
            this.longitude = longitude;
            return this;
        }
        public Builder setLatitude(Integer latitude) {
            this.latitude = latitude;
            return this;
        }
        public Location build() {
            return new Location(this);
        }
    }

    public Location(Builder builder) {
        this.county = builder.county;
        this.sirutaCode = builder.sirutaCode;
        this.locality = builder.locality;
        this.adress = builder.adress;
        this.longitude = builder.longitude;
        this.latitude = builder.latitude;
    }
}
