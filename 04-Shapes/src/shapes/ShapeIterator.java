package shapes;

/**
 * An iterator for shapes arrays
 */
public class ShapeIterator implements Iterator {

        private final Shape[] shapes;
        private final int size;
        private int index;

        /**
         * Constructor
         * @param shapes shapes array to iterate
         * @param size size of array
         */
        public ShapeIterator(Shape[] shapes, int size) {
            this.shapes = shapes;
            this.size = size;
            this.init();
        }

        @Override
        public void init() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public Shape next() {
            return shapes[index++];
        }
    }