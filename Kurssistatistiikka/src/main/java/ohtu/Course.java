package ohtu;

public class Course {
    
    private String name;
    private String term;
    private int[] exercises;

    

    /**
     * @param exercises the exercises to set
     */
    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param term the term to set
     */
    public void setTerm(String term) {
        this.term = term;
    }

    /**
     * @return the exercises
     */
    public int[] getExercises() {
        return exercises;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the term
     */
    public String getTerm() {
        return term;
    }

}