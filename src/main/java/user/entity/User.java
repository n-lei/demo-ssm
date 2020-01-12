package user.entity;

public class User {
    private long id;
    private String userName;
    private String type;
    private String deptNode;
    private String sex;

    private Dept dept;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeptNode() {
        return deptNode;
    }

    public void setDeptNode(String deptNode) {
        this.deptNode = deptNode;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", type='" + type + '\'' +
                ", deptNode='" + deptNode + '\'' +
                ", sex='" + sex + '\'' +
                ", dept=" + dept +
                '}';
    }
}
