# 数组填充

1. **基本用法**
```java
// 填充整个数组
Arrays.fill(array, value);

// 填充指定范围
Arrays.fill(array, fromIndex, toIndex, value);
```

2. **不同类型数组的填充**
- 基本类型数组（int, char, boolean等）
- 对象数组（String, 自定义对象等）
- 多维数组

3. **填充范围**
- 可以填充整个数组
- 可以填充指定范围的元素
- 范围是左闭右开区间[fromIndex, toIndex)

4. **注意事项**

a. 对象数组填充：
```java
// 警告：所有元素引用同一个对象
Arrays.fill(students, new Student("默认学生", 18));

// 正确方式：创建独立对象
for (int i = 0; i < students.length; i++) {
    students[i] = new Student("学生" + i, 18);
}
```

b. 多维数组填充：
```java
// 需要逐行填充
for (int[] row : matrix) {
    Arrays.fill(row, value);
}
```