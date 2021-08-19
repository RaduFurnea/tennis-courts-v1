package com.tenniscourts.tenniscourts;

import com.tenniscourts.config.BaseRestController;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@Validated
@RestController
@RequestMapping("/v1/tennis-courts/court")
public class TennisCourtController extends BaseRestController {

    private final TennisCourtService tennisCourtService;

    @ApiOperation("Create tennis court entry")
    @PostMapping
    public ResponseEntity<Void> addTennisCourt(@RequestBody TennisCourtDTO tennisCourtDTO) {
        return ResponseEntity.created(locationByEntity(tennisCourtService.addTennisCourt(tennisCourtDTO).getId())).build();
    }

    @ApiOperation("Get tennis court entry by id")
    @GetMapping("/{id}")
    public ResponseEntity<TennisCourtDTO> findTennisCourtById(@PathVariable("id") Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtById(tennisCourtId));
    }

    @ApiOperation("Get tennis court with schedules by id")
    @GetMapping("/{id}/with-schedules")
    public ResponseEntity<TennisCourtDTO> findTennisCourtWithSchedulesById(Long tennisCourtId) {
        return ResponseEntity.ok(tennisCourtService.findTennisCourtWithSchedulesById(tennisCourtId));
    }
}
