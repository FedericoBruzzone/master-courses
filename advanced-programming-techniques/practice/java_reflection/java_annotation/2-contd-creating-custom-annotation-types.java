package java_annotation;

class CalculateInterest {
    @interface GroupTODO {
        public enum Severity {CRITICAL, IMPORTANT, TRIVIAL, DOCUMENTATION};
        Severity severity() default Severity.IMPORTANT;
        String item();
        String assignedTo();
    }

    @GroupTODO(
        severity=GroupTODO.Severity.CRITICAL,
        item="Figure out the amount of interest per month",
        assignedTo="Walter Cazzola"
    )
    public void calculateInterest(float amount, float rate) {
        
    }
}