package lapr.project.spapp.controller;

import lapr.project.spapp.model.Company;

import java.io.File;

public class ImportExecutionOrdersController {

    /**
     * Company.
     */
    private Company m_oCompany;

    /**
     * Creates an instance of ImportExecutionOrdersController.
     * @param company Company object.
     */
    public ImportExecutionOrdersController(Company company) {
        this.m_oCompany = company;
    }

    /**
     * Imports the Execution Orders from a file.
     * @param fileSP file containing the execution orders.
     * @return true if successful, false if not.
     */
    public boolean newImport(File fileSP) {
        return this.m_oCompany.getExecutionOrdersRegistry().importExecutionOrders(fileSP);
    }
}
