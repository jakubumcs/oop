package genealogy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Klasa Family — reprezentuje rodzinę jako mapę osób.
 *
 * Zadanie 8: Zmieniliśmy typ mapy z Map<String, Person> na Map<String, List<Person>>,
 * żeby dopuścić wiele osób o tym samym imieniu i nazwisku.
 * HashMap to podstawowa implementacja mapy — szybkie wyszukiwanie po kluczu (O(1)).
 */
public class Family {

    /**
     * Zadanie 6 (oryginał): Map<String, Person>
     * Zadanie 8 (modyfikacja): Map<String, List<Person>>
     *
     * Klucz: "imię nazwisko" (np. "Jan Kowalski")
     * Wartość: lista osób o tym imieniu i nazwisku
     *
     * Map to interfejs — przechowuje pary klucz-wartość.
     * Każdy klucz jest unikalny; teraz jedna lista może mieć wiele Person.
     */
    private Map<String, List<Person>> people = new HashMap<>();

    /**
     * Zadanie 6: add() — dodaje osobę do rodziny.
     * Zadanie 7: zmodyfikowane do przyjmowania wariadycznej listy osób (Person...).
     * Zadanie 8: dostosowane do nowej struktury Map<String, List<Person>>.
     *
     * Varargs (Person... persons) — mechanizm Javy pozwalający przekazać
     * dowolną liczbę argumentów tego samego typu. Wewnątrz metody traktowane
     * jak tablica Person[]. Można wywołać: add(p1), add(p1, p2, p3) itd.
     *
     * @param persons jedna lub więcej osób do dodania
     */
    public void add(Person... persons) {
        for (Person person : persons) {
            // Klucz to "imię nazwisko"
            String key = person.getFirstName() + " " + person.getLastName();

            // getOrDefault() — pobiera istniejącą listę lub tworzy nową, gdy klucza nie ma.
            // Dzięki temu nie musimy pisać "if (map.containsKey(key)) { ... } else { ... }"
            List<Person> list = people.getOrDefault(key, new ArrayList<>());

            list.add(person);

            // Zapisujemy zaktualizowaną listę z powrotem pod kluczem
            people.put(key, list);
        }
    }

    /**
     * Zadanie 6: get() — zwraca osobę po kluczu "imię nazwisko".
     * Zadanie 8: zmodyfikowane — zwraca tablicę Person[] posortowaną
     * od najstarszej do najmłodszej (korzysta z Comparable zdefiniowanego
     * w Zadaniu 4).
     *
     * @param key klucz w formacie "imię nazwisko"
     * @return tablica osób pasujących do klucza, posortowana wg daty urodzin,
     *         lub null jeśli klucz nie istnieje
     */
    public Person[] get(String key) {
        List<Person> list = people.get(key);

        if (list == null) {
            return null; // brak osoby o takim kluczu
        }

        // Tworzymy kopię listy, żeby nie modyfikować oryginału w mapie
        List<Person> sorted = new ArrayList<>(list);

        // Collections.sort() korzysta z Person.compareTo() — sortuje od najstarszej (min) do najmłodszej (max)
        java.util.Collections.sort(sorted);

        // Konwertujemy List<Person> na Person[] (tablicę)
        // toArray(new Person[0]) — wzorzec Javy: przekazujemy pustą tablicę jako "szablon" typu
        return sorted.toArray(new Person[0]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Family {\n");
        for (Map.Entry<String, List<Person>> entry : people.entrySet()) {
            sb.append("  ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        sb.append("}");
        return sb.toString();
    }
}
