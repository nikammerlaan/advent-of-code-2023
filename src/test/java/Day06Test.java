import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day06.Day06Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day06Test extends DayTest {

    public Day06Test() {
        super(new Day06Solution(), 6);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(288L, result.part1Result().result());
        assertEquals(71503L, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(1155175L, result.part1Result().result());
        assertEquals(35961505L, result.part2Result().result());
    }

}
