package pl.javastart.streamstask;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamsTask {

    // metoda powinna zwracać listę kobiet (sprawdzając, czy imię kończy się na "a")
    List<User> findWomen(Collection<User> users) {
        return users.stream()
                .filter(user -> user.getName().endsWith("a"))
                .collect(Collectors.toList());
    }

    // metoda powinna zwracać średni wiek mężczyzn (sprawdzając, czy imię nie kończy się na "a")
    Double averageMenAge(Collection<User> users) {
        return users.stream()
                .filter(user -> !user.getName().endsWith("a"))
                .mapToInt(User::getAge)
                .average()
                .orElseThrow(NoSuchElementException::new);
    }

    // metoda powinna zwracać wydatki zgrupowane po ID użytkownika
    Map<Long, List<Expense>> groupExpensesByUserId(List<Expense> expenses) {
        return expenses.stream()
                .collect(Collectors.groupingBy(Expense::getUserId));
    }

    // metoda powinna zwracać wydatki zgrupowane po użytkowniku
    // podobne do poprzedniego, ale trochę trudniejsze
    Map<User, List<Expense>> groupExpensesByUser(Collection<User> users, List<Expense> expenses) {
        Map<Long, User> userMap = users.stream()
                .collect(Collectors.toMap(User::getId, Function.identity()));
        return expenses.stream()
                .collect(Collectors.groupingBy(expense -> userMap.get(expense.getUserId())));
    }
}
