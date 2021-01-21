package by.nyurush.music;

import by.nyurush.music.dao.DaoHelper;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.dao.exception.DaoException;
import by.nyurush.music.dao.impl.AccountDaoImpl;

import javax.management.relation.RoleInfoNotFoundException;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Runner {
    public static void main(String[] args) throws DaoException {




























        /*
        2
        long result = Arrays.stream(new String[]{"JSE", "JDK", "J"}) // line 1
                .filter(s -> s.length() > 1)
                .filter(s -> s.contains("J"))
                .count();
        System.out.println(result);
         */


        /*
        0 0 JDK
        Predicate<String> predicate = s -> {
            int i = 0;
            boolean result = s.contains("JDK");
            System.out.print(i++ + " ");
            return result;
        };
        Arrays.stream(new String[]{"JRE", "JDK", "JVM", "JSE", "JDK"}).filter(predicate)
                .findFirst().ifPresent(System.out::print);*/


       /* IntStream numbers = new Random().ints(10, 0, 20);
        System.out.println(numbers);
        System.out.print(
                numbers.max()
        );*/


       /*
        14
        Stream.of(1).peek(
                ((Consumer<Integer>) (i -> i += 1))
                        .andThen(i -> i += 2))
                .forEach(System.out::print);
        Stream.of(1).map(
                ((Function<Integer, Integer>) (i -> i += 1))
                        .andThen(i -> i += 2))
                .forEach(System.out::print);*/




       /* Stream<String> strings = Arrays.stream(
                new String[]{"Java", "Standard", "Edition", "version", "14"});
        System.out.print(
                strings.filter(x -> x.length() <= 4).count()
        );*/
       /* Какой оператор должен быть вставлен вместо line 1, чтобы было определено
        число строк с длиной меньше чем 4? (выбрать один)
        a) strings.peek(x -> x.length() <= 4).count().get()
        b) strings.filter(x -> x.length() <= 4).count()
        c) strings.filter(x -> x.length() <= 4).mapToInt(Integer::valueOf).count()
        d) strings.map(x -> x.length() <= 4).count()*/



        /*
        DaoHelperFactory daoHelperFactory = new DaoHelperFactory();
        DaoHelper daoHelper = daoHelperFactory.create();
        AccountDaoImpl accountDao = daoHelper.createAccountDao();
        System.out.println(accountDao.findAll().toString());
        */


/*
        4, 190.0
        ///////////
        List<Order> orders = Arrays.asList(new Order(1, 50), new Order(5, 70),
                new Order(7, 70));
        Order order = orders.stream()
                .reduce(new Order(4, 0), (p1, p2) ->
                        new Order(p1.orderId, p1.amount += p2.amount));
        System.out.print(order);

*/

/*
        7, 70.0
        /////////
        List<Order> orders = Arrays.asList(new Order(1, 50), new Order(5, 70),
                new Order(7, 70));
        orders.stream()
                .reduce((p1, p2) -> p1.amount > p2.amount ? p1 : p2)
                .ifPresent(System.out::println);

*/


/*
        [1, 3]
        //////////
        Map<String, Integer> map = new HashMap<>();
        map.compute("y", (k, v) -> v==null ? 1 : 0);
        map.compute("z", (k, v) -> v==null ? 2 : 0);
        map.computeIfPresent("z", (k, v) -> v!=null ? 3 : 0);
        map.computeIfAbsent("y", v -> v!=null ? 4 : 0);
        System.out.println(map.values());

*/


    }



}

class Order {
    long orderId;
    double amount;
    public Order(long orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
    public String toString() {
        return orderId + ", " + amount ;
    }
}


