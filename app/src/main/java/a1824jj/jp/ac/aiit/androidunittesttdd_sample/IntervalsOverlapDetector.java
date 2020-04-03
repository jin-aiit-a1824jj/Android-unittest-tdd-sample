package a1824jj.jp.ac.aiit.androidunittesttdd_sample;

public class IntervalsOverlapDetector {

    public boolean isOverlap(Interval interval1, Interval interval2){
        return interval1.getEnd() > interval2.getStart()
                && interval1.getStart() <= interval2.getEnd();
    }


    public static class Interval {
        private final int mStart;
        private final int mEnd;

        public Interval(int start, int end){
            if(start >= end) {
                throw new IllegalArgumentException("invalid interval range");
            }

            this.mStart = start;
            this.mEnd = end;
        }

        public int getStart() {
            return mStart;
        }

        public int getEnd() {
            return mEnd;
        }
    }

}
