
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Manager extends Employee {

    ArrayList<Request> requests;

    Manager() {
        requests = new ArrayList<>();
    }

    public void addRequest(Request request) {
        requests.add(request);
    }

    public void process(Job job) {
        int nr = job.getNumberNeeded();
        Request aux = null;
        ArrayList<Request> chosen = new ArrayList<>();
        Iterator<Request> it = requests.iterator();
        while(it.hasNext()) {
            aux = (Request) it.next();
            if (((Job) (aux.getKey())).equals(job)) {
                chosen.add(aux);
            }
        }

        // sortam request-urile descrescator in functie de scor cu ajutorul unui compaator
        Collections.sort(chosen, Request.RequestComparator);
        it = chosen.iterator();
        Application app = Application.getApplication();
        Department dep = null;
        Company comp = app.getCompany(this.getNameOfCompany()); // compania care angajeaza

        // iteram printre request-uri si angajam primii nr aplicanti care indeplinesc conditiile
        while(nr > 0 && it.hasNext()) {
            aux = it.next();
            // verificam daca userul nu a fost angajat deja
            if (app.users.contains(aux.getValue1())) {
                // se verifica daca userul indeplineste conditiile necesare
                if (job.meetsRequirments((User) aux.getValue1())) {
                    dep = comp.getDepartment(job);
                    Employee employee = ((User) aux.getValue1()).convert();
                    employee.setNameOfCompany(comp.name);
                    employee.setSalary(job.getSalary());
                    comp.add(employee, dep);
                    app.remove((User) aux.getValue1());
                    nr--;
                    System.out.println("" + employee.getResume().getInformation().getName() + " a fost angajat pe pozitia " + job.jobName + " " + job.company.name);
                }
            }
        }
        if (nr == 0) {
            // s-au ocupat toate pozitiile disponibile pentru acest job
            job.setFlag(false);
            comp.notifyAllObservers("Postul " + job.jobName + " de la compania " + this.getNameOfCompany() + " a fost ocupat");
        } else {
            // nu s-au ocupat toate pozitiile, deci inca exista nu nr de locuri libere
            job.setNumberNeeded(nr);
        }

    }

    public ArrayList<Request> getRequests() {
        return requests;
    }
}
