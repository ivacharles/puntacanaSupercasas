package iva.puntacana.supercasas.listing;


import java.util.List;

public interface ListingRepo {

    List<Listing> getListings();

    Listing getListing(String propertyID);

    boolean addListing(Listing listing);

    boolean deleteListing(String propertyID);

    boolean updateListing(String propertyID, Listing listing);
}
