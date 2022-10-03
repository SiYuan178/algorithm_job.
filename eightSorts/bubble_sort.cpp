#include<stdio.h>
#include<string.h>
#include<stdlib.h>
int str_compar(const void* e1, const void* e2)
{
	return strcmp((char*)e1,(char*)e2);
}
int int_compar(const void* e1, const void* e2)
{
	return (*(int*)e1 - *(int*)e2);
}
int double_compar(const void* e1, const void* e2)
{
   double d=(*(double*)e1 - *(double*)e2);
   if (d > 0)
   {
	   return 1;
   }
   else if (d < 0)
   {
	   return -1;
   }
   else
   {
	   return 0;
   }
}
void copy(char* buffer1, char* buffer2, int width)
{//将buffer2赋值给buffer1
	for (int i = 0; i < width; i++)
	{
		*buffer1++ = *buffer2++;
	}
}
void swap(char* buffer1, char* buffer2, int width)
{
	for (int i = 0; i < width; i++)
	{
		char temp = *buffer1;
		*buffer1 = *buffer2;
		*buffer2 = temp;
		buffer1++;
		buffer2++;
	}
}
void print(int arr[], int sz)
{
	for (int i = 0; i < sz; i++)
	{
		printf("%d ", arr[i]);
	}
	printf("\n");
}
void print(double arr[], int sz)
{
	for (int i = 0; i < sz; i++)
	{
		printf("%.2f ", arr[i]);
	}
	printf("\n"); 
}
void print(char arr[][10], int sz)
{
	for (int i = 0; i < sz; i++)
	{
		printf("%s ", arr[i]);
	}
	printf("\n");
}
void bubble_sort(void* arr, int sz, int width, int (*cmp)(const void* e1, const void* e2))
{
	for (int i = 0; i < sz - 1; i++)
	{//趟数
		for (int j = 0; j < sz - i - 1; j++)
		{//一趟排序过程
			if (cmp((char*)arr + width * j, (char*)arr + width * (j + 1)) > 0)//比较
			{
				swap((char*)arr + width * j, (char*)arr + width * (j + 1), width);//交换
			}

		}
	}
}
int main()
{
	char str_arr1[][10] = { "1onday", "Tuesday", "Wednesday", "Thursday", "Friday", "1aturday", "Sunday" };
	int  num[10] = { 2,1,12,123,5,219,0,82,8,6 };
	double decimal[10] = { 3.2,1.1,3,5.3,2,4.4,9,0,1.4,9.9 };
	int str_size = sizeof(str_arr1) / sizeof(str_arr1[0]);
	int int_size = sizeof(num) / sizeof(num[0]);
	int double_size = sizeof(decimal) / sizeof(decimal[0]);
	bubble_sort(str_arr1, str_size, sizeof(str_arr1[0]), str_compar);
	bubble_sort(num, int_size, sizeof(num[0]), int_compar);
	bubble_sort(decimal, double_size, sizeof(decimal[0]), double_compar);
	print(str_arr1, str_size);
	print(num,int_size);
	print(decimal, double_size);
	return 0;
}
