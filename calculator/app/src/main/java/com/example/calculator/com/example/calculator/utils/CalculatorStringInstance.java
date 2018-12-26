package com.example.calculator.com.example.calculator.utils;

/**
 * Created by 上官刀刀 on 2018/12/23.
 * 一个工具单例类，用以存储计算器的表达式
 *
 * @author daodao
 * @version 1.0
 */

public class CalculatorStringInstance {
    private volatile static CalculatorStringInstance instance;//单例的定义
    private String calculatorValue;//表达式的定义

    /**
     * 单例的构造函数
     */
    private CalculatorStringInstance()
    {
        calculatorValue =new String();
    }

    /**
     * 用双重检验方式获取单例的实例函数
     * @return the CalculatorStringInstance 单例实例
     */
    public static CalculatorStringInstance getInstance()
    {
        /*
         *第一次判断空是防止每次同步,第二次初始化是为了实例化单例。
         */
        if(instance ==null)
        {
            synchronized (CalculatorStringInstance.class)
            {
                if(instance ==null)
                {
                    instance =new CalculatorStringInstance();
                }
            }
        }
        return instance;
    }

    /**
     * 获取表达式
     * @return the String 返回表达式
     */
    public String getCalculatorValue()
    {
        return calculatorValue;
    }

    /**
     * 设置表达式的值
     * @param calculatorValue the String 用来设置表达式
     */
    public void setCalculatorValue(String calculatorValue)
    {
        this.calculatorValue=calculatorValue;
    }
}
