package co.edu.unicauca.microserviceproject.adapter.out;

import co.edu.unicauca.microserviceproject.adapter.out.repository.CompanyJpaRepository;
import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.company.VO.Email;
import co.edu.unicauca.microserviceproject.infra.entities.CompanyEntity;
import co.edu.unicauca.microserviceproject.infra.mappers.CompanyMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CompanyRepositoryAdapterTest {

    @Mock
    private CompanyJpaRepository companyJpaRepository;

    @InjectMocks
    private CompanyRepositoryAdapter companyRepositoryAdapter;

    @Test
    void testFindCompanyByNit() {
        CompanyEntity entity = new CompanyEntity();
        entity.setNit(900123456L);
        entity.setNombre("Test Company");
        entity.setEmail("test@example.com");

        Company company = new Company(900123456L, "Test Company", new Email("test@example.com"));

        try (MockedStatic<CompanyMapper> mocked = Mockito.mockStatic(CompanyMapper.class)) {
            when(companyJpaRepository.getByNit(900123456L)).thenReturn(entity);
            mocked.when(() -> CompanyMapper.entityToDomain(entity)).thenReturn(company);

            Optional<Company> result = companyRepositoryAdapter.findCompanyByNit(900123456L);

            assertTrue(result.isPresent());
            assertEquals(900123456L, result.get().getNit());
            mocked.verify(() -> CompanyMapper.entityToDomain(entity));
        }
    }

    @Test
    void testSaveCompany() {
        Company company = new Company(900123456L, "Test Company", new Email("test@example.com"));
        CompanyEntity entity = new CompanyEntity();
        entity.setNit(900123456L);
        entity.setNombre("Test Company");
        entity.setEmail("test@example.com");

        try (MockedStatic<CompanyMapper> mocked = Mockito.mockStatic(CompanyMapper.class)) {
            mocked.when(() -> CompanyMapper.domainToEntity(company)).thenReturn(entity);
            when(companyJpaRepository.save(entity)).thenReturn(entity);
            mocked.when(() -> CompanyMapper.entityToDomain(entity)).thenReturn(company);

            Company savedCompany = companyRepositoryAdapter.saveCompany(company);

            assertNotNull(savedCompany);
            assertEquals(900123456L, savedCompany.getNit());
            mocked.verify(() -> CompanyMapper.domainToEntity(company));
            mocked.verify(() -> CompanyMapper.entityToDomain(entity));
        }
    }

    @Test
    void testFindAllCompanies() {
        CompanyEntity entity = new CompanyEntity();
        entity.setNit(900123456L);
        entity.setNombre("Test Company");
        entity.setEmail("test@example.com");

        Company company = new Company(900123456L, "Test Company", new Email("test@example.com"));

        try (MockedStatic<CompanyMapper> mocked = Mockito.mockStatic(CompanyMapper.class)) {
            when(companyJpaRepository.findAll()).thenReturn(List.of(entity));
            mocked.when(() -> CompanyMapper.entityToDomain(entity)).thenReturn(company);

            List<Company> result = companyRepositoryAdapter.findAllCompanies();

            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            mocked.verify(() -> CompanyMapper.entityToDomain(entity));
        }
    }
}