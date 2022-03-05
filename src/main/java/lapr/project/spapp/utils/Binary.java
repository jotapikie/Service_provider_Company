package lapr.project.spapp.utils;

import lapr.project.spapp.model.Company;

import java.io.*;

public class Binary {

    public static Company importCompany() {
        Company company = new Company();
        try {
            FileInputStream fileInputStream = new FileInputStream("config/company.bin");
            if (fileInputStream.available() == 0) {
                return company;
            } else {
                ObjectInputStream is = new ObjectInputStream(fileInputStream);
                company = (Company) is.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            File f = new File("config/company.bin");
            try {
                f.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return company;
    }

    public static void exportCompany(Company company) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("config/company.bin"));
            objectOutputStream.writeObject(company);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

