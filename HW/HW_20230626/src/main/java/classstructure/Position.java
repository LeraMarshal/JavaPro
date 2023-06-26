package classstructure;

public enum Position {
    JUNIOR {
        @Override
        public Position next() {
            return MIDDLE;
        }
    },
    MIDDLE {
        @Override
        public Position next() {
            return SENIOR;
        }
    },
    SENIOR {
        @Override
        public Position next() {
            return SENIOR;
        }
    };

    public abstract Position next();
}
