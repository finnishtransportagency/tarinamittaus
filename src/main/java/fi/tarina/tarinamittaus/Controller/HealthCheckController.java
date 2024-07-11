package fi.tarina.tarinamittaus.Controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/rest/health")
public class HealthCheckController {
    private static Logger logger = LogManager.getLogger(HealthCheckController.class);

    @GetMapping(path = {"", "/"})
    public ResponseEntity<String> healthCheck() {
        logger.debug("Health check called");
        return new ResponseEntity<>("healthy", HttpStatus.OK);
    }
}
