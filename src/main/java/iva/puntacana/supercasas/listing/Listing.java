package iva.puntacana.supercasas.listing;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Listing {

    //properties
    private int propertyID;
    private String code;
    private int categoryID;
    private String location;
    private String bedroom;
    private String m2;
    private int roomService; //1 or 0 bool type
    private int landID;
    private String type;
    private double price;
    private String description;
    private Date dateCreated; //dd.MM.yyyy HH:mm:ss
    private Date dateUpdated;

    @Autowired
    private List<Photo> photos = new ArrayList<>(); //one listing to many photos

    //constructor
    public Listing() {}

    //getters and setters

    public String getCode() {
        return code;
    }

    public int getPropertyID() {
        return propertyID;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getM2() {
        return m2;
    }

    public void setM2(String m2) {
        this.m2 = m2;
    }

    public int getRoomService() {
        return roomService;
    }

    public void setRoomService(int roomService) {
        this.roomService = roomService;
    }

    public int getLandID() {
        return landID;
    }

    public void setLandID(int landID) {
        this.landID = landID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    //Methods
    public void addPhoto(int propertyID, String file) {
        photos.add(new Photo(propertyID, file));
    }
    @Override
    public String toString() {
        return "Listing [code=" + code + ", categoryID=" + categoryID + ", location=" + location + ", bedroom="
                + bedroom + ", m2=" + m2 + ", roomService=" + roomService + ", landID=" + landID + ", type=" + type
                + ", price=" + price + ", description=" + description + ", dateCreated=" + dateCreated
                + ", dateUpdated=" + dateUpdated + ", photos=" + photos + "]";
    }

    /*
     * convert string to date
     * SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
     * String dateInString = "15-10-2015 10:20:56";
     * Date date = sdf.parse(dateInString);
     * System.out.println(date); //Prints Tue Oct 15 10:20:56 SGT 2015
     */

}

