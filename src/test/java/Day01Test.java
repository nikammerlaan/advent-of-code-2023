import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day01.Day01Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day01Test extends DayTest {

    public Day01Test() {
        super(new Day01Solution(), 1);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(142, result.part1Result().result());
        assertEquals(142, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(54927, result.part1Result().result());
        assertEquals(54581, result.part2Result().result());
    }

}
