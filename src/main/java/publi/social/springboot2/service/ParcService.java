package publi.social.springboot2.service;

import publi.social.springboot2.domain.Parceiro;
import publi.social.springboot2.repository.UserRepository;
import publi.social.springboot2.requests.ParcPostRequestBody;
import publi.social.springboot2.requests.ParcPutRequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParcService {

    private final UserRepository userRepository;

    public List<Parceiro> listAll(){
        return userRepository.findAll();
    }

    public Parceiro finByIdThrowBadRequestException(long id){
        return userRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não encontrado"));
    }

    public Parceiro save(ParcPostRequestBody parcPostRequestBody){
        return userRepository.save(Parceiro.builder()
            .parc_name(parcPostRequestBody.getParc_name())
            .build()
        );
    }

    public void delete(long id) {
        userRepository.delete(finByIdThrowBadRequestException(id));
    }

    public void replace(ParcPutRequestBody userPutRequestBody) {
        Parceiro savedParc = finByIdThrowBadRequestException(userPutRequestBody.getId());
        Parceiro parceiro = Parceiro.builder()
            .id(savedParc.getId())
            .parc_name(savedParc.getParc_name())
            .build();
        userRepository.save(parceiro);
    }
}
