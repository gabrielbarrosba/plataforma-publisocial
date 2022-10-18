package publi.social.springboot2.service;

import publi.social.springboot2.domain.Multirao;
import publi.social.springboot2.repository.MultRepository;
import publi.social.springboot2.requests.MultPostRequestBody;
import publi.social.springboot2.requests.MultPutRequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class MultService {

    private final MultRepository multRepository;

    public List<Multirao> listAll(){
        return multRepository.findAll();
    }

    public Multirao finByIdThrowBadRequestException(long id){
        return multRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Multirão não encontrado"));
    }

    public Multirao save(MultPostRequestBody multPostRequestBody){
        return multRepository.save(Multirao.builder()
            .name(multPostRequestBody.getName())
            .category(multPostRequestBody.getCategory())
            .date(multPostRequestBody.getDate())
            .local(multPostRequestBody.getLocal())
            .description(multPostRequestBody.getDescription())
            .build()
        );
    }

    public void delete(long id) {
        multRepository.delete(finByIdThrowBadRequestException(id));
    }

    public void replace(MultPutRequestBody multPutRequestBody) {
        
        Multirao savedMult = finByIdThrowBadRequestException(multPutRequestBody.getId());
        Multirao multirao = Multirao.builder()
            .id(savedMult.getId())
            .name(savedMult.getName())
            .category(savedMult.getCategory())
            .date(savedMult.getDate())
            .local(savedMult.getLocal())
            .description(savedMult.getDescription())
            .build();
        multRepository.save(multirao);
    }
}
