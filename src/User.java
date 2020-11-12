import java.io.Serializable;

public class User implements Serializable {//实现序列化
    String account="";
    String password="";
    int age = 0;
    double height = 0;

    //重写构造方法，方便数的传入
    public User(String account, String password, int age , double height){
        super();
        this.account = account;
        this.password = password;
        this.age = age;
        this.height=height;
    }

    // 重写toString方法，方便显示
    public String toString(){
        return "account="+account + ", password="+ password + ",  age="+age+",  height=" + height+"";
    }
}
