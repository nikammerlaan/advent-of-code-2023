import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day21.Day21Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day21Test extends DayTest {

    public Day21Test() {
        super(new Day21Solution(), 21);
    }

    @Override
    protected void assertExampleResult(Result result) {
        // none of the examples are given here
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(3572L, result.part1Result().result());
        assertEquals(594606492802848L, result.part2Result().result());
    }

}
