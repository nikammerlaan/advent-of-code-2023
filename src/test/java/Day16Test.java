import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day16.Day16Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day16Test extends DayTest {

    public Day16Test() {
        super(new Day16Solution(), 16);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(46, result.part1Result().result());
        assertEquals(51, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(7496, result.part1Result().result());
        assertEquals(7932, result.part2Result().result());
    }

}
