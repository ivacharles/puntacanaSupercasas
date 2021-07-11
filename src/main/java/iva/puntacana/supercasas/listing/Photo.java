package iva.puntacana.supercasas.listing;

public class Photo {
    //properties
    private int propertyID;
    private String file;


    //constructor
    public Photo() {}





    public Photo(int propertyID, String file) {
        super();
        this.propertyID = propertyID;
        this.file = file;
    }





    //getter and setters
    public int getPropertyID() {
        return propertyID;
    }


    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }


    public String getFile() {
        return file;
    }


    public void setFile(String file) {
        this.file = file;
    }


    //Methods

    @Override
    public String toString() {
        return "Photo [propertyID=" + propertyID + ", file=" + file + "]";
    }


}

