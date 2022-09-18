package Stack;

import java.util.Scanner;

class Stack2{
    private int maxsize;
    private int[] stack2;
    int top=-1;

    public Stack2(int maxsize) {
        this.maxsize = maxsize;
        stack2=new int[maxsize];
    }
    public boolean isFull(){
        return top==maxsize-1;
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public void push(int value){
        if(isFull()){
            throw new RuntimeException ("栈已满，无法继续添加元素");
        }
        stack2[++top]=value;
    }
    public int  pop(){
        if(isEmpty ()){
            throw new RuntimeException ("栈为空，无法取出元素");
        }
        int value=stack2[top--];
        return value;
    }
    public void list(){
        if(isEmpty ()){
            throw new RuntimeException ("栈为空");
        }
        for(int i=top;i>=0;i--){
            System.out.println (stack2[i] );
        }
    }
    //返回栈的优先级，优先级是程序员进行确定的，规定优先级使用数字表示，数字越大，优先级越高
    public int priority(int oper){
        if(oper=='*'||oper=='/'){
            return 1;
        }else if(oper=='+'||oper=='-'){
            return 0;
        }else{
            return -1;
        }

    }
    //判断是否为运算符
    public boolean isOper(char val){
        return val=='+'||val=='*'||val=='/'||val=='-'||val=='('||val==')';
    }
    //取栈顶元素
    public int peek(){
        return stack2[top];
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int cal(int num1, int num2, int oper){
        int res=0;
        switch(oper){
            case '+':
                res=num2+num1;
                break;
            case '-':
                res=num2-num1;
                break;
            case '*':
                res=num2*num1;
                break;
            case '/':
                try {
                    res=num2/num1;
                } catch (Exception e) {
                    e.printStackTrace ( );
                } finally {
                }
                break;
            default:break;
        }
        return res;
    }
}
public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        System.out.println ("请输入表达式:" );
        String expression=sc.nextLine ();
        Stack2 numStack = new Stack2(10);
        Stack2 operStack = new Stack2(10);
        //定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res =0;
        char ch = ' ';//将每次扫描到的保存到ch
        String nums = "";//用来扫描多位数
        //使用while来扫描expression
        while(true){
            //依次得到expression的每个字符
            ch = expression.charAt(index);
            if (operStack.isOper(ch)){//当前是运算符
                //判断当前的栈是否为空
                if(operStack.isEmpty()){//当前栈为空直接放入
                    operStack.push(ch);
                }else{//当前的操作符栈中有操作符
                    if(ch=='('){
                        operStack.push(ch);
                    }else if(ch==')'){
                        while (!(operStack.peek()=='(')){
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            numStack.push(numStack.cal(num1,num2,oper));
                        }
                        operStack.pop();
                    }else {
                        if(operStack.priority(ch)<=operStack.priority(operStack.peek())){
                            //如果新读取的操作符的优先级小于等于当前栈顶操作符的优先级那么我们需要从操作数
                            //栈中弹出两个操作数进行运算
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            numStack.push(numStack.cal(num1,num2,oper));
                            operStack.push(ch);
                        }else{
                            operStack.push(ch);
                        }
                    }
                }
            }else{//当前的是数字
                //对多位数的处理
                nums += ch;
                if (index == expression.length()-1){
                    //如果ch已经是我们expression中的最后一位那么我们就直接入栈
                    numStack.push(Integer.parseInt(nums));
                }else{//当前的运算符不是expression的最后一位
                    if (operStack.isOper(expression.charAt (index+1))){//如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(nums));
                        nums="";
                    }
                }
            }
            //让index+1，并判断是否扫描到了expression最后
            index ++;
            if(index>=expression.length()){
                break;
            }
        }

        while (true){
            if(operStack.isEmpty()){
                //当我们的符号栈为空的时候则计算到最后的结果，数栈中只有一个结果那就是我们的结果
                break;
            }
            num1 =  numStack.pop();
            num2 =  numStack.pop();
            oper =  operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        System.out.printf("表达式%s=%d",expression,numStack.pop ());

    }
}