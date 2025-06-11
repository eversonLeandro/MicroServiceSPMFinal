package co.edu.unicauca.microserviceproject.infra.mappers;

import co.edu.unicauca.microserviceproject.domain.model.company.Company;
import co.edu.unicauca.microserviceproject.domain.model.company.VO.Email;
import co.edu.unicauca.microserviceproject.infra.entities.CompanyEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyMapperTest {

    @Test
    void testDomainToEntity() {
        Company company = new Company(
                900123456L,
                "Test Company",
                new Email("test@company.com")
        );

        CompanyEntity entity = CompanyMapper.domainToEntity(company);

        assertNotNull(entity);
        assertEquals(900123456L, entity.getNit());
        assertNotEquals(null, entity.getEmail());
    }

    @Test
    void testEntityToDomain() {
        CompanyEntity entity = new CompanyEntity();
        entity.setNit(900123456L);
        entity.setNombre("Test Company");
        entity.setEmail("test@company.com");

        Company company = CompanyMapper.entityToDomain(entity);

        assertNotNull(company);
        assertEquals(900123456L, company.getNit());
        assertEquals("test@company.com", company.getEmail().getEmail());
    }
}