package java_annotation;

class CalculateInterest {
    @interface TODO {
        String value();
    }

    @TODO("Figure out the amount of interest per match")
    public void calculateInterest(float amount, float rate) {
    
    }
}
