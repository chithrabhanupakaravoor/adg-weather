package com.adg.location;


public class MyLocation {
	
    String address;
    String urlToParse;
	
    public MyLocation() {}
	
    public MyLocation(String address, String url) {
        this.address = address;
        this.urlToParse = url;
    }
	
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrlToParse() {
        return urlToParse;
    }

    public void setUrlToParse(String urlToParse) {
        this.urlToParse = urlToParse;
    }

}
