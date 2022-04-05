public class Customer {
    private int id;
    private String name;
    private String email;
    private static int maxCustomerId = 0;


    public Customer(int id) {

        this.id= id;

    }
    public Customer(int id, String name, String email) {

        this.name = name;
        this.email = email;
        if(id > maxCustomerId){
            maxCustomerId = id;

        } else {
            maxCustomerId++;
        }
        this.id = maxCustomerId ;
        System.out.println("maxCustomer ID : "+maxCustomerId );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
