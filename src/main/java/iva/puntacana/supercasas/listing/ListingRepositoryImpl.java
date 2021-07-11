package iva.puntacana.supercasas.listing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListingRepositoryImpl implements ListingRepo {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final String sqlPhoto = "SELECT propertyID, file FROM supercasas.photos where propertyID = ?";

    @Override
    public List<Listing> getListings() {
        List<Listing> listings = new ArrayList<>();

        String sqlListing = "SELECT L.propertyID, L.code, L.categoryID, L.location, L.bedroom, L.m2, L.roomService, L.landID, L.type, L.price, L.description, L.dateCreated, L.dateUpdated FROM supercasas.propertylisting L order by propertyID;";
        listings.addAll(jdbcTemplate.query(sqlListing, new ListingMapper()));

        for (Listing l : listings) {
            l.setPhotos(jdbcTemplate.query(sqlPhoto, new PhotoMapper(), l.getPropertyID()));
        }
        return listings;
    }

    @Override
    public Listing getListing(String propertyID) {
        Listing listing = new Listing();
        String sqlLIsting = "SELECT * FROM supercasas.propertylisting WHERE propertyID = ?;";
        listing = jdbcTemplate.queryForObject(sqlLIsting, new ListingMapper(), propertyID);
        listing.setPhotos(jdbcTemplate.query(sqlPhoto, new PhotoMapper(), listing.getPropertyID()));
        return listing;
    }

    @Override
    public boolean addListing(Listing listing) {
        //String addListingSQL = "";

        return true;
    }

    @Override
    public boolean deleteListing(String propertyID) {
        //String deleteLIstingSQL = "DELETE FROM supercasas.propertylisting WHERE propertyID = ?;";
        //jdbcTemplate.queryForObject(deleteLIstingSQL, new ListingMapper(), propertyID);
        return true;
    }

    @Override
    public boolean updateListing(String propertyID, Listing listing) {
        return true;
    }
}
