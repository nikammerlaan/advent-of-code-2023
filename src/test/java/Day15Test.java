import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day15.Day15Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day15Test extends DayTest {

    public Day15Test() {
        super(new Day15Solution(), 15);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(1320, result.part1Result().result());
        assertEquals(145, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(515974, result.part1Result().result());
        assertEquals(265894, result.part2Result().result());
    }

}
