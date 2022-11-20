package etc.vn.work_management.model.work;

public class Task extends Work {
    private int priority;
    private int estimate;
    private int remainingWork;
    private int taskPoint;
    private Activity activity;

    public Task(Long id, String title, String description, Long idUser, Status state,
                int priority, int estimate, int remainingWork, int taskPoint,
                Activity activity) {
        this.id = id;
        this.title=title;
        this.description=description;
        this.idUser=idUser;
        this.state=state;
        this.priority = priority;
        this.estimate = estimate;
        this.remainingWork = remainingWork;
        this.taskPoint = taskPoint;
        this.activity = activity;
    }

    public Task() {
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public int getRemainingWork() {
        return remainingWork;
    }

    public void setRemainingWork(int remainingWork) {
        this.remainingWork = remainingWork;
    }

    public int getTaskPoint() {
        return taskPoint;
    }

    public void setTaskPoint(int taskPoint) {
        this.taskPoint = taskPoint;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Task{" +
                " title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", state=" + state +
                ", priority=" + priority +
                ", estimate=" + estimate +
                ", remainingWork=" + remainingWork +
                ", taskPoint=" + taskPoint +
                ", activity=" + activity +
                '}';
    }
}
