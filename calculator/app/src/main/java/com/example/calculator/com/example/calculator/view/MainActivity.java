package com.example.calculator.com.example.calculator.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.calculator.R;
import com.example.calculator.com.example.calculator.presenter.CalculateThread;
import com.example.calculator.com.example.calculator.utils.CalculatorStringInstance;

/**
 * create by daodao on 2018.12.26
 * 该类是计算器的主界面类，用于显示计算器的控件。
 *
 * @author daodao
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    /*
     *定义功能性按钮
     */
    private Button clearAll;//清除所有表达式
    private Button delete;//删除一个
    private Button add;//加号
    private Button sub;//减号
    private Button mul;//乘号
    private Button div;//除号
    private Button equ;//等于号

    /*
     定义数字按钮
     */

    private Button zero;//0
    private Button smallQuantity;//.
    private Button one;//1
    private Button two;//2
    private Button tree;//3
    private Button four;//4
    private Button five;//5
    private Button six;//6
    private Button seven;//7
    private Button eight;//8
    private Button nine;//9

    private TextView result;//结果
    CalculatorStringInstance instance;//表达式单例
    private CalculateThread calculateThread;//计算任务的线程

    /**
     * activity生命周期函数，用来初始化activity。
     * @param savedInstanceState the Bundle 用来保存activity的状态
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
        setClick();
        initCalculateThread();
    }

    /**
     * 初始化界面,包括一些功能性按钮和数字按钮
     */
    public void initView()
    {
        setContentView(R.layout.activity_main);

        /*
         *功能性按钮的初始化
         */
        clearAll= (Button) findViewById(R.id.clearAll);
        delete= (Button) findViewById(R.id.delete);
        add= (Button) findViewById(R.id.add);
        sub= (Button) findViewById(R.id.sub);
        mul= (Button) findViewById(R.id.mul);
        div= (Button) findViewById(R.id.div);
        equ= (Button) findViewById(R.id.equ);

        /*
         *初始化数字按钮
         */
        zero= (Button) findViewById(R.id.zero);
        smallQuantity= (Button) findViewById(R.id.smallQuantity);
        one= (Button) findViewById(R.id.one);
        two= (Button) findViewById(R.id.two);
        tree= (Button) findViewById(R.id.tree);
        four= (Button) findViewById(R.id.four);
        five= (Button) findViewById(R.id.five);
        six= (Button) findViewById(R.id.six);
        seven= (Button) findViewById(R.id.seven);
        eight= (Button) findViewById(R.id.eight);
        nine= (Button) findViewById(R.id.nine);

        // 结果按钮初始化
        result= (TextView) findViewById(R.id.result);
    }

    /**
     * 初始化数据
     */
    public void initData()
    {
        instance=CalculatorStringInstance.getInstance();
    }

    /**
     * 初始化计算任务线程
     */
    public void initCalculateThread()
    {
        calculateThread=new CalculateThread(result);
    }

    /**
     * 设置监听事件
     */
    public void setClick()
    {
        clearAll.setOnClickListener(this);
        delete.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        equ.setOnClickListener(this);
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        tree.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        smallQuantity.setOnClickListener(this);
    }

    /**
     * 计算器按钮监听事件的处理
     * @param view the View 注册了监听函数的view。
     */
    @Override
    public void onClick(View view) {
        int id=view.getId();//控件id
        String calculatorValue=instance.getCalculatorValue();//表达式

        if(id==R.id.equ)
        {
            if(calculatorValue.isEmpty())
            {
                Toast.makeText(getApplication(),"请输入表达式",Toast.LENGTH_SHORT).show();
            }
            else {
                calculateThread = new CalculateThread(result);
                calculateThread.execute();
            }
        }
        else {
            switch (id) {
                case R.id.clearAll: {
                /*
                    清除所有的表达式
                 */
                    calculatorValue = "";
                    break;
                }
                case R.id.delete: {
                /*
                  将表达式的最后一个字符删除
                 */
                    if (calculatorValue.length() >= 1) {
                        int calculatorValueLength = calculatorValue.length();
                        calculatorValue = calculatorValue.substring(0, calculatorValueLength - 1);
                    }
                    break;
                }
                case R.id.add: {
                    calculatorValue = calculatorValue + " + ";
                    break;
                }
                case R.id.sub: {
                    calculatorValue = calculatorValue + " - ";
                    break;
                }
                case R.id.mul: {
                    calculatorValue = calculatorValue + " * ";
                    break;
                }
                case R.id.div: {
                    calculatorValue = calculatorValue + " / ";
                    break;
                }
                case R.id.one: {
                    calculatorValue = calculatorValue + "1";
                    break;
                }
                case R.id.two: {
                    calculatorValue = calculatorValue + "2";
                    break;
                }
                case R.id.tree: {
                    calculatorValue = calculatorValue + "3";
                    break;
                }
                case R.id.four: {
                    calculatorValue = calculatorValue + "4";
                    break;
                }
                case R.id.five: {
                    calculatorValue = calculatorValue + "5";
                    break;
                }
                case R.id.six: {
                    calculatorValue = calculatorValue + "6";
                    break;
                }
                case R.id.seven: {
                    calculatorValue = calculatorValue + "7";
                    break;
                }
                case R.id.eight: {
                    calculatorValue = calculatorValue + "8";
                    break;
                }
                case R.id.nine: {
                    calculatorValue = calculatorValue + "9";
                    break;
                }
                case R.id.zero: {
                    calculatorValue = calculatorValue + "0";
                    break;
                }
                case R.id.smallQuantity: {
                    calculatorValue = calculatorValue + ".";
                    break;
                }
            }
            result.setText(calculatorValue);
            instance.setCalculatorValue(calculatorValue);
        }
    }
}
