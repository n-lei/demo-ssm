package user.entity;

import java.util.List;

public class Dept {
    private String node;
    private String parentNode;
    private String level;
    private String code;
    private String name;

    private List<User> users;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getParentNode() {
        return parentNode;
    }

    public void setParentNode(String parentNode) {
        this.parentNode = parentNode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "node='" + node + '\'' +
                ", parentNode='" + parentNode + '\'' +
                ", level='" + level + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}
