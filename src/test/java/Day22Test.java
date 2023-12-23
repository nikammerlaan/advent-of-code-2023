import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.DaySolution;
import com.github.nikammerlaan.aoc.days.day22.Day22Solution;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

public class Day22Test extends DayTest {

    public Day22Test() {
        super(new Day22Solution(), 22);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(5L, result.part1Result().result());
        assertEquals(7, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(515L, result.part1Result().result());
        assertEquals(101541, result.part2Result().result());
    }

}
