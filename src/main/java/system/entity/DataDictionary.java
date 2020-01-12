package system.entity;


public class DataDictionary {

    String node;
    String parent;
    String level;
    String name;
    String value;
    String sort;
    String show;
    String body;
    String state;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "DataDictionary{" +
                "node='" + node + '\'' +
                ", parent='" + parent + '\'' +
                ", level='" + level + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", sort='" + sort + '\'' +
                ", show='" + show + '\'' +
                ", body='" + body + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}