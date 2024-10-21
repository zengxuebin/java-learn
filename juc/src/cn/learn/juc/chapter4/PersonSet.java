package cn.learn.juc.chapter4;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 如果Person类是可变的，那么访问从PersonSet中获取的Person对象时，还需要额外的同步
 *
 * @author zengxuebin
 * @since 2024/10/21 00:14
 */
class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
public class PersonSet {
    private final Set<Person> mySet = new HashSet<>();

    public synchronized void addPerson(Person person) {
        mySet.add(person);
    }

    public synchronized boolean containsPerson(Person person) {
        return mySet.contains(person);
    }

    public Set<Person> getMySet() {
        return mySet;
    }

    public static void main(String[] args) {
        PersonSet personSet = new PersonSet();
        Person tom = new Person("tom");
        personSet.addPerson(tom);
        new Thread(() -> {
            Set<Person> set = personSet.getMySet();

            // 在线程中访问person对象
            for (Person person : set) {
                System.out.println(person);
            }
            // 模拟其他业务
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 再次访问person对象
            for (Person person : set) {
                System.out.println(person);
            }
        }).start();

        new Thread(() -> {
            // 修改person对象
            tom.setName("jack");
        }).start();
    }
}
