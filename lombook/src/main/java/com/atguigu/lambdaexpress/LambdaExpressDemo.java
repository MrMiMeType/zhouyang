package com.atguigu.lambdaexpress;


@FunctionalInterface
interface Foo
{
    //public void sayHello();
    //public void say886();
    public int add(int x ,int y);

    default int div(int x ,int y)
    {
        return x/y;
    }
    public static int mul(int x,int y)
    {
        return x * y;
    }
}
/**
 * 1    lambda Express
 *  1.1 如何写:    拷贝形括号，写死右箭头，落地大括号
 *  1.2 Lamdba 有且仅有一个方法，注解@FunctionalInterface
 *  1.3 default
 *  1.4 静态方法
 */
public class LambdaExpressDemo
{
    public static void main(String[] args)
    {
//        Foo foo = new Foo()
//        {
//            @Override
//            public void sayHello()
//            {
//                System.out.println("******hello 0416");
//            }
//
//            @Override
//            public void say886()
//            {
//
//            }
//        };
//        foo.sayHello();
//
//        foo = () -> {System.out.println("******hello 0416 v3");};
//        foo.sayHello();

        Foo foo = (x,y) -> {
            System.out.println("*****come in add method");
            return x + y;
        };

        int result = foo.add(3,25);
        System.out.println(result);

        System.out.println(foo.div(10,2));
        System.out.println(Foo.mul(2,3));
    }
}
