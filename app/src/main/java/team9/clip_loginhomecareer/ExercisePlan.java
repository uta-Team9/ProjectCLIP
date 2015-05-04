package team9.clip_loginhomecareer;

import java.io.Serializable;


public class ExercisePlan implements Serializable
{
    private String exerciseName;
    private int caloriesBurned;
    private int durationOfWorkout;
    private String muscleTarget;


    public ExercisePlan() {
        exerciseName= "";
        caloriesBurned = 0;
        durationOfWorkout = 0;
        muscleTarget = "";
        databaseID = 0;
    }

    public ExercisePlan(int dbID) {
        exerciseName= "";
        caloriesBurned = 0;
        durationOfWorkout = 0;
        muscleTarget = "";
        databaseID = dbID;
    }

    public int getDatabaseID() {
        return databaseID;
    }

    public void setDatabaseID(int databaseID) {
        this.databaseID = databaseID;
    }

    private int databaseID;
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public int getDurationOfWorkout() {
        return durationOfWorkout;
    }

    public void setDurationOfWorkout(int durationOfWorkout) {
        this.durationOfWorkout = durationOfWorkout;
    }

    public String getMuscleTarget() {
        return muscleTarget;
    }

    public void setMuscleTarget(String muscleTarget) {
        this.muscleTarget = muscleTarget;
    }
}
