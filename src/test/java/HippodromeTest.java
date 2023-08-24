import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class HippodromeTest {
    @Mock
    ArrayList<Horse> horses;
    ArrayList<Horse> emptyHorses = new ArrayList<>();

    @Test
    void whenListIsNull(){
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", thrown.getMessage());
    }

    @Test
    void whenListIsEmpty(){

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyHorses));
        assertEquals("Horses cannot be empty.", thrown.getMessage());
    }


    @Test
    void getHorses() {
        List<Horse> horses = List.of(
                new Horse("Буцефал", 2.4),
                new Horse("Туз Пик", 2.5),
                new Horse("Зефир", 2.6),
                new Horse("Пожар", 2.7),
                new Horse("Лобстер", 2.8),
                new Horse("Пегас", 2.9),
                new Horse("Вишня", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void move() {
        List<Horse> horses1 = new ArrayList<>();
        for(int i = 0; i < 50; i++) horses1.add(mock(Horse.class));
        new Hippodrome(horses1).move();

        for(Horse horse: horses1) Mockito.verify(horse).move();
    }

    @Test
    void getWinner() {
        List<Horse> horses = List.of(
                new Horse("Буцефал", 2.4,2),
                new Horse("Туз Пик", 2.5, 76),
                new Horse("Зефир", 2.6, 92),
                new Horse("Пожар", 2.7),
                new Horse("Лобстер", 2.8),
                new Horse("Пегас", 2.9),
                new Horse("Вишня", 3)
        );
        Hippodrome hippodrome = new Hippodrome(horses);
        assertSame(horses.get(2), hippodrome.getWinner());
    }
}