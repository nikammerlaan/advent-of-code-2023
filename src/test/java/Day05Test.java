import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day05.Day05Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day05Test extends DayTest {

    public Day05Test() {
        super(new Day05Solution(), 5);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(35L, result.part1Result().result());
        assertEquals(46L, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(111627841L, result.part1Result().result());
        assertEquals(69323688L, result.part2Result().result());
    }

}
