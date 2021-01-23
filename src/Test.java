import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean b=true;
        while (b) {

            Scanner sc = new Scanner(System.in);//定义一个输入类
            System.out.println("请输入用户名：");
            String username = sc.next();//获取输入的用户名

            System.out.println("请输入密码:");
            String password = sc.next();

            /*
            开始读文件
             */
            //File file=new File("C:\\Users\\Administrator.PC-20190823HNEY\\Desktop\\ComdShop\\src\\CmdShop.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/CmdShop.xlsx");
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();//创建对象
            User users[] = readExcel.readExcel(in);//从表格中获取用户数据


            for (int i = 0; i < users.length; i++) {
                if (username.equals(users[i].getUsername()) && password.equals(users[i].getPassword())) {
                    System.out.println("登录成功");
                    b=false;
                    /*
                    显示商品
                     */
                    ReadProductExcel readProductExcel = new ReadProductExcel();//定义一个可以读取商品的类
                    Product products[]=readProductExcel.readExcel(inProduct);
                    for (Product product:products){
                        System.out.print("\t" + product.getPID());
                        System.out.print("\t" + product.getPname());
                        System.out.print("\t" + product.getPrice());
                        System.out.println("\t" + product.getDesc());
                    }
                    //显示商品后，程序又进行阻塞
                    System.out.println("请输入你要购买的商品ID");
                    String PID = sc.next();
                    int count=0;
                    /*
                    创建一个购物车数组：存放你要购买的商品
                     */
                    Product carts[] = new Product[3];
                    /*
                    根据输入的ID去Excel中对比检查是否有此ID信息，有则返回此ID的上商品信息
                     */
                    inProduct = null;
                    inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                    Product product = readProductExcel.getProductByID(PID,inProduct);
                    System.out.print("要购买的商品名称：" + product.getPname());
                    System.out.println("要购买的商品价格：" + product.getPrice());
                    if (product !=null){
                        carts[count++]=product;
                    }
                    System.out.println("继续购买商品请按1");
                    System.out.println("查看购物车请按2");
                    System.out.println("退出购物车请按3");
                    int choose = sc.nextInt();
                    if(choose == 1){
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                        readProductExcel = new ReadProductExcel();//定义一个可以读取商品的类
                        products=readProductExcel.readExcel(inProduct);
                        for (Product product2:products){
                            System.out.print("\t" + product2.getPID());
                            System.out.print("\t" + product2.getPname());
                            System.out.print("\t" + product2.getPrice());
                            System.out.println("\t" + product2.getDesc());
                        }
                        //显示商品后，程序又进行阻塞
                        System.out.println("请输入你要购买的商品ID");
                        PID = sc.next();
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/Product.xlsx");
                        Product product2 = readProductExcel.getProductByID(PID,inProduct);
                        System.out.print("要购买的商品名称：" + product2.getPname());
                        System.out.println("要购买的商品价格：" + product2.getPrice());
                        if (product !=null){
                            carts[count++]=product;
                        }
                    }else if (choose==2){
                        /*
                        查看购物车
                         */

                    }
                    break;
                } else {
                    System.out.println("登录失败");
                    break;
                }
            }
        }
    }
}
