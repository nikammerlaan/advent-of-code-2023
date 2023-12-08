import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day08.Day08Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day08Test extends DayTest {

    public Day08Test() {
        super(new Day08Solution(), 8);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(6, result.part1Result().result());
        // This input only works on part 1, so part 2's behavior is undefined
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(19667, result.part1Result().result());
        assertEquals(19185263738117L, result.part2Result().result());
    }

}
