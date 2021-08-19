package com.tenniscourts.guests;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/tennis-courts/guest")
public class GuestController extends BaseRestController {


    private final GuestService guestService;

    @PostMapping
    @ApiOperation("Create a new guest user")
    public ResponseEntity<GuestDTO> createGuest(@RequestBody @ApiParam("Guest request body") GuestDTO guestDTO) {
        return ResponseEntity.status(201).body(guestService.createGuest(guestDTO));
    }

    @PutMapping("/{id}")
    @ApiOperation("Update guest user by id")
    public ResponseEntity<GuestDTO> updateGuest(@PathVariable("id") @ApiParam("Guest ID") Long id,
                                                @RequestBody @ApiParam("Guest DTO to be saved") GuestDTO guestDTO) {
        return ResponseEntity.ok(guestService.updateGuest(id, guestDTO));
    }

    @GetMapping("/{id}")
    @ApiOperation("Get guest user by id")
    public ResponseEntity<GuestDTO> getGuestById(@PathVariable("id") @ApiParam("Guest ID") Long id) {
        return ResponseEntity.ok(guestService.getGuestById(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete guest user by id")
    public ResponseEntity<Void> deleteGuestById(@PathVariable("id") @ApiParam("Guest ID") Long id) {
        guestService.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/by-name/{name}")
    @ApiOperation("Search guest users by name (exact match)")
    public ResponseEntity<List<GuestDTO>> getGuestsByName(@PathVariable("name") @ApiParam("Guest name to search for") String name) {
        return ResponseEntity.ok(guestService.getGuestsByName(name));
    }

    @GetMapping("/all")
    @ApiOperation("Get all guest users")
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        return ResponseEntity.ok(guestService.getAllGuests());
    }

}
