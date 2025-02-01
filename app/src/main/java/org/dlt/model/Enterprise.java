package org.dlt.model;

import java.util.ArrayList;

public class Enterprise {
    private int id;
    private String tinHead;
    private String tinNumber;
    private String nameEn;
    private String nameKh;
    private ArrayList<String> businessActivities =  new ArrayList<>();
    private String houseNumber;
    private String street;
    private String village;
    private String district;
    private String city;

    public int getId() {
        return id;
    }

    public Enterprise setId(int id) {
        this.id = id;
        return this;
    }

    public String getTinHead() {
        return tinHead;
    }

    public Enterprise setTinHead(String tinHead) {
        this.tinHead = tinHead;
        return this;
    }

    public String getTinNumber() {
        return tinNumber;
    }

    public Enterprise setTinNumber(String tinNumber) {
        this.tinNumber = tinNumber;
        return this;
    }

    public String getFullTinNumber() {
        return tinHead +"-"+tinNumber;
    }

    public String getNameEn() {
        return nameEn;
    }

    public Enterprise setNameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }

    public String getNameKh() {
        return nameKh;
    }

    public Enterprise setNameKh(String nameKh) {
        this.nameKh = nameKh;
        return this;
    }

    public String getPrimaryBusinessActivity() {
        return (!businessActivities.isEmpty()) ? businessActivities.get(0) : null;
    }

    public ArrayList<String> getBusinessActivities() {
        return businessActivities;
    }

    public Enterprise setBusinessActivities(ArrayList<String> businessActivities) {
        this.businessActivities = businessActivities;
        return this;
    }

    public Enterprise addBusinessActivity(String businessActivity) {
        businessActivities.add(businessActivity);
        return this;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public Enterprise setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public Enterprise setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getVillage() {
        return village;
    }

    public Enterprise setVillage(String village) {
        this.village = village;
        return this;
    }

    public String getDistrict() {
        return district;
    }

    public Enterprise setDistrict(String district) {
        this.district = district;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Enterprise setCity(String city) {
        this.city = city;
        return this;
    }

    public String getFullAddress() {
        return "#"+this.getHouseNumber() +", Street:"+ this.getStreet() +", Village:"+ this.getVillage() +", District:"+ this.getDistrict() +", City:"+ this.getCity();
    }
}