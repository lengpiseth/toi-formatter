package org.dlt.model;

import jakarta.persistence.*;

@Entity
@Table(name="enterprises")
public class Enterprise {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=true)
    private String tinHead;
    @Column(unique=true, nullable=false)
    private String tinNumber;
    @Column(nullable=false)
    private String nameEn;
    @Column(nullable=true)
    private String nameKh;
//    private ArrayList<String> businessActivities =  new ArrayList<>();
    @Column(nullable=true)
    private String fullAddress;
//    private String houseNumber;
//    private String street;
//    private String village;
//    private String district;
//    private String city;

    public Enterprise() {}

    public Enterprise(String tinHead, String tinNumber, String nameEn, String nameKh) {
        this.setTinHead(tinHead);
        this.setTinNumber(tinNumber);
        this.setNameEn(nameEn);
        this.setNameKh(nameKh);
    }

    @Override
    public String toString() {
        return this.nameEn;
    }

    public Long getId() {
        return id;
    }

    public Enterprise setId(Long id) {
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
        return "N/A";
    }
//
//    public String getPrimaryBusinessActivity() {
//        return (!businessActivities.isEmpty()) ? businessActivities.get(0) : null;
//    }
//
//    public ArrayList<String> getBusinessActivities() {
//        return businessActivities;
//    }
//
//    public Enterprise setBusinessActivities(ArrayList<String> businessActivities) {
//        this.businessActivities = businessActivities;
//        return this;
//    }
//
//    public Enterprise addBusinessActivity(String businessActivity) {
//        businessActivities.add(businessActivity);
//        return this;
//    }
//
//    public String getHouseNumber() {
//        return houseNumber;
//    }
//
//    public Enterprise setHouseNumber(String houseNumber) {
//        this.houseNumber = houseNumber;
//        return this;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public Enterprise setStreet(String street) {
//        this.street = street;
//        return this;
//    }
//
//    public String getVillage() {
//        return village;
//    }
//
//    public Enterprise setVillage(String village) {
//        this.village = village;
//        return this;
//    }
//
//    public String getDistrict() {
//        return district;
//    }
//
//    public Enterprise setDistrict(String district) {
//        this.district = district;
//        return this;
//    }
//
//    public String getCity() {
//        return city;
//    }
//
//    public Enterprise setCity(String city) {
//        this.city = city;
//        return this;
//    }

//    public String getFullAddress1() {
//        return "#"+this.getHouseNumber() +", Street:"+ this.getStreet() +", Village:"+ this.getVillage() +", District:"+ this.getDistrict() +", City:"+ this.getCity();
//    }

    public Enterprise setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
        return this;
    }

    public String getFullAddress() {
        return fullAddress;
    }
}