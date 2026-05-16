package tdd.train.AgricoreFinanceService.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tdd.train.AgricoreFinanceService.dto.CompteRequestDTO;
import tdd.train.AgricoreFinanceService.dto.CompteResponseDTO;
import tdd.train.AgricoreFinanceService.dto.TransfertRequestDTO;
import tdd.train.AgricoreFinanceService.exception.CompteNotFoundException;
import tdd.train.AgricoreFinanceService.model.Compte;
import tdd.train.AgricoreFinanceService.model.Liquidite;
import tdd.train.AgricoreFinanceService.repository.CompteRepository;
import tdd.train.AgricoreFinanceService.service.CompteService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    
    private CompteRepository compteRepository;

    private CompteService compteService;


    public CompteController(CompteRepository compteRepository, CompteService compteService) {
        this.compteRepository = compteRepository;
        this.compteService = compteService;
    }


    @PostMapping("/virement")
    public void virement(@Validated @RequestBody TransfertRequestDTO dto) {
        compteService.virement(dto.sourceId(), dto.destinationId(), new Liquidite(dto.montant()) );
    }


    @GetMapping
    public List<CompteResponseDTO> getAll() {
        List<Compte> comptes = compteRepository.findAll();

        if (comptes.isEmpty()) {
            throw new CompteNotFoundException("Aucun compte trouvé");
        }

        return compteRepository.findAll()
                .stream()
                .map(CompteResponseDTO::convert)
                .toList();

    }

    @GetMapping("/user/{id}")
    public CompteResponseDTO getByUserId(@PathVariable Integer id) {
        Compte compte = compteRepository.findByUserId(id);
        return CompteResponseDTO.convert(compte);
    }
    

    @GetMapping("/{id}")
    public CompteResponseDTO getById(@PathVariable Integer id) {

        return CompteResponseDTO.convert(compteRepository.findById(id).orElseThrow(() -> new CompteNotFoundException(id)));

    }

    @PostMapping
    public CompteResponseDTO create( @RequestBody CompteRequestDTO compteRequestDTO) {

        Compte compte = new Compte(new Liquidite(compteRequestDTO.balance()));

        compteRepository.save(compte);

        return CompteResponseDTO.convert(compte);
        
    } 

    @PutMapping("/{id}")
    public CompteResponseDTO update(@PathVariable Integer id, @RequestBody CompteRequestDTO compteRequestDTO) {

        Compte compte = compteRepository.findById(id).orElseThrow(() -> new CompteNotFoundException(id));
        
        compte.changerBalance(new Liquidite(compteRequestDTO.balance()));

        compteRepository.save(compte);

        return CompteResponseDTO.convert(compte);
        
    } 

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        
        if(!compteRepository.existsById(id)) {
            throw new CompteNotFoundException(id);
        }
        compteRepository.deleteById(id);
    }






}
