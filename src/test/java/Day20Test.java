import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.DaySolution;
import com.github.nikammerlaan.aoc.days.day20.Day20Solution;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class Day20Test extends DayTest {

    public Day20Test() {
        super(new Day20Solution(), 20);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(11687500, result.part1Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(912199500, result.part1Result().result());
        assertEquals(237878264003759L, result.part2Result().result());
    }

}
