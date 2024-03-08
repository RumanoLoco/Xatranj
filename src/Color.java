public interface Color {
    String getName();

    Color BLANCO = new Color() {
        @Override
        public String getName() {
            return "BLANCO";
        }
    };

    Color NEGRO = new Color() {
        @Override
        public String getName() {
            return "NEGRO";
        }
    };

    static boolean esBlanco(Color color) {
        return BLANCO.equals(color);
    }

    static boolean esNegro(Color color) {
        return NEGRO.equals(color);
    }
}
