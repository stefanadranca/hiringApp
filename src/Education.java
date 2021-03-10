
import java.time.LocalDate;
import java.util.StringTokenizer;

public class Education implements Comparable<Education>{
    private String institutie;
    private String nivel;
    private double medie;
    private LocalDate dataInceput;
    private LocalDate dataSfarsit;

    Education() {
        dataInceput = null;
        dataSfarsit = null;
    }

    // utilizam acest constructor la generarea datelor random
    Education(String institutie, String nivel, double medie, String dataInceput, String dataSfarsit) throws InvalidDatesException {
        this.institutie = institutie;
        this.nivel = nivel;
        this.medie = medie;
        setDataInceput(dataInceput);
        setDataSfarsit(dataSfarsit);
    }
    public void setDataInceput(String dataInceput) throws InvalidDatesException {

        //transformam stringul de data intr-un obiect de tip LocalDate
        StringTokenizer st = new StringTokenizer(dataInceput, ".");
        int an, luna, zi;
        zi = Integer.parseInt(st.nextToken());
        luna = Integer.parseInt(st.nextToken());
        an = Integer.parseInt(st.nextToken());

        LocalDate inceput = LocalDate.of(an, luna, zi);
        if (dataSfarsit != null) {
            if (inceput.isAfter(dataSfarsit))
                throw new InvalidDatesException("InvalidDatesException");
        }
        this.dataInceput = inceput;
    }

    public LocalDate getDataInceput() {
        return dataInceput;
    }

    public void setDataSfarsit(String dataSfarsit) throws InvalidDatesException {
        if (dataSfarsit != null) {
            StringTokenizer st = new StringTokenizer(dataSfarsit, ".");
            int zi, luna, an;
            zi = Integer.parseInt(st.nextToken());
            luna = Integer.parseInt(st.nextToken());
            an = Integer.parseInt(st.nextToken());
            LocalDate sfarsit = LocalDate.of(an, luna, zi);
            if (dataInceput != null) {
                if (sfarsit.isBefore(this.dataInceput))
                    throw new InvalidDatesException("InvalidDatesException");
            }
            this.dataSfarsit = sfarsit;
        }
    }

    public LocalDate getDataSfarsit() {
        return dataSfarsit;
    }

    public void setMedie(double medie) {
        this.medie = medie;
    }

    public double getMedie() {
        return medie;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }

    public void setInstitutie(String institutie) {
        this.institutie = institutie;
    }

    public String getInstitutie() {
        return institutie;
    }

    @Override
    public int compareTo(Education o) {
        if (o == null) throw new NullPointerException();
        if (dataSfarsit == null || o.getDataSfarsit() == null) {
            if (dataInceput.isAfter(o.getDataInceput()))
                return 1;
            if (dataInceput.isBefore(o.getDataInceput()))
                return -1;
            //datele sunt egale
            return 0;
        }
        if (dataSfarsit.isAfter(o.getDataSfarsit()))
            return -1;
        if (dataSfarsit.isBefore(o.getDataSfarsit()))
            return 1;
        //comparatie descrescatoare in functie de medie
        if (medie > o.getMedie())
            return -1;
        if (medie < o.getMedie())
            return 1;
        //mediile sunt egale
        return 0;
    }
    public String toString() {
        String s = "\t{ \n"+
                "\tlevel: " + getNivel() + "\n" +
                "\tname: " + getInstitutie() + "\n" +
                "\tstart_date: " + getDataInceput() + "\n" +
                "\tend_date: " + getDataSfarsit() + "\n" +
                "\tgrade: " + getMedie() + "\n\t}\n";
        return s;

    }
}
