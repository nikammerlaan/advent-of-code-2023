import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day11.Day11Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day11Test extends DayTest {

    public Day11Test() {
        super(new Day11Solution(), 11);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(374, result.part1Result().result());
        // Part 2 isn't listed for this input, so I'll leave out the assertion
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(9681886L, result.part1Result().result());
        assertEquals(791134099634L, result.part2Result().result());
    }

}
