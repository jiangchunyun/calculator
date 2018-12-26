package com.example.calculator.com.example.calculator.presenter;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.calculator.com.example.calculator.utils.CalculatorStringInstance;

import java.util.Stack;

/**
 * Created by 上官刀刀 on 2018/12/24.
 * 用来处理计算任务的
 *
 * @author daodao
 * @version 1.0
 */

public class CalculateThread extends AsyncTask<String, String, String> {

    private String TAG = "CalculateThread";//log标识
    private TextView result;//结果展示

    /**
     * 构造函数
     *
     * @param result the Textview 用来展示计算结果的
     */
    public CalculateThread(TextView result) {
        this.result = result;
    }

    /**
     * 任务执行前初始化
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "start calaulate");
    }

    /**
     * 在子线程中执行任务
     *
     * @return the String 执行完以后回调给onPostExecute函数
     */
    @Override
    protected java.lang.String doInBackground(java.lang.String... strings) {
        return calculate(CalculatorStringInstance.getInstance().getCalculatorValue());
    }

    /**
     * 将结果返回到主线程执行
     *
     * @param s the String 是doInBackground返回来的值
     */
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        /*
         * 将运算结果设置给表达式，并将结果显示出来
         */
        CalculatorStringInstance.getInstance().setCalculatorValue(s);
        result.setText(s);
    }

    /**
     * 具体计算表达式的函数
     *
     * @param calculatorValue the String 需要处理的表达式值
     * @return the String 返回运算的结果
     */
    public String calculate(String calculatorValue) {
        /*
         *定义栈用来存储运算数和运算符号
         */
        Stack<Double> numberStack = new Stack<Double>();//操作数栈
        Stack<String> calculatorStack = new Stack<String>();//操作符栈
        String words[] = calculatorValue.split(" ");//所有符号
        calculatorStack.push("+");

        /*
         * 表达式根据运算符的优先级进行运算（先算乘除后算加减）
         */
        for (int i = 0; i < words.length; i++) {
            /*
             * 如果是运算符进行运算符处理
             */
            if (words[i].equals("+") || words[i].equals("-") || words[i].equals("*") || words[i].equals("/")) {
                /*
                 * 遇到优先级高的直接进行运算，遇到优先级低的进行压栈操作
                 */
                if ((words[i].equals("*") || words[i].equals("/")) && (calculatorStack.peek().equals("+") || calculatorStack.peek().equals("-"))) {
                    String calculator = words[i];//当前操作符
                    Double one = numberStack.peek();//第一个操作数
                    numberStack.pop();
                    Double two = Double.valueOf(words[i + 1]);//第二个操作数
                    i++;
                    Double res = null;//运算结果
                    if (calculator.equals("*")) {
                        res = one * two;
                    } else if (calculator.equals("/")) {
                        res = one / two;
                    }
                    numberStack.push(res);
                } else {
                    calculatorStack.push(words[i]);
                }

            }
            /*
             * 如果是数字则放入栈中
             */
            else {
                numberStack.push(Double.valueOf(words[i]));
            }
        }

        /*
         * 最后进行收尾操作
         */
        while (calculatorStack.size() > 1) {
            String calculator = calculatorStack.peek();//操作符
            calculatorStack.pop();
            Double one = numberStack.peek();//第一个操作数
            numberStack.pop();
            Double two = numberStack.peek();//第二个操作数
            numberStack.pop();
            Double res = null;//运算结果
            if (calculator.equals("*")) {
                res = one * two;
            } else if (calculator.equals("/")) {
                res = one / two;
            } else if (calculator.equals("+")) {
                res = one + two;
            } else if (calculator.equals("-")) {
                res = two - one;
            }
            numberStack.push(res);
        }
        return String.valueOf(numberStack.peek());
    }
}
