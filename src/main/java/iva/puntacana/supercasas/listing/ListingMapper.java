package iva.puntacana.supercasas.listing;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ListingMapper implements RowMapper<Listing> {

    @Override
    public Listing mapRow(ResultSet rs, int rowNum) throws SQLException {
        Listing listing = new Listing();
        int propID = rs.getInt("propertyID");
        listing.setPropertyID(propID);
        listing.setCode(rs.getString("code"));
        listing.setCategoryID(rs.getInt("categoryID"));
        listing.setLocation(rs.getString("location"));
        listing.setBedroom(rs.getString("bedroom"));
        listing.setM2(rs.getString("m2"));
        listing.setRoomService(rs.getInt("roomService"));
        listing.setLandID(rs.getInt("landID"));
        listing.setType(rs.getString("type"));
        listing.setPrice(rs.getDouble("price"));
        listing.setDescription(rs.getString("description"));
        listing.setDateCreated(rs.getDate("dateCreated"));
        listing.setDateUpdated(rs.getDate("dateUpdated"));
        return listing;
    }

}