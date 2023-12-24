import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day23.Day23Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day23Test extends DayTest {

    public Day23Test() {
        super(new Day23Solution(), 23);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(84, result.part1Result().result());
        assertEquals(154, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(2238, result.part1Result().result());
        assertEquals(6398, result.part2Result().result());
    }

}
