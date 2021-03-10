
public class Recruiter extends Employee {

    double rating;

    Recruiter() {
        rating = 5.0;
    }

    public Double getRating() {
        return rating;
    }

    public Double evaluate(Job job, User user) {
        Double score= rating * user.getTotalScore();
        Request request = new Request(job, user, this, score);
        // compania la care lucreaza recruiterul si la care aplica userul
        Company company = Application.getApplication().getCompany(this.getNameOfCompany());
        // adaugarea userului care a aplicat in lista de observeri ai companiei
        company.addObserver(user);
        // managerul companiei
        Manager managerToSendRequest = company.getManager();
        // trimiterea cererii catre manager
        managerToSendRequest.addRequest(request);
        //cresterea ratingului recruiter-ului
        rating += 0.1;
        return score;
    }
}
