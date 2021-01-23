public class Product {
    private String PID;
    private String Pname;
    private String price;
    private String Desc;

    public void setPID(String PID) {
        this.PID = PID;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getPID() {
        return PID;
    }

    public String getPname() {
        return Pname;
    }

    public String getPrice() {
        return price;
    }

    public String getDesc() {
        return Desc;
    }
}
