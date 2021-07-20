package iva.puntacana.supercasas.listing;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("listing")
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @ApiOperation("this method get all the listings.")
    @GetMapping("/")
    public List<Listing> getListings(){
        System.out.println("getListings is called");
        return listingService.getListings();
    }
    @ApiOperation("this method get a listing base on the listing id.")
    @GetMapping("/{propertyID}")
    //@PreAuthorize("hasRole('ADMIN')")//	@PreAuthorize("") can be hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')
    public Listing getListingByPropertyID(@PathVariable("propertyID") String propertyID) {
        System.out.println("propertyID is "+propertyID +" this means that getLIsting is called");
        return listingService.getListing(propertyID);
    }
    @ApiOperation("this method add a listing.")
    @PostMapping(path="/", consumes= {"application/json"})
    @PreAuthorize("hasRole('ADMIN')")
    public Listing addListing(@RequestBody Listing listing) {
        System.out.print(listing);
        if(!listingService.addListing(listing))
            return new Listing();
        return listing;
    }
    @ApiOperation("this method update a listing. It takes 2 parameters, listing id and the updated version listing")
    @PutMapping(path=("/{propertyID}"), consumes= {"application/json"})
    @PreAuthorize("hasRole('ADMIN')")
    public Listing updateListing(@PathVariable("propertyID") String propertyID, @RequestBody Listing listing) {
        System.out.print(listing);
        if(!listingService.updateListing(propertyID, listing))
            return new Listing();
        return listing;
    }

    @ApiOperation("this method delete add a listing base on the listing id.")
    @DeleteMapping("/{propertyID}")
    @PreAuthorize("hasRole('ADMIN')")//	@PreAuthorize("") can be hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')
    public String deleteListing(@PathVariable("propertyID") String propertyID) {
        System.out.println("this means that deleteListing is called");
        if(listingService.deleteListing(propertyID))
            return "Listing was successfully deleted";
        return "Something is wrong";
    }
}
