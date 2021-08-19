package com.tenniscourts.guests;

import com.tenniscourts.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    private final GuestMapper guestMapper;

    public GuestDTO createGuest(GuestDTO guestDTO) {
        return guestMapper.map(guestRepository.save(guestMapper.map(guestDTO)));
    }

    public GuestDTO updateGuest(Long id, GuestDTO guestDTO) {
        Optional<Guest> optionalGuest = guestRepository.findById(id);
        Guest guest = optionalGuest.orElseThrow(() -> new EntityNotFoundException("Guest for update not found."));
        guest.setName(guestDTO.getName());
        return guestMapper.map(guestRepository.save(guest));
    }

    public GuestDTO getGuestById(Long id) {
        return guestMapper.map(guestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Guest not found.")));
    }

    public void deleteGuest(Long id) {
        guestRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Guest not found."));
        guestRepository.deleteById(id);
    }

    public List<GuestDTO> getGuestsByName(String name) {
        return guestMapper.mapList(guestRepository.findByName(name));
    }

    public List<GuestDTO> getAllGuests() {
        return guestMapper.mapList(guestRepository.findAll());
    }

}
