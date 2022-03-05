package lapr.project.spapp.controller;

import lapr.project.spapp.model.ExecutionOrder;
import lapr.project.spapp.model.Company;

import java.util.List;

public class AnalyzeClientTimelineController {

    /**
     * Company.
     */
    private Company m_oCompany;

    /**
     * Creates an instance of AnalyzeClientTimelineController.
     *
     * @param company
     */
    public AnalyzeClientTimelineController(Company company) {
        this.m_oCompany = company;
    }

    /**
     * Returns the existent clients.
     *
     * @return clients names list.
     */
    public List<String> getClients() {
        return this.m_oCompany.getExecutionOrdersRegistry().getClients();
    }

    /**
     * Returns a client's timeline.
     *
     * @param clientName client name.
     * @return client's execution order list.
     */
    public List<ExecutionOrder> newClientTimeline(String clientName) {
        List<ExecutionOrder> lsteo = this.m_oCompany.getExecutionOrdersRegistry().getlstExecutionOrdersBeforeToday();
        return this.m_oCompany.getExecutionOrdersRegistry().getExecutionOrdersByClient(clientName, lsteo);
    }

    public List<ExecutionOrder> getExecutionOrdersBeforeToday() {
        return this.m_oCompany.getExecutionOrdersRegistry().getlstExecutionOrdersBeforeToday();
    }

}
