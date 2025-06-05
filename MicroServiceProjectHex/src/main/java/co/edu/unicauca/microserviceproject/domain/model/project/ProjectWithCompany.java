package co.edu.unicauca.microserviceproject.domain.model.project;

import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProjectWithCompany {
    private Project project;
    private Company company;
}