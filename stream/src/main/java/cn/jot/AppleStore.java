package cn.jot;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class AppleStore {
    static List<Apple> apples = new ArrayList<>();

    static {
        apples.add(new Apple(100, "red"));
        apples.add(new Apple(200, "yellow"));
        apples.add(new Apple(200, "red"));
        apples.add(new Apple(100, "yellow"));
        apples.add(new Apple(200, "blue"));
    }

    /*过滤掉一部分，并且返回list*/
    //通过苹果的颜色和重量进行过滤
    @Test
    public void test1() {
        System.out.println(apples.stream().filter(a -> a.getColor().equals("red") && a.getWeight() >= 150)
                .collect(Collectors.toList()));

        System.out.println("========");

        System.out.println(apples.stream()
                .filter(a -> a.getColor().equals("red") && a.getWeight() >= 150)
                .collect(Collectors.toList()));//collect可以规定多种操作返回不同的数据结构
    }

    /*分组统计，比如找到各种颜色的平均重量*/
    //传统方法
    @Test
    public void test2() {
        HashMap<String, List<Apple>> map = new HashMap<>();
        for (Apple apple : apples) {
            //使用computeIfAbsent初始化一个map，其中包含有ArrayList
            map.computeIfAbsent(apple.getColor(), a -> new ArrayList<>());
            map.get(apple.getColor()).add(apple);
        }
        HashMap<String, Integer> weight = new HashMap<>();
        for (Map.Entry<String, List<Apple>> entry : map.entrySet()) {
            int weights = 0;
            for (Apple apple : entry.getValue()) {
                weights += apple.getWeight();
            }
            weights /= entry.getValue().size();
            weight.put(entry.getKey(), weights);
        }
        System.out.println(weight);
    }

    //使用流式运算，可以将list过滤，collect的时候变成其他的数据结构，groupingBy可以构造map
    @Test
    public void test3() {
        System.out.println(apples.stream().collect(Collectors.groupingBy(Apple::getColor,
                Collectors.averagingInt(Apple::getWeight))));
        System.out.println("======");
        System.out.println(apples.stream().collect(
                Collectors.groupingBy(Apple::getColor,
                        Collectors.averagingInt(Apple::getWeight))));
    }

    /*中间节点实验*/
    @Test
    public void test4() {
        String[] s = apples.stream().filter(a -> a.getColor().equals("red") || a.getColor().equals("yellow"))
                .map(Apple::getColor)
                .distinct()
                .peek(System.out::println)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(s));


        System.out.println("======");

        List<String> list = apples.stream().filter(a -> a.getColor().equals("red") || a.getColor().equals("yellow"))
                .map(Apple::getColor)
                .distinct()
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println(list);
    }

    /*流的产生*/
    @Test
    public void test5() {
        Stream<Integer> stream = Stream.of(5, 6, 7, 8);
        IntStream stream0 = Arrays.stream(new int[]{1, 2, 3, 4, 5});

        System.out.println("======");

        Stream<Integer> stream1 = Stream.of(1, 2, 3, 4);
        IntStream stream2 = Arrays.stream(new int[]{1, 2, 3, 4});
        Stream<Integer> stream3 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }}.stream();
    }

    /*中间操作：过滤、去重、转换类型、peek*/
    @Test
    public void test6() {
        String[] colors = apples.stream()
                .filter(a -> a.getColor().equals("red") || a.getColor().equals("blue"))
                .map(Apple::getColor)
                .peek(System.out::println)
                .distinct()
                .toArray(String[]::new);
        System.out.println(Arrays.toString(colors));
    }

    /*证明流中的元素都是一个一个过来的*/
    @Test
    public void test7() {
        Integer[] s = Stream.of(1, 2, 3, 4)
                .peek(System.out::println)
                .peek(System.out::println)
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(s));
    }

    /*演示toMap*/
    @Test
    public void test8() {
        apples.stream().collect(Collectors.toMap(Apple::getColor, Apple::getWeight, Integer::sum));
        System.out.println("======");
        Map<String, Integer> collect =
                apples.stream().collect(
                        Collectors.toMap(Apple::getColor, Apple::getWeight, Integer::sum));
        System.out.println(collect);
    }

    /*演示join+collect的用法*/
    @Test
    public void test9() {
        System.out.println(apples.stream()
                .map(a -> a.getColor() + ":" + a.getWeight())
                .collect(Collectors.joining("\n")));
        //注意：如果使用joining，则流中的元素必须提前转换为了String
    }

    /*演示flatMap joining的用法*/
    @Test
    public void test10() {
        //1. 构造一个嵌套的ArrayList
        ArrayList<Integer> list0 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        ArrayList<Integer> list1 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        ArrayList<Integer> list2 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
        }};
        ArrayList<ArrayList<Integer>> rootList = new ArrayList<ArrayList<Integer>>() {{
            add(list0);
            add(list1);
            add(list2);
        }};
        //2. 任务一：将ArrayList中的所有的元素的元素都输出成1行，使用；分割每一个元素
        System.out.println(rootList.stream().flatMap(Collection::stream).map(String::valueOf).collect(Collectors.joining(";")));
        System.out.println("======");
        //3. 任务二：将ArrayList以二维矩阵的形式输出，也就是每一个子元素的ArrayList都单独形成一行
        System.out.println(rootList.stream()
                .map(Collection::stream)
                .map(s -> s.map(String::valueOf).collect(Collectors.joining(",")))
                .collect(Collectors.joining("\n")));
    }
}

class Apple {
    private final int weight;
    private final String color;


    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }

    public int getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    public Apple(int weight, String color) {
        this.weight = weight;
        this.color = color;
    }
}
