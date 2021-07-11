package iva.puntacana.supercasas.listing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListingService {

    @Autowired
    private ListingRepo listingRepo;

    public List<Listing> getListings() {
        return listingRepo.getListings();
    }

    public Listing getListing(String propertyID) {
        return listingRepo.getListing(propertyID);
    }

    public boolean addListing(Listing listing) {
        return listingRepo.addListing(listing);
    }

    public boolean deleteListing(String propertyID){
        return listingRepo.deleteListing(propertyID);
    }

    public boolean updateListing(String propertyID, Listing listing){
        return listingRepo.updateListing(propertyID, listing);
    }
}
