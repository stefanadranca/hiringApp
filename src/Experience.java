
import java.time.LocalDate;
import java.util.StringTokenizer;

public class Experience implements Comparable<Experience>{
    private LocalDate dataInceput;
    private LocalDate dataSfarsit;
    private String pozitie;
    private String companie;

    Experience(){
        dataInceput = null;
        dataSfarsit = null;
    }

    Experience(String companie, String pozitie, String dataInceput, String dataSfarsit) throws InvalidDatesException {
        this.companie = companie;
        this.pozitie = pozitie;
        setDataInceput(dataInceput);
        setDataSfarsit(dataSfarsit);
    }
    public void setDataInceput(String inceput) throws InvalidDatesException {
        //transformam stringul de data intr-un obiect de tip LocalDate
        StringTokenizer st = new StringTokenizer(inceput, ".");
        int an, luna, zi;
        zi = Integer.parseInt(st.nextToken());
        luna = Integer.parseInt(st.nextToken());
        an = Integer.parseInt(st.nextToken());
        dataInceput = LocalDate.of(an, luna, zi);
        if (dataSfarsit != null) {
            if (dataInceput.isAfter(dataSfarsit))
                throw new InvalidDatesException("InvalidDatesException");
        }
    }

    public LocalDate getDataInceput() {
        return dataInceput;
    }

    public void setDataSfarsit(String dataSfarsit) throws InvalidDatesException {
        if (dataSfarsit != null) {
            //transformam stringul de data intr-un obiect de tip LocalDate
            StringTokenizer st = new StringTokenizer(dataSfarsit, ".");
            int zi, luna, an;
            zi = Integer.parseInt(st.nextToken());
            luna = Integer.parseInt(st.nextToken());
            an = Integer.parseInt(st.nextToken());
            LocalDate sfarsit = LocalDate.of(an, luna, zi);
            if (dataInceput != null) {
                if (! sfarsit.isAfter(getDataInceput()))
                    throw new InvalidDatesException("InvalidDatesException");
                this.dataSfarsit = sfarsit;
            }
        }
    }

    public LocalDate getDataSfarsit() {
        return dataSfarsit;
    }

    public void setPozitie(String pozitie) {
        this.pozitie = pozitie;
    }

    public String getPozitie() {
        return pozitie;
    }

    public void setCompanie(String companie) {
        this.companie = companie;
    }

    public String getCompanie() {
        return companie;
    }

    @Override
    public int compareTo(Experience o) {
        if (o == null) throw new NullPointerException();
        if (dataSfarsit == null || o.getDataSfarsit() == null || dataSfarsit.isEqual(dataInceput)) {
            return this.companie.compareTo(o.getCompanie());
        }
        if (dataSfarsit.isAfter(o.getDataSfarsit()))
            return -1;
        if (dataSfarsit.isBefore(o.getDataSfarsit()))
            return 1;
        return 0;
    }
    public String toString() {
        String s = "\t{ \n"+
                "\tcompany: " + getCompanie() + "\n" +
                "\tposition: " + getPozitie() + "\n" +
                "\tstart_date: " + getDataInceput() + "\n" +
                "\tend_date: " + getDataSfarsit() + "\n\t}\n";
        return s;

    }
}
