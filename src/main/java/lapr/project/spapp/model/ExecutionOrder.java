package lapr.project.spapp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ExecutionOrder implements Serializable {

    /**
     * Execution Order Number.
     */
    private int orderNumber;

    /**
     * Client Name.
     */
    private String clientName;

    /**
     * Distance to the Client.
     */
    private double distanceToClient;

    /**
     * Execution Order Category.
     */
    private String category;

    /**
     * Execution Order Service Type.
     */
    private String serviceType;

    /**
     * Execution Order Start Date.
     */
    private Date executionDate;

    /**
     * Client's Home Postal Address.
     */
    private PostalAddress oAddress;

    /**
     * Creates an instance of ExecutionOrder.
     *
     * @param orderNumber      execution order number.
     * @param clientName       client name.
     * @param distanceToClient distance from the service provider to the client's home.
     * @param category         service category.
     * @param serviceType      type of service.
     * @param executionDate    start schedule.
     * @param oAddress         client's home postal address.
     */
    public ExecutionOrder(int orderNumber, String clientName, double distanceToClient, String category, String serviceType, Date executionDate, PostalAddress oAddress) {
        this.orderNumber = orderNumber;
        this.clientName = clientName;
        this.distanceToClient = distanceToClient;
        this.category = category;
        this.serviceType = serviceType;
        this.executionDate = executionDate;
        this.oAddress = oAddress;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public String getClientName() {
        return clientName;
    }

    public double getDistanceToClient() {
        return distanceToClient;
    }

    public String getCategory() {
        return category;
    }

    public String getServiceType() {
        return serviceType;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public PostalAddress getAddress() {
        return oAddress;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.orderNumber);

        return hash;
    }

    /**
     * Method Equals in ExecutionOrder that overrides Equals.
     *
     * @param o execution order object.
     * @return true if the order numbers, client names, distances, categories, servicetypes, execution dates and addresses are the same, false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        ExecutionOrder obj = (ExecutionOrder) o;
        return (this.orderNumber == obj.orderNumber);
    }

    /**
     * Returns the textual representation of an Execution Order.
     *
     * @return textual representation of an Execution Order.
     */
    @Override
    public String toString() {
        return String.format("%d-%s-%.02f-%s-%s-%s-%s"/*, this.providerName*/, this.orderNumber, this.clientName, this.distanceToClient, this.category, this.serviceType, this.executionDate.toString(), this.oAddress.toString());
    }
}
