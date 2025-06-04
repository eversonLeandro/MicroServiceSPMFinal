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
            Company c1 = new Company(
                    "InnovativeTechSAS",
                    "innovative@gmail.com",
                    Company.Sector.TECNOLOGIA,
                    Company.Estado.HABILITADO,
                    null,
                    new ArrayList<>()
            );
            c1.setNit("900123456");

            Company c2 = new Company(
                    "HealthPlus",
                    "health@gmail.com",
                    Company.Sector.SALUD,
                    Company.Estado.HABILITADO,
                    null,
                    new ArrayList<>()
            );
            c2.setNit("1002");

            Company c3 = new Company(
                    "EduWorld",
                    "edu@gmail.com",
                    Company.Sector.EDUCATION,
                    Company.Estado.HABILITADO,
                    null,
                    new ArrayList<>()
            );
            c3.setNit("1003");

            Contacto contacto1 = new Contacto(
                    "Garcia",
                    "malocon",
                    "31345",
                    "Gerente",
                    c1
            );

            Contacto contacto2 = new Contacto(
                    "Pérez",
                    "Juan",
                    "31342",
                    "Gerente",
                    c2
            );

            Contacto contacto3 = new Contacto(
                    "Mosquera",
                    "marco",
                    "3143",
                    "Gerente",
                    c3
            );

            c1.setContacto(contacto1);
            c2.setContacto(contacto2);
            c3.setContacto(contacto3);

            companyRepository.save(c1);
            companyRepository.save(c2);
            companyRepository.save(c3);
            System.out.println("Se cargaron 3 compañías.");
        } else {
            System.out.println("Ya existen compañías en la base de datos.");
        }
    }
}
