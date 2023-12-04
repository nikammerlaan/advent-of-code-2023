import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day04.Day04Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day04Test extends DayTest {

    public Day04Test() {
        super(new Day04Solution(), 4);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(13, result.part1Result().result());
        assertEquals(30, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(20855, result.part1Result().result());
        assertEquals(5489600, result.part2Result().result());
    }

}
