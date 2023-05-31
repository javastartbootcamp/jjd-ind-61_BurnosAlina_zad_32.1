package pl.javastart.streamstask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StreamsTaskTest {

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
        List<User> expected = List.of(users.get(0), users.get(2));
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void shouldReturnEmptyList() {
        users = new ArrayList<>();
        //when
        Collection<User> result = streamsTask.findWomen(users);
        //then
        List<User> expected = List.of();
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
    public void shouldThrowNoSuchElementExceptionForAvgMenAge() {
        users = new ArrayList<>();
        //when/then
        Assertions.assertThrows(NoSuchElementException.class, () -> streamsTask.averageMenAge(users));
    }

    @Test
    public void shouldReturnExpensesByUserId() {
        //when
        Map<Long, List<Expense>> result = streamsTask.groupExpensesByUserId(expenses);
        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result.get(1L)).isEqualTo(new ArrayList<>(List.of(
                expenses.get(0),
                expenses.get(1)
        )));
        assertThat(result.get(2L)).isEqualTo(new ArrayList<>(List.of(
                expenses.get(2),
                expenses.get(3),
                expenses.get(4)
        )));
    }

    @Test
    public void shouldReturnEmptyMapForNoExpenses() {
        expenses = new ArrayList<>();
        //when
        Map<Long, List<Expense>> result = streamsTask.groupExpensesByUserId(expenses);
        //then
        assertThat(result.size()).isEqualTo(0);
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

    @Test
    public void shouldThrowNullPointerExceptionForNoUsers() {
        users = new ArrayList<>();
        //when/then
        Assertions.assertThrows(NullPointerException.class, () -> streamsTask.groupExpensesByUser(users, expenses));
    }

    @Test
    public void shouldReturnEmptyMapForNoExpensesSearchingByUser() {
        expenses = new ArrayList<>();
        //when
        Map<User, List<Expense>> result = streamsTask.groupExpensesByUser(users, expenses);
        //then
        assertThat(result.size()).isEqualTo(0);
    }
}
