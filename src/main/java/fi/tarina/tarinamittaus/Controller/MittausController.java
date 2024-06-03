package fi.tarina.tarinamittaus.Controller;

import fi.tarina.tarinamittaus.Model.Mittaus;
import fi.tarina.tarinamittaus.Model.MittausDto;
import fi.tarina.tarinamittaus.Service.MittausService;
import fi.tarina.tarinamittaus.Specification.MittausSearchParameters;
import fi.tarina.tarinamittaus.auth.Constants;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/mittaus")
public class MittausController {

    private MittausService mittausService;
    private static Logger logger = LogManager.getLogger(MittausController.class);

    @Autowired
    public MittausController(MittausService mittausService) {
        this.mittausService = mittausService;
    }

    @GetMapping(path = {"", "/"})
    public List<Mittaus> find(
            MittausSearchParameters parameters
                             ) {
        logger.info("Get all mittaus");
        try {
            return mittausService.searchMittausListByKeyword(parameters);
        } catch (Exception e) {
            throw new CustomBadRequestException(e.getMessage());
        }
    }

    @GetMapping(path ={"{id}", "{id}/"})
    public ResponseEntity<Mittaus> getById(@PathVariable("id") Integer id) {
        logger.info("Get mittaus with id {}", id);
        try {
            Mittaus mittaus = mittausService.getMittaus(id);
            return new ResponseEntity<>(mittaus, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomNotFoundException(e.getMessage());
        }
    }


    @PostMapping(path = {"", "/"})
    public ResponseEntity<Mittaus> saveMittaus(
            @Valid @RequestBody Mittaus mittausRequest,
            HttpServletRequest request) {
        try {
            String remoteUser = request.getAttribute(Constants.JWT_USER_NAME_ATTRIBUTE) == null ? "local user" :
                    request.getAttribute(Constants.JWT_USER_NAME_ATTRIBUTE).toString();
            logger.info("Saving new mittaus");
            mittausRequest.setCreated_by_lx(remoteUser);
            Mittaus savedMittaus = mittausService.saveMittaus(mittausRequest);
            logger.info("Mittaus successfully saved with id {}", savedMittaus.getKohde_id());
            return new ResponseEntity<>(savedMittaus, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.warn("Failed to save new mittaus");
            throw new CustomBadRequestException(e.getMessage());
        }
    }

    @DeleteMapping(path = {"{id}", "{id}/"})
    public ResponseEntity<Mittaus> deleteMittaus(@PathVariable("id") Integer id) {
        try {
            logger.info("Removing mittaus with id {}", id);
            this.mittausService.deleteMittaus(id);
            logger.info("Successfully removed mittaus with id {}", id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            logger.warn("Failed to remove mittaus with id {}", id);
            throw new CustomNotFoundException();
        }
    }

    @PutMapping(path = {"", "/"})
    public ResponseEntity<Void> updateMittaus(
            @RequestBody MittausDto dto,
            HttpServletRequest request) {
        try {
            String remoteUser = request.getAttribute(Constants.JWT_USER_NAME_ATTRIBUTE) == null ? "local user" :
                    request.getAttribute(Constants.JWT_USER_NAME_ATTRIBUTE).toString();
            logger.info("Updating mittaus with id {}", dto.getKohde_id());
            dto.setCreated_by_lx(remoteUser);
            mittausService.updateMittaus(dto);
            logger.info("Successfully updated mittaus with id {}", dto.getKohde_id());
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            logger.warn("Failed to update mittaus with id {}", dto.getKohde_id());
            throw new CustomNotFoundException();
        }
    }

    @GetMapping(path = {"/test", "/test/"})
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("This works", HttpStatus.OK);
    }
}
