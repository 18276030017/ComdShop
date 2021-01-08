import java.io.File;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        /*
        开始读文件
         */
        File file=new File("C:\\Users\\Administrator.PC-20190823HNEY\\Desktop\\ComdShop\\src\\CmdShop.xlsx");
        ReadExcel readExcel = new ReadExcel();//创建对象
        User users[] = readExcel.readExcel(file);//从表格中获取用户数据

        Scanner sc = new Scanner(System.in);//定义一个输入类
        System.out.println( "请输入用户名：");
        String username = sc.next();//获取输入的用户名
        System.out.println("你的用户名是:"+  new String(username));

        System.out.println("请输入密码:");
        String password = sc.next();
        System.out.println("你的密码是:"+  new String(password));


        for(int i=0;i<users.length;i++) {
            if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                System.out.println("登录成功");
                break;
            } else {
                System.out.println("登录失败");
            }
        }
    }

}
