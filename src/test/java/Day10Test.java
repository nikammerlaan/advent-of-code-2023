import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day10.Day10Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day10Test extends DayTest {

    public Day10Test() {
        super(new Day10Solution(), 10);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(80, result.part1Result().result());
        assertEquals(10, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(6923, result.part1Result().result());
        assertEquals(529, result.part2Result().result());
    }

}
