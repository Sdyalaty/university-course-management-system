public abstract class Grade {
    protected String studentId;
    protected String courseCode;

    public Grade(String studentId, String courseCode) {
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public abstract double getGradePoint();
    public abstract String getDisplayValue();

    public static class LetterGrade extends Grade {
        private final char letter;

        public LetterGrade(String studentId, String courseCode, char letter) {
            super(studentId, courseCode);
            this.letter = letter;
        }

        @Override
        public double getGradePoint() {
            switch (letter) {
                case 'A': return 4.0;
                case 'B': return 3.0;
                case 'C': return 2.0;
                case 'D': return 1.0;
                default: return 0.0;
            }
        }

        @Override
        public String getDisplayValue() {
            return String.valueOf(letter);
        }
    }

    public static class PassFailGrade extends Grade {
        private boolean passed;

        public PassFailGrade(String studentId, String courseCode, boolean passed) {
            super(studentId, courseCode);
            this.passed = passed;
        }

        @Override
        public double getGradePoint() {
            return passed ? 4.0 : 0.0;
        }

        @Override
        public String getDisplayValue() {
            return passed ? "Pass" : "Fail";
        }
    }

    public static class NumericalGrade extends Grade {
        private double score;

        public NumericalGrade(String studentId, String courseCode, double score) {
            super(studentId, courseCode);
            this.score = score;
        }

        @Override
        public double getGradePoint() {
            if (score >= 90) return 4.0;
            if (score >= 80) return 3.0;
            if (score >= 70) return 2.0;
            if (score >= 60) return 1.0;
            return 0.0;
        }

        @Override
        public String getDisplayValue() {
            return String.valueOf(score);
        }
    }
}
