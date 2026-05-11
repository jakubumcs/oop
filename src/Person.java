package genealogy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Klasa reprezentująca osobę w drzewie genealogicznym.
 *
 * Zadanie 4: Implementujemy interfejs Comparable<Person>.
 * Comparable to interfejs z jedną metodą compareTo(), który pozwala obiektom
 * "porównywać się" między sobą. Dzięki temu można je sortować np. przez
 * Collections.sort() lub Collections.min() bez podawania osobnego Comparatora.
 * Zasada: compareTo() zwraca wartość ujemną gdy "this" < other, 0 gdy równe,
 * dodatnią gdy "this" > other.
 */
public class Person implements Comparable<Person> {

    // Zadanie 1: prywatne pola - imię, nazwisko (String) i data urodzin (LocalDate)
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    /**
     * Zadanie 2: zbiór (Set) dzieci.
     * Set to kolekcja bez duplikatów — ta sama osoba nie może być dodana
     * dwa razy jako dziecko. HashSet to najszybsza implementacja (O(1) dodawanie).
     */
    private Set<Person> children = new HashSet<>();

    // Konstruktor
    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    // Gettery
    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName; }
    public LocalDate getBirthDate() { return birthDate; }

    /**
     * Zadanie 2: metoda adopt() — dodaje dziecko do zbioru children tego rodzica.
     * Zwraca true jeśli dziecko zostało dodane (nie było wcześniej w zbiorze),
     * false jeśli już istniało (Set automatycznie odrzuca duplikaty).
     *
     * @param child osoba przyjmowana jako dziecko
     * @return czy dodanie się powiodło
     */
    public boolean adopt(Person child) {
        return children.add(child);
        // Set.add() zwraca boolean — true = element był nowy, false = już istniał
    }

    /**
     * Zadanie 3: getYoungestChild() — zwraca najmłodsze dziecko.
     * Jeżeli osoba nie ma dzieci, zwraca null.
     *
     * Zadanie 4: korzystamy tu z Collections.max(), ponieważ w naszym
     * compareTo() "późniejsza data = większa osoba" — więc max() zwróci
     * osobę urodzoną najpóźniej, czyli najmłodszą.
     */
    public Person getYoungestChild() {
        if (children.isEmpty()) {
            return null; // brak dzieci — zwracamy null zgodnie z treścią zadania
        }
        // Collections.max() używa naszego compareTo() zdefiniowanego w Zadaniu 4
        return Collections.max(children);
    }

    /**
     * Zadanie 4: implementacja metody compareTo() z interfejsu Comparable.
     * Porównujemy osoby według daty urodzin — wcześniej urodzona = "mniejsza".
     * LocalDate.compareTo() działa identycznie (zwraca ujemną/0/dodatnią).
     */
    @Override
    public int compareTo(Person other) {
        return this.birthDate.compareTo(other.birthDate);
        // Delegujemy porównanie do LocalDate, który już wie jak porównywać daty
    }

    /**
     * Zadanie 5: getChildren() — zwraca listę dzieci posortowaną od najstarszego
     * do najmłodszego (tj. od najwcześniejszej daty urodzin).
     *
     * new ArrayList<>(children) — tworzymy listę z elementów zbioru, bo Set
     * nie ma metody sort(). Lista ma sort(), a Collections.sort() korzysta
     * z naszego compareTo() (Comparable), więc nie trzeba pisać Comparatora.
     */
    public List<Person> getChildren() {
        List<Person> sorted = new ArrayList<>(children);
        // Collections.sort() sortuje w porządku "naturalnym" — czyli wg compareTo()
        // Najstarszy (najmniejsza data) będzie pierwszy.
        Collections.sort(sorted);
        return sorted;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (ur. " + birthDate + ")";
    }
}
