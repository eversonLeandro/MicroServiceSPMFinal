package co.edu.unicauca.microserviceproject.adapter.in;


import co.edu.unicauca.microserviceproject.aplication.port.in.IGetProjectsByCompany;
import co.edu.unicauca.microserviceproject.aplication.port.out.IProjectRepositoryPort;
import co.edu.unicauca.microserviceproject.domain.model.project.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GetProjectsByCompanyUseCase implements IGetProjectsByCompany {


    private final IProjectRepositoryPort iProjectRepositoryPort;

    @Override
    public List<Project> GetProjectsByCompany(Long nit) {
        return iProjectRepositoryPort.GetProjectsByCompany(nit);
    }

}
