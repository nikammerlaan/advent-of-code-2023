import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day03.Day03Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day03Test extends DayTest {

    public Day03Test() {
        super(new Day03Solution(), 3);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(4361, result.part1Result().result());
        assertEquals(467835, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(536202, result.part1Result().result());
        assertEquals(78272573, result.part2Result().result());
    }

}
