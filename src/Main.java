package genealogy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Klasa główna — demonstruje działanie wszystkich zadań.
 */
public class Main {

    public static void main(String[] args) {

        // ── Zadanie 1: Lista obiektów Person ────────────────────────────────
        System.out.println("=== Zadanie 1: Lista osób ===");

        // List<Person> — interfejs listy z zachowaną kolejnością, dopuszcza duplikaty.
        // ArrayList to najpopularniejsza implementacja listy (tablica dynamiczna).
        List<Person> people = new ArrayList<>();

        Person jan   = new Person("Jan",   "Kowalski", LocalDate.of(1950, 3, 15));
        Person maria = new Person("Maria", "Kowalski", LocalDate.of(1952, 7, 20));
        Person adam  = new Person("Adam",  "Kowalski", LocalDate.of(1975, 1, 10));
        Person ewa   = new Person("Ewa",   "Kowalski", LocalDate.of(1978, 11, 5));
        Person piotr = new Person("Piotr", "Kowalski", LocalDate.of(1980, 6, 30));
        // Osoba z duplikatem klucza (do zadania 8)
        Person jan2  = new Person("Jan",   "Kowalski", LocalDate.of(1955, 8, 22));

        people.addAll(Arrays.asList(jan, maria, adam, ewa, piotr, jan2));
        people.forEach(System.out::println);
        // forEach + referencja do metody (::) — skrót dla lambda: p -> System.out.println(p)

        // ── Zadanie 2: adopt() ──────────────────────────────────────────────
        System.out.println("\n=== Zadanie 2: adopt() ===");

        boolean r1 = jan.adopt(adam);
        boolean r2 = jan.adopt(ewa);
        boolean r3 = jan.adopt(piotr);
        boolean r4 = jan.adopt(adam); // duplikat — powinien zwrócić false

        System.out.println("Dodano Adama: "  + r1);  // true
        System.out.println("Dodano Ewę: "    + r2);  // true
        System.out.println("Dodano Piotra: " + r3);  // true
        System.out.println("Dodano Adama ponownie: " + r4); // false — Set odrzuca duplikat

        // ── Zadanie 3: getYoungestChild() ───────────────────────────────────
        System.out.println("\n=== Zadanie 3: getYoungestChild() ===");

        Person youngest = jan.getYoungestChild();
        System.out.println("Najmłodsze dziecko Jana: " + youngest); // Piotr (ur. 1980)

        Person noKids = new Person("Bezdzietny", "Testowy", LocalDate.of(1990, 1, 1));
        System.out.println("Najmłodsze dziecko Bezdzietnego: " + noKids.getYoungestChild()); // null

        // ── Zadanie 4: Comparable (compareTo) ──────────────────────────────
        System.out.println("\n=== Zadanie 4: Comparable ===");

        // compareTo() — Jan urodzony 1950, Adam 1975, więc Jan < Adam
        int cmp = jan.compareTo(adam);
        System.out.println("jan.compareTo(adam) = " + cmp + " (ujemne → Jan starszy)");

        // ── Zadanie 5: getChildren() ────────────────────────────────────────
        System.out.println("\n=== Zadanie 5: getChildren() posortowane ===");

        List<Person> sorted = jan.getChildren();
        sorted.forEach(System.out::println);
        // Oczekiwana kolejność: Adam (1975), Ewa (1978), Piotr (1980)

        // ── Zadania 6, 7, 8: Family ─────────────────────────────────────────
        System.out.println("\n=== Zadania 6-8: Family ===");

        Family family = new Family();

        // Zadanie 7: varargs — dodajemy kilka osób naraz jednym wywołaniem
        family.add(jan, maria, adam, ewa, piotr);

        // Zadanie 8: dodajemy drugiego "Jan Kowalski" — mapa List<Person> to obsłuży
        family.add(jan2);

        System.out.println(family);

        // get() — zwraca tablicę Person[] posortowaną od najstarszej
        System.out.println("Wynik get(\"Jan Kowalski\"):");
        Person[] jans = family.get("Jan Kowalski");
        if (jans != null) {
            for (Person p : jans) {
                System.out.println("  " + p);
            }
        }
        // Oczekiwane: jan (1950) przed jan2 (1955)

        System.out.println("\nWynik get(\"Adam Kowalski\"):");
        Person[] adams = family.get("Adam Kowalski");
        if (adams != null) {
            Arrays.stream(adams).forEach(p -> System.out.println("  " + p));
        }

        System.out.println("\nWynik get(\"Nieistniejący Ktoś\"): " + family.get("Nieistniejący Ktoś"));
    }
}
