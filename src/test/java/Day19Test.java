import com.github.nikammerlaan.aoc.data.Result;
import com.github.nikammerlaan.aoc.days.day19.Day19Solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day19Test extends DayTest {

    public Day19Test() {
        super(new Day19Solution(), 19);
    }

    @Override
    protected void assertExampleResult(Result result) {
        assertEquals(19114, result.part1Result().result());
        assertEquals(167409079868000L, result.part2Result().result());
    }

    @Override
    protected void assertRealResult(Result result) {
        assertEquals(397061, result.part1Result().result());
        assertEquals(125657431183201L, result.part2Result().result());
    }

}
