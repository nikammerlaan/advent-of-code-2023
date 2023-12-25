package com.github.nikammerlaan.aoc.days.day24;

public record Hailstone(
    long x,
    long y,
    long z,
    long vx,
    long vy,
    long vz
) {

    public boolean intersects2d(Hailstone other) {
        // Check for vertical lines (vx = 0)
        if (this.vx == 0 && other.vx == 0) {
            return false; // Both lines are vertical and parallel
        } else if (this.vx == 0) {
            // Calculate intersection point using other's line equation
            double intersectY = (double) other.vy / other.vx * (this.x - other.x) + other.y;
            return isWithinTestArea(this.x, intersectY);
        } else if (other.vx == 0) {
            // Calculate intersection point using this line equation
            double intersectY = (double) this.vy / this.vx * (other.x - this.x) + this.y;
            return isWithinTestArea(other.x, intersectY);
        }

        // Calculate the line coefficients for both hailstones
        double m1 = (double) this.vy / this.vx;
        double c1 = this.y - m1 * this.x;

        double m2 = (double) other.vy / other.vx;
        double c2 = other.y - m2 * other.x;

        // Check if lines are parallel
        if (m1 == m2) {
            return false; // Parallel lines do not intersect
        }

        // Calculate intersection point
        double intersectX = (c2 - c1) / (m1 - m2);
        double intersectY = m1 * intersectX + c1;

        // Calculate intersection time for each hailstone
        double t1 = (intersectX - this.x) / this.vx;
        double t2 = (intersectX - other.x) / other.vx;

        // Check if intersection happens in the past for either hailstone
        if (t1 < 0 || t2 < 0) {
            return false; // Intersection in the past
        }

        return isWithinTestArea(intersectX, intersectY);
    }

    private boolean isWithinTestArea(double x, double y) {
        return x >= 200000000000000L && x <= 400000000000000L &&
            y >= 200000000000000L && y <= 400000000000000L;
    }

    private boolean isWithinExampleTestArea(double x, double y) {
        return x >= 7 && x <= 27 &&
            y >= 7 && y <= 27;
    }

}
