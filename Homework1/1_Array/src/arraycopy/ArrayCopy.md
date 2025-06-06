# 数组复制

## 方法简述

1. `System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)`
    - 需要预先创建目标数组
    - 性能最好，是本地方法
    - 参数分别是：源数组、源数组起始位置、目标数组、目标数组起始位置、复制长度

2. `Arrays.copyOf(T[] original, int newLength)`
    - 内部实际上调用了System.arraycopy
    - 会自动创建新数组
    - 可以指定新数组的长度（可以比原数组长或短）

3. `Arrays.copyOfRange(T[] original, int from, int to)`
    - 特点：
        - 复制指定范围的元素
        - 范围是左闭右开区间 [from, to)
        - 如果to大于原数组长度，会用默认值填充
    - 内部也是使用System.arraycopy实现

## copyOfRange的特点
`copyOfRange`的重要特点：
1. 如果`to`超出原数组长度，会用默认值填充多出的部分
2. 如果`from`等于`to`，会返回空数组
3. 如果`from`大于`to`，会抛出异常
4. 如果索引为负，会抛出`ArrayIndexOutOfBoundsException`

## 方法评价
- `System.arraycopy`性能最好，但使用较复杂
- `Arrays.copyOf`最常用，用法简单
- `Arrays.copyOfRange`在需要部分复制时很有用
