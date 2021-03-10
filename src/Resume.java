
import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.TreeSet;

public class Resume {
        private Information information;
        private TreeSet<Education> education;
        private TreeSet<Experience> experience;
        private Resume() {
            information = new Information();
            education = new TreeSet<>();
            experience = new TreeSet<>();
        }
        private Resume(ResumeBuilder builder) throws ResumeIncompleteException {
            if (builder.information == null)
                throw new ResumeIncompleteException("ResumeIncompleteException");
            if (builder.education.isEmpty())
                throw new ResumeIncompleteException("ResumeIncompleteException");
            this.information = builder.information;
            this.education = builder.education;
            this.experience = builder.experience;
        }

        public void setInformatii(Information informatii) {
            this.information = informatii;
        }

        public void addEducation(Education education) {
            (this.education).add(education);
        }

        public void addExperience(Experience experience) {
            (this.experience).add(experience);
        }

        public Information getInformation() {
            return information;
        }

        public TreeSet<Education> getEducation() {
            return education;
        }

        public TreeSet<Experience> getExperience() {
            return experience;
        }

        public int getYearsOfExperience() {
            Period period = null;
            if (experience.isEmpty()) return 0;
            for (Experience exp : experience) {
                if (exp.getDataSfarsit() != null) {
                    period = Period.between(exp.getDataInceput(), exp.getDataSfarsit());
                } else {
                    period = Period.between(exp.getDataInceput(), LocalDate.now());
                }
            }
            if (period.getMonths() > 0)
                return period.getYears() + 1;
            return  period.getYears();
        }

        public static class ResumeBuilder {
            private Information information;
            private TreeSet<Education> education;
            private TreeSet<Experience> experience;

            public ResumeBuilder() {
                information = null;
                education = new TreeSet<>();
                experience = new TreeSet<>();
            }
            public ResumeBuilder(Information information) {
                this.information = information;
            }

            public ResumeBuilder education(TreeSet<Education> education) {
                this.education = education;
                return this;
            }

            public ResumeBuilder experience(TreeSet<Experience> experience) {
                this.experience = experience;
                return this;
            }

            public Resume build() throws ResumeIncompleteException {
                return new Resume(this);
            }
        }

    class ResumeIncompleteException extends Throwable {
        public ResumeIncompleteException(String msg) {
            super(msg);
        }
    }

    public String toString() {
        String ed = "";
        String ex = "";
        Iterator it = education.iterator();
        while(it.hasNext()) {
            Education educ = (Education) it.next();
            ed += educ.toString();
        }
        it = experience.iterator();
        while(it.hasNext()) {
            Experience exp = (Experience) it.next();
            ex += exp.toString();
        }
        return "Resume\n\n" +
                "Infomation: { " + "\n" +
                    "\tname: " + information.getName() + "\n" +
                    "\temail: " + information.getEmail() + "\n" +
                    "\tphone: " + information.getPhone() + "\n" +
                    "\tLanguages" + information.getLanguages().toString() + "\n" +
                    "\t}" + "\n\n" +
                "Education: [ \n" + ed + "]\n\n" +
                "Experience: [ \n" + ex + "]\n";
    }
}
