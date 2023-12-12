import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day12.Day12Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day12Test extends DayTest {

    public Day12Test() {
        super(new Day12Solution(), 12);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(21L, result.part1Result().result());
        assertEquals(525152L, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(7599L, result.part1Result().result());
        assertEquals(15454556629917L, result.part2Result().result());
    }

}
