import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day23.Day23Solution;
import com.github.nikammerlaan.aoc.days.day24.Day24Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day24Test extends DayTest {

    public Day24Test() {
        super(new Day24Solution(), 24);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(47L, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(23760, result.part1Result().result());
        assertEquals(888708704663413L, result.part2Result().result());
    }

}
