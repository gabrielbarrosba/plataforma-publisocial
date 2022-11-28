package publi.social.springboot2.controller;

import publi.social.springboot2.domain.Multirao;
import publi.social.springboot2.requests.MultPostRequestBody;
import publi.social.springboot2.requests.MultPutRequestBody;
import publi.social.springboot2.util.DateUtil;
import publi.social.springboot2.service.MultService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("eventos")
@Log4j2
@RequiredArgsConstructor
@CrossOrigin
public class MultController {
    private final DateUtil dateUtil;
    private final MultService multService;

    @GetMapping
    public ResponseEntity<List<Multirao>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(multService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Multirao> findById(@PathVariable long id){
        return ResponseEntity.ok(multService.finByIdThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Multirao> save(@RequestBody MultPostRequestBody multPostRequestBody){
        return new ResponseEntity<>(multService.save(multPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        multService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody MultPutRequestBody multPutRequestBody) {
        multService.replace(multPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
