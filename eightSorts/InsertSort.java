public class InsertSort {
    static  public void list(int[] num){
        for(int i=0;i<num.length;i++){
            System.out.print (num[i]+" " );
        }
        System.out.println ( );
    }
    static public void InsertSort(int[] num){
    for(int i=1;i<num.length;i++) {
        int temp = num[i];
        int j = 0;
        for (j = i - 1; j >= 0 && num[j] > temp; j--) {
            num[j + 1] = num[j];
        }
        num[j + 1] = temp;
    }
    }

    public static void main(String[] args) {
        int[] a=new int[]{2,1,4,3,7,0};
        InsertSort(a);
        list(a);


    }
}
