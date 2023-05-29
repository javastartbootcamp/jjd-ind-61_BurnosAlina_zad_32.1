package pl.javastart.streamstask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

public class ExampleTest {

    private StreamsTask streamsTask = new StreamsTask();
    private List<User> users;
    private List<Expense> expenses;

    @BeforeEach
    public void setup() {
        users = new ArrayList<>();
        users.add(new User(1L, "Alicja", 20));
        users.add(new User(2L, "Dominik", 15));
        users.add(new User(3L, "Patrycja", 25));
        users.add(new User(4L, "Marcin", 30));
        users.add(new User(5L, "Tomek", 18));
        users.add(new User(6L, "Damian", 26));

        expenses = new ArrayList<>();
        expenses.add(new Expense(1L, "Buty", new BigDecimal("149.99"), ExpenseType.WEAR));
        expenses.add(new Expense(1L, "Sałatka", new BigDecimal("14.99"), ExpenseType.FOOD));
        expenses.add(new Expense(2L, "Bluza", new BigDecimal("100"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Skarpetki", new BigDecimal("39"), ExpenseType.WEAR));
        expenses.add(new Expense(2L, "Pizza", new BigDecimal("25"), ExpenseType.FOOD));
    }

    @Test
    public void shouldFindWomen() {
        //when
        Collection<User> result = streamsTask.findWomen(users);
        //then
        List<User> expected = new ArrayList<>();
        expected.add(users.get(0));
        expected.add(users.get(2));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnCorrectForAvgMenAge() {
        //when
        Double result = streamsTask.averageMenAge(users);
        //then
        Assertions.assertEquals(22.25, result);
    }

    @Test
    public void shouldReturnExpensesByUserId() {
        //when
        Map<Long, List<Expense>> result = streamsTask.groupExpensesByUserId(expenses);
        //then
        Map<Long, List<Expense>> expected = new HashMap<>();
        expected.put(1L, new ArrayList<>(List.of(
                expenses.get(0),
                expenses.get(1)
        )));
        expected.put(2L, new ArrayList<>(List.of(
                expenses.get(2),
                expenses.get(3),
                expenses.get(4)
        )));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnExpensesByUser() {
        //when
        Map<User, List<Expense>> result = streamsTask.groupExpensesByUser(users, expenses);
        //then
        Map<User, List<Expense>> expected = new HashMap<>();
        expected.put(users.get(0), new ArrayList<>(List.of(
                expenses.get(0),
                expenses.get(1)
        )));
        expected.put(users.get(1), new ArrayList<>(List.of(
                expenses.get(2),
                expenses.get(3),
                expenses.get(4)
        )));
        Assertions.assertEquals(expected, result);
    }
}
