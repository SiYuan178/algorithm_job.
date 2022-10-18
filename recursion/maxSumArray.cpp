#include<stdio.h>
#include<limits.h>
#include<math.h>
int max(int a, int b)
{
	return a > b ? a : b;
}
int maxsubArray(int num[], int low, int high)
{
	if (low == high)
	{//分解到只有一个元素的情况
		if (num[low] > INT_MIN)
		{
			return num[low];
		}
		else
		{
			return INT_MIN;
		}
	}
	int mid = (low + high) / 2;//从中间元素开始分解
	int maxLeft=maxsubArray(num, low, mid);//求左边的最大子序列和
	int maxRight=maxsubArray(num, mid + 1, high);//求右边的最大子序列和
	//最大序列和由左右序列的部分组成
	int sumLeft = INT_MIN;
	int sumRight = INT_MIN;
	int a = 0, b = 0;
	for (int i =mid; i>=low; i--)
	{
		a += num[i];
		if (a > sumLeft)
		{
			sumLeft = a;
		}
	}
	for (int j = mid + 1; j <= high; j++)
	{
		b += num[j];
		if (b > sumRight)
		{
			sumRight = b;
		}
	}
	int sum = sumLeft + sumRight;
	return max(max(maxLeft, maxRight), sum);

}
int main()
{
	int num[12] = { 1,-2,4,5,-2,8,3,-2,6,3,7,-1 };
	printf("%d\n",maxsubArray( num,0, 11));
	return 0;
}