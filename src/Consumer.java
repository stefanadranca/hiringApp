
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public abstract class Consumer {
    Resume resume;
    ArrayList<Consumer> acquaintances;
    private String password;
    private String username;

    Consumer() {
        acquaintances = new ArrayList<>();
    }
    public void setAcquaintances(ArrayList<Consumer> acquaintances) {
        this.acquaintances = acquaintances;
    }

    public ArrayList<Consumer> getAcquaintances() {
        return acquaintances;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public Resume getResume() {
        return resume;
    }

    public void add(Education education) {
        resume.addEducation(education);
    }

    public void add(Experience experience) {
        resume.addExperience(experience);
    }

    public void add(Consumer consumer) {
        acquaintances.add(consumer);
    }

    public void remove(Consumer consumer) {
        acquaintances.remove(consumer);
    }

    public Integer getGraduationYear() {
        TreeSet<Education> education = resume.getEducation();
        for (Education ed:education) {
            if (ed.getNivel().equals("college")) {
                if (ed.getDataSfarsit() != null)
                    return ed.getDataSfarsit().getYear();
            }
        }
        return null;
    }

    public Double meanGPA() {
        Double sum = 0.0;
        for (Education ed : resume.getEducation()) {
            sum += ed.getMedie();
        }
        return sum / (resume.getEducation()).size();
    }

    public int getDegreeInFriendship(Consumer consumer) {
        HashMap<Consumer, Integer> visited = new HashMap<>();
        LinkedList<Consumer> queue = new LinkedList<>();
        visited.put(this, 0);
        queue.add(this);
        Consumer aux;
        while (!queue.isEmpty()) {
            aux = queue.poll();
            if (aux.equals(consumer))
                return visited.get(aux);
            for (Consumer cons : aux.acquaintances) {
                if (!visited.containsKey(cons)) {
                    visited.put(cons, visited.get(aux) + 1);
                    queue.add(cons);
                }
            }
        }
        return -1;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        if (resume != null) {
            return resume.getInformation().getName();
        }
        return null;
    }

    public String getEmail() {
        if (resume != null) {
            return resume.getInformation().getEmail();
        }
        return null;
    }
}
