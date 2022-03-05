/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lapr.project.spapp.model;
import lapr.project.spapp.registry.ExecutionOrdersRegistry;

import java.io.Serializable;

public class Company implements Serializable {

    /**
     * Designation
     */
    private static final String m_strDesignation = "LAPR2 Services Lda";

    /**
     * NIF
     */
    private static final String m_strNIF = "123456789";

    /**
     * Execution Orders Registry.
     */
    private final ExecutionOrdersRegistry regExecutionOrders;


    /**
     * Creates an instance of Company.
     */
    public Company()  {
        this.regExecutionOrders = new ExecutionOrdersRegistry();
    }

    /**
     * Returns the Execution Orders Registry.
     * @return ExecutionOrdersRegistry object.
     */
    public ExecutionOrdersRegistry getExecutionOrdersRegistry(){return regExecutionOrders;    }

}

    
    
