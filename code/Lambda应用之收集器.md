---
title:  Lambda应用之收集器
tags: lambda
grammar_cjkRuby: true
---

## Lambda应用之收集器

```java?linenums
// : Utils.java
// 工具类
public class Utils {

    public static <T, R> R collect(List<T> list, Collector<R, T> collector, Class<R> clazz) throws Exception {
        R e = clazz.newInstance();
        for (T t : list) {
            collector.collect(e, t);
        }
        return e;
    }

    // 测试
    public static void main(String[] args) throws Exception {
        // 填充数据
        // Dictionary 实体类
        ArrayList<Dictionary> objects = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Dictionary dictionary = new Dictionary();
            dictionary.setValue(i + "");
            objects.add(dictionary);
        }
        // 列表处理
        StringBuilder stringBuilder = collect(objects, (sb, d)-> sb.append(d.getValue()), StringBuilder.class);
        // 输出结果
        System.out.println(stringBuilder.toString());
    }
} ///:~

// : Collector.java
// 函数式接口
@FunctionalInterface
public interface Collector<T, R> {
    void collect(T t, R r);
} ///:~

```