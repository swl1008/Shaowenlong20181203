package swl.bwie.com.shaowenlong201812033;

public class WaterBean {
    public String name;
    public String uuid;

    public WaterBean(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
