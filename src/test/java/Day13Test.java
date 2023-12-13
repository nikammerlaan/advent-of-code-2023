import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day13.Day13Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day13Test extends DayTest {

    public Day13Test() {
        super(new Day13Solution(), 13);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(405, result.part1Result().result());
        assertEquals(400, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(33195, result.part1Result().result());
        assertEquals(31836, result.part2Result().result());
    }

}
