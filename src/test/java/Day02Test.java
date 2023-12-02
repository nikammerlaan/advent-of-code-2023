import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day02.Day02Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day02Test extends DayTest {

    public Day02Test() {
        super(new Day02Solution(), 2);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(8, result.part1Result().result());
        assertEquals(2286, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(2377, result.part1Result().result());
        assertEquals(71220, result.part2Result().result());
    }

}
