package dao;

public class Admin extends Role{

    private int level;

    public Admin(){

    }

    public Admin(String name, String password) {
        super(name,password);
        level=0;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "level=" + level +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
