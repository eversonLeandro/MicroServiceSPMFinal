package co.edu.unicauca.companymicroservice.Repositories;

import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Repositories.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import co.edu.unicauca.companymicroservice.Entities.Company;
import co.edu.unicauca.companymicroservice.Entities.Contacto;
import co.edu.unicauca.companymicroservice.Entities.Project;
import co.edu.unicauca.companymicroservice.Repositories.CompanyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CompanyDataLoader implements CommandLineRunner {

    private final CompanyRepository companyRepository;

    public CompanyDataLoader(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (companyRepository.count() == 0) {
            // Creamos listas vacías (puedes agregar Contacto y Project si lo necesitas)
            List<Contacto> contactos1 = new ArrayList<>();
            List<Project> proyectos1 = new ArrayList<>();

            List<Contacto> contactos2 = new ArrayList<>();
            List<Project> proyectos2 = new ArrayList<>();

            List<Contacto> contactos3 = new ArrayList<>();
            List<Project> proyectos3 = new ArrayList<>();

            Company c1 = new Company("1001", "TechCorp", Company.Estado.HABILITADO, "TECNOLOGIA", contactos1, proyectos1);
            Company c2 = new Company("1002", "HealthPlus", Company.Estado.HABILITADO, "SALUD", contactos2, proyectos2);
            Company c3 = new Company("1003", "EduWorld", Company.Estado.HABILITADO, "EDUCATION", contactos3, proyectos3);

            Company company = new Company("900123456","Innovative Tech S.A.S.",Company.Estado.HABILITADO,"TECNOLOGIA",contactos3,proyectos1);

            companyRepository.save(company);
            companyRepository.save(c1);
            companyRepository.save(c2);
            companyRepository.save(c3);

            System.out.println("Se cargaron 3 compañías.");
        } else {
            System.out.println("Ya existen compañías en la base de datos.");
        }
    }
}
