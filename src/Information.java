
import java.util.HashMap;

public class Information {
    private String name;
    private String email;
    private String phone;
    private String birthday;
    private String genre;
    private HashMap<String, String> languages;

    Information() {
        languages = new HashMap<>();
    }

    public void addLanguage(String language, String level) {
        languages.put(language, level);
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public String getGenre() {
        return genre;
    }

    public HashMap<String, String> getLanguages() {
        return languages;
    }
}
