package com.hexi.kotlindemo;

public class NewDupCode {
    private int a;
    
    public NewDupCode(int a) {
        this.a = a;
    }

    /**
     * 创建对象并返回对象实例
     * @return
     */
    public NewDupCode newDupCode() {
        return new NewDupCode(1);
    }

    /**
     * 程序做赋值操作
     * @return
     */
    public int voidUseNewDupCode() {
        final NewDupCode o = new NewDupCode(1);
        return o.a + o.hashCode();
    }

    /**
     * 程序不做赋值操作，直接操作对象的成员
     */
    public int voidUseNonRefNewDupCode() {
        return new NewDupCode(1).a;
    }

    /**
     * 程序不对这个对象操作，只进行一个 new 操作
     */
    public void voidNewDupCode() {
        new NewDupCode(1);
    }
}
