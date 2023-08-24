import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class HorseTest {
    public Horse horse2;

    @BeforeEach
    void setUp() {
        horse2 = new Horse("Sten", 1, 1);
    }

    @Test
    void ifHorseNameIsNull() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", thrown.getMessage());
    }

    @ParameterizedTest
    @CsvSource({
            " ' ' ,1,1",
            "'    ' , 1,1"
    })
    void ifHorseNameIsEmpty(String name, double speed, double distance) {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, distance));
        assertEquals("Name cannot be blank.", thrown.getMessage());
    }

    @Test
    void ifHorseSpeedIsNegative() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Horse("Sta", -1, 1));
        assertEquals("Speed cannot be negative.", thrown.getMessage());
    }

    @Test
    void ifHorseDistanceIsNegative() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Horse("Stenam", 1, -1));
        assertEquals("Distance cannot be negative.", thrown.getMessage());
    }

    @Test
    void getName() {
        assertEquals("Sten", horse2.getName());
    }

    @Test
    void getSpeed() {
        assertEquals(1, horse2.getSpeed());
    }

    @Test
    void getDistance() {
        assertEquals(1, horse2.getDistance());
    }

    @Test
    void moveWithGetRandom() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse2.move();
            horseMockedStatic.verify(()-> Horse.getRandomDouble(0.2,0.9));
        }
    }

    @Test
    void move() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(1.0);
            double horseDistantWithMock = horse2.getDistance() + horse2.getSpeed() * Horse.getRandomDouble(0.2,0.9);
            horse2.move();
            assertEquals(horseDistantWithMock,horse2.getDistance());
        }
    }
}