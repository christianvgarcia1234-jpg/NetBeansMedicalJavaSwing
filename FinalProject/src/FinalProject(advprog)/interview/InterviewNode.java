package FinalProject.advprog.interview;

import java.util.function.Consumer;

public class InterviewNode {

    private String question;
    private InterviewNode yesNode;
    private InterviewNode noNode;
    private Consumer<Boolean> handler;

    public InterviewNode(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public InterviewNode getYesNode() {
        return yesNode;
    }

    public void setYesNode(InterviewNode yesNode) {
        this.yesNode = yesNode;
    }

    public InterviewNode getNoNode() {
        return noNode;
    }

    public void setNoNode(InterviewNode noNode) {
        this.noNode = noNode;
    }

    public Consumer<Boolean> getHandler() {
        return handler;
    }

    public void setHandler(Consumer<Boolean> handler) {
        this.handler = handler;
    }
}
