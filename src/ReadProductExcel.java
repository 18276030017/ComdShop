import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class ReadProductExcel {
    public static void main(String[] args) throws ClassNotFoundException {
        Product []products=new ReadProductExcel().readExcel(Class.forName("ReadProductExcel").getResourceAsStream("/ReadProductExcel.xlsx"));
        for (Product product:products){
            System.out.println(product.getPID());
            System.out.println(product.getPname());
            System.out.println(product.getPrice());
            System.out.println(product.getDesc());
        }
    }
    public Product[] readExcel(InputStream in) {
        Product products[] = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            products = new Product[xs.getLastRowNum()];
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();//每循环一次就把电子表格的一行的数据给对象赋值
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setPID(this.getValue(cell));//给username属性赋值
                    } else if (k == 1) {
                        product.setPname(this.getValue(cell));//给password属性赋值
                    } else if (k == 2) {
                        product.setPrice(this.getValue(cell));//给address属性赋值
                    } else if (k == 3) {
                        product.setDesc(this.getValue(cell));//给phone属性赋值
                    }
                }
                products[j-1] = product;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductByID(String id,InputStream in) {
        Product products[] = null;
        try {
            XSSFWorkbook xw = new XSSFWorkbook(in);
            XSSFSheet xs = xw.getSheetAt(0);
            for (int j = 1; j <= xs.getLastRowNum(); j++) {
                XSSFRow row = xs.getRow(j);
                Product product = new Product();//每循环一次就把电子表格的一行的数据给对象赋值
                for (int k = 0; k <= row.getLastCellNum(); k++) {
                    XSSFCell cell = row.getCell(k);
                    if (cell == null)
                        continue;
                    if (k == 0) {
                        product.setPID(this.getValue(cell));//给username属性赋值
                    } else if (k == 1) {
                        product.setPname(this.getValue(cell));//给password属性赋值
                    } else if (k == 2) {
                        product.setPrice(this.getValue(cell));//给address属性赋值
                    } else if (k == 3) {
                        product.setDesc(this.getValue(cell));//给phone属性赋值
                    }
                }
                if (id.equals(product.getPID())){
                    return product;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;//没有什么意义，防止不执行return product（当查找ID与商品ID不一致时，不执行return product）时程序出错，当都找不到商品时就返回一个空
    }
    private String getValue(XSSFCell cell) {
        String value;
        CellType type = cell.getCellTypeEnum();
        DecimalFormat df = new DecimalFormat("#");
        switch (type) {
            case STRING:
                value = cell.getStringCellValue();
                break;
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = cell.getBooleanCellValue() + "";
                break;
            case NUMERIC:
                value = df.format(cell.getNumericCellValue());//double和一个空字符串相连接，最终得到字符串
                System.out.println("转换后的："+value);
                break;
            case FORMULA:
                value = cell.getCellFormula();
                break;
            case ERROR:
                value = "非法字符";
                break;
            default:
                value = "";
                break;
        }
        return value;
    }
}