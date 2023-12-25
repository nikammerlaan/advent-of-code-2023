package com.github.nikammerlaan.aoc.days.day24;

import com.github.nikammerlaan.aoc.days.AbstractDaySolution;

import java.util.Arrays;
import java.util.List;

public class Day24Solution extends AbstractDaySolution<List<Hailstone>> {

    @Override
    protected Object solvePart1(List<Hailstone> hailstones) {
        for(var hailstone : hailstones) {
            if(hailstone.vx() == 0) {
                System.out.println(hailstone);
            }
        }
        int count = 0;
        for(int i = 0; i < hailstones.size(); i++) {
            var a = hailstones.get(i);
            for(int j = i + 1; j < hailstones.size(); j++) {
                var b = hailstones.get(j);
                if(a.intersects2d(b)) {
                    count++;
                }
            }
        }
        return count;
    }

    @Override
    protected Object solvePart2(List<Hailstone> hailstones) {
        var h1 = hailstones.get(0);
        var h2 = hailstones.get(1);

        int range = 500;
        for (int vx = -range; vx <= range; vx++) {
            for (int vy = -range; vy <= range; vy++) {
                for (int vz = -range; vz <= range; vz++) {
                    if (vx == 0 || vy == 0 || vz == 0) {
                        continue;
                    }

                    // Find starting point for rock that will intercept first two hailstones (x,y) on this trajectory

                    // simultaneous linear equation (from part 1):
                    // H1:  x = A + a*t   y = B + b*t
                    // H2:  x = C + c*u   y = D + d*u
                    //
                    //  t = [ d ( C - A ) - c ( D - B ) ] / ( a * d - b * c )
                    //
                    // Solve for origin of rock intercepting both hailstones in x,y:
                    //     x = A + a*t - vx*t   y = B + b*t - vy*t
                    //     x = C + c*u - vx*u   y = D + d*u - vy*u

                    long A = h1.x(), a = h1.vx() - vx;
                    long B = h1.y(), b = h1.vy() - vy;
                    long C = h2.x(), c = h2.vx() - vx;
                    long D = h2.y(), d = h2.vy() - vy;

                    // skip if division by 0
                    if (c == 0 || (a * d) - (b * c) == 0) {
                        continue;
                    }

                    // Rock intercepts H1 at time t
                    long t = (d * (C - A) - c * (D - B)) / ((a * d) - (b * c));

                    // Calculate starting position of rock from intercept point
                    long x = h1.x() + h1.vx() * t - vx * t;
                    long y = h1.y() + h1.vy() * t - vy * t;
                    long z = h1.z() + h1.vz() * t - vz * t;


                    // check if this rock throw will hit all hailstones

                    boolean hitall = true;
                    for (var h : hailstones) {
                        long u;
                        if (h.vx() != vx) {
                            u = (x - h.x()) / (h.vx() - vx);
                        } else if (h.vy() != vy) {
                            u = (y - h.y()) / (h.vy() - vy);
                        } else if (h.vz() != vz) {
                            u = (z - h.z()) / (h.vz() - vz);
                        } else {
                            throw new IllegalStateException();
                        }

                        if ((x + u * vx != h.x() + u * h.vx()) || (y + u * vy != h.y() + u * h.vy()) || (z + u * vz != h.z() + u * h.vz())) {
                            hitall = false;
                            break;
                        }
                    }

                    if (hitall) {
                        return x + y + z;
                    }
                }
            }
        }

        throw new IllegalStateException();
    }

    @Override
    protected List<Hailstone> parseInput(String rawInput) {
        return rawInput.lines()
            .map(this::parseVector)
            .toList();
    }

    private Hailstone parseVector(String rawInput) {
        var numbers = Arrays.stream(rawInput.split("[ @,]+"))
            .map(Long::parseLong)
            .toArray(Long[]::new);
        return new Hailstone(
            numbers[0],
            numbers[1],
            numbers[2],
            numbers[3],
            numbers[4],
            numbers[5]
        );
    }

}
