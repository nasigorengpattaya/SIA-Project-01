package sg.edu.rp.c346.sia_project_01;

/**
 * Created by 15017420 on 26/10/2017.
 */

public class Attraction {

    private String name;
    private String address;

    public Attraction(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
