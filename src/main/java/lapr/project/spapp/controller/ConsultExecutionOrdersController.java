package lapr.project.spapp.controller;

import lapr.project.spapp.model.Company;
import lapr.project.spapp.model.ExecutionOrder;

import java.util.List;

public class ConsultExecutionOrdersController {

    /**
     * Company.
     */
    private Company m_oCompany;

    /**
     * Creates an instance of ConsultExecutionOrdersController.
     * @param company Company object.
     */
    public ConsultExecutionOrdersController(Company company) {
        this.m_oCompany = company;
    }

    /**
     * Returns the Executions Orders after today.
     * @return execution orders list.
     */
    public List<ExecutionOrder> getExecutionOrdersAfterToday() {
        return this.m_oCompany.getExecutionOrdersRegistry().getlstExecutionOrdersAfterToday();
    }

}
