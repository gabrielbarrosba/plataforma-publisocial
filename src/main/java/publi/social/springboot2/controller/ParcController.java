package publi.social.springboot2.controller;

import publi.social.springboot2.domain.Parceiro;
import publi.social.springboot2.requests.ParcPostRequestBody;
import publi.social.springboot2.requests.ParcPutRequestBody;
import publi.social.springboot2.service.ParcService;
import publi.social.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("parceiros")
@Log4j2
@RequiredArgsConstructor
public class ParcController {
    private final DateUtil dateUtil;
    private final ParcService parcService;

    @GetMapping
    public ResponseEntity<List<Parceiro>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(parcService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Parceiro> findById(@PathVariable long id){
        return ResponseEntity.ok(parcService.finByIdThrowBadRequestException(id));
    }

    @PostMapping
    public ResponseEntity<Parceiro> save(@RequestBody ParcPostRequestBody parcPostRequestBody){
        return new ResponseEntity<>(parcService.save(parcPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        parcService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody ParcPutRequestBody parcPutRequestBody) {
        parcService.replace(parcPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
