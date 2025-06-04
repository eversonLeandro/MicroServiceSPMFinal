package co.edu.unicauca.companymicroservice.Controller;

import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Infra.DTO.CompanyDTO;
import co.edu.unicauca.companymicroservice.Infra.DTO.ProjectRequestCompany;
import co.edu.unicauca.companymicroservice.Service.CompanyService;
import co.edu.unicauca.companymicroservice.Service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/apiCompanies")
public class CompanyController {

    @Autowired
    private  CompanyService companyService;
    @Autowired
    private ProjectService projectService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }


    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.ok(companyService.findAllDTOs());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error. No se pudo encontrar las Compañías.\"}");
        }
    }

    @GetMapping("/{nit}")
    public ResponseEntity<?> getOne(@PathVariable String nit) throws Exception {
        CompanyDTO dto = companyService.findByNit(nit);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> save(@Valid @RequestBody CompanyDTO entity){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(companyService.save(entity));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. no se pudo Guardar la Compañia.\"}");
        }
    }

    @PutMapping("/{idCompany}")
    public ResponseEntity<?> update(@PathVariable String idCompany, @Valid @RequestBody Company entity) {
        try {
            boolean updated =  companyService.update(idCompany, entity);
            if (updated) {
                return ResponseEntity.status(HttpStatus.OK).body("{\"message\":\"Compañía actualizada correctamente.\"}");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Compañía no encontrada para actualizar.\"}");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error al actualizar la compañía.\"}");
        }
    }
    @PostMapping("/saveProject")
    public ResponseEntity<ProjectRequestCompany> save(@RequestBody ProjectRequestCompany entity){
        try {
            ProjectRequestCompany projectRequestCompany = projectService.createProject(entity);

            return ResponseEntity.status(HttpStatus.CREATED).body(projectRequestCompany);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // o un mensaje de error si prefieres
        }
    }

}
