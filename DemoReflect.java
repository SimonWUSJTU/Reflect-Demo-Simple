package Demo;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class DemoReflect {

    public static void main(String[] args) throws Exception {
        //创建配置文件对应的对象
        Properties pro=new Properties();

        //加载配置文件，使用的是 pro.load(is);但是，通过一定的方法获得这个输入流
        //取出该类字节类码的路径获得器classLoade
        ClassLoader classLoader=DemoReflect.class.getClassLoader();
        //用路径加载器加载配置文件，存入输入流
        InputStream is=classLoader.getResourceAsStream("pro.properties");
        pro.load(is);

        //读配置文件的内容，属性文件就是一个Mapy一样，有key，也有value
        String className=pro.getProperty("className");
        String methodName=pro.getProperty("methodName");

        //获得从配置文件内读来的类的，该类的字节码，class类
       Class cls= Class.forName(className);

       //调用class类中的构造方法，实例化这个类
       Object obj=cls.newInstance();

       //调用class中的成员对象方法，获取成员方法
       Method method=cls.getMethod(methodName);
       //使用成员方法
       method.invoke(obj);

    }
}
