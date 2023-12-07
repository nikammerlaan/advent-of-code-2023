import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day07.Day07Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day07Test extends DayTest {

    public Day07Test() {
        super(new Day07Solution(), 7);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(6440, result.part1Result().result());
        assertEquals(5905, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(248569531, result.part1Result().result());
        assertEquals(250382098, result.part2Result().result());
    }

}
