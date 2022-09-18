package Stack;

import java.util.Random;

class Stack{
    private int maxsize;
    private int[] stack;
    int top=-1;

    public Stack(int maxsize) {
        this.maxsize = maxsize;
        stack=new int[maxsize];
    }
    public boolean isFull(){
        return top==maxsize-1;
    }
    public boolean isEmpty(){
        return top==-1;
    }
    public void pop(int value){
        if(isFull()){
            throw new RuntimeException ("栈已满，无法继续添加元素");
        }
        stack[++top]=value;
    }
    public int  push(){
        if(isEmpty ()){
            throw new RuntimeException ("栈为空，无法取出元素");
        }
        int value=stack[top];
        top--;
        return value;
    }
    public void list(){
        if(isEmpty ()){
            throw new RuntimeException ("栈为空");
        }
        for(int i=top;i>=0;i--){
            System.out.println (stack[i] );
        }
    }
}
public class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack (5);
        for (int i=0;i<5;i++){
            stack.pop(new Random ().nextInt (10));
        }
        stack.list();
        System.out.println ( stack.push ());
        System.out.println ( );
        stack.list();
    }
}
