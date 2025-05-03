

package co.edu.unicauca.interfaces;

import co.edu.unicauca.domain.entities.Company;


/**
 *
 * @author Brayan
 */
public interface ICompanyRepository extends IRepository{
    Company getCompanyWithUser(String nit);
}
