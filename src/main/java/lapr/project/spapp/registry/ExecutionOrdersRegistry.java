package lapr.project.spapp.registry;

import lapr.project.spapp.model.ExecutionOrder;
import lapr.project.spapp.model.PostalAddress;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class ExecutionOrdersRegistry implements Serializable {

    /**
     * Execution Orders List.
     */
    private List<ExecutionOrder> m_lstExecutionOrders;

    /**
     * Creates an instance of ExecutionOrdersRegistry.
     */
    public ExecutionOrdersRegistry() {
        this.m_lstExecutionOrders = new ArrayList<>();
    }

    /**
     * Returns the list of execution orders after today's date.
     *
     * @return execution orders list.
     */
    public List<ExecutionOrder> getlstExecutionOrdersAfterToday() {
        List<ExecutionOrder> leo = new ArrayList<>();
        Date date = new Date();
        for (ExecutionOrder ex : m_lstExecutionOrders) {
            if (ex.getExecutionDate().after(date))
                leo.add(ex);
        }
        List<ExecutionOrder> lf = new ArrayList<>(leo);
        return lf;
    }


    /**
     * Returns the list of execution orders before today's date.
     *
     * @return execution orders list.
     */
    public List<ExecutionOrder> getlstExecutionOrdersBeforeToday() {
        Set<ExecutionOrder> leo = new HashSet<>();
        Date date = new Date();
        for (ExecutionOrder ex : m_lstExecutionOrders) {
            if (ex.getExecutionDate().before(date))
                leo.add(ex);
        }
        List<ExecutionOrder> lf = new ArrayList<>(leo);
        return lf;
    }

    /**
     * Returns the existent clients.
     *
     * @return clients names list.
     */
    public List<String> getClients() {
        Set<String> lcli = new HashSet<>();
        for (ExecutionOrder e : m_lstExecutionOrders)
            lcli.add(e.getClientName());
        List<String> lf = new ArrayList<>(lcli);
        return lf;
    }

    /**
     * Gets the execution orders from a client before today.
     *
     * @param clientName client name.
     * @param lsteo      execution orders before today.
     * @return executions orders list.
     */
    public List<ExecutionOrder> getExecutionOrdersByClient(String clientName, List<ExecutionOrder> lsteo) {
        List<ExecutionOrder> leo = new ArrayList<>();
        for (ExecutionOrder e : lsteo) {
            if (e.getClientName().equalsIgnoreCase(clientName))
                leo.add(e);
        }
        return leo;
    }

    /**
     * Imports the execution orders from a file.
     *
     * @param fileSP file path.
     * @return true if successful, false if not.
     */
    public boolean importExecutionOrders(File fileSP) {
        if (!isFileEmpty(fileSP)) {
            addExecutionOrders(fileSP);
            return true;
        }
        return false;
    }

    /**
     * Adds execution orders from a file.
     *
     * @param fileSP file.
     */
    private void addExecutionOrders(File fileSP) {
        try {
            FileInputStream file = new FileInputStream(fileSP);
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            reader.readLine();
            String line = reader.readLine();
            while (line != null) {
                String[] temp = line.split(";");
                String[] tempHour = temp[6].split(":");
                DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                LocalDate ld = LocalDate.parse(temp[5], df);
                Date date = getDate(ld, Integer.parseInt(tempHour[0]), Integer.parseInt(tempHour[1]), 0);
                this.m_lstExecutionOrders.add(new ExecutionOrder(Integer.parseInt(temp[0]), temp[1], Double.parseDouble(temp[2]), temp[3], temp[4], date, new PostalAddress(temp[7], temp[9], temp[8])));
                line = reader.readLine();
            }
        } catch (IOException | NullPointerException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Checks if a file is empty.
     *
     * @param f file.
     * @return true is the file is empty, false if not.
     */
    private boolean isFileEmpty(File f) {
        try {
            FileInputStream file = new FileInputStream(f);
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            if (reader.readLine().isEmpty()) {
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Creates a date in Date format.
     *
     * @param ld      date.
     * @param hour    hours.
     * @param minutes minutes.
     * @param seconds seconds.
     * @return
     */
    Date getDate(LocalDate ld, int hour, int minutes, int seconds) {
        Calendar c = Calendar.getInstance();
        c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth(), hour, minutes, seconds);
        return c.getTime();
    }
}
