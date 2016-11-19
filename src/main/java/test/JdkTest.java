package test;

/**
 * @author NikoBelic
 * @create 17/11/2016 15:22
 */
public class JdkTest
{
    public static void main(String[] args)
    {
        int a = 127;
        int _a = 127;
        System.out.println(a == _a);
        Integer b = 127;
        Integer _b = 127;
        System.out.println(b == _b);
        System.out.println(a == _b);
        Long.valueOf(a);
        Integer.parseInt("1");
    }
}
