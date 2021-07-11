package iva.puntacana.supercasas.listing;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoMapper implements RowMapper<Photo> {
    @Override
    public Photo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Photo photo = new Photo();
        photo.setPropertyID(rs.getInt("propertyID"));
        photo.setFile(rs.getString("file"));
        return photo;
    }
}
